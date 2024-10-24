package com.paidy.dar.interview

import org.apache.spark.sql._
import org.apache.spark.sql.functions.{col, concat_ws, count, first, lead, lit, max, min, row_number, sum, unix_timestamp, when, year}
import org.apache.spark.sql.types.{DataType, DoubleType, StructType}
import org.apache.spark.sql.expressions.Window

import java.sql.Timestamp

object SessionStatistics {

  val TargetTableName: String = "session_statistics"
  val TargetTableSchema: String =
    """
      |    session_id STRING NOT NULL,
      |    user_id STRING NOT NULL,
      |    session_end_at TIMESTAMP,
      |    session_start_at TIMESTAMP,
      |    num_events SHORT,
      |    device_id STRING,
      |    time_spent_in_shopping DOUBLE,
      |    session_end_year SHORT NOT NULL
      |""".stripMargin

  final def main(args: Array[String]): Unit = {
    val start_date = args(0)
    val end_date = args(1)

    val spark = createSparkSession()

    // create target table if not exists
    if (!spark.catalog.tableExists(TargetTableName)) {
      spark.createDataFrame(spark.sparkContext.emptyRDD[Row], DataType.fromDDL(TargetTableSchema).asInstanceOf[StructType])
        .write.partitionBy("session_end_year")
        .saveAsTable(TargetTableName)
    }

    // data from the source
    val sourceDF =
      spark.read.parquet("src/test/resources/app_events")
        .filter((col("event_timestamp") >= start_date) && (col("event_timestamp") < end_date))


    // if no activity from a user for 30 mins, then the last event before the period of inactivity represents the
    // end of their session
    val sessionExpirationThresholdMins = 30

    /*
     We will only assess that an event is the last in a session (and therefore the session has ended)
     if the most recent event is more than {sessionExpirationThresholdMins} mins earlier than the
     latest event in the mixpanel period.
      */
    val mostRecentTime = sourceDF.select(max(col("event_timestamp")).as("latest_time"))

    val eventTimeDistWindowSpec = Window.partitionBy("user_id").orderBy(col("event_timestamp").asc)

    val endedSessions = sourceDF.select("user_id", "event_timestamp").dropDuplicates()
      .crossJoin(mostRecentTime)
      .withColumn("next_event_or_max_tdist",
        when(lead(col("event_timestamp"), 1).over(eventTimeDistWindowSpec).isNull, unix_timestamp(col("latest_time")) - unix_timestamp(col("event_timestamp")))
          .otherwise(unix_timestamp(lead(col("event_timestamp"), 1).over(eventTimeDistWindowSpec)) - unix_timestamp(col("event_timestamp"))))
      .filter(col("next_event_or_max_tdist").cast("long").cast("Double").divide(60) > lit(sessionExpirationThresholdMins))
      .select(col("user_id"),
        col("event_timestamp").as("session_end_at"),
        concat_ws("__", col("user_id"), unix_timestamp(col("event_timestamp")).cast("string")).as("session_id"))

    val appEventsSessionLinked = sourceDF.join(endedSessions, Seq("user_id"), "inner")
      .withColumn("event_to_session_end_at_tdist", unix_timestamp(col("session_end_at")) - unix_timestamp(col("event_timestamp")))
      .filter(col("event_to_session_end_at_tdist") >= lit(0))
      .withColumn("session_rank",
        row_number().over(Window.partitionBy( "event_id").orderBy(col("event_to_session_end_at_tdist").asc)))
      .filter((col("session_rank") === 1) &&
        (col("session_end_at") >= start_date) &&
        (col("session_end_at") < end_date))

    val sessionStatsMain =
      appEventsSessionLinked
        .withColumn("page_stay_duration",
          lead(col("event_timestamp"), 1).over(Window.partitionBy(col("session_id")).orderBy(col("event_timestamp"))).cast(DoubleType) - col("event_timestamp").cast(DoubleType))
        .groupBy("session_id", "user_id", "session_end_at")
        .agg(min(col("event_timestamp")).alias("session_start_at"),
          count(col("event_name")).alias("num_events"),
          first(col("device_id")).alias("device_id"),
          sum(when(col("event_name").contains("shop"), col("page_stay_duration")).otherwise(lit(null))).alias("time_spent_in_shopping")
      )
        .withColumn("session_end_year", year(col("session_end_at")))

    sessionStatsMain.write.insertInto(TargetTableName)


    // TODO refactor for more flexible calculation of fraud features
  }

  private def createSparkSession(): SparkSession = {
    val builder = SparkSession.builder

    builder.enableHiveSupport()
    builder.master("local[4]")

    builder.getOrCreate()
  }
}

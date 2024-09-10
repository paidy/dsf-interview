# Data Science Foundations: Data Science Engineer Take-Home Exercise

We understand that your time is valuable and want to thank you for working on this exercise.
A major aspect of the role is to design and implement data pipelines. This exercise tries to cover some of these aspects.

Please note, that anything you build as part of this assignment is entirely owned by you in perpetuity.
We might store/archive your solution and use it internally for discussion and evaluation.

Please do not share the exercise nor your solution or any details about it with the public.


## Task

- Our marketing team wants to build samples of consumer accounts based on a variety of conditions (age, location, status, etc) for e-mail marketing campaigns.
- For this, we need to provide a `consumers` data table that reflects the current state and details of each consumer account.
- Create an ETL job in Scala/Spark that loads data from an event-based source and updates the `consumers` table accordingly.
- We've provided you with some basic boilerplate and the expected target schama in `src/main/scala/LoadConsumers.scala` to get you started.
- You'll find some example test data in `test/resources/consumer_events/`.
- The ETL job should support filtering the incoming data for a specific time interval.
  - For various reasons:
    - Restricting the data volume to be processed in a single job to only newly incoming events
    - Reloading historical data in case of errors or changes.
  - These arguments are passed as datetime-strings formatted as `yyyy-mm-dd hh:mm:ss`
  - The second argument defining the end of the interval is optional and defaults to the current time.
- The task description is vague on purpose. Make your own best guesses and assumptions, but document them.
- We don't expect you to provide a perfect and complete production ready solution. It's fine to take shortcuts, but you need to document them properly:
  - Document TODOs properly with concrete steps/requirements.
  - Document assumptions.
  - Document limitations.


## Notes on the structure of the source data

- The incoming data you'll be processing is event-based, where only modified fields are set.
- Target table's `status` column should be one of `enabled`, `disabled` or `closed`.


## Submission
- Push your solution to a private GitHub repository and invite the following GitHub users for read-access:
  - `bkkkk` (Jacobo Blanco)
  - `Chandan-Purbia-Paidy`
  - `Salim-Doost-Paidy`
- We will review your submission and schedule a call with you, where we can discuss your solution together.

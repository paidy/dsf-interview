# Data & Risk Division: Business Intelligence Engineer Take-Home Exercise

We understand that your time is valuable and want to thank you for working on this exercise.

A major aspect of the Business Intelligence Engineer role in our division at Paidy is being able to take quick-and-dirty or POC level solutions written by data scientists and develop and maintain robust ETL pipelines to scale the solutions and deliver them to the business reliably, especially finding ways where some definitions or common transformations can be standardized across multiple use cases. This assignment is based on a real world problem for a fictional Buy Now, Pay Later company called Spendy that is ripe for you to demonstrate that you can excel in this role.

Please note:

- anything you build as part of this assignment is entirely owned by you in perpetuity
- we might store/archive your solution and use it internally for discussion and evaluation
- we request that you do not share the exercise nor your solution or any details about it with the public

## Task

Spendy is a leading Buy Now, Pay Later service in Japan. Shoppers on many of Japan's leading e-commerce merchants and marketplaces have the option to use Spendy at checkout by logging in with just their email and phone number, and if approved, they can buy the requested basket of items right now, then get a bill the following calendar month that they can pay by cash settlement at convenience stores or bank transfer.

Like many parts of e-commerce, Spendy is occasionally a victim of different types of fraud that costs Spendy money and – depending on the type of fraud – negatively effects the experience of using Spendy as a shopper or merchant. In the past few yesrs, Spendy fraud team detected or customers reported various fraud MOs such as phishing, account take over (ATO) or first party abuse.

Spendy tries to stay on top of stopping fraud partially by identifying patterns in customer behavior and transaction data through internally designed and built monitoring system that trigger alerts which got sent to the operation team for manual review, sometimes leading to disabling Spendy accounts, and changing the decision made by Spendy's risk engine.

Some examples of these:
* The monitoring system lead Spendy agents to a group of abusers. After in-depth analysis, risk engine decision could be changed for a small segment of customers while not affecting other good customers too much.
* The monitoring system shows sign of ATO. The agents would suspend the potentially affected accounts, and the risk managers would discuss if a temporary protection against ATO would need to be put in.

One area which data is particularly hard to work with is Spendy's app activity data. In Spendy's app, users can pay bills, check their balance, manage their account, and shop for new products. Spendy collects data of every login, click, and page view event in the app with metadata about which Spendy customer it's for, what device they're using, how they're connected to the internet, and more.

Spendy's fraud data scientists/operation agents know intuitively, from reviewing fraud cases, the following examples include patterns that may be indicative of fraud and patterns that may be a strong indicator that an account with otherwise suspicious activity is in fact *not* fraudulent:

- a Spendy user logging into their account from multiple devices in the same day
- a Spendy user logging into their account from a new device when they haven't logged into their account in the App for a long time
- One device logging into multiple Spendy accounts over a short period of time
- a Spendy user after logging into their account from a new device, immediately perform key actions such as change account information or make expensive purchase
- a Spendy user spending more than 5 minutes looking at pages in the app that explain where they can use Spendy to shop (these pages have an event name prefixed with "Shop ")

This app login data is an enormous table of relatively raw event log data, and so setting up individual queries (and maintaining changes to their logic) for identifying these patterns has proven challenging for the fraud data scientists.

Included in this assignment is a sample ETL pipeline written with Spark for identifying how many app sessions per day a Spendy user has performed that include more than 5 minutes of shopping for new products, as that is where the fraud data scientists have left off on this problem. Also included is a sample dataset with the same schema as the large raw dataset the fraud data scientists have been querying to identify these patterns. From Intellij in the SBT console `run 2023-06-01 2023-07-01` should run the example pipeline successfully and write to a target local table.

Your job in this assignment is to propose to Spendy's data scientists changes to this ETL pipeline that can track some of the mentioned patterns as well as make it easy for you or some fraud data scientists to add additional tracking of new identified patterns from the same data source. With this solution built out, the fraud team at Spendy can more easily analyze these patterns and use the data sources for training machine learning models to predict which accounts are experiencing fraudulent activity.

We prefer assignment submissions in Scala, and this is the language we are using for Spark in many ETL and ML feature engineering projects at Paidy. However, if you are already a strong developer in PySpark without Scala experience, and you think your assignment will be hindered by having to switch languages, we are also ok with submissions in PySpark.

- We expect you to spend, at most, 6 hours on this exercise, once you've reached this limit:
    - Document TODOs properly.
    - Document assumptions.
    - Document limitations.


## Submission

- Push your solution to a private GitHub repository and invite the following GitHub users for read-access:
    - jared-adler-paidy, bkkkk, BrandonMassaroPaidy, mtleeghac, Matthew-Jones-Paidy, Salim-Doost-Paidy
- We will review your submission and schedule a call with you, where we can discuss your solution together.
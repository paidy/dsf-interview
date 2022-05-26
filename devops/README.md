# DevOps Engineer Assignment Brief

We understand that your time is valuable and want to thank you for working on this exercise.

A major aspect of the role is to design and implement the next generation of infrastructure and
CI/CD for data science at Paidy.
In this exercise you will be working to make the documentation for our internal Python package, PaidySuperAI, publicly available so that it's easy for our data scientists to review it while building the next generation ML models.

Please note, that anything you build as part of this assignment is entirely owned by you in perpetuity. 

We might store/archive your solution and use it internally for discussion and evaluation.

## The Assignment

For the source Python files in the subfolder `src/`, on any change within the source files,
the build pipeline (can be CircleCI, GitHub Actions, BitBucket pipelines, etc.) shall:

1. Generate browsable documentation for PaidySuperAI (preferably using pydoc or Sphinx)
2. Containerize the documentation and upload it to AWS ECR
3. Deploy it on AWS and make it publicly accessible / browsable

Please see the requirements below for additional restrictions.

## Requirements

Please keep the following things in mind:

* Approach the exercise as if you are working in a real production environment.
* Document and explain any assumptions and decisions you make, and any shortcuts you take (for
  example, due to time constraints) in the process.
* Use AWS, Terraform, and Python where applicable.
* The documentation must be containerized.
* Since PaidySuperAI is an internal package we want to limit access to the documentation, blocking
  all IPs outside the range `60.125.0.0/16`, `152.165.0.0/16` and `<jacobs-ip-range>`.
* Share the code, and documents you create or are going to present as part of this project in some
  public place (GitHub, Gitlab, BitBucket, etc… are all acceptable).```

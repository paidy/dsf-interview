# DevOps Engineer Assignment Brief

We understand that your time is valuable and want to thank you for working on this exercise.

A major aspect of the role is to design and implement the next generation of infrastructure and
CI/CD for data science at Paidy.
In this exercise you will be provided a dummy Python file, that is assumed to contain some
shared functionality, and work to make its documentation available for others to read.

Please note, that anything you build as part of this assignment is entirely owned by you in
perpetuity.

## The Assignment

For the source Python files in the subfolder `src/`, on any change within the source files,
the build pipeline (can be CircleCI, bitbucket pipelines, etc.) shall:

1. Generate browsable documentation for it (preferably using pydoc or Sphinx)
2. Containerize it and upload it to AWS ECR
3. Deploy it on AWS and make it publicly accessible / browsable
4. However, block any access to the documentation that is outside the IP range `60.125.0.0/16`,
   `<paidy-office-ip-range>` and `<jacobs-ip-range>`.

## Requirements

Please approach the exercise as if you are working in a real production environment.
Do not hesitate to ask any questions about the exercise, and please document and explain any
assumptions and decisions you make, and any shortcuts you take (for example, due to time
constraints) in the process.

Please share the code, and documents you create or are going to present as part of this project in
some public place (GitHub, Gitlab, BitBucket, etc… are all acceptable).

Please use AWS, terraform and python where applicable.

We might store/archive your solution and use it internally for discussion and evaluation.

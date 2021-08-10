# PAN Data Quality Challenge App (FIAP)

![Architecture](https://lucid.app/publicSegments/view/40c32a4a-e129-43da-a8c1-2a52d0eea33f/image.png)

The following project intends to implement a simple data quality pipeline, according to the flow down below:
1. A LOAD001.csv file is uploaded to the S3 bucket, below the "inbound/" directory;
2. The referring S3CreateEvent triggers a Lambda, which percolates the CSV stream, row-by-row filtering out any incorrect record/row;
3. The filtered out stream is then pushed upon the S3Client, which creates a brand new "outbound/" directory, appending a "LOAD001_CORRECT" below it;
4. Error logs are then created within the ErrorsTable with DynamoDB, provided with filepath, timestamp and a numeric set, corresponding to the row ids previously filtered out records, later to be analyzed;
5. It is, finally, possible to create an Athena table, based upon a view of the just recently create .csv file and, thus, analyze the records at will.

The project source includes function code and supporting resources:

- `src/main` - A Java function along with a minimal 3-tier set of components.
- `src/test` - A unit test and helper classes.
- `template.yml` - An AWS CloudFormation template that creates an application.
- `pom.xml` - A Maven build file.
- `1-create-bucket.sh`, `2-deploy.sh`, etc. - Shell scripts that use the AWS CLI to deploy and manage the application.

Use the following instructions to deploy the application.

# Requirements
- [Java 8 runtime environment (SE JRE)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3](https://maven.apache.org/docs/history.html)
- The Bash shell. For Linux and macOS, this is included by default. In Windows 10, you can install the [Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install-win10) to get a Windows-integrated version of Ubuntu and Bash.
- [The AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html).

# Setup
Download or clone this repository.

    $ git clone https://github.com/gianvittorio/pan-data-quality-challenge.git

To create a new bucket for deployment artifacts, run `1-create-bucket.sh`.

    s3-java$ ./1-create-bucket.sh
    make_bucket: lambda-artifacts-a5e491dbb5b22e0d


# Deploy
To deploy the application, run `3-deploy.sh`.

    s3-java$ ./2-deploy.sh
    BUILD SUCCESSFUL in 1s
    Successfully packaged artifacts and wrote output template to file out.yml.
    Waiting for changeset to be created..
    Successfully created/updated stack - s3-java

This script uses AWS CloudFormation to deploy the Lambda functions and an IAM role. If the AWS CloudFormation stack that contains the resources already exists, the script updates it with any changes to the template or function code.


# Test
To upload an image file to the application bucket and trigger the function, run `3-upload.sh`.

    s3-java$ ./3-upload.sh

...


# Cleanup
To delete the application, run `6-cleanup.sh`.

    blank$ ./4-cleanup.sh




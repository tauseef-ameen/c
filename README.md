# Project Introduction

## Demo Project: Uploading Documents to AWS S3 using Java Spring Boot

This project serves as a foundational example for demonstrating how to interact with AWS S3 using the AWS SDK for Java within a Spring Boot application. It's designed to be used as a reference in Kubernetes tutorials, showcasing the integration of AWS services with containerized applications.

## Key Features

- **Bucket Creation**: Creates an S3 bucket in the us-east-1 region.
- **Document Upload**: Uploads documents to a specified S3 bucket using a multipart form data POST request.
- **Bucket Deletion**: Deletes a specified S3 bucket.

## Endpoints

### Create Bucket

- **URL**: `/api/s3/create-bucket/{bucket-name}`
- **Method**: POST
- **Description**: Creates a new S3 bucket in the specified region.

  **Example:**
  ```bash
  curl --location 'http://localhost:8080/api/s3/create-bucket/my-bucket'

## Upload Document

- **URL**: `/api/s3/upload/{bucket-name}`
- **Method**: POST
- **Description**: Uploads a document to the specified S3 bucket.

  **Example:**
  ```bash
  curl --location 'http://localhost:8080/api/s3/upload/my-bucket' \
  --form 'file=@"/path/to/file"'

## Delete Bucket

- **URL**: `/api/s3/delete-bucket/{bucket-name}`
- **Method**: DELETE
- **Description**: Deletes the specified S3 bucket.

  **Example:**
  ```bash
  curl --location --request DELETE 'http://localhost:8080/api/s3/delete-bucket/my-bucket'

## Prerequisites

- Java 11 or later
- Apache Maven
- AWS account with appropriate permissions

## Getting Started

1. Clone the repository.
2. Install dependencies using `mvn clean install`.
3. Configure your AWS credentials in `application.properties`.
4. Run the application using `mvn spring-boot:run`.

## Additional Notes

- To build the Docker image, use the following command:
  ```bash
  mvn clean install -Pcreateimage
## To Run the Docker Container

```bash
 docker run -p 8080:8080 -e AWS_ACCESS_KEY_ID=<aws-access-key> -e AWS_SECRET_ACCESS_KEY=<your-access-key-id> secrets/awss3:0.0.1-SNAPSHOT
```

**Note:**

- Ensure your AWS credentials are stored securely. Consider using environment variables or Kubernetes Secrets to avoid exposing them in plain text.
- For more advanced use cases, explore additional features of the AWS SDK for Java, such as object versioning, lifecycle management, and encryption.

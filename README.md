# MLOps Pipeline Management API

## Client-Server Architectures Coursework

**Student:** Elif Eylül Ak (w2134947)
**Module:** 5COSC022W - Client-Server Architectures

---

# Project Overview

This project implements a RESTful API using Jakarta RESTful Web Services (JAX-RS). The API manages Machine Learning Workspaces, Machine Learning Models, and Evaluation Metrics. All data is stored in memory using Java collections (`HashMap` and `ArrayList`), without using a database, as required by the coursework.

---

# Technologies

- Java
- Jakarta RESTful Web Services (JAX-RS)
- Maven
- GlassFish Server
- JSON
- Postman

---

# Project Structure

Resources:

- DiscoveryResource
- WorkspaceResource
- ModelResource
- EvaluationMetricResource

Models:

- MLWorkspace
- MachineLearningModel
- EvaluationMetric

Other Classes:

- DataStore
- ErrorResponse
- WorkspaceNotEmptyException
- LinkedWorkspaceNotFoundException
- ModelDeprecatedException
- GlobalExceptionMapper
- APILoggingFilter

---

# Running the Project

1. Open the project in NetBeans.
2. Clean and Build the project.
3. Run the project using GlassFish Server.
4. Open Postman.
5. Test the API endpoints.

---

# API Endpoints

## Discovery

GET

```
/api/v1
```

---

## Workspaces

GET

```
/api/v1/workspaces
```

POST

```
/api/v1/workspaces
```

GET

```
/api/v1/workspaces/{id}
```

DELETE

```
/api/v1/workspaces/{id}
```

---

## Models

GET

```
/api/v1/models
```

GET

```
/api/v1/models?status=DEPLOYED
```

POST

```
/api/v1/models
```

---

## Metrics

GET

```
/api/v1/models/{modelId}/metrics
```

POST

```
/api/v1/models/{modelId}/metrics
```

---

# Sample cURL Commands

## Get API Information

```bash
curl http://localhost:8080/MLOpsPipelineAPI/api/v1
```

## Create Workspace

```bash
curl -X POST http://localhost:8080/MLOpsPipelineAPI/api/v1/workspaces \
-H "Content-Type: application/json" \
-d '{"id":"WS-001","teamName":"Computer Vision Lab","storageQuotaGb":100}'
```

## Get All Workspaces

```bash
curl http://localhost:8080/MLOpsPipelineAPI/api/v1/workspaces
```

## Create Model

```bash
curl -X POST http://localhost:8080/MLOpsPipelineAPI/api/v1/models \
-H "Content-Type: application/json" \
-d '{"id":"MOD-001","framework":"TensorFlow","status":"DEPLOYED","latestAccuracy":95.5,"workspaceId":"WS-001"}'
```

## Get Models by Status

```bash
curl "http://localhost:8080/MLOpsPipelineAPI/api/v1/models?status=DEPLOYED"
```

---

# Coursework Questions

## Part 1

### 1. Explain the role of a MessageBodyWriter or JSON provider.

A MessageBodyWriter converts Java objects into JSON before they are sent to the client. JSON providers such as Jackson perform automatic serialization and deserialization, allowing Java objects and JSON data to be exchanged easily.

### 2. What is Statelessness?

Statelessness means every request contains all the information required for processing. The server does not store client session information. This improves scalability because any server instance can process any request independently.

---

## Part 2

### 1. Cache-Control

Cache-Control headers allow clients to cache GET responses. This reduces unnecessary requests, improves response time, and decreases server workload.

### 2. HEAD Method

The HEAD method checks whether a resource exists without returning the response body. It is useful for saving bandwidth.

---

## Part 3

### 1. Why should the server generate IDs?

Server-generated IDs improve security, prevent duplicate identifiers, and maintain data integrity. UUIDs provide globally unique identifiers.

### 2. URL Encoding

Special characters and spaces must be URL encoded.

Example:

```
Scikit Learn & Tools
```

becomes

```
Scikit%20Learn%20%26%20Tools
```

This ensures the URL is interpreted correctly.

---

## Part 4

### 1. Class-level @Produces

Using @Produces at the class level reduces code duplication because all methods inherit the same media type. Individual methods can override it if necessary.

---

## Part 5

### 1. Why does validation return a 4xx status code?

Validation errors are caused by incorrect client requests, therefore they belong to the 4xx class rather than 5xx server errors.

### 2. Exception Mapper Selection

JAX-RS always selects the most specific ExceptionMapper available for the thrown exception. If no specific mapper exists, the global mapper handles the exception.

### 3. Useful Metadata for Logging

Examples include:

- HTTP Method
- Request URI
- Response Status Code
- Request Headers

---

# Conclusion

This project demonstrates the implementation of a RESTful API using JAX-RS. The API supports CRUD operations for workspaces and machine learning models, filtering models by status, managing evaluation metrics, custom exception handling, and request/response logging while following RESTful principles.

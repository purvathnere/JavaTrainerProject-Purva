#  Smart Training Platform – Java Trainer Machine Test

A Spring Boot-based training batch management system with automatic trainer allocation and batch recommendations for students.

---

##  Project Overview

This project was created as part of the Java Trainer Machine Test. It includes RESTful APIs to manage students, trainers, and batches, with added business logic to:

- Automatically assign the best available trainer to a batch
- Recommend suitable batches to students based on subject, past courses, and availability

---

## Features

-  Student, Trainer, and Batch entity modeling
-  Smart trainer assignment algorithm
-  Batch recommendation logic for students
-  CRUD APIs for all entities
-  Validation and exception handling
-  Docker support
-  Deployed live on Render

---

##  Project Structure
src/
└── main/
└── java/
└── com/purva/trainingplatform/
├── controller/
├── service/
├── repository/
├── model/
├── dto/
├── exception/
└── util/
Dockerfile
README.md
pom.xml


---

##  Questions 

### 1. What is Dependency Injection and how is it used in your app?

Dependency Injection is a way to give an object what it needs from outside instead of creating it itself. It helps in writing clean, testable code  


Example:
```java
public StudentController(StudentService studentService) {
    this.studentService = studentService;
}
```

### 2. How would you explain the trainer assignment logic to students?
The trainer assignment logic follows these steps:

Filter trainers who are experts in the batch subject.

Exclude trainers who are already booked during the batch period.

Sort remaining trainers:

Highest rating first

Then by lowest workload (fewer batches assigned)

### 3. Why did you choose your architecture and flow?
I used a layered architecture with:

Controller → handles HTTP requests

Service → contains business logic

Repository → handles database operations

This keeps code organized, testable, and easy to extend. It also follows industry-standard practices for Spring Boot apps.


### API Endpoints
## Student
POST /api/students – Add student

GET /api/students/{id} – Get student by ID

PUT /api/students/{id} – Update student

DELETE /api/students/{id} – Delete student

GET /api/students/{id}/recommendations – Get batch recommendations for student

##  Trainer
POST /api/trainers – Add trainer

(You can add more trainer endpoints as needed)

## Batch
POST /api/batches – Add batch

GET /api/batches/subject/{subject} – Get batches by subject

POST /api/batches/{batchId}/assign-trainer – Assign best trainer to batch

### Docker Instructions

```
docker build -t training-platform .
docker run -p 8080:8080 training-platform
```
## Deployment Instructions (Render)
1. Dockerfile in Root:

```
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/trainingplatform-0.0.1-SNAPSHOT.jar"]

```
## 2. Render Deployment Steps:

Go to https://render.com

1) New > Web Service

2) Connect GitHub repo: JavaTrainerProject-Purva

3) Fill:

Field	Value
Name	:purva-machine-test
Environment:	Docker
Branch	:main
Dockerfile Path	./Dockerfile
Port	:8080
Instance Type	:Free

4) Click Deploy



Final Links
🔗 GitHub Repo
https://github.com/purvathnere/JavaTrainerProject-Purva

🔗 Live Demo on Render
https://purva-machine-test.onrender.com

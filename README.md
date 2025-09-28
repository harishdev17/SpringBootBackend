# 🌐 HacktoberBlog - Spring Boot Backend

Welcome to the backend repository for **HacktoberBlog** – a simple blogging platform built with **Spring Boot** and **Firebase Firestore**. This project is beginner-friendly and perfect for your first open-source contribution during **Hacktoberfest**! 🎉

---

## 📌 Project Overview

This is the **backend service** for HacktoberBlog. It provides APIs to:

- 👤 Create, update, delete, and list users
- 📝 Create and retrieve blog posts
- ❤️ Like a blog
- 💬 Comment on a blog
- 📧 Send welcome emails to new users

Built using:
- Java + Spring Boot
- Firebase Firestore (NoSQL DB)
- Firebase Admin SDK
- Spring Boot Actuator & Mail
- Lombok for clean code

---

## 🗂️ Project Structure

```
src/
└── main/
├── java/com/hacktober/blog/
│ ├── blog/ → Blog service logic
│ ├── user/ → User service logic
│ ├── email/ → Email service logic
│ ├── config/ → Firebase initialization
│ └── utils/ → Utility functions (e.g., password encoding)
└── resources/
└── application.properties
```

---

## 📘 API Documentation

Interactive API documentation is available out of the box thanks to Swagger UI and the generated OpenAPI specification.

1. Run the application locally:

```bash
./mvnw spring-boot:run
```

2. Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) in your browser to explore and try the endpoints.

The raw OpenAPI document can be downloaded from [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) for integration with other tools.

---
## 🚀 Getting Started

### 1. Prerequisites

- Java 17+
- Maven
- Email SMTP (Gmail or others)
- Firebase Project (Firestore + Service Account)

### 2. Clone the repo

```bash
git clone https://github.com/HacktoberBlog/SpringBootBackend.git
cd SpringBootBackend
```

### 3. Firebase Setup

- Go to Firebase Console

- Create a project

- Enable Firestore

- Generate a Service Account Key JSON file

- Save the file in a secure path, e.g., /etc/secrets/firebaseServiceAccountKey.json

### 4. Configure Email 

Edit src/main/resources/application.properties:

```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password= 16 char app key such as abcd efgh ijkl mnop
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
redis.password= your redis db password
```

#### Getting Keys/Passwords for Email, DB, etc.
Gmail 16 char App Key: You can get the app key by navigating to google myaccount settings->search app passwords->give an app name-> create and copy the key

Redis Password: You can create a free db on [Redis](https://redis.io/). Once your db is created, click on the "Connect using Redis CLI, Client, or Insight" button. There you will be able to view your password (view/hide it using eye icon)

## ⚠️ **NOTE:**  
**🚫 DO NOT PUSH** the changes made in `application.properties`, `RedisConfig`, and `FirestoreService`.  
🔒 _Keep your secrets safe!_ 🔒

## 🚀 Live Demo  
🎉 The project is now LIVE! Check it out here: [HacktoberBlog Deployment](https://springbootbackend-onuz.onrender.com) 🌟  

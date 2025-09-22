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
spring.mail.password=16 char app key such as abcd efgh ijkl mnop
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

You can get the app key by navigating to google myaccount settings->search app passwords->give an app name-> create and copy the key
# SpringTaskOne
## 📌 GymApp – Spring Core Project

### 📖 Overview

GymApp is a Java 17 application built with **Spring Core** (no Spring Boot). It simulates a simple gym CRM with trainees, trainers, training types, and training sessions.
The project uses **object-based in-memory storage**, loads initial data from JSON files, and is fully tested using **JUnit 5**.

### ✅ Features

* **Spring Core (Java Config)** – No XML configuration.
* **Domain Models**:

  * `Trainee`
  * `Trainer`
  * `Training`
  * `TrainingType`
* **In-Memory Storage**:

  * Loads entities from JSON files on startup.
  * Stores them in `Map<String, Entity>` collections.
* **DAO Layer**:

  * CRUD-like operations for each entity.
* **Service Layer**:

  * Encapsulates business logic.
* **Facade Layer**:

  * Provides high-level operations for the app.
* **Utilities**:

  * `Helpers` class for generating usernames and passwords.
* **Logging**:

  * Configured with **Log4j2** via SLF4J.
  * Logs stored in `/tmp/gymapp/gymapp.log` locally and in `/home/ubuntu/app/app.log` on EC2.
* **Unit Testing**:

  * JUnit 5 tests for storage, DAOs, services, and helpers.

---

## 🚀 How It Works

1. **App Startup**:

   * The `InMemoryStorage` bean loads JSON files into memory using Jackson.
   * All entities are stored in maps for quick access.
2. **Services & Facades**:

   * Service layer validates and manages operations.
   * Facade layer provides user-facing functions (e.g., creating a trainee).
3. **Username/Password Generation**:

   * Unique usernames are created based on trainee names.
   * Secure random passwords are generated automatically.
4. **Logging**:

   * Every significant operation is logged to file and console.
   * EC2 deployments write to `/home/ubuntu/app/app.log`.

---

## 📦 Project Structure

```
gymapp/
 ├── src/main/java/com/gymapp
 │    ├── config/        # Spring JavaConfig
 │    ├── model/         # Domain models
 │    ├── dao/           # DAO interfaces & impls
 │    ├── service/       # Service interfaces & impls
 │    ├── facade/        # Facades for high-level API
 │    ├── storage/       # InMemoryStorage for JSON loading
 │    ├── util/          # Helpers for usernames/passwords
 │    └── AppRunner.java # Entry point
 ├── src/main/resources/
 │    ├── trainees.json
 │    ├── trainers.json
 │    ├── trainings.json
 │    └── training-types.json
 └── pom.xml
```

---

## 🔧 CI/CD Pipeline

We integrated **GitHub Actions** with **AWS EC2** to automate build, test, and deployment.

### Workflow:

1. **On every push to `main`**:

   * Runs Maven `clean package`.
   * Executes all unit tests.
   * Uploads JAR to EC2 using `appleboy/scp-action`.
   * Runs the app on EC2 using a deployment script.
   * Prints logs from EC2 directly into GitHub Actions console.

### EC2 Setup:

* Ubuntu instance with:

  * Java 17 installed.
  * SSH key configured for GitHub Secrets.
* The app runs and logs into:

  ```
  /home/ubuntu/app/gym-crm-spring-core-1.0-SNAPSHOT.jar
  /home/ubuntu/app/app.log
  ```

---

## 🧪 Testing

* **JUnit 5** for unit tests.
* Tests cover:

  * Data loading from JSON (`InMemoryStorageTest`)
  * DAO operations
  * Service logic
  * Helpers utility functions

Run tests locally:

```bash
mvn test
```

---

## ▶️ Running Locally

1. Clone the repository:

   ```bash
   git clone <repo-url>
   cd gymapp
   ```
2. Build the project:

   ```bash
   mvn clean package
   ```
3. Run the app:

   ```bash
   java -jar target/gym-crm-spring-core-1.0-SNAPSHOT.jar
   ```

---

## 🌐 Deployment on EC2

* Push changes to `main`.
* GitHub Actions deploys automatically.
* View logs via SSH:

  ```bash
  ssh -i SpringCore.pem ubuntu@<EC2-IP>
  cat /home/ubuntu/app/app.log
  ```

---

## 🏗 Tech Stack

* **Java 17**
* **Spring Core 6**
* **Maven**
* **JUnit 5**
* **Jackson (JSON)**
* **Lombok**
* **Log4j2**
* **AWS EC2**
* **GitHub Actions**

---

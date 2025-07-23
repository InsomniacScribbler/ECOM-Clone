# 🛍️ E-Commerce Backend Clone (Spring Boot + JWT + MySQL)

This project is a **learning-focused** E-Commerce backend clone built with Java and Spring Boot, following RESTful best practices, security standards, and clean code architecture. 

Ideal for backend developers who want to **build and scale** a real-world application with **Spring Boot**, **Spring Security 6**, and **JPA**.

---

## 🚀 Tech Stack

| Layer        | Tech                                  |
|--------------|----------------------------------------|
| Language     | Java 17+                               |
| Framework    | Spring Boot (Latest Stable)            |
| API Layer    | Spring Web (REST)                      |
| Security     | Spring Security 6 + JWT (stateless)    |
| ORM          | Spring Data JPA                        |
| DB           | MySQL                                  |
| Boilerplate  | Lombok                                 |
| Docs         | Swagger (OpenAPI 3)                    |
| API Testing  | Postman                                |
| Deployment   | AWS EC2 / Docker                       |

---

## 🧱 Features

### ✅ Core Modules
- **User Authentication** (JWT + BCrypt)
- **Role-Based Access** (`ADMIN`, `CUSTOMER`, `SELLER`)
- **Product Management**
- **Cart Management**
- **Order Placement**
- **Review System**

### 🔐 Security
- JWT Token generation + validation
- BCrypt Password Encoding
- Role-based API access via `@PreAuthorize`

### 📄 API Documentation
- Integrated **Swagger UI** at `/swagger-ui/index.html`
- JWT token header included for secured endpoints

### 🧪 Postman Collection
- Pre-configured Postman collection with:
  - Auth flow (register/login/token)
  - CRUD operations for Product, Cart, Orders
  - Pre-filled request bodies and JWT support

---

## 📁 Folder Structure

src/
└── main/
├── java/com/yourname/ecommerce/
│ ├── controller/
│ ├── service/
│ ├── repository/
│ ├── entity/
│ ├── dto/
│ ├── security/
│ └── config/
└── resources/
├── application.properties
└── application-prod.properties
+
---

## 🧑‍💻 Getting Started

### ✅ Prerequisites
- Java 17+
- Maven
- MySQL
- Postman
- Docker (optional)

### ⚙️ Setup (Local)
```bash
# Clone the repo
git clone https://github.com/<your-username>/ecommerce-backend.git

# Navigate to project directory
cd ecommerce-backend

# Add DB config in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

# Run the app
./mvnw spring-boot:run
☁️ AWS Deployment
🛠️ Manual Setup (EC2)
Install Java, MySQL

Upload the built .jar file

Setup application-prod.properties

Run with:

bash
Copy
Edit
java -jar ecommerce-backend.jar --spring.profiles.active=prod
🐳 Docker (Optional)
dockerfile
Copy
Edit
# Dockerfile included in repo
docker build -t ecommerce-backend .
docker run -p 8080:8080 ecommerce-backend
📬 Postman Collection
📁 postman/ folder contains the full Postman collection.
✅ Includes:

Login/Register

Product CRUD

Role-based endpoints

JWT pre-configured

✅ To-Do (Learning Progress)
 Spring Boot + JPA base setup

 JWT Security flow

 Role-based access guards

 Cart & Order logic

 Swagger integration

 Dockerfile + AWS EC2 deployment

 Unit & Integration Testing

 Frontend (React/Vite or plain HTML)

📘 Learning Goals
Deepen understanding of RESTful architecture

Practice secure API development with Spring Security 6

Build hands-on JWT-based authentication

Explore clean architecture and DTO separation

Learn AWS deployment workflows

💡 Author
Nikhil Singh
🔗 LinkedIn | 📧 singhnikhilsingh21@gmail.com
   

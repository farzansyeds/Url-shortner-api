# URL Shortener API 🔗

[//]: # (**Day 1 of 7-Day Backend Architecture Challenge**)

This is a RESTful API built with Java and Spring Boot that takes long, complex URLs and converts them into short, manageable links. When a user navigates to the short link, the application performs a fast database lookup and instantly redirects them to the original destination.

## 🚀 Features

*   **URL Shortening:** Generates a unique, randomized 6-character alphanumeric short code for any valid URL.
*   **Automatic Redirection:** Seamlessly intercepts short link requests and issues an HTTP 302 redirect to the target URL.
*   **In-Memory Storage:** Utilizes an H2 database for lightweight, fast data persistence during runtime.
*   **Object-Relational Mapping (ORM):** Uses Spring Data JPA to map Java entities directly to database tables without writing raw SQL.

## 🛠️ Tech Stack

*   **Language:** Java (JDK 17+)
*   **Framework:** Spring Boot 3
*   **Database:** H2 (In-Memory Database)
*   **ORM:** Spring Data JPA / Hibernate
*   **Build Tool:** Maven
*   **Testing Client:** Postman

## ⚙️ How It Works (Architecture)

1.  **Controller Layer:** The `UrlController` intercepts incoming HTTP requests (`POST` for creation, `GET` for redirection).
2.  **Service Layer:** The `UrlService` handles business logic. It generates a 6-character short code using a customized `StringBuilder` algorithm.
3.  **Data/Repository Layer:** The `UrlRepository` interface leverages Spring Data JPA to execute database operations (saving and retrieving URLs) based on simple method naming conventions (e.g., `findByShortCode`).

## 🚦 Getting Started

### Prerequisites
*   Java Development Kit (JDK) installed (Version 17 or higher recommended).
*   Maven installed (or use the included Maven wrapper).
*   An IDE like IntelliJ IDEA or Eclipse.

### Installation & Execution

1.  Clone this repository:
    ```bash
    git clone https://github.com/yourusername/url-shortener-api.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd url-shortener-api
    ```
3.  Run the application using your IDE or via Maven in the terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  The server will start locally on `http://localhost:8080`.

## 📖 API Documentation

### 1. Create a Short URL
Converts a long URL into a short code.

*   **Endpoint:** `/shorten`
*   **Method:** `POST`
*   **Content-Type:** `text/plain`
*   **Request Body:**
    ```text
    https://www.example-very-long-url.com/articles/2026/java-backend
    ```
*   **Success Response (200 OK):**
    ```text
    http://localhost:8080/aB3x9Q
    ```

### 2. Redirect to Original URL
Navigates the user to the original destination mapped to the short code.

*   **Endpoint:** `/{shortCode}`
*   **Method:** `GET`
*   **Success Response:** 
    *   `302 FOUND`
    *   *Headers:* `Location: https://www.example-very-long-url.com/...`
*   **Error Response:**
    *   `404 NOT FOUND` (If the short code does not exist in the database).

## 🔮 Future Improvements

*(Areas I plan to expand on in the future)*
*   Add custom short-code generation (user-defined aliases).
*   Implement a persistent database like PostgreSQL or MySQL.
*   Add analytics tracking (e.g., how many times a link was clicked).
*   Add input validation to ensure the submitted string is a valid HTTP/HTTPS URL.
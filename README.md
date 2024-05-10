Task Manager Application

This is a task manager application built using Spring Boot, Java 17, and PostgreSQL. The application allows users to manage their tasks through a RESTful API.

Features

Task Management: Create, update, delete, and view tasks.
User Authentication: Secure endpoints using JWT (JSON Web Tokens) authentication.
Database Integration: Store task data in a PostgreSQL database.

Technologies Used
Spring Boot: Java-based framework for building web applications.
Java 17: Latest version of Java programming language.
PostgreSQL: Open-source relational database management system.

Prerequisites
Java Development Kit (JDK) 17: Ensure Java 17 is installed on your machine.
PostgreSQL: Install PostgreSQL database server.
Maven: Dependency management tool for Java projects.
Getting Started

Follow these instructions to get the project up and running on your local machine.

1. Clone the Repository
bash
Copy code
git clone 
cd task-manager
2. Configure the Database
Create a PostgreSQL database for the application and update the database configuration in application.properties.

3. Build and Run the Application
bash
Copy code
mvn clean install
mvn spring-boot:run
The application will start on http://localhost:8081.

# Uber LLD Project

This is a Java project implementing a Low-Level Design (LLD) for an Uber-like system.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
uber-lld/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── Main.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
├── pom.xml
└── README.md
```

## Setup Instructions

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean install` to build the project
4. Run `mvn exec:java -Dexec.mainClass="com.example.Main"` to execute the main class

## Development

- The project uses Maven for dependency management and building
- JUnit 5 is included for testing
- The project follows standard Java package naming conventions 
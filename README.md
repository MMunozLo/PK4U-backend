<!--
<p align="center">
  <img src="public/logo-transparent.png" alt="PK4U Logo" width="120"/>   
</p>

<p align="center">
  <img src="https://miro.medium.com/v2/resize:fit:720/format:webp/1*-uckV8DOh3l0bCvqZ73zYg.png" alt="PK4U Logo" width="300"/>
</p>

<h1 align="center">
  <img src="https://github.com/Jefffer/pk4u-frontend/blob/main/public/logo-transparent.png" alt="PK4U Logo" width="50"/>  
  <span>PK4U</span>: Parking for You - Backend
</h1>

<p align="center">
  <img src="https://miro.medium.com/v2/resize:fit:720/format:webp/1*-uckV8DOh3l0bCvqZ73zYg.png" alt="java" width="220"/>
</p>
-->

<div align="center">
  <img src="https://github.com/Jefffer/pk4u-frontend/blob/main/public/logo-transparent.png?raw=true" alt="PK4U Logo" width="80"/>
  <h1>PK4U: Parking for You - Backend</h1>
  <p>
    <em>The core of the application's business logic and data management.</em>
  </p>
  <p>
    <img src="https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
    <img src="https://img.shields.io/badge/Spring_Cloud-3-green.svg?style=for-the-badge&logo=spring&logoColor=green" alt="Spring Cloud 3"/>
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge" alt="License: MIT"/>
  </p>
</div>

This repository contains the **Backend** of the PK4U system, the core of the application's business logic. Developed with **Java** and **Spring Boot**, this service is responsible for:

-   Exposing a **RESTful API** to be consumed by the Frontend.
-   Managing data persistence with **MongoDB**.
-   Processing parking spot status updates asynchronously via **RabbitMQ**.
-   Integrating with **Elasticsearch** to provide advanced search capabilities.
-   Registering with and discovering other services through the **Eureka Service Registry**.

## 🚀 Local Deployment

To run the backend service locally, you will need to have its dependency ecosystem active.

### **📋 Prerequisites**

-   **JDK 21** (or the version specified in the `pom.xml`).
-   **Maven** or **Gradle** for dependency management and project building.

### **1. External Dependencies** 📦

The backend needs to connect to the following services.

-   **MongoDB**: The primary database.
-   **RabbitMQ**: Message broker for asynchronous communication with the simulator.
-   **Elasticsearch**: Engine for text-based searches.
-   **Eureka Server**: Must be running for this service to register itself.

### **2. Clone the Repository ⬇️**

```bash
git clone [https://github.com/MMunozLo/PK4U-backend.git](https://github.com/MMunozLo/PK4U-backend.git)
cd PK4U-backend
```

### 3. Environment Setup 🛠️
The application's configuration is managed in the `src/main/resources/application.yml` file. Ensure that the connection properties for databases and external services are correct for your local environment.

Example `application.yml`:

```yaml
server:
  port: 8081 # Port for this microservice

spring:
  application:
    name: pk4u-ms # Service name to register in Eureka
  data:
    mongodb:
      uri: mongodb://localhost:27017/pk4u # URI for your MongoDB instance
  rabbitmq:
    host: localhost
    port: 5672
  elasticsearch:
    uris: http://localhost:9200

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka # URL of your Eureka Server
```

### 4. Running the Application ▶️
You can run the application using the Maven or Gradle plugin.

With Maven:

```bash
mvn spring-boot:run
```

With Gradle:

```bash
./gradlew bootRun
```

Once started, the service will register with **Eureka** and will be ready to receive requests through the **API Gateway**.

### 5. API Endpoints ⚙

This service exposes the following RESTful endpoints, which are routed through the API Gateway.

| Method | Endpoint                             | Description                                      |
|:-------|:-------------------------------------|:-------------------------------------------------|
| `GET`  | `/api/v1/parkings`                   | Retrieves a list of all parkings.                |
| `GET`  | `/api/v1/parkings/{parkingId}`       | Gets the details of a specific parking.          |
| `GET`  | `/api/v1/parkings/{parkingId}/spots` | Lists all spots for a specific parking.          |
| `PUT`  | `/api/v1/parkings/{parkingId}/spots/{spotId}` | Updates the occupancy status of a specific spot. |
| `GET`  | `/api/v1/search`                     | Searches for parkings using Elasticsearch.       |

### 6. Running the Full System 🌐
Keep in mind that the frontend is only the **presentation layer** of the PK4U system. for full functionality, you need to have all the backend services running.

Make sure to clone and run the following repositories in the recommended order:

| Order | Component             | Description                                                                                          | Repository                                                                    |
| :---: | --------------------- | ---------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
|   1   | **Eureka Server** | Essential service for dynamic discovery and registration of all microservices.                       | [eureka_service_tfm](https://github.com/gecamara/eureka_service_tfm)       |
|   2   | **API Gateway** | Single entry point for all requests, routing traffic from the frontend to the appropriate services.  | [gateway_service](https://github.com/gecamara/gateway_service)         |
|   3   | **PK4U Backend** | The core application that centralizes business logic and communicates with the database.             | [PK4U-backend](https://github.com/MMunozLo/PK4U-backend.git)         |
|   4   | **DB Scripts** | Scripts to initialize the MongoDB database with the required data structure.                         | [pk4u-db-scripts](https://github.com/Jefffer/pk4u-db-scripts)           |
|   5   | **Simulator** | Emulates IoT sensor behavior, generating and sending real-time parking occupancy data.               | [Simulator](https://github.com/MMunozLo/Simulator)                   |
|   6   | **PK4U Frontend** | Presentation layer of the PK4U system developed with React, Vite and TailwindCSS              | [pk4u-frontend](https://github.com/Jefffer/pk4u-frontend)                   |

---
## 🌟 What is PK4U?

In modern cities, finding parking has become a daily challenge that causes stress and unnecessarily increases traffic and pollution. This phenomenon, known as _cruising for parking_, negatively affects the quality of life and urban sustainability.

**PK4U** was created to address this problem by offering an open-source solution that centralizes and displays real-time parking availability in a city. Our platform unifies data from multiple parking facilities into a single interface with interactive maps, empowering drivers to make better decisions and contributing to smarter, more sustainable mobility.

### 💻 Core Technology Stack

| Área                | Tecnologías Clave                                                              |
| ------------------- | ------------------------------------------------------------------------------ |
| **Frontend** | `React` `Vite` `React Router` `Tailwind CSS` `Leaflet` `i18next`                 |
| **Backend** | `Java` `Spring Boot` `Spring Cloud`                                            |
| **Data & Search**| `MongoDB` `Elasticsearch`                                                      |
| **Communication** | `REST API` `RabbitMQ`                                                          |
| **Architecture** | `Microservicios` `API Gateway` `Service Registry (Eureka)`                     |

### 🤝 Contribution
Your help is welcome! If you wish to contribute to this script project, please feel free to:

* Open an **Issue** to report a problem or propose an improvement.
* Open a **Pull Request** with your changes and contributions.

### 📄 License
This project is distributed under an Open Source license, encouraging collaboration and transparency in the development of solutions for Smart Cities.

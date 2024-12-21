# Job, Company, and Reviews Microservices

This repository contains three independent microservices for managing jobs, companies, and reviews. Each service has its own database and provides CRUD operations through RESTful APIs.

---

## Features

### Job Service
- Manage job listings with fields such as title, description, salary, and location.
- Fetch job details along with associated company information via inter-service communication.

### Company Service
- Manage details about companies including name and description.

### Review Service
- Handle reviews related to jobs or companies.
- Add, update, and delete reviews.

---

## Technologies Used

- **Java** (Spring Boot Framework)
- **MySQL** (Relational Database)
- **JPA** (Java Persistence API)
- **RestTemplate** (Inter-service communication)
- **Postman** (API Testing)
- **Docker** (Optional for containerized deployment)

---

## Microservice Architecture

### Job Service
- **Base URL**: `/jobs`
- Manages job data such as title, description, location, and salary.
- Communicates with the Company Service to fetch company details.

### Company Service
- **Base URL**: `/companies`
- Manages company-related data.
- Provides CRUD operations for companies.

### Review Service
- **Base URL**: `/reviews`
- Handles user reviews for jobs or companies.

---

## API Endpoints

### Job Service
| Method | Endpoint              | Description                        |
|--------|-----------------------|------------------------------------|
| GET    | `/jobs`               | Fetch all jobs                    |
| POST   | `/jobs`               | Create a new job                  |
| GET    | `/jobs/{id}`          | Fetch job details by ID           |
| PUT    | `/jobs/{id}`          | Update job details                |
| DELETE | `/jobs/{id}`          | Delete job by ID                  |

### Company Service
| Method | Endpoint              | Description                        |
|--------|-----------------------|------------------------------------|
| GET    | `/companies`          | Fetch all companies               |
| POST   | `/companies`          | Create a new company              |
| GET    | `/companies/{id}`     | Fetch company details by ID       |
| PUT    | `/companies/{id}`     | Update company details            |
| DELETE | `/companies/{id}`     | Delete company by ID              |

### Review Service
| Method | Endpoint              | Description                        |
|--------|-----------------------|------------------------------------|
| GET    | `/reviews`            | Fetch all reviews                 |
| POST   | `/reviews`            | Add a new review                  |
| GET    | `/reviews/{id}`       | Fetch review details by ID        |
| PUT    | `/reviews/{id}`       | Update review details             |
| DELETE | `/reviews/{id}`       | Delete review by ID               |

---

## Installation and Setup

### Prerequisites
- **Java 11 or higher**
- **MySQL**
- **Maven**

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/job-company-review-services.git
   cd job-company-review-services
   ```

2. Set up databases:
   - Create three MySQL databases: `job_service`, `company_service`, and `review_service`.

3. Update configuration:
   - Modify the `application.yml` files in each service with your database credentials.

4. Build and run each service:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. Test APIs using Postman or any API testing tool.

---

## Future Enhancements

- Implement **API Gateway** for routing.
- Add **Eureka Server** for service discovery.
- Integrate **JWT Authentication** for securing APIs.
- Add **Docker Compose** for easier multi-service deployment.

---

## Authors

- **Rohan Saini**  
   Connect with me at [sainirohan830@gmail.com](mailto:sainirohan830@gmail.com)

---

This README file provides a detailed overview of the project, making it easier for contributors to understand and use the repository.

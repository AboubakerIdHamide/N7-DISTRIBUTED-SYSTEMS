# Bank microservices lesson

Four Maven modules from the video: a customer API, an account API, Eureka discovery, and a Spring Cloud Gateway. Each business service uses its own in-memory H2 database.

## Run

Start these from the project root in this order, each in a separate terminal:

```bash
./mvnw -pl discovery-service spring-boot:run
./mvnw -pl customer-service spring-boot:run
./mvnw -pl ebank-service spring-boot:run
./mvnw -pl gateway-service spring-boot:run
```

## Review endpoints

- Eureka dashboard: `http://localhost:8761`
- Customer service: `http://localhost:8056/customers` — [CustomerRestController.java](customer-service/src/main/java/net/sdia/customerservice/web/CustomerRestController.java)
- Account service: `http://localhost:8057/accounts` — [BankAccountRestController.java](ebank-service/src/main/java/net/sdia/ebankservice/web/BankAccountRestController.java)
- Gateway: `http://localhost:9999/customer-service/customers` and `http://localhost:9999/ebank-service/accounts`
- Swagger UI: `http://localhost:8056/swagger-ui/index.html` and `http://localhost:8057/swagger-ui/index.html`

Key concepts: independent services and databases, Eureka service discovery, dynamic gateway routes, OpenFeign calls, and a Resilience4j circuit breaker. The video’s later chatbot, Angular, Telegram, and security sections are outside this backend milestone.

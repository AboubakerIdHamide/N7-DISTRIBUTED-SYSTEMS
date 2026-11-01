# Bank microservices lesson

The project follows the course videos: customer and account services, Eureka discovery, a Spring Cloud Gateway, and an `ebankbot` that connects Spring AI to the services through MCP.

## Run

Start these from the project root in this order, each in a separate terminal:

```bash
./mvnw -pl discovery-service spring-boot:run
./mvnw -pl customer-service spring-boot:run
./mvnw -pl ebank-service spring-boot:run
./mvnw -pl gateway-service spring-boot:run
./mvnw -pl ebankbot spring-boot:run
```

The chatbot module runs on port `8058`. Set `OPENAI_API_KEY`, `DISCORD_TOKEN`, and `TELEGRAM_BOT_TOKEN` before starting it; the bot integrations need credentials from their respective platforms.

## Review endpoints

- Eureka dashboard: `http://localhost:8761`
- Customer service: `http://localhost:8056/customers` — [CustomerRestController.java](customer-service/src/main/java/net/sdia/customerservice/web/CustomerRestController.java)
- Account service: `http://localhost:8057/accounts` — [BankAccountRestController.java](ebank-service/src/main/java/net/sdia/ebankservice/web/BankAccountRestController.java)
- Gateway: `http://localhost:9999/customer-service/customers` and `http://localhost:9999/ebank-service/accounts`
- Chatbot: `http://localhost:8058/chat?query=List%20the%20customers` — [EbankAIAgent.java](ebankbot/src/main/java/net/sdia/app/ebankbot/agents/EbankAIAgent.java) and [EbankChatbotController.java](ebankbot/src/main/java/net/sdia/app/ebankbot/controllers/EbankChatbotController.java)
- Swagger UI: `http://localhost:8056/swagger-ui/index.html` and `http://localhost:8057/swagger-ui/index.html`

Key concepts: service discovery, gateway routing, OpenFeign, circuit breakers, MCP tool exposure and discovery, chat memory, and chat clients. MCP tools are annotated in [CustomerService.java](customer-service/src/main/java/net/sdia/customerservice/services/CustomerService.java) and [EBankService.java](ebank-service/src/main/java/net/sdia/ebankservice/services/EBankService.java).

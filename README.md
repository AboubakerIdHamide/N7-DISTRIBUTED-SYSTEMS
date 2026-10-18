# Distributed Systems Projects

Quick revision map for the two projects in this folder.

## `ebank` — banking API

**Stack:** Java 17, Spring Boot, Spring Data JPA/REST, H2, GraphQL, OpenAPI.

**Review path:**

1. Domain model and persistence: [BankAccount](ebank/src/main/java/org/sid/bankaccountservice/entities/BankAccount.java), [repositories](ebank/src/main/java/org/sid/bankaccountservice/repositories/BankAccountRepository.java).
2. Service layer and REST: [AccountServiceImpl](ebank/src/main/java/org/sid/bankaccountservice/service/AccountServiceImpl.java), [AccountRestController](ebank/src/main/java/org/sid/bankaccountservice/web/AccountRestController.java).
3. GraphQL schema, resolver, and errors: [schema](ebank/src/main/resources/graphql/schema.graphqls), [controller](ebank/src/main/java/org/sid/bankaccountservice/web/BankAccountGraphQLController.java), [exception handler](ebank/src/main/java/org/sid/bankaccountservice/web/GraphQLExceptionHandler.java).

## `ecom-ms-app` — e-commerce microservices

**Stack:** Java 21, Spring Boot, Spring Cloud, Maven, Eureka, Spring Cloud Gateway, Config Server, OpenFeign, Resilience4j, JPA, H2, Actuator.

**Review path:**

1. Service discovery: [Eureka app](ecom-ms-app/discovery-service/src/main/java/net/app/discoveryservice/DiscoveryServiceApplication.java).
2. Central configuration and refresh: [Config Server](ecom-ms-app/config-service/src/main/java/net/app/configservice/ConfigServiceApplication.java), [config files](ecom-ms-app/config-repo/application.properties), [client refresh demo](ecom-ms-app/customer-service/src/main/java/net/app/customerservice/web/ConfigTestRestController.java).
3. API routing: [Gateway routes](ecom-ms-app/gateway-service/src/main/resources/application.yml), [discovery routes](ecom-ms-app/gateway-service/src/main/java/net/app/gatewayservice/GatewayServiceApplication.java).
4. Service-to-service calls and resilience: [Feign clients](ecom-ms-app/billing-service/src/main/java/net/app/billingservice/feign/CustomerServiceRestClient.java), [fallbacks](ecom-ms-app/billing-service/src/main/java/net/app/billingservice/feign/InventoryServiceRestClient.java).
5. Billing data and API: [entities](ecom-ms-app/billing-service/src/main/java/net/app/billingservice/entities/Bill.java), [REST controller](ecom-ms-app/billing-service/src/main/java/net/app/billingservice/web/BillRestController.java).

**Suggested order:** discovery → config → gateway → service calls → resilience.

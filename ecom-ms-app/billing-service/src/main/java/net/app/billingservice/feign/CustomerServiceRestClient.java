package net.app.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.app.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {

    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id, Exception exception) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("default customer");
        customer.setEmail("default@gmail.com");
        exception.printStackTrace();
        return customer;
    }
}

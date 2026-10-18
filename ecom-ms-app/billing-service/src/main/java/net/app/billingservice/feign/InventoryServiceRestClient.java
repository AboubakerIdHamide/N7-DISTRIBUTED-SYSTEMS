package net.app.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.app.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceRestClient {

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getDefaultProduct")
    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id);

    default Product getDefaultProduct(Long id, Exception exception) {
        exception.printStackTrace();
        return Product.builder()
                .id(id)
                .build();
    }
}

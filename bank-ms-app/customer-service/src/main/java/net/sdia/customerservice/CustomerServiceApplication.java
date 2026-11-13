package net.sdia.customerservice;

import net.sdia.customerservice.entities.Customer;
import net.sdia.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initCustomers(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Mohamed", "mohamed@gmail.com"));
            customerRepository.save(new Customer(null, "Imane", "imane@gmail.com"));
            customerRepository.save(new Customer(null, "Yassine", "yassine@gmail.com"));
        };
    }
}

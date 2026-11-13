package net.sdia.customerservice.services;

import net.sdia.customerservice.entities.Customer;
import net.sdia.customerservice.repositories.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find customer by ID")
    public Customer findCustomerById(@McpToolParam(description = "customer ID") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not found"));
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @McpTool(description = "New customer")
    public Customer saveCustomer(@McpToolParam(description = "new customer with its name and email") Customer customer) {
        return customerRepository.save(customer);
    }
}

package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository,
                            CustomerRepository customerRepository) {
        return args -> {
            for (int customerIndex = 0; customerIndex < 10; customerIndex++) {
                Customer customer = new Customer();
                customer.setName("Customer " + customerIndex);
                customer = customerRepository.save(customer);

                for (int accountIndex = 0; accountIndex < 10; accountIndex++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5
                                    ? AccountType.CURRENT_ACCOUNT
                                    : AccountType.SAVING_ACCOUNT)
                            .balance(10_000 + Math.random() * 90_000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            }
        };
    }
}

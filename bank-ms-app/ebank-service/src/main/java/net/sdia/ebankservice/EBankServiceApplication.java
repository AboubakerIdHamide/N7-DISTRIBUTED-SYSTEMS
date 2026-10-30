package net.sdia.ebankservice;

import net.sdia.ebankservice.entities.BankAccount;
import net.sdia.ebankservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class EBankServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EBankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initAccounts(BankAccountRepository bankAccountRepository) {
        return args -> {
            for (long customerId = 1; customerId <= 3; customerId++) {
                for (int i = 0; i < 2; i++) {
                    String type = Math.random() < 0.5 ? "CURRENT_ACCOUNT" : "SAVING_ACCOUNT";
                    double balance = 1_000 + Math.random() * 60_000;
                    bankAccountRepository.save(new BankAccount(
                            UUID.randomUUID().toString(), new Date(), balance, type, customerId, null));
                }
            }
        };
    }
}

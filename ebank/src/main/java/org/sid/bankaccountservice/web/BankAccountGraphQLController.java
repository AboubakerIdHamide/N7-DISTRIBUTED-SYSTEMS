package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;

    public BankAccountGraphQLController(BankAccountRepository bankAccountRepository,
                                        AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }

    @QueryMapping
    public List<BankAccount> accounts() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument("bankAccountDTO") BankAccountRequestDTO bankAccountDTO) {
        return accountService.addAccount(bankAccountDTO);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument("id") String id,
                                                @Argument("bankAccountDTO") BankAccountRequestDTO bankAccountDTO) {
        return accountService.updateAccount(id, bankAccountDTO);
    }

}

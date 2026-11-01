package net.sdia.ebankservice.services;

import net.sdia.ebankservice.entities.BankAccount;
import net.sdia.ebankservice.feign.CustomerRestClient;
import net.sdia.ebankservice.repositories.BankAccountRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EBankService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    public EBankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> getAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        accounts.forEach(this::loadCustomer);
        return accounts;
    }

    public Optional<BankAccount> getAccount(String id) {
        return bankAccountRepository.findById(id).map(this::loadCustomer);
    }

    public List<BankAccount> getAccountsByCustomerId(Long customerId) {
        List<BankAccount> accounts = bankAccountRepository.findByCustomerId(customerId);
        accounts.forEach(this::loadCustomer);
        return accounts;
    }

    public BankAccount saveAccount(BankAccount bankAccount) {
        return loadCustomer(bankAccountRepository.save(bankAccount));
    }

    @McpTool(description = "get all bank accounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "get bank account by ID")
    public BankAccount getBankAccountById(@McpToolParam(description = "bank account ID") String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @McpTool(description = "save bank account")
    public BankAccount saveBankAccount(@McpToolParam(description = "Bank account with its balance, type and customer ID") BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    private BankAccount loadCustomer(BankAccount bankAccount) {
        bankAccount.setCustomer(customerRestClient.getCustomer(bankAccount.getCustomerId()));
        return bankAccount;
    }
}

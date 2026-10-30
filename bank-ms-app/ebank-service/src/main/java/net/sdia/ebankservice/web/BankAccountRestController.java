package net.sdia.ebankservice.web;

import net.sdia.ebankservice.entities.BankAccount;
import net.sdia.ebankservice.services.EBankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class BankAccountRestController {
    private final EBankService eBankService;

    public BankAccountRestController(EBankService eBankService) {
        this.eBankService = eBankService;
    }

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return eBankService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Optional<BankAccount> getAccount(@PathVariable String id) {
        return eBankService.getAccount(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAccountsByCustomer(@PathVariable Long customerId) {
        return eBankService.getAccountsByCustomerId(customerId);
    }

    @PostMapping
    public BankAccount saveAccount(@RequestBody BankAccount bankAccount) {
        return eBankService.saveAccount(bankAccount);
    }
}

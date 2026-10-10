package org.sid.bankaccountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.sid.bankaccountservice.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;
}

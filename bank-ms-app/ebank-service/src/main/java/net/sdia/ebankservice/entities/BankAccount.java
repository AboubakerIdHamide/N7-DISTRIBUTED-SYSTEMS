package net.sdia.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sdia.ebankservice.feign.Customer;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private double balance;
    private String type;
    private Long customerId;
    @Transient
    private Customer customer;
}

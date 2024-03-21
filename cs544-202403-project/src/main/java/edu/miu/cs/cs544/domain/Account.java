package edu.miu.cs.cs544.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    
    private BigDecimal balance;
    private double defaultBalance = 100;

    @ManyToMany
    private Set<Member> members;

//    @ManyToOne
//    private Scanner scanner;

    public Account() {}

   public Account(String description, String name, AccountType accountType, BigDecimal balance, Set<Account> Accounts, Set<Member> members) {
        this.description = description;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
        this.defaultBalance = defaultBalance;
        this.members = members;
    }

}

package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class AccountPayload implements Serializable {

    private static final long serialVersionUID = 1L; // Recommended for Serializable classes


    private Long id;

    private String description;
    private String name;


    private AccountType accountType;

    private BigDecimal balance;

//    private Long scannerId;
//
//    private Set<Member> members;

    // Constructors, Getters, Setters, etc.
}


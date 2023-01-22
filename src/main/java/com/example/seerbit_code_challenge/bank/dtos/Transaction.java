package com.example.seerbit_code_challenge.bank.dtos;

import com.example.seerbit_code_challenge.bank.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Transaction {
    private String timestamp;
    private BigDecimal amount;

}

package com.c1736.userservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransferDTO {

    private BankDTO fromAccount;
    private BankDTO toAccount;
    private BigDecimal amount;
    private String description;
    private LocalDateTime transferData;
}

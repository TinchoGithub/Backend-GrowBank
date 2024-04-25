package com.c1736.userservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {

    private Long idAccount;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}

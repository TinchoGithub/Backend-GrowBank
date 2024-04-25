package com.c1736.bankservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequestDto {

    private Long idAccount;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

}

package com.c1736.bankservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {

    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
    private String description;

}

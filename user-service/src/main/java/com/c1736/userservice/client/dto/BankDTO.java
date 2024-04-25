package com.c1736.userservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BankDTO {
    private String email;
    private AccountTypeEnum accountType;
    private String balance;
    private TypeCoinEnum typeCoin;

}

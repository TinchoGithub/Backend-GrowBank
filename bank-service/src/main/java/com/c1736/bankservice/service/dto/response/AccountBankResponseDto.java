package com.c1736.bankservice.service.dto.response;

import com.c1736.bankservice.entities.AccountTypeEnum;
import com.c1736.bankservice.entities.TypeCoinEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountBankResponseDto {

    private Long id;
    private String email;
    private AccountTypeEnum accountType;
    private String balance;
    private TypeCoinEnum typeCoin;
}

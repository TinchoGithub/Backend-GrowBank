package com.c1736.bankservice.service.dto.request;

import com.c1736.bankservice.entities.AccountTypeEnum;
import com.c1736.bankservice.entities.TypeCoinEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAccountBankRequestDto {

    private Long id;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @NotNull(message = "El tipo de moneda es obligatorio")
    @Enumerated(EnumType.STRING)
    private TypeCoinEnum typeCoin;
}

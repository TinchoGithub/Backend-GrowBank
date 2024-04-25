package com.c1736.bankservice.service.mapper.response;

import com.c1736.bankservice.entities.AccountBank;
import com.c1736.bankservice.service.dto.response.AccountBankResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAccountBankResponseMapper {

    AccountBankResponseDto toResponseAccountBankOptional(Optional<AccountBank> accountBank);
    AccountBankResponseDto toResponseAccountBank(AccountBank accountBank);
    AccountBank toEntityResponse(AccountBankResponseDto accountBankResponseDto);
}

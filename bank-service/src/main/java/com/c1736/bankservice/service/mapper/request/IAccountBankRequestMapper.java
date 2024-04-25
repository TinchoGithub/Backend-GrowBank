package com.c1736.bankservice.service.mapper.request;

import com.c1736.bankservice.entities.AccountBank;
import com.c1736.bankservice.service.dto.request.AccountBankRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAccountBankRequestMapper {
    AccountBank toAccountBankRequest(AccountBankRequestDto accountBankRequestDto);
    AccountBankRequestDto toEntity(AccountBank accountBank);
}

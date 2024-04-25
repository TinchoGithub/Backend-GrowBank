package com.c1736.bankservice.controller;

import com.c1736.bankservice.client.IUserFeignClient;
import com.c1736.bankservice.client.dto.UserDTO;
import com.c1736.bankservice.service.dto.request.AccountBankRequestDto;
import com.c1736.bankservice.service.dto.request.UpdateAccountBankRequestDto;
import com.c1736.bankservice.service.dto.response.AccountBankResponseDto;
import com.c1736.bankservice.service.exceptions.UnauthorizedException;
import com.c1736.bankservice.service.exceptions.UserNotFound;
import com.c1736.bankservice.service.impl.AccountBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final AccountBankService accountBankService;
    private final IUserFeignClient userFeignClient;

    public CompanyController(AccountBankService accountBankService, IUserFeignClient userFeignClient) {
        this.accountBankService = accountBankService;
        this.userFeignClient = userFeignClient;
    }

    @PostMapping("/account")
    public ResponseEntity<Void> saveAccount(@RequestBody AccountBankRequestDto accountBankRequestDto) {
        UserDTO user = userFeignClient.getUserCompany(accountBankRequestDto.getEmail());

        if (user == null) throw new UserNotFound();
        if (!user.getRole().getName().equals("ROLE_COMPANY")) throw new UnauthorizedException();

        accountBankService.saveAccount(accountBankRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody UpdateAccountBankRequestDto updateAccountBankRequestDto) {
        updateAccountBankRequestDto.setId(id);
        accountBankService.updateAccount(updateAccountBankRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Void> deleteAccountBank(@PathVariable Long id) {
        AccountBankResponseDto accountBankResponseDto = accountBankService.getAccountBank(id);
        UserDTO user = userFeignClient.getUserCompany(accountBankResponseDto.getEmail());

        if (user == null) throw new UserNotFound();
        if (!user.getRole().getName().equals("ROLE_ADMIN")) {
            if (!user.getRole().getName().equals("ROLE_COMPANY")) {
                throw new UnauthorizedException();
            }
        }
        accountBankService.deleteAccountBank(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

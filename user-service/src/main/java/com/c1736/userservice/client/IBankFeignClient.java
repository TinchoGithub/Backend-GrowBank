package com.c1736.userservice.client;

import com.c1736.userservice.client.dto.BankDTO;
import com.c1736.userservice.client.dto.DepositDTO;
import com.c1736.userservice.client.dto.TransferDTO;
import com.c1736.userservice.client.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name = "bank-service", url = "http://bank-service:8092/api/v1/", configuration = FeignClientInterceptor.class)
public interface IBankFeignClient {

    @GetMapping("admin/{id}")
    public ResponseEntity<BankDTO> getAccountBank(@PathVariable Long id);

    @GetMapping("admin/listAccounts")
    public ResponseEntity<List<BankDTO>> getAllAccountBank();

    @DeleteMapping("admin/deleteAccount/{id}")
    public ResponseEntity<Void> deleteAccountBank(@PathVariable Long id);

    @PostMapping("admin/account")
    public ResponseEntity<Void> saveAccount(@RequestBody BankDTO accountBankRequestDto);

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody BankDTO updateAccountBankRequestDto);

    @PostMapping("client/account")
    public ResponseEntity<Void> saveAccountClient(@RequestBody BankDTO accountBankRequestDto);

    @PostMapping("company/account")
    public ResponseEntity<Void> saveAccountCompany(@RequestBody BankDTO accountBankRequestDto);

    @PostMapping("accounts/transfer")
    public ResponseEntity<TransferDTO> transfer(@RequestBody TransferDTO transferRequestDto);

    @PostMapping("accounts/deposit")
    public ResponseEntity<DepositDTO> Deposit(@RequestBody DepositDTO depositDTO);
}

package com.c1736.bankservice.controller;

import com.c1736.bankservice.service.IAccountBankService;
import com.c1736.bankservice.service.dto.request.DepositRequestDto;
import com.c1736.bankservice.service.dto.request.TransferRequestDto;
import com.c1736.bankservice.service.dto.response.DepositResponseDto;
import com.c1736.bankservice.service.dto.response.TransferResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final com.c1736.bankservice.service.IAccountBankService accountBankService;
    @Autowired
    public AccountController(IAccountBankService accountBankService){
        this.accountBankService = accountBankService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDto> transfer(@RequestBody TransferRequestDto transferRequestDto){
        try {
            accountBankService.Transfer(transferRequestDto);
            return ResponseEntity.ok(new TransferResponseDto("Transferencia realizada con éxito."));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new TransferResponseDto("Error durante la transferencia: " + e.getMessage()));
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDto> Deposit(@RequestBody DepositRequestDto depositRequestDto){
        try {
            accountBankService.deposit(depositRequestDto);
            return ResponseEntity.ok(new DepositResponseDto("Deposito realizado con éxito"));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(new DepositResponseDto("Error durante el deposito: "+ex.getMessage()));
        }
    }

}

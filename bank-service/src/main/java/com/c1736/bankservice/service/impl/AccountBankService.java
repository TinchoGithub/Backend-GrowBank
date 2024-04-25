package com.c1736.bankservice.service.impl;

import com.c1736.bankservice.client.IMessagingFeignClient;
import com.c1736.bankservice.client.dto.MessagingDTO;
import com.c1736.bankservice.configuration.Constants;
import com.c1736.bankservice.entities.AccountBank;
import com.c1736.bankservice.entities.AccountTransfer;
import com.c1736.bankservice.repository.AccountTransferRepository;
import com.c1736.bankservice.repository.IAccountBankRepository;
import com.c1736.bankservice.service.IAccountBankService;
import com.c1736.bankservice.service.dto.request.AccountBankRequestDto;
import com.c1736.bankservice.service.dto.request.DepositRequestDto;
import com.c1736.bankservice.service.dto.request.TransferRequestDto;
import com.c1736.bankservice.service.dto.request.UpdateAccountBankRequestDto;
import com.c1736.bankservice.service.dto.response.AccountBankResponseDto;
import com.c1736.bankservice.service.exceptions.AccountBankNotFound;
import com.c1736.bankservice.service.exceptions.UnauthorizedException;
import com.c1736.bankservice.service.mapper.request.IAccountBankRequestMapper;
import com.c1736.bankservice.service.mapper.response.IAccountBankResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountBankService implements IAccountBankService {
    private final IAccountBankRepository accountBankRepository;
    private final IAccountBankRequestMapper accountBankRequestMapper;
    private final IAccountBankResponseMapper accountBankResponseMapper;
    private final IMessagingFeignClient messagingFeignClient;
    private final AccountTransferRepository accountTransferRepository;

    public AccountBankService(IAccountBankRepository accountBankRepository, IAccountBankRequestMapper accountBankRequestMapper, IAccountBankResponseMapper accountBankResponseMapper, IMessagingFeignClient messagingFeignClient, AccountTransferRepository accountTransferRepository) {
        this.accountBankRepository = accountBankRepository;
        this.accountBankRequestMapper = accountBankRequestMapper;
        this.accountBankResponseMapper = accountBankResponseMapper;
        this.messagingFeignClient = messagingFeignClient;
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    public AccountBankResponseDto getAccountBank(Long id) {
        AccountBank account = accountBankRepository.findById(id).orElseThrow(() -> new AccountBankNotFound());
        return accountBankResponseMapper.toResponseAccountBank(account);
    }

    @Override
    public List<AccountBankResponseDto> getAllAccountBank() {
        List<AccountBank> accountBanks = accountBankRepository.findAll();
        if (accountBanks.isEmpty()) {
            throw new AccountBankNotFound();
        }
        return accountBanks.stream()
                .map(accountBankResponseMapper::toResponseAccountBank)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAccount(AccountBankRequestDto accountBankRequestDto) {
        // Establecer el saldo en cero y darle formato adecuado
        String balanceFormat = "0";
        accountBankRequestDto.setBalance(balanceFormat);

        AccountBank accountBank = accountBankRequestMapper.toAccountBankRequest(accountBankRequestDto);
        accountBankRepository.save(accountBank);

//        MessagingDTO messageDto = new MessagingDTO(accountBankRequestDto.getEmail(), "CUENTA CREADA EXITOSAMENTE", Constants.NEW_ACCOUNT);
//        messagingFeignClient.sendEmail(messageDto);
    }

    @Override
    public void updateAccount(UpdateAccountBankRequestDto updateAccountBankRequestDto) {
        Optional<AccountBank> optionalAccountBank = accountBankRepository.findById(updateAccountBankRequestDto.getId());

        if (optionalAccountBank.isPresent()) {
            AccountBank accountBank = optionalAccountBank.get();

            // Validar si el usuario que realiza la actualización es el propietario de la cuenta
            if (!accountBank.getEmail().equals(updateAccountBankRequestDto.getEmail())) {
                throw new UnauthorizedException();
            }
            
            accountBank.setAccountType(updateAccountBankRequestDto.getAccountType());
            accountBank.setTypeCoin(updateAccountBankRequestDto.getTypeCoin());
            accountBankRepository.save(accountBank);
        } else {
            throw new AccountBankNotFound();
        }
    }

    @Override
    public void deleteAccountBank(Long id) {
        try {
            accountBankRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AccountBankNotFound();
        }
    }

    @Override
    public void Transfer(TransferRequestDto transferRequestDto) {
        AccountBank fromAccount =accountBankRepository.findById(transferRequestDto.getFromAccount())
                .orElseThrow(AccountBankNotFound::new);

        AccountBank toAccount = accountBankRepository.findById(transferRequestDto.getToAccount())
                .orElseThrow(AccountBankNotFound::new);

        if(fromAccount.getBalance().compareTo(transferRequestDto.getAmount())<0){
            throw new IllegalArgumentException("Saldo insuficiente para realizar la transferencia");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequestDto.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequestDto.getAmount()));

        AccountTransfer transfer = new AccountTransfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(transferRequestDto.getAmount());
        transfer.setDescription(transferRequestDto.getDescription());
        transfer.setTransferDate(LocalDateTime.now());

        accountTransferRepository.save(transfer);
        accountBankRepository.save(fromAccount);
        accountBankRepository.save(toAccount);

//        MessagingDTO messageDto = new MessagingDTO(fromAccount.getEmail(), "TRANSFERENCIA EXITOSA", Constants.NEW_TRANSFER+"Monto: "+transferRequestDto.getAmount()+"\n Destino: "+ toAccount.getEmail());
//        messagingFeignClient.sendEmail(messageDto);

    }

    @Override
    public void deposit(DepositRequestDto depositRequestDto) {
        AccountBank toAccount = accountBankRepository.findById(depositRequestDto.getIdAccount())
                .orElseThrow(AccountBankNotFound::new);
        if(depositRequestDto.getAmount().compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Ingresa un valor correcto");
        }

        toAccount.setBalance(toAccount.getBalance().add(depositRequestDto.getAmount()));
        accountBankRepository.save(toAccount);

//        MessagingDTO messagingDTO = new MessagingDTO(toAccount.getEmail(), "DEPOSITO EXITOSO", Constants.NEW_DEPOSIT+" Monto: "+ depositRequestDto.getAmount());
//        messagingFeignClient.sendEmail(messagingDTO);

    }

}

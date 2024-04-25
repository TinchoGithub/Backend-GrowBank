package com.c1736.bankservice.repository;

import com.c1736.bankservice.entities.AccountBank;
import com.c1736.bankservice.entities.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountBankRepository extends JpaRepository<AccountBank, Long> {
}

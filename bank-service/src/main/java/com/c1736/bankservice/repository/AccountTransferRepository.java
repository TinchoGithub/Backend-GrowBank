package com.c1736.bankservice.repository;

import com.c1736.bankservice.entities.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
}

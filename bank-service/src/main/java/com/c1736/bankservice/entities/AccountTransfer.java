package com.c1736.bankservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fromAccountId", referencedColumnName = "id")
    private AccountBank fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccountId", referencedColumnName = "id")
    private AccountBank toAccount;

    private BigDecimal amount;
    private String description;
    private LocalDateTime transferDate;




}

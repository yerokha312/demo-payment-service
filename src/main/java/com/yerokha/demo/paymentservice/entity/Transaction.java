package com.yerokha.demo.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "payer_wallet_id")
    private Wallet payer;

    @ManyToOne
    @JoinColumn(name = "payee_wallet_id")
    private Wallet payee;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}

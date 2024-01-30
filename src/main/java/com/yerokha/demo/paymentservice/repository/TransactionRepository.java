package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.entity.Transaction;
import com.yerokha.demo.paymentservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}

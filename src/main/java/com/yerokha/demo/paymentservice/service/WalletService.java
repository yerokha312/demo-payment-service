package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import com.yerokha.demo.paymentservice.entity.Wallet;
import com.yerokha.demo.paymentservice.exception.EntityNotFoundException;
import com.yerokha.demo.paymentservice.exception.InsufficientFundsException;
import com.yerokha.demo.paymentservice.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Transactional
    public Wallet create(String username) {
        AppUserDetails userDetails = userService.getByUsername(username);
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setAppUserDetails(userDetails);
        wallet.setUsername(username);
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(String username) {
        return walletRepository.findWalletByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));
    }

    @Transactional
    public Wallet topUp(String username, BigDecimal amount) {
        Wallet wallet = getWallet(username);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return wallet;
    }

    @Transactional
    public void debit(Wallet wallet, BigDecimal paymentAmount) {
        if (wallet.getBalance().compareTo(paymentAmount) < 0) {
            throw new InsufficientFundsException("Not enough money in the wallet");
        }

        wallet.setBalance(wallet.getBalance().subtract(paymentAmount));
        walletRepository.save(wallet);
    }

    @Transactional
    public void credit(Wallet wallet, BigDecimal paymentAmount) {
        wallet.setBalance(wallet.getBalance().add(paymentAmount));
        walletRepository.save(wallet);
    }
}

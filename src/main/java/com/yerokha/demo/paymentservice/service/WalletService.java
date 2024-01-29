package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import com.yerokha.demo.paymentservice.entity.Wallet;
import com.yerokha.demo.paymentservice.exception.EntityNotFoundException;
import com.yerokha.demo.paymentservice.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    public void create(String username, String name) {
        AppUserDetails userDetails = userService.getByUsername(username);
        Wallet wallet = new Wallet();
        wallet.setName(name);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setAppUserDetails(userDetails);
        wallet.setActive(true);
        walletRepository.save(wallet);
    }

    public Set<Wallet> getAll(String username) {
        return walletRepository.findByAppUserDetails_User_UsernameAndActive(username, true);
    }

    public Wallet getById(String username, Long walletId) {
        return walletRepository.findByAppUserDetails_User_UsernameAndWalletId(username, walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));
    }

    public Wallet topUp(String username, Long walletId, BigDecimal amount) {
        Wallet wallet = getById(username, walletId);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return wallet;
    }

    public void deactivate(String username, Long walletId) {
        Wallet wallet = getById(username, walletId);
        if (!Objects.equals(wallet.getBalance(), BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Wallet balance must be zero to delete");
        }
        wallet.setActive(false);
        walletRepository.save(wallet);
    }
}

package com.yerokha.demo.paymentservice.controller;

import com.yerokha.demo.paymentservice.entity.Wallet;
import com.yerokha.demo.paymentservice.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(Authentication authentication, @RequestBody String name) {
        walletService.create(authentication.getName(), name);
    }

    @GetMapping("/all")
    public Set<Wallet> getAll(Authentication authentication) {
        return walletService.getAll(authentication.getName());
    }

    @GetMapping("/{walletId}")
    public Wallet getById(Authentication authentication, @PathVariable Long walletId) {
        return walletService.getById(authentication.getName(), walletId);
    }

    @PutMapping("/top-up/{walletId}")
    public String topUp(Authentication authentication, @PathVariable Long walletId, @RequestBody BigDecimal amount) {
        Wallet wallet = walletService.topUp(authentication.getName(), walletId, amount);
        return "Your wallet %s balance is: %s".formatted(wallet.getName(), wallet.getBalance());
    }

    @DeleteMapping("/{walletId}")
    public void delete(Authentication authentication, @PathVariable Long walletId) {
        walletService.deactivate(authentication.getName(), walletId);
    }
}

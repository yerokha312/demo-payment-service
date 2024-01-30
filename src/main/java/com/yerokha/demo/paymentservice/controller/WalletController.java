package com.yerokha.demo.paymentservice.controller;

import com.yerokha.demo.paymentservice.entity.Wallet;
import com.yerokha.demo.paymentservice.service.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/")
    public Wallet getWallet(Authentication authentication) {
        return walletService.getWallet(authentication.getName());
    }

    @PutMapping("/top-up")
    public String topUp(Authentication authentication, @RequestBody BigDecimal amount) {
        Wallet wallet = walletService.topUp(authentication.getName(), amount);
        return "Your wallet balance is: " + wallet.getBalance();
    }
}

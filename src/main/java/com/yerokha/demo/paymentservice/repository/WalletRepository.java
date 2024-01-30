package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByUsername(String username);
}

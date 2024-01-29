package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Set<Wallet> findByAppUserDetails_User_UsernameAndActive(String username, Boolean active);

    Optional<Wallet> findByAppUserDetails_User_UsernameAndWalletId(String username, Long walletId);
}

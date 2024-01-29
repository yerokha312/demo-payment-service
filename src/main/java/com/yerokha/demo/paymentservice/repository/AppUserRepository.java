package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}

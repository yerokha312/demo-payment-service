package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDetailsRepository extends JpaRepository<AppUserDetails, Long> {
}

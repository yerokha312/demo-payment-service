package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}

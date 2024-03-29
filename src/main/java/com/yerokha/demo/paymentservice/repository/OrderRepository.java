package com.yerokha.demo.paymentservice.repository;

import com.yerokha.demo.paymentservice.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<PaymentOrder, UUID> {
}

package com.yerokha.demo.paymentservice.entity;

import com.yerokha.demo.paymentservice.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_order")
@Data
public class PaymentOrder {

    @Id
    @Column(name = "payment_order_id")
    private UUID orderId;

    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "requester")
    private String requester;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}

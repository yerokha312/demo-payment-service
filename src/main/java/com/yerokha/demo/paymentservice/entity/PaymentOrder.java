package com.yerokha.demo.paymentservice.entity;

import com.yerokha.demo.paymentservice.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @Column(name = "report_uri")
    private String reportUri;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private AppUserDetails userDetails;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}

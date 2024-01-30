package com.yerokha.demo.paymentservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Receipt(String info, BigDecimal amount, LocalDateTime dateTime) {
}

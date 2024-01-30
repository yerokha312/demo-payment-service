package com.yerokha.demo.paymentservice.dto;

import com.yerokha.demo.paymentservice.enums.Status;

public record OrderResponse(Long orderId, Status status) {
}

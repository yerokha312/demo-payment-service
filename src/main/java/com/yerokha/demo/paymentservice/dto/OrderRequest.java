package com.yerokha.demo.paymentservice.dto;

import java.math.BigDecimal;

public record OrderRequest(Long requestId, String requester, BigDecimal amount) {
}

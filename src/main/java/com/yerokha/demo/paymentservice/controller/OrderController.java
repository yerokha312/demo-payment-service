package com.yerokha.demo.paymentservice.controller;

import com.yerokha.demo.paymentservice.dto.OrderRequest;
import com.yerokha.demo.paymentservice.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public String create(@RequestBody OrderRequest request) {
        return orderService.create(request);
    }

    @PutMapping("/pay/{walletId}/{orderId}")
    public String pay(Authentication authentication, @PathVariable Long walletId, @PathVariable String orderId) {
        return orderService.pay(authentication.getName(), walletId, orderId);
    }
}

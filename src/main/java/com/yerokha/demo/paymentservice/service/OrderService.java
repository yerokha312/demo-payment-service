package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.dto.OrderRequest;
import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import com.yerokha.demo.paymentservice.entity.PaymentOrder;
import com.yerokha.demo.paymentservice.enums.OrderStatus;
import com.yerokha.demo.paymentservice.exception.EntityNotFoundException;
import com.yerokha.demo.paymentservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public String create(OrderRequest request) {
        PaymentOrder paymentOrder = new PaymentOrder();

        paymentOrder.setOrderId(UUID.randomUUID());
        paymentOrder.setRequestId(request.requestId());
        paymentOrder.setRequester(request.requester());
        paymentOrder.setDateTime(LocalDateTime.now());
        paymentOrder.setAmount(request.amount());
        paymentOrder.setStatus(OrderStatus.PENDING);

        return orderRepository.save(paymentOrder).getOrderId().toString();
    }

    public String pay(String username, Long walletId, String orderId) {
        PaymentOrder paymentOrder = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(
                () -> new EntityNotFoundException("Order not found")
        );

        AppUserDetails userDetails = userService.getByUsername(username);

        return null;
    }

}

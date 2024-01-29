package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.dto.OrderRequest;
import com.yerokha.demo.paymentservice.entity.Order;
import com.yerokha.demo.paymentservice.enums.OrderStatus;
import com.yerokha.demo.paymentservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String create(OrderRequest request) {
        Order order = new Order();

        order.setOrderId(UUID.randomUUID());
        order.setRequestId(request.requestId());
        order.setRequester(request.requester());
        order.setDateTime(LocalDateTime.now());
        order.setAmount(request.amount());
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order).getOrderId().toString();
    }
//
//    public String pay(String username, String orderId) {
//
//    }
}

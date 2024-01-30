package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.dto.OrderRequest;
import com.yerokha.demo.paymentservice.dto.OrderResponse;
import com.yerokha.demo.paymentservice.dto.Receipt;
import com.yerokha.demo.paymentservice.entity.PaymentOrder;
import com.yerokha.demo.paymentservice.entity.Transaction;
import com.yerokha.demo.paymentservice.entity.Wallet;
import com.yerokha.demo.paymentservice.enums.Status;
import com.yerokha.demo.paymentservice.exception.EntityNotFoundException;
import com.yerokha.demo.paymentservice.exception.InsufficientFundsException;
import com.yerokha.demo.paymentservice.exception.InvalidPaymentException;
import com.yerokha.demo.paymentservice.repository.OrderRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final WalletService walletService;
    private final RestTemplate restTemplate;
    private final TransactionService transactionService;

    public OrderService(OrderRepository orderRepository, UserService userService, WalletService walletService, RestTemplate restTemplate, TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.walletService = walletService;
        this.restTemplate = restTemplate;
        this.transactionService = transactionService;
    }

    public String create(OrderRequest request) {
        PaymentOrder paymentOrder = new PaymentOrder();

        paymentOrder.setOrderId(UUID.randomUUID());
        paymentOrder.setReportUri(request.reportUri());
        paymentOrder.setRequestId(request.requestId());
        paymentOrder.setUserDetails(userService.getByUsername(request.requester()));
        paymentOrder.setDateTime(LocalDateTime.now());
        paymentOrder.setAmount(request.amount());
        paymentOrder.setStatus(Status.PENDING);

        return orderRepository.save(paymentOrder).getOrderId().toString();
    }

    @Transactional
    public Receipt pay(String payerUsername, String paymentOrderId) {
        PaymentOrder paymentOrder = orderRepository.findById(UUID.fromString(paymentOrderId)).orElseThrow(
                () -> new EntityNotFoundException("Payment order not found")
        );
        if (!paymentOrder.getStatus().equals(Status.PENDING)) {
            throw new InvalidPaymentException("Payment order already paid");
        }
        Wallet payerWallet = walletService.getWallet(payerUsername);
        Wallet payeeWallet = paymentOrder.getUserDetails().getWallet();
        BigDecimal paymentAmount = paymentOrder.getAmount();
        try {
            walletService.debit(payerWallet, paymentAmount);
            walletService.credit(payeeWallet, paymentAmount);
            paymentOrder.setStatus(Status.PAID);

        } catch (InsufficientFundsException e) {
            paymentOrder.setStatus(Status.FAILED);
            orderRepository.save(paymentOrder);
            throw new InsufficientFundsException(e.getMessage());
        }
        Transaction transaction = new Transaction();
        transaction.setPayer(payerWallet);
        transaction.setPayee(payeeWallet);
        transaction.setPaymentOrder(paymentOrder);
        transaction.setAmount(paymentAmount);
        transaction.setDateTime(LocalDateTime.now());
        transactionService.save(transaction);
        paymentOrder.setTransaction(transaction);
        orderRepository.save(paymentOrder);

        sendPaymentResponse(paymentOrder);

        String info = payeeWallet.getAppUserDetails().getFirstName() + " " + payeeWallet.getAppUserDetails().getLastName();
        return new Receipt(info, paymentAmount, paymentOrder.getDateTime());
    }

    private void sendPaymentResponse(PaymentOrder paymentOrder) {
        OrderResponse orderResponse = new OrderResponse(
                paymentOrder.getRequestId(),
                paymentOrder.getStatus()
        );
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<OrderResponse> httpEntity = new HttpEntity<>(orderResponse, headers);
        restTemplate.postForEntity(paymentOrder.getReportUri(), httpEntity, String.class);
    }


}




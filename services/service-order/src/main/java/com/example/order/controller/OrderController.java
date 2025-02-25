package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope    // 自动刷新注解
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/config")
    public String config() {
        return "timeout: " + orderTimeout + ", auto-confirm: " + orderAutoConfirm;
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam ("productId") Long productId,
                             @RequestParam ("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}

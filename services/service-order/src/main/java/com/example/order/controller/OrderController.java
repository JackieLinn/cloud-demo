package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam ("productId") Long productId,
                             @RequestParam ("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}

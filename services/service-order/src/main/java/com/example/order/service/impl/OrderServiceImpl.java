package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
        // TODO: 总金额
        order.setTotalAmount(new BigDecimal(0));
        order.setUserId(userId);
        order.setNickName("JackieLinn");
        order.setAddress("云南大学");
        // TODO: 远程查询商品列表
        order.setProductList(null);
        return order;
    }
}

package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import com.example.product.entity.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    DiscoveryClient discoveryClient;

    @Resource
    RestTemplate restTemplate;

    @Resource
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        // 总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("JackieLinn");
        order.setAddress("云南大学");
        // 远程查询商品列表
        order.setProductList(List.of(product));
        return order;
    }

    // 默认取第一个ip进行访问
    private Product getProductFromRemote(Long productId) {
        // 1. 获取到商品服务所在的所有机器 IP 和 Port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 远程url
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求: {}", url);

        // 2. 给远程发送请求，发送Get方法
        return restTemplate.getForObject(url, Product.class);
    }

    // 负载均衡进行访问
    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("负载均衡远程请求: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }

    // 使用注解来配置负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {
        // 写微服务名字即可
        String url = "http://service-product/product/" + productId;
        log.info("注解负载均衡远程请求: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }
}

package com.example.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {

    @LoadBalanced   // 负载均衡注解
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

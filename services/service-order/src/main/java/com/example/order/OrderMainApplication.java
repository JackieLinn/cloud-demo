package com.example.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    // 1. 项目启动就监听配置文件变化
    // 2. 发送变化后拿到的变化值
    // 3. 发送邮件
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager manager){
        return args -> {
            ConfigService configService = manager.getConfigService();
            // 配置文件不一定是application里面写好的，可以是任意的
            configService.addListener("service-order.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    // 监听任务在线程池运行，假设是4个线程池
                    return Executors.newFixedThreadPool(4);
                }

                // 接收配置信息
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("变化配置信息 = " + configInfo);
                }
            });
        };
    }
}

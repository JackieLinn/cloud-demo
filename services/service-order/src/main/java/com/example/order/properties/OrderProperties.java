package com.example.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 需要写成Bean，批量绑定前缀是order的配置，下面的属性和前缀后面一样即可，短横线写成驼峰命名
// 无需要 @RefreshScope
@ConfigurationProperties(prefix = "order")
@Component
@Data
public class OrderProperties {
    String timeout;
    String autoConfirm;
}

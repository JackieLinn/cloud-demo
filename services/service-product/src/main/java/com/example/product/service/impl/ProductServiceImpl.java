package com.example.product.service.impl;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal(99));
        product.setProductName("苹果" + productId);
        product.setNum(2);
        return product;
    }
}

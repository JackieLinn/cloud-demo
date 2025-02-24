package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Resource
    ProductService productService;

    // 查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }
}

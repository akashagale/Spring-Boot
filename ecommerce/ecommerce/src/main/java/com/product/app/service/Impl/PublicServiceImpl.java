package com.product.app.service.Impl;

import com.product.app.entity.Product;
import com.product.app.repository.ProductRepo;
import com.product.app.service.PublicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicServiceImpl implements PublicService {

    private ProductRepo productRepo;

    public PublicServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = this.productRepo.findAll();
        return productList;
    }
}

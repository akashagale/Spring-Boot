package com.app.ecommerce.service.impl;

import com.app.ecommerce.entity.Product;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.repo.ProductRepo;
import com.app.ecommerce.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicServiceImpl implements PublicService {

    private final ProductRepo productRepo;

    @Autowired
    public PublicServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProducts(String productName) {
        return this.productRepo.findByProductName(productName);
    }

    @Override
    public List<Product> searchProduct(String productName) {
        List<Product> productList = this.productRepo.searchProduct(productName);
        System.out.println(productList.toString());
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = this.productRepo.findById(id);
        if (product.isEmpty()){
            throw  new ResourceNotFoundException("Product not found");
        }
        return product.get();
    }
}

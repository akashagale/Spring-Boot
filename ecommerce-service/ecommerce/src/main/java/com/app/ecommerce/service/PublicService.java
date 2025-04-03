package com.app.ecommerce.service;

import com.app.ecommerce.entity.Product;
import java.util.List;

public interface PublicService {
    public List<Product> getProducts(String productName);

    List<Product> searchProduct(String productName);

    Product findById(Integer id);
}

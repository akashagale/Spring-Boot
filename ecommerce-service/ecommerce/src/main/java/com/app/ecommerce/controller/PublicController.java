package com.app.ecommerce.controller;


import com.app.ecommerce.entity.Product;
import com.app.ecommerce.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicController {

    private final PublicService publicService;

    @Autowired
    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }


    @GetMapping("/test/{name}")
    public ResponseEntity<?> test(@PathVariable String name) {
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/product/search/{keyword}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable(name = "keyword") String keyword) {
        List<Product> productList = this.publicService.getProducts(keyword);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable(name = "keyword") String keyword) {
        List<Product> productList = this.publicService.searchProduct(keyword);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Integer id) {
        Product product = this.publicService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }




}

package com.product.app.restcontrollers;


import com.product.app.entity.Product;
import com.product.app.service.PublicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private PublicService publicService;

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = this.publicService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}

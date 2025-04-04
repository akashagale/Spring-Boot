package com.app.ecommerce.controller;


import com.app.ecommerce.dto.ProductDto;
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

    @GetMapping("search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable(name = "keyword") String keyword) {
        List<ProductDto> productDtoList = this.publicService.searchProduct(keyword);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("searchby/{keyword}")
    public ResponseEntity<List<ProductDto>> searchByProductNameOrCategory(@PathVariable(name = "keyword") String keyword) {
        List<ProductDto> productDtoList = this.publicService.searchProductNameOrCategoryName(keyword);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }


    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") Integer id) {
        ProductDto productDto = this.publicService.findById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }




}

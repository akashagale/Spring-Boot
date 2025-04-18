package com.app.ecommerce.controller;


import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/public")
public class PublicController {

    private final PublicService publicService;

    @Autowired
    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<ProductDto>> searchProducts(
//           @Valid @RequestParam(name = "keyword", required = true) String keyword) {
//        List<ProductDto> productDtoList = this.publicService.searchProduct(keyword);
//        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
//    }

    @GetMapping("/searchby")
    public ResponseEntity<List<ProductDto>> searchByProductNameOrCategory
            (@RequestParam(name = "keyword", required = true) String keyword) {
        List<ProductDto> productDtoList = this.publicService.searchProductNameOrCategoryName(keyword);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") Integer id) {
        ProductDto productDto = this.publicService.findById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

}

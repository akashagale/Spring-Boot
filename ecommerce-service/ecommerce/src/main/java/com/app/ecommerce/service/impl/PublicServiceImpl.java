package com.app.ecommerce.service.impl;

import com.app.ecommerce.converter.Converter;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Product;
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

    public PublicServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductDto> searchProduct(String productName) {
        List<Product> product = this.productRepo.searchProduct(productName);
        return Converter.ProductListToProductDtoList(product);
    }

    @Override
    public List<ProductDto> searchProductNameOrCategoryName(String keyword) {
        List<Product> productList = this.productRepo.searchProductNameOrCategoryName(keyword,keyword);
        return Converter.ProductListToProductDtoList(productList);
    }

    @Override
    public ProductDto findById(Integer id) {
        Optional<Product> product = this.productRepo.findById(id);
        if (product.isEmpty()){
            throw  new ResourceNotFoundException("Product not found");
        }
        ProductDto productDto = Converter.OptionalProductToProductDto(product);
        return productDto;
    }
}

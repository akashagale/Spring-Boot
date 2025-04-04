package com.app.ecommerce.service;

import com.app.ecommerce.dto.ProductDto;

import java.util.List;

public interface PublicService {

    List<ProductDto> searchProduct(String productName);

    List<ProductDto> searchProductNameOrCategoryName(String keyword);

    ProductDto findById(Integer id);
}

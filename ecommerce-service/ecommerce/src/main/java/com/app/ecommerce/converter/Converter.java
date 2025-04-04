package com.app.ecommerce.converter;

import com.app.ecommerce.dto.CategoryDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Category;
import com.app.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Converter {

    public static ProductDto ProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setCategoryDto(CategoryToCategoryDto(product.getCategory()));
        return productDto;
    }

    private static CategoryDto CategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }

    public static List<ProductDto> ProductListToProductDtoList(List<Product> productList) {
        ArrayList<ProductDto> productDtoDtoList = new ArrayList<>();
        for (com.app.ecommerce.entity.Product product : productList) {
            productDtoDtoList.add(ProductToProductDto(product));
        }
        return productDtoDtoList;
    }

    public static ProductDto OptionalProductToProductDto(Optional<Product> product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.get().getProductId());
        productDto.setProductName(product.get().getProductName());
        productDto.setProductDescription(product.get().getProductDescription());
        productDto.setPrice(product.get().getPrice());
        productDto.setCategoryDto(CategoryToCategoryDto(product.get().getCategory()));
        return productDto;
    }
}
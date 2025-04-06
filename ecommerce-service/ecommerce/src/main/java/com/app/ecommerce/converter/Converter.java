package com.app.ecommerce.converter;

import com.app.ecommerce.dto.CategoryDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.entity.Category;
import com.app.ecommerce.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Converter {

    public static ProductDto ProductToProductDto(
            @NotNull(message = "Product is required" ) Product product){
        ProductDto productDto = new ProductDto();

        if (product == null) return null;

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setCategoryDto(CategoryToCategoryDto(product.getCategory()));
        return productDto;
    }

    private static CategoryDto CategoryToCategoryDto(
            @NotNull(message = "Category is required" ) Category category) {
        CategoryDto categoryDto = new CategoryDto();

        if (category == null) return null;

        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }

    public static List<ProductDto> ProductListToProductDtoList(
            @NotNull(message = "Product list is required") List<Product> productList) {
        ArrayList<ProductDto> productDtoDtoList = new ArrayList<>();

        if (productList.isEmpty()) return null;

        for (Product product : productList) {
            productDtoDtoList.add(ProductToProductDto(product));
        }
        return productDtoDtoList;
    }

    public static ProductDto OptionalProductToProductDto(
            @NotNull(message = "Product is required" ) Optional<Product> product) {
        ProductDto productDto = new ProductDto();

        if (product.isEmpty()) return null;

        productDto.setProductId(product.get().getProductId());
        productDto.setProductName(product.get().getProductName());
        productDto.setProductDescription(product.get().getProductDescription());
        productDto.setPrice(product.get().getPrice());
        productDto.setCategoryDto(CategoryToCategoryDto(product.get().getCategory()));
        return productDto;
    }
}
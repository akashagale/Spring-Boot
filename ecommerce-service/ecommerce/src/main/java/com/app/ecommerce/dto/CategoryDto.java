package com.app.ecommerce.dto;

public class CategoryDto {

    private String categoryName;


    public CategoryDto() {
    }

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryDto[" +
                "categoryName='" + categoryName + '\'' +
                ']';
    }
}

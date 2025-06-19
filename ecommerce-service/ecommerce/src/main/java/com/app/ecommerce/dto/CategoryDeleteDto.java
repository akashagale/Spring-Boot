package com.app.ecommerce.dto;

public class CategoryDeleteDto {
    private String categoryName;
    private Long categoryId;

    public CategoryDeleteDto() {
    }

    public CategoryDeleteDto(String categoryName, Long categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "category{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}

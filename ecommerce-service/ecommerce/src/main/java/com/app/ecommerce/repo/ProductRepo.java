package com.app.ecommerce.repo;

import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductName(String name);

    @Query(value = "select * from product where product_name like :productName", nativeQuery = true)
    List<Product> searchProduct(@Param("productName") String productName);
}
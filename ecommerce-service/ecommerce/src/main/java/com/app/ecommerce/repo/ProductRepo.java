package com.app.ecommerce.repo;

import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product p where p.product_name like %:productName%",nativeQuery = true)
    List<Product> searchProduct(@Param("productName") String productName);

    @Query(value = """
    SELECT p.*
    FROM product p
    INNER JOIN category c ON p.category_id = c.category_id
    WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(c.category_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """, nativeQuery = true)
    List<Product> searchProductsByKeyword(@Param("keyword") String keyword);
}
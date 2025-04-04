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

    @Query(value = "select * from product p inner join category c on p.product_id = c.category_id where p.product_name like %:productName% or c.category_id like %:categoryName%",nativeQuery = true)
    List<Product> searchProductNameOrCategoryName(@Param("productName") String productName,
                                                  @Param("categoryName") String categoryName);
}
package com.product.app.Repository;

import com.product.app.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Product, Integer> {
}

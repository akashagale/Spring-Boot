package com.app.ecommerce.repo;

import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.entity.CartProduct;
import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepo extends JpaRepository<CartProduct, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);
}

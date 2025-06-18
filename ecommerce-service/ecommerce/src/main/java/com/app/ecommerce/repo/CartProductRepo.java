package com.app.ecommerce.repo;

import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.entity.CartProduct;
import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    @Query(value = "SELECT cp.* FROM CartProduct cp WHERE cp.cart.cartId = :cartId", nativeQuery = true)
    Optional<CartProduct> findByCartId(@Param("cartId") int cartId);

    @Query(value = "SELECT cp.quantity FROM cart_product cp WHERE cp.cp_id = :cp_id", nativeQuery = true)
    int findQuantityByCartId(@Param("cp_id") Integer cp_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_product SET quantity = :quantity WHERE cart_id = :cartId", nativeQuery = true)
    void updateQuantityByCartId(int cartId, Integer quantity);

    @Query(value = "SELECT cp.*, p.* " +
            "FROM cart_product cp INNER JOIN product p " +
            "ON cp.product_id = p.product_id " +
            "WHERE cp.cart_id = :cartId",
            nativeQuery = true)
    List<CartProduct> findCartProductByCartId(@Param("cartId") int cartId);
}

package com.app.ecommerce.repo;

import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {

//    @Query(value = "SELECT cart.cartId,cart.totalAmount,cp.cpId,p.productId,p.productName,p.price,c.categoryName " +
//            "FROM Cart cart \n" +
//            "INNER JOIN CartProduct cp\n" +
//            "ON cart.cartId = cp.cartId\n" +
//            "INNER JOIN Product p\n" +
//            "ON cp.productId = p.productId\n" +
//            "INNER JOIN Category c\n" +
//            "ON p.categoryId=c.categoryId")
//    public List<Object> getAllCartItems();

    @Query(value = "SELECT cart.cartId,cart.totalAmount,cp.cpId FROM Cart cart INNER JOIN CartProduct cp ON cart.cartId = cp.cartId"
            ,nativeQuery = true)
    public List<Cart> getAllCartProductsItems();

    @Query(value = "SELECT c.*, cp.* " +
            "FROM cart c " +
            "INNER JOIN cart_product cp ON c.cart_id = cp.cart_id "
            , nativeQuery = true)
    List<Object> findCartWithCartProducts();

    @Query(value = "select user_id from cart where email = :email", nativeQuery = true)
    public Integer getUserIdFromAuth(@Param("email") String email);

//    @Query(value = "select c.* from cart c inner join user on c.u.userId = c.cartId where c.u.userId = :userId ",nativeQuery = true)
    public Cart findByUserId(User user);

    Optional<Cart> findByUser(User user);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    Optional<Cart> findCartByUserId(@Param("userId") Integer userId);
}
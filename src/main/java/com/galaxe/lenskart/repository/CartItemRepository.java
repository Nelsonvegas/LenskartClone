package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.CartItem;
import com.galaxe.lenskart.entity.Product;

import jakarta.transaction.Transactional;

import java.util.List;



@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	@Query(value = "select COUNT(*) from cart_item where cart_id=:cartId",nativeQuery = true)
	Integer cartCount(Integer cartId);
	
	CartItem findByProductAndCart(Product product,Cart cart);
	List<CartItem> findByCart(Cart cart);
	
	@Modifying
	@Query(value = "delete cart_item where cart_id=:cartId",nativeQuery = true)
	void deleteCartItems(Integer cartId);
}

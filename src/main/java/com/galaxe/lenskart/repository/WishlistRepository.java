package com.galaxe.lenskart.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.Wishlist;
import com.galaxe.lenskart.entity.Product;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	List<Wishlist> findByCustomer(Customer customer);
	void deleteByCustomerAndProduct(Customer customer, Product product);
	
	
	@Query(value = "select product_id from wishlist where customer_id=:customerId",nativeQuery = true)
	List<Integer> getProductsFromWishlist(Integer customerId);
}

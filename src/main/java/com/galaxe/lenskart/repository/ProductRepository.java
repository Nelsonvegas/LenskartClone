package com.galaxe.lenskart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT p.prod_id,p.price,p.prod_description,p.prod_img,p.prod_name,p.quantity,c.category_id,c.category_name from Product p join Category c on p.category_id=c.category_id WHERE p.prod_name LIKE CONCAT('%',:query,'%') OR p.prod_description LIKE CONCAT('%',:query,'%') OR c.category_name LIKE CONCAT('%',:query,'%')",nativeQuery = true)
	List<Product> searchProducts(String query);
	@Query(value = "select p.prod_name, c.category_name from product p join category c on p.category_id=c.category_id and p.prod_name LIKE CONCAT('%',:query,'%')", nativeQuery = true)
	List<Object> searchresult(String query);
	
	Product findByProdId(Integer prodId);
	
	List<Product> findByProdNameContainingOrProdDescriptionContaining(String prodName,String prodDescription);
	
	Product findByProdId(long prodId);
	
}

package com.galaxe.lenskart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Category;
import com.galaxe.lenskart.entity.Product;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Product> findByCategoryId(int id);
	@Query(value = "select category_name from category c where c.category_name LIKE CONCAT('%',:query,'%')", nativeQuery = true)
	List<Object> categoryList(String query);
}

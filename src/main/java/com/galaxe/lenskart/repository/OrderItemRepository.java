package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.OrderItem;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.entity.CustomerOrder;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	void deleteByCustomerOrderAndProduct(CustomerOrder customerOrder, Product product);
	void deleteByCustomerOrder(CustomerOrder customerOrder);
}

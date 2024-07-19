package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.CustomerOrder;

import jakarta.transaction.Transactional;

import java.util.List;


import com.galaxe.lenskart.entity.Customer;

@Transactional
@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{

	List<CustomerOrder>  findByCustomer(Customer customer);
	CustomerOrder  findByPaymentId(String paymentId);
	String deleteByOrderId(Integer orderId);
	
	@Modifying
	@Query(value = "  update customer_order set order_status='Delivered' where DATEDIFF(DAY,expected_delivery_date,CURRENT_TIMESTAMP)>=1 update customer_order set order_status='Order Packed' where DATEDIFF(DAY,expected_delivery_date,CURRENT_TIMESTAMP)<=-6 update customer_order set order_status='Out for delivery' where DATEDIFF(DAY,expected_delivery_date,CURRENT_TIMESTAMP)=0 update customer_order set order_status='Arrived at Nearest hub' where DATEDIFF(DAY,expected_delivery_date,CURRENT_TIMESTAMP)=-1",nativeQuery = true)
	void updateDeliveryStatus();
}

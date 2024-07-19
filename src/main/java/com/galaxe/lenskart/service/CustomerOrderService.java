package com.galaxe.lenskart.service;

import java.util.List;



import com.galaxe.lenskart.dto.CustomerOrderDTO;

public interface CustomerOrderService {

	String createOrder( Integer addressId,
			 String paymentMode,
			 Long discount,
			 String paymentId);
	
	List<CustomerOrderDTO> getCustomerOrder();
	String deleteOrderedProduct(Integer orderId,Integer productId);
	
	String deleteOrder(Integer OrderId);
}

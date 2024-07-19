package com.galaxe.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.CustomerOrderDTO;
import com.galaxe.lenskart.service.CustomerOrderService;

@RestController
@CrossOrigin("*")
public class CustomerOrderController {

	@Autowired
	CustomerOrderService customerOrderService;
	
	/**
	 * Create an order when the payment is successful
	 * @param addressId the delivery address of the customer
	 * @param paymentMode the mode of payment
	 * @param discount the discount on the current order
	 * @param paymentId the unique id generated after the payment
	 * @return the string message on execution
	 */
	@PostMapping("/createOrder/{addressId}/{paymentMode}/{discount}/{paymentId}")
	public ResponseEntity<String> createOrder(@PathVariable Integer addressId,
			@PathVariable String paymentMode,
			@PathVariable Long discount,
			@PathVariable String paymentId){
		System.out.println(addressId);
	
		return new ResponseEntity<String>(customerOrderService.createOrder(addressId,paymentMode,discount,paymentId),HttpStatus.OK);
	}
	
	/**
	 * Get all the order for the current user
	 * @return the orders in the form of customer order DTO
	 */
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<CustomerOrderDTO>> getAllOrders(){
		return new ResponseEntity<List<CustomerOrderDTO>>(customerOrderService.getCustomerOrder(),HttpStatus.OK);
				
	}
	
	/**
	 * Remove a product from order
	 * @param orderId the id of the order which needs to be deleted
	 * @param productId the id of the product that needs to be removed
	 * @return the string message on execution
	 */
	@DeleteMapping("/deleteOrderedProduct/{orderId}/{productId}")
	public ResponseEntity<String> deleteOrderedProduct(@PathVariable Integer orderId,@PathVariable Integer productId){
		return new ResponseEntity<String>(customerOrderService.deleteOrderedProduct(orderId,productId),HttpStatus.OK);
	}
	
	/**
	 * Remove the whole order if the order needs to be cancelled
	 * @param orderId the id of the order that needs to be removed
	 * @return the string message on execution
	 */
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId){
		return new ResponseEntity<String>(customerOrderService.deleteOrder(orderId),HttpStatus.OK);
	}
	
	
}

package com.galaxe.lenskart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.CartDTO;
import com.galaxe.lenskart.dto.CartItemDTO;
import com.galaxe.lenskart.service.impl.CartItemServiceImpl;
import com.galaxe.lenskart.service.impl.CartServiceImpl;

@RestController
@CrossOrigin("*")
public class CartController {
	
	@Autowired
	CartItemServiceImpl cartItemServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;

	/**
	 * Add products to the cart
	 * @param prodId the id of the product to be added to cart
	 * @return the cart Item DTO which contains details about the product
	 */
	@PostMapping("/addToCart/{prodId}")
	public ResponseEntity<CartItemDTO> addToCart(@PathVariable Integer prodId) {
		
		return new ResponseEntity<CartItemDTO>(cartItemServiceImpl.addTocart(prodId),HttpStatus.OK);
	}
	
	/**
	 * Get the cart for a particular user
	 * @return the cart DTO containing all the cart information
	 */
	@GetMapping("/getCartItems")
	public ResponseEntity<CartDTO> getCartItems(){
		return new ResponseEntity<CartDTO>(cartServiceImpl.getCartItems(),HttpStatus.OK);
	}
	
	/**
	 * Increase the quantity of the product
	 * @param cartItemId the cart item id where the quantity of the product needs to be increased
	 */
	@PutMapping("/repeatProduct/{cartItemId}")
	public void repeatProduct(@PathVariable Integer cartItemId) {
		cartItemServiceImpl.repeatProduct(cartItemId);
	}
	
	/**
	 * Decreasing quantity or removing the product based on the quantity
	 * @param cartItemId he cart item id, where the quantity of the product needs to be decreased or the removed
	 */
	@DeleteMapping("/removeProduct/{cartItemId}")
	public void removeProduct(@PathVariable Integer cartItemId){

		cartItemServiceImpl.removeProduct(cartItemId);
	}
	
	/**
	 * Retrieving the number of products in the cart
	 * @return the integer value containing the cart count
	 */
	@GetMapping("/cartCount")
	public ResponseEntity<Integer> cartCount(){
		return new ResponseEntity<Integer>(cartItemServiceImpl.cartCount(),HttpStatus.OK);
	}
	
	
}

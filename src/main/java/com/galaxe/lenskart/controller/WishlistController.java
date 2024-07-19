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

import com.galaxe.lenskart.dto.WishlistDTO;
import com.galaxe.lenskart.service.impl.WishlistServiceImpl;

@RestController
@CrossOrigin("*")
public class WishlistController {

	@Autowired
	WishlistServiceImpl wishlistServiceImpl;
	
	/**
	 * Get all the products in the wish list
	 * @return the list of wish list DTO containing the products in the wish list
	 */
	@GetMapping("/getwishlist")
	public ResponseEntity<List<WishlistDTO>> getWishlist(){
		return new ResponseEntity<List<WishlistDTO>>(wishlistServiceImpl.getCutsomerWishlist(),HttpStatus.OK);
	}
	
	/**
	 * Add the product to the wish list
	 * @param prodId the id of the product that needs to be added to wishlist
	 * @return the string message on execution
	 */
	@PostMapping("/addToWishlist/{prodId}")
	public ResponseEntity<String> addToWishlist(@PathVariable Integer prodId){
		return new ResponseEntity<String>(wishlistServiceImpl.addTOWishlist( prodId),HttpStatus.OK);
	}
	
	/**
	 * Add the product to the wish list
	 * @param prodId the id of the product that needs to be removed to wish list
	 * @return the string message on execution
	 */
	@DeleteMapping("/removeFromWishlist/{productId}")
	public ResponseEntity<String> removeFromWishlist(@PathVariable Integer productId){
		return new ResponseEntity<String>(wishlistServiceImpl.removeFromWishlist(productId),HttpStatus.OK);
	}
	
	/**
	 * Fetch all the product ids of the product in wish list
	 * @return the list of product ids of the product in wish list
	 */
	@GetMapping("/getWishListProductIds")
	public ResponseEntity<List<Integer>> getWishListProductId(){
		return new ResponseEntity<List<Integer>>(wishlistServiceImpl.getProductsFromWishlist(),HttpStatus.OK);
	}
}

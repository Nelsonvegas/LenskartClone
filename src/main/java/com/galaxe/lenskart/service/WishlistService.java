package com.galaxe.lenskart.service;

import java.util.List;

import com.galaxe.lenskart.dto.WishlistDTO;

public interface WishlistService {

	String addTOWishlist(Integer productId);

	String removeFromWishlist(Integer productId);

	List<WishlistDTO> getCutsomerWishlist();
	
	List<Integer> getProductsFromWishlist();
	
}

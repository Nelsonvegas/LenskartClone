package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.WishlistDTO;
import com.galaxe.lenskart.entity.Wishlist;

@Component
public class WishlistMapper {

	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	public WishlistDTO toWishlistDTO(Wishlist wishlist) {
		WishlistDTO wishlistDTO=WishlistDTO.builder().customerDTO(customerMapper.toCustomerDTO(wishlist.getCustomer())).productDTO(productMapper.toProductDTO(wishlist.getProduct())).dateAdded(wishlist.getDateAdded()).wishlistId(wishlist.getWishlistId()).build();
		return wishlistDTO;
	}
	
	public Wishlist toWishlist(WishlistDTO wishlistDTO) {
		Wishlist wishlist=Wishlist.builder().customer(customerMapper.toCustomer(wishlistDTO.getCustomerDTO())).product(productMapper.toProduct(wishlistDTO.getProductDTO())).dateAdded(wishlistDTO.getDateAdded()).wishlistId(wishlistDTO.getWishlistId()).build();
		return wishlist;
	}
	
	
}

package com.galaxe.lenskart.service;

import com.galaxe.lenskart.dto.CartItemDTO;

public interface CartItemService {

	CartItemDTO addTocart(Integer prodId);
	void repeatProduct(Integer prodId);
	void removeProduct(Integer prodId);
	Integer cartCount();
}

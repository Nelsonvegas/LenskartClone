package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CartItemDTO;
import com.galaxe.lenskart.entity.CartItem;

@Component
public class CartItemMapper {

	@Autowired
	ProductMapper productMapper;

	public CartItemDTO tocartItemDTO(CartItem cartItem) {
		CartItemDTO cartItemDTO = CartItemDTO.builder().cartItemId(cartItem.getCartItemId())
				.productDTO(productMapper.toProductDTO(cartItem.getProduct())).quantity(cartItem.getQuantity()).build();

		return cartItemDTO;
	}

	public CartItem tocartItem(CartItemDTO cartItemDTO) {
		CartItem cartItem = CartItem.builder().cartItemId(cartItemDTO.getCartItemId())
				.product(productMapper.toProduct(cartItemDTO.getProductDTO())).quantity(cartItemDTO.getQuantity())
				.build();

		return cartItem;
	}

}

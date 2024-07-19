package com.galaxe.lenskart.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CartDTO;
import com.galaxe.lenskart.entity.Cart;

@Component
public class CartMapper {

	@Autowired
	CartItemMapper cartItemMapper;

	public CartDTO toCartDTO(Cart cart) {

		CartDTO cartDTO = CartDTO.builder().cartId(cart.getCartId()).totalPrice(cart.getTotalPrice()).cartItem(
				cart.getCartItem().stream().map(p -> cartItemMapper.tocartItemDTO(p)).collect(Collectors.toList()))
				.build();

		return cartDTO;

	}

	public Cart toCart(CartDTO cartDTO) {

		Cart cart = Cart
				.builder().cartId(cartDTO.getCartId()).totalPrice(cartDTO.getTotalPrice()).cartItem(cartDTO
						.getCartItem().stream().map(p -> cartItemMapper.tocartItem(p)).collect(Collectors.toList()))
				.build();

		return cart;

	}

}

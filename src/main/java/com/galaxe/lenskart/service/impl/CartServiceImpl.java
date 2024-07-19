package com.galaxe.lenskart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.CartDTO;
import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.mapper.CartMapper;
import com.galaxe.lenskart.repository.CartRepository;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CartMapper cartMapper;

	/**
	 * Retrieve the cart information for the particular user
	 * @return the cartDTO containing all the cart details
	 */
	@Override
	public CartDTO getCartItems() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);

		Cart cart = cartRepository.findByCustomer(customer);
		return cartMapper.toCartDTO(cart);
	}

}

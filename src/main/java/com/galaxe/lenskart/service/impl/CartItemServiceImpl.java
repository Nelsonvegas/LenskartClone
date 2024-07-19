package com.galaxe.lenskart.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.controller.CustomerController;
import com.galaxe.lenskart.dto.CartItemDTO;
import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.CartItem;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.mapper.CartItemMapper;
import com.galaxe.lenskart.repository.CartItemRepository;
import com.galaxe.lenskart.repository.CartRepository;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.repository.ProductRepository;
import com.galaxe.lenskart.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CustomerController customerController;

	@Autowired
	CartItemMapper cartItemMapper;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	/**
	 * Add items to the cart
	 * @param prodId the id of the product which needs to be added to the cart
	 * @return the cartItemDTO containing necessary details
	 */
	@Override
	public CartItemDTO addTocart(Integer prodId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Customer customer = customerRepository.findByEmail(username);
		Cart cart = cartRepository.findByCustomer(customer);
		Product product = productRepository.findByProdId(prodId);

		CartItem cartItem1 = cartItemRepository.findByProductAndCart(product, cart);

		if (cartItem1 != null) {
			cartItem1.setQuantity(cartItem1.getQuantity() + 1);
			cartItemRepository.save(cartItem1);
			return cartItemMapper.tocartItemDTO(cartItem1);
		}

		else {
			CartItem cartItem = new CartItem();

			cartItem.setProduct(product);

			cartItem.setCart(cart);
			cartItem.setQuantity(1);

			CartItem cartItem2 = cartItemRepository.save(cartItem);
			return cartItemMapper.tocartItemDTO(cartItem2);
		}

	}

	/**
	 * Increase the quantity of the product in cart Item
	 * @param cartItemId the cart item Id of the cart whose particular product quantity needs to be increased
	 */
	@Override
	public void repeatProduct(Integer cartItemId) {
		CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

		cartItem.setQuantity(cartItem.getQuantity() + 1);

		cartItemRepository.save(cartItem);

	}
	
	/**
	 * Decrease the quantity of the product in cart Item
	 * @param cartItemId the cart item Id of the cart whose particular product quantity needs to be decreased
	 */
	@Override
	public void removeProduct(Integer cartItemId) {
		CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

		if (cartItem.getQuantity() > 1) {
			cartItem.setQuantity(cartItem.getQuantity() - 1);

			cartItemRepository.save(cartItem);
		} else if (cartItem.getQuantity() == 1) {
			cartItemRepository.delete(cartItem);
		}

	}

	/**
	 * Counts the number of products in the cart for a customer
	 * @return the product count of the cart
	 */
	@Override
	public Integer cartCount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Customer customer = customerRepository.findByEmail(username);

		return cartItemRepository.cartCount(customer.getCart().getCartId());
	}

}

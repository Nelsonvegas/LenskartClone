package com.galaxe.lenskart.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.WishlistDTO;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.entity.Wishlist;
import com.galaxe.lenskart.mapper.CustomerMapper;
import com.galaxe.lenskart.mapper.ProductMapper;
import com.galaxe.lenskart.mapper.WishlistMapper;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.repository.ProductRepository;
import com.galaxe.lenskart.repository.WishlistRepository;
import com.galaxe.lenskart.service.WishlistService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	WishlistRepository wishlistRepository;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	WishlistMapper wishlistMapper;
	
	
	/**
	 * Add product to wish list
	 * @param productId the product Id of the product to be added to wishlist
	 * @return returns a message on execution
	 */
	@Override
	public String addTOWishlist(Integer productId) {
		Product product=productRepository.findByProdId(productId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		List<Wishlist> wishlists=wishlistRepository.findByCustomer(customer);
		Long countProd= wishlists.stream().filter(p->p.getProduct().getProdId()==(product.getProdId())).collect(Collectors.counting());
		if (countProd==0) {
			java.util.Date currentDate = new java.util.Date();
			Timestamp timestamp = new Timestamp(currentDate.getTime());
			WishlistDTO wishlistDTO=new WishlistDTO();

			wishlistDTO.setCustomerDTO(customerMapper.toCustomerDTO(customer));
			wishlistDTO.setProductDTO(productMapper.toProductDTO(product));
			wishlistDTO.setDateAdded(timestamp);
			
			wishlistRepository.save(wishlistMapper.toWishlist(wishlistDTO));
		}
		return "Success";
	}

	/**
	 * Remove product from wish list
	 * @param productId the product Id of the product to be removed from wish list
	 * @return the string message on execution
	 */
	@Override
	public String removeFromWishlist(Integer productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		Product product=productRepository.findByProdId(productId);
		wishlistRepository.deleteByCustomerAndProduct(customer,product);
		
		return "Success";
	}

	/**
	 * Get all the wish list for the current customer 
	 * @return the WishlistDTO which contains necessary fields 
	 */
	@Override
	public List<WishlistDTO> getCutsomerWishlist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		List<Wishlist> wishlists= wishlistRepository.findByCustomer(customer);

		List<WishlistDTO> wishlistDTOs=wishlists.stream().map(wishlist->wishlistMapper.toWishlistDTO(wishlist)).collect(Collectors.toList());
		return wishlistDTOs;
	}

	/**
	 * Get all the product id's of the product in the wish list for the current customer 
	 * @return the list of product id's 
	 */
	@Override
	public List<Integer> getProductsFromWishlist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		
		return wishlistRepository.getProductsFromWishlist(customer.getCustomerID());
	}
	

}

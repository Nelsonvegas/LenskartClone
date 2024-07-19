package com.galaxe.lenskart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.controller.AuthController;
import com.galaxe.lenskart.dto.CustomerDTO;
import com.galaxe.lenskart.dto.CustomerUpdateDTO;
import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.exceptionhandler.DuplicateKeyException;
import com.galaxe.lenskart.mapper.CustomerMapper;
import com.galaxe.lenskart.repository.CartRepository;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	AuthController authController;

	/**
	 * Create a customer and cart for that customer as soon as sign up
	 * @param customerDTO containing all the customer details
	 * @return customerDTO which contains the customer details
	 * @throws DuplicateKeyException if the mail is already present
	 */
	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer1=customerRepository.findByEmail(customerDTO.getEmail());
	
		if(customer1==null) {
		
		customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
		customerDTO.setRole("USER");

		Customer customer = customerRepository.save(customerMapper.toCustomer(customerDTO));

		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.setTotalPrice(0);
		cartRepository.save(cart);

		return customerMapper.toCustomerDTO(customer);}
		else {
			throw new DuplicateKeyException();
		}
	}

	

	/**
	 * Update the customer details. User info update and password and user info update are separated
	 * @param customerUpdateDTO the DTO containing all the required valued for update
	 * @return the string message on execution
	 */
	@Override
	public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);

		if (customerUpdateDTO.getIsPasswordUpdated().equals("yes")) {

			if (bCryptPasswordEncoder.matches(customerUpdateDTO.getPassword(), customer.getPassword())) {

				customer.setPassword(bCryptPasswordEncoder.encode(customerUpdateDTO.getNewPassword()));
				customer.setGender(customerUpdateDTO.getGender());
				customer.setFirstName(customerUpdateDTO.getFirstName());
				customer.setLastName(customerUpdateDTO.getLastName());

				customerRepository.save(customer);
				return "Success";
			} else {
				return "previous password does not match";
			}
		} else {
			customer.setGender(customerUpdateDTO.getGender());
			customer.setFirstName(customerUpdateDTO.getFirstName());
			customer.setLastName(customerUpdateDTO.getLastName());

			customerRepository.save(customer);
			return "Success";
		}

	}

	/**
	 * Get the currently logged in customer details
	 * @return the customerDTO of the current customer
	 */
	@Override
	public CustomerDTO getCustomer() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);

		return customerMapper.toCustomerDTO(customer);
	}

}

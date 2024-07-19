package com.galaxe.lenskart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.CustomCustomer;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.repository.CustomerRepository;

@Service
public class CustomCustomerServiceImpl implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * A method for creating the AuthenticationManager bean which is from spring security 
	 * @param builder the AuthenticationConfiguration for security
	 * @return a bean of AuthenticationManager
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	/**
	 *  A method for creating the BCryptPasswordEncoder bean 
	 * @return a bean of BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

	/**
	 * Gets the user details which in return is passed as userDetails
	 * @return the customer entity containing the customer information as userDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email);

		if (customer == null) {
			throw new UsernameNotFoundException("Customer not Found");
		}

		return new CustomCustomer(customer);
	}

}

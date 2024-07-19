package com.galaxe.lenskart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.CustomerDTO;
import com.galaxe.lenskart.dto.CustomerUpdateDTO;
import com.galaxe.lenskart.exceptionhandler.DuplicateKeyException;
import com.galaxe.lenskart.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Fetch the current user name
	 * @param principal the parameter containing the current user info
	 * @return the email(user name) of the customer
	 */
	@GetMapping("/currentusername")
	public String currentUserName(Principal principal) {
		return principal.getName();
	}

	/**
	 * Create a customer on successful sign up
	 * @param customerDTO contains all the information of the customer
	 * @return the customerDTO containing all the customer information
	 * @throws DuplicateKeyException the custom exception for handling uniqueness of the email
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		
		try {
			CustomerDTO customerDTO1 = customerService.createCustomer(customerDTO);
				return new ResponseEntity<CustomerDTO>(customerDTO1, HttpStatus.OK);
			
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("Email already exists");
		} catch (Exception e) {
	throw e;
}

	}


	/**
	 * Update the customer information
	 * @param customerUpdateDTO the DTO containing all the customer information that needs to be updated
	 * @return the success message on execution
	 */
	@PutMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
		return new ResponseEntity<String>(customerService.updateCustomer(customerUpdateDTO), HttpStatus.OK);

	}

	/**
	 * Get the customer details for the currently logged in user
	 * @return the customerDTO containing all the customer information
	 */
	@GetMapping("/getCustomer")
	public ResponseEntity<CustomerDTO> getCustomer() {
		return new ResponseEntity<CustomerDTO>(customerService.getCustomer(), HttpStatus.OK);
	}

}

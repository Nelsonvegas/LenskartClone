package com.galaxe.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.CustomerAddressDTO;
import com.galaxe.lenskart.service.CustomerAddressService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("*")
@Transactional
public class CustomerAddressController {

	@Autowired
	CustomerAddressService customerAddressService;
	
	/**
	 * Save the address of the customer
	 * @param customerAddressDTO the DTO containing all the customer address details
	 * @return the string message on execution
	 */
	@PostMapping("/saveAddress")
	public ResponseEntity<String> saveAddress(@RequestBody CustomerAddressDTO customerAddressDTO){
		return new ResponseEntity<String>(customerAddressService.addAddress(customerAddressDTO),HttpStatus.OK);
	}
	
	
	/**
	 * Retrieve all the address for a particular customer
	 * @return the list of addresses for that particular user
	 */
	@GetMapping("/getAllAddress")
	public ResponseEntity<List<CustomerAddressDTO>> getAllAddress() {
		return new ResponseEntity<List<CustomerAddressDTO>>(customerAddressService.getAllAddress(),HttpStatus.OK);
	}
	
	/**
	 * Delete the address of the customer
	 * @param customerAddressId the Id of the address that needs to be deleted
	 * @return the string message on execution
	 */
	@DeleteMapping("/deleteAddress/{customerAddressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Integer customerAddressId){
		return new ResponseEntity<String>(customerAddressService.deleteCustomerAddress(customerAddressId),HttpStatus.OK);
	}
	
	/**
	 * Fetch one address of the customer based on the address id
	 * @param customerAddressId the address id of the customer that needs to be saved
	 * @return the customer address DTO containing the particular customer address details
	 */
	@GetMapping("/getOneAddress/{customerAddressId}")
	public ResponseEntity<CustomerAddressDTO> getAddress(@PathVariable Integer customerAddressId){
		return new ResponseEntity<CustomerAddressDTO>(customerAddressService.getCustomerAddress(customerAddressId),HttpStatus.OK);
	}
	
	/**
	 * updating the customer address
	 * @param customerAddressId the id of the customer address which needs to be updated
	 * @param customerAddressDTO the address information which needs to be updated
	 * @return the string message on execution
	 */
	@PutMapping("/updateCustomerAddress/{customerAddressId}")
	public ResponseEntity<String> updateCustomerAddress(@PathVariable Integer customerAddressId,@RequestBody CustomerAddressDTO customerAddressDTO){
		return new ResponseEntity<String>(customerAddressService.updateCustomerAddress(customerAddressId,customerAddressDTO),HttpStatus.OK);
	}
	
}

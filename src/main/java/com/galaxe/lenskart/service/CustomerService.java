package com.galaxe.lenskart.service;

import com.galaxe.lenskart.dto.CustomerDTO;
import com.galaxe.lenskart.dto.CustomerUpdateDTO;

public interface CustomerService {

	CustomerDTO createCustomer(CustomerDTO customerDTO);
	String updateCustomer(CustomerUpdateDTO customerUpdateDTO);
	CustomerDTO getCustomer();
}

package com.galaxe.lenskart.mapper;

import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CustomerDTO;
import com.galaxe.lenskart.entity.Customer;

@Component
public class CustomerMapper {

	public CustomerDTO toCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = CustomerDTO.builder().customerID(customer.getCustomerID())
				.firstName(customer.getFirstName()).lastName(customer.getLastName()).mobileNo(customer.getMobileNo())
				.email(customer.getEmail()).password(customer.getPassword()).gender(customer.getGender())
				.role(customer.getRole()).build();

		return customerDTO;

	}

	public Customer toCustomer(CustomerDTO customerDTO) {
		Customer customer = Customer.builder().customerID(customerDTO.getCustomerID())
				.firstName(customerDTO.getFirstName()).lastName(customerDTO.getLastName())
				.mobileNo(customerDTO.getMobileNo()).email(customerDTO.getEmail()).password(customerDTO.getPassword())
				.gender(customerDTO.getGender()).role(customerDTO.getRole()).build();

		return customer;

	}

}

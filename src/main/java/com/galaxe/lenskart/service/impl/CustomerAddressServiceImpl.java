package com.galaxe.lenskart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.CustomerAddressDTO;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.CustomerAddress;
import com.galaxe.lenskart.mapper.CustomerAddressMapper;
import com.galaxe.lenskart.mapper.CustomerMapper;
import com.galaxe.lenskart.repository.CustomerAddressRepository;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

	@Autowired
	CustomerAddressRepository customerAddressRepository;

	@Autowired
	CustomerAddressMapper customerAddressMapper;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	/**
	 * Add an address for a particular customer
	 * @param customerAddressDTO the details of the customer address
	 * @return the string message on execution
	 */
	@Override
	public String addAddress(CustomerAddressDTO customerAddressDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		customerAddressDTO.setCustomerDTO(customerMapper.toCustomerDTO(customer));
		customerAddressRepository.save(customerAddressMapper.tocustomerAddress(customerAddressDTO));

		return "Success";
	}

	/**
	 * Retrieve all the customer address for the current customer
	 * @return the list of customer address DTO
	 */
	@Override
	public List<CustomerAddressDTO> getAllAddress() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);

		List<CustomerAddress> customerAddresses = customerAddressRepository.findByCustomer(customer);
		List<CustomerAddressDTO> customerAddressDTOs = customerAddresses.stream()
				.map(c -> customerAddressMapper.tocustomerAddressDTO(c)).collect(Collectors.toList());

		return customerAddressDTOs;
	}

	/**
	 * Deletes the customer address 
	 * @param customerAddressId the address Id of the customer address which has to be deleted
	 * @return the string message on execution
	 */
	@Override
	public String deleteCustomerAddress(Integer customerAddressId) {
		
		customerAddressRepository.deleteByAddressId(customerAddressId);
		return "Success";
	}

	/**
	 * Retrieve one customer address
	 * @param customerAddressId the id of the address which needs to be retrieved
	 * @return the customer address DTO which contains the address
	 */
	@Override
	public CustomerAddressDTO getCustomerAddress(Integer customerAddressId) {
		CustomerAddress customerAddress=customerAddressRepository.findByAddressId(customerAddressId);
		return customerAddressMapper.tocustomerAddressDTO(customerAddress);
	}

	/**
	 * Update the customer address details 
	 * @param customerAddressId the address id of the address which needs to be updated
	 * @param customerAdddressDTO the details of the address which needs to be inserted
	 * @return the string message one execution
	 */
	@Override
	public String updateCustomerAddress(Integer customerAddressId,CustomerAddressDTO customerAdddressDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		customerAdddressDTO.setAddressId(customerAddressId);
		customerAdddressDTO.setCustomerDTO(customerMapper.toCustomerDTO(customer));
		customerAddressRepository.save(customerAddressMapper.tocustomerAddress(customerAdddressDTO));
		return "Success";
	}
	
	

}

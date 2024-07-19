package com.galaxe.lenskart.service;

import java.util.List;

import com.galaxe.lenskart.dto.CustomerAddressDTO;

public interface CustomerAddressService {

	String addAddress(CustomerAddressDTO customerAddressDTO);
	List<CustomerAddressDTO> getAllAddress();
	String deleteCustomerAddress(Integer customerAdddressId);
	CustomerAddressDTO getCustomerAddress(Integer customerAddressId);
	String updateCustomerAddress(Integer customerAddressId,CustomerAddressDTO customerAdddressDTO);
}

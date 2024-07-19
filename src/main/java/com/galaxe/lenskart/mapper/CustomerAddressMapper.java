package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CustomerAddressDTO;
import com.galaxe.lenskart.entity.CustomerAddress;

@Component
public class CustomerAddressMapper {

	@Autowired
	CustomerMapper customerMapper;

	public CustomerAddressDTO tocustomerAddressDTO(CustomerAddress customerAddress) {
		CustomerAddressDTO customerAddressDTO = CustomerAddressDTO.builder().addressId(customerAddress.getAddressId())
				.firstName(customerAddress.getFirstName()).lastName(customerAddress.getLastName())
				.gender(customerAddress.getGender()).phoneNumber(customerAddress.getPhoneNumber())
				.email(customerAddress.getEmail()).addressLine1(customerAddress.getAddressLine1())
				.addressLine2(customerAddress.getAddressLine2()).city(customerAddress.getCity())
				.postalCode(customerAddress.getPostalCode()).state(customerAddress.getState())
				.country(customerAddress.getCountry()).isDefaultAddress(customerAddress.isDefaultAddress())
				.addressType(customerAddress.getAddressType())
				.customerDTO(customerMapper.toCustomerDTO(customerAddress.getCustomer())).build();

		return customerAddressDTO;

	}

	public CustomerAddress tocustomerAddress(CustomerAddressDTO customerAddressDTO) {
		CustomerAddress customerAddress = CustomerAddress.builder().addressId(customerAddressDTO.getAddressId())
				.firstName(customerAddressDTO.getFirstName()).lastName(customerAddressDTO.getLastName())
				.gender(customerAddressDTO.getGender()).phoneNumber(customerAddressDTO.getPhoneNumber())
				.email(customerAddressDTO.getEmail()).addressLine1(customerAddressDTO.getAddressLine1())
				.addressLine2(customerAddressDTO.getAddressLine2()).city(customerAddressDTO.getCity())
				.postalCode(customerAddressDTO.getPostalCode()).state(customerAddressDTO.getState())
				.country(customerAddressDTO.getCountry()).isDefaultAddress(customerAddressDTO.isDefaultAddress())
				.addressType(customerAddressDTO.getAddressType())
				.customer(customerMapper.toCustomer(customerAddressDTO.getCustomerDTO())).build();

		return customerAddress;

	}

}

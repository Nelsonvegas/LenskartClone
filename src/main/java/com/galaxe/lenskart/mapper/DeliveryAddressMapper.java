package com.galaxe.lenskart.mapper;

import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.DeliveryAddressDTO;
import com.galaxe.lenskart.entity.DeliveryAddress;

@Component
public class DeliveryAddressMapper {

	public DeliveryAddressDTO toDeliveryAddressDTO(DeliveryAddress deliveryAddress) {
		DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
				.deliveryAddressId(deliveryAddress.getDeliveryAddressId()).firstName(deliveryAddress.getFirstName())
				.lastName(deliveryAddress.getLastName()).gender(deliveryAddress.getGender())
				.phoneNumber(deliveryAddress.getPhoneNumber()).email(deliveryAddress.getEmail())
				.addressLine1(deliveryAddress.getAddressLine1()).addressLine2(deliveryAddress.getAddressLine2())
				.city(deliveryAddress.getCity()).postalCode(deliveryAddress.getPostalCode())
				.state(deliveryAddress.getState()).country(deliveryAddress.getCountry())
				.addressType(deliveryAddress.getAddressType()).build();

		return deliveryAddressDTO;
	}

	public DeliveryAddress toDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO) {
		DeliveryAddress deliveryAddress = DeliveryAddress.builder()
				.deliveryAddressId(deliveryAddressDTO.getDeliveryAddressId())
				.firstName(deliveryAddressDTO.getFirstName()).lastName(deliveryAddressDTO.getLastName())
				.gender(deliveryAddressDTO.getGender()).phoneNumber(deliveryAddressDTO.getPhoneNumber())
				.email(deliveryAddressDTO.getEmail()).addressLine1(deliveryAddressDTO.getAddressLine1())
				.addressLine2(deliveryAddressDTO.getAddressLine2()).city(deliveryAddressDTO.getCity())
				.postalCode(deliveryAddressDTO.getPostalCode()).state(deliveryAddressDTO.getState())
				.country(deliveryAddressDTO.getCountry()).addressType(deliveryAddressDTO.getAddressType()).build();

		return deliveryAddress;

	}

}

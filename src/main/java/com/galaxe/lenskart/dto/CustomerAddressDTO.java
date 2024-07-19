package com.galaxe.lenskart.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDTO {
	private int addressId;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String email;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	private String state;
	private String country;
	private boolean isDefaultAddress;
	private String addressType;
	private CustomerDTO customerDTO;

}

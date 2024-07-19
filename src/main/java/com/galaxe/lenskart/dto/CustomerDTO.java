package com.galaxe.lenskart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private Integer customerID;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String password;
	private String role;
	private String gender;
}

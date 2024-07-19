package com.galaxe.lenskart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateDTO {


	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String password;
	private String role;
	private String gender;
	private String newPassword;
	private String isPasswordUpdated;
}

package com.galaxe.lenskart.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerID;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNo;
	

	private String email;
	
	private String password;
	
	private String gender;
	
	private String role;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Cart cart;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<CustomerAddress> customerAddress;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<CustomerOrder> orders;


	@OneToMany(mappedBy = "customer")
	private List<Wishlist> wishlist;
	
	@OneToOne(mappedBy = "customer")
	private GoldMembership goldMembership;

	
	
}

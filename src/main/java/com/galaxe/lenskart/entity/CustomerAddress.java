package com.galaxe.lenskart.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	

	
}

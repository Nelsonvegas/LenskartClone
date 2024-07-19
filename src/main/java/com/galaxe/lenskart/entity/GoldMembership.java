package com.galaxe.lenskart.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoldMembership {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer goldMembershipId;
	private Timestamp membershipStartDate;
	private Timestamp membershipEndDate;
	private String paymentId;
	@OneToOne
	@JoinColumn(name = "cutomerId")
	private Customer customer;
	
}

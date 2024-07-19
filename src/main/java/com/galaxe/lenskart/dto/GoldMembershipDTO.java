package com.galaxe.lenskart.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoldMembershipDTO {

	private Integer goldMembershipId;
	private Timestamp membershipStartDate;
	private Timestamp membershipEndDate;
	private String paymentId;
	private CustomerDTO customerDTO;

}

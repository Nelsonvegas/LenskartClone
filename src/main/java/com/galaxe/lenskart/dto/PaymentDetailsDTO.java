package com.galaxe.lenskart.dto;

import java.sql.Timestamp;

import com.galaxe.lenskart.entity.CustomerOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsDTO {

	private Integer orderDetailsId;
	private String paymentId; 
	private Long paymentAmount;
	private Timestamp date;
	private CustomerOrder customerOrder;
}

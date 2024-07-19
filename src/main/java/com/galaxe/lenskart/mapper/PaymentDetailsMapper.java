package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.PaymentDetailsDTO;
import com.galaxe.lenskart.entity.PaymentDetails;

@Component
public class PaymentDetailsMapper {

	@Autowired
	CustomerOrderMapper customerOrderMapper;
	
	public PaymentDetailsDTO topaymentDetailsDTO(PaymentDetails paymentDetails) {
		PaymentDetailsDTO paymentDetailsDTO = PaymentDetailsDTO.builder()
				.customerOrder(paymentDetails.getCustomerOrder() ).date(paymentDetails.getDate())
				.orderDetailsId(paymentDetails.getOrderDetailsId()).paymentId(paymentDetails.getPaymentId())
				.paymentAmount(paymentDetails.getPaymentAmount()).build();

		return paymentDetailsDTO;
	}

	public PaymentDetails topaymentDetails(PaymentDetailsDTO paymentDetailsDTO) {
		PaymentDetails paymentDetails = PaymentDetails.builder().customerOrder(paymentDetailsDTO.getCustomerOrder())
				.date(paymentDetailsDTO.getDate()).orderDetailsId(paymentDetailsDTO.getOrderDetailsId())
				.paymentId(paymentDetailsDTO.getPaymentId()).paymentAmount(paymentDetailsDTO.getPaymentAmount())
				.build();

		return paymentDetails;
	}
}

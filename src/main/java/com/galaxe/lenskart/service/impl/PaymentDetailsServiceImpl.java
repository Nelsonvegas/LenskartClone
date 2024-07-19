package com.galaxe.lenskart.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.PaymentDetailsDTO;
import com.galaxe.lenskart.mapper.CustomerOrderMapper;
import com.galaxe.lenskart.mapper.PaymentDetailsMapper;
import com.galaxe.lenskart.repository.CustomerOrderRepository;
import com.galaxe.lenskart.repository.PaymentDetailsRepository;
import com.galaxe.lenskart.service.PaymentDetailsService;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

	@Autowired
	PaymentDetailsMapper paymentDetailsMapper;

	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;

	@Autowired
	CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	CustomerOrderMapper customerOrderMapper;
	
	/**
	 * Save the payment details into the payment detail table
	 * @param the paymentDetailsDTO which contains all the payment details
	 * @return the string message
	 */
	@Override
	public String savePaymentDetails(PaymentDetailsDTO paymentDetailsDTO) {
		java.util.Date currentDate = new java.util.Date();

		Timestamp timestamp = new Timestamp(currentDate.getTime());
		paymentDetailsDTO.setCustomerOrder(customerOrderRepository.findByPaymentId(paymentDetailsDTO.getPaymentId()) );
		paymentDetailsDTO.setDate(timestamp);
		paymentDetailsRepository.save(paymentDetailsMapper.topaymentDetails(paymentDetailsDTO));

		return "Success";
	}

}

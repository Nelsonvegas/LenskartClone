package com.galaxe.lenskart.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.GoldMembershipDTO;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.GoldMembership;
import com.galaxe.lenskart.mapper.CustomerMapper;
import com.galaxe.lenskart.mapper.GoldMembershipMapper;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.repository.GoldMembershipRepository;
import com.galaxe.lenskart.service.GoldMembershipService;

@Service
public class GoldMembershipServiceImpl implements GoldMembershipService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	GoldMembershipRepository goldMembershipRepository;
	
	@Autowired
	GoldMembershipMapper goldMembershipMapper;
	
	@Override
	public GoldMembershipDTO createGoldMembership(String paymentId) {
		GoldMembershipDTO goldMembershipDTO=new GoldMembershipDTO();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		
		java.util.Date currentDate = new java.util.Date();

		Timestamp timestamp = new Timestamp(currentDate.getTime());
	
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.add(Calendar.YEAR, 1);
		
	    Timestamp timestamp1 = new Timestamp(calendar.getTime().getTime());
	    
	    goldMembershipDTO.setCustomerDTO(customerMapper.toCustomerDTO(customer));
	    
	    goldMembershipDTO.setMembershipStartDate(timestamp);
	    
	    goldMembershipDTO.setMembershipEndDate(timestamp1);
	    
	    goldMembershipDTO.setPaymentId(paymentId);
	    
	    GoldMembership goldMembership=goldMembershipRepository.save(goldMembershipMapper.toGoldMembership(goldMembershipDTO));
	    
		return goldMembershipMapper.toGoldMembershipDTO(goldMembership);
	}
	
	public String deleteGoldMembership() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		
		goldMembershipRepository.deleteByCustomer(customer);
		
		return "Success";
		
	}

	
}

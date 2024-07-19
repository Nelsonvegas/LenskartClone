package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.GoldMembershipDTO;
import com.galaxe.lenskart.entity.GoldMembership;

@Component
public class GoldMembershipMapper {

	@Autowired
	CustomerMapper customerMapper;
	
	public GoldMembershipDTO toGoldMembershipDTO(GoldMembership goldMembership) {
		GoldMembershipDTO goldMembershipDTO=GoldMembershipDTO.builder().customerDTO(customerMapper.toCustomerDTO(goldMembership.getCustomer())).goldMembershipId(goldMembership.getGoldMembershipId()).membershipEndDate(goldMembership.getMembershipEndDate()).membershipStartDate(goldMembership.getMembershipStartDate()).paymentId(goldMembership.getPaymentId()).build();
		return goldMembershipDTO;
	}
	
	public GoldMembership toGoldMembership(GoldMembershipDTO goldMembershipDTO) {
		GoldMembership goldMembership=GoldMembership.builder().customer(customerMapper.toCustomer(goldMembershipDTO.getCustomerDTO())).goldMembershipId(goldMembershipDTO.getGoldMembershipId()).membershipEndDate(goldMembershipDTO.getMembershipEndDate()).membershipStartDate(goldMembershipDTO.getMembershipStartDate()).paymentId(goldMembershipDTO.getPaymentId()).build();
		return goldMembership;
	}
}

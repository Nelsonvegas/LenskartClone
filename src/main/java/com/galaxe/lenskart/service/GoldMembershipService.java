package com.galaxe.lenskart.service;

import com.galaxe.lenskart.dto.GoldMembershipDTO;

public interface GoldMembershipService {

	GoldMembershipDTO createGoldMembership(String paymentId);
	String deleteGoldMembership();
}

package com.galaxe.lenskart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.GoldMembershipDTO;
import com.galaxe.lenskart.service.impl.GoldMembershipServiceImpl;

@CrossOrigin("*")
@RestController
public class GoldMembershipController {

	@Autowired
	GoldMembershipServiceImpl goldMembershipServiceImpl;
	
	@PostMapping("/createGoldMembership/{paymentId}")
	public ResponseEntity<GoldMembershipDTO> createGoldmembership(@PathVariable String paymentId){
		return new ResponseEntity<GoldMembershipDTO>(goldMembershipServiceImpl.createGoldMembership(paymentId),HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteGoldMembership(){
		return new ResponseEntity<String>(goldMembershipServiceImpl.deleteGoldMembership(),HttpStatus.OK);
	}
	
	
}

package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.GoldMembership;
import com.galaxe.lenskart.entity.Customer;


@Repository
public interface GoldMembershipRepository extends JpaRepository<GoldMembership, Integer>{
	void deleteByCustomer(Customer customer);
}

package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.PaymentDetails;
import com.galaxe.lenskart.entity.CustomerOrder;


@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer>{

	void deleteByCustomerOrder(CustomerOrder customerOrder);
}

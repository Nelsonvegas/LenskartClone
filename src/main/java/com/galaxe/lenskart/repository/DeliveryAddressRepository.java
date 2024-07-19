package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.DeliveryAddress;
import com.galaxe.lenskart.entity.CustomerOrder;


@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer>{

	void deleteByCustomerOrder(CustomerOrder customerOrder);
}

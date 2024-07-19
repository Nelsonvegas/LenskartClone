package com.galaxe.lenskart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>{

	List<CustomerAddress> findByCustomer(Customer customer);
	CustomerAddress findByAddressId(int addressId);
	String  deleteByAddressId(int addressId);
	
}

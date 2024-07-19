package com.galaxe.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.Customer;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByCustomer(Customer customer);

}

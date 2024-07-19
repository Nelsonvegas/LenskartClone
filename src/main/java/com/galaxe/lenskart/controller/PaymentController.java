package com.galaxe.lenskart.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.lenskart.dto.PaymentDetailsDTO;
import com.galaxe.lenskart.service.impl.PaymentDetailsServiceImpl;
import com.razorpay.*;


@CrossOrigin("*")
@RestController
public class PaymentController {

	@Autowired
	PaymentDetailsServiceImpl paymentDetailsServiceImpl;
	
	/**
	 * Create a payment order and send it to the razorpay account of the admin
	 * @param amount the amount that needs to be paid
	 * @return the string message on execution
	 * @throws Exception on an exception that occurred
	 */
	@PostMapping("/createPaymentOrder/{amount}")
	public ResponseEntity<String> createOrder(@PathVariable Integer amount) throws Exception {
		RazorpayClient client=new RazorpayClient("rzp_test_FpurvsDpzlCYvx", "nKVUU5gZnePTPKQgcgmXbGSh");
		JSONObject object=new JSONObject();
		object.put("amount", amount*100);
		object.put("currency","INR");
		object.put("receipt", "txn_123456");
		
		Order order=client.Orders.create(object);
		System.out.println(order);
		return new ResponseEntity<String>(order.toString(),HttpStatus.OK);
	}
	
	/**
	 * Save the payment details to the payment table on successful payment
	 * @param paymentDetailsDTO contains the payment related information
	 * @return the string message on execution
	 */
	@PostMapping("/savePaymentDetails")
	public ResponseEntity<String>  savePaymentDetails(@RequestBody PaymentDetailsDTO paymentDetailsDTO){
		
		return new ResponseEntity<String>(paymentDetailsServiceImpl.savePaymentDetails(paymentDetailsDTO),HttpStatus.OK);
		
	}
}

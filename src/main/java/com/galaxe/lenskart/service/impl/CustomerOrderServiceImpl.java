package com.galaxe.lenskart.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.galaxe.lenskart.dto.CustomerOrderDTO;
import com.galaxe.lenskart.entity.Cart;
import com.galaxe.lenskart.entity.CartItem;
import com.galaxe.lenskart.entity.Customer;
import com.galaxe.lenskart.entity.CustomerAddress;
import com.galaxe.lenskart.entity.CustomerOrder;
import com.galaxe.lenskart.entity.DeliveryAddress;
import com.galaxe.lenskart.entity.OrderItem;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.mapper.CustomerOrderMapper;
import com.galaxe.lenskart.repository.CartItemRepository;
import com.galaxe.lenskart.repository.CartRepository;
import com.galaxe.lenskart.repository.CustomerAddressRepository;
import com.galaxe.lenskart.repository.CustomerOrderRepository;
import com.galaxe.lenskart.repository.CustomerRepository;
import com.galaxe.lenskart.repository.DeliveryAddressRepository;
import com.galaxe.lenskart.repository.OrderItemRepository;
import com.galaxe.lenskart.repository.PaymentDetailsRepository;
import com.galaxe.lenskart.repository.ProductRepository;
import com.galaxe.lenskart.service.CustomerOrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@EnableScheduling
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	CustomerOrderMapper customerOrderMapper;

	@Autowired
	CustomerOrderRepository customerOrderRepository;

	@Autowired
	CustomerAddressRepository customerAddressRepository;

	@Autowired
	DeliveryAddressRepository deliveryAddressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;

	/**
	 * Creates a new order when the payment is successful for the current user. The delivery address is set and the customer order is saved. The order items are saved from the cart items. Finally the cart is cleared.
	 * @param addressId the saved address of the customer to fetch the selected delivery address
	 * @param paymentMode the mode of payment
	 * @param discount the discount on the order
	 * @param paymentId the unique Id generated from razor pay after payment
	 * @return the string message on execution
	 */
	@Override
	public String createOrder(Integer addressId, String paymentMode,

			Long discount,String paymentId) {
		System.out.println("inside service");

		CustomerOrder customerOrder = new CustomerOrder();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		Cart cart = cartRepository.findByCustomer(customer);

		CustomerAddress customerAddress = customerAddressRepository.findByAddressId(addressId);
		DeliveryAddress deliveryAddress = DeliveryAddress.builder().firstName(customerAddress.getFirstName())
				.lastName(customerAddress.getLastName()).gender(customerAddress.getGender())
				.phoneNumber(customerAddress.getPhoneNumber()).email(customerAddress.getEmail())
				.addressLine1(customerAddress.getAddressLine1()).addressLine2(customerAddress.getAddressLine2())
				.city(customerAddress.getCity()).postalCode(customerAddress.getPostalCode())
				.state(customerAddress.getState()).country(customerAddress.getCountry())
				.addressType(customerAddress.getAddressType()).build();

		deliveryAddressRepository.save(deliveryAddress);

		// setdeliveryaddr
		customerOrder.setDeliveryAddress(deliveryAddress);
		// setcustomer
		customerOrder.setCustomer(customer);

		customerOrder.setDiscount(discount);

		customerOrder.setPaymentMode(paymentMode);
		
		customerOrder.setPaymentId(paymentId);

		java.util.Date currentDate = new java.util.Date();

		Timestamp timestamp = new Timestamp(currentDate.getTime());
		
		Calendar cal = Calendar.getInstance();
	    
	    cal.setTimeInMillis(timestamp.getTime());
	    
	    cal.add(Calendar.DAY_OF_MONTH, 5);
	    Timestamp timestamp1 = new Timestamp(cal.getTime().getTime());

	    customerOrder.setExpectedDeliveryDate(timestamp1);
	    
		customerOrder.setOrderDate(timestamp);

		customerOrder.setOrderStatus("Order Placed");

		customerOrderRepository.save(customerOrder);

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (CartItem c : cart.getCartItem()) {
			OrderItem orderItem = OrderItem.builder().product(c.getProduct()).quantity(c.getQuantity())
					.customerOrder(customerOrder).build();

			orderItems.add(orderItem);

			orderItemRepository.save(orderItem);

		}

		cartItemRepository.deleteCartItems(cart.getCartId());

		return "success";
	}

	/**
	 * Get all the order information for the current user
	 * @return the list of cutomerOrderDTO 
	 */
	@Override
	public List<CustomerOrderDTO> getCustomerOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Customer customer = customerRepository.findByEmail(username);
		List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomer(customer);

		List<CustomerOrderDTO> customerOrderDTOs = customerOrders.stream()
				.map(c -> customerOrderMapper.toCustomerOrderDTO(c)).collect(Collectors.toList());

		return customerOrderDTOs;
	}

	

	/**
	 * Deleting the order when order is cancelled
	 * @param OrderId the Id of the order which is cancelled
	 * @return string message on execution
	 */
	@Override
	public String deleteOrder(Integer OrderId) {
		CustomerOrder customerOrder=customerOrderRepository.findById(OrderId).orElse(null);
		orderItemRepository.deleteByCustomerOrder(customerOrder);
		paymentDetailsRepository.deleteByCustomerOrder(customerOrder);
		deliveryAddressRepository.deleteByCustomerOrder(customerOrder);
		customerOrderRepository.deleteByOrderId(OrderId);
		return "Success";
	}

	/**
	 * Deletes a single product in the order 
	 * @param orderId the id of the order from which product have to be removed
	 * @param productId the Id of the product to be removed from order
	 * @return the string message on execution
	 */
	@Override
	public String deleteOrderedProduct(Integer orderId, Integer productId) {
		
		Product product=productRepository.findByProdId(productId);
		CustomerOrder customerOrder=customerOrderRepository.findById(orderId).orElse(null);
		orderItemRepository.deleteByCustomerOrderAndProduct(customerOrder, product);
		return "Success";
	}

	/**
	 * Update the order status everyday at 11 10 A M. This method executes everyday at 10 AM if service is active.
	 */
	@Scheduled(cron = "0 10 11 * * *")
	public void updateDeliveryStatus() {
		customerOrderRepository.updateDeliveryStatus();
		}


	

}

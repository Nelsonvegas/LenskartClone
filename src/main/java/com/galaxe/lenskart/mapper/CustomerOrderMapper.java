package com.galaxe.lenskart.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CustomerOrderDTO;
import com.galaxe.lenskart.entity.CustomerOrder;

@Component
public class CustomerOrderMapper {

	@Autowired
	DeliveryAddressMapper deliveryAddressMapper;

	@Autowired
	OrderItemMapper orderItemMapper;

	public CustomerOrderDTO toCustomerOrderDTO(CustomerOrder customerOrder) {
		CustomerOrderDTO customerOrderDTO = CustomerOrderDTO.builder().orderId(customerOrder.getOrderId())
				.orderDate(customerOrder.getOrderDate()).paymentMode(customerOrder.getPaymentMode()).expectedDeliveryDate(customerOrder.getExpectedDeliveryDate())
				.paymentId(customerOrder.getPaymentId()).discount(customerOrder.getDiscount())
				.orderStatus(customerOrder.getOrderStatus())
				.deliveryAddressDTO(deliveryAddressMapper.toDeliveryAddressDTO(customerOrder.getDeliveryAddress()))
				.orderItemsDTO(customerOrder.getOrderItems().stream().map(o -> orderItemMapper.toOrderItemDTO(o))
						.collect(Collectors.toList()))
				.build();

		return customerOrderDTO;

	}

	public CustomerOrder toCustomerOrder(CustomerOrderDTO customerOrderDTO) {
		CustomerOrder customerOrder = CustomerOrder.builder().orderId(customerOrderDTO.getOrderId())
				.orderDate(customerOrderDTO.getOrderDate()).paymentMode(customerOrderDTO.getPaymentMode()).expectedDeliveryDate(customerOrderDTO.getExpectedDeliveryDate())
				.paymentId(customerOrderDTO.getPaymentId()).discount(customerOrderDTO.getDiscount())
				.orderStatus(customerOrderDTO.getOrderStatus())
				.deliveryAddress(deliveryAddressMapper.toDeliveryAddress(customerOrderDTO.getDeliveryAddressDTO()))
				.orderItems(customerOrderDTO.getOrderItemsDTO().stream().map(o -> orderItemMapper.toOrderItem(o))
						.collect(Collectors.toList()))
				.build();

		return customerOrder;

	}

}

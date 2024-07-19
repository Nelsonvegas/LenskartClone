package com.galaxe.lenskart.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.OrderItemDTO;
import com.galaxe.lenskart.entity.OrderItem;

@Component
public class OrderItemMapper {

	@Autowired
	ProductMapper productMapper;

	public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
		OrderItemDTO orderItemDTO = OrderItemDTO.builder().orderItemId(orderItem.getOrderItemId())
				.quantity(orderItem.getQuantity()).productDTO(productMapper.toProductDTO(orderItem.getProduct()))
				.build();

		return orderItemDTO;
	}

	public OrderItem toOrderItem(OrderItemDTO orderItemDTO) {
		OrderItem orderItem = OrderItem.builder().orderItemId(orderItemDTO.getOrderItemId())
				.quantity(orderItemDTO.getQuantity()).product(productMapper.toProduct(orderItemDTO.getProductDTO()))
				.build();

		return orderItem;
	}

}

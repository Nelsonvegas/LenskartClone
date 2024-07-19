package com.galaxe.lenskart.dto;

import java.sql.Timestamp;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDTO {

	private Integer orderId;
	private Timestamp orderDate;
	private Timestamp expectedDeliveryDate;
	private String paymentMode;
	private Long discount;
	private String orderStatus;
	private String paymentId;

	
	private List<OrderItemDTO> orderItemsDTO;
	
	private DeliveryAddressDTO deliveryAddressDTO;
}

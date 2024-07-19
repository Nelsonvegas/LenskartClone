package com.galaxe.lenskart.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

	private Integer orderItemId;
	
	private Integer quantity;
	
	private ProductDTO productDTO;
	
	
}

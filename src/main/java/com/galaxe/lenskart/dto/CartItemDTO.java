package com.galaxe.lenskart.dto;



import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

	
	private int cartItemId;
	private int quantity;
	
	
	private ProductDTO productDTO;
}

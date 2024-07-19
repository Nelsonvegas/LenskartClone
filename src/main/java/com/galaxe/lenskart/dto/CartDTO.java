package com.galaxe.lenskart.dto;

import java.util.List;



import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	private int cartId;
	private long totalPrice;
	private List<CartItemDTO> cartItem;


}

package com.galaxe.lenskart.dto;

import java.sql.Timestamp;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO {

	private Integer wishlistId;
	
	private Timestamp dateAdded;
	
	private CustomerDTO customerDTO;
	
	private ProductDTO productDTO;



}

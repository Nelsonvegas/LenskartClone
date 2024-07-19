package com.galaxe.lenskart.dto;


import com.galaxe.lenskart.entity.SubCategory;

import jakarta.persistence.Lob;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private long prodId;
	private String prodName;
	private String prodDescription;
	private int price;
	@Lob
	private String prodImg;
	private int quantity;
	private SubCategory subCategory;
	
	
}

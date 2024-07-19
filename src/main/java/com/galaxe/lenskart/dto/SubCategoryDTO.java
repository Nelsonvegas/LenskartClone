package com.galaxe.lenskart.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDTO {

	private int subCategoryId;
	private String subCategoryName;
	private String description;
	private CategoryDTO parentCategory;
	private List<ProductDTO> products;

}

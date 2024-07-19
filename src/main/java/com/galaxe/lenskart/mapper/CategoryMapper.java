package com.galaxe.lenskart.mapper;

import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.CategoryDTO;
import com.galaxe.lenskart.entity.Category;

@Component
public class CategoryMapper {

	public CategoryDTO toCategoryDTO(Category category) {
		CategoryDTO categoryDTO = CategoryDTO.builder().id(category.getCategoryId()).name(category.getCategoryName())
				.description(category.getDescription()).build();

		return categoryDTO;
	}

	public Category toCategory(CategoryDTO categoryDTO) {
		Category category = Category.builder().categoryId(categoryDTO.getId()).categoryName(categoryDTO.getName())
				.description(categoryDTO.getDescription()).build();

		return category;
	}

}

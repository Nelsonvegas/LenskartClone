package com.galaxe.lenskart.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.SubCategoryDTO;
import com.galaxe.lenskart.entity.SubCategory;

@Component
public class SubCategoryMapper {

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	ProductMapper productMapper;

	public SubCategoryDTO toSubCategoryDTO(SubCategory subCategory) {

		SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder().subCategoryId(subCategory.getSubCategoryId())
				.subCategoryName(subCategory.getSubCategoryName()).description(subCategory.getDescription())
				.parentCategory(categoryMapper.toCategoryDTO(subCategory.getParentCategory())).products(subCategory
						.getProducts().stream().map(p -> productMapper.toProductDTO(p)).collect(Collectors.toList()))
				.build();

		return subCategoryDTO;
	}

	public SubCategory toSubCategory(SubCategoryDTO subCategoryDTO) {

		SubCategory subCategory = SubCategory.builder().subCategoryId(subCategoryDTO.getSubCategoryId())
				.subCategoryName(subCategoryDTO.getSubCategoryName()).description(subCategoryDTO.getDescription())
				.parentCategory(categoryMapper.toCategory(subCategoryDTO.getParentCategory())).products(subCategoryDTO
						.getProducts().stream().map(p -> productMapper.toProduct(p)).collect(Collectors.toList()))
				.build();

		return subCategory;

	}

}

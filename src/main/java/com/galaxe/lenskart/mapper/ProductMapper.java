package com.galaxe.lenskart.mapper;

import org.springframework.stereotype.Component;

import com.galaxe.lenskart.dto.ProductDTO;
import com.galaxe.lenskart.entity.Product;

@Component
public class ProductMapper {
	
	
	public ProductDTO toProductDTO(Product product) {
		ProductDTO productDTO=ProductDTO.builder().prodId(product.getProdId()).prodName(product.getProdName()).prodDescription(product.getProdDescription()).price(product.getPrice()).prodImg(product.getProdImg()).quantity(product.getQuantity()).subCategory(product.getSubCategory()).build();
	
		return productDTO;
	}
	
	public Product toProduct(ProductDTO productDTO) {
		Product product=Product.builder().prodId(productDTO.getProdId()).prodDescription(productDTO.getProdDescription()).prodName(productDTO.getProdName()).price(productDTO.getPrice()).prodImg(productDTO.getProdImg()).quantity(productDTO.getQuantity()).subCategory(productDTO.getSubCategory()).build();
	
		return product;
	}
	
	
	
}

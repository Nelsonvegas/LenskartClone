package com.galaxe.lenskart.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.galaxe.lenskart.dto.ProductDTO;
import com.galaxe.lenskart.entity.Product;

public interface ProductService {
	
	Product saveProduct(String name,String description,int price,MultipartFile file,int category) throws Exception;
	List<ProductDTO> searchProducts(String query);
	List<Object> searchresults(String query);
	List<Object> getCategoryList(String query);
	ProductDTO getOneProduct(Integer productId);
}

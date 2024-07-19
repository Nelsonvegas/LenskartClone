package com.galaxe.lenskart.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.lenskart.dto.ProductDTO;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.entity.SubCategory;
import com.galaxe.lenskart.mapper.ProductMapper;
import com.galaxe.lenskart.repository.CategoryRepository;
import com.galaxe.lenskart.repository.ProductRepository;
import com.galaxe.lenskart.repository.SubCategoryRepository;
import com.galaxe.lenskart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	ProductMapper productMapper;

	/**
	 * Save the product to the product table
	 * @param name the name of the product
	 * @param description the description for the product
	 * @param price the price of the product
	 * @param file the image file to be uploaded into database
	 * @param subCategory the sub category of the product
	 * @throws throws IOException if there is input or output exception
	 * @return the product entity of the saved product
	 */
	@Override
	public Product saveProduct(String name, String description, int price, MultipartFile file, int subCategory)
			throws IOException {
		SubCategory category1 = subCategoryRepository.findById(subCategory).orElse(null);
		Product product = Product.builder().prodImg(Base64.getEncoder().encodeToString(file.getBytes())).prodName(name)
				.price(price).quantity(5).prodDescription(description).subCategory(category1).build();

		productRepository.save(product);
		return product;

	}

	/**
	 * Search the products based on query
	 * @param the query which needs to be searched
	 * @return the list of products which matches the conditions
	 */
	@Override
	public List<ProductDTO> searchProducts(String query) {
		List<Product> products = productRepository.searchProducts(query);
		List<ProductDTO> productDTOs = products.stream().map(p -> productMapper.toProductDTO(p))
				.collect(Collectors.toList());

		return productDTOs;
	}

	/**
	 * Search the matching product names for suggestions
	 * @param the query which is entered in search box
	 * @return list of product name and category name
	 */
	@Override
	public List<Object> searchresults(String query) {

		return productRepository.searchresult(query);
	}

	/**
	 * Get the category for category suggestions
	 * @param the query entered into the search box
	 * @return list of category names
	 */
	@Override
	public List<Object> getCategoryList(String query) {
		return categoryRepository.categoryList(query);
	}

	/**
	 * Get one product based on id
	 * @param the product Id of the product
	 * @return the productDTO of the product
	 */
	@Override
	public ProductDTO getOneProduct(Integer productId) {
		Product product=productRepository.findByProdId(productId);
		return productMapper.toProductDTO(product);
	}

}

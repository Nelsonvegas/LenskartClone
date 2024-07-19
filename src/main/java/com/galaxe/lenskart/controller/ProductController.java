package com.galaxe.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.lenskart.dto.ProductDTO;
import com.galaxe.lenskart.entity.Product;
import com.galaxe.lenskart.service.ProductService;



@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	/**
	 * Save the product to the product table
	 * @param file the image of the file that needs to be inserted into database
	 * @param name the name of the product
	 * @param price the price of the product
	 * @param desc the description of the product
	 * @param category the sub category id of the product
	 * @return Product entity containing the currently stored product
	 * @throws Exception on any exception
	 */
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestParam("file") MultipartFile file, @RequestParam("prodname") String name,
			@RequestParam("price") int price, @RequestParam("desc") String desc,@RequestParam("category") int category) throws Exception {

		return new ResponseEntity<Product>(productService.saveProduct(name, desc, price,file,category),HttpStatus.OK);

	}
	
	/**
	 * Suggestions for the search bar
	 * @param query the search query entered in the search input field
	 * @return the object containing product category and product name
	 */
	@GetMapping("/searchresults/{query}")
	public ResponseEntity<List<Object>> searchResult(@PathVariable String query){
		return new ResponseEntity<List<Object>>(productService.searchresults(query),HttpStatus.OK);
	}
	
	/**
	 * The search method for the product based on name and description and  product category
	 * @param query the search query entered in the search input field
	 * @return the list of products matching the condition
	 */
	@GetMapping("/search/{query}")
	public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable String query){
		return new ResponseEntity<List<ProductDTO>>(productService.searchProducts(query),HttpStatus.OK);
	}
	
	/**
	 * Returns the category suggestions for the search bar input
	 * @param query the search query entered in the search input field
	 * @return list of names of the category
	 */
	@GetMapping("/categoryList/{query}")
	public ResponseEntity<?> getProductList(@PathVariable String query){
		return new ResponseEntity<>(productService.getCategoryList(query),HttpStatus.OK);
	}
	
	/**
	 * Retrieve one product based on product id
	 * @param productId the id of the product that needs to be retrieved
	 * @return the productDTO of the matching product id
	 */
	@GetMapping("/getOneProduct/{productId}")
	public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Integer productId){
		return new ResponseEntity<ProductDTO>(productService.getOneProduct(productId),HttpStatus.OK);
	}

}

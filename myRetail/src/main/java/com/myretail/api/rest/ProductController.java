package com.myretail.api.rest;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.api.dto.ProductDto;
import com.myretail.api.exception.InvalidRequestException;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.model.Product;
import com.myretail.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	ProductController(ProductService productServiceImpl){
		this.productService = productServiceImpl;
	}
	
	@GetMapping("/{id}")
	public ProductDto retrieveProduct(@Valid @PathVariable(value = "id") String id){
		return productService.getProduct(id);
	}
	
	@PutMapping("/{id}")
	public void updateProduct(@Valid @PathVariable(value = "id") String id, @RequestBody ProductDto productDto){
		if(!id.equals(productDto.getId())) {
			throw new InvalidRequestException("product id does not match id in the request body");
		}
		Product product = productService.findById(id);
		if(product!=null) {
			productService.updatePrice(id, productDto.getCurrentPrice());
		}else {
			throw new ProductNotFoundException(String.format("Product not found for id:%s", id));
		}
		
	}
	

}

package com.target.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.api.service.ProductDetailsService;

@RestController
@RequestMapping(value = "/productdetails")
public class ProductDetailsController {
	private ProductDetailsService productDetailsService;
	
	@Autowired
	ProductDetailsController(ProductDetailsService productDetailsService){
		this.productDetailsService = productDetailsService;
	}
	
	@GetMapping(value = "/{id}/name")
	public String getProductName(@PathVariable(value="id") String id) {
		return productDetailsService.getNameById(id);	
	}
}

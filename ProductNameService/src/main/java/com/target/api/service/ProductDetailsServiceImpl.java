package com.target.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.api.model.ProductDetails;
import com.target.api.repository.ProductDetailsRepository;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	private ProductDetailsRepository productDetailsRepository;
	
	@Autowired
	ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository){
		this.productDetailsRepository = productDetailsRepository;
	}
	
	@Override
	public String getNameById(String id) {
		Optional<ProductDetails> product = productDetailsRepository.findById(id);
		if(product.isPresent()) {
			return product.get().getName();
		}
		return null;
	}

}

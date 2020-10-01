package com.myretail.api.service;

import com.myretail.api.dto.ProductDto;
import com.myretail.api.model.Price;
import com.myretail.api.model.Product;

public interface ProductService {
	ProductDto getProduct(String id);
	Product findById(String id);
	void updatePrice(String id, Price price);
}

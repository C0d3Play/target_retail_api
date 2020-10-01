package com.myretail.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.api.dto.ProductDto;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.model.Price;
import com.myretail.api.model.Product;
import com.myretail.api.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{


	private ProductRepository productRepository;
	
	private RestTemplate restTemplate;
	
	@Value("${target.api.base.url}")
	private String targetApiBaseUrl;
	

	@Value("${target.api.product.uri}")
	private String productUri;
	
	@Autowired
	ProductServiceImpl(ProductRepository productRepository){
		this.productRepository = productRepository;
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public ProductDto getProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			String url = targetApiBaseUrl.concat(String.format(productUri, id));
			ResponseEntity<String> productNameEntity = restTemplate.getForEntity(url, String.class);
			
			if(!productNameEntity.getStatusCode().is2xxSuccessful()) {
				throw new ProductNotFoundException(String.format("Couldn't fetch product name for the id:%s", id));
			}
			
			return new ProductDto(id, product.get().getCurrentPrice(), productNameEntity.getBody());
		}
		
		throw new ProductNotFoundException(String.format("Product not found for id:%s", id));
	}

	@Override
	public Product findById(String id) {
		Optional<Product> product = productRepository.findById(id);
		if(!product.isPresent()) {
			throw new ProductNotFoundException(String.format("Product not found for id:%s", id));
		}
		return product.get();
	}
	
	@Override
	public void updatePrice(String id, Price price) {
		Product product = new Product(id, price);
		productRepository.save(product);
	}

}

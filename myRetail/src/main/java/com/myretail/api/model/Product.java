package com.myretail.api.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {

	@Id
	private String productId;

	@NotNull(message = "Price cannot be null")
	private Price currentPrice;
	
	public String getProductId() {
		return productId;
	}

	public Product(String productId, @NotNull(message = "Price cannot be null") Price currentPrice) {
		super();
		this.productId = productId;
		this.currentPrice = currentPrice;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Price getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}

}

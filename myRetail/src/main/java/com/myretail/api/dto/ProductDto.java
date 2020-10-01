package com.myretail.api.dto;

import javax.validation.constraints.NotNull;

import com.myretail.api.model.Price;

public class ProductDto {
	@NotNull
	private String id;
	@NotNull
	private Price currentPrice;
	private String name;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Price getCurrentPrice() {
		return currentPrice;
	}


	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ProductDto(String id, Price price, String name) {
		super();
		this.id = id;
		this.currentPrice = price;
		this.name = name;
	}
	
}

package com.target.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductDetails")
public class ProductDetails {

	@Id
	private String productId;
	
	@NotEmpty
	private String name;
	private String description;
	private String category;
	private String modelCode;
	
	public ProductDetails(String productId, @NotEmpty String name, String description, String category, String modelCode) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.modelCode = modelCode;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	
}

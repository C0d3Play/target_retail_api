package com.myretail.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Price {
	@NotNull(message = "Price value cannot be null")
	@Min(value=0, message="Price should be a positive value")
	private Double value;
	
	@NotEmpty(message = "Currency code value cannot be empty")
	private String currencyCode;

	public Price(
			@NotNull(message = "Price value cannot be null") @Min(value = 0, message = "Price should be a positive value") Double value,
			@NotEmpty(message = "Currency code value cannot be empty") String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}

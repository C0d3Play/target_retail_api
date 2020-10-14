package com.myretail.api.rest;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myretail.api.dto.ProductDto;
import com.myretail.api.exception.InvalidRequestException;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.model.Price;
import com.myretail.api.model.Product;
import com.myretail.api.service.ProductService;


public class ProductControllerTest {
	
	@Mock
	private ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void when_retrieveProduct_then_returnProductDetails() {
		ProductDto product = new ProductDto("123", new Price(100.0, "USD"), "name123");
		when(productService.getProduct("123")).thenReturn(product);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ProductDto productDto = productController.retrieveProduct("123");

		verify(productService).getProduct("123");
		assertEquals(productDto.getId(), "123");
		assertEquals(productDto.getCurrentPrice().getValue(), Double.valueOf(100.0));
		assertEquals(productDto.getCurrentPrice().getCurrencyCode(), "USD");
		assertEquals(productDto.getName(), "name123");
	}

	@Test
	public void when_retrieveProduct_then_expectProductNotFoundException() {
		when(productService.getProduct("invalidId")).thenThrow(new ProductNotFoundException(""));
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> productController.retrieveProduct("invalidId"));
	}
	
	@Test
	public void when_updateProduct_then_returnSuccess(){
		Price newPrice = new Price(299.99, "USD");
		Product product = new Product("123", newPrice);
		
		when(productService.findById("123")).thenReturn(product);
		doNothing().when(productService).updatePrice("123", newPrice);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ProductDto productDto = new ProductDto("123", newPrice, "");
		productController.updateProduct("123", productDto);

		verify(productService).findById("123");
		verify(productService).updatePrice("123", newPrice);
		
		Product updatedProduct = productService.findById("123");
		assertEquals(updatedProduct.getCurrentPrice().getValue(), Double.valueOf(299.99));
		assertEquals(updatedProduct.getCurrentPrice().getCurrencyCode(), "USD");
			
	}
	
	@Test
	public void when_updateProduct_then_expectProductNotFoundException(){
		when(productService.findById("invalidId")).thenThrow(new ProductNotFoundException(""));
			
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Price newPrice = new Price(100.0, "USD");
        ProductDto productDto = new ProductDto("invalidId", newPrice, "");
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> productController.updateProduct("invalidId", productDto));
		
		verify(productService).findById("invalidId");
		verifyNoMoreInteractions(productService);	
	}
	
	@Test
	public void when_updateProduct_then_expectInvalidRequestException(){

		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Price newPrice = new Price(100.0, "USD");
        ProductDto productDto = new ProductDto("id1", newPrice, "");
		
		Assertions.assertThrows(InvalidRequestException.class, () -> productController.updateProduct("id2", productDto));
		verifyNoInteractions(productService);	
	}

}

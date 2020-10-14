package com.myretail.api.service;

import com.myretail.api.dto.ProductDto;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.model.Price;
import com.myretail.api.model.Product;
import com.myretail.api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;

public class ProductServiceImplTest {

	private static final String PRODUCT_NAME = "productName";
	private static final String TARGET_API_BASE_URL = "targetApiBaseUrl";
	private static final String PRODUCT_URI = "productUri";
	private static final String REST_TEMPLATE = "restTemplate";

	@Mock
	ProductRepository productRepository;

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	ProductServiceImpl productService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(productService, TARGET_API_BASE_URL, "http://productNameService");
		ReflectionTestUtils.setField(productService, PRODUCT_URI, "/product");
		ReflectionTestUtils.setField(productService, REST_TEMPLATE, restTemplate);
	}

	@Test
	public void when_getProduct_then_returnProductDetails() {
		Product product = new Product("123", new Price(100.0, "USD"));
		ResponseEntity<String> responseEntity = new ResponseEntity<>(PRODUCT_NAME, HttpStatus.ACCEPTED);
		when(productRepository.findById("123")).thenReturn(Optional.of(product));
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

		ProductDto productDto = productService.getProduct("123");

		verify(productRepository).findById("123");
		verify(restTemplate).getForEntity(anyString(), eq(String.class));
		assertEquals(productDto.getId(), "123");
		assertEquals(productDto.getCurrentPrice().getValue(), Double.valueOf(100.0));
		assertEquals(productDto.getCurrentPrice().getCurrencyCode(), "USD");
		assertEquals(productDto.getName(), PRODUCT_NAME);
	}

	@Test
	public void when_getProductWithInvalidId_then_expectProductNotFoundException() {
		when(productRepository.findById("invalidId")).thenReturn(Optional.empty());

		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProduct("invalidId"));
	}

	@Test
	public void when_getProduct_errorResponseFromTarget_then_expectProductNotFoundException() {
		Product product = new Product("123", new Price(100.0, "USD"));
		ResponseEntity<String> responseEntity = new ResponseEntity<>(PRODUCT_NAME, HttpStatus.BAD_REQUEST);
		when(productRepository.findById("123")).thenReturn(Optional.of(product));
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProduct("123"));

		verify(productRepository).findById("123");
		verify(restTemplate).getForEntity(anyString(), eq(String.class));
	}

	@Test
	public void when_findById_then_returnProduct() {
		Product product = new Product("123", new Price(100.0, "USD"));
		when(productRepository.findById("123")).thenReturn(Optional.of(product));

		Product result = productService.findById("123");

		verify(productRepository).findById("123");
		assertEquals(result, product);
	}

	@Test
	public void when_findByIdWithInvalidId_then_expectProductNotFoundException() {
		when(productRepository.findById("invalidId")).thenReturn(Optional.empty());

		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById("invalidId"));
	}


	@Test
	public void when_updatePrice_then_expectRepositoryMethodToBECalled() {
		productService.updatePrice("123", new Price(200.0, "USD"));

		verify(productRepository).save(any());
	}

}

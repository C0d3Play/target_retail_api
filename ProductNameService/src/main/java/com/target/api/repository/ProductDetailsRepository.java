package com.target.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.target.api.model.ProductDetails;

@Repository
public interface ProductDetailsRepository extends MongoRepository<ProductDetails, String>{

}

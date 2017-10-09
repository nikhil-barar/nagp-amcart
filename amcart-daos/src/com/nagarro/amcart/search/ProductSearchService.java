package com.nagarro.amcart.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nagarro.amcart.models.product.Product;

public interface ProductSearchService {
	Product save(Product Product);

	Product findOne(String id);

	Iterable<Product> findAll();

	Page<Product> findByName(String name, PageRequest pageRequest);
}
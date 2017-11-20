package com.nagarro.amcart.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nagarro.amcart.models.product.Product;

public interface ProductSearchRepository {
	Product save(Product Product);

	Iterable<Product> findAll();

	Page<Product> findByName(String name, PageRequest pageRequest);

    Iterable<Product> save(Iterable<Product> products);
}
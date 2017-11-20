package com.nagarro.amcart.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.search.repository.ElasticSearchProductRepository;
import com.nagarro.amcart.search.service.ProductSearchRepository;

@Service("productSearchRepository")
public class ProductSearchRepositoryImpl implements ProductSearchRepository {
	
	@Autowired
	private ElasticSearchProductRepository elasticsearchProductRepository;

	@Override
	public Product save(Product product) {
		return elasticsearchProductRepository.save(product);
	}

	@Override
	public Iterable<Product> findAll() {
		return elasticsearchProductRepository.findAll();
	}

	@Override
	public Page<Product> findByName(String name, PageRequest pageRequest) {
		return elasticsearchProductRepository.findByName(name, pageRequest);
	}
	
	@Override
	public Iterable<Product> save(Iterable<Product> products) {
	    return elasticsearchProductRepository.save(products);
	}
}
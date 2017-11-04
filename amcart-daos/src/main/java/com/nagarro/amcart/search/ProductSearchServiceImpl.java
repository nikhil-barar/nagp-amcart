package com.nagarro.amcart.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.models.product.Product;

@Service("searchService")
public class ProductSearchServiceImpl implements ProductSearchService {
	
	@Autowired
	private ProductSearchRepository searchRepository;

	@Override
	public Product save(Product Product) {
		searchRepository.save(Product);
		return Product;
	}

	@Override
	public Product findOne(String id) {
		return searchRepository.findOne(id);
	}

	@Override
	public Iterable<Product> findAll() {
		return searchRepository.findAll();
	}

	@Override
	public Page<Product> findByName(String name, PageRequest pageRequest) {
		return searchRepository.findByName(name, pageRequest);
	}
}
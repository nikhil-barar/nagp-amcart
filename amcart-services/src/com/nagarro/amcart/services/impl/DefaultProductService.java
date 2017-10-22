package com.nagarro.amcart.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.daos.ProductDao;
import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.ProductService;

public class DefaultProductService implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product findById(Integer id) {
		return productDao.findOne(id);
	}

	@Override
	public List<Product> findByName(String name) {
		List<Product> productList = new ArrayList<>();
		Collection<Product> result = productDao.findByName(name);
		if (CollectionUtils.isNotEmpty(result)) {
			productList.addAll(result);
		}
		return productList;
	}

	@Override
	public List<Product> findByStatus(ProductStatus status) {
		List<Product> productList = new ArrayList<>();
		Collection<Product> result = productDao.findByStatus(status);
		if (CollectionUtils.isNotEmpty(result)) {
			productList.addAll(result);
		}
		return productList;
	}

	@Override
	public List<Product> findByCategories(Set<Category> categories) {
		List<Product> productList = new ArrayList<>();
		Collection<Product> result = productDao.findByCategories(categories);
		if (CollectionUtils.isNotEmpty(result)) {
			productList.addAll(result);
		}
		return productList;
	}

	@Override
	public List<Product> findByCategoriesAndStatus(Set<Category> categories, ProductStatus status) {
		List<Product> productList = new ArrayList<>();
		Collection<Product> result = productDao.findByCategoriesAndStatus(categories, status);
		if (CollectionUtils.isNotEmpty(result)) {
			productList.addAll(result);
		}
		return productList;
	}

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	/**
	 * @return the productDao
	 */
	public ProductDao getProductDao() {
		return productDao;
	}

}

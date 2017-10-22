package com.nagarro.amcart.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.daos.CategoryDao;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.services.CategoryService;

public class DefaultCategoryService implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category getCategoryByCode(String code) {
		return getCategoryDao().findByCode(code);
	}

	/**
	 * @return the categoryDao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

}
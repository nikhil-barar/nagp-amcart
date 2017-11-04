package com.nagarro.amcart.services;

import java.util.List;

import com.nagarro.amcart.models.product.Category;

public interface CategoryService {

	Category getCategoryByCode(String code);
	
	List<Category> getAllCategories();
}

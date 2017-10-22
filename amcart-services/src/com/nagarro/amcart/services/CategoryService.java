package com.nagarro.amcart.services;

import com.nagarro.amcart.models.product.Category;

public interface CategoryService {

	Category getCategoryByCode(String code);
}

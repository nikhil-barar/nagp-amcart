package com.nagarro.amcart.services;

import java.util.List;
import java.util.Set;

import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;

public interface ProductService {

	
	/**
	 * finds products using id
	 * 
	 * @param name
	 * @return Product
	 */
	Product findById(Integer id);
	
	/**
	 * finds products using id
	 * 
	 * @param name
	 * @return Product
	 */
	List<Product> findAllProducts();

	/**
	 * finds products using name
	 * 
	 * @param name
	 * @return List of Products
	 */
	List<Product> findByName(String name);

	/**
	 * finds products using productStatus
	 * 
	 * @param ProductStatus
	 * @return List of Products
	 */
	List<Product> findByStatus(ProductStatus status);

	/**
	 * finds products using categories and productStatus
	 * 
	 * @param Category
	 * @return List of Products
	 */
	List<Product> findByCategories(Set<Category> categories);

	/**
	 * finds products using categories and productStatus
	 * 
	 * @param Category
	 * @param ProductStatus
	 * @return List of Products
	 */
	List<Product> findByCategoriesAndStatus(Set<Category> categories, ProductStatus status);

}

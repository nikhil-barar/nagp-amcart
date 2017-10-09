package com.nagarro.amcart.daos.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Product;

/**
 * Defines DAO operations for the Product model.
 * 
 *
 */
@Repository
@Component("productDao")
public interface ProductDao extends JpaRepository<Product, String> {

	/**
	 * finds products using name
	 * @param name
	 * @return List of Products
	 */
	List<Product> findByName(String name);
	
	/**
	 * finds products using productStatus
	 * @param ProductStatus
	 * @return List of Products
	 */
	List<Product> findByStatus(ProductStatus status);
}
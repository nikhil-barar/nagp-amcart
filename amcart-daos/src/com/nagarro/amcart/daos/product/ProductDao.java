package com.nagarro.amcart.daos.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.daos.exceptions.DAOException;
import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Media;
import com.nagarro.amcart.models.product.Price;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.models.product.Stock;

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
	
	/**
	 * API to update stock value in product
	 */
	void updateStock(String productCode,Stock stock) throws DAOException;
	
	/**
	 * API to update price value in product
	 */
	void updatePrice(String productCode,Price price) throws DAOException;
	
	/**
	 * API to add media to a product
	 */
	void addMedia(String productCode,Media media) throws DAOException;
}
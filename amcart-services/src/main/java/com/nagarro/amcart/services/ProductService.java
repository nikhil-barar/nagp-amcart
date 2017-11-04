package com.nagarro.amcart.services;

import java.util.List;
import java.util.Set;

import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.exceptions.ModelNotFoundException;

public interface ProductService {

    /**
     * finds products using id
     * 
     * @param name
     * @return Product
     * @throws ModelNotFoundException
     */
    Product findById(Integer id);

    /**
     * finds products using id
     * 
     * @param name
     * @return Product
     * @throws ModelNotFoundException
     */
    List<Product> findAllProducts();

    /**
     * finds products using name
     * 
     * @param name
     * @return List of Products
     * @throws ModelNotFoundException
     */
    List<Product> findByName(String name) throws ModelNotFoundException;

    /**
     * finds products using productStatus
     * 
     * @param ProductStatus
     * @return List of Products
     * @throws ModelNotFoundException
     */
    List<Product> findByStatus(ProductStatus status) throws ModelNotFoundException;

    /**
     * finds products using categories and productStatus
     * 
     * @param Category
     * @return List of Products
     * @throws ModelNotFoundException
     */
    List<Product> findByCategories(Set<Category> categories) throws ModelNotFoundException;

    /**
     * finds products using categories and productStatus
     * 
     * @param Category
     * @param ProductStatus
     * @return List of Products
     * @throws ModelNotFoundException
     */
    List<Product> findByCategoriesAndStatus(Set<Category> categories, ProductStatus status)
            throws ModelNotFoundException;

    /**
     * Save or update.
     *
     * @param product
     *            the product
     * @return the product
     */
    Product saveOrUpdate(Product product);

}

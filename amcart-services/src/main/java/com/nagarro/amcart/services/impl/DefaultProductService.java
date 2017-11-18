package com.nagarro.amcart.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.ProductDao;
import com.nagarro.amcart.daos.exceptions.DAOException;
import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.ProductService;
import com.nagarro.amcart.services.exceptions.ModelNotFoundException;


@Service
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Long id) {
        Validate.notNull(id, "Parameter id cannot be null!");
        return productDao.findOne(id);
    }

    @Override
    public List<Product> findByName(String name) throws ModelNotFoundException {
        Validate.notNull(name, "Parameter name cannot be null!");
        try {
            return new ArrayList<>(productDao.findByName(name));
        } catch (DAOException e) {
            throw new ModelNotFoundException(this.getClass().getName(), "findByName", e);
        }
    }

    @Override
    public List<Product> findByStatus(ProductStatus status) throws ModelNotFoundException {
        Validate.notNull(status, "Parameter status cannot be null!");
        try {
            return new ArrayList<>(productDao.findByStatus(status));
        } catch (DAOException e) {
            throw new ModelNotFoundException(this.getClass().getName(), "findByStatus", e);
        }
    }

    @Override
    public List<Product> findByCategories(Set<Category> categories) throws ModelNotFoundException {
        Validate.notNull(categories, "Parameter Categries cannot be null!");
        try {
            return new ArrayList<>(productDao.findByCategories(categories));
        } catch (DAOException e) {
            throw new ModelNotFoundException(this.getClass().getName(), "findByCategories", e);
        }
    }

    @Override
    public List<Product> findByCategoriesAndStatus(Set<Category> categories, ProductStatus status)
            throws ModelNotFoundException {
        try {
            Validate.notNull(categories, "Parameter Categries cannot be null!");
            Validate.notNull(status, "Parameter status cannot be null!");
            return new ArrayList<>(productDao.findByCategoriesAndStatus(categories, status));
        } catch (DAOException exception) {
            throw new ModelNotFoundException(this.getClass().getName(), "findByCategoriesAndStatus", exception);
        }
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        Validate.notNull(product, "Parameter product cannot be null!");
        return productDao.saveAndFlush(product);
    }

    /**
     * @return the productDao
     */
    public ProductDao getProductDao() {
        return productDao;
    }

}

package com.nagarro.amcart.services.impl;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.CategoryDao;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.services.CategoryService;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getCategoryByCode(String code) {
        return getCategoryDao().findByCode(code);
    }

    @Override
    public List<Category> getAllCategories() {
        return getCategoryDao().findAll();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        Validate.notNull(category, "Parameter Category cannot be null!");
        return categoryDao.saveAndFlush(category);
    }

    /**
     * @return the categoryDao
     */
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

}
package com.nagarro.amcart.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.daos.ProductDao;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.search.service.ProductIndexerService;
import com.nagarro.amcart.search.service.ProductSearchRepository;

public class ProductIndexerServiceImpl implements ProductIndexerService {

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductSearchRepository elasticsearchService;
    
    @Override
    public void index(Product product) {
        elasticsearchService.save(product);
    }

    @Override
    public void indexAll() {
        List<Product> products = productDao.findAll();
        elasticsearchService.save(products);
    }
}

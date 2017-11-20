package com.nagarro.amcart.search.service;

import com.nagarro.amcart.models.product.Product;

public interface ProductIndexerService {
    
    void index(Product product);
    
    void indexAll();
}

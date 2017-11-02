package com.nagarro.amcart.search.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.search.ProductSearchService;

public class ProductSearchRepositoryTest {

    @Autowired
    ProductSearchService searchService;

    @Test
    public void testFindByName() throws Exception {
        Product product = new Product();
        // product.setCode("1");
        product.setName("US Polo Shirt");
        searchService.save(product);
        Product product2 = new Product();
        // product2.setCode("2");
        product2.setName("UCB Shirt");
        searchService.save(product2);
        Page<Product> products = searchService.findByName("US Polo Shirt", new PageRequest(0, 10));
        Page<Product> products2 = searchService.findByName("UCB Shirt", new PageRequest(0, 10));
        Page<Product> products3 = searchService.findByName("Levis Jeans", new PageRequest(0, 10));
        assertThat(products.getTotalElements(), is(1L));
        assertThat(products2.getTotalElements(), is(1L));
        assertThat(products3.getTotalElements(), is(0L));
    }
}
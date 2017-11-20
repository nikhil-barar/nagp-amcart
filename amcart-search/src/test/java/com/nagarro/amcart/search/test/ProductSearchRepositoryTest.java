package com.nagarro.amcart.search.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.search.service.ProductSearchRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/platform-config.xml" })
//@ContextConfiguration(classes = {App.class})
public class ProductSearchRepositoryTest {

    //@Autowired
    ProductSearchRepository productSearchRepository;

    //@Test
    public void testFindByName() throws Exception {
        Product product = new Product();
        // product.setCode("1");
        product.setName("US Polo Shirt");
        productSearchRepository.save(product);
        Product product2 = new Product();
        // product2.setCode("2");
        product2.setName("UCB Shirt");
        productSearchRepository.save(product2);
        Page<Product> products = productSearchRepository.findByName("US Polo Shirt", new PageRequest(0, 10));
        Page<Product> products2 = productSearchRepository.findByName("UCB Shirt", new PageRequest(0, 10));
        Page<Product> products3 = productSearchRepository.findByName("Levis Jeans", new PageRequest(0, 10));
        assertThat(products.getTotalElements(), is(1L));
        assertThat(products2.getTotalElements(), is(1L));
        assertThat(products3.getTotalElements(), is(0L));
    }
}
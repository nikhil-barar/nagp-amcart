package com.nagarro.amcart.facade.imports;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.connect.message.type.MessageType;
import com.nagarro.amcart.connect.processor.impl.AbstractCsvMessageProcessor;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.CategoryService;
import com.nagarro.amcart.services.ProductService;

@Component
public class ProductMessageProcessor extends AbstractCsvMessageProcessor<Product> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public ProductMessageProcessor() {
        super(Product.class);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.PRODUCT;
    }

    @Override
    protected Product processObject(Product product) {
        Collection<Category> categoryCodes = product.getCategories();
        if (CollectionUtils.isNotEmpty(categoryCodes)) {
            List<Category> categories = categoryCodes.stream()
                    .map(category -> categoryService.getCategoryByCode(category.getCode()))
                    .collect(Collectors.toList());
            product.setCategories(categories);
        }
        Product saveOrUpdate = productService.saveOrUpdate(product);
        saveOrUpdate.setCategories(categoryCodes);
        return saveOrUpdate;
    }
}

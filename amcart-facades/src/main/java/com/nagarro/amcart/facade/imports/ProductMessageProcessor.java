package com.nagarro.amcart.facade.imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.connect.message.type.MessageType;
import com.nagarro.amcart.connect.processor.impl.AbstractStringMessageProcessor;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.ProductService;

@Component
public class ProductMessageProcessor extends AbstractStringMessageProcessor<Product> {

    @Autowired
    private ProductService productService;

    public ProductMessageProcessor() {
        super(Product.class);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.PRODUCT;
    }

    @Override
    protected void processObject(Product product) {
        productService.saveOrUpdate(product);
    }

}

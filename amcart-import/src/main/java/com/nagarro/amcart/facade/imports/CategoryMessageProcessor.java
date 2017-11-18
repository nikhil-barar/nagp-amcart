package com.nagarro.amcart.facade.imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.connect.message.type.MessageType;
import com.nagarro.amcart.connect.processor.impl.AbstractCsvMessageProcessor;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.services.CategoryService;

@Component
public class CategoryMessageProcessor extends AbstractCsvMessageProcessor<Category> {

    @Autowired
    private CategoryService categoryService;

    public CategoryMessageProcessor() {
        super(Category.class);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CATEGORY;
    }

    @Override
    protected Category processObject(Category category) {
        return categoryService.saveOrUpdate(category);
    }

}

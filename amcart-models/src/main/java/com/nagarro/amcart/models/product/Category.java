package com.nagarro.amcart.models.product;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    private String code;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories",cascade= {CascadeType.MERGE})
    private Collection<Product> products;

    public Category() {
    }

    public Category(String code, String name, String description, Collection<Product> products) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the products
     */
    public Collection<Product> getProducts() {
        return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

}

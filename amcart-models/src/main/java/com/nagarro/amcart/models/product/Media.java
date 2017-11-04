package com.nagarro.amcart.models.product;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;

/**
 * Class to represent the Media
 */
@Entity
@Table(name = "medias")
public class Media extends AbstractEntity {

    private String url;

    /**
     * Constructor
     */
    public Media() {
        super();
    }

    /**
     * Parameterized Constructor
     */
    public Media(String url) {
        super();
        this.url = url;
    }

    /**
     * Gets the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets the url
     */
    public void setUrl(String value) {
        this.url = value;
    }
}

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

    private String code;

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

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

}

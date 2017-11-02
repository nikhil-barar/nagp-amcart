package com.nagarro.amcart.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;

@Entity
@Table(name = "countries")
public class Country extends AbstractEntity {

    /** The country name. */
    @Column(name = "country_name")
    private String countryName;

    /** The country code. */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * Instantiates a new country.
     */
    Country() {
    }

    /**
     * Gets the country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName
     *            the new country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Gets the country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country code.
     *
     * @param countryCode
     *            the new country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}

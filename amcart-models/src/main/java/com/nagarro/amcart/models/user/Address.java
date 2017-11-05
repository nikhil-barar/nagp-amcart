package com.nagarro.amcart.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;
import com.nagarro.amcart.models.enums.AddressType;

@Entity
@Table(name = "address")
public class Address extends AbstractEntity {

    /** The first name. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name. */
    @Column(name = "last_name")
    private String lastName;

    /** The mobile no. */
    @Column(name = "mobile_no")
    private String mobileNo;

    /** The address type. */
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @Column(name = "address_line_1")
    /** The line 1. */
    private String line1;

    /** The line 2. */
    @Column(name = "address_line_2")
    private String line2;

    /** The city. */
    @Column(name = "city")
    private String city;

    /** The state. */
    @Column(name = "state")
    private String state;

    /** The country. */
    @ManyToOne
    private Country country;

    /** The company. */
    @Column(name = "company")
    private String company;

    @ManyToOne
    private User user;

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName
     *            the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName
     *            the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the mobile no.
     *
     * @return the mobile no
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the mobile no.
     *
     * @param mobileNo
     *            the new mobile no
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * Gets the address type.
     *
     * @return the address type
     */
    public AddressType getAddressType() {
        return addressType;
    }

    /**
     * Sets the address type.
     *
     * @param addressType
     *            the new address type
     */
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    /**
     * Gets the line 1.
     *
     * @return the line 1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * Sets the line 1.
     *
     * @param line1
     *            the new line 1
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * Gets the line 2.
     *
     * @return the line 2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * Sets the line 2.
     *
     * @param line2
     *            the new line 2
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city
     *            the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state
     *            the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country
     *            the new country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Gets the company.
     *
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company.
     *
     * @param company
     *            the new company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

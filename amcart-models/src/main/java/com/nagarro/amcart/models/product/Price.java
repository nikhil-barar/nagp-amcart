package com.nagarro.amcart.models.product;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;
import com.nagarro.amcart.models.enums.Currency;

/**
 * Class to represent the Price
 */
@Entity
@Table(name = "prices")
public class Price extends AbstractEntity{

	private Currency currency;

	private Double priceValue;

	/**
	 * Constructor
	 */
	public Price() {
		super();
	}

	public Price(Currency currency, Double priceValue) {
		super();
		this.currency = currency;
		this.priceValue = priceValue;
	}

	/**
	 * Gets the currency
	 */
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * Sets the currency
	 */
	public void setCurrency(Currency value) {
		this.currency = value;
	}

	/**
	 * @return the priceValue
	 */
	public Double getPriceValue() {
		return priceValue;
	}

	/**
	 * @param priceValue
	 *            the priceValue to set
	 */
	public void setPriceValue(Double priceValue) {
		this.priceValue = priceValue;
	}

}
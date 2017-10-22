package com.nagarro.amcart.models.product;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;

@Entity
@Table(name = "stocks")
public class Stock extends AbstractEntity {

	private Long value;

	/**
	 * Constructor
	 */
	public Stock() {
		super();
	}

	public Stock(Long value) {
		super();
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Long value) {
		this.value = value;
	}

}
package com.nagarro.amcart.models.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stockId;

	private Long stockValue;

	/**
	 * Constructor
	 */
	public Stock() {
		super();
	}

	public Stock(Long stockValue) {
		super();
		this.stockValue = stockValue;
	}

	/**
	 * @return the stockId
	 */
	public int getStockId() {
		return stockId;
	}

	/**
	 * @return the stockValue
	 */
	public Long getStockValue() {
		return stockValue;
	}

	/**
	 * @param stockValue
	 *            the stockValue to set
	 */
	public void setStockValue(Long stockValue) {
		this.stockValue = stockValue;
	}

}

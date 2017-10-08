package com.nagarro.amcart.models.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Class to represent the Media
 */
@Entity
@Table(name="medias")
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;

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

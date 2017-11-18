package com.nagarro.amcart.models.product;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

import com.nagarro.amcart.models.AbstractEntity;
import com.nagarro.amcart.models.enums.ProductStatus;

/**
 * Class to represent the Product
 */
@Entity
@Table(name = "products")
@Document(indexName = "amcart_product")
public class Product extends AbstractEntity {

	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	private Collection<Media> media;

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "products_categories", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Collection<Category> categories;

	@OneToOne(cascade = CascadeType.ALL)
	private Stock stock;

	@OneToOne(cascade = CascadeType.ALL)
	private Price price;

	private ProductStatus status;

	/**
	 * Constructor
	 */
	public Product() {
		super();
	}

	/**
	 * Parameterized Constructor
	 */

	public Product(String name, String description, Collection<Media> media, Stock stock, Price price,
			ProductStatus status) {
		super();
		this.name = name;
		this.description = description;
		this.media = media;
		this.stock = stock;
		this.price = price;
		this.status = status;
	}

	/**
	 * Gets the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the media
	 */
	public Collection<Media> getMedia() {
		return this.media;
	}

	/**
	 * Sets the media
	 */
	public void setMedia(Collection<Media> value) {
		this.media = value;
	}

	/**
	 * Gets the stock
	 */
	public Stock getStock() {
		return this.stock;
	}

	/**
	 * Sets the stock
	 */
	public void setStock(Stock value) {
		this.stock = value;
	}

	/**
	 * Gets the price
	 */
	public Price getPrice() {
		return this.price;
	}

	/**
	 * Sets the price
	 */
	public void setPrice(Price value) {
		this.price = value;
	}

	/**
	 * Gets the status
	 */
	public ProductStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the status
	 */
	public void setStatus(ProductStatus value) {
		this.status = value;
	}

	/**
	 * @return the categories
	 */
	public Collection<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

}

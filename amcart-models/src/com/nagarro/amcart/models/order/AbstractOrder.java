package com.nagarro.amcart.models.order;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.nagarro.amcart.models.Address;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.models.enums.OrderStatus;
import com.nagarro.amcart.models.enums.PaymentMethod;
import com.nagarro.amcart.models.product.Price;


public class AbstractOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderID;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Price totalPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Price shippingPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address shippiAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address billiAddress;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Price basePrice;
	
	private PaymentMethod paymentMethod;
	private Date creationTime;
	private Date modifiedTime;
	private Date deliveryDate;
	private OrderStatus status;
	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return the totalPrice
	 */
	public Price getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Price totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the shippingPrice
	 */
	public Price getShippingPrice() {
		return shippingPrice;
	}
	/**
	 * @param shippingPrice the shippingPrice to set
	 */
	public void setShippingPrice(Price shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	/**
	 * @return the shippiAddress
	 */
	public Address getShippiAddress() {
		return shippiAddress;
	}
	/**
	 * @param shippiAddress the shippiAddress to set
	 */
	public void setShippiAddress(Address shippiAddress) {
		this.shippiAddress = shippiAddress;
	}
	/**
	 * @return the billiAddress
	 */
	public Address getBilliAddress() {
		return billiAddress;
	}
	/**
	 * @param billiAddress the billiAddress to set
	 */
	public void setBilliAddress(Address billiAddress) {
		this.billiAddress = billiAddress;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the basePrice
	 */
	public Price getBasePrice() {
		return basePrice;
	}
	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Price basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * @return the modifiedTime
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}
	/**
	 * @param modifiedTime the modifiedTime to set
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
}

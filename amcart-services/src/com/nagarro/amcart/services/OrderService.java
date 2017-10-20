package com.nagarro.amcart.services;

import java.util.List;

import com.nagarro.amcart.models.User;
import com.nagarro.amcart.models.order.Order;

public interface OrderService {
	
	public Order saveOrUpdate(Order order);

	public void delete(long id);

	public Order getOrderById(long id);
	
	public List<Order> getOrdersByUser(User user);


}

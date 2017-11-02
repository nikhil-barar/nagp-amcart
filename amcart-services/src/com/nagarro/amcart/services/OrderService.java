package com.nagarro.amcart.services;

import java.util.List;

import com.nagarro.amcart.models.order.Order;
import com.nagarro.amcart.models.user.User;

public interface OrderService {
	
	public Order saveOrUpdate(Order order);

	public void delete(long id);

	public Order getOrderById(long id);
	
	public List<Order> getOrdersByUser(User user);


}

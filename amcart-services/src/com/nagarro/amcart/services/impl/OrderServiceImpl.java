package com.nagarro.amcart.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.nagarro.amcart.daos.order.OrderDao;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.models.order.Order;
import com.nagarro.amcart.services.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order saveOrUpdate(Order order) {
		Order savedOrder = null;
		
		if (!ObjectUtils.isEmpty(order)) {
			savedOrder = orderDao.saveAndFlush(order);
			
			if (!ObjectUtils.isEmpty(savedOrder)) {
				LOGGER.warn("Unable to save order");
			}
		} else {
			LOGGER.warn("Unable to save order");
		}
		return savedOrder;
	}

	@Override
	public void delete(long id) {
		
		if (!ObjectUtils.isEmpty(id)) {
			orderDao.delete(id);
		} else {
			LOGGER.warn("Unable to delete order");
		}
		
	}

	@Override
	public Order getOrderById(long id) {
		
		Order order = null;
		
		if (!ObjectUtils.isEmpty(id)) {
			orderDao.getOne(id);
		} else {
			LOGGER.warn("Unable to get order");
		}
		
		return order;
	}

	@Override
	public List<Order> getOrdersByUser(User user) {
		List<Order> orders = null;
		if (!ObjectUtils.isEmpty(user)) {
			orders = orderDao.findByUser(user);

			if (!ObjectUtils.isEmpty(orders)) {
				LOGGER.warn("Unable to get order for user");
			}
		} else {
			LOGGER.warn("Unable to get order for user");
		}
		return orders;
	}

}

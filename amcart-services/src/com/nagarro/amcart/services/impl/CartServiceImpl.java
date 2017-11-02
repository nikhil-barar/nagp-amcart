package com.nagarro.amcart.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.nagarro.amcart.daos.UserDao;
import com.nagarro.amcart.daos.order.CartDao;
import com.nagarro.amcart.models.order.Cart;
import com.nagarro.amcart.models.user.User;
import com.nagarro.amcart.services.CartService;

public class CartServiceImpl implements CartService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private CartDao cartDao;

	@Override
	public Cart saveOrUpdate(Cart cart) {
		Cart savedCart = null;
		
		if(!ObjectUtils.isEmpty(cart)){
			savedCart = cartDao.saveAndFlush(cart);
		}else {
			LOGGER.warn("Can not save or update cart");
		}
		return savedCart;
	}

	@Override
	public void delete(long id) {
		
		if(!ObjectUtils.isEmpty(id)){
			cartDao.delete(id);
		}else{
			LOGGER.warn("Unable to delete cart");
		}
	}

	@Override
	public Cart getCartById(long id) {
		Cart cart = null;
		if(!ObjectUtils.isEmpty(id)){
			cart = cartDao.findOne(id);
			if(!ObjectUtils.isEmpty(cart)){
				LOGGER.warn("Can not gate cart with id ", id);
			}
		}else{
			LOGGER.warn("Can not gate cart");
		}
		return cart;
	}

	@Override
	public Cart getCartsOfUser(User user) {
		Cart cart = null;
		if(ObjectUtils.isEmpty(user)){
			cart = cartDao.findOneByUser(user);
		}else{
			LOGGER.warn("Can not find cart of user");
		}
		return cart;
	}

	@Override
	public boolean addtoCart(Cart cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFromCart(Cart cart) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.nagarro.amcart.services;

import com.nagarro.amcart.models.User;
import com.nagarro.amcart.models.order.Cart;

public interface CartService {

	public Cart saveOrUpdate(Cart order);

	public void delete(long id);

	public Cart getCartById(long id);
	
	public Cart getCartsOfUser(User user);
	
	public boolean addtoCart(Cart cart);
	
	public boolean deleteFromCart(Cart cart);
}

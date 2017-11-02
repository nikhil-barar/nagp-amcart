package com.nagarro.amcart.daos.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.order.Cart;
import com.nagarro.amcart.models.user.User;

@Repository
@Component("cartDao")
public interface CartDao extends JpaRepository<Cart, Long>{
	
	/**
	 * find order by users
	 * 
	 * @param user 
	 * @return {@link List}
	 */
	Cart findOneByUser(User user);

}

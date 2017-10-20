package com.nagarro.amcart.daos.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.User;
import com.nagarro.amcart.models.order.Order;

@Repository
@Component("orderDao")
public interface OrderDao  extends JpaRepository<Order, Long>{
	
	/**
	 * find order by users
	 * 
	 * @param user 
	 * @return {@link List}
	 */
	List<Order> findByUser(User user);

}

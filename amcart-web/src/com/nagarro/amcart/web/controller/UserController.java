package com.nagarro.amcart.web.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.amcart.facade.UserFacade;
import com.nagarro.amcart.models.User;


@RestController
@RequestMapping("api/v1/")
public class UserController {
	
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public List<User> list() {
 		return userFacade.list();
	}

	@RequestMapping(value = "users", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userFacade.saveOrUpdate(user);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public User get(@PathVariable Long id) {
		return userFacade.get(id);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable Long id, @RequestBody User user) {
		User existingUser = userFacade.get(id);
		BeanUtils.copyProperties(user, existingUser);
		return userFacade.saveOrUpdate(existingUser);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable Long id) {
		User existingUser = userFacade.get(id);
		userFacade.delete(id);
		return existingUser;
	}
	
}

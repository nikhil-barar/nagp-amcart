package com.nagarro.amcart.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.facade.UserFacade;
import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.platform.converters.Converter;
import com.nagarro.amcart.services.UserService;

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private UserService userService;
	
	@Autowired
	private Converter<User, UserData> userConverter;

	@Override
	public User saveOrUpdate(User user) {
		return userService.saveOrUpdate(user);
	}

	@Override
	public void delete(long id) {
		userService.delete(id);
	}

	@Override
	public User get(long id) {
		return userService.get(id);
	}

	@Override
	public List<UserData> list() {
		List<User> users = userService.list();
		List<UserData> convertedUsers = new ArrayList<UserData>();
		for (User user: users) {
			convertedUsers.add(userConverter.convert(user));
		}
		return convertedUsers;
	}

}

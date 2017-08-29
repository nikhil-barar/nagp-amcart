package com.nagarro.amcart.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.UserDao;
import com.nagarro.amcart.models.User;
import com.nagarro.amcart.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User saveOrUpdate(User user) {
		return userDao.saveAndFlush(user);
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User get(long id) {
		return userDao.findOne(id);
	}

	@Override
	public List<User> list() {
		return userDao.findAll();
	}

}

package com.nagarro.amcart.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.UserDao;
import com.nagarro.amcart.models.user.User;
import com.nagarro.amcart.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Override
	public User saveOrUpdate(User user) {
		LOGGER.info("Saving user with name : {} ", user.getName());
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

	@Override
	public User getUser(final String userName, final char[] password) {
		return null;
	}

}

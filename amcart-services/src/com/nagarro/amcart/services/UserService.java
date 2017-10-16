package com.nagarro.amcart.services;

import java.util.List;

import com.nagarro.amcart.models.User;

public interface UserService {

	public User saveOrUpdate(User user);

	public void delete(long id);

	public User get(long id);

	public List<User> list();

	User getUser(String userName, char[] password);

}

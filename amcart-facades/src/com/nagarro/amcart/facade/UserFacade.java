package com.nagarro.amcart.facade;

import java.util.List;

import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.User;

public interface UserFacade {

	public User saveOrUpdate(User user);

	public void delete(long id);

	public User get(long id);

	public List<UserData> list();
}

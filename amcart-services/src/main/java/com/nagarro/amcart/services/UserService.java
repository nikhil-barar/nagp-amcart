package com.nagarro.amcart.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nagarro.amcart.models.user.User;

public interface UserService extends UserDetailsService {

    public User saveOrUpdate(User user);

    public void delete(long id);

    public User get(long id);

    public List<User> list();

    User getUser(String userName, String password);

}

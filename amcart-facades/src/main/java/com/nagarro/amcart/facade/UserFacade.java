package com.nagarro.amcart.facade;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.user.User;

public interface UserFacade {

    public User saveOrUpdate(User user);

    public void delete(long id);

    public User get(long id);

    public List<UserData> list();
    
    public User register(String emailId, String password);

    public UserDetails loadUserByUsername(String username);
}

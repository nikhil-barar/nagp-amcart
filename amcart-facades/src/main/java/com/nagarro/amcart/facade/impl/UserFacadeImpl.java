package com.nagarro.amcart.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.facade.UserFacade;
import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.enums.RoleType;
import com.nagarro.amcart.models.user.Role;
import com.nagarro.amcart.models.user.User;
import com.nagarro.amcart.platform.converters.Converter;
import com.nagarro.amcart.services.RoleService;
import com.nagarro.amcart.services.UserService;

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
        List<UserData> convertedUsers = new ArrayList<>();
        for (User user : users) {
            convertedUsers.add(userConverter.convert(user));
        }
        return convertedUsers;
    }

    @Override
    public User register(String emailId, String password) {
        User user = new User();
        user.setEmailId(emailId);
        user.setPassword(password);
        Role roleByName = roleService.getRoleByName(RoleType.ROLE_CUSTOMER.getName());
        Set<Role> defaultRoles = Collections.singleton(roleByName);
        user.setRoles(defaultRoles);
        return userService.saveOrUpdate(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userService.loadUserByUsername(username);
    }

}

package com.nagarro.amcart.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.UserDao;
import com.nagarro.amcart.models.user.Role;
import com.nagarro.amcart.models.user.User;
import com.nagarro.amcart.platform.util.AmcartObjectUtils;
import com.nagarro.amcart.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveOrUpdate(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public User getUser(final String userName, final String password) {
        return null;
    }

    // Here user name is email id.
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByEmailId(username);
        if (AmcartObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("No user present with email: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
                    getAuthorities(user.getRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = Collections.emptyList();
        if (!AmcartObjectUtils.isEmpty(roles)) {
            authorities = new ArrayList<>(roles.size());
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        return authorities;
    }

}

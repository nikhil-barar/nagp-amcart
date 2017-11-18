package com.nagarro.amcart.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.amcart.daos.RoleDao;
import com.nagarro.amcart.models.user.Role;
import com.nagarro.amcart.services.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }

}

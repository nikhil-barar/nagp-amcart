package com.nagarro.amcart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.user.Role;

/**
 * Defines DAO operations for the Role model.
 * 
 *
 */
@Repository
@Component("roleDao")
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
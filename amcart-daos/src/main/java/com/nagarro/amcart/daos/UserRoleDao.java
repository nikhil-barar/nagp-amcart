package com.nagarro.amcart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.user.Role;

@Repository
@Component("userRoleDao")
public interface UserRoleDao extends JpaRepository<Role, Long> {

}

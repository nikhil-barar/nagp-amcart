package com.nagarro.amcart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.User;

/**
 * Defines DAO operations for the User model.
 * 
 *
 */
@Repository
@Component("userDao")
public interface UserDao extends JpaRepository<User, Long> {

}
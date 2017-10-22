package com.nagarro.amcart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.product.Category;


/**
 * Defines DAO operations for the Category.
 * 
 *
 */
@Repository
@Component("categoryDao")
public interface CategoryDao extends JpaRepository<Category, Integer> {

	Category findByCode(String code);
}

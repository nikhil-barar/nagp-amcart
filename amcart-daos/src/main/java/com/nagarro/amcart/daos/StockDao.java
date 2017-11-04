package com.nagarro.amcart.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nagarro.amcart.models.product.Stock;

/**
 * Defines DAO operations for the Stock.
 * 
 *
 */
@Repository
@Component("stockDao")
public interface StockDao extends JpaRepository<Stock, Integer> {

}

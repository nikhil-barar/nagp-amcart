package com.nagarro.amcart.services;

import com.nagarro.amcart.models.product.Stock;

public interface StockService {

	boolean updateStock(Integer productId,Long value);
	
	boolean updateStock(Stock stock);

	boolean reserveStock(Integer productId, Long reservedValue);

}

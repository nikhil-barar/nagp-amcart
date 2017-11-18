package com.nagarro.amcart.services;

import com.nagarro.amcart.models.product.Stock;

public interface StockService {

	boolean updateStock(Long productId,Long value);
	
	boolean updateStock(Stock stock);

	boolean reserveStock(Long productId, Long reservedValue);

}

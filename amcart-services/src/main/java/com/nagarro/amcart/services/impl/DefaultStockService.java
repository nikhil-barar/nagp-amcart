package com.nagarro.amcart.services.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.amcart.daos.ProductDao;
import com.nagarro.amcart.daos.StockDao;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.models.product.Stock;
import com.nagarro.amcart.services.StockService;

public class DefaultStockService implements StockService {

	@Autowired
	private StockDao stockDao;

	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional
	public boolean updateStock(Long productId, Long value) {
		boolean isStockUpdated = false;
		Product product = getProductDao().findOne(productId);
		if (product != null) {
			Stock stock = product.getStock();
			stock.setValue(value);
			isStockUpdated = updateStock(stock);
		}
		return isStockUpdated;
	}

	@Override
	public boolean updateStock(Stock stock) {
		Stock updatedStock = getStockDao().saveAndFlush(stock);
		return !Objects.isNull(updatedStock);
	}

	@Override
	public boolean reserveStock(Long productId, Long reservedValue) {
		boolean isStockReserved = false;
		Product product = getProductDao().findOne(productId);
		if (product != null) {
			Stock stock = product.getStock();
			Long newValue = stock.getValue() - reservedValue;
			stock.setValue(newValue);
			isStockReserved = updateStock(stock);
		}
		return isStockReserved;
	}

	/**
	 * @return the stockDao
	 */
	public StockDao getStockDao() {
		return stockDao;
	}

	/**
	 * @return the productDao
	 */
	public ProductDao getProductDao() {
		return productDao;
	}

}

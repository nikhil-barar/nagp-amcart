package com.nagarro.amcart.services.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.nagarro.amcart.daos.ProductDao;
import com.nagarro.amcart.daos.exceptions.DAOException;
import com.nagarro.amcart.models.enums.ProductStatus;
import com.nagarro.amcart.models.product.Category;
import com.nagarro.amcart.models.product.Product;
import com.nagarro.amcart.services.ProductService;
import com.nagarro.amcart.services.exceptions.ModelNotFoundException;

import junit.framework.Assert;

public class DefaultProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductDao productDao;

	@Before
	public void setUp() {
		productService = new DefaultProductService();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testFindByIdShouldReturnProduct() throws ModelNotFoundException {
		Product product = mock(Product.class);
		when(productDao.findOne(1L)).thenReturn(product);
		Product actualProduct = productService.findById(1L);

		Assert.assertEquals(product, actualProduct);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByIdShouldThrowIllegalArgumentException() throws ModelNotFoundException {
		productService.findById(null);
	}

	@Test
	public void testFindAllProductsShouldReturnProducts() throws ModelNotFoundException {
		when(productDao.findAll()).thenReturn(new ArrayList<Product>());
		List<Product> actualResult = productService.findAllProducts();

		Assert.assertNotNull(actualResult);
	}

	@Test
	public void testFindByNameShouldReturnProducts() throws ModelNotFoundException, DAOException {
		List<Product> mockedList = Mockito.anyListOf(Product.class);
		when(productDao.findByName("TEST")).thenReturn(mockedList);
		List<Product> actualResult = productService.findByName("TEST");

		Assert.assertEquals(mockedList, actualResult);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByNameShouldThrowIllegalArgumentException() throws ModelNotFoundException, DAOException {
		productService.findByName(null);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ModelNotFoundException.class)
	public void testFindByNameShouldThrowModelNotFoundException() throws ModelNotFoundException, DAOException {
		when(productDao.findByName("TEST")).thenThrow(DAOException.class);
		productService.findByName("TEST");

	}
	
	@Test
	public void testFindByStatusShouldReturnProducts() throws ModelNotFoundException, DAOException {
		List<Product> mockedList = Mockito.anyListOf(Product.class);
		when(productDao.findByStatus(ProductStatus.APPROVED)).thenReturn(mockedList);
		List<Product> actualResult = productService.findByStatus(ProductStatus.APPROVED);

		Assert.assertEquals(mockedList, actualResult);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByStatusShouldThrowIllegalArgumentException() throws ModelNotFoundException, DAOException {
		productService.findByStatus(null);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ModelNotFoundException.class)
	public void testFindByStatusShouldThrowModelNotFoundException() throws ModelNotFoundException, DAOException {
		when(productDao.findByStatus(ProductStatus.APPROVED)).thenThrow(DAOException.class);
		productService.findByStatus(ProductStatus.APPROVED);

	}
	
	@Test
	public void testFindByCategoriesAndStatusShouldReturnProducts() throws ModelNotFoundException, DAOException {
		List<Product> mockedList =  Mockito.anyListOf(Product.class);
		when(productDao.findByCategoriesAndStatus(Mockito.anySetOf(Category.class), ProductStatus.APPROVED)).thenReturn(mockedList);
		List<Product> actualResult = productService.findByStatus(ProductStatus.APPROVED);

		Assert.assertNotNull(actualResult);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByCategoriesAndStatusShouldThrowIllegalArgumentException() throws ModelNotFoundException, DAOException {
		productService.findByCategoriesAndStatus(null, null);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ModelNotFoundException.class)
	public void testFindByCategoriesAndStatusShouldThrowModelNotFoundException() throws ModelNotFoundException, DAOException {
		when(productDao.findByCategoriesAndStatus(new HashSet<>(),ProductStatus.APPROVED)).thenThrow(DAOException.class);
		productService.findByCategoriesAndStatus(new HashSet<>(),ProductStatus.APPROVED);
	}

}

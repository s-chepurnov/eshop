package org.shop.site;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import org.shop.dao.ProductDao;
import org.shop.dao.TransactionManager;
import org.shop.dao.TransactionManagerMock;
import org.shop.entity.Product;

public class ProductControllerTest {
	private ProductController controller;
	private ProductDao dao;
	private TransactionManager trManager;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher dispatcher;
	private Product product;
	
	@Before
	public void setUpRequest(){
		request = mock(HttpServletRequest.class);
		dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	}
	@Before
	public void setUpResponse(){
		response = mock(HttpServletResponse.class);
	}
	@Before
	public void setUpController(){
		controller = new ProductController();
		dao = mock(ProductDao.class);
		controller.productDao = dao;
		trManager = new TransactionManagerMock();
		controller.trManager = trManager;
	}

	@Before 
	public void setUpProduct(){
		product = mock(Product.class);
	}

	@Test
	public void test() throws Exception{
		//arrange
		when(dao.selectById(anyInt())).thenReturn(product);
		when(request.getParameter(anyString())).thenReturn("123");
		
		//act	
		controller.doGet(request, response);
		
		//assert
		verify(dao).selectById(anyInt());
		
		verify(request).getParameter(anyString());
		verify(request).setAttribute(anyString(),anyObject());
		verify(request).getRequestDispatcher(anyString());
		verify(dispatcher).forward(request, response);
		
		verifyNoMoreInteractions(dao);
		verifyZeroInteractions(response);
		verifyNoMoreInteractions(request);
	}
}
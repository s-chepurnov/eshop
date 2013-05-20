package org.shop.inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyObject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.junit.Before;
import org.junit.Test;
import org.shop.dao.TransactionManager;
import org.shop.dao.TransactionManagerMock;

public class DependencyInjectionServletTest {

	DependencyInjectionServlet dis, spy;;
	List<Field> listFields;
	ApplicationContext appCtxParser;
	Field field;
	Field[] fields;
	TransactionManager trManager;
	Inject annotation;
	Class clazz;
	HttpServlet servlet;
	ServletConfig config;
	ServletContext ctx;

	@Before 
	public void setUp() throws ClassNotFoundException{
		dis = new DependencyInjectionServlet();
		spy = spy(dis);
		clazz = Class.forName("org.shop.inject.DependencyInjectionServlet");
		fields = clazz.getDeclaredFields();

		listFields = new ArrayList<Field>();
		listFields.addAll(Arrays.asList(fields));
		field = fields[0];

		trManager = new TransactionManagerMock();

		appCtxParser = mock(ApplicationContext.class);
		annotation = mock(Inject.class);

		//servlet= mock(HttpServlet.class);
		config = mock(ServletConfig.class);
		ctx = mock(ServletContext.class);
		//servlet = mock(Dependen.class);

	}
	@Test
	public void getAllFieldsTest(){
		assertThat(dis.getAllFields0(ClassChildForDepInjServTest.class, new ArrayList<Field>()), hasSize(6));
	}

	@Test
	public void setFieldsWithBeanTestNoOneAnnotation() throws Exception{
		//arrange
		when(appCtxParser.getBean(anyString())).thenReturn(trManager);
		//act
		dis.setFieldsWithBean(listFields, appCtxParser);
		//assert
		//verify(appCtxParser).getBean(anyString());
		verifyZeroInteractions(appCtxParser);
	}

	@Test(expected = ServletException.class)
	public void readInitParameterTestAnyString() throws ServletException{
		//arrange
		when(spy.getServletConfig()).thenReturn(config);
		when(spy.getServletContext()).thenReturn(ctx);
		when(spy.getInitParameter(anyString())).thenReturn(anyString());
		
		//act
		spy.init(config); // this throws ServletException();
		spy.readInitParameter(anyString());
		//assert
		verify(config).getInitParameter(anyString());		
	}
}
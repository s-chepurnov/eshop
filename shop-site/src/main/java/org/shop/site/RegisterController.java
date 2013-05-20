package org.shop.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.shop.inject.DependencyInjectionServlet;
import org.shop.utils.ClassNameUtil;

public class RegisterController extends DependencyInjectionServlet{

	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		//Customer customer = new Customer(String firstName, String lastName, String email, String password);
		//insert customer into shop.Customers
	}
}
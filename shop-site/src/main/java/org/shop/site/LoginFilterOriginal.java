package org.shop.site;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.shop.entity.Customer;
import org.shop.utils.ClassNameUtil;

public class LoginFilterOriginal implements Filter{

	public static final String ATTR_NAME_CUSTOMER = "customer";
	public static final String PARAM_NAME_REDIRECT_TO = "from";
	public static final String BASE_PAGE = "index.jsp";
	
	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		//NOP
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//chain.doFilter(request,response);
		
		HttpSession session = httpRequest.getSession(true);
		
		String firstName = "first";
		String lastName = "last";
		String email = "mail";
		String password = "pass";
		Customer customer = new Customer(firstName, lastName, email, password);

		session.setAttribute(ATTR_NAME_CUSTOMER,customer);
		logger.info("login - ok : {login = " + customer.getEmail() + ", name =" + customer.getFirstName() + " " +customer.getLastName() + " }");
		
		String locationRedirectTo = httpRequest.getParameter(LoginFilterOriginal.PARAM_NAME_REDIRECT_TO);
		if(locationRedirectTo != null){
			locationRedirectTo = locationRedirectTo.replace(";", "?id=");
			logger.debug("send redirect to "+ locationRedirectTo);
			httpResponse.sendRedirect(locationRedirectTo);
		}else{
			logger.warn("send redirect to BASE PAGE: " + BASE_PAGE);
			httpResponse.sendRedirect(BASE_PAGE);
		}
	}
	
	@Override 
	public void destroy(){
		//NOP
	}
}
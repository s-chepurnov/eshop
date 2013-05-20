package org.shop.site;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.shop.utils.ClassNameUtil;

public class LoginStrFilter implements Filter{

	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";
	public static final String CAUSE = "your login and password are invalid, please try again";
	public static final String ATTR_CAUSE = "cause";
	
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String ATTR_CUSTOMER = "customer";

	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		logger.trace("LoginStrFilter start implementing doFilter()");
		
		String login = request.getParameter(PARAM_EMAIL);
		String pass = request.getParameter(PARAM_PASSWORD);
		
		if(login!= null && !login.equals("") && pass!= null && !pass.equals("")){
			logger.debug("strings login and password are valid, call next filter");
			logger.trace("preprocess chain.doFilter(request, response);");
			chain.doFilter(request, response);
			logger.trace("postprocess chain.doFilter(request, response);");
		}else{
			logger.debug("strings login"+"{"+login+"}"+" and password"+"{"+pass+"}"+" are invalid, call login page again" + PAGE_LOGIN);
			try {
				request.setAttribute(ATTR_CAUSE, CAUSE);
				request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
				logger.debug("redirect to PAGE_LOGIN: request.getRequestDispatcher(" + PAGE_LOGIN + ").forward(request, response) ");
			} catch (IOException e) {
				logger.warn("exception occur when call: request.getRequestDispatcher() ", e);
			}
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		this.filterConfig = filterConfig;
	}

	@Override 
	public void destroy(){
		filterConfig = null;
	}
}
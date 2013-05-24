package org.shop.site;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.shop.dao.CustomerDao;
import org.shop.dao.CustomerDaoJdbcImpl;
import org.shop.dao.TransactionManager;
import org.shop.dao.TransactionManagerImpl;
import org.shop.entity.Customer;
import org.shop.utils.ClassNameUtil;


public class LoginDBFilter implements Filter{

	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";
	
	public static final String ATTR_CUSTOMER = "customer";
	public static final String ATTR_CAUSE = "cause";
	
	public static final String PAGE_REGISTER = "register.jsp";
	public static final String PAGE_LOGIN = "login.jsp";
	
	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	//@Inject("customerDao")
	private CustomerDao customerDao = new CustomerDaoJdbcImpl();
	//@Inject("trManager")
	private TransactionManager trManager = new TransactionManagerImpl();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		//NOP
		logger.trace("LoginDBFilter start implementing init()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		logger.trace("LoginDBFilter start implementing doFilter()");
		
		String login = request.getParameter(PARAM_EMAIL);
		String pass = request.getParameter(PARAM_PASSWORD);
		
		try{
			logger.trace("login='"+login+"'; password='"+pass+"'");
			Customer customer = selectLogin(login);
			logger.debug("customer was selected='"+customer+"';");
			//this login exists in DB or not
			if(customer != null){
				//check an entered password with the password from DB 
				if( pass.equals(customer.getPassword()) ){
					request.setAttribute(ATTR_CUSTOMER, customer);
					logger.debug("setAttribute:" + ATTR_CUSTOMER);
					logger.trace("preprocess chain.doFilter(request, response);");
					chain.doFilter(request, response);
					logger.trace("postprocess chain.doFilter(request, response);");
				}else{
					logger.warn("the wrong login and password of customer:" + "{" + login + ", " + pass + "}");
					request.setAttribute(ATTR_CAUSE, "the wrong login and password");
					request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
					logger.debug("getRequestDispatcher(" + PAGE_LOGIN + ").forward(req, resp)");
				}
			}else{
				logger.warn("this customer doesn't exist:" + "{" + login + ", " + pass + "}");
				request.setAttribute(ATTR_CAUSE, "this customer doesn't exist, please register");
				request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
				logger.debug("getRequestDispatcher(" + PAGE_REGISTER + ").forward(req, resp)");
				
			}
		}catch(Exception e){
			logger.warn("the login operation was interrupted by exception: ", e);
		}
	}

	@Override 
	public void destroy(){
		//NOP
	}
	private Customer selectLogin(final String login) throws Exception{
		//this function execute SQL statement in the transaction
		logger.debug("trManager='"+trManager+"';");
		return trManager.doInTransaction(new Callable<Customer>() {
			@Override
			public Customer call() throws Exception{
				logger.debug("customerDao='"+customerDao+"'");
				return customerDao.selectByEmail(login);
			}
		});
	}
}
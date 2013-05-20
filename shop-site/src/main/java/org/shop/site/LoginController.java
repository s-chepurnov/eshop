package org.shop.site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.shop.entity.Customer;
import org.shop.utils.ClassNameUtil;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet{
	public static final String ATTR_CUSTOMER = "customer";
	public static final String BASE_PAGE = "index.jsp";

	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.trace("LoginController doGet() begin");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.trace("LoginController doPost() begin");
		Customer customer= null;
		customer = (Customer) request.getAttribute(ATTR_CUSTOMER);
		if(customer!=null){
			HttpSession session = request.getSession(true);
			session.setAttribute(ATTR_CUSTOMER, customer);
			logger.info("login - ok : {login = " + customer.getEmail()
					+ ", name =" + customer.getFirstName() + " "
					+ customer.getLastName() + " }");
			try{
				Cookie cookie = new Cookie("cart", "chees");
				response.addCookie(cookie);
				logger.debug("add the cookie:" + cookie.getName() + " - " + cookie.getValue());
				logger.debug("try to redirect to: " + BASE_PAGE);
				response.sendRedirect(BASE_PAGE);
				logger.debug("response.sendRedirect(" + BASE_PAGE + ")");

			}catch(Exception e){
				logger.warn("exception occurred when: getRequestDispatcher(" + BASE_PAGE + ")forward(req, resp)");
			}
		}
	}
}
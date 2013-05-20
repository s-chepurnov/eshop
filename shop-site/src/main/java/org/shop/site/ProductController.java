package org.shop.site;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.shop.dao.ProductDao;
import org.shop.dao.TransactionManager;
import org.shop.entity.Product;
import org.shop.inject.DependencyInjectionServlet;
import org.shop.inject.Inject;
import org.shop.utils.ClassNameUtil;

public class ProductController extends DependencyInjectionServlet{

	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	public static final String PARAM_ID = "id";
	public static final String ATTRIB_PRODUCT= "product";
	public static final String PAGE_OK= "product.jsp";
	public static final String PAGE_ERR= "index.jsp";
	
	@Inject("productDao")
	protected ProductDao productDao; //= new ProductDaoJdbcImpl();
	@Inject("trManager")
	protected TransactionManager trManager;// = new TransactionManagerImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		logger.trace("productDao= '" + productDao + "'; trManager= '" + trManager+"'");
		String idStr = request.getParameter(PARAM_ID);
		if(idStr!= null && !idStr.equals("")){
			try{
				final Integer id = Integer.valueOf(idStr);
				Product product = selectById(id);
				if(product != null){
					request.setAttribute(ATTRIB_PRODUCT, product);
					logger.debug("setAttribute:" + ATTRIB_PRODUCT);
					//OK
					request.getRequestDispatcher(PAGE_OK).forward(request, response);
					logger.debug("getRequestDispatcher(" + PAGE_OK + ")forward(req, resp)");
					return;
				}
			}catch(Exception e){
				//e.g. DB doesn't work
				logger.warn("exception: ", e);
			}
		}

		//ERROR
		try {
			response.sendRedirect(PAGE_ERR);
			logger.warn("redirect to error page: sendRedirect(" + PAGE_ERR + ") ");
		} catch (IOException e) {
			logger.warn("exception(redirect to error page): ", e);
		}
	}
	private Product selectById(final int id) throws Exception{
		return trManager.doInTransaction(new Callable<Product>() {
			@Override
			public Product call() throws Exception{
				return productDao.selectById(id);
			}
		});
	}
}
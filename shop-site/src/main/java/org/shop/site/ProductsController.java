package org.shop.site;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.shop.dao.DBSystemException;
import org.shop.dao.ProductDao;
import org.shop.dao.TransactionManager;
import org.shop.entity.Product;
import org.shop.inject.DependencyInjectionServlet;
import org.shop.inject.Inject;
import org.shop.utils.ClassNameUtil;

public class ProductsController extends DependencyInjectionServlet{
	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	@Inject("productDao")
	private ProductDao productDao; // = new ProductDaoJdbcImpl();
	@Inject("trManager")
	private TransactionManager trManager; // = new TransactionManagerImpl();
	
	public static final String PARAM_FROM = "from";
	public static final String PARAM_COUNT = "count";
	public static final String ATTRIB_PRODUCTS= "products";
	public static final String PAGE_OK= "products.jsp";
	public static final String PAGE_ERR= "index.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String fromStr = request.getParameter(PARAM_FROM);
		String countStr = request.getParameter(PARAM_COUNT);
		if(fromStr!= null && !fromStr.equals("") && countStr!= null && !countStr.equals("")){
			
			try{
				final Integer from = Integer.valueOf(fromStr);
				final Integer count = Integer.valueOf(countStr);
				
				List<Product> products = selectPagination(from,count); 
			
				if(products != null){
					
					request.setAttribute(ATTRIB_PRODUCTS, products);
					logger.debug("setAttribute:" + ATTRIB_PRODUCTS);
					//OK
					request.getRequestDispatcher(PAGE_OK).forward(request, response);
					logger.debug("getRequestDispatcher(" + PAGE_OK + ")forward(req, resp)");
					return;
				}
			}catch(Exception e){
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
	private List<Product> selectPagination(final int from, final int count) throws Exception{
			
		List<Product> listProducts = trManager.doInTransaction(new Callable<List<Product>>(){
					public List<Product> call() throws Exception{
						List<Product> result = null;
						try{
							result = productDao.selectPagination(from, count);
						}catch(DBSystemException e){
							logger.warn("DBSystemException occur when doInTransaction(): ",e);
						}
						return result;
					}
				});
		return listProducts;
	}
}
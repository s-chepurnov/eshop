package org.shop.site;

import java.io.IOException;
import java.util.ArrayList;
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

	public static final String PARAM_PAGE = "p";
	public static final String PARAM_FROM = "from";
	public static final String PARAM_COUNT = "count";
	public static final String ATTRIB_PRODUCTS= "products";
	public static final String ATTRIB_TOTAL_PAGES= "totalPages";
	public static final String ATTRIB_LINKS= "links";
	
	public static final String PAGE_OK= "products.jsp";
	public static final String PAGE_ERR= "index.jsp";
	public static final int UNITS_PER_PAGE = 12;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String pageStr = request.getParameter(PARAM_PAGE);
		
		
		if(pageStr!= null && !pageStr.equals("")){

			try{
				final Integer pageNum = Integer.valueOf(pageStr);

				List<Product> products = selectPagination(pageNum); 

				if(products != null){
					//setAttribute 'products' from DB
					request.setAttribute(ATTRIB_PRODUCTS, products);
					logger.debug("setAttribute:" + ATTRIB_PRODUCTS);
					//number of pages for pagination
					int totalPages = selectCount();
					logger.debug("totalPages: " + totalPages);
					request.setAttribute(ATTRIB_TOTAL_PAGES, totalPages);
					logger.debug("setAttribute:" + ATTRIB_TOTAL_PAGES);
					//create links to each page
					List<Integer> links = createLinks(totalPages);
					request.setAttribute(ATTRIB_LINKS, links);
					logger.debug("setAttribute:" + ATTRIB_LINKS);

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
	private List<Product> selectPagination(final int page) throws Exception{

		final int from = (page-1)*UNITS_PER_PAGE;
		final int count = UNITS_PER_PAGE;

		List<Product> listProducts = trManager.doInTransaction(new Callable<List<Product>>(){
			public List<Product> call() throws Exception{
				List<Product> result = null;
				try{
					result = productDao.selectPagination(from, count);
					
				}catch(DBSystemException e){
					logger.warn("DBSystemException occur when doInTransaction(): ", e);
				}
				logger.debug("select '" + result.size() + "'" + "units into '"+page+"' page");
				return result;
			}
		});
		return listProducts;
	}
	private int selectCount() throws Exception{
		int count = trManager.doInTransaction(new Callable<Integer>(){
			public Integer call() throws Exception{
				Integer result = null;
				try{
					result = productDao.selectCount();
					logger.debug("units of products in DB: '" + result + "'");
				}catch(DBSystemException e){
					logger.warn("DBSystemException occur when doInTransaction(): ", e);
				}
				return result;
			}
		});
		int numberOfPages = (int) Math.ceil(count/(double)UNITS_PER_PAGE);
		logger.debug("total number of pages: '" + numberOfPages + "'");
		return numberOfPages;
	}
	private List<Integer> createLinks(int totalPages) throws Exception{

		List<Integer> links = new ArrayList<Integer>();
		for(int i=1; i<=totalPages; i++){
			links.add(i);
		}
		return links;
	}
}
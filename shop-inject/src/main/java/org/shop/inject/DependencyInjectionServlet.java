package org.shop.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.shop.utils.ClassNameUtil;

public class DependencyInjectionServlet extends HttpServlet{

	private static final String APP_CTX_PATH = "appCtxPath";
	private static final String APP_CTX_CLASS = "appCtxClass";

	private static Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	public void init() throws ServletException{
		//this method does all job of DI
		logger.trace("DepInjServlet start implementing init()");
		
		//1.read init parameters
		String appCtxPath = readInitParameter(APP_CTX_PATH); //it's the path to xml file with beans(classes of dao)
		String appCtxClass = readInitParameter(APP_CTX_CLASS); //it's class which will extract bean from xml (DOM, SAX, Mock, ...)

		try{
			//2.load the implementation class of ApplicationContext interface from init parameters
			ApplicationContext appCtxParser = loadAppCtxParser(appCtxClass, appCtxPath);

			//3.inject from AppContext
			List<Field> listFields = getAllFields(this.getClass());
			logger.debug("getFields: " + (listFields.toString()));
			//4.set fields with bean
			setFieldsWithBean(listFields, appCtxParser);
			
		}catch(Exception e){
			logger.warn("exception during DInjectionServlet",e);
		}
	}
	//read parameters from init section
	protected String readInitParameter(String parameter) throws ServletException{
		
		String appCtx = this.getInitParameter(parameter);
		logger.debug("load" +parameter+ "parameter: " + appCtx);

		if(appCtx == null){
			logger.error("I need init param" + parameter);
			throw new ServletException(parameter + " init param = " + appCtx);
		}
		return appCtx;
	}
	//load the implementation class of ApplicationContext interface from init parameters 
	protected ApplicationContext loadAppCtxParser(String nameOfParserClass, String pathToFileWithBean) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		ApplicationContext applicationContextParser = (ApplicationContext) Class.forName(nameOfParserClass).newInstance();
		logger.debug("create instance of " + nameOfParserClass);
		applicationContextParser.init(pathToFileWithBean);
		logger.debug("init() instance of " + pathToFileWithBean);
		return applicationContextParser;
	}
	
	//find fields with annotation and set them with bean from xml
	protected void setFieldsWithBean(List<Field> listFields, ApplicationContext appCtxParser) throws Exception{
		for(Field field : listFields){
			field.setAccessible(true);
			//find fields with annotation
			Inject annotation = field.getAnnotation(Inject.class);
			if(annotation != null){
				logger.debug("I find method marked by @Inject" + field);
				//get field's name //e.g. 'productDao'
				String beanName = annotation.value();
				logger.debug("I must instantiate and inject '" + beanName + "'");

				//get and set Bean
				Object bean = appCtxParser.getBean(beanName); //retrieve bean from xml and instantiate it
				logger.debug("Instantiation succeed: '" +beanName+"'" );
				if(bean==null){
					throw new Exception("There isn't bean with name: '" + beanName +"'");
				}
				//set bean by reflection
				field.set(this,bean);
			}
		}
	} 

	// Recursively calls superclass and get all fields
	private List<Field> getAllFields(Class<?> clazz){
		//call method that do all job
		return getAllFields0(clazz, new ArrayList<Field>());
	}

	protected List<Field> getAllFields0(Class<?> clazz, List<Field> list){
		//condition to finish recursive
		if(clazz == null){
			return list;
		}
		//getDeclaredFields from this class
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			list.add(field);
		}
		//recursive
		return getAllFields0(clazz.getSuperclass(), list);
	}
}
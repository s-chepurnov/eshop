package org.shop.inject;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ApplicationContextDomImpl implements ApplicationContext{
	//private String xmlFile;
	private Document document;
	
	@Override
	public void init(String xmlFile){
		//this.xmlFile = xmlFile;
		try{
		//init IS because our xml file exist in war
		InputStream in  = getClass().getClassLoader().getResourceAsStream(xmlFile);
		//init DOM
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder dbuilder = factory.newDocumentBuilder();
		this.document = dbuilder.parse(in);
		this.document.getDocumentElement().normalize();

		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public Object getBean(String name){
		return getBean(name, Object.class);
	}
	@Override
	public <T> T getBean(String beanName, Class<T> clazz){
		try{
			NodeList beanNodes = this.document.getElementsByTagName("bean");
			
			for(int beanIndex=0; beanIndex<beanNodes.getLength(); beanIndex++){
				Node node = beanNodes.item(beanIndex);
				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element) node;
					String currentBeanName = element.getAttribute("name");
					String beanClass = element.getAttribute("class");
					if(beanName.equals(currentBeanName)){
						return (T) newInstance(beanClass);
					}
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		throw new RuntimeException("no such bean declaration: '" + beanName+"'");
	}
	private Object newInstance(String beanClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> beanClass = Class.forName(beanClassName);
		return beanClass.newInstance();
	}
}
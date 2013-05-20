package org.shop.utils;

public final class ClassNameUtil {
	private ClassNameUtil(){}
	
	public static String getCurrentClassName(){
		try{
			throw new RuntimeException();
		}catch(RuntimeException e){
			return e.getStackTrace()[1].getClassName();
		}
	}
}

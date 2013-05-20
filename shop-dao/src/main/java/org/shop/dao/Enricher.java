package org.shop.dao;
public interface Enricher<T> {
	public static final Enricher NOTHING = new Enricher(){ 
		@Override
		public void enrich(Object record){
			//NOP
		}
	};
	public void enrich(T element) throws DBSystemException;
}

package org.shop.inject;

class SuperParent{
	private int privateSP;
	public int publicSP;
}
class Parent extends SuperParent{
	private int privateP;
	public int publicP;
}
public class ClassChildForDepInjServTest extends Parent{
	@Inject("trManager")
	private int privateCh;
	public int publicCh;
}
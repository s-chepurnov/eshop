<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.shop.entity.Product"%>
<%@page import="org.shop.entity.Supplier"%>
<html>
<body>
<%@ include file="header.jspf"%>

	<br> productName
	<br> ${product.productName}
	<br> product.productId:
	<br> ${product.productId};
	<br> product.categoryId:
	<br> ${product.categoryId};
	<br> ${product.supplierId};
	<br> ${product.SKU};
	<br> ${product.productName};
	<br> ${product.productDescription};
	<br> ${product.unitPrice};
	<br> ${product.uninWeight};
	<br> ${product.shippingWeight};
	<br> ${product.unitsInStock};
	<br> ${product.picture};
	<br> ${product.flavour};
	<br> ${product.UPC};
	<br> ${product.expirationDate};
	<br> product.category:
	<br> ${product.category.categoryName};
	<br> product.supplier:
	<br> ${product.supplier.companyName};

<!-- absolutely the same, but with a scriplet
	<%//get this: product.supplier.companyName
			Product prd = (Product) request.getAttribute("product");
			//out.print(prd);
			Supplier spl = prd.getSupplier();
			//out.print(spl);
			String name = spl.getCompanyName();
			out.print(name);%>
-->
	
</body>
</html>
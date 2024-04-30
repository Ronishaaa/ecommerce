<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/addProduct.css" />
</head>
<body>

<div class="container">
	<h1>Add New Product</h1>
	<c:set var="student" value="${product}"></c:set>
	<form action="${pageContext.request.contextPath}/updateProduct" method="post">
	   
	   <div class="input-field">
	    <label for="productName">Product Name:</label>
	    <input type="text" id="productName" name="productName" value="${product.product_name}" required>
	   </div>
	
	
	<div class="input-field">
	    <label for="productDescription">Product Description:</label>
	    <textarea id="productDescription" name="productDescription" required>{product.product_description}</textarea>
	</div>
	
	
	<div class="input-field">
	    <label for="unitPrice">Unit Price:</label>
	    <input type="number" id="unitPrice" name="unitPrice" value="unit_price" required>
	</div>
	
	<div class="input-field">
	
	    <label for="stock">Stock:</label>
	    <input type="number" id="stock" name="stock" value="stock" required>
	</div>
	
	    <button type="submit" value="Submit">Submit</button>
	</form>
</div>
</body>
</html>
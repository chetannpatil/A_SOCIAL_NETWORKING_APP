<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: green;" align="center">
${ successMessage}
</h1>
<h2>Note :</h2>
<h5>${noteMessage }</h5>
<h1 style="color: red;">Congratulation ${newUserName } ....</h1>


<form action="TestRegConfirmation">
<input type="submit" value="${confirmRegLink }">
<input readonly="true" value="${userId }" name="userId">
</form>
<h2><a href="toHome">To Home Page</a></h2>
</body>
</html>
<%@ include file="Footer.jsp" %>
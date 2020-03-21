<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<h3 align="center" style="color: maroon;">${dpStatusMessage }</h3>
<body>
<table align="center" border="0">
<tr>

<td>
<s:form action="changeDp" >
<input name="dp" type="file"/>
<input type="submit" value="UPLOAD DP">
</s:form>
</td>
</tr>
</table>
</body>
</html>
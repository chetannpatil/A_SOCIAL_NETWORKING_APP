<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1UsersBFs</title>
</head>
<body>

<h3 style="color: maroon;font-family: cursive;" align="center">${showOneUsersBFStatusMessage }</h3>
<c:if test="${!empty oneUsersBFsSet }">
<h3 style="color: maroon;" align="center">Followings are ${oneUserBean.firstName} ${oneUserBean.lastName} 's best friends for Life Time.</h3>

<table align="center" border="0">
<c:forEach var="userBean" items="${oneUsersBFsSet}">
<tr>

<td>


<c:if test="${userBean.profilePic != null}">

<img src="data:image/jpg;base64,${userBean.base64Image}" width="50" height="50"/>

</c:if>

</td>


<td>
<c:if test="${userBean eq loggedUserBean }">
<b style="color: green;">You</b>
</c:if>

<c:if test="${userBean ne loggedUserBean }">



<s:form action="showOneUserProfile" >
<input type="hidden" name="oneUserEmail" value="${userBean.email }">
<input type="submit" value="${userBean.firstName }  ${userBean.lastName }" 
style="color: white;background-color: green;" align="middle">
</s:form>
</c:if>
</td>
</tr>
</c:forEach>
</table>
</c:if>
</body>
</html>
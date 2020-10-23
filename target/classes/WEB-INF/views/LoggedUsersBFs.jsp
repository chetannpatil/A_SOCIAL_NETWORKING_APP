<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyBFs</title>

</head>
<body>
<h3 style="color: maroon;font-family: cursive;">${noBFsYetMessage }</h3>
<c:if test="${!empty loggedUsersBFsMap }">
<h3 style="color: maroon;" align="center">Followings are your best friends for Life Time.</h3>
<table border='1' bordercolor="green" style="width:80%" align="center">

<c:forEach var="entryBean" items="${loggedUsersBFsMap.entrySet()}">
<tr>

<td width="50" height="50">

<c:if test="${entryBean.getKey().profilePic != null}">
<!-- photo -->
 <img src="data:image/jpg;base64,${entryBean.getKey().base64Image}" width="50" height="50"/>
</c:if>

</td>

<td width="150" >
<s:form action="showOneBFProfile" >
<input type="hidden" name="oneUserEmail" value="${entryBean.getKey().email }">
<input type="submit" value="${entryBean.getKey().firstName }  ${entryBean.getKey().lastName }"
style="color: white;background-color: green;">

</s:form>
</td>
<td style="color: white;" bgcolor="maroon">You became friends on : ${entryBean.getValue().toString().substring(0,19) }</td>


</tr>


</c:forEach>
</table>
</c:if>


</body>
</html>
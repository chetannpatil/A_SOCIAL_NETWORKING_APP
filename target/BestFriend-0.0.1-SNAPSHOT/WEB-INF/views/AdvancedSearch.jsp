<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdvancedSearch</title>
</head>
<body>
<h3 style="color: maroon;" align="center">Enter what you  know about your BF to search</h3>

<form action="advancedSearch" method="POST">
<table border="0" bordercolor="maroon" align="center" >
<tr>
<td>
FIRST NAME
</td>
<td>
LAST NAME
</td>
<td>
EMAIL
</td>
<td>
CITY  
</td>
<td>
STATE  
</td>
<td>
COUNTRY 
</td>
</tr>
<tr>
<td>
<textarea rows="1" cols="20" name="firstName"></textarea>
</td>
<td >
<textarea rows="1" cols="20"  name="lastName"></textarea>
</td>


<td>
 <textarea rows="1" cols="20" name="email"></textarea>
</td>

<td>
<textarea rows="1" cols="20" name="city"></textarea>
</td>

<td>
<textarea rows="1" cols="20" name="state"></textarea>
</td>

<td>
<textarea rows="1" cols="20" name="country"></textarea>

</td>



</tr>


</table>
<h3 align="center"><input type="submit" value="Search " style="color: white;background-color: maroon;"/> 
</h3>

</form>
<h3 style="color: maroon;" align="center">${advSerchResStatusMessage }</h3>
<c:if test="${!empty searchResultSet }">
<h3 style="color: maroon;" align="center">Could followings be your best friends ? </h3>
<table border='1' style="width:80%" align="center">

<c:forEach var="userBean" items="${searchResultSet}">

<tr>

<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${userBean.email }" style="color: maroon;">
<input type="submit" value="${userBean.firstName }  ${userBean.lastName }" style="color: white;background-color:green;">
${userBean.dob } ${userBean.address.street } ${userBean.address.city } ${userBean.address.state } ${userBean.address.country } ${userBean.email }
</s:form>
</td>

<td>
<c:if test="${loggedUserBean.friendsSet.contains(userBean) }">
<label style="color: maroon;">My BF</label>
</c:if>

<c:if test="${!loggedUserBean.friendsSet.contains(userBean) }">

<c:forEach var="invitationBean" items="${loggedUserBean.invitationsSet }">

<c:if test="${(invitationBean.invitationFromUser eq loggedUserBean) and (invitationBean.invitationToUser eq userBean)}">  
<label style="color: maroon;">BF request sent</label>
</c:if>
</c:forEach>

</c:if>
<c:if test="${!loggedUserBean.friendsSet.contains(userBean) }">
<c:forEach var="invitationBean" items="${userBean.invitationsSet }">
<c:if test="${(invitationBean.invitationFromUser eq userBean) and (invitationBean.invitationToUser eq loggedUserBean)}"> 

<label style="color: maroon;">has sent BF request to you</label>
</c:if>
</c:forEach>


</c:if>
</td>


</tr>


</c:forEach>
</table>
</c:if>
</body>
</html>
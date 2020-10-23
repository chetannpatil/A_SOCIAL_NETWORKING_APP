<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WallLikesAndHates</title>
</head>
<body>
<h3 style="color: maroon;">${seeAlllikesNHatesStatusMessage }</h3>

<c:if test="${ wallBean != null}">
<h3 style="color: maroon;" align="center">POST : ${wallBean.wallMessage }</h3>
<hr>
<h3 style="color: green;" align="center">The liked users :</h3>
<c:if test="${wallBean.likedUsersSet.size() == 0 }">
<h3 style="color: maroon;" align="center">None liked this wall :(</h3>
</c:if>
<table align="center" border="0" style="color: green;">
<c:forEach var="likedUserBean" items="${wallBean.likedUsersSet }">
<tr>


<c:if test="${likedUserBean.profilePic != null}">
<td>
<img src="data:image/jpg;base64,${likedUserBean.base64Image}" width="50" height="50"/>
</td>
</c:if>



<td style="color: maroon;">
<c:if test="${likedUserBean eq loggedUserBean }">


<h4 style="color: green;">You</h4>
</c:if>
<c:if test="${likedUserBean ne loggedUserBean }">


 
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${likedUserBean.email }" style="color: maroon;">
<input type="submit" value="${likedUserBean.firstName }  ${likedUserBean.lastName }"
style="color: white;background-color: green;">

</s:form>
</c:if>
</td>
</tr>




</c:forEach>
</table>
<br/>
<hr>
<h3 style="color: red;" align="center">The hated users :</h3>
<c:if test="${wallBean.hatedUsersSet.size() == 0 }">
<h3 style="color: maroon;" align="center">None hated this wall :)</h3>
</c:if>
<table align="center" border="0" style="color: green;">
<c:forEach var="hatedUserBean" items="${wallBean.hatedUsersSet }">
<tr>


 
 <c:if test="${hatedUserBean.profilePic != null}">
   <td>
      <img src="data:image/jpg;base64,${hatedUserBean.base64Image}" width="50" height="50"/>
   </td>
</c:if>


<td style="color: maroon;">
 
<c:if test="${hatedUserBean eq loggedUserBean }">


 
<h4 style="color: green;">You</h4>
</c:if>

<c:if test="${hatedUserBean ne loggedUserBean }">


 
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${hatedUserBean.email }" style="color: maroon;">
<input type="submit" value="${hatedUserBean.firstName }  ${hatedUserBean.lastName }"
style="color: white;background-color: green;">

</s:form>
</c:if>
</td>

</c:forEach>
</table>

</c:if>
</body>
</html>
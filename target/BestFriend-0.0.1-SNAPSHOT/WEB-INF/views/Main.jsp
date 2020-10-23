<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
</head>
<body>


<table id="t2" border='1' style="width:80%" align="center">
<tr height='170'>
<td id='tdHome' align='center'>
<h1 style="color: maroon;">Your Best D.P</h1>

 
 <c:if test="${loggedUserBean.profilePic != null}">
   <img src="data:image/jpg;base64,${loggedUserBean.base64Image}" width="200" height="200"/>
  </c:if>
  
  
<!--  <pre> -->
<hr>
 <a href="openUploadProfilePicOption" style="color: maroon;">
Add / Change D.P
</a>

<!--  </pre> -->


<c:if test="${isUserLikeToAddOrChangeDP}">


<form action="uploadProfilePic" enctype = "multipart/form-data" method = "post">

<input name="profilePic" type="file"/>
<input type="submit" value="UPLOAD DP">

</form>

</c:if>


<hr>
 <a href="openDeleteProfilePicOption" style="color: maroon;">
Remove D.P
</a>
<!-- isUserLikeToDeleteDP -->

<c:if test="${isUserLikeToDeleteDP}">

<h3 style="color: maroon;">R U sure , u wanna delete DP?
</h3>
<a href="toMain" style="color: maroon;">
NO
</a>

<br/>
<br/>
<form action="deleteProfilePic"  method = "GET">

<input type="submit" value="YES">

</form>

</c:if>


</td>

<td id='tdAbout'align='center' >
<a href='showMyBfs' style="color: maroon;" >Your Best Friends
</a>
</td>

<td id='tdContact' align='center'>
<a href='showMyMessages' style="color: maroon;" >Your Best Messages
</a>
</td>
</tr>
</table>
<br/>
<hr>
<br/>

<c:if test="${!empty searchResultSet }">
<h3 style="color: maroon;" align="center">Could followings be your best friends ? </h3>
<table border='1' style="width:80%" align="center">

<c:forEach var="userBean" items="${searchResultSet}">

<tr>


<!-- photo -->
<td width="50">
<c:if test="${userBean.profilePic != null}">
 
  <img src="data:image/jpg;base64,${userBean.getBase64Image()}" width="50" height="50"/>
  
  </c:if>
</td>


<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${userBean.email }" style="color: maroon;">
<input type="submit" value="${userBean.firstName }  ${userBean.lastName }" style="color: white;background-color:green;">
${userBean.dob.toString().substring(0,10) } ${userBean.address.street } ${userBean.address.city } ${userBean.address.state } ${userBean.address.country } ${userBean.email }
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
<h3 align="center" style="color: red;">${errorMessage}</h3>

<h3 align="center" style="color: green;">${successMessage}</h3>
<!-- ---------------------------------------------------------------------------------------------------------------------------- -->




<h3 style="color: maroon;" align="center">${wallStatusMessage }</h3>

<form action="selfWallIt" method="POST">
<table border='1' style="width:60%"  align="center">
<tr height="30px">

<td width="130" >
<textarea name="wallMessageStr" rows="1" cols="130" 
style="color: green;font-style: oblique;font-family: monospace;" >
</textarea>
</td>

<td>
<input type="submit" value="Wall it" style="color: white;background-color: maroon;height:30px">
</td>

</tr>

</table>
</form>








<h1 align="center" style="color: maroon;font-family: sans-serif;">WALL</h1>


<h3 style="color: maroon;" align="center">${wallDeleteStatusMessage }</h3>
<h3 style="color: maroon;" align="center">${likeWallStatusMessage }</h3>
<h3 style="color: maroon;" align="center">${hateWallStatusMessage }</h3>
<table border='1' style="width:80%;color: maroon;" align="center" >


<c:if test="${!empty allWallsList }">
<c:forEach var="wallBean" items="${allWallsList}">

<tr>

<td width="50">
<c:if test="${wallBean.wallFromUser.profilePic != null}">

 <img src="data:image/jpg;base64,${wallBean.wallFromUser.base64Image}" width="50" height="50"/>

</c:if>
</td>
 


<c:if test="${wallBean.wallFromUser  eq loggedUserBean }">
<td style="color: red;">
<b>
You
</b>
</td>
</c:if>

<c:if test="${wallBean.wallFromUser  ne loggedUserBean }">
<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${wallBean.wallFromUser.email }" style="color: maroon;">
<input type="submit" value="${wallBean.wallFromUser.firstName }  ${wallBean.wallFromUser.lastName }"
style="color: white;background-color: green;">
</s:form>
</td>

</c:if>


<td width="">${wallBean.wallMessage }</td>
<td>${wallBean.dateOfWallCreated.toString().substring(0,19) }</td>

<c:if test="${wallBean.wallToUser eq loggedUserBean}">
<td>
<form action="deleteMyThisWall" method="POST">
<input type="hidden" name="wallId" value="${wallBean.wallId }">
<input type="hidden" name="makeBFMessage" value="">
<input type="hidden" name="bfReqSentStatusMessage" value="">
<input type="hidden" name="oneUsersEmail" value="">
<input type="submit" value="Delete" style="color: white;background-color: red;">
</form>
</td>
</c:if>

<c:if test="${wallBean.wallFromUser ne loggedUserBean }">

<td style="color: green;">

<c:if test="${wallBean.likedUsersSet.contains(loggedUserBean) }">
<b>You Liked it</b>
</c:if>

<c:if test="${!wallBean.likedUsersSet.contains(loggedUserBean) }">
<form action="likeThisWall">
<input type="hidden" name="wallId" value="${wallBean.wallId}">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="submit" value="I Like it :)" style="color: white;background-color: green;">
</form>
</c:if>


</td>
</c:if>

<td>
<b style="color: green;">${wallBean.totalWallLikes }  liked</b>
</td>

<c:if test="${wallBean.wallFromUser ne loggedUserBean }">

<td style="color: maroon;">

<c:if test="${wallBean.hatedUsersSet.contains(loggedUserBean) }">
<b>You hated it</b>
</c:if>

<c:if test="${!wallBean.hatedUsersSet.contains(loggedUserBean) }">
<form action="hateThisWall">
<input type="hidden" name="wallId" value="${wallBean.wallId}">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="submit" value="I hate it :(" style="color: white;background-color: maroon;">
</form>
</c:if>
</td>

</c:if>
<td>
<b style="color: maroon;">${wallBean.totalWallHates }  hated</b>
</td>
<td>
<form action="showAllLikesAndHates" method="POST">
<input type="hidden" name="wallId" value="${wallBean.wallId}">
<input type="submit" value="see all " style="color: white;background-color: maroon;">
</form>
</td>
</tr>

</c:forEach>
</c:if>




</table>

</body>
</html>
<%@ include file="Footer.jsp" %>
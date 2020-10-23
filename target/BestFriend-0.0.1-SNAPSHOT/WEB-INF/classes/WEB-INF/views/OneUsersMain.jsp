<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OneUsersMain</title>
</head>
<body>

<table border="0" align="center" style="width:80%">
<tr>
<td><h1 style="color: green;">${oneUserBean.firstName.toUpperCase()}   ${oneUserBean.lastName.toUpperCase() }</h1>
</td>

<td>
<c:if test="${makeBFMessage.trim().length() > 0 }">
<s:form action="makeBF">

<input type="hidden" name="oneUserEmail" value="${oneUserBean.email }">
<input type="submit" value="makeBF" style="color: white;background-color: green;">
</s:form>
</c:if>
</td>


<td>
<h2 align="center" style="color: maroon;">${bfReqSentStatusMessage }
<c:if test="${makeBFMessage eq null || makeBFMessage.trim().length() == 0 }">
<c:if test="${loggedUserBean.friendsSet.contains(oneUserBean) eq true}">
<form action="unFriend" method="POST">
<input type="hidden" name="oneUserId" value="${oneUserBean.userId }">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="submit" value="unFriend" style="color: white;background-color: maroon;">
</form>
</c:if>
</c:if>
</h2>
</td>
</tr>
</table>

<!-- --------------------one user's header ends -------------------------------------------------- -->


<table  border='1' style="width:80%" align="center">
<tr height='170'>
<td align='center'>

<%-- <h1  style="color: green;">${oneUserBean.firstName }  ${oneUserBean.lastName}'s  D.P</h1> --%>

<c:if test="${oneUserBean.profilePic == null}">

<h3  style="color: green;">${oneUserBean.firstName }  ${oneUserBean.lastName} has no D.P yet</h3>

</c:if>
 
 
 <c:if test="${oneUserBean.profilePic != null}">
 
 <img src="data:image/jpg;base64,${oneUserBean.base64Image}" width="200" height="200"/>
 
</c:if>


</td>


<td>
<form action="toOneUsersAccount" method="POST">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="submit" value="More About ${oneUserBean.firstName }  ${oneUserBean.lastName} "
style="color: white; background:green;">
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
</form>
</td>


<td align='center' >
<form action="showOneUsersBFs" method="POST">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="submit" value="${oneUserBean.firstName }  ${oneUserBean.lastName} 's  Best Friends"
style="color: white; background:green;">
</form>
</td>

<td style="font-family: sans-serif; color: green;">
Enter Message below & press send
<form action="sendMessageToOneUserBean" method="POST">

<textarea rows="4" cols="25" name="messageToOneUserBeanStr">
</textarea>
<input type="hidden" name="requestedPageStr" value="OneUsersMainPage">

<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">

<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="submit" value="Send message to ${oneUserBean.firstName }  ${oneUserBean.lastName}"
 align="right" style="color: white; background:green;">
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
</form>
<b style="color: maroon;">${messageSentStatusMessage }</b>
</td>
</tr>
</table>

<c:if test="${detailedOneUserBean != null }">
<br/>
<hr>

<h2 align="center" style="color: maroon;">
The Details of ${detailedOneUserBean.firstName}  ${detailedOneUserBean.lastName}</h2>

<div align="center">
<c:if test="${detailedOneUserBean.profilePic != null}">

 <img src="data:image/jpg;base64,${detailedOneUserBean.base64Image}" width="50" height="50" />
 
</c:if>


</div>

 
<table align="center" border="0" width="400">

<tr>
<td>
I AM
</td>
<td>
${detailedOneUserBean.gender}
</td>
</tr>

<tr>
<td>
MORE ABOUT ME 
</td>
<td>
${detailedOneUserBean.moreAboutMe }
</td>
</tr>

<tr>
<td>
I BORN ON
</td>
<td>
${detailedOneUserBean.dob.toString().substring(0,10)}
</td>
</tr>

<tr>
<td>
I AM
</td>
<td>
${detailedOneUserBean.maritalStatus}
</td>
</tr>

<tr>
<td>
I STAY @
</td>
<td>
${detailedOneUserBean.address.houseNumber} 
${detailedOneUserBean.address.street} 
${detailedOneUserBean.address.city} 
${detailedOneUserBean.address.zip} 
${detailedOneUserBean.address.state} 
${detailedOneUserBean.address.country}
</td>
</tr>

</table>
</c:if>
 
 <!-- ---------------------------------------------------------------------------------------------------------------------------- -->
                              
                              <!--walling here  -->
<hr>
<h3 style="color: maroon;" align="center">${wallStatusMessage }</h3>
<form action="othersWallIt" method="POST">
<table border='1' style="width:60%" align="center">
<tr height="30px">

<td width="130">
<textarea name="wallMessageStr" rows="1" cols="130" 
style="color: green;size: 24pt;font-style: oblique;font-family: monospace;" >
</textarea>
</td>

<td >
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="submit" value="Wall it" style="color: white;background-color: maroon;height: 30px;">
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
</td>

</tr>

</table>
</form>

                       <!--displaying all walls here  -->

<h1 align="center" style="color: maroon;font-family: sans-serif;">WALL</h1>
<h3 style="color: maroon;" align="center">${wallDeleteStatusMessage }</h3>
<h3 style="color: maroon;" align="center">${likeWallStatusMessage }</h3>
<h3 style="color: maroon;" align="center">${hateWallStatusMessage }</h3>
<table border='1' style="width:80%;color: maroon;" align="center">


<c:if test="${!empty allWallsList }">
<c:forEach var="wallBean" items="${allWallsList}">
<tr>

 <td width="50">
  <c:if test="${wallBean.wallFromUser.profilePic != null}">
  
 
    <img src="data:image/jpg;base64,${wallBean.wallFromUser.base64Image}" width="50" height="50"/>
    
    
 </c:if>
</td>


<c:if test="${wallBean.wallFromUser  eq loggedUserBean }">
<td style="color: green;">
<b>You</b>
</td>
</c:if>

<c:if test="${wallBean.wallFromUser  eq oneUserBean }">
<c:if test="${oneUserBean.gender eq 'MALE'}">
<td style="color: red;"><b>Himself</b></td>
</c:if>
<c:if test="${oneUserBean.gender eq 'FEMALE'}">
<td style="color: red;"><b>Herself</b></td>
</c:if>
<c:if test="${oneUserBean.gender == null}">
<td style="color: red;"><b>Self</b></td>
</c:if>




</c:if>

<c:if test="${(wallBean.wallFromUser  ne loggedUserBean) and (wallBean.wallFromUser  ne oneUserBean)}">

<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${wallBean.wallFromUser.email }" style="color: maroon;">

<input type="submit" value="${wallBean.wallFromUser.firstName }  ${wallBean.wallFromUser.lastName }"
style="color: white;background-color: green;">
</s:form>
</td>

</c:if>
<td>${wallBean.wallMessage }</td>
<td>${wallBean.dateOfWallCreated.toString().substring(0,19) }</td>

<c:if test="${wallBean.wallFromUser eq loggedUserBean }">
<td>
<form action="deleteMyThisWall" method="POST">
<input type="hidden" name="wallId" value="${wallBean.wallId }">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
<input type="submit" value="Delete" style="color: white;background-color: red;">

</form>
</td>
</c:if>

<c:if test="${wallBean.wallFromUser ne loggedUserBean }">

<td style="color: green;">
<c:if test="${wallBean.likedUsersSet.contains(loggedUserBean) }">
You Liked it
</c:if>

<c:if test="${!wallBean.likedUsersSet.contains(loggedUserBean) }">
<form action="likeThisWall">
<input type="hidden" name="wallId" value="${wallBean.wallId}">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${oneUserBean.email }">
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
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
<input type="hidden" name="unFriendMessage" value="${unFriendMessage}">
<input type="submit" value="I hate it :(" style="color: white;background-color: maroon;">
</form>
</c:if>

</c:if>
<td>
<b style="color: maroon;">${wallBean.totalWallHates }  hated</b>
</td>

<td>
<form action="showAllLikesAndHates" method="POST">
<input type="hidden" name="wallId" value="${wallBean.wallId}">
<input type="submit" value="see all " style="color: white;background-color:maroon;">
</form>
</td>
</tr>

</c:forEach>
</c:if>
</table>

</body>
</html>
<%@ include file="Footer.jsp" %>
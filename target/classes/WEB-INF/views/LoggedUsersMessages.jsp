<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LUSBMessages</title>
</head>
<body>
<h4  style="color: maroon;" align="center">${repliedMessageStatusMessage }</h4>
<h3 style="color: maroon;" align="center">${deleteMessageStatusMessage }</h3>
<h2 style="color: maroon;" align="center"> ${lusbAllMessagesStatusMessage }</h2>
<c:if test="${!empty lusbAllMesgList }">
<table align="center" border="1">
<tr style="color: maroon;">
<th >
Sender
</th>
<th>
Receiver
</th>
<th>
Message
</th>
<th>
Date
</th>

</tr>

<c:forEach var="messageBean" items="${lusbAllMesgList }">

<tr>
<c:if test="${messageBean.messageFromUser eq loggedUserBean}">

<td style="color: red;">
<b>You</b>

<c:if test="${loggedUserBean.profilePic != null}">
 <img src="data:image/jpg;base64,${loggedUserBean.base64Image}" width="50" height="50"/>
</c:if>


</td>

<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${messageBean.messageToUser.email }" style="color: maroon;">
<input type="submit" value="${messageBean.messageToUser.firstName }  ${messageBean.messageToUser.lastName }"
style="color: white;background-color: green;">
</s:form>

<!-- photo -->

<%--  <img src="data:image/jpg;base64,${loggedUserBean.base64Image}" width="200" height="200"/> --%>

<c:if test="${messageBean.messageToUser.profilePic != null}">
 <img src="data:image/jpg;base64,${messageBean.messageToUser.base64Image}" width="50" height="50"/>
</c:if>





</td>
</c:if>

<c:if test="${messageBean.messageToUser eq loggedUserBean}">

<td style="color: maroon;">
<s:form action="showOneUserProfile" >

<input type="hidden" name="oneUserEmail" value="${messageBean.messageFromUser.email }" style="color: maroon;">
<input type="submit" value="${messageBean.messageFromUser.firstName }  ${messageBean.messageFromUser.lastName }"
style="color: white;background-color: green;">
</s:form>

<!-- photo -->

<%--  <img src="data:image/jpg;base64,${loggedUserBean.base64Image}" width="200" height="200"/> --%>

 
 
 <c:if test="${messageBean.messageFromUser.profilePic != null}">
 <img src="data:image/jpg;base64,${messageBean.messageFromUser.base64Image}" width="50" height="50"/>
</c:if>

</td>

<td style="color: red;">
<b>You</b>

<c:if test="${loggedUserBean.profilePic != null}">
 <img src="data:image/jpg;base64,${loggedUserBean.base64Image}" width="50" height="50"/>
</c:if>


</td>
</c:if>

<td>
${messageBean.message }
</td>
<td>
${messageBean.dateOfMessageCreated.toString().substring(0,19) }
</td>

<td>
<form action="deleteMyThisMessage" method="POST">
<input type="hidden" name="messageId" value="${messageBean.messageId}">
<input type="submit" value="delete" style="color: white; background-color: red">
</form>
</td>
<c:if test="${messageBean.messageToUser eq loggedUserBean}">
<td>

<form action="sendMessageToOneUserBean" method="POST">

<textarea rows="2" cols="25" name="messageToOneUserBeanStr">
</textarea>
<input type="hidden" name="requestedPageStr" value="LoggedUsersMessagesPage">
<input type="hidden" name="makeBFMessage" value="${makeBFMessage}">
<input type="hidden" name="bfReqSentStatusMessage" value="${bfReqSentStatusMessage}">
<input type="hidden" name="oneUsersEmail" value="${messageBean.messageFromUser.email }">
<input type="submit" value="Reply" style="color: white; background:green;">


</form>

</td>
</c:if>
</tr>

</c:forEach>
</table>
</c:if>


</body>
</html>
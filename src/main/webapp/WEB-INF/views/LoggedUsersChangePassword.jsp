<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LoggedUsersChangePassword</title>
</head>
<body>
<h2 align="center" style="color: maroon;">${changePasswordStatusMessage}</h2>
<h2 style="color: maroon;" align="center">Change Your Password</h2>
<form action="changeMyPassword" method="POST">
<table align="center">
<tr>
<td>
Enter Current Password
</td>
<td>

 <input type="password" name="currentPassword">

</td>
</tr>

<tr>
<td>
Enter Brand New Password 
</td>
<td>
 <input type="password" name="newPassword">
</td>
</tr>

<tr>
<td>
Re-Enter Brand New Password 
</td>
<td>
 <input type="password" name="reEnterNewPassword">
</td>
</tr>
</table>
<h3 align="center">
<input type="submit" value="changeMyPassword" style="color: white;background-color: red">
</h3>
</form>
</body>
</html>
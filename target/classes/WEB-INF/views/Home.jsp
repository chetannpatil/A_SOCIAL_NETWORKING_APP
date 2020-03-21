<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BEST-FRIENDS-FOR-EVER</title>
<link rel="stylesheet" type="text/css" href="Design.css">
</head>

<body bgcolor="maroon">

<h1 style="color: white;"><marquee>${signOutMessage }</marquee> </h1>
<p style="color: white; font-family:monospace;  font-size: 70pt; " align="center"   >BEST-FRIEND</p>
                 

<h2 align="center"  style="color: white;"><a style="color: white; " href="openRegisterView"  >REGISTER</a></h2>

<s:form action="signIn" method="POST" >
<h4 style="color: white;" align="center">${loginErrorMessage }</h4>
    
<h4 style="color: white;" align="center">${errorMessage }</h4>
<table align="center" border="2">
<tr>
<td>

<p style="color: white;" align="center">SIGN-IN</p>

<p style="color: white;" >EMAIL-ID :&nbsp; :<input  type="text" name="email" value="${email }" />

</p>
                        
<p style="color: white;" >PASSWORD  :<input type="password" name="password" />

</p>

<p align="center"><input type="submit"  value='LOG IN' />
</p>
</td>
</tr>

</table>

</s:form>

<h6 style="color: white;"><marquee>${SuccessMessage }</marquee> </h6>
</body>
</html>
<%@ include file="Footer.jsp" %>
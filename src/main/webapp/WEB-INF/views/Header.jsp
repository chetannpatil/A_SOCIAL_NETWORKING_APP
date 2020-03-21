<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
</head>
<body>


<table border="0" align="left">
<tr >
<td width="1800">
<h1  style="color: red;" align="left">${loggedUserBean.firstName.toUpperCase()}   ${loggedUserBean.lastName.toUpperCase() }</h1>
</td>

<td width="200">
<h1  style="color: red;"><a  href="toMain"  style="color: red;">Home</a></h1> 
</td>

<td width="200">
<h1  style="color: red;"><a  href="toAccount"  style="color: red;">Account</a></h1> 
</td>

<td >
<h1  style="color: red;"><a  href="signOut"  style="color: red;">Sign-Out</a></h1>
</td>
</tr>
</table>

<table align="center">
<tr>
<td width="1800" align="center">

<form action="searchBFs">
<input type="text" width="150" name="searchStr" style="color: green;">
<input type="submit" value="search" style="color: white;background-color: red;"> 
</form>
</td>

<td>
<form action="showMyBFRequests" >
<h2 align="right" style="color: red;">
<input type="submit" value="My BF requests" style="color: white;background-color: red;">
</h2>
</form>
</td>
</tr>

<tr>
<td style="color: red;" width="1800" align="center">${noMatchingRecordsMessage }</td>
</tr>
</table>
<hr style="color: maroon;">
</body>
</html>
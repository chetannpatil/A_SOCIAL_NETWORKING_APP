<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AccountDetails</title>
</head>
<body>
<h3 align="center" style="color: maroon;">${lubAccountUpdateStatusMessage }</h3>
<h2 align="center" style="color: maroon;">${changePasswordStatusMessage}</h2>
<table border="0" align="center" width="1000px">
<tr>
<td>
<h2><a href="editMyAccountDetails" style="color: maroon;">Edit Account Details</a></h2>
</td>
<td>
<h1 align="center" style="color: maroon;">ACCOUNT INFO :</h1>
</td>

<td>
<h2><a href="openChangeMyPasswordView" style="color: maroon;">Change Password</a></h2>
</td>
</tr>
</table>


<table align="center" border="0" bordercolor="maroon" width="400px" cellspacing="12px">
<tr>
<td>
FIRST NAME
</td>
<td>
${loggedUserBean.firstName }
</td>
</tr>

<tr>
<td>
LAST NAME
</td>
<td >
${loggedUserBean.lastName}
</td>
</tr>

<tr>
<td>
EMAIL
</td>
<td>
${loggedUserBean.email }
</td>
</tr>


<tr>
<td>
GENDER
</td>
<td>
${loggedUserBean.gender}
</td>
</tr>

<tr>
<td>
D.O.B  
</td>
<td>
${loggedUserBean.dob.toString().substring(0,10)}
</td>

</tr>

<tr>
<td>
MORE-ABOUT-ME  
</td>
<td>
${loggedUserBean.moreAboutMe}
</td>
</tr>

<tr>
<td>
MARITAL-STATUS  
</td>
<td>
${loggedUserBean.maritalStatus}
</td>
</tr>

<tr>
<td>
QUALIFICATION  
</td>
<td>
${loggedUserBean.qualification}
</td>
</tr>

<tr>
<td>
PROFESSION  
</td>
<td>
${loggedUserBean.profession}
</td>
</tr>

<tr>
<td colspan="2" align="center" >ADDRESS :  
</td>
</tr>

<tr>
<td>
HOUSE-NUMBER  
</td>
<td>
${loggedUserBean.address.houseNumber}
</td>
</tr>

<tr>
<td>
STREET  
</td>
<td>
${loggedUserBean.address.street}
</td>
</tr>

<tr>
<td>
CITY  
</td>
<td>
${loggedUserBean.address.city}
</td>
</tr>

<tr>
<td>
ZIP  
</td>
<td>
${loggedUserBean.address.zip}
</td>
</tr>

<tr>
<td>
STATE  
</td>
<td>
${loggedUserBean.address.state}
</td>
</tr>

<tr>
<td>
COUNTRY 
</td>
<td>
${loggedUserBean.address.country}
</td>
</tr>

</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BF REGISTERATION</title>
<style type="text/css">
#h1
{color: maroon;
}
</style>
</head>
<body>
<h1 align="center" class="h1" id="h1">Thanks for joining us </h1>
<pre>
<marquee >You just fill the following form 
& complete formal registration.
Then it is our responsible to find out your 
missed out best friends across oceans.
<h2>THANK YOU</h2>
</marquee>
</pre>

<s:form action="register"  modelAttribute="userBean">
<h1 align="center">ACCOUNT INFO :[MANDATORY FIELDS]</h1>
<table align="center" border="0" bordercolor="maroon">
<tr>
<td>
FIRST NAME
</td>
<td>
<s:input path="firstName"/> 
<s:errors path="firstName" style="color:red"></s:errors>
</td>
</tr>
<tr>
<td>
EMAIL
</td>
<td>
 <s:input path="email"/>
<s:errors path="email" style="color:red"></s:errors>
</td>

<td><b style="color: red;">${dupUserErrorMessage }</b></td>
</tr>


<tr>
<td>
PASSWORD  
</td>
<td>
 <s:input path="password" type="password"/>
 <s:errors path="password" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
REPEAT-PASSWORD  
</td>
<td>
 <s:input path="repeatPassword" type="password"/>
 <s:errors path="repeatPassword" style="color:red"></s:errors>
</td>

<td> <b style="color: red;">${pswErrorMessage }</b></td>
</tr>

<tr>
<td>
GENDER
</td>
<td>
MALE<s:radiobutton path="gender" value="MALE"></s:radiobutton>
FEMALE<s:radiobutton path="gender" value="FEMALE"></s:radiobutton>
<s:errors path="gender" style="color:red"></s:errors>
</td>
</tr>
</table>



<h1 align="center">PERSONNEL INFO :[OPTIONAL FIELDS]</h1>
<table align="center" border="0" bordercolor="maroon" >
<tr >
<td>
LAST NAME
</td>
<td >
<s:input path="lastName" ></s:input> <s:errors path="lastName" style="color:red"></s:errors>
</td>
</tr>



<tr>
<td>
D.O.B  
</td>
<td>
 <s:input path="dob"/>
 <s:errors path="dob" style="color:red"></s:errors>
</td>

<td> <b style="color: red;">${dobErrorMessage }</b></td>
</tr>

<tr>
<td>
MORE-ABOUT-ME  
</td>
<td>
<s:textarea path="moreAboutMe" rows="5" cols="33"></s:textarea>
<s:errors path="moreAboutMe" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
MARITAL-STATUS  
</td>
<td>
<s:radiobutton path="maritalStatus" value="SINGLE"></s:radiobutton>SINGLE
<s:radiobutton path="maritalStatus" value="MARRIED"></s:radiobutton>MARRIED
<s:errors path="maritalStatus" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
QUALIFICATION  
</td>
<td>
<s:input path="qualification" rows="5" cols="33"/>
<s:errors path="qualification" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
PROFESSION  
</td>
<td>
<s:input path="profession" rows="5" cols="33"/>
<s:errors path="profession" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td colspan="2" align="center" >ADDRESS  
</td>
</tr>

<tr>
<td>
HOUSE-NUMBER  
</td>
<td>
<s:input path="address.houseNumber"></s:input>
<s:errors path="address.houseNumber" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
STREET  
</td>
<td>
<s:input path="address.street"></s:input>
<s:errors path="address.street" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
CITY  
</td>
<td>
<s:input path="address.city"></s:input>
<s:errors path="address.city" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
ZIP  
</td>
<td>
<s:input path="address.zip"></s:input>
<s:errors path="address.zip" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
STATE  
</td>
<td>
<s:input path="address.state"></s:input>
<s:errors path="address.state" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
COUNTRY 
</td>
<td>
<s:input path="address.country"></s:input>
<s:errors path="address.country" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td colspan="2" align="center">
<input type="submit" value="sub mit details "/> 
</td>
</tr>


</table>
</s:form>


</body>
</html>
<%@ include file="Footer.jsp" %>
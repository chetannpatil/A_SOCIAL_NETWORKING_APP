<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ include file="Header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EditLUsAccountDetails</title>
</head>
<body>
<h2 style="color: maroon;" align="center">Enter new Details for the fields you want to change & save</h2>
<s:form action="updateEditedAccount" modelAttribute="updatedLoggedUserBean"  method="POST" >

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
GENDER
</td>
<td>
MALE<s:radiobutton path="gender" value="MALE"></s:radiobutton>
FEMALE<s:radiobutton path="gender" value="FEMALE"></s:radiobutton>
<s:errors path="gender" style="color:red"></s:errors>
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
<td >
<s:input path="qualification" ></s:input>
<s:errors path="qualification" style="color:red"></s:errors>
</td>
</tr>

<tr>
<td>
PROFESSION
</td>
<td >
<s:input path="profession" ></s:input>
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
<td>
EMAIL
</td>
<td>
 <s:input path="email" readonly="true"/> Unchangeable Sorry
</td>
</tr>


</table>
 <s:input path="password" readonly="true" type="hidden"/>
 <s:input path="repeatPassword" readonly="true" type="hidden"/>

<h2 align="center">
<input type="submit" value="Save Changes " style="color: white; background-color: red" /> 
</h2>

</s:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Creation</title>
<script src="resources/js/jquery-2.2.3.js" type="text/javascript">
<!--

//-->
</script>
</head>
<body>

<jsp:include page="mndsFrame.jsp"/>


<p>Pilot information not found!

<p>Please enter a valid e-mail address to proceed

<form:form action="submitEmail" method="post"
		modelAttribute="evePilotEmailForm" >
		${characterID }
		<form:hidden path="characterID" value="${characterID }"/>
<table>
	<tr>
	<td>
	E-Mail:
	</td>
	<td>
	<form:input path="emailAddress" cols="50" />
	</td>
	</tr>
	<tr>
				<td></td>
				<td><input style="width: 120px; height: 50px; font-size: 18px;"
					type="submit" name="submitEmail" value="Submit" /></td>
			</tr>
</table>

</form:form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EVE API Entry</title>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/js/jquery-2.2.3.js" type="text/javascript">
<!--

//-->
</script>
</head>
<body>

No valid API Keys Found!

<p>Please enter your API keys below: 
<form:form action="submitKeys" method="post"
		modelAttribute="evePilotAPIForm" >
<table>
<tr> 
     <td>Key: <input type="text" id="key" name="key"></td> 
     <td>vCode: <input type="text" id="vCode" name="vCode"></td> 
</tr> 


</table>

<button>Add API Key</button>
<input style="width: 120px; height: 50px; font-size: 18px;"
					type="submit" name="submitEmail" value="Submit" />
</form:form>

</body>

<script>

var i = 1;
$("button").click(function() {
  $("table tr:first").clone().find("input").each(function() {
    $(this).attr({
      'id': function(_, id) { return id + i },
      'name': function(_, name) { return name + i },     
    });
  }).end().appendTo("table");
  i++;
});
</script>
</html>
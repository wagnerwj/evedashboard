<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="resources/js/jquery-2.2.3.js" type="text/javascript">
<!--

//-->
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EVE SSO Testing</title>
</head>
<body>
<img src="/evedashboard/img/indexblack.png" onClick="submitToEve()"/>
</body>

<script type="text/javascript">
 function submitToEve(){
	 alert("Hi There!");
	 $.ajax({
	    	type: "GET",
	    	url: "/evedashboard/signIn",
	    	dataType: 'html',
	    	

	    	success: function(html){
	                 $("#container").html(html);
	    	},

	    	error: function(){
	    	}
	    });

 }

</script>
</html>
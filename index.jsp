<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/registration.js"></script>
  <link href="<%=request.getContextPath()%>/css/registration.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css">
</head>
<script>
	function noBack()
	{
		window.location.hash="no-back-button";
		window.location.hash="Again-No-back-button";
	}
	window.onhashchange=function()
	{
		window.location.hash="no-back-button";
	}
</script>
<body onload="objPopup.getAllProducts();noBack();">
<!--Wrapper div starts here-->
	<div class="wrapper">
		
		
		<%@ include file = "/jsp/header.jsp" %>
		<%@ include file = "/jsp/container.jsp" %>
		<%@ include file = "/jsp/footer.jsp" %>
		
	</div>
	<!--Wrapper div ends here-->


</body>
</html>
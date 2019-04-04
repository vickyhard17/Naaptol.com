
<%
String name=(String)session.getAttribute("firstName");
String id=String.valueOf(session.getAttribute("userId"));

	if(name!=null && id!=null)
	{
		session.setAttribute(name,null);
		session.setAttribute(id,null);
		
		 response.setHeader("Cache-Control", "no-cache");
		 response.setHeader("Cache-Control", "no-store");
		 response.setHeader("Pragma", "no-cache");
		 response.setDateHeader("Expires", 0);
		 session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

%>
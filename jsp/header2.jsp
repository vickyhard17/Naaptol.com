  <link href="<%=request.getContextPath()%>/css/registration.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/loginIfNot.js"></script>
  <input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
<!--Header div starts here-->
		<div class="header">

			<!--Logo div starts here-->
			<div class="logoDiv" onclick="objLogin.showHomePage()">
				<img src="<%=request.getContextPath()%>/images/logoImage.png">
			</div>
			<!--Logo div ends here-->

			<p class="isoP">An ISO 9001 &amp; 10002 Certified Company</p>

			<!--Links div starts here-->
			<div class="linksDiv">
			<%String isAdmin=(String)session.getAttribute("isAdmin");
			if(session.getAttribute("userId")!=null && "N".equals(isAdmin))
				  {%>
						<span class="headLink">HI<b>&nbsp;<%=session.getAttribute("firstName")%></b></span> |
						<a href="<%=request.getContextPath()%>/jsp/logout.jsp"" class="headLink link" >Log Out</a>
				<%}
			else if(session.getAttribute("userId")!=null && "Y".equals(isAdmin))
			{
				
					response.sendRedirect(request.getContextPath()+"/jsp/admin.jsp");
				
			
			}else{ %>	
				
				<span class="headLink">HI<b> Guest</b></span>	
				<% }%>
				<%if(session.getAttribute("userId")!=null && !"Y".equals(isAdmin)){ %>
				| <a href="<%=request.getContextPath()%>/jsp/allOrders.jsp" class="headLink link">Track Order</a>	
				<% }%>
				<p class="callP">Call <span class="headNumber">0922-006-2000</span> to order</p>
			</div>
			<!--Links div ends here-->
			
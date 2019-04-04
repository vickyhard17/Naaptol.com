  <%@page import="java.util.List"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/registration.js"></script>

  <link href="<%=request.getContextPath()%>/css/registration.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css">

	<jsp:include page="/jsp/login.jsp"></jsp:include>
  	<jsp:include page="/jsp/registration.jsp"></jsp:include>


<!--Header div starts here-->
		<div class="header">

			<!--Logo div starts here-->
			<div class="logoDiv" onclick="objPopup.showHomePage()">
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
				
				<span class="headLink">HI<b> Guest</b></span> |
				<a href="#" class="headLink link" onclick="objPopup.showPopup()">Log In</a>	|
				<a href="#" class="headLink link" onclick="obj.showRegForm()">Sign Up</a>	
				<% }%>
				<%if(session.getAttribute("userId")!=null){ %>
				| <a href="<%=request.getContextPath()%>/jsp/allOrders.jsp" class="headLink link">Track Order</a>	
				| <a href="<%=request.getContextPath()%>/jsp/myProfile.jsp" class="headLink link">My Account</a>
				<% }%>
				<p class="callP">Call <span class="headNumber">0922-006-2000</span> to order</p>
			</div>
			<!--Links div ends here-->
			
			<!--searchDiv div starts here-->
			<div class="searchDiv">
				
				<!--categoryDiv div starts here-->
				<div class="categoryDiv" onmouseover="objPopup.showCategories()" onmouseout="objPopup.hideCategories()">
					<span class="categorySpan">Shopping Categories</span>

					
					<span class="arrowNav">&nbsp;</span>
				</div>
				<!--categoryDiv div ends here-->
				
					<!--SearchInput field div starts here-->
					<div class="searchInputDiv">	

						<input type="text" class="searchInput" onkeyup="objPopup.autoComplete(this)" placeholder="Find your favourite brand, product, model and many more">
						
						<!--SearchButton div starts here-->
						<div class="searchButton">

							<img src="<%=request.getContextPath()%>/images/srchIcon.png" class="searchIcon" alt="searchLogo">

						</div>
						<!--SearchButton div ends here-->

					</div>
					<!--SearchInput field div ends here-->
					
					<!--cartDiv starts here-->
					<div class="cartDiv" onclick="objPopup.showCart()">
					<span class="cartIcon"></span>
					<%if(session.getAttribute("productData")!=null)
						{
							List list=(List)session.getAttribute("productData");
							%>
							<span class="cartCount"><%=list.size()%></span>
						
						<% }
						else{%>
								<span class="cartCount">0</span>
						<%} %>

					</div>
					<!--cartDiv ends here-->

			</div>
			<!--searchDiv div ends here-->


		</div>
		<div class="categoryContanerDiv">
			<div class="categoryListDiv" onmouseover="objPopup.showCategoryDiv()" onmouseout="objPopup.hideCategories()">
			</div>
			
			<div class="subCatListDiv" onmouseover="objPopup.showCategoryDiv()" onmouseout="objPopup.hideCategories()">
			</div>
			
			<div class="autocompleteDiv">
			</div>
		</div>
		<!--Header div ends here-->
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
		<div class="backDiv" id="backDivId" onclick="objPopup.hidePopup();obj.hideRegForm()"></div>
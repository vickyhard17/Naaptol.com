<jsp:include page="/jsp/header2.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<%@page import="com.naaptolOnlineShopping.bean.ProductDescOutputBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
<body>
	<div class="cartContainer">
		<div class="cartHeader">
				<%
				if(session.getAttribute("productData")!=null)
				{
					List list=(List)session.getAttribute("productData");%>
					
					
					<h1>My Shopping Cart </h1>
					<span class="cartPresentItem">At present, you have<span>(<%=list.size()%>)</span> items.</span>
					<div class="cartLinksDiv">
						<a href="<%=request.getContextPath()%>/index.jsp" class="cartContinueShopping">Continue Shopping</a>
						<a href="<%=request.getContextPath()%>/jsp/shippingAddress.jsp" class="cartCheckout">Proceed to Checkout</a>
					</div>
				
		</div>
				<div class="cartProductHead">
					
					<table class="tableClass">
						<tr>
							<th class="tableTh">Item(s)</th>
							<th class="tableTh">Color | Size</th>
							<th class="tableTh">Quantity</th>
							<th class="tableTh">Unit Price</th>
							<th class="tableTh">Shipping</th>
							<th class="tableTh">Order Amount</th>
							<th class="tableTh"></th>
						
						</tr>
					<%float totalCartValue=0;%>
					<%
					
					if(list!=null && !list.isEmpty())
					{
						for(int i=0;i<list.size();i++)  
						{
				           ProductDescOutputBean opBean=(ProductDescOutputBean)list.get(i);
				           if(opBean.isStatusFlag())
				           {
				        %>	<tr class="tableTr">
										<td class="tableTd"><%=opBean.getProductName()%></td>
										
										<%if(opBean.getColor()!=null && opBean.getSize()==null)
										{%>
										
											<td class="tableTd"><%=opBean.getColor()%></td>
											
										<%} else if(opBean.getSize()!=null && opBean.getColor()==null)
										  {%>
											
											<td class="tableTd"><%=opBean.getSize()%></td>
											
										<%} else
										{%>
											<td class="tableTd"><%=opBean.getColor()%>&nbsp;&nbsp;<%=opBean.getSize()%></td>
											
										<%} %>
										<td class="tableTd"><select name="selectedQuantity" id="selectedQuantity" onchange="objPopup.updateQuanity(<%=opBean.getSellerDetailId()%>,this)">
													<option value='<%=opBean.getQuantity()%>' selected='selected'><%=opBean.getQuantity()%></option>
													<%for(int j=1;j<=5;j++)
													{%>
												
														<option value="<%=j%>"><%=j%></option>
													
													<% }%>
													
											</select>
										</td>
										
										
										<td class="tableTd"><%=opBean.getProductPrice()%></td>
										<td class="tableTd"><%=opBean.getShippingCost()%></td>
										<% Float unitPrice=opBean.getProductPrice();
											Float shippingCost=opBean.getShippingCost();
											
												Long quantity=opBean.getQuantity();
											float orderAmount=quantity.floatValue()*unitPrice.floatValue()+shippingCost.floatValue()*quantity.floatValue();%>
											
										<td class="tableTd"><%=orderAmount%></td>
										<td><a class="removeFromCart" onclick="objPopup.removeFromCart(<%=opBean.getSellerDetailId()%>)">Remove</a></td>
							</tr>
							<%totalCartValue=totalCartValue+orderAmount;%>
				           
				           
						<%}}
						
					}%>
					
					
				</table>
			</div>
			
		
	</div>
	
		<div class="cartPTotalPrice">
			<span>Total Cart Value: &nbsp;<%=totalCartValue%></span>
		</div>
<%}
else{%><h1>My Shopping Cart: </h1>
			<span class="cartPresentItem">At present, you have&nbsp;<span>0</span> items.</span>
			<div class="cartLinksDiv">
				<a href="<%=request.getContextPath()%>/index.jsp" class="cartContinueShopping">Continue Shopping</a>
				
			</div>
		</div>
		<div class="cartProductHead">
			
			<table class="tableClass">
				<tr>
					<th class="tableTh">Item(s)</th>
					<th class="tableTh">Color | Size</th>
					<th class="tableTh">Quantity</th>
					<th class="tableTh">Unit Price</th>
					<th class="tableTh">Shipping</th>
					<th class="tableTh">Order Amount</th>
					
				</tr>
		<h2>no items in the cart</h2>
		</table>
		</div>
		
	</div>
	<%}%>

</body>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
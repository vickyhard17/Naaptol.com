<jsp:include page="/jsp/header2.jsp"></jsp:include>

<%@page import="com.naaptolOnlineShopping.bean.UserShippingAddressOpBean"%>
<%@page import="com.naaptolOnlineShopping.helper.UserShippingAddressHelper"%>
<%@page import="com.naaptolOnlineShopping.bean.ProductDescOutputBean"%>
<%@page import="java.util.List"%>
<%if(session.getAttribute("userId")!=null)
{
		UserShippingAddressOpBean addressBean=null;
		
		Long userid=(Long)session.getAttribute("userId");
		
		String addressId=(String)request.getParameter("addressId");
		
		if(addressId!=null && !"".equals(addressId))
		{
			UserShippingAddressHelper helper=new UserShippingAddressHelper();
			
			addressBean=helper.getShippingAddressById(addressId);
		}
		
		
	
	
%>
<div>
	<form action="<%=request.getContextPath()%>/CommonController" method="post">
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
				<%float totalCartValue=0; %>
				<%if(session.getAttribute("productData")!=null);
				{
					List list=(List)session.getAttribute("productData");
					if(list!=null && !list.isEmpty())
					{
						for(int i=0;i<list.size();i++)  
						{
				           ProductDescOutputBean opBean=(ProductDescOutputBean)list.get(i);
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
										<td class="tableTd"><%=opBean.getQuantity()%></td>
										
										
										<td class="tableTd"><%=opBean.getProductPrice()%></td>
										<td class="tableTd"><%=opBean.getShippingCost()%></td>
										<% Float unitPrice=opBean.getProductPrice();
										   Float shippingCost=opBean.getShippingCost();
											Long quantity=opBean.getQuantity();
											float orderAmount=quantity.floatValue()*unitPrice.floatValue()+shippingCost.floatValue()*quantity.floatValue();%>
											
										<td class="tableTd"><%=orderAmount%></td>
										
							</tr>
							<%totalCartValue=totalCartValue+orderAmount;%>
				           
			           
					<%}
					
				}
				
			}%>
				
				
			</table>
		</div>
		
	
	
	
		<div class="cartPTotalPrice">
			<span>Total Cart Value: &nbsp;<%=totalCartValue%></span>
		</div>
	
		<div class="orderAddress">
			<span><%=addressBean.getFirstName()%></span>
			<span><%=addressBean.getLastName()%></span></br>
			<Span><%=addressBean.getAddress()%></Span></br>
			<span><%=addressBean.getPincode()%></span>
			<span><%=addressBean.getLandmark()%></span><br>
			<span><%=addressBean.getContactNumber()%></span></br>
		</div>
				<input type="hidden" name="addressId" value="<%=addressBean.getShippingAddressId()%>">
				<input type="hidden" name="cartValue" value="<%=totalCartValue%>">
				
				<input type="hidden" name="action" value="placeOrder">
				<input type="submit" value="CLICK HERE TO PLACE ORDER" class="orderButtn">
		
		
	</form>
	
</div>	
<%}%>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
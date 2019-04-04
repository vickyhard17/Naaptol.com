<%@page import="java.util.List"%>
<%@page import="com.naaptolOnlineShopping.bean.ProductDescOutputBean"%>
<jsp:include page="/jsp/header2.jsp"></jsp:include>

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

<body onload="noBack()">
<%if(session.getAttribute("userId")!=null)
{%>
	<h1>Order Successfully placed !!</h1>
	<h2>Thanks For your order :-)</h2>	
	<div class="oredrSuccessDiv" onclick="objLogin.showHomePage()">Continue Shopping</div>
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
		
<%
	session.removeAttribute("productData");
}%>

</body>
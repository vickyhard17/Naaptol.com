<jsp:include page="/jsp/header2.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
<%@page import="com.naaptolOnlineShopping.bean.OrdersOutputBean"%>
<%@page import="java.util.List"%>
<%@page import="com.naaptolOnlineShopping.helper.OrderDetailsHelper"%>
<%
Long userId=(Long)session.getAttribute("userId");
if(userId!=null)
{
	OrderDetailsHelper helper=new OrderDetailsHelper();
	List list=helper.getAllOrders(userId);
	if(list!=null && !list.isEmpty())
	{%>
		<table class="tableClass">
			<tr>
				<th class="tableTh">ORDER ID</th>
				<th class="tableTh">ORDER DATE</th>
				<th class="tableTh">PAYMENT TYPE</th>
				<th class="tableTh">PRODUCT NAME</th>
				<th class="tableTh">PRODUCT PRICE</th>
				<th class="tableTh">SHIPPING COST</th>
				<th class="tableTh">OREDER AMOUNT</th>
				<th class="tableTh">COLOR&nbsp;|&nbsp;SIZE</th>
				<th class="tableTh">CANCEL OREDER</th>
				
			</tr>
		<%for(int i=0;i<list.size();i++)  
		{
           OrdersOutputBean opBean=(OrdersOutputBean)list.get(i);
           if(opBean.isStatusFlag())
           {
        %>	<tr class="tableTr">
						<td class="tableTd"><%=opBean.getOrderId()%></td>
						<td class="tableTd"><%=opBean.getOrderDate()%></td>
						<td class="tableTd"><%=opBean.getPaymentType()%></td>
						<td class="tableTd"><%=opBean.getProductName()%></td>
						<td class="tableTd"><%=opBean.getProductPrice()%></td>
						<td class="tableTd"><%=opBean.getShippingCost()%></td>
						<td class="tableTd"><%=opBean.getOrderAmount()%></td>
						
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
						
						<td class="tableTd"><a class="cancelOrderClass" onclick="objPopup.cancelOrder(<%=opBean.getOrderId()%>)">Cancel Order</a></td>
						
						
			</tr>
			<%}
           else
      		{%>
      			<h2>No Orders found !</h2>
      		<%}
		}%>
			</table>
	
	
	
	<%}
}%>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
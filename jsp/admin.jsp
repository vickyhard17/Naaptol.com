<jsp:include page="/jsp/header2.jsp"></jsp:include>
<%@page import="com.naaptolOnlineShopping.bean.SellerInfoOpBean"%>


 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
 
<!--adminContainer div starts here-->
  <div class="adminContainer">

	<div class="adminHeader">
		<h1>ADMIN PANEL</h1>
		<%
			String name=(String) session.getAttribute("firstName");
			if(name!=null && !"".equals(name))
			{
		%>
		<a href="<%=request.getContextPath()%>/jsp/logout.jsp">
			<div class="userLogout"  id="userlogoutId" ">
				<p class="adminNavP">Logout</p>
			</div>
		</a>
		<div class="welcome">
		<p class="navP">Welcome  <%=name%></p>
		</div>
		<%
		}%>
		
			
		
	</div>
	<!--add product cointainer starts here-->
	<div class="ModifySeller">
		<span>Add Product</span>
		
			<form action="<%=request.getContextPath()%>/CommonController" method="post">
				<%if(request.getAttribute("isSellerAdded")!=null)
				{
					SellerInfoOpBean opBean=(SellerInfoOpBean)request.getAttribute("isSellerAdded");
					if(opBean.isStatusFlag())
					{%>
						<span><%=opBean.getStatusMessage()%></span>
				  <%}
					else
					{%>
						<span><%=opBean.getStatusMessage()%></span>
					<%}
				}
				String adminId=String.valueOf(session.getAttribute("userId"));%>
				<table>
					<tr>
						<td>Enter Seller Name</td>
						<td><input type="text" name="sellerName"></td>
					</tr>
					<tr>
						<td>Contact Number</td>
						<td><input type="text" name="sellerContact"></td>
					</tr>
					<tr>
						<td>Category</td>
						<td><input type="text" name="category"></td>
					</tr>
					<tr>
						<td>Subcategory</td>
						<td><input type="text" name="subcategory"></td>
					</tr>
					<tr>
						<td>Product Name</td>
						<td><input type="text" name="prodName"></td>
					</tr>
					<tr>
						<td>Size</td>
						<td><input type="text" name="size"></td>
					</tr>
					<tr>
						<td>Color</td>
						<td><input type="text" name="color"></td>
					</tr>
					<tr>
						<td>Image</td>
						<td><input type="text" name="image"></td>
					</tr>
					<tr>
						<td>Description</td>
						<td><textarea  name="description"></textarea></td>
					</tr>
					<tr>
						<td>Price per Unit</td>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<td>Shipping cost</td>
						<td><input type="text" name="shippingCost"></td>
					</tr>
					<tr>
						<td>Stock</td>
						<td><input type="text" name="stock"></td>
					</tr>
				</table>
				
				
				
				
				
				
				
				<input type="submit" name="action" value="addProduct">
				<input type="hidden" name="adminId" value="<%=adminId%>">

			</form>
		</div>
	</div>
	<!--add product cointainer ends here-->

	
	
</div>
<!--adminContainer div ends here-->
 
<jsp:include page="/jsp/footer.jsp"></jsp:include>
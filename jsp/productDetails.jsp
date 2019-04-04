<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.naaptolOnlineShopping.bean.ProductDescOutputBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.naaptolOnlineShopping.helper.ProductInfoHelper"%>
  <link href="<%=request.getContextPath()%>/css/registration.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css">
  
  <div class="descWrapper">
  <jsp:include page="/jsp/header.jsp"></jsp:include>
  <script type="text/javascript">
		var isSizeAvailable;
		var isColorAvailable;
 </script>
  <%
  	String productId=request.getParameter("productId");
  	ProductInfoHelper helper=new ProductInfoHelper(); 
  	ArrayList list=(ArrayList)helper.getProductDescHelper(productId);
  	
  	Set colorSet=new HashSet();
  	Set sizeSet=new HashSet();
  	if(list!=null && !list.isEmpty())
  	{
  		ProductDescOutputBean opBeaN=(ProductDescOutputBean)list.get(0);
  		
  	for(int i=0;i<list.size();i++)
	{
		ProductDescOutputBean opBeaN1=(ProductDescOutputBean)list.get(i);
		 if(opBeaN1.getColor()!=null)
		{ 
		 	colorSet.add(opBeaN1.getColor());
	    } 
		if(opBeaN1.getSize()!=null)
		{ 
			sizeSet.add(opBeaN1.getSize());
	    } 
	}
  %>
  
  <div class="descContainer">
	
				
			<div class="descImage">
				<img src="<%=request.getContextPath()%>/images/<%=opBeaN.getProductImage()%>">
			</div>
			<div class="descProdInfo">
			
				<h1 class="productName"><%=opBeaN.getProductName()%></h1></br>
				
				<div class="sellerDiv">
					<span class="descSellerName"><%=opBeaN.getSellerName()%></span></br>
				</div>
				
				<div class="adjustDesc">
					<span class="productPrice"><%=opBeaN.getProductPrice()%></span></br>
					<span class="shippingCost"><%=opBeaN.getShippingCost()%></span></br>
				</div>
				
				<div class="colorSizeDiv">
				
					<%if(opBeaN.getColor()!=null)
					{%>
						<script>
							isColorAvailable=true;
						</script>
						<span>Select Color: <span id="selectClr"></span></span>
						<%Iterator itrClr=colorSet.iterator();
						int x=0;
						while(itrClr.hasNext())
						{ 
					%>	
						<span class="prodColor" id="prodColor_<%=x%>" onclick="objPopup.showColor(this)"><%=itrClr.next()%></span>
						
					  <%x++;}
					}%>
					</br></br></br>
				  <%if(opBeaN.getSize()!=null)
					{%>
						<script>
							isSizeAvailable=true;
						</script>
					  	<span>Select Size: <span id="selectSize"></span></span>
						<%Iterator itrSize=sizeSet.iterator();
						int x=0;
						while(itrSize.hasNext())
						{
						%>
							
							<span class="prodSize" id="prodSize_<%=x%>" onclick="objPopup.showSize(this)"><%=itrSize.next()%></span>
				  		<%x++;}
				   }%>
					
					
					
				</div>
				
				<span>Call 0922-006-2000 to Order</span>&nbsp;&nbsp;&nbsp;<span class="errorClrSize"></span></br>
				
				<input type="button" class="pCart" onclick="objPopup.addToCart(<%=opBeaN.getProductId()%>)" value="Click here to Buy"></br>
			</div>
			
			<div class="prodDesc">
				<h2>Product Details of <%=opBeaN.getProductName()%></h2>
				<%=opBeaN.getDescription()%>
			</div>
			
	<% 
	
	}%>
			
			<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
	</div>
			
			
		

	
	


<jsp:include page="/jsp/footer.jsp"></jsp:include>
</div>
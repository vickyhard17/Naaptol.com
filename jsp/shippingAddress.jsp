<jsp:include page="/jsp/header2.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/loginIfNot.js"></script>
<%@page import="com.naaptolOnlineShopping.helper.CartHelper"%>
<%@page import="com.naaptolOnlineShopping.bean.UserShippingAddressOpBean"%>
<%@page import="java.util.List"%>
<%@page import="com.naaptolOnlineShopping.helper.UserShippingAddressHelper"%>
<%if(session.getAttribute("userId")!=null)
{
	Long userid=(Long)session.getAttribute("userId");
	if(session.getAttribute("productData")!=null);
	{	
		
		List list=(List)session.getAttribute("productData");
		if(list!=null && !list.isEmpty())
		{
			CartHelper helper=new CartHelper();
			helper.validateCartInsert(list,userid);
		}
		
	}
	
	UserShippingAddressHelper helper=new UserShippingAddressHelper();
	List list=helper.getShippingAddress(userid);
	if(list!=null && !list.isEmpty())
	{
%>
	
		<%for(int i=0;i<list.size();i++)
				{%>
					<div class="pastAddressDiv">
					<%UserShippingAddressOpBean opBean=(UserShippingAddressOpBean)list.get(i);%>
						<span><%=opBean.getFirstName()%></span>
						<span><%=opBean.getLastName()%></span></br>
						<Span><%=opBean.getAddress()%></Span>
						<span><%=opBean.getPincode()%></span></br>
						<span>Landmark: &nbsp;<%=opBean.getLandmark()%></span><br>
						<span>Mobile: &nbsp;+91<%=opBean.getContactNumber()%></span></br>
						<!-- <input type="submit" value="Ship To this Address"> -->
						
						<a class="addressLink" href="<%=request.getContextPath()%>/jsp/order.jsp?addressId=<%=opBean.getShippingAddressId()%>">Ship To this Address</a>
					</div>
				<%} 
		}%>	
	

	<div class="addNewAddressDiv">
	<form action="<%=request.getContextPath()%>/CommonController" method="post" onsubmit="return (objLogin.validateAddress())">
		<p id="fNameP" class="resetP"></p>
		<input type="text" id="fNameInput" class="shopAddrInp" name="firstName" size="22" placeholder="First name" maxlength="10" onblur="return objLogin.checkFname()" onkeypress="return objLogin.onlyLetters(event)" onpaste="return false"></br>
		
		<p id="lNameP" class="resetP"></p>
		<input type="text" id="lNameInput" class="shopAddrInp" name="lastName" size="22" placeholder="Last name" maxlength="10" onblur="return objLogin.checkLname()" onkeypress="return objLogin.onlyLetters(event)" onpaste="return false"></br>
		
		<p id="addressP" class="resetP"></p>
		<textarea id="addressInput" class="addrTextArea" name="completeAddress"  rows="3" placeholder="Complete Address" onblur="return objLogin.checkAddress()" onkeypress="return objLogin.spacePrevent(event)"></textarea></br>
		
		<input type="text" class="shopAddrInp" name="landmark" size="22" placeholder="Landmark (Optional)"></br>
		
		<p id="pincodeP" class="resetP"></p>
		<input type="text" id="pincode" class="shopAddrInp" name="pincode" size="22" placeholder="Pincode" maxlength="6" onblur="return objLogin.checkPincode()" onkeypress="return objLogin.onlyDigits(event)" onpaste="return false"></br>
		
		<p id="mobP" class="resetP"></p>
		<input type="text" id="mobInput" class="shopAddrInp" name="mobile" size="22" placeholder="+91 Mobile" maxlength="10" onblur="return objLogin.checkMobAddr()" onkeypress="return objLogin.onlyDigits(event)" onpaste="return false"></br>
		
		<input type="submit"  value="Add new Address" class="addNewAddr">
		
		
		<input type="hidden" name="action" value="addNewaddress">
		<input type="hidden" name="userId" value="<%=userid%>">
	</form>
	</div>
<%}
else{
	
		response.sendRedirect(request.getContextPath()+"/jsp/loginIfNot.jsp");
	
	}%>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
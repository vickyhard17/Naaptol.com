<%@page import="com.naaptolOnlineShopping.pojo.UserInfo"%>
<%@page import="com.naaptolOnlineShopping.helper.UserInfoHelper"%>
<jsp:include page="/jsp/header2.jsp"></jsp:include>

<%if(session.getAttribute("userId")!=null)
{
	Long userid=(Long)session.getAttribute("userId");
	UserInfoHelper helper=new UserInfoHelper();
	UserInfo user=(UserInfo)helper.getUserProfile(userid);
%>
<h1>My Profile <span class="myprofSpan">View and Edit Your Personal Information, Change Password and More.</span></h1>
<div class="profileContainerDiv">
<div class="profileSideNav">
	    
</div>
	<div class="profileImageContainerDiv">
	
		<div class="profileImageDiv">
			<img src="<%=request.getContextPath()%>/<%=user.getImage()%>">
		</div>
		
		<div class="uploadPhotoDiv">
			<form action="<%=request.getContextPath()%>/CommonController" method="post">
				<p class="profileP">Upload Your Photo : </p>
				<input type="file" class="uploadPhoto" accept="image/*" name="photo">
				<input type="submit" value="Upload Photo">
				<input type="hidden" name="action" value="uploadImage">
				<input type="hidden" name="userId" value="<%=userid%>">
			</form>
		</div>
	</div>
		<div class="profileTableDiv">
			<table class="profileTable">
				<tr>
					<td><strong>Email Id:</strong></td>
					<td><%=user.getEmail()%></td>
				</tr>
				<tr>
					<td><strong>Mobile Number:</strong></td>
					<td><%=user.getContactNumber()%></td>
				</tr>
				<tr>
					<td><strong>First Name:</strong></td>
					<td><%=user.getFirstName()%></td>
				</tr>
				<tr>
					<td><strong>Last Name:</strong></td>
					<td><%=user.getLastName()%></td>
				</tr>
				<tr>
					<td><strong>Gender:</strong></td>
					<td><%=user.getGender()%></td>
				</tr>
			</table>
		</div>
</div>
<%}%>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
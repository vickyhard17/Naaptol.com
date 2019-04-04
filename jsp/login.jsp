
 
		<!--Login popup starts here-->
		<div class="popup" id="popupId">
		
			<img src="<%=request.getContextPath()%>/images/closebuton.png" id="closeImage" onclick="return objPopup.hidePopup()">
			<%-- <form id="myLoginForm" action="<%=request.getContextPath()%>/CommonContoller" method="get" onSubmit="return objPopup.validateLogin()"> --%>
				
				<label class="labelClass">Email</label>
				<p id="loginEmailP" class="resetLoginP"></p>
				<input type="text" class="popupInput" id="loginEmail" name="email" onblur="return objPopup.checkLoginEmail()" onkeypress="return objPopup.avoidSpace(event)" maxlength="30">

				<label class="labelClass">Password</label>
				<p id="loginPasswdP" class="resetLoginP"></p>
				<input type="password" class="popupInput" id="loginPassword" name="password" onblur="return objPopup.checkLoginPassword()" onkeypress="return objPopup.avoidSpace(event)" onpaste="return false" maxlength="20">

			<button type="submit" class="popupButton" onclick="return objPopup.validateLogin()">CONTINUE</button>
					<br><br><br><br>
				<hr></hr>
			<span class="newUserSpan">New User? </span>
			<a href="#" class="popupLink" onclick="objPopup.hidePopup();obj.showRegForm();"><span class="signUpSpan">SIGN UP</span></a>
			<input type="hidden" name="action" value="login">
			<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
		<!-- </form> -->
	 </div>
	<!--Login popup ends here-->
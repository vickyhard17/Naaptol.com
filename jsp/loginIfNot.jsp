	<jsp:include page="/jsp/header2.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/registration.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/loginIfNot.js"></script>
  
   
 <div class="loginRegContainer"> 
<!--Login popup starts here-->
		<div class="popupIfNtLogin" id="popupId">
		
			
			<%-- <form id="myLoginForm" action="<%=request.getContextPath()%>/CommonContoller" method="get" onSubmit="return objPopup.validateLogin()"> --%>
				
				<label class="labelClass">Email</label>
				<p id="loginEmailP" class="resetLoginP1"></p>
				<input type="text" class="popupInput1" id="loginEmail" name="email" onblur="return objLogin.checkLoginEmail()" onkeypress="return objLogin.avoidSpace(event)" maxlength="30"></br>

				<label class="labelClass">Password</label>
				<p id="loginPasswdP" class="resetLoginP1"></p>
				<input type="password" class="popupInput1" id="loginPassword" name="password" onblur="return objLogin.checkLoginPassword()" onkeypress="return objLogin.avoidSpace(event)" onpaste="return false"  maxlength="20"></br>

			<button type="submit" class="popupButton1" onclick="return objLogin.validateLogin()">CONTINUE</button>
					<br><br><br><br>
				<hr></hr>
				
			<a href="#" class="popupLink1"><span class="newUserSpan">New User?</span><span class="signUpSpan1"  onclick="objLogin.showRegForm()">SIGN UP</span></a>
			<input type="hidden" name="action" value="login">
			<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
		<!-- </form> -->
	 </div>
	<!--Login popup ends here-->
	
	<!--Registration Form popup starts-->
	<div class="formDiv1">

		
		<span class="registerSuccessClass">Registration successful!! Please Login!</span>
		<h2 class="heading">REGISTRATION FORM</h2>
		
		<img src="<%=request.getContextPath()%>/images/closebuton.png" id="closeRegImage" onclick="objLogin.hideRegForm();">
		<form id="myForm" action="<%=request.getContextPath()%>/CommonController" method="post" onsubmit="return (obj.validateForm())">

			<label for="Fname"><b>FIRST NAME<i class="regI">*</i></b></label>
			<p id="fNameP" class="resetP"></p>
			<input type="text" id="fNameInput" class="commonInput" name="firstName" placeholder="Enter first name here..." maxlength="20" onblur="return objLogin.checkFname()" onkeypress="return objLogin.onlyLetters(event)" onpaste="return false">
				
					
			<label for="Lname"><b>LAST NAME<i class="regI">*</i></b></label>
			<p id="lNameP" class="resetP"></p>
			<input type="text" id="lNameInput" class="commonInput" name="lastName"placeholder="Enter last name here..." maxlength="20" onblur="return objLogin.checkLname()" onkeypress="return objLogin.onlyLetters(event)" onpaste="return false">
				
			<label for="email"><b>E-MAIL<i class="regI">*</i></b></label>
			<p id="emailP" class="resetP"></p>
			<input type="text" id="emailInput" class="commonInput" name="email" placeholder="Enter e-mail Id here..." maxlength="50" onblur="return objLogin.checkEmail()" onkeypress="return objLogin.avoidSpace(event)" onpaste="return false" >
				

			
			<label><b>GENDER<i class="regI">*</i></b></label>
			<p id="genderP" class="resetP"></p>
			<div class="genderDiv">
					MALE  <input type="radio" id="mGender" name="gender"  value="MALE" onclick="return objLogin.checkGender()">
					FEMALE<input type="radio" id="fGender" name="gender"  value="FEMALE">
			</div>
			
			<label for="mobile"><b>MOBILE NUMBER<i class="regI">*</i></b></label>
			<p id="mobP" class="resetP"></p>
			<input type="text" id="mobInput" class="commonInput" name="mobileNumber" placeholder="Enter mobile number here..." maxlength="10" onblur="return objLogin.checkMob()" onkeypress="return objLogin.onlyDigits(event)" onpaste="return false">
					
			<label for="address"><b>ADDRESS<i class="regI">*</i></b></label>
			<p id="addressP" class="resetP"></p>
			<textarea id="addressInput" class="regtextarea" name="address" placeholder="Enter your address here..." onblur="return objLogin.checkAddress()" onkeypress="return objLogin.spacePrevent(event)"></textarea> 
					
			<label for="pincode"><b>PINCODE<i class="regI">*</i></b></label>
			<p id="pincodeP" class="resetP"></p>
			<input type="text" id="pincode" class="commonInput" name="pincode" placeholder="Enter pincode here..." maxlength="6" onblur="return objLogin.checkPincode()" onkeypress="return objLogin.onlyDigits(event)" onpaste="return false">
				
					
			<label for="newPassword"><b>NEW PASSWORD<i class="regI">*</i></b></label>
			<p id="NpassP" class="resetP"></p>
			<input type="password" id="Npass" class="commonInput" placeholder="Enter new password here.[minimum length : 8 ]" maxlength="15" onblur="return objLogin.checkNewPassword()" onkeypress="return objLogin.avoidSpace(event)" onpaste="return false">
				
					
			<label for="confirmPassword"><b>CONFIRM PASSWORD<i class="regI">*</i></b></label>
			<p id="CpassP" class="resetP"></p>
			<input type="password" id="Cpass" class="commonInput" name="password" placeholder="Enter confirm password here..." maxlength="15" onblur="return objLogin.checkConfirmPassword()" onkeypress="return objLogin.avoidSpace(event)" onpaste="return false">
				
					
			<input type="submit"  value="SUBMIT" class="submitButton">
			<input type="reset"  id="resetId" value="RESET" class="resetButton" onclick="return objLogin.resetAll()">
			
			<input type="hidden" name="action" value="registration">
			<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
		</form>
	</div>
	<!--Registration Form popup ends-->
</div>
<div class="backDiv" id="backDivId" onclick="objLogin.hideRegForm()"></div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
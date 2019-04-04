<!--Registration Form popup starts-->
	<div class="formDiv">

		
		<span class="registerSuccessClass">Registration successful!! Please Login!</span>
		<h2 class="heading">REGISTRATION FORM</h2>
		<img src="<%=request.getContextPath()%>/images/closebuton.png" id="closeRegImage" onclick="obj.hideRegForm(); objPopup.hidePopup()">
		
		<form id="myForm" action="<%=request.getContextPath()%>/CommonController" method="post" onsubmit="return (obj.validateForm())">

			<label for="Fname"><b>FIRST NAME<i class="regI">*</i></b></label>
			<p id="fNameP" class="resetP"></p>
			<input type="text" id="fNameInput" class="commonInput" name="firstName" placeholder="Enter first name here..." maxlength="20" onblur="return obj.checkFname()" onkeypress="return obj.onlyLetters(event)" onpaste="return false">
				
					
			<label for="Lname"><b>LAST NAME<i class="regI">*</i></b></label>
			<p id="lNameP" class="resetP"></p>
			<input type="text" id="lNameInput" class="commonInput" name="lastName"placeholder="Enter last name here..." maxlength="20" onblur="return obj.checkLname()" onkeypress="return obj.onlyLetters(event)" onpaste="return false">
				
			<label for="email"><b>E-MAIL<i class="regI">*</i></b></label>
			<p id="emailP" class="resetP"></p>
			<input type="text" id="emailInput" class="commonInput" name="email" placeholder="Enter e-mail Id here..." maxlength="50" onblur="return obj.checkEmail()" onkeypress="return obj.avoidSpace(event)" onpaste="return false" >
				

			
			<label><b>GENDER<i class="regI">*</i></b></label>
			<p id="genderP" class="resetP"></p>
			<div class="genderDiv">
					MALE  <input type="radio" id="mGender" name="gender"  value="MALE" onclick="return obj.checkGender()">
					FEMALE<input type="radio" id="fGender" name="gender"  value="FEMALE">
			</div>
			
			<label for="mobile"><b>MOBILE NUMBER<i class="regI">*</i></b></label>
			<p id="mobP" class="resetP"></p>
			<input type="text" id="mobInput" class="commonInput" name="mobileNumber" placeholder="Enter mobile number here..." maxlength="10" onblur="return obj.checkMob()" onkeypress="return obj.onlyDigits(event)" onpaste="return false">
					
			<label for="address"><b>ADDRESS<i class="regI">*</i></b></label>
			<p id="addressP" class="resetP"></p>
			<textarea id="addressInput" class="regtextarea" name="address" placeholder="Enter your address here..." onblur="return obj.checkAddress()" onkeypress="return obj.spacePrevent(event)"></textarea> 
					
			<label for="pincode"><b>PINCODE<i class="regI">*</i></b></label>
			<p id="pincodeP" class="resetP"></p>
			<input type="text" id="pincode" class="commonInput" name="pincode" placeholder="Enter pincode here..." maxlength="6" onblur="return obj.checkPincode()" onkeypress="return obj.onlyDigits(event)" onpaste="return false">
				
					
			<label for="newPassword"><b>NEW PASSWORD<i class="regI">*</i></b></label>
			<p id="NpassP" class="resetP"></p>
			<input type="password" id="Npass" class="commonInput" placeholder="Enter new password here.[minimum length : 8 ]" maxlength="15" onblur="return obj.checkNewPassword()" onkeypress="return obj.avoidSpace(event)" onpaste="return false">
				
					
			<label for="confirmPassword"><b>CONFIRM PASSWORD<i class="regI">*</i></b></label>
			<p id="CpassP" class="resetP"></p>
			<input type="password" id="Cpass" class="commonInput" name="password" placeholder="Enter confirm password here..." maxlength="15" onblur="return obj.checkConfirmPassword()" onkeypress="return obj.avoidSpace(event)" onpaste="return false">
				
					
			<input type="submit"  value="SUBMIT" class="submitButton">
			<input type="reset"  id="resetId" value="RESET" class="resetButton" onclick="return obj.resetAll()">
			
			<input type="hidden" name="action" value="registration">
			<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>">
		</form>
	</div>
	<!--Registration Form popup ends-->
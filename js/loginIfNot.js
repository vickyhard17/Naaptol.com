var objLogin=new ObjectOriented();


function ObjectOriented()
{	
	/*Login function declaration*/
	this.isEmpty=isEmpty;
	this.checkLoginEmail=checkLoginEmail;
	this.checkLoginPassword=checkLoginPassword;
	this.validateLogin=validateLogin;
	this.avoidSpace=avoidSpace;
	this.showHomePage=showHomePage;
	this.validateUserLogin=validateUserLogin;
	this.checkMobAddr=checkMobAddr;
	this.validateAddress=validateAddress;
	this.noBack=noBack;
	

	this.checkFname=checkFname;
	this.checkLname=checkLname;
	this.checkEmail=checkEmail;
	this.checkGender=checkGender;
	this.checkMob=checkMob;
	this.checkAddress=checkAddress;
	this.checkPincode=checkPincode;
	this.checkNewPassword=checkNewPassword;
	this.checkConfirmPassword=checkConfirmPassword;
	this.isEmpty=isEmpty;
	this.onlyLetters=onlyLetters;
	this.onlyDigits=onlyDigits;
	this.eraseAll=eraseAll;
	this.resetAll=resetAll;
	this.validateForm=validateForm;
	this.showRegForm=showRegForm;
	this.hideRegForm=hideRegForm;
	this.autoRemove=autoRemove;
	this.spacePrevent=spacePrevent;
	this.checkMobByAjax=checkMobByAjax;
	this.checkEmailByAjax=checkEmailByAjax;
	
	
	var ajaxMobFlag;
	var ajaxEmailFlag;
	var nameRegEx=/^[a-zA-Z]+$/;
	var emailRegEx=/^(([a-zA-Z]+)|((([.]?[a-zA-Z0-9])+|([.]?[a-zA-Z0-9])*([.]?|[_]?)([a-zA-Z0-9][.]?)*)[a-zA-Z0-9]+))\@(([a-z0-9]+\.[a-z]{3}\.[a-z]{2})|([a-z0-9]+\.[a-z]{3})|([a-z0-9]+\.[a-z]{2}\.[a-z]{2}))$/;
	var mobRegEx=/^(^[7-9])[0-9]{9}$/;
	var pincodeRegEx=/^[0-9]{6}$/;
	var passwordRegEx=/^[a-zA-Z0-9]{8,}$/;
	
	
	
	/*show index page onclick logo*/
	function showHomePage()
	{
		var contextPath=$("#contextPath").val();
		window.location.href=contextPath+"/index.jsp";
	}
	
	/*disabling back button*/
	function noBack()
	{
		window.history.forward();
	}
	
	/*ajax call on login*/
	function validateUserLogin()
	{
		var contextPath=$("#contextPath").val();
		var email=$("#loginEmail").val();
		var passwd=$("#loginPassword").val();
		
				$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
					
					{ 	
						action : "validateUserLogin",
						emailParam :email ,
						passwdParam : passwd
					},				
					function( data )
					{
				
						var jsonData=$.parseJSON(data);
						if(!jsonData.statusFlag)
							{
							
								$("#loginPassword").css("border-color","red");
								$("#loginEmailP").text("Email password did not match").css("color","red").show();
							}
						else
							{
								window.location.href=contextPath+"/jsp/shippingAddress.jsp";
							}
					});
			
	}

	/*avoid space */
	function avoidSpace(event)
	{
		var key = event.charCode;
		if(key==32)
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	
	/*validate email*/
	function checkLoginEmail()
	{
		var email=$("#loginEmail").val();
		 
		 if(!isEmpty(email))
			{
				
				$("#loginEmail").css("border-color","red");
				$("#loginEmailP").text("Email is Required").css("color","red").show();

				return false;
			}

		if(!emailRegEx.test(email))
			{
				
				
				$("#loginEmail").css("border-color","red");
				$("#loginEmailP").text("Invalid Email Entered").css("color","red").show();
				return false;
			}
		else
			{
				$("#loginEmail").css("border-color","");
				$("#loginEmailP").hide();

				return true;
			}	
	}
	/*validate pasword*/
	function checkLoginPassword()
	{
		var newPassword=$("#loginPassword").val();
		 
		 if(!isEmpty(newPassword))
			{
				
				$("#loginPassword").css("border-color","red");
				$("#loginPasswdP").text("password is Required").css("color","red").show();

				return false;
			}
		if(!passwordRegEx.test(newPassword))
			{
				
				
				$("#loginPassword").css("border-color","red");
				$("#loginPasswdP").text("Invalid Password Entered").css("color","red").show();

				return false;
			}
		else
			{
				$("#loginPassword").css("border-color","");
				$("#loginPasswdP").hide();
				validateUserLogin();
				return true;
			}	
	}
	
	
	function validateLogin()
	{
		if(!checkLoginEmail())
		{
			return false;
		}
		
		if(!checkLoginPassword())
		{
			return false;
		}
		
		else
		{
			return true;
		}

		
	}


	
	
	

	/*Show reg form popup*/
	function showRegForm()
	{
		$(".formDiv1").show();
		$(".backDiv").css("display","block");

	}

	/*Hide reg form popup*/
	function hideRegForm()
	{
		$(".formDiv1").hide();
		$(".registerSuccessClass").hide();
		$(".backDiv").css("display","none");
		resetAll();

	}
	/*check for empty,null and undefined*/
	function isEmpty(value)
	{
		if(value!="" && value!=null && value!=undefined)
		{
			return true;
		}

		else
		{
			return false;
		}
	}
	
	/*check for characters only*/
	function onlyLetters(event)
	{
		var key = event.charCode;
        if ((key < 65 || key > 90) && (key < 97 || key > 123) || (key == 32))
		{
			return false;
		}
       
        else
        	return true;
        	
	}
	function avoidSpace(event)
	{
		var key = event.charCode;
		if(key==32)
		{
			return false;
		}
		else
		{
			return true;
		}

	}
	
	/*prevent only first space in address */
	function spacePrevent(event)
	{
		var key = event.charCode;
		var address=$("#addressInput").val();

		if(key==32 && !isEmpty(address))
		{
			return false;
		}
		else
		{
			return true;
		}

	}
	/* using this function user can only enter numeric values */
	function onlyDigits(event)
	{
		var key = event.charCode;
        if (key < 48 || key > 57)
         
			return false;
		else
            return true;
	}
	/*First Name validation*/
	function checkFname()
	{		
		 var firstName=$("#fNameInput").val();
		 
		 if(!isEmpty(firstName))
			{
				
				$("#fNameInput").css("border-color","red");
				$("#fNameP").text("First Name Required").css("color","red").show();

				return false;
			}

		 if(!nameRegEx.test(firstName))
			{
				
				
				$("#fNameInput").css("border-color","red");
				$("#fNameP").text("Invalid first name entered").css("color","red").show();
				return false;
			}
		 
		else
			{
				$("#fNameInput").css("border-color","");
				$("#fNameP").hide();

				return true;
			}
		
	}

	/*Last Name validation*/
	function checkLname()
	{		
		 var lastName=$("#lNameInput").val();
		 
		 if(!isEmpty(lastName))
			{
				
				$("#lNameInput").css("border-color","red");
				$("#lNameP").text("Last Name Required").css("color","red").show();

				return false;
			}

		 if(!nameRegEx.test(lastName))
			{
				
				
				$("#lNameInput").css("border-color","red");
				$("#lNameP").text("Invalid last name entered").css("color","red").show();
				return false;
			}
		else
			{
				$("#lNameInput").css("border-color","");
				$("#lNameP").hide();

				return true;
			}
		
	}
	/*email validation*/
	function checkEmail()
	{
		var email=$("#emailInput").val();
		 
		 if(!isEmpty(email))
			{
				
				$("#emailInput").css("border-color","red");
				$("#emailP").text("Email is Required").css("color","red").show();

				return false;
			}

		if(!emailRegEx.test(email))
			{
				
				
				$("#emailInput").css("border-color","red");
				$("#emailP").text("Invalid Email Entered").css("color","red").show();
				return false;
			}
		else
			{
				$("#emailInput").css("border-color","");
				$("#emailP").hide();
				obj.checkEmailByAjax();
				if(ajaxEmailFlag)
					{
						return true;
					}
				else
				return false;
			}	
	}
	/*verify email through ajax call*/
	function checkEmailByAjax()
	{
		var contextPath=$("#contextPath").val();
		var email=$("#emailInput").val();
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
				
			{ 	
				action : "validateEmail",
				emailParam : email
			},				
			function( data ) {
		
			var jsonData=$.parseJSON(data);
			if(!jsonData.statusFlag)
				{
					$("#emailInput").css("border-color","red");
					$("#emailP").text("Email already registered").css("color","red").show();
					ajaxEmailFlag=false;
				}
			else
				{
					ajaxEmailFlag=true;
				}
			});
		
	}
	/*Gender function starts here*/
	function checkGender()
	{
		 var Value = $("input[name='gender']:checked").val();	
		 
			if(!isEmpty(Value))
			{
				
				$(".genderDiv").css("border-color","red");
				$("#genderP").text("Select your gender").css("color","red").show();

				return false;
			}
				
		else
			{
				$(".genderDiv").css("border-color","");
				$("#genderP").hide();

				return true;
			}
				
	}
	
	
	/*Mobile Number function*/
	function checkMob()
	{
		var mob=$("#mobInput").val();
		 
		 if(!isEmpty(mob))
			{
				
				$("#mobInput").css("border-color","red");
				$("#mobP").text("Mobile Number is Required").css("color","red").show();

				return false;
			}

		 if(!mobRegEx.test(mob))
			{
				
				
				$("#mobInput").css("border-color","red");
				$("#mobP").text("Invalid Mobile no. Entered").css("color","red").show();

				return false;
			}
		else
			{
				$("#mobInput").css("border-color","");
				$("#mobP").hide();
				obj.checkMobByAjax();
				if(ajaxMobFlag)
				{
					return true;
				}
				else
					return false;
			}	
	}
	/*ajax call to verify mob no.*/
	function checkMobByAjax()
	{
		var contextPath=$("#contextPath").val();
		var mobNo=$("#mobInput").val();
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
				
			{ 	
				action : "validateMobile",
				mobNoParam : mobNo
			},				
			function( data ) {
			var jsonData=$.parseJSON(data);
			if(!jsonData.statusFlag)
				{
					$("#mobInput").css("border-color","red");
					$("#mobP").text("Mobile number already registered").css("color","red").show();
					ajaxMobFlag=false;
				}
			else
				{
					ajaxMobFlag=true;
				}
			});
		
	}
	function checkMobAddr()
	{
		var mob=$("#mobInput").val();
		 
		 if(!isEmpty(mob))
			{
				
				$("#mobInput").css("border-color","red");
				$("#mobP").text("Mobile Number is Required").css("color","red").show();

				return false;
			}

		 if(!mobRegEx.test(mob))
			{
				
				
				$("#mobInput").css("border-color","red");
				$("#mobP").text("Invalid Mobile no. Entered").css("color","red").show();

				return false;
			}
		else
			{
				$("#mobInput").css("border-color","");
				$("#mobP").hide();
				
				return true;
			}	
	}
	/*address function starts here*/
	function checkAddress()
	{
		var address=$("#addressInput").val();
		 
		 if(!isEmpty(address))
			{
				
				$("#addressInput").css("border-color","red");
				$("#addressP").text("Address is Required").css("color","red").show();

				return false;
			}
		else
			{
				$("#addressInput").css("border-color","");
				$("#addressP").hide();

				return true;
			}	
	}
	
	
	/*Pincode function starts here*/
	function checkPincode()
	{
		var pin=$("#pincode").val();
		 
		 if(!isEmpty(pin))
			{
				
				$("#pincode").css("border-color","red");
				$("#pincodeP").text("Pincode is Required").css("color","red").show();

				return false;
			}
		if(!pincodeRegEx.test(pin))
			{
				
				
				$("#pincode").css("border-color","red");
				$("#pincodeP").text("Invalid Pincode Entered").css("color","red").show();

				return false;
			}
		 
		else
			{
				$("#pincode").css("border-color","");
				$("#pincodeP").hide();

				return true;
			}	
	}
	
	/*Check new password function*/
	function checkNewPassword()
	{
		var newPassword=$("#Npass").val();
		 
		 if(!isEmpty(newPassword))
			{
				
				$("#Npass").css("border-color","red");
				$("#NpassP").text("New password is Required").css("color","red").show();

				return false;
			}
		if(!passwordRegEx.test(newPassword))
			{
				
				
				$("#Npass").css("border-color","red");
				$("#NpassP").text("Invalid new Password Entered").css("color","red").show();

				return false;
			}
		else
			{
				$("#Npass").css("border-color","");
				$("#NpassP").hide();

				return true;
			}	
	}
	/*Check confirm password function*/
	function checkConfirmPassword()
	{
		var newPassword=$("#Npass").val();
		var confirmPassword=$("#Cpass").val();
		 
		 if(!isEmpty(confirmPassword))
			{
				
				$("#Cpass").css("border-color","red");
				$("#CpassP").text("confirm password is Required").css("color","red").show();

				return false;
			}
		if(!passwordRegEx.test(confirmPassword))
			{
				
				
				$("#Cpass").css("border-color","red");
				$("#CpassP").text("Invalid confirm password Entered").css("color","red").show();

				return false;
			}

		if(newPassword!=confirmPassword)
			{
			
				$("#Cpass,#Npass").css("border-color","red");
				$("#CpassP,#NpassP").text("New and confirm Password must be same").css("color","red").show();

				return false;
			}
		 
		else
			{
				$("#Cpass").css("border-color","");
				$("#CpassP").hide();

				return true;
			}	
	}

	
	/*Clear all values on submit*/
	function eraseAll()
	{

		/*$("#fNameInput").val("");

		$("#lNameInput").val("");

		$("#emailInput").val("");

		$("input[name='gender']:checked").prop("checked",false);

		$(".hobbies:checked").each(function() {
			$(".hobbies:checked").prop("checked",false);
		});

		$("#dobInput").val("");
			
		$("#mobInput").val("");

		$("#addressInput").val("");
			
		$("#citySelect").val("");

		$("#pincode").val("");

		$("#Npass").val("");

		$("#Cpass").val("");

		return true;*/
	}
	/*Reset form and P element*/
	function resetAll()
	{
		$("#myForm")[0].reset();
		$(".resetP").hide();
		$("input,.error").css("borderColor","")
		
	}

	/*auto remove after 5 seconds*/
	function autoRemove()
	{
		$(".registerSuccessClass").show();

		setTimeout(function(){
			hideRegForm();
		},5000);
	
	}

	/*Validate function*/
	function validateForm()
	{
		if(!checkFname())
		{
			return false;
		}
		
		if(!checkLname())
		{
			return false;
		}
		
		var email=$("#emailInput").val();
		if(!isEmpty(email))
		{
				
			$("#emailInput").css("border-color","red");
			$("#emailP").text("Email is Required").css("color","red").show();

			return false;
		}
		 
		if(ajaxEmailFlag)
		{
			if(!checkEmail())
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		if(!checkGender())
		{
			return false;
		}
		
		var mob=$("#mobInput").val();
		if(!isEmpty(mob))
		{
				
			$("#mobInput").css("border-color","red");
			$("#mobP").text("Mobile Number is Required").css("color","red").show();

			return false;
		}
		if(ajaxMobFlag)
		{
			if(!checkMob())
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		if(!checkAddress())
		{
			return false;
		}
		
		if(!checkPincode())
		{
			return false;
		}
		if(!checkNewPassword())
		{
			return false;
		}
		if(!checkConfirmPassword())
		{
			return false;
		}
		

		else
		{
			
			autoRemove();

			return true;
		}
			

	}
	function validateAddress()
	{
		if(!checkFname())
		{
			return false;
		}
		
		if(!checkLname())
		{
			return false;
		}
		
		if(!checkAddress())
		{
			return false;
		}
		
		if(!checkPincode())
		{
			return false;
		}
		if(!checkMobAddr())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	

}
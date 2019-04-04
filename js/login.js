var objPopup=new ObjectOriented();


function ObjectOriented()
{	
	/*Login function declaration*/
	this.showPopup=showPopup;
	this.hidePopup=hidePopup;
	this.isEmpty=isEmpty;
	this.checkLoginEmail=checkLoginEmail;
	this.checkLoginPassword=checkLoginPassword;
	this.resetAll=resetAll;
	this.validateLogin=validateLogin;
	this.avoidSpace=avoidSpace;
	
	this.validateUserLogin=validateUserLogin;
	this.showHomePage=showHomePage;
	
	
	
	this.showCategories=showCategories;
	this.hideCategories=hideCategories;
	this.showSubCategories=showSubCategories;
	this.showSubcategoryProducts=showSubcategoryProducts;
	this.showCategoryDiv=showCategoryDiv;
	this.displayAllCategoryData=displayAllCategoryData;
	this.getAllProducts=getAllProducts;
	this.autoComplete=autoComplete;
	this.showCart=showCart;
	this.showSize=showSize;
	this.showColor=showColor;
	this.addToCart = addToCart;
	this.updateQuanity=updateQuanity;
	this.removeFromCart=removeFromCart;
	this.cancelOrder=cancelOrder;
	
	
	var emailRegEx=/^(([a-zA-Z]+)|((([.]?[a-zA-Z0-9])+|([.]?[a-zA-Z0-9])*([.]?|[_]?)([a-zA-Z0-9][.]?)*)[a-zA-Z0-9]+))\@(([a-z0-9]+\.[a-z]{3}\.[a-z]{2})|([a-z0-9]+\.[a-z]{3})|([a-z0-9]+\.[a-z]{2}\.[a-z]{2}))$/;
	var passwordRegEx=/^[a-zA-Z0-9]{8,}$/;

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
								window.location.reload();
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
	/*show popup function*/
	function showPopup()
	{
		
		$("#popupId").show();
		$(".backDiv").css("display","block");
	}
	/*hide popup function*/
	function hidePopup()
	{
		$("#popupId").hide();
		/*$(".cartContainer").css("display","none")*/;
		$(".backDiv").css("display","none");
		resetAll();
		
	}


	
	
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
	/*Reset all fields*/
	function resetAll()
	{
		$("#loginEmail").val("");
		$("#loginPassword").val("");
		$(".resetLoginP").hide();
		$(".popupInput").css("borderColor","")
		
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
	/*show home page onclick logo*/
	function showHomePage()
	{
		var contextPath=$("#contextPath").val();
		window.location.href=contextPath+"/index.jsp";
	}
	
	/*=======================================Ajax calls for getting products and category lists=========================*/
	/*call to home page onclick logo */
	
	
	function hideCategories()
	{
		$(".categoryListDiv").hide();
		$(".subCatListDiv").hide();
	}
	function showCategoryDiv()
	{
		$(".categoryListDiv").show();
		$(".subCatListDiv").show();
	}
	/*ajax call for categories*/
	function showCategories()
	{
		$(".categoryListDiv").show();
		var contextPath=$("#contextPath").val();
		
		
				$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
					
					{ 	
						action : "getCategories"
						
					},				
					function( data )
					{
						$(".categoryListDiv").html("");
						var jsonData=$.parseJSON(data);
						
						if(jsonData.statusFlag)
							{
						
							var htm="";
							htm+="<ul>"
							for(var i=0;i<jsonData.categoryList.length;i++)
								{
									var x=jsonData.categoryList[i][0];
									//var categoryListData=jsonData.categoryList[i];
									for(var j=1;j<jsonData.categoryList[i].length;j++)
										{
												
												
												htm+="<li id="+x+" class='categoryList' onmouseover='objPopup.showSubCategories(this);' onclick='objPopup.displayAllCategoryData(this)'>"+jsonData.categoryList[i][j]+"</li>";
												
												
											
										}
									
								}
								htm+="</ul>"
								$(".categoryListDiv").append(htm);
							}
						
					});
		}
				
	/*ajax call for subCategories*/
	function showSubCategories(id)
	{
		var categoryId=$(id).attr("id");
		var contextPath=$("#contextPath").val();
		
		
				$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
					
					{ 	
						action : "getSubCategories",
						categoryParam :categoryId
						
					},				
					function( data )
					{
						$(".subCatListDiv").html("");
						var jsonData=$.parseJSON(data);
						
						if(jsonData.statusFlag)
							{
							
							var htm="";
							htm+="<ul>"
							for(var i=0;i<jsonData.categoryList.length;i++)
								{
								var x=jsonData.categoryList[i][0];
									//var categoryListData=jsonData.categoryList[i];
									for(var j=1;j<jsonData.categoryList[i].length;j++)
										{
												
												
												htm+="<li id="+x+" class='subcategoryList' onclick='objPopup.showSubcategoryProducts(this)'>"+jsonData.categoryList[i][j]+"</li>";
												
											
											
										}
									
								}
								htm+="</ul>"
								$(".subCatListDiv").append(htm);
							}
						
					});
		}
	
	/*ajax call show products onclick subcategory*/
	function showSubcategoryProducts(subcatId)
	{
		objPopup.hideCategories();
		var subCategoryId=$(subcatId).attr("id");
		var contextPath=$("#contextPath").val();
		
		
				$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
					
					{ 	
						action : "getSubcategoryProducts",
						subCategoryIdParam :subCategoryId
						
					},				
					function( data )
					{
						$(".container").html("");
						var jsonData=$.parseJSON(data);
						
						if(jsonData.statusFlag)
							{
						
							var htm="";
							
							for(var i=0;i<jsonData.productList.length;i++)
								{
	
									htm+="<div id=productDiv_"+i+" class='productDiv'>";
									
									htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+" id=quickView_"+i+" class='quickView'>Qick View</a>";

									
									htm+="<div id=productImage_"+i+" class='productImage'>";
									htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+"><img src='"+contextPath+"/images/"+jsonData.productList[i][3]+"' class='imaaage' alt='product'></a>";
									htm+="</div>";
									
									htm+="<div id=nameDiv_"+i+" class='nameDiv'>";
									htm+=jsonData.productList[i][1];
									htm+="<div class='clr'></div>";
									htm+="</div>";
									
									htm+="<div id=priceDiv_"+i+" class='priceDiv'>";
									htm+="<p id=price_"+i+" class='boldPrice'>Rs"+jsonData.productList[i][2]+"</p>";
									htm+="<div class='clr'></div>";
									htm+="</div>";
										
									htm+="<div class='clr'></div>";
									
									htm+="</div>";
										
								}
								
								$(".container").append(htm);
							}
						
					});
		}
	/*ajax call show products onclick category*/
	function displayAllCategoryData(id)
	{
		objPopup.hideCategories();
		var categoryId=$(id).attr("id");
		var contextPath=$("#contextPath").val();
		
		
				$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
					
					{ 	
						action : "getAllCategoryData",
						categoryIdParam :categoryId
						
					},				
					function( data )
					{
						$(".container").html("");
						var jsonData=$.parseJSON(data);
						
						if(jsonData.statusFlag)
							{
						
							var htm="";
							
							for(var i=0;i<jsonData.productList.length;i++)
								{
	
									htm+="<div id=productDiv_"+i+" class='productDiv'>";
									
									htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+" id=quickView_"+i+" class='quickView'>Qick View</a>";

									
									htm+="<div id=productImage_"+i+" class='productImage'>";
									htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+"><img src='"+contextPath+"/images/"+jsonData.productList[i][3]+"' class='imaaage' alt='product'></a>";
									htm+="</div>";
									
									htm+="<div id=nameDiv_"+i+" class='nameDiv'>";
									htm+=jsonData.productList[i][1];
									htm+="<div class='clr'></div>";
									htm+="</div>";
									
									htm+="<div id=priceDiv_"+i+" class='priceDiv'>";
									htm+="<p id=price_"+i+" class='boldPrice'>Rs"+jsonData.productList[i][2]+"</p>";
									htm+="<div class='clr'></div>";
									htm+="</div>";
										
									htm+="<div class='clr'></div>";
									
									htm+="</div>";
										
								}
								
								$(".container").append(htm);
							}
						
					});
	}
	/*getting all products onload homepage*/
	function getAllProducts()
	{
		var contextPath=$("#contextPath").val();
		
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "getAllProducts"
			},				
			function( data )
			{
				$(".container").html("");
				var jsonData=$.parseJSON(data);
				
				if(jsonData.statusFlag)
					{
				
					var htm="";
					
					for(var i=0;i<jsonData.productList.length;i++)
						{
							htm+="<div id=productDiv_"+i+" class='productDiv'>";
							
							
							htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+" id=quickView_"+i+" class='quickView'>Qick View</a>";
							
							htm+="<div id=productImgDiv_"+i+" class='productImage'>";
							htm+="<a href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productList[i][0]+"><img  src='"+contextPath+"/images/"+jsonData.productList[i][3]+"' class='imaaage' alt='product'></a>";
							htm+="</div>";
							
							htm+="<div id=nameDiv_"+i+" class='nameDiv'>";
							htm+=jsonData.productList[i][1];
							htm+="<div class='clr'></div>";
							htm+="</div>";
							
							htm+="<div id=priceDiv_"+i+" class='priceDiv'>";
							htm+="<p id=price_"+i+" class='boldPrice'>Rs "+jsonData.productList[i][2]+"</p>";
							htm+="<div class='clr'></div>";
							htm+="</div>";
								
							htm+="<div class='clr'></div>";
							
							htm+="</div>";
								
						}
						
						$(".container").append(htm);
					}
				
			});
	}
	/*function to autoComplete user search*/
	function autoComplete(value)
	{
		var contextPath=$("#contextPath").val();
		var value=$(value).val();
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "autoComplete",
				valueParam :value
			},				
			function( data )
			{
				$(".autocompleteDiv").html("")
				var jsonData=$.parseJSON(data);
				
				if(jsonData.statusFlag)
					{
					var htm="";
						htm+="<ul>"
						for(var i=0;i<jsonData.productNameList.length;i++)
							{
							
								htm+="<a class='autoCompleteClass' href="+contextPath+'/jsp/productDetails.jsp?productId='+jsonData.productNameList[i][0]+"><li id="+jsonData.productNameList[i][0]+" class='autocompleteList'>"+jsonData.productNameList[i][1]+"</li></a>";
												
							}
							htm+="</ul>"
							$(".autocompleteDiv").append(htm);
							
						}
				
			});
	}
	
	
	/*dispaly cart onclick cart div*/
	function showCart()
	{
		var contextPath=$("#contextPath").val();
		window.location.href=contextPath+"/jsp/cart.jsp";
		
	}
	
	/*show size on select on desc page*/
	function showSize(value)
	{
		var result=$(value).text();
		$("#selectSize").html("");
		$("#selectSize").append(result);
		var id=$(value).attr("id");
		$("#"+id).css("background","#666");
		$("#"+id).css("color","white");
		
		
	}
	/*show color on select on desc page*/
	function showColor(value)
	{
		var result=$(value).text();
		$("#selectClr").html("");
		$("#selectClr").append(result);
		var id=$(value).attr("id");
		$("#"+id).css("background","#666");
		$("#"+id).css("color","white");
	}
	/*add to cart from desc page*/
	function addToCart(productId)
	{	
		if(isColorAvailable!=undefined && isColorAvailable==true)
		{
			var color=$("#selectClr").html();
			if(color==undefined || color==null || color=="")
				{	
					$(".errorClrSize").css("display","block");
					$(".errorClrSize").html("please select color");
					return;
				}
		}
		if(isSizeAvailable!=undefined && isSizeAvailable==true)
		{
			var size=$("#selectSize").html();
			if(size==undefined || size==null || size=="")
				{
					$(".errorClrSize").css("display","block");
					$(".errorClrSize").html("please select size");
					return;
				}
		}
		
	
		var contextPath=$("#contextPath").val();
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "addToCart",
				sizeParam : size,
				colorParam : color,
				productParam : productId
			},				
			function( data )
			{
				
				window.location.href=contextPath+"/jsp/cart.jsp";
				
			});
	}
	
	/*update quantity in cart ajax call*/
	
	function updateQuanity(sellerDetailsId,obj)
	{
		var contextPath=$("#contextPath").val();
		var selectedValue=parseInt(obj.value);
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "updateQuantity",
				selectedValueParam :selectedValue,
				sellerDetailsIdParam : sellerDetailsId
			},				
			function( data )
			{
				window.location.reload();
				
			});
	}
	
	/*ajx call to remove item from cart*/
	function removeFromCart(sellerDetailsId)
	{
		var contextPath=$("#contextPath").val();
		
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "removeCartItem",
				
				sellerDetailsIdParam : sellerDetailsId
			},				
			function( data )
			{
				window.location.reload();
				
			});
		
	}
	
	/*ajax call to cancel order*/
	function cancelOrder(orderId)
	{
		var contextPath=$("#contextPath").val();
		
		
		$.post(contextPath+"/ajaxHandler/ajaxHandler.jsp",
			
			{ 	
				action : "cancelOrder",
				
				orderIdParam : orderId
			},				
			function( data )
			{
				
				window.location.reload();
				
			});
		
	}
}
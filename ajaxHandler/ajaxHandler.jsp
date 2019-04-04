
<%@page import="org.hibernate.Session"%>
<%@page import="com.naaptolOnlineShopping.handler.CommonAjaxHandler"%>
<%!
	public String execute(HttpServletRequest request,HttpServletResponse response)
	{
		String result=null;
		String action=request.getParameter("action");
		
		if(action.equals("validateMobile"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.checkMobileNumber(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("validateEmail"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.checkEmail(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("validateUserLogin"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateUserLogin(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("getCategories"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateCategories(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("getSubCategories"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateSubCategories(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("getSubcategoryProducts"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateSubCategoriesProducts(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("getAllCategoryData"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateAllCategoryData(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("getAllProducts"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateAllProductsData(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("autoComplete"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateAutocomplete(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("addToCart"))
		{
			CommonAjaxHandler handler=new CommonAjaxHandler();
			try
			{
				 result=handler.validateAddToCart(request,response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
			
	
		
	else if(action.equals("updateQuantity"))
	{
		CommonAjaxHandler handler=new CommonAjaxHandler();
		try
		{
			 handler.validateUpdateQuantity(request,response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	else if(action.equals("removeCartItem"))
	{
		CommonAjaxHandler handler=new CommonAjaxHandler();
		try
		{
			 handler.removeItemFromCart(request,response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	else if(action.equals("cancelOrder"))
	{
		CommonAjaxHandler handler=new CommonAjaxHandler();
		try
		{
			 handler.cancelOrder(request,response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
		return result;

}
%>
<%=execute(request,response)%>

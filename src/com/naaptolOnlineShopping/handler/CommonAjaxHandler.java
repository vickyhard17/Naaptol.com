package com.naaptolOnlineShopping.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.naaptolOnlineShopping.bean.AutocompleteOutputBean;
import com.naaptolOnlineShopping.bean.CategoryOutputBean;
import com.naaptolOnlineShopping.bean.OrdersOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.bean.ProductInfoOutputBean;
import com.naaptolOnlineShopping.bean.UserInfoInputBean;
import com.naaptolOnlineShopping.bean.UserInfoOutputBean;
import com.naaptolOnlineShopping.helper.CartHelper;
import com.naaptolOnlineShopping.helper.CategoriesHelper;
import com.naaptolOnlineShopping.helper.OrderDetailsHelper;
import com.naaptolOnlineShopping.helper.ProductInfoHelper;
import com.naaptolOnlineShopping.helper.SellerDetailsHelper;
import com.naaptolOnlineShopping.helper.UserInfoHelper;



public class CommonAjaxHandler
{
	List list=new ArrayList();
	
	public String toJSON(Object jsonObject)
	{
		Gson gson= new GsonBuilder().create();
		return gson.toJson(jsonObject);
	}
	
	public String checkMobileNumber(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		UserInfoInputBean ipBean=new UserInfoInputBean();
		ipBean.setContactNumber(request.getParameter("mobNoParam"));
		
		UserInfoHelper helper=new UserInfoHelper();
		
		UserInfoOutputBean outputBean=helper.validateMobNo(ipBean);
		
		return toJSON(outputBean);
		
	}
	
	public String checkEmail(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		UserInfoInputBean ipBean=new UserInfoInputBean();
		ipBean.setEmail(request.getParameter("emailParam"));
		
		UserInfoHelper helper=new UserInfoHelper();
		
		UserInfoOutputBean outputBean=helper.validateEmail(ipBean);
		
		return toJSON(outputBean);
		
	}
	public String validateUserLogin(HttpServletRequest request,HttpServletResponse response)
	{
		UserInfoHelper helper=new UserInfoHelper();
		String email=request.getParameter("emailParam");
		String password=request.getParameter("passwdParam");
		
		
		UserInfoOutputBean opBean=helper.validateUserHelper(email,password);
		
		if(opBean.isStatusFlag())
		{
			opBean.setStatusMessage("valid user");
			HttpSession session=request.getSession(false);
			session.setAttribute("userId", opBean.getUserid());
			session.setAttribute("firstName",opBean.getFirstName());
			session.setAttribute("lastName",opBean.getLastName());
			session.setAttribute("isAdmin", opBean.getIsAdmin());
			
			/*
			CartHelper cartHelp=new CartHelper();
			List list=cartHelp.getUserCart((Long)opBean.getUserid());
			
			if(list!=null && !list.isEmpty())
			{
				session.setAttribute("productData",list);
			}*/
					
		}
		else
		{
			opBean.setStatusMessage("invalid credentials entered");
		}
			
		return toJSON(opBean);
		
	}
	public String validateCategories(HttpServletRequest request,HttpServletResponse response)
	{
		CategoriesHelper helper=new CategoriesHelper();
		CategoryOutputBean opBean=new CategoryOutputBean();
		
		opBean=helper.getCategoriesHelper();
		
		return toJSON(opBean);
		
	}
	public String validateSubCategories(HttpServletRequest request,HttpServletResponse response)
	{
		CategoriesHelper helper=new CategoriesHelper();
		String id=request.getParameter("categoryParam");
		
		CategoryOutputBean opBean=new CategoryOutputBean();
		
		opBean=helper.getSubCategoriesHelper(id);
		
		return toJSON(opBean);
		
	}
	public String validateSubCategoriesProducts(HttpServletRequest request,HttpServletResponse response)
	{
		ProductInfoHelper helper=new ProductInfoHelper(); 
		String id=request.getParameter("subCategoryIdParam");
		
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		
		opBean=helper.getProductsHelper(id);
		
		return toJSON(opBean);
		
	}
	public String validateAllCategoryData(HttpServletRequest request,HttpServletResponse response)
	{
		
		
		
		ProductInfoHelper helper=new ProductInfoHelper(); 
		String id=request.getParameter("categoryIdParam");
		
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		
		opBean=helper.getAllCategoryProductsHelper(id);
		
		
		
		return toJSON(opBean);
		
	}
	public String validateAllProductsData(HttpServletRequest request,HttpServletResponse response)
	{
		ProductInfoHelper helper=new ProductInfoHelper(); 
		
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		
		opBean=helper.getProductsHelper();
		
		return toJSON(opBean);
		
	}
	public String validateAutocomplete(HttpServletRequest request,HttpServletResponse response)
	{
		ProductInfoHelper helper=new ProductInfoHelper(); 
		String value=request.getParameter("valueParam");
		AutocompleteOutputBean opBean=new AutocompleteOutputBean();
		
		opBean=helper.getProductNames(value);
		
		return toJSON(opBean);
		
	}
	public String validateAddToCart(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession(false);
		
			String productId=request.getParameter("productParam");
			String size=request.getParameter("sizeParam");
			String color=request.getParameter("colorParam");
			
			
			
			SellerDetailsHelper helper=new SellerDetailsHelper();
			ProductDescOutputBean opBean=helper.getSellerDetailsIdHelper(productId,size,color);
			
			
			
			
			if(opBean!=null)
		  	{
				List productDataList =(List) session.getAttribute("productData");
				
				if(productDataList!=null)
				{	
					List list=new ArrayList<>();
					boolean isAddToCart=true;
					
					for(int i=0;i<productDataList.size();i++)
					{
						ProductDescOutputBean opBean1=(ProductDescOutputBean) productDataList.get(i);
						if(opBean1.getProductId().equals(opBean.getProductId()))
						{
							list.add(opBean1);
						}						
					}
					if(list!=null && !list.isEmpty())
					{
						for(int i=0;i<list.size();i++)
						{
							ProductDescOutputBean opBean1=(ProductDescOutputBean) list.get(i);
							if(opBean1.getSize()==null && opBean.getSize()==null && opBean1.getColor()==null && opBean.getColor()==null)	
							{
								isAddToCart=false;
								break;
							}
							else if(opBean1.getSize()!=null && opBean.getSize()!=null && opBean1.getColor()==null && opBean.getColor()==null)
							{
								if(opBean1.getSize().equals(opBean.getSize()))
								{
									isAddToCart=false;
									break;
								}
							}
							else if(opBean1.getColor()!=null && opBean.getColor()!=null && opBean1.getSize()==null && opBean.getSize()==null)
							{
								if(opBean1.getColor().equals(opBean.getColor()))
								{
									isAddToCart=false;
									break;
								}
							}
							else if(opBean1.getSize()!=null && opBean.getSize()!=null && opBean1.getColor()!=null && opBean.getColor()!=null)
							{
								if(opBean1.getSize().equals(opBean.getSize()) && opBean1.getColor().equals(opBean.getColor()))
								{
									isAddToCart=false;
									break;
								}
							}
							
						}
					}

					if(isAddToCart)
					{
						productDataList.add(opBean);
						session.setAttribute("productData",productDataList);
					}
					
					
				}
				else
				{
					list.add(opBean);
					session.setAttribute("productData",list);
				}
				
				
		  		
		  	}
			return toJSON(list);
			
	}
	public String validateUpdateQuantity(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession(false);
		
		List productDataList =(List) session.getAttribute("productData");
		
		String quantity=request.getParameter("selectedValueParam");
		String id=request.getParameter("sellerDetailsIdParam");
		
		if(productDataList!=null)
		{	
			
			ProductDescOutputBean opBean=null;
			for(int i=0;i<productDataList.size();i++)
			{
				opBean=(ProductDescOutputBean) productDataList.get(i);
				
				String sellerId=String.valueOf(opBean.getSellerDetailId());
				if(sellerId.equals(id))
				{
					opBean.setQuantity(new Long(quantity));
					productDataList.add(opBean);
					productDataList.remove(i);
				}						
			}
			
			
			session.setAttribute("productData",productDataList);
		}
		return null;
	}
	
	public String removeItemFromCart(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession(false);
		
		List productDataList =(List) session.getAttribute("productData");
		String id=request.getParameter("sellerDetailsIdParam");
		
		if(productDataList!=null)
		{	
			
			ProductDescOutputBean opBean=null;
			for(int i=0;i<productDataList.size();i++)
			{
				opBean=(ProductDescOutputBean) productDataList.get(i);
				
				String sellerId=String.valueOf(opBean.getSellerDetailId());
				if(sellerId.equals(id))
				{
					productDataList.remove(i);
				}						
			}
			
			if(productDataList!=null && !productDataList.isEmpty())
			{
				session.setAttribute("productData",productDataList);
			}
			else
			{
				session.removeAttribute("productData");
			}
		}
		return null;
	}
	public String cancelOrder(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession(false);
		
		
		String id=request.getParameter("orderIdParam");
		
		OrderDetailsHelper helper=new OrderDetailsHelper();
		OrdersOutputBean opBean=helper.cancelOrderHelper(id);
		
		
		return toJSON(opBean);
	}
	
	
}

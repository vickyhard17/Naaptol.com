package com.naaptolOnlineShopping.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naaptolOnlineShopping.bean.AddProductInputBean;
import com.naaptolOnlineShopping.bean.OrderDetailsOutputBean;
import com.naaptolOnlineShopping.bean.SellerInfoOpBean;
import com.naaptolOnlineShopping.bean.UserInfoInputBean;
import com.naaptolOnlineShopping.bean.UserInfoOutputBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressIpBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.helper.OrderDetailsHelper;
import com.naaptolOnlineShopping.helper.SellerInfoHelper;
import com.naaptolOnlineShopping.helper.UserInfoHelper;
import com.naaptolOnlineShopping.helper.UserShippingAddressHelper;
import com.naaptolOnlineShopping.util.MyConnection;
import com.sun.jmx.snmp.UserAcl;




public class CommonController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    
    public CommonController()
    {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException
	{
		new MyConnection();
	}

	
	public void destroy()
	{
		MyConnection.releaseConnection();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String actionJsp=request.getParameter("action");
		
		if(actionJsp!=null && !"".equals(actionJsp))
		{
			if("registration".equals(actionJsp))
			{
				UserInfoInputBean ipBean=new UserInfoInputBean();
				
				ipBean.setFirstName(request.getParameter("firstName"));
				ipBean.setLastName(request.getParameter("lastName"));
				ipBean.setEmail(request.getParameter("email"));
				ipBean.setGender(request.getParameter("gender"));
				ipBean.setContactNumber(request.getParameter("mobileNumber"));
				ipBean.setAddress(request.getParameter("address"));
				ipBean.setPincode(request.getParameter("pincode"));
				ipBean.setPassword(request.getParameter("password"));
				
				
				
				UserInfoHelper helper=new UserInfoHelper();
				UserInfoOutputBean opBean;
				
					opBean = helper.validateRegistration(ipBean);
					request.setAttribute("message", opBean);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					
				
			}
			
			else if("placeOrder".equals(actionJsp))
			{
				HttpSession session=request.getSession(false);
		
				List list=(List)session.getAttribute("productData");
				Long userId=(Long) session.getAttribute("userId");	
				String shippingAddressId=request.getParameter("addressId");
				String cartValue=request.getParameter("cartValue");
				
				OrderDetailsHelper helper=new OrderDetailsHelper();
				OrderDetailsOutputBean opBean=helper.insertOrderDetails(list, cartValue,userId,shippingAddressId);
				
				if(opBean.isStatusFlag())
				{
					
					response.sendRedirect(request.getContextPath()+"/jsp/orderSuccess.jsp");
				}
			}
			else if("addNewaddress".equals(actionJsp))
			{
				UserShippingAddressIpBean inputBean=new UserShippingAddressIpBean();
				
				inputBean.setFirstName(request.getParameter("firstName"));
				inputBean.setLastName(request.getParameter("lastName"));
				inputBean.setAddress(request.getParameter("completeAddress"));
				inputBean.setLandmark(request.getParameter("landmark"));
				inputBean.setPincode(request.getParameter("pincode"));
				inputBean.setContactNumber(request.getParameter("mobile"));
				String userId=request.getParameter("userId");
				
				UserShippingAddressHelper helper =new UserShippingAddressHelper();
				UserShippingAddressOpBean opBean= helper.validateNewAddress(inputBean,userId);
				if(opBean.isStatusFlag())
				{
					response.sendRedirect(request.getContextPath()+"/jsp/shippingAddress.jsp");
				}
				
			}
			else if("addProduct".equals(actionJsp))
			{
				AddProductInputBean ipBean=new AddProductInputBean();
				ipBean.setAdminId(request.getParameter("adminId"));
				ipBean.setSellerName(request.getParameter("sellerName"));
				ipBean.setContactNumber(request.getParameter("sellerContact"));
				ipBean.setProductName(request.getParameter("prodName"));
				ipBean.setProductPrice(request.getParameter("price"));
				ipBean.setProductImage(request.getParameter("image"));
				ipBean.setSize(request.getParameter("size"));
				ipBean.setColor(request.getParameter("color"));
				ipBean.setShippingCost(request.getParameter("shippingCost"));
				ipBean.setDescription(request.getParameter("description"));
				ipBean.setCategory(request.getParameter("category"));
				ipBean.setSubcategory(request.getParameter("subcategory"));
				ipBean.setQuantity(request.getParameter("stock"));
				
				
				SellerInfoHelper helper=new SellerInfoHelper();
				SellerInfoOpBean opBean=helper.validateSeller(ipBean);
				
				request.setAttribute("isSellerAdded",opBean);
				request.getRequestDispatcher("/jsp/admin.jsp").forward(request,response);
			}
			
			else if("uploadImage".equals(actionJsp))
			{
				
				String img=request.getParameter("photo");
				String userId=request.getParameter("userId");
				UserInfoHelper helper=new UserInfoHelper();
				helper.validateUploadImg(userId, img);
			}
			else
			{
				System.out.println("action not found");
			}
			
			
				
		}
	}
	
	
	
	

}

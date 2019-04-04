package com.naaptolOnlineShopping.helper;

import com.naaptolOnlineShopping.bean.AddProductInputBean;
import com.naaptolOnlineShopping.bean.SellerInfoOpBean;
import com.naaptolOnlineShopping.dao.CategoriesDao;

public class SellerInfoHelper
{

	public SellerInfoOpBean validateSeller(AddProductInputBean ipBean)
	{	
		SellerInfoOpBean opBean=new SellerInfoOpBean();
		
		if(ipBean.getSellerName()==null || "".equals(ipBean.getSellerName()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("seller name is empty");
		}
		else if(ipBean.getContactNumber()==null || "".equals(ipBean.getContactNumber()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("contact number is empty");
		}
		else if(ipBean.getAdminId()==null || "".equals(ipBean.getAdminId()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("admin id is empty");
		}
		else if(ipBean.getCategory()==null || "".equals(ipBean.getCategory()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("category is empty");
		}
		else if(ipBean.getSubcategory()==null || "".equals(ipBean.getSubcategory()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("subcategory is empty");
		}
		else if(ipBean.getProductName()==null || "".equals(ipBean.getProductName()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("product name is empty");
		}
		else if(ipBean.getDescription()==null || "".equals(ipBean.getDescription()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("description is empty");
		}
		else if(ipBean.getShippingCost()==null || "".equals(ipBean.getShippingCost()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("shipping cost is empty");
		}
		else if(ipBean.getQuantity()==null || "".equals(ipBean.getQuantity()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("quantity is empty");
		}
		else
		{
			
			CategoriesDao dao=new CategoriesDao();
			
			opBean=dao.addCategory(ipBean);
			return opBean;
			
		}
		return opBean;
		
	}
	
}

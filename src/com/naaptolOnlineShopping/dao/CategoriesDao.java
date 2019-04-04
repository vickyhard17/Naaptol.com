package com.naaptolOnlineShopping.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.AddProductInputBean;
import com.naaptolOnlineShopping.bean.CategoryOutputBean;
import com.naaptolOnlineShopping.bean.SellerInfoOpBean;
import com.naaptolOnlineShopping.pojo.Category;
import com.naaptolOnlineShopping.pojo.ProductInfo;
import com.naaptolOnlineShopping.pojo.SellerDetails;
import com.naaptolOnlineShopping.pojo.SellerInfo;
import com.naaptolOnlineShopping.pojo.Subcategory;
import com.naaptolOnlineShopping.util.MyConnection;

public class CategoriesDao
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*getting all categories from db*/
	public CategoryOutputBean getCategoriesFromDb()
	{
		CategoryOutputBean opBean=new CategoryOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select c.categoryId,c.categoryName from"
					+ " Category c where c.isActive='Y'");
			
			
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setCategoryList(list);
				opBean.setStatusFlag(true);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			MyConnection.closeSession();
			MyConnection.releaseConnection();
		}
		
		
		
		return opBean;
		
	}
	/*adding product by admin panel*/
	public SellerInfoOpBean addCategory(AddProductInputBean ipBean)
	{
		SellerInfoOpBean opBean=new SellerInfoOpBean();
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			
			Category cat=new Category();
			cat.setCategoryName(ipBean.getCategory());
			cat.setIsActive("Y");
			cat.setCreatedDate(new Date());
			cat.setCreatedBy(Long.valueOf(ipBean.getAdminId()));
			
			session.save(cat);
			
			Subcategory subcat=new Subcategory();
			subcat.setSubcategoryName(ipBean.getSubcategory());
			subcat.setIsActive("Y");
			subcat.setCreatedDate(new Date());
			subcat.setCreatedBy(Long.valueOf(ipBean.getAdminId()));
			
			subcat.setCategory(cat);
			
			session.save(subcat);
			
			
			ProductInfo product=new ProductInfo();
			product.setProductName(ipBean.getProductName());
			product.setProductPrice(Float.valueOf(ipBean.getProductPrice()));
			product.setProductImage(ipBean.getProductImage());
			product.setDescription(ipBean.getDescription());
			product.setIsAvailable("Y");
			product.setIsActive("Y");
			product.setCreatedDate(new Date());
			product.setCreatedBy(Long.valueOf(ipBean.getAdminId()));
			
			product.setSubcategory(subcat);
			
			session.save(product);
			
			SellerInfo seller=new SellerInfo();
			seller.setSellerName(ipBean.getSellerName());
			seller.setContactNumber(Long.valueOf(ipBean.getContactNumber()));
			seller.setIsActive("Y");
			seller.setCreatedDate(new Date());
			seller.setCreatedBy(Long.valueOf(ipBean.getAdminId()));
			
			session.save(seller);
			
			SellerDetails sellD=new SellerDetails();
			sellD.setColor(ipBean.getColor());
			sellD.setSize(ipBean.getSize());
			sellD.setShippingCost(Float.valueOf(ipBean.getShippingCost()));
			sellD.setQuantity(Long.valueOf(ipBean.getQuantity()));
			sellD.setIsActive("Y");
			sellD.setCreatedDate(new Date());
			sellD.setCreatedBy(Long.valueOf(ipBean.getAdminId()));
			
			sellD.setProductInfo(product);
			sellD.setSellerInfo(seller);
			
			
			session.save(sellD);
			
			
			
			
			txn.commit();
			
			opBean.setStatusFlag(true);
			opBean.setStatusMessage("product successfully added");
			return opBean;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txn.rollback();
		}
		finally
		{
			MyConnection.closeSession();
		}
		
		opBean.setStatusFlag(false);
		opBean.setStatusMessage("product not added!! Try again");
		return opBean;
		
		
		
	}
	
}

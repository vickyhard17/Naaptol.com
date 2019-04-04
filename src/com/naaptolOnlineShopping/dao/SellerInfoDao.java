package com.naaptolOnlineShopping.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.SellerInfoOpBean;
import com.naaptolOnlineShopping.pojo.SellerInfo;
import com.naaptolOnlineShopping.util.MyConnection;

public class SellerInfoDao 
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	public SellerInfoOpBean addSeller(SellerInfo seller)
	{	
		SellerInfoOpBean opBean=new SellerInfoOpBean();
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			session.save(seller);
		
			txn.commit();
			
			opBean.setStatusFlag(true);
			opBean.setStatusMessage("seller successfully added");
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
		opBean.setStatusMessage("seller not added");
		return opBean;
	}
	
}

package com.naaptolOnlineShopping.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.UserInfoOutputBean;
import com.naaptolOnlineShopping.pojo.UserInfo;
import com.naaptolOnlineShopping.util.MyConnection;

public class UserInfoDao
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*inserting user data into db from registration form*/
	public boolean insertUserIntoDb(UserInfo userPojo)
	{
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			session.save(userPojo);
		
			txn.commit();
			
			return true;
			
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
		
		return false;
		
	}
	/*validate mobile number on ajax call*/
	public boolean validateMobNoFromDb(UserInfo userPojo)
	{
		try
		{
			session=MyConnection.getConnection();
			
			Long mobileNo=userPojo.getContactNumber();
			Query query=session.createQuery("select u.contactNumber from UserInfo  u where u.contactNumber=:mobNo and"
					+ " u.isActive='Y'");
			query.setParameter("mobNo",mobileNo);
			List list=query.list();
			if(list!=null && list.isEmpty())
			{
				return false;
			}
			else
				return true;
			
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
		return false;
	}
	/*validate email on ajax call*/
	public boolean validateEmailFromDb(UserInfo userPojo)
	{
		try
		{
			session=MyConnection.getConnection();
			//session=factory.openSession();
			String email=userPojo.getEmail();
			Query query=session.createQuery("select u.email from UserInfo u where u.email=:email and u.isActive='Y'");
			query.setParameter("email",email);
			List list=query.list();
			if(list!=null && list.isEmpty())
			{
				return false;
			}
			else
				return true;
			
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
		return false;
		
	}
	
	/*validate user login on ajax call*/
	public UserInfoOutputBean validateUserLoginFromDb(UserInfo userPojo)
	{
		UserInfoOutputBean opBean=new UserInfoOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select u.userId,u.firstName,u.lastName,u.isAdmin from UserInfo u where u.email=:email and "
					+ " u.password=:passwd and u.isActive='Y'");
			query.setParameter("email",userPojo.getEmail());
			query.setParameter("passwd",userPojo.getPassword());
			List list=query.list();
			if(list!=null && list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				Iterator itr=list.iterator();
				while (itr.hasNext())
				{
					Object [] obj= (Object[]) itr.next();
					
					opBean.setUserid(Long.valueOf(obj[0].toString()));
					opBean.setFirstName(obj[1].toString());
					opBean.setLastName(obj[2].toString());
					opBean.setIsAdmin(obj[3].toString());
				}
				
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
	
	public UserInfo getUser(String userId)
	{
		UserInfo user=null;
		try
		{
			
			session=MyConnection.getConnection();
			
			user=(UserInfo) session.get(UserInfo.class,Long.valueOf(userId));
			return user;
			
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
		return user;
		
	}
	public UserInfoOutputBean uploadImgIntoDb(String id, String img)
	{
		UserInfoOutputBean userOpBean=null;
		try
		{
			
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
				UserInfo user=(UserInfo) session.get(UserInfo.class,Long.valueOf(id));
			
				File file = new File("images/firstlook.jpg");
		        byte[] bFile = new byte[(int) file.length()];
		        
		        
			    FileInputStream fileInputStream = new FileInputStream(file);
			    fileInputStream.read(bFile);
			    fileInputStream.close();
		        
		        
		       
		       
		        
		        
			user.setImage(bFile);
			session.save(user);
			
			txn.commit();
			userOpBean.setStatusFlag(true);
			userOpBean.setStatusMessage("photo uploaded successfully");
			return userOpBean;
			
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
			userOpBean.setStatusFlag(false);
			userOpBean.setStatusMessage("upload failed");
			return userOpBean;
	}
	
	
	

}

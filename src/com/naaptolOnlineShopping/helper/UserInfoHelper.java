package com.naaptolOnlineShopping.helper;


import java.util.Date;
import java.util.regex.Pattern;

import com.naaptolOnlineShopping.bean.OrdersOutputBean;
import com.naaptolOnlineShopping.bean.UserInfoInputBean;
import com.naaptolOnlineShopping.bean.UserInfoOutputBean;
import com.naaptolOnlineShopping.dao.UserInfoDao;
import com.naaptolOnlineShopping.pojo.UserInfo;

public class UserInfoHelper
{
	
	
	
	/*validate registration form values*/
	public UserInfoOutputBean validateRegistration(UserInfoInputBean ipBean)
	{
		UserInfoOutputBean opBean=new UserInfoOutputBean();
		
		if(ipBean.getFirstName()==null ||  "".equals(ipBean.getFirstName()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("firstName is empty");
		}
		else if(ipBean.getLastName()==null || "".equals(ipBean.getLastName()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("lastName is empty");
		}
		else if(ipBean.getEmail()==null || "".equals(ipBean.getEmail()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("email is empty");
		}
		else if(ipBean.getGender()==null || "".equals(ipBean.getGender()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("gender not selected");
		}
		
		else if(ipBean.getContactNumber()==null || "".equals(ipBean.getContactNumber()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("Contact number is empty");
		}
		else if(ipBean.getAddress()==null || "".equals(ipBean.getAddress()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("Address is empty");
		}
		
		else if(ipBean.getPincode()==null || "".equals(ipBean.getPincode()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("pincode is empty");
		}
		
		else if(ipBean.getPassword()==null || "".equals(ipBean.getPassword()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("password is empty");
		}
		else if(!Pattern.compile("^[a-zA-Z]+$").matcher(ipBean.getFirstName()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid firstName");
		}
		else if(!Pattern.compile("^[a-zA-Z]+$").matcher(ipBean.getLastName()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid lastName");
		}
		else if(!Pattern.compile("^(([a-zA-Z]+)|((([.]?[a-zA-Z0-9])+|([.]?[a-zA-Z0-9])*([.]?|[_]?)([a-zA-Z0-9][.]?)*)[a-zA-Z0-9]+))@(([a-z0-9]+.[a-z]{3}.[a-z]{2})|([a-z0-9]+.[a-z]{3})|([a-z0-9]+.[a-z]{2}.[a-z]{2}))$").matcher(ipBean.getEmail()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid email");
		}
		
		else if(!Pattern.compile("^(^[7-9])[0-9]{9}$").matcher(ipBean.getContactNumber()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid mobile number");
		}
		else if(!Pattern.compile("^[0-9]{6}$").matcher(ipBean.getPincode()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid pincode");
		}
		else if(!Pattern.compile("^[a-zA-Z0-9]{8,}$").matcher(ipBean.getPassword()).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid password");
		}
		
		else
		{
			UserInfo userPojo=new UserInfo();
			
			userPojo.setFirstName(ipBean.getFirstName());
			userPojo.setLastName(ipBean.getLastName());
			userPojo.setEmail(ipBean.getEmail());
			userPojo.setGender(ipBean.getGender());
			userPojo.setContactNumber(Long.parseLong(ipBean.getContactNumber()));
			userPojo.setAddress(ipBean.getAddress());
			userPojo.setPincode(Long.parseLong(ipBean.getPincode()));
			userPojo.setPassword(ipBean.getPassword());
			userPojo.setIsAdmin("N");
			userPojo.setIsActive("Y");
			userPojo.setCreatedDate(new Date());
			userPojo.setCreatedBy(1L);
			
			
			
			UserInfoDao dao=new UserInfoDao();
			boolean res=dao.insertUserIntoDb(userPojo);
			if(res)
			{
				opBean.setStatusFlag(true);
				opBean.setStatusMessage("user successfully registered");
				
			}
			else
			{
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("user not registered");
			}
		}
		
		return opBean;
		
	}
	/*validate mobile number on ajax call*/
	public UserInfoOutputBean validateMobNo(UserInfoInputBean ipBean)
	{
		
		UserInfoOutputBean opBean=new UserInfoOutputBean();
		
		if(ipBean.getContactNumber()==null ||  "".equals(ipBean.getContactNumber()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("mobile no. is invalid");
			
		}
		else
		{
			UserInfoDao dao=new UserInfoDao();
			UserInfo userPojo=new UserInfo();
			
			userPojo.setContactNumber(Long.parseLong(ipBean.getContactNumber()));
			boolean result=dao.validateMobNoFromDb(userPojo);
			if(result)
			{
				opBean.setStatusMessage("Mobile Number already registered");
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setStatusMessage("Mobile number not registered");
				opBean.setStatusFlag(true);
			}
		}
		
		return opBean;
		
	}

	
	/*validate email on ajax call*/
	public UserInfoOutputBean validateEmail(UserInfoInputBean ipBean)
	{
		UserInfoOutputBean opBean=new UserInfoOutputBean();
		UserInfo userPojo=new UserInfo();
		
		if(ipBean.getEmail()==null &&  "".equals(ipBean.getEmail()))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid email address");
			
		}
		else
		{
			UserInfoDao dao=new UserInfoDao();
			userPojo.setEmail(ipBean.getEmail());
			boolean result=dao.validateEmailFromDb(userPojo);
			if(result)
			{
				opBean.setStatusMessage("Email already registered");
				opBean.setStatusFlag(false);
				
			}
			else
			{
				opBean.setStatusMessage("Email not registered");
				opBean.setStatusFlag(true);
			}
		}
		
		return opBean;
		
	}
	/*validate user login on ajax call */
	public UserInfoOutputBean validateUserHelper(String email, String password)
	{
		UserInfoOutputBean opBean=new UserInfoOutputBean();
		if(email==null || "".equals(email))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("email is empty");
		}
		else if(!Pattern.compile("^(([a-zA-Z]+)|((([.]?[a-zA-Z0-9])+|([.]?[a-zA-Z0-9])*([.]?|[_]?)([a-zA-Z0-9][.]?)*)[a-zA-Z0-9]+))@(([a-z0-9]+.[a-z]{3}.[a-z]{2})|([a-z0-9]+.[a-z]{3})|([a-z0-9]+.[a-z]{2}.[a-z]{2}))$").matcher(email).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid email");
		}
		else if(password==null || "".equals(password))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("password is empty");
		}
		else if(!Pattern.compile("^[a-zA-Z0-9]{8,}$").matcher(password).find())
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("invalid password");
		}
		
		else
		{
			UserInfoDao dao=new UserInfoDao();
			UserInfo userPojo=new UserInfo();
			userPojo.setEmail(email);
			userPojo.setPassword(password);
			
			opBean=dao.validateUserLoginFromDb(userPojo);
			
		}
		return opBean;
	}
	
	public UserInfoOutputBean validateUploadImg(String id, String img)
	{
		UserInfoOutputBean userOpBean;
		UserInfoDao dao=new UserInfoDao();
		userOpBean=dao.uploadImgIntoDb(id,img);
		return userOpBean;
	}
	public UserInfo getUserProfile(Long id)
	{
		UserInfo user;
		UserInfoDao dao=new UserInfoDao();
		user=dao.getUser(String.valueOf(id));
		return user;
	}
	
	
	

}

package com.naaptolOnlineShopping.helper;

import java.util.Date;
import java.util.List;

import com.naaptolOnlineShopping.bean.UserShippingAddressIpBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.dao.UserInfoDao;
import com.naaptolOnlineShopping.dao.UserShippingAddressDao;
import com.naaptolOnlineShopping.pojo.UserInfo;
import com.naaptolOnlineShopping.pojo.UserShippingAddress;

public class UserShippingAddressHelper
{	
	/*getting shipping address of specified user*/
	public List getShippingAddress(Long userId)
	{
		List opList=null;
		
		if(userId!=null)
		{
			
			UserShippingAddressDao dao=new UserShippingAddressDao();
			
			opList=dao.getShippingAddress(userId);
			
		}
		else
		{
			UserShippingAddressOpBean opBean=new UserShippingAddressOpBean();
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("user id is empty");
		}
		
		return opList;
		
	}
	/*getting shipping address to which order is placed*/
	public UserShippingAddressOpBean getShippingAddressById(String addressId)
	{
		UserShippingAddressOpBean addressBean=new UserShippingAddressOpBean();
		
		if(addressId!=null && !"".equals(addressId))
		{
			
			UserShippingAddressDao dao=new UserShippingAddressDao();
			
			addressBean=dao.getShippingAddressFromDb(addressId);
			
			
		}
		else
		{
			
			addressBean.setStatusFlag(false);
			addressBean.setStatusMessage("address id is empty");
		}
		
		return addressBean;
		
	}
	/*validating new address on add*/
	public UserShippingAddressOpBean validateNewAddress(UserShippingAddressIpBean inputBean,String userId)
	{
		UserShippingAddressOpBean opBean=new UserShippingAddressOpBean();
		
		if(inputBean.getFirstName()==null || "".equals(inputBean.getFirstName())) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("firstName is empty");
		}
		else if(inputBean.getLastName()==null || "".equals(inputBean.getLastName())) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("lastName is empty");
		}
		else if(inputBean.getAddress()==null || "".equals(inputBean.getAddress())) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("address is empty");
		}
		else if(inputBean.getPincode()==null || "".equals(inputBean.getPincode())) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("pincode is empty");
		}
		else if(inputBean.getContactNumber()==null || "".equals(inputBean.getContactNumber())) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("contact number is empty");
		}
		else if(userId==null || "".equals(userId)) 
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("user id is empty");
		}
		else
		{
			UserShippingAddress address=new UserShippingAddress();
			UserInfoDao userDao=new UserInfoDao();
			UserInfo user=userDao.getUser(userId);
			
			if(user!=null)
			{
				address.setUserInfo(user);

				address.setFirstName(inputBean.getFirstName());
				address.setLastName(inputBean.getLastName());
				address.setAddress(inputBean.getAddress());
				address.setPincode(Long.valueOf(inputBean.getPincode()));
				address.setLandmark(inputBean.getLandmark());
				address.setContactNumber(Long.valueOf(inputBean.getContactNumber()));
				address.setIsActive("Y");
				address.setCreatedDate(new Date());
				address.setCreatedBy(Long.valueOf(userId));
				
				UserShippingAddressDao dao=new UserShippingAddressDao();
					
				Boolean result=dao.addNewAddress(address);
				if(result)
				{
					opBean.setStatusFlag(true);
					opBean.setStatusMessage("address added successfully");
				}
				else
				{
					opBean.setStatusFlag(false);
					opBean.setStatusMessage("address not added");
				}
				
			}
			else
			{
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("address not added coz user not found");
			}
			
				
			
		}
		return opBean;
		
	}
}

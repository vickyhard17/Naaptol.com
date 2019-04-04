package com.naaptolOnlineShopping.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.UserInfoOutputBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressIpBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.pojo.UserShippingAddress;
import com.naaptolOnlineShopping.util.MyConnection;

public class UserShippingAddressDao
{
	
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*Getting all shipping addresses of specified user*/
	public List getShippingAddress(Long userId)
	{
		List oplist=new ArrayList();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select ship.shippingAddressId,ship.firstName,ship.lastName,ship.address,ship.landmark,ship.pincode,ship.contactNumber "
					+ " from UserShippingAddress ship"
					+ " join ship.userInfo u with u.isActive='Y'"
					+ " where u.userId=:id and "
					+ " ship.isActive='Y'");
			
			query.setParameter("id",userId);
			
			List list=query.list();
			if(list!=null && !list.isEmpty())
			{
				Iterator itr=list.iterator();
				while (itr.hasNext())
				{
					Object [] obj= (Object[]) itr.next();
					
					UserShippingAddressOpBean opBean=new UserShippingAddressOpBean();
					
					opBean.setShippingAddressId((Long)obj[0]);
					opBean.setFirstName((String)obj[1]);
					opBean.setLastName((String)obj[2]);
					opBean.setAddress((String)obj[3]);
					opBean.setLandmark((String)obj[4]);
					opBean.setPincode((Long)obj[5]);
					opBean.setContactNumber((Long)obj[6]);
					
					oplist.add(opBean);
				}
			}
			else
			{
				UserShippingAddressOpBean opBean=new UserShippingAddressOpBean();
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("List empty!! no address found");
				oplist.add(opBean);
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
		return oplist;
		
	}
	/*getting shipping address by addressId for order page*/
	public UserShippingAddressOpBean getShippingAddressFromDb(String addressId)
	{
		UserShippingAddressOpBean opBean=new UserShippingAddressOpBean();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select ship.shippingAddressId,ship.firstName,ship.lastName,ship.address,ship.landmark,ship.pincode,ship.contactNumber "
					+ " from UserShippingAddress ship"
					+ " where ship.shippingAddressId=:id and ship.isActive='Y'");
			
			query.setParameter("id",Long.valueOf(addressId));
			
			List list=query.list();
			if(list!=null && !list.isEmpty())
			{
				Iterator itr=list.iterator();
				while (itr.hasNext())
				{
					Object [] obj= (Object[]) itr.next();
					
					opBean.setShippingAddressId((Long)obj[0]);
					opBean.setFirstName((String)obj[1]);
					opBean.setLastName((String)obj[2]);
					opBean.setAddress((String)obj[3]);
					opBean.setLandmark((String)obj[4]);
					opBean.setPincode((Long)obj[5]);
					opBean.setContactNumber((Long)obj[6]);
					
					
				}
			}
			else
			{
				
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("Adress not found");
				
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
	public Boolean addNewAddress(UserShippingAddress address)
	{
		try
		{
			
			
		
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			session.save(address);
		
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
	
	
}

package com.naaptolOnlineShopping.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.CartOutputBean;
import com.naaptolOnlineShopping.bean.OrderDetailsOutputBean;
import com.naaptolOnlineShopping.bean.OrdersOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.pojo.OrderDetails;
import com.naaptolOnlineShopping.pojo.UserShoppingCart;
import com.naaptolOnlineShopping.util.MyConnection;

public class OrderDetailsDao
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*insert order details to order table*/
	public OrderDetailsOutputBean insertOrderDetailsIntoDb(String cartValue,Long userid, Long sellerDetailId, UserShippingAddressOpBean addressBean)
	{
		
		
		
		OrderDetailsOutputBean opBean=new OrderDetailsOutputBean();
		
		
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			
				OrderDetails order=new OrderDetails();
				order.setUserId(userid);
				order.setSellerDetailsId(sellerDetailId);
				order.setSellerDetailsId(sellerDetailId);
				order.setFirstName(addressBean.getFirstName());
				order.setLastName(addressBean.getLastName());
				order.setAddress(addressBean.getAddress());
				order.setLandmark(addressBean.getLandmark());
				order.setPincode(addressBean.getPincode());
				order.setContactNumber(addressBean.getContactNumber());
				order.setOrderAmount(Float.valueOf(cartValue));
				order.setOrderDate(new Date());
				order.setPaymentType("COD");
				order.setIsActive("Y");
				order.setCreatedDate(new Date());
				order.setCreatedBy(userid);
				
				session.save(order);
			
			txn.commit();
			
			opBean.setStatusFlag(true);
			opBean.setStatusMessage("order placed");
			
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
			MyConnection.releaseConnection();
		}
		opBean.setStatusFlag(false);
		return opBean;
		
	}
	/*getting all orders of specified user*/
	public List getAllOrderDataById(Long userId)
	{
		List opList =new ArrayList();
		OrdersOutputBean  orderBean;
		Object[] obj;
		
			try
			{
				session=MyConnection.getConnection();
				Query query=session.createQuery("select order1.firstName,order1.lastName,order1.address,"
					+ " order1.landmark,order1.pincode,order1.contactNumber,order1.orderAmount,order1.orderDate,order1.paymentType,"
						+ " sellD.size,sellD.color,sellD.shippingCost,product.productName,product.productPrice,product.productImage,order1.orderId"
						+ " from OrderDetails order1"
						+ "  join order1.sellerDetail sellD with sellD.isActive='Y'"
						+ "  join sellD.productInfo product with product.isActive='Y'"
						+ "	 join order1.userInfo user with user.isActive='Y'"
						+ " where user.userId=:id and order1.isActive='Y'");
		
	
				
				query.setParameter("id",userId);
				
				List list=query.list();
				if(list!=null && !list.isEmpty())
				{
					Iterator itr=list.iterator();
					
					while(itr.hasNext())
					{
						obj= (Object[]) itr.next();
						
						orderBean=new OrdersOutputBean();
						orderBean.setFirstName((String)obj[0]);
						orderBean.setLastName((String)obj[1]);
						orderBean.setAddress((String)obj[2]);
						orderBean.setLandmark((String)obj[3]);
						orderBean.setPincode((Long)obj[4]);
						orderBean.setContactNumber((Long)obj[5]);
						orderBean.setOrderAmount((Float)obj[6]);
						
						 Date orderDate=getUtilDate(obj[7].toString());
						
						 orderBean.setOrderDate(orderDate);
						 orderBean.setPaymentType((String)obj[8]);
						 orderBean.setSize((String)obj[9]);
						 orderBean.setColor((String)obj[10]);
						 orderBean.setShippingCost((Float)obj[11]);
						 orderBean.setProductName((String)obj[12]);
						 orderBean.setProductPrice((Float)obj[13]);
						 orderBean.setProductImage((String)obj[14]);
						 orderBean.setOrderId((Long)obj[15]);
						 
						 opList.add(orderBean);
						
						
						orderBean.setStatusFlag(true);
					}
					
					
				}
				else
				{
					orderBean=new OrdersOutputBean();
					orderBean.setStatusFlag(false);
					 opList.add(orderBean);
				}
				
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally
			{
				MyConnection.closeSession();
			}
			
		
		return opList;
		

	}
	public java.util.Date getUtilDate(String dateS) throws ParseException
	{
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		Date date=fmt.parse(dateS);
		java.sql.Date finalDob=new java.sql.Date(date.getTime());
		
		return finalDob;
	}
	
	/*removing entry from db on cancel order*/
	public OrdersOutputBean cancelOrderFromDb(String id)
	{
		OrdersOutputBean opBean=new OrdersOutputBean();
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
				
			Query query = session.createQuery("UPDATE OrderDetails order1 SET order1.isActive=:N "
					+ " WHERE order1.orderId=:orderId and order1.isActive='Y'");
			query.setParameter("N","N");
			query.setParameter("orderId",Long.valueOf(id));
			query.executeUpdate();
				
			txn.commit();
			opBean.setStatusFlag(true);
			opBean.setStatusMessage(" cancel order successfull");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txn.rollback();
		}
		finally
		{
			
			MyConnection.closeSession();
			MyConnection.releaseConnection();
		}
		return opBean;
		
		
		
	}
	
}

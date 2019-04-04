package com.naaptolOnlineShopping.helper;

import java.util.List;

import com.naaptolOnlineShopping.bean.OrderDetailsOutputBean;
import com.naaptolOnlineShopping.bean.OrdersOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.dao.OrderDetailsDao;
import com.naaptolOnlineShopping.dao.ShoppingCartDao;

public class OrderDetailsHelper 
{	
	/*validating cart data to place order*/
	public OrderDetailsOutputBean insertOrderDetails(List list,String cartValue,Long userid, String shippingAddressId)
	{
		OrderDetailsOutputBean opBean =new OrderDetailsOutputBean();
		
		if(list!=null && cartValue!=null && !"".equals(cartValue) && userid!=null)
		{
			UserShippingAddressHelper helper=new UserShippingAddressHelper();
			
			UserShippingAddressOpBean addressBean=helper.getShippingAddressById(shippingAddressId);
			
			for(int i=0;i<list.size();i++)
			{
				ProductDescOutputBean productOpBean=(ProductDescOutputBean)list.get(i);
				Long sellerDetailId=productOpBean.getSellerDetailId();
				
				OrderDetailsDao dao=new OrderDetailsDao();
				opBean=dao.insertOrderDetailsIntoDb(cartValue,userid,sellerDetailId,addressBean);
				
			}
			
			
			
			
			
			if(opBean.isStatusFlag())
			{
				ShoppingCartDao cartDao=new ShoppingCartDao();
				cartDao.removeFromCart(userid);
				
			}
					
		}
		else
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("addressBean or cartValue");
		}
		
		return opBean;
		
	}
	/*getting all orders*/
	public List getAllOrders(Long userId)
	{	
		OrdersOutputBean opBean;
		if(userId!=null)
		{
			OrderDetailsDao dao=new OrderDetailsDao();
			List list=dao.getAllOrderDataById(userId);
			if(list!=null && !list.isEmpty())
				return list;
			
		}
		
		
		return null;
		
	
	}

	public OrdersOutputBean cancelOrderHelper(String id)
	{
		OrderDetailsDao dao=new OrderDetailsDao();
		OrdersOutputBean opBean=dao.cancelOrderFromDb(id);
		return opBean;
		
	}
}

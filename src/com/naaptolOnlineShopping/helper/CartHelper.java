package com.naaptolOnlineShopping.helper;

import java.util.List;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.dao.ShoppingCartDao;

public class CartHelper 
{
	/*insert items into cart*/
	public void validateCartInsert(List list,Long userId)
	{
		if(list!=null && !list.isEmpty())
		{
			ShoppingCartDao dao=new ShoppingCartDao();
			dao.insertIntoCart(list,userId);
		}
	}
	
	/*get user cart on login*/
	public List getUserCart(Long userId)
	{
		List list=null;
		
		if(userId!=null)
		{
			ShoppingCartDao dao=new ShoppingCartDao();
			list=dao.getUserCartFromDb(userId);
			return list;
		}
		else
		{
			ProductDescOutputBean opBean=new ProductDescOutputBean();
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("user id is null");
			list.add(opBean);
		}
		return list;
	}
	
}

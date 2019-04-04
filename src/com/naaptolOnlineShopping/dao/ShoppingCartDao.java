package com.naaptolOnlineShopping.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.CartOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.bean.UserShippingAddressOpBean;
import com.naaptolOnlineShopping.pojo.UserShoppingCart;
import com.naaptolOnlineShopping.util.MyConnection;

public class ShoppingCartDao
{
	
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*insert into cart on proceed to checkout*/
	public CartOutputBean insertIntoCart(List list,Long userId)
	{

		
		
		CartOutputBean opBean=new CartOutputBean();
		
		
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			
			for(int i=0;i<list.size();i++)
			{
				ProductDescOutputBean cartOpBean=(ProductDescOutputBean) list.get(i);
				UserShoppingCart cart=new UserShoppingCart();
				cart.setUserId(userId);
				cart.setQuantity(cartOpBean.getQuantity());
				cart.setColor(cartOpBean.getColor());
				cart.setSize(cartOpBean.getSize());
				cart.setSellerDetailsId(cartOpBean.getSellerDetailId());
				cart.setProductId(cartOpBean.getProductId());
				cart.setIsAdded("Y");
				cart.setIsActive("Y");
				cart.setCreatedDate(new Date());
				cart.setCreatedBy(userId);
				
				session.save(cart);
				
			}
			
			txn.commit();
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
	
	/*remove from cart when order is placed*/
	public CartOutputBean removeFromCart(Long userid)
	{
		CartOutputBean opBean=new CartOutputBean();
		try
		{
			session=MyConnection.getConnection();
			txn=session.beginTransaction();
			
			UserShoppingCart cart =new UserShoppingCart();
				
			Query query = session.createQuery("UPDATE UserShoppingCart cart SET cart.isActive=:N ,cart.isAdded=:N"
					+ " WHERE cart.userId= :userId and cart.isActive='Y' and cart.isAdded='Y'");
			query.setParameter("N","N");
			query.setParameter("userId",userid);
			query.executeUpdate();
				
			txn.commit();
			opBean.setStatusFlag(true);
			opBean.setStatusMessage("successfully removed from cart");
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
	/*getting user cart data*/
	public List getUserCartFromDb(Long userId)
	{
		List oplist=new ArrayList();
		try
		{
			
			session=MyConnection.getConnection();
			
			
			Query query=session.createQuery("select product.productName,product.productPrice,sellD.shippingCost,cart.quantity,cart.color"
					+ " from ProductInfo product "
					+ " join product.sellerDetails sellD with sellD.isActive='Y'"
					+ " join product.userShoppingCart cart with cart.isActive='Y'"
					+ "	where cart.userId=:userId and product.isActive='Y'");
			
				query.setParameter("userId",userId);
			
			List list=query.list();
			if(list!=null && !list.isEmpty())
			{
				Iterator itr=list.iterator();
				while (itr.hasNext())
				{
					Object [] obj= (Object[]) itr.next();
					
					ProductDescOutputBean opBean=new ProductDescOutputBean();
					opBean.setProductName((String)obj[0]);
					opBean.setProductPrice((Float)obj[1]);
					opBean.setQuantity((Long)obj[3]);
					opBean.setColor((String)obj[4]);
					opBean.setSize((String)obj[4]);
					opBean.setShippingCost((Float)obj[2]);
					
					oplist.add(opBean);
					opBean.setStatusFlag(true);
				}
			}
			else
			{
				ProductDescOutputBean opBean=new ProductDescOutputBean();
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("cart empty");
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
	
}

package com.naaptolOnlineShopping.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.CategoryOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.util.MyConnection;

public class SellerDetailsDao
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	
	public ProductDescOutputBean getSellerDetailsId(String productId, String size, String color)
	{
		
		ProductDescOutputBean opBean=new ProductDescOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			StringBuffer q1=new StringBuffer();
			q1.append("select product.productId,sellD.sellerDetailId,product.productName,product.productPrice,product.productImage,product.description,sellD.size,sellD.color,sellD.shippingCost,sellI.sellerName");
			q1.append(" from SellerDetails sellD ");
			q1.append(" join sellD.productInfo product with product.isActive='Y' and product.isAvailable='Y' ");
			q1.append(" join sellD.sellerInfo sellI with sellI.isActive='Y'");
			q1.append( " where ");
			if(size!=null)
			{
				q1.append("sellD.size=:size and ");
			}
			if(color!=null)
			{
				q1.append("sellD.color=:color and ");
			}
			q1.append("product.productId=:id and  sellD.isActive='Y'");
			
			Query query=session.createQuery(q1.toString());
			
			if(size!=null)
			{
				query.setParameter("size", size);
			}
			if(color!=null)
			{
				query.setParameter("color", color);
			}
			
			
			query.setParameter("id", Long.valueOf(productId));
			
			List list=query.list();
			if(list!=null && !list.isEmpty())
			{
				Iterator itr=list.iterator();
				/*opBean.setStatusFlag(true);*/
				while(itr.hasNext())
				{
					Object[] obj= (Object[]) itr.next();
					opBean.setProductId((Long)obj[0]);
					opBean.setSellerDetailId((Long)obj[1]);
					opBean.setProductName((String)obj[2]);
					opBean.setProductPrice((Float)obj[3]);
					opBean.setProductImage((String)obj[4]);
					opBean.setDescription((String)obj[5]);
					opBean.setSize((String)obj[6]);
					opBean.setColor((String)obj[7]);
					opBean.setShippingCost((Float)obj[8]);
					opBean.setSellerName((String)obj[9]);
					opBean.setQuantity(1L);
					
					opBean.setStatusFlag(true);
				}
				
			}
			else
			{
				opBean.setStatusFlag(false);
				
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

}

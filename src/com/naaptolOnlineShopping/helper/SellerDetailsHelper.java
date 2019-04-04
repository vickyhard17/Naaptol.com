package com.naaptolOnlineShopping.helper;

import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.dao.SellerDetailsDao;

public class SellerDetailsHelper 
{

	public ProductDescOutputBean getSellerDetailsIdHelper(String productId, String size,String color)
	{
		ProductDescOutputBean opBean=new ProductDescOutputBean();
		
		if(productId==null ||  "".equals(productId))
		{
			
			opBean.setStatusFlag(false);
			opBean.setStatusMessage(" product id is empty");
			
		}
		else
		{
			SellerDetailsDao dao=new SellerDetailsDao();
			opBean=dao.getSellerDetailsId(productId,size,color);
			
		}
		return opBean;
		
	}

}

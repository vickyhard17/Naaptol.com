package com.naaptolOnlineShopping.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.CategoryOutputBean;
import com.naaptolOnlineShopping.pojo.Category;
import com.naaptolOnlineShopping.util.MyConnection;

public class SubcategoryDao
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*getting all subcategories using category id*/
	public CategoryOutputBean getSubcategoriesFromDb(Category category)
	{
		CategoryOutputBean opBean=new CategoryOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			
			Query query=session.createQuery("select s.subcategoryId,s.subcategoryName from Subcategory s "
					+ "join s.category c where c.categoryId=:id and c.isActive='Y' and s.isActive='Y'");
			query.setParameter("id",category.getCategoryId());
			
			
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setCategoryList(list);
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
}

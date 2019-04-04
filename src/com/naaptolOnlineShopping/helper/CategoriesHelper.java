package com.naaptolOnlineShopping.helper;

import com.naaptolOnlineShopping.bean.CategoryOutputBean;
import com.naaptolOnlineShopping.dao.CategoriesDao;
import com.naaptolOnlineShopping.dao.SubcategoryDao;
import com.naaptolOnlineShopping.pojo.Category;

public class CategoriesHelper
{	
	/*getting categories*/
	public CategoryOutputBean getCategoriesHelper()
	{
		CategoryOutputBean opBean=new CategoryOutputBean();
		CategoriesDao dao=new CategoriesDao();
		
		opBean=dao.getCategoriesFromDb();
		
		return opBean;
		
	}
	/*getting subcategories*/
	public CategoryOutputBean getSubCategoriesHelper(String id)
	{
		CategoryOutputBean opBean=new CategoryOutputBean();
		if(id==null ||  "".equals(id))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("category id is empty");
		}
		else
		{
			SubcategoryDao dao=new SubcategoryDao();
			Category category=new Category();
			category.setCategoryId(Long.parseLong(id));
			opBean=dao.getSubcategoriesFromDb(category);
		}

		return opBean;
	}
}

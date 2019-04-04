package com.naaptolOnlineShopping.helper;

import java.util.List;

import com.naaptolOnlineShopping.bean.AutocompleteOutputBean;
import com.naaptolOnlineShopping.bean.ProductInfoOutputBean;
import com.naaptolOnlineShopping.dao.ProductInfoDao;
import com.naaptolOnlineShopping.pojo.Category;
import com.naaptolOnlineShopping.pojo.Subcategory;

public class ProductInfoHelper 
{
	/*getting products by subcategory id*/
	public ProductInfoOutputBean getProductsHelper(String id)
	{
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		
		if(id==null ||  "".equals(id))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage(" subCategory id is empty");
		}
		else
		{
			ProductInfoDao dao=new ProductInfoDao();
			Subcategory subCat=new Subcategory();
			subCat.setSubcategoryId(Long.parseLong(id));
			opBean=dao.getSubcategoriesFromDb(subCat);
		}

		return opBean;

	}
	/*getting all products by category id*/
	public ProductInfoOutputBean getAllCategoryProductsHelper(String id)
	{
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		
		if(id==null ||  "".equals(id))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("category id is empty");
		}
		else
		{
			ProductInfoDao dao=new ProductInfoDao();
			Category category=new Category();
			category.setCategoryId(Long.parseLong(id));
			opBean=dao.getProductsByCategory(category);
		}

		return opBean;
	}
	/*Getting all products onload*/
	public ProductInfoOutputBean getProductsHelper()
	{	
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		ProductInfoDao dao=new ProductInfoDao();
		
		opBean=dao.getAllProductsFromDb();
		
		return opBean;
	}
	/*Getting product names for autosuggestion */
	public AutocompleteOutputBean getProductNames(String value)
	{
		AutocompleteOutputBean opBean=new AutocompleteOutputBean();
		
		if(value==null ||  "".equals(value))
		{
			opBean.setStatusFlag(false);
			opBean.setStatusMessage("entered string is empty");
		}
		else
		{
			ProductInfoDao dao=new ProductInfoDao();
			
			opBean= dao.getAllProductNames(value);

		}
			
			return opBean;
	}
	
	/*Getting product description by product id*/
	public List getProductDescHelper(String id)
	{
		ProductInfoDao dao=new ProductInfoDao();
		List opList=dao.getProductDescription(id);
		return opList;	
	}

}

package com.naaptolOnlineShopping.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.naaptolOnlineShopping.bean.AutocompleteOutputBean;
import com.naaptolOnlineShopping.bean.ProductDescOutputBean;
import com.naaptolOnlineShopping.bean.ProductInfoOutputBean;
import com.naaptolOnlineShopping.pojo.Category;
import com.naaptolOnlineShopping.pojo.Subcategory;
import com.naaptolOnlineShopping.util.MyConnection;

public class ProductInfoDao 
{
	SessionFactory factory=null;
	Session session=null;
	Transaction txn=null;
	
	/*getting all products by using subcategory id*/
	public ProductInfoOutputBean getSubcategoriesFromDb(Subcategory subCat)
	{
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select product.productId,product.productName,product.productPrice,product.productImage"
					+ " from ProductInfo product"
					+ " join product.subcategory subcat"
					+ " where subcat.subcategoryId=:id and product.isAvailable='Y' and product.isActive='Y'"
					+ " and subcat.isActive='Y'");
			query.setParameter("id",subCat.getSubcategoryId());
			
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setProductList(list);
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
	
	/*getting all products by using category id*/
	public ProductInfoOutputBean getProductsByCategory(Category category)
	{

		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select product.productId,product.productName,product.productPrice,product.productImage"
					+ " from ProductInfo product"
					+ " join product.subcategory subcat "
					+ " join subcat.category cat "
					+ " where cat.categoryId=:id and cat.isActive='Y' and"
					+ " subcat.isActive='Y' and product.isActive='Y' and product.isAvailable='Y'");
			query.setParameter("id",category.getCategoryId());
			
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setProductList(list);
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
	/*Getting all products onload home page*/
	public ProductInfoOutputBean getAllProductsFromDb()
	{
		ProductInfoOutputBean opBean=new ProductInfoOutputBean();
		try
		{
			
			session=MyConnection.getConnection();
			
			Query query=session.createQuery("select product.productId,product.productName,product.productPrice,"
					+ " product.productImage from ProductInfo product where product.isAvailable='Y' and product.isActive='Y'" );
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
			}
			else
			{
				opBean.setProductList(list);
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
			//MyConnection.releaseConnection();
		}
		
		
		
		return opBean;
		
	}
	/*getting all names of category subcategory and products for autocomplete*/
	public AutocompleteOutputBean getAllProductNames(String value)
	{
		AutocompleteOutputBean opBean=new AutocompleteOutputBean();
		
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select product.productId,product.productName"
					+ " from ProductInfo product"
					+ " where product.productName like ? "
					+ " and product.isAvailable='Y' and product.isActive='Y'");
			
			query.setString(0, "%"+value+"%");
			
			
			List list= query.list();
			if(list==null || list.isEmpty())
			{
				opBean.setStatusFlag(false);
				opBean.setStatusMessage("Either no product matches the word entered by you or please remove some of filter options selected to see products.");
			}
			else
			{
				opBean.setStatusFlag(true);
				opBean.setProductNameList(list);
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
	/*Getting product description onclick product*/
	public List getProductDescription(String id)
	{	
		
		List opList =new ArrayList();
		ProductDescOutputBean opBean;
		Object[] obj;
		try
		{
			
			session=MyConnection.getConnection();
			Query query=session.createQuery("select product.productId,product.productName,product.productPrice,"
			+ " product.productImage,product.description,sellD.sellerDetailId,sellD.size,sellD.color,sellD.shippingCost,sellI.sellerName"
			+ " from SellerDetails sellD "
			+ " join sellD.productInfo product with product.isActive='Y' and product.isAvailable='Y' "
			+ " join sellD.sellerInfo sellI with sellI.isActive='Y'"
			+ " where product.productId=:id and  sellD.isActive='Y'");
	

			
			query.setParameter("id",Long.valueOf(id));
			
			List list=query.list();
			if(list!=null && !list.isEmpty())
			{
				Iterator itr=list.iterator();
				
				while(itr.hasNext())
				{
					obj= (Object[]) itr.next();
					
					opBean=new ProductDescOutputBean();
					
					opBean.setProductId((Long)obj[0]);
					opBean.setProductName((String)obj[1]);
					opBean.setProductPrice((Float)obj[2]);
					opBean.setProductImage((String)obj[3]);
					opBean.setDescription((String)obj[4]);
					opBean.setSellerDetailId((Long)obj[5]);
					opBean.setSize((String)obj[6]);
					opBean.setColor((String)obj[7]);
					opBean.setShippingCost((Float)obj[8]);
					opBean.setSellerName((String)obj[9]);
					opList.add(opBean);
					
					
					
				}
				
			}
			else
			{
				 opBean=new ProductDescOutputBean();
				 opBean.setStatusFlag(false);
				 opList.add(opBean);
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


}

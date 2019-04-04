package com.naaptolOnlineShopping.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MyConnection
{
	static SessionFactory factory=null;
	static Session session=null;
	
	static
	{
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public  static Session getConnection()
	{
		return factory.openSession();
	}
	
	public static void releaseConnection()
	{
		if(factory!=null)
		{
			factory.close();
		}
	}
	
	
	public static void closeSession()
	{
		if(session!=null)
		{
			session.close();
		}
	}

}

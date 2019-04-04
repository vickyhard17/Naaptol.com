package com.naaptolOnlineShopping.bean;

import java.util.List;

public class CategoryOutputBean
{
	private List categoryList;
	private boolean statusFlag;
	private String statusMessage;
	
	
	public List getCategoryList()
	{
		return categoryList;
	}
	public void setCategoryList(List categoryList)
	{
		this.categoryList = categoryList;
	}
	public boolean isStatusFlag()
	{
		return statusFlag;
	}
	public void setStatusFlag(boolean statusFlag)
	{
		this.statusFlag = statusFlag;
	}
	public String getStatusMessage()
	{
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage)
	{
		this.statusMessage = statusMessage;
	}
}

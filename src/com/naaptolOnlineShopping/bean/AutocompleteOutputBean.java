package com.naaptolOnlineShopping.bean;

import java.util.List;

public class AutocompleteOutputBean 
{	
	private List productNameList;
	private boolean statusFlag;
	private String statusMessage;
	
	public List getProductNameList()
	{
		return productNameList;
	}
	public void setProductNameList(List productNameList)
	{
		this.productNameList = productNameList;
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

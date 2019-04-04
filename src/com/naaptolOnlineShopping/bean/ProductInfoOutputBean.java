package com.naaptolOnlineShopping.bean;

import java.util.List;

public class ProductInfoOutputBean 
{
	private List productList;
	
	private boolean statusFlag;
	private String statusMessage;
	
	
	
	public List getProductList() {
		return productList;
	}
	public void setProductList(List productList) {
		this.productList = productList;
	}
	public boolean isStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}

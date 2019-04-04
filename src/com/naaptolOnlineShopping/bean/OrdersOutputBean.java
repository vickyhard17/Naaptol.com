package com.naaptolOnlineShopping.bean;

import java.util.Date;

public class OrdersOutputBean
{
	private Long orderId;
	private String firstName;
	private String lastName;
	private String address;
	private String landmark;
	private Long pincode;
	private Long contactNumber;
	private Float orderAmount;
	private Date orderDate;
	private String paymentType;
	private String productName;
	private Float productPrice;
	private String productImage;
	private String size;
	private String color;
	private Float shippingCost;
	
	private boolean statusFlag;
	private String statusMessage;
	
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
	public Long getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getLandmark()
	{
		return landmark;
	}
	public void setLandmark(String landmark)
	{
		this.landmark = landmark;
	}
	public Long getPincode()
	{
		return pincode;
	}
	public void setPincode(Long pincode)
	{
		this.pincode = pincode;
	}
	public Long getContactNumber()
	{
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	public Float getOrderAmount()
	{
		return orderAmount;
	}
	public void setOrderAmount(Float orderAmount)
	{
		this.orderAmount = orderAmount;
	}
	public Date getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	public String getPaymentType()
	{
		return paymentType;
	}
	public void setPaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public Float getProductPrice()
	{
		return productPrice;
	}
	public void setProductPrice(Float productPrice)
	{
		this.productPrice = productPrice;
	}
	public String getProductImage()
	{
		return productImage;
	}
	public void setProductImage(String productImage)
	{
		this.productImage = productImage;
	}
	public String getSize()
	{
		return size;
	}
	public void setSize(String size)
	{
		this.size = size;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public Float getShippingCost()
	{
		return shippingCost;
	}
	public void setShippingCost(Float shippingCost)
	{
		this.shippingCost = shippingCost;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

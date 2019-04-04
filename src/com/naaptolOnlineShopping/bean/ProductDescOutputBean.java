package com.naaptolOnlineShopping.bean;

public class ProductDescOutputBean
{	
	private Long productId;
	private Long sellerDetailId;
	private String productName;
	private Float productPrice;
	private String productImage;
	private String description;
	private Long quantity;
	private String size;
	private String color;
	private Float shippingCost;
	private String sellerName;
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
	
	public Long getSellerDetailId() 
	{
		return sellerDetailId;
	}
	public void setSellerDetailId(Long sellerDetailId)
	{
		this.sellerDetailId = sellerDetailId;
	}
	public Long getProductId()
	{
		return productId;
	}
	public void setProductId(Long productId)
	{
		this.productId = productId;
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
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
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
	public Long getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(Long quantity)
	{
		this.quantity = quantity;
	}
	public String getSellerName() 
	{
		return sellerName;
	}
	public void setSellerName(String sellerName) 
	{
		this.sellerName = sellerName;
	}
	
	
}

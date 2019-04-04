package com.naaptolOnlineShopping.pojo;

import java.util.Date;
import java.util.Set;

public class SellerDetails
{
	private Long sellerDetailId;
	private String size;
	private String color;
	private Long quantity;
	private Float shippingCost;
	private String isActive;
	private Date createdDate;
	private Long createdBy;
	private Date modifiedDate;
	private Long modifiedBy;
	
	private SellerInfo sellerInfo;
	private ProductInfo productInfo;
	private Set orderDetails;
	
	public SellerDetails()
	{
		super();
		
	}

	public Set getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getSellerDetailId() {
		return sellerDetailId;
	}

	public void setSellerDetailId(Long sellerDetailId) {
		this.sellerDetailId = sellerDetailId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
	

}

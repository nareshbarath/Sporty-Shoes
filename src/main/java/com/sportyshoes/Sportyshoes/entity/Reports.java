package com.sportyshoes.Sportyshoes.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Reports {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private String productCategory;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private Long qty;

	@Column(nullable = false)
	private Double totalPrice;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;

	public Reports() {

	}

	public Reports(Integer productId, String productName, String productCategory, String userEmail, String userName,
			Long qty, Double totalPrice, Date date) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.userEmail = userEmail;
		this.userName = userName;
		this.qty = qty;
		this.totalPrice = totalPrice;
		this.date = date;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String userEmail() {
		return userEmail;
	}

	public void setUserId(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Reports [id=" + id + ", productId=" + productId + ", productName=" + productName + ", productCategory="
				+ productCategory + ", userEmail=" + userEmail + ", userName=" + userName + ", qty=" + qty
				+ ", totalPrice=" + totalPrice + ", date=" + date + "]";
	}

}

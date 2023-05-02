package com.sportyshoes.Sportyshoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer cartId;

	@Column(nullable = false)
	private Integer productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private Integer qty;

	@Column(nullable = false)
	private Integer userId;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String status;

	public Cart() {

	}

	public Cart(Integer cartId, Integer productId, String productName, Integer qty, Integer userId, String userName,
			String status) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productName = productName;
		this.qty = qty;
		this.userId = userId;
		this.userName = userName;
		this.status = status;
	}

	public Integer getCardId() {
		return cartId;
	}

	public void setCardId(Integer cardId) {
		this.cartId = cardId;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", productName=" + productName + ", qty=" + qty
				+ ", userId=" + userId + ", userName=" + userName + ", status=" + status + "]";
	}

}

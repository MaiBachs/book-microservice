package com.example.BookService.dto;

import java.util.Date;

public class HistoryPaymentEbookDto {
	private Long id;
	private Long userId;
	private String userName;
	private Long bookId;
	private String bookName;
	private String bookCategory;
	private Date createdDate;
	private String codePayment;
	private String contentPayment;
	private Long moneyPayment;
	
	
	public HistoryPaymentEbookDto(Long id, Long userId, String userName, Long bookId, String bookName,
			String bookCategory, Date createdDate, String codePayment, String contentPayment, Long moneyPayment) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookCategory = bookCategory;
		this.createdDate = createdDate;
		this.codePayment = codePayment;
		this.contentPayment = contentPayment;
		this.moneyPayment = moneyPayment;
	}
	
	public HistoryPaymentEbookDto() {
		super();
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCodePayment() {
		return codePayment;
	}
	public void setCodePayment(String codePayment) {
		this.codePayment = codePayment;
	}
	public String getContentPayment() {
		return contentPayment;
	}
	public void setContentPayment(String contentPayment) {
		this.contentPayment = contentPayment;
	}
	public Long getMoneyPayment() {
		return moneyPayment;
	}
	public void setMoneyPayment(Long moneyPayment) {
		this.moneyPayment = moneyPayment;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}

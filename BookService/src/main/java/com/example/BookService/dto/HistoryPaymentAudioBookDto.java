package com.example.BookService.dto;

import java.util.Date;

public class HistoryPaymentAudioBookDto {
	private Long id;
	private Long userId;
	private String userName;
	private Long AudioBookId;
	private String AudioBookName;
	private String AudioBookCategory;
	private Date createdDate;
	private String codePayment;
	private String contentPayment;
	private Long moneyPayment;
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
	public Long getAudioBookId() {
		return AudioBookId;
	}
	public void setAudioBookId(Long audioBookId) {
		AudioBookId = audioBookId;
	}
	public String getAudioBookName() {
		return AudioBookName;
	}
	public void setAudioBookName(String audioBookName) {
		AudioBookName = audioBookName;
	}
	public String getAudioBookCategory() {
		return AudioBookCategory;
	}
	public void setAudioBookCategory(String audioBookCategory) {
		AudioBookCategory = audioBookCategory;
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
	public HistoryPaymentAudioBookDto(Long id, Long userId, String userName, Long audioBookId, String audioBookName,
			String audioBookCategory, Date createdDate, String codePayment, String contentPayment, Long moneyPayment) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		AudioBookId = audioBookId;
		AudioBookName = audioBookName;
		AudioBookCategory = audioBookCategory;
		this.createdDate = createdDate;
		this.codePayment = codePayment;
		this.contentPayment = contentPayment;
		this.moneyPayment = moneyPayment;
	}
	public HistoryPaymentAudioBookDto() {
		super();
	}
	
}

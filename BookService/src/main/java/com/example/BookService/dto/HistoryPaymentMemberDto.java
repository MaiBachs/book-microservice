package com.example.BookService.dto;

import java.util.Date;

public class HistoryPaymentMemberDto {
	private String codePayment;
	private String userName;
	private Long term;
	private Long moneyPayment;
	private Date createdDate;
	private String contentPayment;
	private Date startDate;
	private Date endDate;
	
	public String getCodePayment() {
		return codePayment;
	}
	public void setCodePayment(String codePayment) {
		this.codePayment = codePayment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Long getMoneyPayment() {
		return moneyPayment;
	}
	public void setMoneyPayment(Long moneyPayment) {
		this.moneyPayment = moneyPayment;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getContentPayment() {
		return contentPayment;
	}
	public void setContentPayment(String contentPayment) {
		this.contentPayment = contentPayment;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public HistoryPaymentMemberDto(String codePayment, String userName, Long term, Long moneyPayment, Date createdDate,
			String contentPayment, Date startDate, Date endDate) {
		super();
		this.codePayment = codePayment;
		this.userName = userName;
		this.term = term;
		this.moneyPayment = moneyPayment;
		this.createdDate = createdDate;
		this.contentPayment = contentPayment;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public HistoryPaymentMemberDto() {
		super();
	}
	
}

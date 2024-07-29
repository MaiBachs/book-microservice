package com.example.BookService.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "history_payment")
public class HistoryPayment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long history_payment_id;
	@Column
	private String codePayment;
	@Column
	private Long bookId;
	@Column
	private String contentPayment;
	@Column
	private Long moneyPayment;
	@Column
	private Date createdDate;
	@Column
	private Long termId;
	@Column
	private Long userId;
	
	public Long getHistory_payment_id() {
		return history_payment_id;
	}
	public void setHistory_payment_id(Long history_payment_id) {
		this.history_payment_id = history_payment_id;
	}
	public String getCodePayment() {
		return codePayment;
	}
	public void setCodePayment(String codePayment) {
		this.codePayment = codePayment;
	}
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getTermId() {
		return termId;
	}
	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public HistoryPayment(Long history_payment_id, String codePayment, Long bookId, String contentPayment,
			Long moneyPayment, Date createdDate, Long termId, Long userId) {
		super();
		this.history_payment_id = history_payment_id;
		this.codePayment = codePayment;
		this.bookId = bookId;
		this.contentPayment = contentPayment;
		this.moneyPayment = moneyPayment;
		this.createdDate = createdDate;
		this.termId = termId;
		this.userId = userId;
	}
	public HistoryPayment() {
		super();
	}
}

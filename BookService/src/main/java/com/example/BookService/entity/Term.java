package com.example.BookService.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "term")
public class Term {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long termId;
	@Column
	private Long term;
	@Column
	private Long cost;
	@Column
	private Long status;
	@Column
	private Date createdDate;
	@Column
	private Date updatedDate;
	
	
	public Term(Long termId, Long term, Long cost, Long status, Date createdDate, Date updatedDate) {
		super();
		this.termId = termId;
		this.term = term;
		this.cost = cost;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	public Term() {
		super();
	}
	public Long getTermId() {
		return termId;
	}
	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}

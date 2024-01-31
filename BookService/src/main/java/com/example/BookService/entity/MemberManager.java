package com.example.BookService.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "member_manager")
public class MemberManager {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberManagerId;
	@Column
	private Long userId;
	@Column
	private Long termId;
	@Column
	private Date createdDate;
	@Column
	private Date updatedDate;
	@Column
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	private Long status;
	@Transient
	private Long term;
	
	public MemberManager(Long memberManagerId, Long userId, Long termId, Date createdDate, Date updatedDate,
			Date startDate, Date endDate, Long status) {
		super();
		this.memberManagerId = memberManagerId;
		this.userId = userId;
		this.termId = termId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	

	public MemberManager(Long memberManagerId, Long userId, Long termId, Date createdDate, Date updatedDate,
			Date startDate, Date endDate, Long status, Long term) {
		super();
		this.memberManagerId = memberManagerId;
		this.userId = userId;
		this.termId = termId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.term = term;
	}


	public MemberManager() {
		super();
	}

	public Long getMemberManagerId() {
		return memberManagerId;
	}

	public void setMemberManagerId(Long memberManagerId) {
		this.memberManagerId = memberManagerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}


	public Long getTerm() {
		return term;
	}


	public void setTerm(Long term) {
		this.term = term;
	}
	
	
}

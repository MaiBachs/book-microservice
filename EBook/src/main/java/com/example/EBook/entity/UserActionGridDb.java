package com.example.EBook.entity;

import java.util.Date;

import com.toshiba.mwcloud.gs.RowKey;

public class UserActionGridDb {
	@RowKey
	private Date ts;
	private Long userId;
	private Long categoryId;
	private Float views;
	
	
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Float getViews() {
		return views;
	}
	public void setViews(Float views) {
		this.views = views;
	}
	
	public UserActionGridDb(Date ts, Long userId, Long categoryId, Float views) {
		super();
		this.ts = ts;
		this.userId = userId;
		this.categoryId = categoryId;
		this.views = views;
	}
	public UserActionGridDb() {
		super();
	}
}

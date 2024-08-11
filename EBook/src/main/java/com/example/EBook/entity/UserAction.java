package com.example.EBook.entity;

import com.toshiba.mwcloud.gs.RowKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long userId;
	@Column
	private Long categoryId;
	@Column
	private Float views;
	@Column
	private String category;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public UserAction(Long id, Long userId, Long categoryId, Float views, String category) {
		super();
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.views = views;
		this.category = category;
	}
	public UserAction() {
		super();
	}
	
}

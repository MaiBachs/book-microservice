package com.example.Podcast.input;

import java.util.ArrayList;
import java.util.List;

public class AudioBookInput {
	 private int page;
    private int size;
    private String audioBookCategory;
    private String audioBookName;
    private Long audioBookType;
    private String audioBookAuthor;
    private List<String> categories = new ArrayList();
    private Long userId;
    
    
	public String getAudioBookAuthor() {
		return audioBookAuthor;
	}
	public void setAudioBookAuthor(String audioBookAuthor) {
		this.audioBookAuthor = audioBookAuthor;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getAudioBookCategory() {
		return audioBookCategory;
	}
	public void setAudioBookCategory(String audioBookCategory) {
		this.audioBookCategory = audioBookCategory;
	}
	public String getAudioBookName() {
		return audioBookName;
	}
	public void setAudioBookName(String audioBookName) {
		this.audioBookName = audioBookName;
	}
	public Long getAudioBookType() {
		return audioBookType;
	}
	public void setAudioBookType(Long audioBookType) {
		this.audioBookType = audioBookType;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public AudioBookInput() {
		super();
	}
	public AudioBookInput(int page, int size, String audioBookCategory, String audioBookName, Long audioBookType,
			List<String> categories) {
		super();
		this.page = page;
		this.size = size;
		this.audioBookCategory = audioBookCategory;
		this.audioBookName = audioBookName;
		this.audioBookType = audioBookType;
		this.categories = categories;
	}
	
    
}

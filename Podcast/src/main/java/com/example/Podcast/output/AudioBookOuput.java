package com.example.Podcast.output;

import java.util.List;

import com.example.Podcast.entity.AudioBook;

public class AudioBookOuput {
	private int page;
    private int totalPage;
    private List<AudioBook> audioBooks;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<AudioBook> getAudioBooks() {
		return audioBooks;
	}
	public void setAudioBooks(List<AudioBook> audioBooks) {
		this.audioBooks = audioBooks;
	}
	public AudioBookOuput(int page, int totalPage, List<AudioBook> audioBooks) {
		super();
		this.page = page;
		this.totalPage = totalPage;
		this.audioBooks = audioBooks;
	}
	public AudioBookOuput() {
		super();
	}
    
}

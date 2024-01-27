package com.example.EBook.output;

import java.util.List;

import com.example.EBook.entity.BookEntity;

public class BookOutput {
    private int page;
    private int totalPage;
    private List<BookEntity> bookEntityList;

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

	public List<BookEntity> getBookEntityList() {
		return bookEntityList;
	}

	public void setBookEntityList(List<BookEntity> bookEntityList) {
		this.bookEntityList = bookEntityList;
	}

	public BookOutput(int page, int totalPage, List<BookEntity> bookEntityList) {
		this.page = page;
		this.totalPage = totalPage;
		this.bookEntityList = bookEntityList;
	}

	public BookOutput() {
	}
    
}

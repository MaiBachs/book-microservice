package com.example.EBook.input;

public class BookInput {
    private int page;
    private int size;
    private String bookCategory;
    private String bookName;
    private Long bookPrice;
    private String bookAuthor;
    private Long bookType;
    private Long userId;
    
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

    public String getBookCategory() {
        return bookCategory;
    }
    
    public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Long bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

	public Long getBookType() {
		return bookType;
	}

	public void setBookType(Long bookType) {
		this.bookType = bookType;
	}
    
}

package com.example.BookService.input;

public class HistoryPaymentInput {
	private int page;
    private int size;
    private Long userId;
    
    private String bookName;
    private String bookCategory;
    
    private String audioBookName;
    private String audioBookCategory;
    
    private String userName;
    private Long bookType;
    private String fromDate;
    private String toDate;
    
    private Long term;
    
    
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public String getAudioBookName() {
		return audioBookName;
	}
	public void setAudioBookName(String audioBookName) {
		this.audioBookName = audioBookName;
	}
	public String getAudioBookCategory() {
		return audioBookCategory;
	}
	public void setAudioBookCategory(String audioBookCategory) {
		this.audioBookCategory = audioBookCategory;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getBookType() {
		return bookType;
	}
	public void setBookType(Long bookType) {
		this.bookType = bookType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public HistoryPaymentInput(int page, int size, Long userId) {
		super();
		this.page = page;
		this.size = size;
		this.userId = userId;
	}
	public HistoryPaymentInput() {
		super();
	}
    
}

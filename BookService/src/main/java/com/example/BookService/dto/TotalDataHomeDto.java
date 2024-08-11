package com.example.BookService.dto;

public class TotalDataHomeDto {
	private Long totalReadingBook;
	private Long totalAudioBook;
	private Long totalPaymentInMonth;
	private Long totalMoneyPaymentInMonth;
	private Long totalMember;
	private Long totalMemberRegisInMonth;
	
	public Long getTotalReadingBook() {
		return totalReadingBook;
	}
	public void setTotalReadingBook(Long totalReadingBook) {
		this.totalReadingBook = totalReadingBook;
	}
	public Long getTotalAudioBook() {
		return totalAudioBook;
	}
	public void setTotalAudioBook(Long totalAudioBook) {
		this.totalAudioBook = totalAudioBook;
	}
	public Long getTotalPaymentInMonth() {
		return totalPaymentInMonth;
	}
	public void setTotalPaymentInMonth(Long totalPaymentInMonth) {
		this.totalPaymentInMonth = totalPaymentInMonth;
	}
	public Long getTotalMoneyPaymentInMonth() {
		return totalMoneyPaymentInMonth;
	}
	public void setTotalMoneyPaymentInMonth(Long totalMoneyPaymentInMonth) {
		this.totalMoneyPaymentInMonth = totalMoneyPaymentInMonth;
	}
	public Long getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(Long totalMember) {
		this.totalMember = totalMember;
	}
	public Long getTotalMemberRegisInMonth() {
		return totalMemberRegisInMonth;
	}
	public void setTotalMemberRegisInMonth(Long totalMemberRegisInMonth) {
		this.totalMemberRegisInMonth = totalMemberRegisInMonth;
	}
	public TotalDataHomeDto(Long totalReadingBook, Long totalAudioBook, Long totalPaymentInMonth,
			Long totalMoneyPaymentInMonth, Long totalMember, Long totalMemberRegisInMonth) {
		super();
		this.totalReadingBook = totalReadingBook;
		this.totalAudioBook = totalAudioBook;
		this.totalPaymentInMonth = totalPaymentInMonth;
		this.totalMoneyPaymentInMonth = totalMoneyPaymentInMonth;
		this.totalMember = totalMember;
		this.totalMemberRegisInMonth = totalMemberRegisInMonth;
	}
	public TotalDataHomeDto() {
		super();
	}
	
}

package com.example.BookService.output;

import java.util.List;

import com.example.BookService.entity.HistoryPayment;

public class HistoryPaymentOutput {
	private int page;
    private int totalPage;
    private List<HistoryPayment> historyPayments;
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
	public List<HistoryPayment> getHistoryPayments() {
		return historyPayments;
	}
	public void setHistoryPayments(List<HistoryPayment> historyPayments) {
		this.historyPayments = historyPayments;
	}
	public HistoryPaymentOutput(int page, int totalPage, List<HistoryPayment> historyPayments) {
		super();
		this.page = page;
		this.totalPage = totalPage;
		this.historyPayments = historyPayments;
	}
	public HistoryPaymentOutput() {
		super();
	}
}

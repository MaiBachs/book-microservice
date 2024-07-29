package com.example.BookService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookService.entity.HistoryPayment;
import com.example.BookService.repository.HistoryPaymentRepository;

@Service
public class HistoryPaymentService {
	@Autowired
	private HistoryPaymentRepository historyPaymentRepository;
	
	public HistoryPayment findByUserIdAndBookId(Long userId, Long bookId) {
		return historyPaymentRepository.findByUserIdAndBookId(userId, bookId);
	}
	
	public HistoryPayment save(HistoryPayment historyPayment) {
		return historyPaymentRepository.save(historyPayment);
	}
}

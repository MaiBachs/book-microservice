package com.example.BookService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookService.entity.HistoryPayment;
import com.example.BookService.entity.MemberManager;
import com.example.BookService.service.HistoryPaymentService;

import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/book-service/book")
@RequiredArgsConstructor
public class BookController {
	@Autowired
	private HistoryPaymentService historyPaymentService;
	
	@GetMapping(value = "/check-paid")
	public ResponseEntity<?> checkPaid(@RequestParam("userId") Long userId, @RequestParam("bookId") Long bookId){
		HistoryPayment historyPayment = historyPaymentService.findByUserIdAndBookId(userId, bookId);
		return ResponseEntity.ok(historyPayment);
	}
}

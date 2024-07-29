package com.example.BookService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.BookService.entity.HistoryPayment;

public interface HistoryPaymentRepository extends JpaRepository<HistoryPayment, Long>{
	HistoryPayment findByUserIdAndBookId(@Param("userId") Long userId,@Param("bookId") Long bookId );
}

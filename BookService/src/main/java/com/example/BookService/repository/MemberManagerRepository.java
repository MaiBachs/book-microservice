package com.example.BookService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BookService.entity.MemberManager;

public interface MemberManagerRepository extends JpaRepository<MemberManager, Long>{
	@Query("select mm from MemberManager mm where mm.userId = :userId and mm.startDate <= SYSDATE() and mm.endDate >= SYSDATE() ")
	MemberManager findByUserIdAndCurrentDate(Long userId);
}

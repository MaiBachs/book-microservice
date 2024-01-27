package com.example.BookService.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookService.entity.MemberManager;
import com.example.BookService.entity.Term;
import com.example.BookService.repository.MemberManagerRepository;
import com.example.BookService.repository.TermRepository;

@Service
public class MemberManagerService {
	@Autowired
	private MemberManagerRepository memberManagerRepository;
	@Autowired
	private TermRepository termRepository;
	
	public MemberManager register(MemberManager memberManager) {
		memberManager.setCreatedDate(new Date());
		memberManager.setStatus(1l);
		memberManager.setUpdatedDate(new Date());
		Term term = termRepository.findById(memberManager.getTermId()).orElse(null);
		memberManager.setStartDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 6);
		memberManager.setEndDate(calendar.getTime());
		return memberManagerRepository.save(memberManager);
	}
	
	public MemberManager checkRegister(Long userId) {
		return memberManagerRepository.findByUserIdAndCurrentDate(userId);
	}
}

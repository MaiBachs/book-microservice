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
		Term term = termRepository.findByTerm(memberManager.getTerm());
		memberManager.setStartDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, Integer.valueOf(term.getTerm().toString()));
		memberManager.setEndDate(calendar.getTime());
		memberManager.setTermId(term.getTermId());
		return memberManagerRepository.save(memberManager);
	}
	
	public MemberManager checkRegister(Long userId) {
		return memberManagerRepository.findByUserIdAndCurrentDate(userId);
	}
}

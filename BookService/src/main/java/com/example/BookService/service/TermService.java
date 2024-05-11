package com.example.BookService.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookService.entity.Term;
import com.example.BookService.repository.TermRepository;

@Service
public class TermService {
	@Autowired
	private TermRepository termRepository;
	
	public List<Term> searchAll() {
		return termRepository.findAll();
	}
	
	public Term add(Term term) {
		term.setCreatedDate(new Date());
		term.setUpdatedDate(new Date());
		term.setStatus(1l);
		return termRepository.save(term);
	}
	
	public Term edit(Term term) {
		term.setUpdatedDate(new Date());
		return termRepository.save(term);
	}
	
	public void delete(Term term) {
		termRepository.delete(term);
	}
}

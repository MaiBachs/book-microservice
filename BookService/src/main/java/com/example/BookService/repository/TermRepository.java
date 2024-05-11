package com.example.BookService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookService.entity.Term;

public interface TermRepository extends JpaRepository<Term, Long>{
	Term findByTerm(Long term);
}

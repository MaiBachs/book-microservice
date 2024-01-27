package com.example.BookService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookService.entity.Term;
import com.example.BookService.service.TermService;

import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/book-service/term")
@RequiredArgsConstructor
public class TermController {
	@Autowired
	private TermService termService;
	
	@GetMapping(value = "/search-all")
	public ResponseEntity<?> searchAll(){
		List<Term> terms = termService.searchAll();
		return ResponseEntity.ok(terms);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@RequestBody Term term){
		term = termService.add(term);
		return ResponseEntity.ok(term);
	}
	
	@PutMapping(value = "/edit")
	public ResponseEntity<?> edit(@RequestBody Term term){
		term = termService.edit(term);
		return ResponseEntity.ok(term);
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<?> delete(@RequestBody Term term){
		termService.delete(term);
		return ResponseEntity.ok(null);
	}
}

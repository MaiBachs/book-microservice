package com.example.BookService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookService.entity.MemberManager;
import com.example.BookService.service.MemberManagerService;

import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/book-service/member")
@RequiredArgsConstructor
public class MemberManagerController {
	@Autowired
	private MemberManagerService memberManagerService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody MemberManager memberManager){
		memberManager = memberManagerService.register(memberManager);
		return ResponseEntity.ok(memberManager);
	}
	
	@GetMapping(value = "/check-register")
	public ResponseEntity<?> checkRegister(@RequestParam("userId") Long userId){
		MemberManager memberManager = memberManagerService.checkRegister(userId);
		return ResponseEntity.ok(memberManager);
	}
}
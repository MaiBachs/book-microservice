package com.example.EBook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EBook.Service.BookService;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/e-book-service")
public class BookAPI {
    @Autowired
    private BookService bookService;
    
//    @GetMapping(value = "/getAllBook")
//    public List<BookEntity> getAllBook(){
//        return bookService.findAll();
//    }
//
//    @PostMapping(value = "/getBookByName")
//    public List<BookEntity> getBookByName(@RequestBody BookEntity bookEntity){
//        return bookService.findByBookName(bookEntity.getBookName());
//    }
//    @GetMapping(value = "/topBook")
//    public List<BookEntity> findTopBook(){
//        return bookService.findTopBook();
//    }
//    @PostMapping(value = "/getBookByCategory")
//    public BookOutput getBookByCategory(@RequestBody BookInput bookInput){
//        return bookService.findByBookCategory(bookInput.getPage()-1,  bookInput.getSize(), bookInput.getBookCategory());
//    }
    @PostMapping(value = "/getBookByPage")
    public ResponseEntity<BookOutput> findBookByPage(@RequestBody BookInput bookInput){
    	ResponseEntity<BookOutput> response = null;
        BookOutput bOutput = bookService.getBookByPage(bookInput.getPage()-1, bookInput.getSize());
        response = ResponseEntity.ok(bOutput);
        return response;
    }
}

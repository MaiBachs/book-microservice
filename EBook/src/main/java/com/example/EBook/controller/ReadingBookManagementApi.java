package com.example.EBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EBook.Service.BookService;
import com.example.EBook.factory.ResponseFactory;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;
import com.example.EBook.output.GeneralResponse;

import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/e-book-service/management")
@RequiredArgsConstructor
public class ReadingBookManagementApi {
	@Autowired
    private BookService bookService;
    @Autowired
    private ResponseFactory responseFactory;
    @Value("${book.path}")
    private String bookPath;
    
    @PostMapping(value = "/search-book-by-page")
    public ResponseEntity<GeneralResponse<BookOutput>> searchBookByPage(@RequestBody BookInput bookInput){
        BookOutput bookOutput = bookService.searchBookByPage(bookInput, bookInput.getPage()-1, bookInput.getSize());
        return responseFactory.success(bookOutput);
    }
}

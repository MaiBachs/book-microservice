package com.example.EBook.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.EBook.Service.BookService;
import com.example.EBook.entity.BookEntity;
import com.example.EBook.factory.ResponseFactory;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;
import com.example.EBook.output.GeneralResponse;

import lombok.RequiredArgsConstructor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/e-book-service/management")
@RequiredArgsConstructor
@ControllerAdvice
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
    
    @PostMapping(value = "/add-reading-book")
    public ResponseEntity<GeneralResponse<BookEntity>> addReadingBook(
    		@RequestParam("bookEntityStr") String bookEntityStr 
    		,@RequestParam("file") MultipartFile file){
    	BookEntity bookEntity = new BookEntity();
    	try {
    		Gson gson = new Gson();
    		bookEntity = gson.fromJson(bookEntityStr, new TypeToken<BookEntity>() {
            }.getType());
			bookEntity = bookService.addNewReadingBook(file, bookEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseFactory.success(bookEntity);
    }
    
    @PostMapping(value = "/edit")
    public ResponseEntity<GeneralResponse<BookEntity>> edit(
    		@RequestParam("bookEntityStr") String bookEntityStr 
    		,@RequestParam("file") MultipartFile file){
    	BookEntity bookEntity = new BookEntity();
    	try {
    		Gson gson = new Gson();
    		bookEntity = gson.fromJson(bookEntityStr, new TypeToken<BookEntity>() {
            }.getType());
			bookEntity = bookService.edit(file, bookEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseFactory.success(bookEntity);
    }
}

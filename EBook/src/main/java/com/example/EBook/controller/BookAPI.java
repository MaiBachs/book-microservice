package com.example.EBook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EBook.Service.BookService;
import com.example.EBook.entity.BookEntity;
import com.example.EBook.factory.ResponseFactory;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;
import com.example.EBook.output.GeneralResponse;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/e-book-service")
@RequiredArgsConstructor
public class BookAPI {
    @Autowired
    private BookService bookService;
    @Autowired
    private ResponseFactory responseFactory;
    @Value("${book.path}")
    private String bookPath;
    
    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> downloadPdf(
    		@RequestParam("pdfFileName") String pdfFileName
    		, @RequestParam(value = "userId", required = false) Long userId
    		, @RequestParam(value = "category", required = false) String category) throws IOException {
    	FileSystemResource pdfFile = new FileSystemResource(bookPath + "/" + pdfFileName);
    	bookService.ViewFilePdf(pdfFileName, userId, category);
        return ResponseEntity
                .ok()
                .contentLength(pdfFile.contentLength())
                .header("Content-Disposition", "inline; filename=\"" + pdfFile.getFilename() + "\"")
                .contentType(
                        MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
    
    @GetMapping(value = "/get-all-book")
    public ResponseEntity<GeneralResponse<List<BookEntity>>> getAllBook(){
         List<BookEntity> result = bookService.findAll();
         return responseFactory.success(result);
    }

    @PostMapping(value = "/get-book-by-name")
    public ResponseEntity<GeneralResponse<List<BookEntity>>> getBookByName(@RequestBody BookEntity bookEntity){
    	List<BookEntity> books = bookService.findByBookName(bookEntity.getBookName());
        return responseFactory.success(books);
    }
    @GetMapping(value = "/top-book")
    public ResponseEntity<GeneralResponse<List<BookEntity>>> findTopBook(){
    	List<BookEntity> books = bookService.findTopBook();
        return responseFactory.success(books);
    }
    @PostMapping(value = "/get-book-by-category")
    public ResponseEntity<GeneralResponse<BookOutput>> getBookByCategory(@RequestBody BookInput bookInput){
    	BookOutput bookOutput = bookService.findByBookCategory(bookInput.getPage()-1,  bookInput.getSize(), bookInput.getBookCategory());
        return responseFactory.success(bookOutput);
    }
    @PostMapping(value = "/get-book-by-page")
    public ResponseEntity<GeneralResponse<BookOutput>> findBookByPage(@RequestBody BookInput bookInput){
        BookOutput bookOutput = bookService.getBookByPage(bookInput.getPage()-1, bookInput.getSize(), bookInput);
        return responseFactory.success(bookOutput);
    }
    
    @GetMapping(value = "/detail-book")
    public ResponseEntity<GeneralResponse<BookEntity>> detailBook(@RequestParam("bookId") Long bookId){
    	BookEntity book = bookService.detailBook(bookId);
        return responseFactory.success(book);
    }
    
    @PostMapping(value = "/get-book-paid")
    public ResponseEntity<GeneralResponse<BookOutput>> getBookPaid(@RequestBody BookInput bookInput){
    	BookOutput bookOutput = bookService.getBookPaid(bookInput.getPage()-1, bookInput.getSize(), bookInput.getUserId());
        return responseFactory.success(bookOutput);
    }
    
    @GetMapping(value = "/recomend-for-user")
    public ResponseEntity<GeneralResponse<List<BookEntity>>> recomentForUser(@RequestParam("userId") Long userId){
    	List<BookEntity> books = bookService.recomentForUser(userId);
        return responseFactory.success(books);
    }
}

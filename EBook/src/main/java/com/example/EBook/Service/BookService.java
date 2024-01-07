package com.example.EBook.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.EBook.Repository.BookRepository;
import com.example.EBook.entity.BookEntity;
import com.example.EBook.output.BookOutput;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public BookOutput getBookByPage(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.findAll(pageable);
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
	}
}

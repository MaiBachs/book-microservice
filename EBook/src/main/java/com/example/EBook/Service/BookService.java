package com.example.EBook.Service;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.EBook.Repository.BookRepository;
import com.example.EBook.entity.BookEntity;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;

@Service
public class BookService {
	private static final int BUFFER_SIZE = 1024 * 8;
	@Value("${book.path}")
    private String uploadFolder;
	
	@Autowired
	private BookRepository bookRepository;
	
	public BookOutput getBookByPage(int page, int size, BookInput bookInput){
		Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = null;
        if(bookInput.getBookType() == 1l) {
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else if(bookInput.getBookType() == 2l){
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else if(bookInput.getBookType() == 3l) {
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else {
        	bookPage = bookRepository.findAll(pageable);
        }
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
	}
	
	public List<BookEntity> findAll() {
		return bookRepository.findAll();
	}
	
	public BookOutput findByBookCategory(int page, int size,String bookCategory) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.findByBookCategory(bookCategory, pageable);
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
    }
	
	public List<BookEntity> findTopBook() {
        return bookRepository.findTop10ByOrderByFavoriteDesc();
    }

    public List<BookEntity> findByBookName(String bookName) {
        return bookRepository.findByBookNameContaining(bookName);
    }
    
    public BookOutput searchBookByPage(BookInput bookInput, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.searchBookByPage(bookInput.getBookName(), bookInput.getBookAuthor(), bookInput.getBookCategory(), bookInput.getBookPrice(), pageable);
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
	}
    
    public BookEntity addNewReadingBook(MultipartFile file, BookEntity bookEntity) throws IOException {
    	File fileUpload = convertToFile(file);
    	Date date = new Date();
    	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".pdf";
    	File dirUpload = new File(uploadFolder);
    	saveFile(fileUpload, fileName, dirUpload);
    	bookEntity.setPreview(fileName);
    	bookEntity.setLastUpdate(new Date());
    	bookEntity.setFavorite(0l);
    	bookEntity.setView(0l);
    	bookEntity.setPurchases(0l);
    	bookEntity = bookRepository.save(bookEntity);
    	return bookEntity;
    }
    
    public File convertToFile(MultipartFile multipartFile) throws FileNotFoundException, IOException {
	   File file = new File("src/main/resources/targetFile.pdf");

	    try (OutputStream os = new FileOutputStream(file)) {
	       os.write(multipartFile.getBytes());
	    }
	    return file;
	}
    
    public static void saveFile(File f, String fileName, File desDir) throws IOException {
        InputStream stream = new FileInputStream(f);
        OutputStream out = new FileOutputStream(desDir.getAbsolutePath() + File.separator + fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        stream.close();
        out.close();
    }
    
    public void ViewFilePdf(String preview) {
    	BookEntity bookEntity = bookRepository.findByPreview(preview);
    	bookEntity.setView(bookEntity.getView() + 1l);
    	bookRepository.save(bookEntity);
    }
}

package com.example.Podcast.service;

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
import com.example.Podcast.Repository.AudioBookRepository;
import com.example.Podcast.entity.AudioBook;
import com.example.Podcast.input.AudioBookInput;
import com.example.Podcast.output.AudioBookOuput;

@Service
public class AudioBookService {
	@Autowired
	private AudioBookRepository audioBookRepository;
	private static final int BUFFER_SIZE = 1024 * 8;
	@Value("${book.path.user}")
    private String uploadFolderUser;
	@Value("${book.path.admin}")
    private String uploadFolderAdmin;
	
	public List<AudioBook> findAll(){
		return audioBookRepository.findAll();
	}
	
	public AudioBookOuput findByCategoryAndType(AudioBookInput input){
		Pageable pageable = PageRequest.of(input.getPage()-1, input.getSize());
		Page<AudioBook> page = audioBookRepository.findByCategoryAndType(input.getCategories().size() > 0 ? input.getCategories(): null, input.getAudioBookType(),pageable);
		AudioBookOuput audioBookOuput = new AudioBookOuput();
		audioBookOuput.setPage(input.getPage());
		audioBookOuput.setTotalPage(page.getTotalPages());
		audioBookOuput.setAudioBooks(page.getContent());
		return audioBookOuput;
	}
	
	public AudioBook detailBook(Long audioBookId) {
		return audioBookRepository.findById(audioBookId).orElse(null);
	}
	
	public AudioBookOuput getAudioBookPaid(int page, int size, Long userId) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Long> pageIds = audioBookRepository.getBookPaid(userId, pageable);
    	List<AudioBook> audioBooks = audioBookRepository.findAllById(pageIds.getContent());
    	AudioBookOuput audioBookOuput = new AudioBookOuput();
    	audioBookOuput.setPage(page+1);
    	audioBookOuput.setTotalPage(pageIds.getTotalPages());
    	audioBookOuput.setAudioBooks(audioBooks);
    	return audioBookOuput;
    }
	
	public AudioBookOuput searchBookByPage(AudioBookInput audioBookInput, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
        Page<AudioBook> bookPage = audioBookRepository.searchBookByPage(audioBookInput.getAudioBookName(), audioBookInput.getAudioBookAuthor(), audioBookInput.getAudioBookCategory(), audioBookInput.getAudioBookType(), pageable);
        AudioBookOuput audioBookOuput = new AudioBookOuput();
        audioBookOuput.setPage(page+1);
        audioBookOuput.setTotalPage(bookPage.getTotalPages());
        audioBookOuput.setAudioBooks(bookPage.getContent());
        return audioBookOuput;
	}
	
	public AudioBook addNewAudioBook(MultipartFile file, AudioBook audioBook) throws IOException {
    	File fileUpload = convertToFile(file);
    	Date date = new Date();
    	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".ogg";
    	File dirUploadUser = new File(uploadFolderUser);
    	File dirUploadAdmin = new File(uploadFolderAdmin);
    	saveFile(fileUpload, fileName, dirUploadUser);
    	saveFile(fileUpload, fileName, dirUploadAdmin);
    	audioBook.setPreview("audioBook\\" + fileName);
    	audioBook.setLastUpdate(new Date());
    	audioBook.setFavorite(0l);
    	audioBook.setView(0l);
    	audioBook.setPurchases(0l);
    	audioBook = audioBookRepository.save(audioBook);
    	return audioBook;
    }
	
	public File convertToFile(MultipartFile multipartFile) throws FileNotFoundException, IOException {
		   File file = new File("src/main/resources/targetFile.ogg");

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
    
    public void delete(Long bookId) {
    	audioBookRepository.deleteById(bookId);
    }
    
    public AudioBook edit(MultipartFile file, AudioBook audioBook) throws IOException {
    	if(file != null) {
    		File fileUpload = convertToFile(file);
        	Date date = new Date();
        	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".ogg";
        	File dirUploadUser = new File(uploadFolderUser);
        	File dirUploadAdmin = new File(uploadFolderAdmin);
        	saveFile(fileUpload, fileName, dirUploadUser);
        	saveFile(fileUpload, fileName, dirUploadAdmin);
        	audioBook.setPreview("audioBook\\" + fileName);
        	audioBook.setLastUpdate(new Date());
        	audioBook = audioBookRepository.save(audioBook);
    	}else {
    		audioBook = audioBookRepository.save(audioBook);
    	}
    	return audioBook;
    }
}

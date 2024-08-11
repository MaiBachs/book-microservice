package com.example.Podcast.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.Podcast.entity.AudioBook;
import com.example.Podcast.factory.ResponseFactory;
import com.example.Podcast.input.AudioBookInput;
import com.example.Podcast.output.AudioBookOuput;
import com.example.Podcast.output.GeneralResponse;
import com.example.Podcast.service.AudioBookService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/api/podcast-service/management")
public class AudioBookManagement {
	@Autowired
	private AudioBookService audioBookService;
	@Autowired
    private ResponseFactory responseFactory;
	
	@PostMapping(value = "/search-audio-book-by-page")
    public ResponseEntity<GeneralResponse<AudioBookOuput>> searchAudioBookByPage(@RequestBody AudioBookInput input){
		AudioBookOuput audioBookOutput = audioBookService.searchBookByPage(input, input.getPage()-1, input.getSize());
        return responseFactory.success(audioBookOutput);
    }
	
	@PostMapping(value = "/add-audio-book")
    public ResponseEntity<GeneralResponse<AudioBook>> addReadingBook(
    		@RequestParam("bookEntityStr") String bookEntityStr 
    		,@RequestParam("file") MultipartFile file){
		AudioBook audioBook = new AudioBook();
    	try {
    		Gson gson = new Gson();
    		audioBook = gson.fromJson(bookEntityStr, new TypeToken<AudioBook>() {
            }.getType());
    		audioBook = audioBookService.addNewAudioBook(file, audioBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseFactory.success(audioBook);
    }
}

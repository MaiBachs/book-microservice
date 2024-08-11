package com.example.Podcast.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Podcast.entity.AudioBook;
import com.example.Podcast.factory.ResponseFactory;
import com.example.Podcast.input.AudioBookInput;
import com.example.Podcast.output.AudioBookOuput;
import com.example.Podcast.output.GeneralResponse;
import com.example.Podcast.service.AudioBookService;

@Controller
@RequestMapping("/api/podcast-service/audio-book")
public class AudioBookController {
	@Autowired
	private AudioBookService audioBookService;
	@Autowired
    private ResponseFactory responseFactory;
	
	@GetMapping(value = "/get-all-audio-book")
    public ResponseEntity<GeneralResponse<List<AudioBook>>> getAllBook(){
         List<AudioBook> result = audioBookService.findAll();
         return responseFactory.success(result);
    }
	
	@PostMapping(value = "/get-audio-book-by-category")
    public ResponseEntity<GeneralResponse<AudioBookOuput>> findByCategoryAndType(@RequestBody AudioBookInput input){
		 AudioBookOuput result = audioBookService.findByCategoryAndType(input);
         return responseFactory.success(result);
    }
	
	@GetMapping(value = "/detail-audio-book")
    public ResponseEntity<GeneralResponse<AudioBook>> detailBook(@RequestParam("audioBookId") Long audioBookId){
		AudioBook audioBook = audioBookService.detailBook(audioBookId);
        return responseFactory.success(audioBook);
    }
	
	@PostMapping(value = "/get-audio-book-paid")
    public ResponseEntity<GeneralResponse<AudioBookOuput>> getAudioBookPaid(@RequestBody AudioBookInput input){
		AudioBookOuput bookOutput = audioBookService.getAudioBookPaid(input.getPage()-1, input.getSize(), input.getUserId());
        return responseFactory.success(bookOutput);
    }
}

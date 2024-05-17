package com.example.Podcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Podcast.entity.AudioBook;
import com.example.Podcast.factory.ResponseFactory;
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
}

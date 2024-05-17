package com.example.Podcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Podcast.Repository.AudioBookRepository;
import com.example.Podcast.entity.AudioBook;

@Service
public class AudioBookService {
	@Autowired
	private AudioBookRepository audioBookRepository;
	
	public List<AudioBook> findAll(){
		return audioBookRepository.findAll();
	}
}

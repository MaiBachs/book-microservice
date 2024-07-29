package com.example.Podcast.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Podcast.Repository.AudioBookRepository;
import com.example.Podcast.entity.AudioBook;
import com.example.Podcast.input.AudioBookInput;
import com.example.Podcast.output.AudioBookOuput;

@Service
public class AudioBookService {
	@Autowired
	private AudioBookRepository audioBookRepository;
	
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
}

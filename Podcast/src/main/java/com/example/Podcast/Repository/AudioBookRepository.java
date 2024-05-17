package com.example.Podcast.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Podcast.entity.AudioBook;

@Repository
public interface AudioBookRepository extends JpaRepository<AudioBook, Long>{

}

package com.example.Podcast.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Podcast.entity.AudioBook;

@Repository
public interface AudioBookRepository extends JpaRepository<AudioBook, Long>{
	@Query("select ab from AudioBook ab where 1=1 "
			+ "and (:categories is null or ab.audioBookCategory in (:categories)) "
			+ "and (:audioBookType is null or ab.audioBookType = :audioBookType) ")
	Page<AudioBook> findByCategoryAndType(@Param("categories") List<String> categories,@Param("audioBookType") Long audioBookType, Pageable pageable);
}

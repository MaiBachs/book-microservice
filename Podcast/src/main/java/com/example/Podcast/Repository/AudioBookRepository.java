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

	@Query(value = "select h.audio_book_id "
			+ "from book.history_payment h "
			+ "where h.audio_book_id is not null "
			+ "and h.user_id = :userId ", nativeQuery = true)
	Page<Long> getBookPaid(@Param("userId") Long userId, Pageable pageable);
	
	@Query(value = "select ab from AudioBook ab where 1=1 "
			+ "and (:audioBookName is null or upper(ab.audioBookName) like concat('%',upper(:audioBookName),'%' ) )"
			+ "and (:audioBookAuthor is null or upper(ab.audioBookAuthor) like concat('%',upper(:audioBookAuthor),'%' ) )"
			+ "and (:audioBookCategory is null or upper(ab.audioBookCategory) like concat('%',upper(:audioBookCategory),'%' ) )"
			+ "and (:audioBookType is null or ab.audioBookType = :audioBookType ) "
			+ "order by ab.audioBookName asc ")
	Page<AudioBook> searchBookByPage(String audioBookName, String audioBookAuthor, String audioBookCategory, Long audioBookType, Pageable pageable);
}

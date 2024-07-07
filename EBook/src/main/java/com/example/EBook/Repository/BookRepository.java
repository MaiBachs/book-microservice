package com.example.EBook.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.EBook.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	Page<BookEntity> findByBookCategory(String bookCategory, Pageable pageable);
	List<BookEntity> findByBookNameContaining(String bookName);
	List<BookEntity> findTop10ByOrderByFavoriteDesc();
	@Query(value = "select be from BookEntity be where 1=1 "
			+ "and (:bookName is null or upper(be.bookName) like concat('%',upper(:bookName),'%' ) )"
			+ "and (:bookAuthor is null or upper(be.bookName) like concat('%',upper(:bookAuthor),'%' ) )"
			+ "and (:bookCategory is null or upper(be.bookName) like concat('%',upper(:bookCategory),'%' ) )"
			+ "and (:bookPrice = 0 and be.bookPrice = 0) or (:bookPrice = 1 and be.bookPrice >= 1) "
			+ "order by bookName asc ")
	Page<BookEntity> searchBookByPage(String bookName, String bookAuthor, String bookCategory, Long bookPrice, Pageable pageable);
}

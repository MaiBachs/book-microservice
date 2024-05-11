package com.example.EBook.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.EBook.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	Page<BookEntity> findByBookCategory(String bookCategory, Pageable pageable);
	List<BookEntity> findByBookNameContaining(String bookName);
	List<BookEntity> findTop10ByOrderByFavoriteDesc();
}

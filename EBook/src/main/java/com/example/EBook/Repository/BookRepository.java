package com.example.EBook.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.EBook.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	Page<BookEntity> findByBookCategory(String bookCategory, Pageable pageable);
	List<BookEntity> findByBookNameContaining(String bookName);
	List<BookEntity> findTop10ByOrderByViewDesc();
	@Query(value = "select be from BookEntity be where 1=1 "
			+ "and (:bookName is null or upper(be.bookName) like concat('%',upper(:bookName),'%' ) )"
			+ "and (:bookAuthor is null or upper(be.bookAuthor) like concat('%',upper(:bookAuthor),'%' ) )"
			+ "and (:bookCategory is null or upper(be.bookCategory) like concat('%',upper(:bookCategory),'%' ) )"
			+ "and (:bookType is null or be.bookType = :bookType ) "
			+ "order by bookName asc ")
	Page<BookEntity> searchBookByPage(String bookName, String bookAuthor, String bookCategory, Long bookType, Pageable pageable);
	
	BookEntity findByPreview(String preview);
	
	Page<BookEntity> findByBookType(Long bookType, Pageable pageable);
	
	@Query(value = "select h.book_id "
			+ "from book.history_payment h "
			+ "where h.book_id is not null "
			+ "and h.user_id = :userId ", nativeQuery = true)
	Page<Long> getBookPaid(@Param("userId") Long userId, Pageable pageable);
}

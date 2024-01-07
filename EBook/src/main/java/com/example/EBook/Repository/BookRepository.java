package com.example.EBook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.EBook.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    
}

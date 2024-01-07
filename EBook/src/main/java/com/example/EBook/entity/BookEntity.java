package com.example.EBook.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String bookName;
    @Column
    private String bookAuthor;
    @Column
    private String bookCategory;
    @Column(columnDefinition = "LONGTEXT")
    private String bookDescription;
    @Column
    private Long view;
    @Column
    private String coverBook;
    @Column
    private Long bookPrice;
    @Column
    private Long purchases;
    
    public BookEntity() {
    }

	public BookEntity(Long id, String bookName, String bookAuthor, String bookCategory, String bookDescription,
			Long view, String coverBook, Long bookPrice, Long purchases) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookDescription = bookDescription;
		this.view = view;
		this.coverBook = coverBook;
		this.bookPrice = bookPrice;
		this.purchases = purchases;
	}
}

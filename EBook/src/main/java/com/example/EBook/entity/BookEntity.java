package com.example.EBook.entity;

import java.util.Date;
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
    @Column
    private String preview;
    @Column
    private Long favorite;
    @Column
    private Date lastUpdate;
    @Column
    private String lastUser;
    @Column
    private Long bookType;
    
    public BookEntity() {
    }

	public BookEntity(Long id, String bookName, String bookAuthor, String bookCategory, String bookDescription,
			Long view, String coverBook, Long bookPrice, Long purchases, String preview, Long favorite, Date lastUpdate,
			String lastUser, Long bookType) {
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
		this.preview = preview;
		this.favorite = favorite;
		this.lastUpdate = lastUpdate;
		this.lastUser = lastUser;
		this.bookType = bookType;
	}

	public String getPreview() {
		return preview;
	}
	
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	public Long getFavorite() {
		return favorite;
	}

	public void setFavorite(Long favorite) {
		this.favorite = favorite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Long getView() {
		return view;
	}

	public void setView(Long view) {
		this.view = view;
	}

	public String getCoverBook() {
		return coverBook;
	}

	public void setCoverBook(String coverBook) {
		this.coverBook = coverBook;
	}

	public Long getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Long bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Long getPurchases() {
		return purchases;
	}

	public void setPurchases(Long purchases) {
		this.purchases = purchases;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public Long getBookType() {
		return bookType;
	}

	public void setBookType(Long bookType) {
		this.bookType = bookType;
	}

}


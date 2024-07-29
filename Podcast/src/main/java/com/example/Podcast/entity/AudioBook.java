package com.example.Podcast.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AudioBook {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String audioBookAuthor;
	@Column
	private String audioBookCategory;
	@Column(columnDefinition = "LONGTEXT")
	private String audioBookDescription;
	@Column
	private String audioBookName;
	@Column
	private Long audioBookPrice;
	@Column
	private String coverAudioBook;
	@Column
	private Long favorite;
	@Column
	private String audio;
	@Column
	private Long purchases;
	@Column
	private Long view;
	@Column
	private String preview;
	@Column
	private Long audioBookType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAudioBookAuthor() {
		return audioBookAuthor;
	}
	public void setAudioBookAuthor(String audioBookAuthor) {
		this.audioBookAuthor = audioBookAuthor;
	}
	
	public String getAudioBookCategory() {
		return audioBookCategory;
	}
	public void setAudioBookCategory(String audioBookCategory) {
		this.audioBookCategory = audioBookCategory;
	}
	public String getAudioBookDescription() {
		return audioBookDescription;
	}
	public void setAudioBookDescription(String audioBookDescription) {
		this.audioBookDescription = audioBookDescription;
	}
	public String getAudioBookName() {
		return audioBookName;
	}
	public void setAudioBookName(String audioBookName) {
		this.audioBookName = audioBookName;
	}
	public Long getAudioBookPrice() {
		return audioBookPrice;
	}
	public void setAudioBookPrice(Long audioBookPrice) {
		this.audioBookPrice = audioBookPrice;
	}
	public String getCoverAudioBook() {
		return coverAudioBook;
	}
	public void setCoverAudioBook(String coverAudioBook) {
		this.coverAudioBook = coverAudioBook;
	}
	public Long getFavorite() {
		return favorite;
	}
	public void setFavorite(Long favorite) {
		this.favorite = favorite;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public Long getPurchases() {
		return purchases;
	}
	public void setPurchases(Long purchases) {
		this.purchases = purchases;
	}
	public Long getView() {
		return view;
	}
	public void setView(Long view) {
		this.view = view;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	public Long getAudioBookType() {
		return audioBookType;
	}
	public void setAudioBookType(Long audioBookType) {
		this.audioBookType = audioBookType;
	}
	
	public AudioBook(Long id, String audioBookAuthor, String audioBookCategory, String audioBookDescription,
			String audioBookName, Long audioBookPrice, String coverAudioBook, Long favorite, String audio,
			Long purchases, Long view, String preview, Long audioBookType) {
		super();
		this.id = id;
		this.audioBookAuthor = audioBookAuthor;
		this.audioBookCategory = audioBookCategory;
		this.audioBookDescription = audioBookDescription;
		this.audioBookName = audioBookName;
		this.audioBookPrice = audioBookPrice;
		this.coverAudioBook = coverAudioBook;
		this.favorite = favorite;
		this.audio = audio;
		this.purchases = purchases;
		this.view = view;
		this.preview = preview;
		this.audioBookType = audioBookType;
	}
	public AudioBook() {
		super();
	}
	
	
}
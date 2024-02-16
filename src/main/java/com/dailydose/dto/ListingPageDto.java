package com.dailydose.dto;

public class ListingPageDto {
	
	private Long postId;
	private String link;
	private String title;
	private String author;
	private String category;
	private String postedDate;
	
	public ListingPageDto(Long postId, String link, String title, String author, String category,
			String postedDate) {
		super();
		this.postId = postId;
		this.link = link;
		this.title = title;
		this.author = author;
		this.category = category;
		this.postedDate = postedDate;
	}

	public ListingPageDto() {
		super();
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "ListingPageDto [postId=" + postId + ", link=" + link + ", title=" + title + ", author=" + author
				+ ", category=" + category + ", postedDate=" + postedDate + "]";
	}
	
	
}

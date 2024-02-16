package com.dailydose.dto;

public class FeaturedPostsDto {
	
	private Long postId;
	private String link;
	private String title;
	private String featuredImage;
	private String author;
	private String postedDate;
	private String shortDesc;
	private String category;
	
	public FeaturedPostsDto() {
		super();
	}

	public FeaturedPostsDto(Long postId, String link, String title, String featuredImage, String author,
			String postedDate, String shortDesc,String category) {
		super();
		this.postId = postId;
		this.link = link;
		this.title = title;
		this.featuredImage = featuredImage;
		this.author = author;
		this.postedDate = postedDate;
		this.shortDesc = shortDesc;
		this.category = category;
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

	public String getFeaturedImage() {
		return featuredImage;
	}

	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "FeaturedPostsDto [postId=" + postId + ", link=" + link + ", title=" + title + ", featuredImage="
				+ featuredImage + ", author=" + author + ", postedDate=" + postedDate + ", shortDesc=" + shortDesc
				+ ", category=" + category + "]";
	}

	
	
}

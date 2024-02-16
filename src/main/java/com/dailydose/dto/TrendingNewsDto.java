package com.dailydose.dto;

public class TrendingNewsDto {
	
	private Long postId;
	private String link;
	private String title;
	private String shortDesc;
	private String category;
	private String trendingImage;
	private String author;
	private String postedDate;
	
	public TrendingNewsDto() {
		super();
	}

	public TrendingNewsDto(Long postId, String link, String title, String shortDesc, String trendingImage,
			String author, String postedDate,String category) {
		super();
		this.postId = postId;
		this.link = link;
		this.title = title;
		this.shortDesc = shortDesc;
		this.trendingImage = trendingImage;
		this.author = author;
		this.postedDate = postedDate;
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

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getTrendingImage() {
		return trendingImage;
	}

	public void setTrendingImage(String trendingImage) {
		this.trendingImage = trendingImage;
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
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "TrendingNewsDto [postId=" + postId + ", link=" + link + ", title=" + title + ", shortDesc=" + shortDesc
				+ ", category=" + category + ", trendingImage=" + trendingImage + ", author=" + author + ", postedDate="
				+ postedDate + "]";
	}


	
	
}

package com.dailydose.dto;

public class TopPostsDTO {
	
	private Long postId;
	private String shortTitle;
	private String topNewsImage;
	private String category;
	private String postedDate;
	private String title;
	
	public TopPostsDTO() {
		super();
	}
	
	public TopPostsDTO(Long postId, String shortTitle, String topNewsImage, String category, 
			String postedDate,String title) {
		super();
		this.postId = postId;
		this.shortTitle = shortTitle;
		this.topNewsImage = topNewsImage;
		this.category = category;
		this.postedDate = postedDate;
		this.title = title;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getTopNewsImage() {
		return topNewsImage;
	}
	public void setTopNewsImage(String topNewsImage) {
		this.topNewsImage = topNewsImage;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "TopPostsDTO [postId=" + postId + ", shortTitle=" + shortTitle + ", topNewsImage=" + topNewsImage
				+ ", category=" + category + ", postedDate=" + postedDate + ", title=" + title + "]";
	}
	
	
}

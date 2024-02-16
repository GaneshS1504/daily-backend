package com.dailydose.dto;

public class PostsDto {
	
	private Long postId;
	private String shortTitle;
	private String featuredImage;
	private String category;
	private String postedDate;
	private String shortDesc;
	
	public PostsDto() {
		super();
	}
	
	public PostsDto(Long postId, String shortTitle, String featuredImage, String category, 
			String postedDate,String shortDesc) {
		super();
		this.postId = postId;
		this.shortTitle = shortTitle;
		this.featuredImage = featuredImage;
		this.category = category;
		this.postedDate = postedDate;
		this.shortDesc = shortDesc;
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
	public String getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(String bannerImage) {
		this.featuredImage = bannerImage;
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
	
	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	@Override
	public String toString() {
		return "PostsDto [postId=" + postId + ", shortTitle=" + shortTitle + ", featuredImage=" + featuredImage
				+ ", category=" + category + ", postedDate=" + postedDate + ", shortDesc=" + shortDesc + "]";
	}

	
	
}

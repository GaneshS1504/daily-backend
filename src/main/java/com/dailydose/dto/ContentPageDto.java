package com.dailydose.dto;


public class ContentPageDto {
	
	private Long postId;
	private String title;
	private String bannerImage;
	private String desc;
	private String author;
	private String postedDate;
	
	public ContentPageDto() {
		super();
	}

	public ContentPageDto(Long postId, String title, String bannerImage, String desc, String author,
			String postedDate) {
		super();
		this.postId = postId;
		this.title = title;
		this.bannerImage = bannerImage;
		this.desc = desc;
		this.author = author;
		this.postedDate = postedDate;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	@Override
	public String toString() {
		return "ContentPageDto [postId=" + postId + ", title=" + title + ", bannerImage=" + bannerImage + ", desc="
				+ desc + ", author=" + author + ", postedDate=" + postedDate + "]";
	}
	
	
	
}

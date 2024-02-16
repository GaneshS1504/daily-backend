package com.dailydose.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "col_posts")
public class Posts {
	
	@Id
	private String id;
	private Long postId;
	private String link;
	private String title;
	private String shortDesc;
	private String desc;
	private String category;
	private String subCategory;
	private String author;
	private String postedDate;
	private String modifiedDate;
	private String stage;
	
	public Posts() {
		super();
	}

	public Posts(String id, Long postId, String link, String title, String shortDesc, String desc,
			String category, String subCategory, String author, String postedDate, String modifiedDate, String stage) {
		super();
		this.id = id;
		this.postId = postId;
		this.link = link;
		this.title = title;
		this.shortDesc = shortDesc;
		this.desc = desc;
		this.category = category;
		this.subCategory = subCategory;
		this.author = author;
		this.postedDate = postedDate;
		this.modifiedDate = modifiedDate;
		this.stage = stage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", postId=" + postId + ", link=" + link + ", title=" + title + ", shortDesc="
				+ shortDesc + ", desc=" + desc + ", category=" + category + ", subCategory=" + subCategory + ", author="
				+ author + ", postedDate=" + postedDate + ", modifiedDate=" + modifiedDate + ", stage=" + stage + "]";
	}

	
}

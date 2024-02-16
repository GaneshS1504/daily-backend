package com.dailydose.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Table(name = "tbl_posts_images",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"postsId","imageType"})})
@Entity
public class PostsImages {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	private String path;
	
	@Column(nullable = false)
	private Long postsId;
	
	@Column(nullable = false)
	private String imageType;

	public PostsImages(Long id, String path, Long postsId, String imageType) {
		super();
		Id = id;
		this.path = path;
		this.postsId = postsId;
		this.imageType = imageType;
	}

	public PostsImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getPostsId() {
		return postsId;
	}

	public void setPostsId(Long postsId) {
		this.postsId = postsId;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "PostsImages [Id=" + Id + ", path=" + path + ", postsId=" + postsId + ", imageType=" + imageType + "]";
	}
	
	
}

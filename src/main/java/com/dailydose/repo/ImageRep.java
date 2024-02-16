package com.dailydose.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailydose.model.PostsImages;

@Repository
public interface ImageRep extends JpaRepository<PostsImages, Long> {
	
	Optional<List<PostsImages>> findByPostsId(Long postId);

	void deleteByPostsId(Long postId);
	
	
}

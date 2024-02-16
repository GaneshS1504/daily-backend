package com.dailydose.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dailydose.dto.ListingPageDto;
import com.dailydose.dto.PostsDto;
import com.dailydose.dto.TopPostsDTO;
import com.dailydose.model.FinalPosts;
import com.dailydose.model.Posts;
import com.dailydose.model.PostsImages;
import com.dailydose.repo.ImageRep;
import com.dailydose.repo.PostsRepo;
import com.dailydose.utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;

@Service
public class PostsService {

	private PostsRepo postsRepo;
	private ImageRep imageRep;
	private ObjectMapper objectMapper;

	@Autowired
	private FileUtils fileUtils;

	public PostsService(PostsRepo postsRepo, ImageRep imageRep, ObjectMapper objectMapper) {
		super();
		this.postsRepo = postsRepo;
		this.imageRep = imageRep;
		this.objectMapper = objectMapper;
	}

	public Posts addNewPost(Posts newPost) {

		Posts latestPost = getLastAddedPost();
		Long latestPostId;
		if (latestPost == null) {
			latestPostId = Long.valueOf(1);
		} else {
			latestPostId = latestPost.getPostId();
			latestPostId += 1;
		}
		newPost.setPostId(latestPostId);
		LocalDateTime postedDate = LocalDateTime.now();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatedPostedDate = postedDate.format(format);

		newPost.setPostedDate(formatedPostedDate);
		newPost.setAuthor("Ganesh");
		newPost.setStage("draft");
		return postsRepo.addPosts(newPost);
	}

	private Posts getLastAddedPost() {

		return postsRepo.getLatestPost();
	}

	public PostsImages insertImagePath(String imagePath, Long postId, String imageType) {

		PostsImages postImage = new PostsImages();
		postImage.setPath(imagePath);
		postImage.setPostsId(postId);
		postImage.setImageType(imageType);

		return imageRep.save(postImage);
	}

	public FinalPosts submitNewPost(Posts newPost, Long postId) {

		Optional<List<PostsImages>> images = imageRep.findByPostsId(postId);
		FinalPosts finalPosts = objectMapper.convertValue(newPost, FinalPosts.class);

		if (images.isPresent()) {
			
			Map<Long, String> bannerImage = images.get().stream()
					.filter((obj) -> obj.getImageType().equalsIgnoreCase("banner"))
					.collect(Collectors.toMap((map) -> map.getPostsId(), (map) -> map.getPath()));


			Map<Long, String> featureImage = images.get().stream()
					.filter((obj) -> obj.getImageType().equalsIgnoreCase("feature"))
					.collect(Collectors.toMap((map) -> map.getPostsId(), (map) -> map.getPath()));

			Map<Long, String> topImage = images.get().stream()
					.filter((obj) -> obj.getImageType().equalsIgnoreCase("topImage"))
					.collect(Collectors.toMap((map) -> map.getPostsId(), (map) -> map.getPath()));

			Map<Long, String> trendingImages = images.get().stream()
					.filter((obj) -> obj.getImageType().equalsIgnoreCase("trending"))
					.collect(Collectors.toMap((map) -> map.getPostsId(), (map) -> map.getPath()));
			
			if(!bannerImage.isEmpty()) {
				finalPosts.setBannerImage(bannerImage.get(postId));
			}

			if (!featureImage.isEmpty()) {
				finalPosts.setFeaturedNews(true);
				finalPosts.setPopular(true);
				finalPosts.setFeaturedImage(featureImage.get(postId));
				finalPosts.setBannerImage(featureImage.get(postId));
			}
			if (!topImage.isEmpty()) {
				finalPosts.setIstopNews(true);
				finalPosts.setTopNewsImage(topImage.get(postId));
				finalPosts.setBannerImage(topImage.get(postId));
			}
			if (!trendingImages.isEmpty()) {
				finalPosts.setTrendingNews(true);
				finalPosts.setTrendingImage(trendingImages.get(postId));
				finalPosts.setBannerImage(trendingImages.get(postId));
			}

			finalPosts.setPostId(postId);
			finalPosts.setStage("final");
			finalPosts.setActive(true);
		}

		FinalPosts newLivePost = postsRepo.submitFinalPosts(finalPosts);
		return newLivePost;
	}

	public Map<String, Object> getPost(String category, String shortTitle) {

		Map<String, Object> response = new HashMap<String, Object>();
		List<FinalPosts> post = postsRepo.getPostsById(shortTitle);
		response.put("content", post);

		List<FinalPosts> relatedCategory = postsRepo.getRelatedCategoryData(category, shortTitle);
		List<PostsDto> relatedCategoryDto = objectMapper.convertValue(relatedCategory,
				new TypeReference<List<PostsDto>>() {
				});
		response.put("category", relatedCategoryDto);

		List<FinalPosts> featuredPosts = postsRepo.getFeaturedPostsOnBlogPage(shortTitle);
		List<PostsDto> featuredPostsDto = objectMapper.convertValue(featuredPosts, new TypeReference<List<PostsDto>>() {
		});

		response.put("featured", featuredPostsDto);
		response.put("popular", featuredPostsDto);

		List<FinalPosts> latestPosts = postsRepo.getLatestPosts(shortTitle);
		List<PostsDto> latestPostsDto = objectMapper.convertValue(latestPosts, new TypeReference<List<PostsDto>>() {
		});

		response.put("latest", latestPostsDto);

		return response;

	}

	public Map<String, Object> getTabData() {

		Map<String, Object> response = new HashMap<String, Object>();

		List<FinalPosts> featuredPosts = postsRepo.getTabDataOnHomePage();
		List<PostsDto> featuredPostsDto = objectMapper.convertValue(featuredPosts, new TypeReference<List<PostsDto>>() {
		});

		response.put("featured", featuredPostsDto);
		response.put("popular", featuredPostsDto);

		List<FinalPosts> latestPosts = postsRepo.getLatestPostsOnHomePage();
		List<PostsDto> latestPostsDto = objectMapper.convertValue(latestPosts, new TypeReference<List<PostsDto>>() {
		});

		response.put("latest", latestPostsDto);

		return response;
	}

	public List<TopPostsDTO> getTopNews() {

		List<FinalPosts> topPosts = postsRepo.getTopNewsOnPageLoad();
		List<TopPostsDTO> topPostsDto = objectMapper.convertValue(topPosts, new TypeReference<List<TopPostsDTO>>() {
		});

		return topPostsDto;
	}

	public Map<String, Object> getAllCategoriesPosts() {

		Map<String, Object> allCategoriesData = new HashMap<String, Object>();

		List<FinalPosts> sportsPosts = postsRepo.getSportsPosts();
		List<PostsDto> sportsPostsDto = objectMapper.convertValue(sportsPosts, new TypeReference<List<PostsDto>>() {
		});

		allCategoriesData.put("sports", sportsPostsDto);

		List<FinalPosts> businessPosts = postsRepo.getBusinessPosts();
		List<PostsDto> businessPostsDto = objectMapper.convertValue(businessPosts, new TypeReference<List<PostsDto>>() {
		});

		allCategoriesData.put("businness", businessPostsDto);

		List<FinalPosts> technologyPosts = postsRepo.getTechnologyPosts();
		List<PostsDto> technolgyPostsDto = objectMapper.convertValue(technologyPosts,
				new TypeReference<List<PostsDto>>() {
				});

		allCategoriesData.put("news", technolgyPostsDto);

		List<FinalPosts> entertainmentPosts = postsRepo.getEntertainmentPosts();
		List<PostsDto> entertainmentPostsDto = objectMapper.convertValue(entertainmentPosts,
				new TypeReference<List<PostsDto>>() {
				});

		allCategoriesData.put("entertainment", entertainmentPostsDto);

		return allCategoriesData;
	}

	public List<ListingPageDto> getAllPosts() {

		List<FinalPosts> posts = postsRepo.getAllPosts();
		List<ListingPageDto> postsDto = objectMapper.convertValue(posts, new TypeReference<List<ListingPageDto>>() {
		});

		return postsDto;
	}

	public FinalPosts updatePostById(Long postId, FinalPosts post) {
		return postsRepo.updatePostById(postId, post);
	}

	public List<FinalPosts> getByPostId(Long postId) {
		return this.postsRepo.getPostById(postId);
	}

	@Modifying
	@Transactional
	public DeleteResult deletePost(Long postId) {

		Optional<List<PostsImages>> imagesTobeDeleted = imageRep.findByPostsId(postId);

		List<String> images = new ArrayList<String>();
		String response = null;
		if (!imagesTobeDeleted.isEmpty()) {
			List<String> imagePaths = imagesTobeDeleted.get().stream().map((obj) -> obj.getPath())
					.collect(Collectors.toList());

			for (String s : imagePaths) {
				String[] imagePathArray = s.split("/");
				images.add(imagePathArray[3]);
			}
		}

		if (!imagesTobeDeleted.isEmpty()) {
			response = fileUtils.removeImage(images);
			imageRep.deleteByPostsId(postId);
		}

		return postsRepo.deletePost(postId);

	}

}

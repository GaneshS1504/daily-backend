package com.dailydose.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dailydose.dto.ContentPageDto;
import com.dailydose.dto.FeaturedPostsDto;
import com.dailydose.dto.LatestPostsDto;
import com.dailydose.dto.TopNewsDto;
import com.dailydose.dto.TopPostsDTO;
import com.dailydose.dto.TrendingNewsDto;
import com.dailydose.model.FinalPosts;
import com.dailydose.repo.DailyDoseRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DailyDoseService {
	
	private DailyDoseRepo dailyDoseRepo;
	private ObjectMapper objectMapper;

	public DailyDoseService(DailyDoseRepo dailyDoseRepo,ObjectMapper objectMapper) {
		super();
		this.dailyDoseRepo = dailyDoseRepo;
		this.objectMapper = objectMapper;
	}
	
	public Map<String,Object> getTrendingNewsOnPageLoad(){
		
		Map<String,Object> postsResponse = new HashMap<String, Object>();
		
		List<FinalPosts> trendingNewsList = dailyDoseRepo.getTredingNewsOnLoad();
		List<TrendingNewsDto> trendingNewsDto = objectMapper.convertValue(trendingNewsList,
				new TypeReference<List<TrendingNewsDto>>() {
				});
		
		postsResponse.put("trendingNews", trendingNewsDto);
		
		List<FinalPosts> featuredPostsList = dailyDoseRepo.getFeaturedNewsOnLoad();
		List<FeaturedPostsDto> featuredPostsDto = objectMapper.convertValue(featuredPostsList,
				new TypeReference<List<FeaturedPostsDto>>() {
				});
		
		postsResponse.put("featuredNews", featuredPostsDto);
		
		List<FinalPosts> latestPostsList = dailyDoseRepo.getLatestPostsOnHomePage();
		List<LatestPostsDto> latestPostsDto = objectMapper.convertValue(latestPostsList,
				new TypeReference<List<LatestPostsDto>>() {
				});
		
		postsResponse.put("latestNews", latestPostsDto);
		
		postsResponse.put("popularNews", featuredPostsDto);
		
		List<FinalPosts> sportsPostsList = dailyDoseRepo.getSportsPosts();
		List<LatestPostsDto> sportsPostsDto = objectMapper.convertValue(sportsPostsList,
				new TypeReference<List<LatestPostsDto>>() {
				});
		
		postsResponse.put("sportsPosts", sportsPostsDto);
		
		List<FinalPosts> technologyPostsList = dailyDoseRepo.getTechnologyPosts();
		List<LatestPostsDto> technologyPostsDto = objectMapper.convertValue(technologyPostsList,
				new TypeReference<List<LatestPostsDto>>() {
				});
		
		postsResponse.put("technologyPosts", technologyPostsDto);
		
		List<FinalPosts> businessPostsList = dailyDoseRepo.getBusinessPosts();
		List<LatestPostsDto> businessPostsDto = objectMapper.convertValue(businessPostsList,
				new TypeReference<List<LatestPostsDto>>() {
				});
		
		postsResponse.put("businessPosts", businessPostsDto);
		
		List<FinalPosts> entertainmentPostsList = dailyDoseRepo.getEntertainmentPosts();
		List<LatestPostsDto> entertainmentPostsDto = objectMapper.convertValue(entertainmentPostsList,
				new TypeReference<List<LatestPostsDto>>() {
				});
		
		postsResponse.put("entertainmentPosts", entertainmentPostsDto);
		
		List<FinalPosts> topPostsList = dailyDoseRepo.getTopNewsOnLoad();
		List<TopNewsDto> topPostsDto = objectMapper.convertValue(topPostsList,
				new TypeReference<List<TopNewsDto>>() {
				});
		
		postsResponse.put("topPosts", topPostsDto);
		
		return postsResponse;
	}
	
	public Map<String, Object> getContentOnBlogPage(String category,String link){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		List<FinalPosts> blog = dailyDoseRepo.getContentPageData(category, link);
		List<ContentPageDto> blogDto = objectMapper.convertValue(blog, new TypeReference<List<ContentPageDto>>() {
		});
		
		response.put("content", blogDto);
		
		List<FinalPosts> latestPosts = dailyDoseRepo.getLatestPostsOnContentPage(category, link);
		List<LatestPostsDto> latestPostsDto = objectMapper.convertValue(latestPosts, new TypeReference<List<LatestPostsDto>>() {
		});
		
		response.put("latest", latestPostsDto);
		
		return response;
	}
}

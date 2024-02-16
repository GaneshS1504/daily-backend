package com.dailydose.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dailydose.model.FinalPosts;

@Repository
public class DailyDoseRepo {

	private MongoTemplate mongoTemplate;

	public DailyDoseRepo(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<FinalPosts> getTredingNewsOnLoad(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("isTrendingNews").is(true));
		query.with(Sort.by(Sort.Direction.DESC, "_id"));
		
		query.fields().include("postId").include("category").include("title").include("link").include("shortDesc")
			.include("trendingImage").include("author").include("postedDate").exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getFeaturedNewsOnLoad(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("isFeaturedNews").is(true));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("category")
		.include("link").include("title").include("featuredImage")
			.include("postedDate").include("shortDesc").include("author")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getLatestPostsOnHomePage( ){
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("category").include("link")
		.include("title").include("bannerImage")
			.include("postedDate").include("shortDesc").include("author")
		    .exclude("_id");
		
		query.limit(6);
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getSportsPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("sports"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("category")
		.include("title").include("shortDesc")
		.include("bannerImage").include("author").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getBusinessPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("business"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("category")
		.include("title").include("shortDesc")
		.include("bannerImage").include("author").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getTechnologyPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("tech"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("category")
		.include("title").include("shortDesc")
		.include("bannerImage").include("author").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getEntertainmentPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("entertainment"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("title")
		.include("shortDesc").include("category")
		.include("bannerImage").include("author").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);	
	}
	
	public List<FinalPosts> getContentPageData(String category, String link){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category").is(category)
				.and("link").is(link));
		
		query.fields().include("postId").include("title")
		.include("bannerImage").include("author").include("postedDate").include("desc")
	    .exclude("_id");
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getLatestPostsOnContentPage(String category,String link){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category").is(category)
				.and("link").ne(link));
		
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("title")
		.include("bannerImage").include("category")
			.include("postedDate").include("shortDesc").include("author")
		    .exclude("_id");
		
		query.limit(5);
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getTopNewsOnLoad(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("istopNews").is(true));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("category")
		.include("link").include("title").include("topNewsImage")
			.include("postedDate").include("shortDesc").include("author")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
}

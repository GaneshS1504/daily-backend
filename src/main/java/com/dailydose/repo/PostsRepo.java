package com.dailydose.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.dailydose.model.FinalPosts;
import com.dailydose.model.Posts;
import com.mongodb.client.result.DeleteResult;

@Repository
public class PostsRepo {
	
	private MongoTemplate mongoTemplate;

	public PostsRepo(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	public Posts addPosts(Posts newPost) {
		
		return mongoTemplate.save(newPost, "col_posts");
	}
	
	public Posts getLatestPost() {
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "_id"));
		query.limit(1);
		Posts latestpost = mongoTemplate.findOne(query, Posts.class,"col_posts");
		return latestpost;
	}
	
	public FinalPosts submitFinalPosts(FinalPosts finalPosts) {
		return mongoTemplate.save(finalPosts, "col_posts_final");
	}
	
	public List<FinalPosts> getPostsById(String shortTitle) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("shortTitle").is(shortTitle));
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getFeaturedPostsOnBlogPage(String shortTitle){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("isFeaturedNews").is(true).and("shortTitle")
				.ne(shortTitle));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
			.include("category").include("postedDate")
		    .exclude("_id");
		
		query.limit(4);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getRelatedCategoryData(String category,String shortTitle){
		Query query = new Query();
		query.addCriteria(Criteria.where("category").is(category).and("shortTitle")
				.ne(shortTitle));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("bannerImage")
			.include("category").include("postedDate")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
			
	}
	
	public List<FinalPosts> getLatestPosts(String shortTitle){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("shortTitle").ne(shortTitle));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("bannerImage")
			.include("category").include("postedDate")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getTabDataOnHomePage(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("isFeaturedNews").is(true));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
			.include("category").include("postedDate").include("shortDesc")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getLatestPostsOnHomePage( ){
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
			.include("category").include("postedDate").include("shortDesc")
		    .exclude("_id");
		
		query.limit(5);
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getTopNewsOnPageLoad(){
			
		Query query = new Query();
		query.addCriteria(Criteria.where("istopNews").is(true));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("topNewsImage")
			.include("category").include("postedDate")
		    .exclude("_id");
		
		query.limit(6);
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public List<FinalPosts> getSportsPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("sports"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
		.include("category").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getBusinessPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("business"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
		.include("category").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getTechnologyPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("news"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
		.include("category").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getEntertainmentPosts(){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category")
				.is("entertainment"));
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("shortTitle").include("featuredImage")
		.include("category").include("postedDate")
	    .exclude("_id");
		
		query.limit(3);
		
		return mongoTemplate.find(query, FinalPosts.class);	
	}
	
	public List<FinalPosts> getAllPosts(){
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.fields().include("postId").include("link").include("author")
		.include("category").include("postedDate").include("title")
	    .exclude("_id");
		
		return mongoTemplate.find(query, FinalPosts.class);
	}
	
	public FinalPosts updatePostById(Long postId, FinalPosts postRequest){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		
		Update update = new Update();
		if(postRequest.getLink() != null || postRequest.getLink() != "") {
			update.set("link", postRequest.getLink());
		}
		if(postRequest.getDesc() != null && postRequest.getDesc() != "") {
			update.set("Desc", postRequest.getDesc());
		}
		if(postRequest.getTitle() != null && postRequest.getTitle() != "") {
			update.set("title", postRequest.getTitle());
		}
		if(postRequest.getShortDesc() != null && postRequest.getShortDesc() != "") {
			update.set("shortDesc", postRequest.getShortDesc());
		}
		if(postRequest.getCategory() != null && postRequest.getCategory() != "") {
			update.set("category", postRequest.getCategory());
		}
		if(postRequest.getSubCategory() != null && postRequest.getSubCategory() != "") {
			update.set("subCategory", postRequest.getSubCategory());
		}
		
		
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(false);
		return mongoTemplate.findAndModify(query, update,options, FinalPosts.class);
		
	}
	
	public List<FinalPosts> getPostById(Long postId){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		
		return mongoTemplate.find(query,FinalPosts.class);
	}
	
	public DeleteResult deletePost(Long postId) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		
		return mongoTemplate.remove(query, FinalPosts.class);
	}
}

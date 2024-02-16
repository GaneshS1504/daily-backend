package com.dailydose.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dailydose.dto.ListingPageDto;
import com.dailydose.dto.TopPostsDTO;
import com.dailydose.model.FinalPosts;
import com.dailydose.model.Posts;
import com.dailydose.model.PostsImages;
import com.dailydose.service.PostsService;
import com.dailydose.utils.FileUtils;
import com.mongodb.client.result.DeleteResult;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = {"*"})
public class PostsController {
	
	private PostsService postsService;
	private FileUtils fileUtils;
	
	public PostsController(PostsService postsService,FileUtils fileUtils) {
		super();
		this.postsService = postsService;
		this.fileUtils = fileUtils;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Posts> addNewPost(@RequestBody Posts post){
		return new ResponseEntity<Posts>(postsService.addNewPost(post), HttpStatus.CREATED);
	}
	
	@PostMapping("/upload-image/{postId}/{imageType}")
	public String uploadImage(@PathVariable Long postId ,@PathVariable String imageType,@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		String fileName = multipartFile.getOriginalFilename();
		
		try {
			
			try {
				 String path = fileUtils.saveImage(fileName, multipartFile,postId,imageType);
				 PostsImages savedImage = postsService.insertImagePath(path, postId, imageType);
				 System.out.println(savedImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "file uploaded successfully";
	}
	
	@PostMapping("/submit/{postId}")
	public ResponseEntity<FinalPosts> submitPost(@PathVariable Long postId,@RequestBody Posts post){
		
		return new ResponseEntity<FinalPosts>(postsService.submitNewPost(post, postId), HttpStatus.CREATED);
	}
	
	@GetMapping("/getPostById/{shortTitle}/{category}")
	public ResponseEntity<Map<String,Object>> getPosts(@PathVariable String category,
			@PathVariable String shortTitle){
		
		return new ResponseEntity<Map<String,Object>>(postsService.getPost(category,shortTitle), HttpStatus.OK);
	}
	
	@GetMapping("/gettabdata")
	public ResponseEntity<Map<String,Object>> getHomePageTabData(){
		return new ResponseEntity<Map<String,Object>>(postsService.getTabData(), HttpStatus.OK);
	}
	
	@GetMapping("/get-topnews")
	public ResponseEntity<List<TopPostsDTO>> getHomePageTopNews(){
		return new ResponseEntity<List<TopPostsDTO>>(postsService.getTopNews(), HttpStatus.OK);
	}
	
	@GetMapping("/get-allcategories-posts")
	public ResponseEntity<Map<String,Object>> getAllCategoriesPosts(){
		return new ResponseEntity<Map<String,Object>>(postsService.getAllCategoriesPosts(), HttpStatus.OK);
	}
	
	@PostMapping("/upload-image")
	public ResponseEntity<Map<String,Object>> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		String fileName = multipartFile.getOriginalFilename();
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			
			try {
				 String path = fileUtils.saveImageFromCkeditor(fileName, multipartFile);
				 response.put("url", path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<List<ListingPageDto>> getAllPosts(){
		
		return new ResponseEntity<List<ListingPageDto>>(postsService.getAllPosts(), HttpStatus.OK);
	}
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<FinalPosts> updatePost(@PathVariable Long postId,
			@RequestBody FinalPosts request){
		
		return new ResponseEntity<FinalPosts>(postsService.updatePostById(postId, request), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getById/{postId}")
	public ResponseEntity<List<FinalPosts>> getPostById(@PathVariable Long postId){
		return new ResponseEntity<List<FinalPosts>>(postsService.getByPostId(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{postId}")
	public ResponseEntity<DeleteResult> deletePostById(@PathVariable Long postId){
		return new ResponseEntity<DeleteResult>(postsService.deletePost(postId), HttpStatus.ACCEPTED);
	}

}

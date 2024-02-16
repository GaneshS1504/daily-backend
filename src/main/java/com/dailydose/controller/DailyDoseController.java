package com.dailydose.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailydose.service.DailyDoseService;

@RestController
@RequestMapping("/api/daily-dose")
@CrossOrigin(origins = {"*"})
public class DailyDoseController {
	
	private DailyDoseService dailyDoseService;
	
	public DailyDoseController(DailyDoseService dailyDoseService) {
		super();
		this.dailyDoseService = dailyDoseService;	
	}
	
	@GetMapping("/get-posts")
	public ResponseEntity<Map<String, Object>> getPostsOnPageLoad(){
		return new ResponseEntity<Map<String,Object>>(dailyDoseService.getTrendingNewsOnPageLoad(),
				org.springframework.http.HttpStatus.OK);
	}
	
	@GetMapping("/get-content/{category}/{link}")
	public ResponseEntity<Map<String, Object>> getContentOnBlogPage(@PathVariable String category,
			@PathVariable String link){
		return new ResponseEntity<Map<String,Object>>(dailyDoseService.getContentOnBlogPage(category, link)
				,HttpStatus.OK);
	}
}

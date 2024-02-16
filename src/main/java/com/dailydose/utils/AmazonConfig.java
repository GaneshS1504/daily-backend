package com.dailydose.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {
	
	@Value("${access.key.id}")
	private String accessKey;
	
	@Value("${access.key.secret}")
	private String secretKey;
	
	@Value("${s3.region.name}")
	private String region;
	
	@Bean
	public AmazonS3 amazonS3Init() {
			
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		
		return AmazonS3ClientBuilder
				.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();
	}
}

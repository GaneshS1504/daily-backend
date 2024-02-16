package com.dailydose.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class FileUtils {
	
	@Value("${s3.bucket.name}")
	private String bucketName;
	
	private static final String S3URL = ".s3.ap-south-1.amazonaws.com/"; 
	
	private AmazonS3 amazonS3;
	
	public FileUtils(AmazonS3 amazonS3) {
		super();
		this.amazonS3 = amazonS3;
	}

	public String saveImage(String fileName, MultipartFile multipartFile, Long postId,String imageType)
			throws IOException {

		if(multipartFile.isEmpty()) {
			throw new IllegalStateException("cannot upload empty file");
		}
		
		try {
			
			//upload image into s3
			File file = new File(multipartFile.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.write(multipartFile.getBytes());
			fos.close();
			
			System.out.println("path "+bucketName+" file name "+fileName);
			amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			String path = "https://"+bucketName+S3URL+fileName;
			System.out.println(path);
			return path;
			
		} catch (IOException ioe) {
			if (ioe instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(ioe.getMessage());
		}
		
	}
	
	public String saveImageFromCkeditor(String fileName, MultipartFile multipartFile)
			throws IOException {

		if(multipartFile.isEmpty()) {
			throw new IllegalStateException("cannot upload empty file");
		}
		
		try {
			
			//upload image into s3
			File file = new File(multipartFile.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.write(multipartFile.getBytes());
			fos.close();
			
			System.out.println("path "+bucketName+" file name "+fileName);
			amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			String path = "https://"+bucketName+S3URL+fileName;
			System.out.println(path);
			return path;
			
		} catch (IOException ioe) {
			if (ioe instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(ioe.getMessage());
		}
		
	}
	
	public String removeImage(List<String> imagePath) {
		
		for(String image: imagePath) {
			amazonS3.deleteObject(bucketName, image);
		}
		return "Deleted File"+imagePath;
	}
}

package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.payload.PostDto;
import com.myapp.payload.PostResponse;
import com.myapp.service.PostService;

import net.bytebuddy.asm.Advice.This;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("/api/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
//		PostDto createdPost = this.postService.createPost(postDto);
		return new ResponseEntity<>(this.postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/api/posts/{id}")
	public ResponseEntity<PostDto> getAllposts(
			@PathVariable(name = "id")long id){
//		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<>(this.postService.getPostById(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/api/posts")
	public List<PostDto> getAllPosts(){
		List<PostDto> list = this.postService.getAllPost();
		return list;
	}
	
	
	@GetMapping("/api/post") //http://localhost:8282/api/post?pageNo=0&pageSize=15&sortBy=id
	public PostResponse getAllPost(
		@RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
		@RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy){
		return this.postService.getAllPosts(pageNo, pageSize,sortBy);
	}
	
	@PutMapping("/api/post/{postId}")
	public ResponseEntity<PostDto> updatePost(
		@PathVariable(value = "postId") long postId,
		@RequestBody PostDto postDto){
		PostDto updatePost = this.postService.updatePostById(postId, postDto);
		return new ResponseEntity<>(updatePost,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/post/{postId}")
	public ResponseEntity<String> deleteBypostId(
			@PathVariable(value = "postId") long postId){
		this.postService.deleteBypostId(postId);
		String msg = "Post no. "+ postId +" Deleted Succesfully"; 
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}

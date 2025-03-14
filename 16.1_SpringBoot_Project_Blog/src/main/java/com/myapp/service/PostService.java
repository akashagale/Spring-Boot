package com.myapp.service;

import java.util.List;

import com.myapp.payload.PostDto;
import com.myapp.payload.PostResponse;


public interface PostService {
	
	// insert posts
	PostDto createPost(PostDto postDto);
	
	// find all
	List<PostDto> getAllPost();
	
	// find by id
	PostDto getPostById(long id);
	
	// find all with all content
	PostResponse getAllPosts(int pageNo,int pageSize,String sortBy);
	
	// get all by id with all content
	PostResponse getAllById();
	
	PostDto updatePostById(long postId,PostDto postDto);
	
	public void deleteBypostId(long postId);
}

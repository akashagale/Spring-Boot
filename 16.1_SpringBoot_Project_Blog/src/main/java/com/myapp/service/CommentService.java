package com.myapp.service;

import com.myapp.payload.CommentDto;


public interface CommentService {
	
   public CommentDto getCommentByPostId(long postId);
   
   public CommentDto getCommentById(long postId,long commentId);
	
   // creating comment using postId ===== OR ====> Commenting in Post
   public CommentDto createCommentByPostId(long postId,CommentDto commentDto);
   
   // updating comment
   public CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
   
   // deleting comment
   public void deleteCommentBy(long postId,long commentPost);
}

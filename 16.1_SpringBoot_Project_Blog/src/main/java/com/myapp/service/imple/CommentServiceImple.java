package com.myapp.service.imple;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.CommentToken;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.myapp.entity.Comment;
import com.myapp.entity.Post;
import com.myapp.exception.BlogAPIException;
import com.myapp.exception.ResourceNotFoundException;
import com.myapp.payload.CommentDto;
import com.myapp.payload.PostDto;
import com.myapp.repository.CommentRepository;
import com.myapp.repository.PostRepository;
import com.myapp.service.CommentService;

import net.bytebuddy.asm.Advice.This;

@Service
public class CommentServiceImple implements CommentService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	
	// get Comment By postId
	public CommentDto getCommentByPostId(long postId) {
		Comment comments= this.commentRepository.findById(postId)
			.orElseThrow(
			() -> new ResourceNotFoundException("Comment", "Id", postId));
		return mapToDto(comments);
	}
	
	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		
		// searching the post
		Post posts = findByPostId(postId);
			
		// getting the comment from body 
		Comment comment = findByCommentId(commentId);
		
		if (!posts.getId().equals(comment.getPost().getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment not belongs to this Post!");
		}
		
		CommentDto newComment = mapToDto(comment);
		
		return newComment;
	}
	
	
	@Override
	public CommentDto createCommentByPostId(long postId,CommentDto commentDto) {
	 
	 // searching the post
	 Post post = this.postRepository.findById(postId)
	 .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
	
	 // getting the comment from body 
	 Comment comment = mapToEntity(commentDto);
	 comment.setPost(post);
	 
	 
	 Comment newComment = this.commentRepository.save(comment);
	 
	 return mapToDto(newComment);
	}

	
	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
		// finding the post
		Post post = findByPostId(postId);
		
		// finding comment of post
		Comment comment = findByCommentId(commentId);
		
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment is not Belongs to this post");
		}
		
		comment.setBody(commentDto.getEmail());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		Comment updatedComment = this.commentRepository.save(comment);
		
		return mapToDto(updatedComment);
	}
	
	@Override
	public void deleteCommentBy(long postId, long commentId) {

		// finding Post
		Post post = findByPostId(postId);
		
		// finding comment
		Comment comment = findByCommentId(commentId);

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new  BlogAPIException(HttpStatus.NOT_FOUND, "Comment is not Found");
		}
		this.commentRepository.deleteById(commentId);
	}
	
	
	
	// Convert Entity to DTO
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto =mapper.map(comment, CommentDto.class); 
		return commentDto;
	}
	
	// Convert DTO to Entity
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment =mapper.map(commentDto, Comment.class); 
		return comment;
	}

	
	private Post findByPostId(long postId) {
		Post post =  this.postRepository.findById(postId)
		.orElseThrow(
		() -> new ResourceNotFoundException("Post", "postId", postId));
		return post;
	}

	private Comment findByCommentId(long commentId) {
		Comment  comment=this.commentRepository.findById(commentId)
		.orElseThrow(
		() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		return comment;
	}


}

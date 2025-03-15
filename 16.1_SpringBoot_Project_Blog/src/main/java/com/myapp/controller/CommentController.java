package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.payload.CommentDto;
import com.myapp.service.CommentService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {

	private CommentService commentService;
	
	@GetMapping("/{postId}/comments")
	public ResponseEntity<CommentDto> getCommemtBypostId(
			@PathVariable(value = "postId") long postId) {
		CommentDto comments = this.commentService.getCommentByPostId(postId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(
		@PathVariable(value = "postId") long postId,
		@PathVariable(value = "commentId") long commentId){
		
		return new ResponseEntity<>
		(this.commentService.getCommentById(postId, commentId),HttpStatus.OK);	
	}
	
	@PutMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(
		@PathVariable(value = "postId") long postId,
		@PathVariable(value = "commentId") long commentId,
		@RequestBody CommentDto commentDto){
		
 		return new ResponseEntity<>(
 			this.commentService.updateComment(postId, commentId, commentDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(long postId, long commentId){
		this.commentService.deleteCommentBy(postId, commentId);
		return new ResponseEntity<>("Comment Deleted Succesfully",HttpStatus.OK);
	}
}
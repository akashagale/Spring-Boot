package com.myapp.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostDto {

	private Long id;
	private String title;	
	private String description;
	private String content;
	
	private List<CommentDto> comments;
}

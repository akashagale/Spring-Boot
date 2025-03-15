package com.myapp.service.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myapp.entity.Post;
import com.myapp.exception.ResourceNotFoundException;
import com.myapp.payload.PostDto;
import com.myapp.payload.PostResponse;
import com.myapp.repository.PostRepository;
import com.myapp.service.PostService;

@Service
@AllArgsConstructor
public class PostServiceImple implements PostService{

//	@Autowired
	private PostRepository postRepository;
//	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post newPost = this.postRepository.save(post);
		
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}
	
	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = this.postRepository.findAll();

		List<PostDto> list = posts
			.stream()
			.map(post -> mapToDto(post))
			.collect(Collectors.toList());
		return list;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post=this.postRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		return mapToDto(post);
	}
	
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy) {
		
		PageRequest request = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
																		//.ascending() 						
		Page<Post> posts = this.postRepository.findAll(request);
		
		// get content from page object
		List<Post> listOfPosts = posts.getContent();
		
		
		// convert to PostDto
		List<PostDto> content = listOfPosts
		.stream()
		.map(post ->mapToDto(post))
		.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(postResponse.getPageNo());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLastPage(posts.isLast());
		postResponse.setTotalPages(posts.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto updatePostById(long postId, PostDto postDto) {
		
		this.postRepository.findById(postId)
		.orElseThrow(
		() ->new ResourceNotFoundException("Post", "postId", postId));
		
		Post post = mapToEntity(postDto);
		Post updatedPost = this.postRepository.save(post);
		return mapToDto(updatedPost);
	}

	
	@Override
	public void deleteBypostId(long postId) {
		this.postRepository.deleteById(postId);
	}
	
	
	@Override
	public PostResponse getAllById() {
		
		return null;
	}
	
	
	
	// Convert Dto to Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		//postDto.po
		return post;
	}
	
	// Convert Entity to Dto
	private PostDto mapToDto(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
//		postDto.setId(post.getId());
//		postDto.setContent(post.getContent());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
		return postDto;
	}

	private PostDto findByPostId(long postId) {
		Post post = this.postRepository.findById(postId)
		.orElseThrow(
		() -> new ResourceNotFoundException("Post", "postId", postId));
		return mapToDto(post);
	}

}

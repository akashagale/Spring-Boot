package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
		
}

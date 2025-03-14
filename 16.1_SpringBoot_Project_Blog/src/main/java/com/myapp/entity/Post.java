package com.myapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames="title")})
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@Column(name = "content",nullable = false)
	private String content;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments;
}

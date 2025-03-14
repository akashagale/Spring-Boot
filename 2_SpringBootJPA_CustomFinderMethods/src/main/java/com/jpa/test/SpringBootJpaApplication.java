package com.jpa.test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class SpringBootJpaApplication {
	public static void main(String[] args) {
	ApplicationContext context=	SpringApplication.run(SpringBootJpaApplication.class, args);
		
	UserRepository userRepository = context.getBean(UserRepository.class);
	
	// get deatils by name
//	List<User> name = userRepository.findByName("Nitin");
//	System.out.println(name);
//	name.forEach(e -> System.out.println(e));
	
	List<User> findByNameAndCity = userRepository.findByNameOrCity("Pankaj", "Hyd");
	for (User user : findByNameAndCity) {
		System.out.println(user);
	}
	}
}
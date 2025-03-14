package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.firstname = :firstname")
	public User getUserByUserName(@Param("firstname") String firstname);

}
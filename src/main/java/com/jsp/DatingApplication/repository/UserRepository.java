package com.jsp.DatingApplication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.DatingApplication.entity.User;
import com.jsp.DatingApplication.util.User_gender;


public interface UserRepository extends JpaRepository<User,Integer>{
	
	List<User> findByGender(User_gender male);
	
//	custom method so custom query we have to write. ? -> place holder (1 parameter)
	@Query("select u from User u where u.name like ?1")
	
//	@Query("select u from User u where u.name like ?1and ?2 email ") if 2 parameters are there
	
	List<User> searchByName(String letters);
	
}
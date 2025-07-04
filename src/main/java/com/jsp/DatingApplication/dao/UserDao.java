package com.jsp.DatingApplication.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.DatingApplication.entity.User;
import com.jsp.DatingApplication.repository.UserRepository;
import com.jsp.DatingApplication.util.User_gender;


@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;

	public User saveUsers(User user) {
		return userRepository.save(user);
	}	
	public List<User> findAllFemaleUsers() {
		// TODO Auto-generated method stub
		return userRepository.findByGender(User_gender.FEMALE);
	}
	
	public List<User> findAllMaleUsers() {
		return userRepository.findByGender(User_gender.MALE);
	}
	
	public Optional<User> findUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}
	public List<User> searchByName(String letters) {
		
//		searchByName() -> not there in JpaRepo so create.
		return userRepository.searchByName(letters);
	}


}
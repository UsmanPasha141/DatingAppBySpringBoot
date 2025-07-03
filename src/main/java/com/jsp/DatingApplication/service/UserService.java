package com.jsp.DatingApplication.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.DatingApplication.controller.*;
import com.jsp.DatingApplication.dao.UserDao;
import com.jsp.DatingApplication.dto.MatchingUser;
import com.jsp.DatingApplication.entity.User;
import com.jsp.DatingApplication.util.UserSorting;
import com.jsp.DatingApplication.util.User_gender;

@Service
public class UserService {

    
	@Autowired
	UserDao userDao;

	public ResponseEntity<?> saveUsers(User user) {
		User savedUser = userDao.saveUsers(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}


//	public ResponseEntity<?> findAllMaleUsers() {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}

//	
	public ResponseEntity<?> findBestMatch(int id, int topno) {
		
		Optional<User> optional = userDao.findUserById(id);
		
		if(optional.isEmpty()) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id Unable ti find the Best Matches");
		}
		
		User user = optional.get();
		
//		for matching for marrage
		List<User> users = null;
		
		if(user.getGender().equals(User_gender.MALE)) {
//			write the method in JpaRepository
			users = userDao.findAllFemaleUsers();
		}else {			
//			write the method in JpaRepository
			users = userDao.findAllMaleUsers();
		}
		
		// for confirmation which users are getting
//		for(User u : users)
//			System.out.println(u);
		
//		calculate age difference and interest count logic.
		List<MatchingUser> matchingUsers = new ArrayList<>();
		for(User u : users) {
			MatchingUser mu = new MatchingUser();
			u.setId(u.getId());
			u.setName(u.getName());
			u.setEmail(u.getEmail());
			u.setPhone(u.getPhone());
			u.setAge(u.getAge());
			u.setInterests(u.getInterests());
			u.setGender(u.getGender());
			
			int ageDiff = Math.abs(user.getAge() - u.getAge());
			mu.setAgeDiff(ageDiff);
			
			List<String> interest1 = u.getInterests();
			
			List<String> interest2 = u.getInterests();
			
			int mic = 0; // interest count
			for(String s : interest1)
			{
				if(interest2.contains(s)) {
					mic++;
				}
			}
			
			mu.setMic(mic);
			
			matchingUsers.add(mu);
			
		}
		
//		for confirmation
//		for(MatchingUser mu : matchingUsers) {
//			System.out.println();
//		}
		
		Comparator<MatchingUser> c = new UserSorting();
 		
		Collections.sort(matchingUsers, c);
		
//		for confirmation
//		for(MatchingUser mu : matchingUsers) {
//			System.out.println();
//		}
		
		List<MatchingUser> result = new ArrayList<>();
		for(MatchingUser mu : matchingUsers) {
			if(topno == 0) {
				break;
			}
			else {
				result.add(mu);
				topno--;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(result)
		
		
	}

}
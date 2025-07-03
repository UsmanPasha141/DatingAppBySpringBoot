package com.jsp.DatingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.DatingApplication.entity.User;
import com.jsp.DatingApplication.service.UserService;



@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUsers(@RequestBody User user){
		return userService.saveUsers(user);
	}
	
//	@GetMapping("/users/gender/male")
//	public ResponseEntity<?> findAllMaleUsers(){
//		return userService.findAllMaleUsers();
//	}
	
	
//	for id = 1 or 5  -> topno = 3 or 10 top 3 or 10 people who is matching the id
	@GetMapping("/users/best-match/{id}/{topno}")
	public ResponseEntity<?> findBestMatch(@PathVariable int id, @PathVariable int topno){
		return userService.findBestMatch(id, topno);
	}
	
}
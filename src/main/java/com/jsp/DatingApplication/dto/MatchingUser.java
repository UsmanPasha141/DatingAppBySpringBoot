package com.jsp.DatingApplication.dto;

import java.util.List;

import com.jsp.DatingApplication.util.User_gender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MatchingUser {
	
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private User_gender gender;
	private List<String> interests;
	private int ageDiff;
	private int mic; //matching interest count

}

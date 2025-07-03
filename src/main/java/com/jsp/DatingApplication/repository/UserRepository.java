package com.jsp.DatingApplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.DatingApplication.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>{

}
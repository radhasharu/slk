package com.slksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slksoft.dao.UsersDao;
import com.slksoft.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // qualified for component scan
public class UserService {

	@Autowired
	UsersDao dao;

	public UserService() {
		log.info("UserService instantiated");
	}

	public void addNewUser(User u) {
		 dao.addNewUser(u);
	}

	public User loginUser(String uname, String pass) {
	
		return dao.loginUser(uname, pass);
	}

	
}

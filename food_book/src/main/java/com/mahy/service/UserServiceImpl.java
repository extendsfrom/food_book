package com.mahy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahy.dao.UserDao;
import com.mahy.vo.UserUser;

@Service("userService")
public class UserServiceImpl {
	
	@Autowired
	private UserDao userDao;
	
	public void addUser(UserUser user) {
		userDao.insertUser(user);
	}
}

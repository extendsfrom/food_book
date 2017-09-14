package com.food.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.book.dao.UserDao;
import com.food.book.vo.UserUser;

@Service("userService")
public class UserServiceImpl {
	@Autowired
	private UserDao userDao;
	
	public boolean registerUser(UserUser user) {
		return userDao.insert(user) == 1 ? true : false;
	}
}

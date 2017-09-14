package com.food.book.dao;

import org.springframework.stereotype.Repository;

import com.food.book.vo.UserUser;

public interface UserDao {
	public int insert(UserUser user);
}

package com.mahy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahy.service.UserServiceImpl;
import com.mahy.vo.UserUser;
import com.sun.istack.internal.logging.Logger;

@Controller
@RequestMapping("/login")
public class Login {
	private static final Logger logger = Logger.getLogger(Login.class);
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/toRegister.do")
	public String toRegister() {
		return "register";
	}
	
	@RequestMapping("/registerUser.do")
	public String registerUser(UserUser user) {
		System.out.println("userName:" + user.getName() + ",password:" + user.getPassword());
		userService.addUser(user);
		logger.info("register success!");
		return "register";
	}
}

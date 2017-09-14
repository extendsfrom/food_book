package com.food.book.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comm.util.StringUtil;
import com.food.book.service.UserServiceImpl;
import com.food.book.vo.UserUser;

@Controller
@RequestMapping("/user")
public class UserAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="userRegister.do")
	public String userRegister(UserUser user) {
		if(null != user) {
			if(StringUtil.isEmpty(user.getUserName())) {
				logger.info("用户姓名不能为空");
				return "error";
			}
			if(StringUtil.isEmpty(user.getUserPassword())) {
				logger.info("用户密码不能为空");
				return "error";
			}
			logger.info("before register userInfo:" + StringUtil.printParam(user));
			userService.registerUser(user);
			logger.info("after register userInfo:" + StringUtil.printParam(user));
			return "registerSuc";
		}
		return "error";
	}
}

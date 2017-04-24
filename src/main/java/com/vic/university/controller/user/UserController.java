package com.vic.university.controller.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vic.university.service.user.IUserService;

@Controller
public class UserController {
	private transient Logger log = LoggerFactory.getLogger(UserController.class);
	@Resource
	private IUserService userService;
	@RequestMapping("/user/getUserInfoById.action")
	public String getUserInfoById(Integer userId,Model model){
		log.debug("*****进入了**getUserInfoById");
		model.addAttribute("userInfo", userService.queryUserById(1));
		return "/index.jsp";
	}
}

package com.vic.university.controller.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vic.university.controller.security.ShiroSecurityAction;
import com.vic.university.service.user.IUserService;

@Controller
public class UserController {
	protected transient static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private IUserService userService;
	@RequestMapping("/user/getUserInfoById")
	public String getUserInfoById(Integer userId,Model model){
//		System.out.println(request.getRemoteAddr());
		System.out.println("asdf*---------------------*************************");
		logger.info("asdf*---------------------*************************");
		logger.debug("asdf*---------------------*************************");
		model.addAttribute("userInfo", userService.queryUserById(userId));
		return "/index.jsp";
	}
}

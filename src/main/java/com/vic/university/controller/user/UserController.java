package com.vic.university.controller.user;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vic.university.service.user.IUserService;

@Controller
public class UserController {
	@Resource
	private IUserService userService;
	@RequestMapping("/user/getUserInfoById")
	public String getUserInfoById(Integer userId,Model model){
//		System.out.println(request.getRemoteAddr());
		System.out.println("asdf*---------------------*************************");
		model.addAttribute("userInfo", userService.queryUserById(userId));
		return "/index.jsp";
	}
}

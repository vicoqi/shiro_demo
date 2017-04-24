package com.vic.university.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vic.university.bean.User;
import com.vic.university.controller.security.ShiroSecurityAction;
import com.vic.university.service.user.IUserService;

@Controller
public class UserController {
	protected transient static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private IUserService userService;
	@RequestMapping("/user/getUserInfoById")
	public ModelAndView getUserInfoById(HttpServletRequest request,Integer userId,Model model){
		logger.debug("*************************---进入到 getUserInfoById");
		User userInfo = userService.queryUserById(1);
		logger.debug("************************"+userInfo.toString());
		List userInfoList = new ArrayList();
		userInfoList.add(userInfo);
		model.addAttribute("userInfoList", userInfoList);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("hello", "nihao");
		request.setAttribute("userInfoList", userInfoList);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("hello", "nihao");
		logger.debug("************************hello zhi"+request.getAttribute("hello"));
		ModelAndView mv= new ModelAndView("index");
		mv.addObject("hello", "nihao");
		return mv;
	}
}

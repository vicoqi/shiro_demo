package com.vic.university.controller.security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;







import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
//两个log日志
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vic.university.shiro.ShiroToken;




@Controller
@RequestMapping("/security")
public class ShiroSecurityAction {
	
	protected transient static Logger logger = LoggerFactory.getLogger(ShiroSecurityAction.class);
	
	@RequestMapping("/loginView")
	public ModelAndView loginView(){
		logger.info("下面进入登陆验证");
		return new ModelAndView("/securityView/login.jsp");
	}
	
	@RequestMapping("/loginData")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request){
		logger.info("进入登陆验证");
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		ShiroToken token = new ShiroToken("admin", "21232f297a57a5a743894a0e4a801fc3");
		token.setRememberMe(false);
		SecurityUtils.getSubject().login(token);
		ShiroToken token2 = (ShiroToken) SecurityUtils.getSubject().getPrincipal();
		logger.info(token2.getUsername() + "," + token2.getPswd());

        resultMap.put("status", 200);
        resultMap.put("message", "登录成功");
        
        /**
         * 获取登录之前的地址
         */
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (null != savedRequest)
        {
            url = savedRequest.getRequestUrl();
        }
        // 跳转地址
        resultMap.put("back_url", url);
        
		return resultMap;
	}
}

package com.vic.university.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vic.university.controller.security.ShiroSecurityAction;


public class MyRealm extends AuthorizingRealm{
	protected transient static Logger logger = LoggerFactory.getLogger(ShiroSecurityAction.class);
	
//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection paramPrincipalCollection) {
		ShiroToken token = (ShiroToken) SecurityUtils.getSubject().getPrincipal();
		logger.debug(token.getUsername()+"授权");
		
		// 从数据库中查找该用户的角色和权限
        SimpleAuthorizationInfo sainfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        Set<String> permissions = new HashSet<String>();
        permissions.add("add");
        permissions.add("delete");
        sainfo.setRoles(roles);
        sainfo.setStringPermissions(permissions);
		return sainfo;
	}
//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken paramAuthenticationToken)
			throws AuthenticationException {
		ShiroToken token = (ShiroToken) paramAuthenticationToken;
        String username = token.getUsername();

        // 根据username从数据库查找用户，得到密码
        // 假设找到的用户如下
        // User user = userService.findByUsername(username)
        com.vic.university.bean.User user = new com.vic.university.bean.User();
        user.setUserName(username);
        user.setPassword("21232f297a57a5a743894a0e4a801fc3"); // 数据库中的密码md5加密的
        
        if (null == user)
        {
            throw new AccountException("username is not exist");
        }
        else if (!user.getPassword().equals(token.getPswd()))   //自己手动判断，不使用 凭证匹配器。写一个类 继承UsernamePasswordToken 的原因是。为了能够获得到密码，因为paramAuthenticationToken没有get密码的方法
        {
            throw new AccountException("password is not right");  
        }
        else
        {
            // 登陆成功
            logger.info("{} login success.", username);
        }
        
        return new SimpleAuthenticationInfo(paramAuthenticationToken, user.getPassword(), username);
	}

}

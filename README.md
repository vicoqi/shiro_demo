ssh
===============

## 以springmvc_hibernate_spring 为根本搭建起来的web项目架子。

- bra_add_shiro 分支。 在master分支的基础上添加了 shiro 安全权限管理框架。

#### 
    spring-shiro.xml 是shiro 的配置文件，然后再 import 到 spring-bean.xml（spring的核心配置文件） 文件中。其中我写的注释很详细。
    
## 使用方法

- 进入登录的action：http://localhost:8989/phoneBiShe/security/loginData.action   ----->登陆验证和授权 用户是admin1 , 权限是 admin
- 测试：http://localhost:8989/phoneBiShe/user/getUserInfoById.action ----->访问成功
- 但是如果先访问 ：http://localhost:8989/phoneBiShe/user/getUserInfoById.action   因为没有登陆，就会跳到 ：http://localhost:8989/phoneBiShe/security/loginView.action        登陆界面，因为没有权限


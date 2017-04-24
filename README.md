购物系统
=====================
[![Build Status](https://travis-ci.org/vito16/shop.svg?branch=master)](https://travis-ci.org/vito16/shop) [![Coverage Status](https://coveralls.io/repos/github/vito16/shop/badge.svg?branch=master)](https://coveralls.io/github/vito16/shop?branch=master)  

在线商城项目，持续完善中...

## 启动服务

    com.vito16.shop.Application.main()
    
访问URL：[http://localhost:8081/](localhost:8081/)  

## 配置修改
可以在application.properties中修改相关配置（http服务端口、数据库配置...）

- 访问端口：server.port
- 数据库相关配置：jdbc.***


ssh
========================
以springmvc+hibernate+spring 为根本搭建起来的web项目架子。并后续添加各种功能。

## master是主分支。
并以maven构建起来的三大框架为架子。后续的开发是以master主分支进行开发的。
    
## bra_add_shiro 分支。
在master分支的基础上添加了 shiro 安全权限管理框架。

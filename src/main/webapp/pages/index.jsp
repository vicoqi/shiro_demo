<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 原来是<%@ page isELIgnored="false" %>没有加
如果一定要追查为什么，只能说，默认EL的忽略是true的，所以EL是没法使用的。
	但是我的事没事的 EL 表达式可以用
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
my page
<%-- <table border="1">
            <thead>
                <tr>
                    <td width="60px">id</td>
                    <td width="120px">name</td>
                    <td width="60px">age</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userInfo}" varStatus="status">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.userName}</td>
                        <td>${user.age}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</body> --%>
<p>${userInfo.userId}<p/>
<p>${userInfo.userName}<p/>
<p>${userInfo.age}<p/>
</html>
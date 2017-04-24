<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${pageContext.request.contextPath}
<form id="getUserInfo" action="/phoneBiShe/user/getUserInfoById" method="get">
	id:<input type="text" name = "userId"/>
	<input type="submit" value="提交">
</form>
</body>
</html>
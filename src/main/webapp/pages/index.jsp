<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String aaa = (String)request.getAttribute("hello");
	
%>
<script type="text/javascript">
	var str='<%=(String)request.getAttribute("hello")%>';
	var uid='<%=request.getParameter("userInfo")%>';
	var aaa = '<%=aaa %>';
	alert(str);
	
</script>
</head>
<body>
<%
	String aa = (String)request.getAttribute("hello");
	System.out.println(aa);
%>
<%=aa %>
${userInfo.userId}
${userInfo.userName}
${userInfo.age}
${requestScope.hello}
${hello}
</body>
</html> 
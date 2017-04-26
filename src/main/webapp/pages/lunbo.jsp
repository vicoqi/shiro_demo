<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery多图3D旋转图片轮播特效</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ZoomPic.js"></script>
<script type="text/javascript">
$(function(){
	new ZoomPic("jswbox");	
})
</script>
<style type="text/css">.box1{background:url('${pageContext.request.contextPath}/images/cover.jpg')} .hd{width:320px;height:161px;overflow:hidden}</style>
</head>
<body>
<div style="width:100%;hight:2000px;background-image: url('${pageContext.request.contextPath}/images/cover.jpg');">
<div id="jswbox">
	<pre class="prev">prev</pre>
	<pre class="next">next</pre>
	<ul>
		<!--  <li><img style="overflow: hidden;margin-bottom: -100px;margin-left: -36px;margin-right: 36px;" src="${pageContext.request.contextPath}/images/1.jpg"/></li>-->		<li><img  src="${pageContext.request.contextPath}/images/1.jpg"/></li>		
		<li><img  src="${pageContext.request.contextPath}/images/2.jpg"/></li>
		<li><img  src="${pageContext.request.contextPath}/images/3.jpg"/></li>
		<li><img  src="${pageContext.request.contextPath}/images/4.jpg"/></li>
		<li><img  src="${pageContext.request.contextPath}/images/5.jpg"/></li>
		<li><img  src="${pageContext.request.contextPath}/images/6.jpg"/></li>
		<li><img  src="${pageContext.request.contextPath}/images/7.jpg"/></li>
	</ul>
</div></div>

</body>
</html>

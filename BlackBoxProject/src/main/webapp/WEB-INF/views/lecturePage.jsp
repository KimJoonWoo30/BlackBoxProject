<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 3.3.4 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/bootstrap/js/bootstrap.min.js" rel="stylesheet" type="" />

<!-- handlebars -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- jQuery library (served from Google) -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!-- bxSlider Javascript file -->
<script src="/resources/bxslider/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="/resources/bxslider/jquery.bxslider.css" rel="stylesheet" />

<style>


.audiobox audio{
	margin : 0 auto;
	margin-top: 40px;
	display : block;
}
</style>

</head>
<body>

	<div class="container">

		<ul class="bxslider">
			<c:forEach items="${images}" var="imagePath" varStatus="status">
				<li><img src="${imagePath}" data-imageIndex="${status.count}" /></li>
			</c:forEach>
		</ul>
	</div>
	
	<div class="container-fluid audiobox">
		<c:forEach items="${audios}" var="audio" varStatus="status">
			<audio src="${audio}" controls="controls" ></audio>
		</c:forEach>
	</div>


	<script>
		$(document).ready(function() {
			$('.bxslider').bxSlider({
				minSlides : 2,
				maxSlides : 2,
				slideWidth : 1300,
				slideMargin : 10
			}); // ul에 있는 class명을 기준으로 선언을 합니다. 즉, 이미지구성요소들을 감싸고있는 객체에 선언해 줍니다. 
		});
	</script>
</body>
</html>

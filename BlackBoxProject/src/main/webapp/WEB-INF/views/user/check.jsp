<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/navbar.jsp"%>
<%@include file="../include/carousel.jsp"%>
<body>

	<%@include file="../include/profile.jsp"%>
	<div class="container check">
		<div class="notice">
			<h2>공지 사항</h2>
			<ol>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
				<li>댓글테스트</li>
			</ol>
		</div>
		<div class="recentReply">
			<h2>나의 최근 댓글</h2>
			<ol>
				<c:forEach var="comment" items="${comment}">
				
							<li><a href="/qnaboard/readPage?qnaPostId=${comment.qnaPostId}">${comment.qnaCommentContent} (${comment.qnaCommentRegdate})</a></li>
				</c:forEach>
			</ol>
		</div>
	</div>
	<!-- jQuery 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.2 JS -->
	<script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- iCheck -->
	<script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>

<script>
		var successMsg = '${successMsg}';
		if (successMsg != '') {
			alert(successMsg);
		}
		var failMsg = '${failMsg}';
		if (failMsg != '') {
			alert(failMsg);
		}
	</script>
	<%@include file="../include/footer.jsp"%>
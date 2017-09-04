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

</head>
<style>
.jumbotron {
	padding: 30px 15px;
	margin-bottom: 30px;
	color: inherit;
	background-color: #eee;
	background-image:
		url("http://img.daoki.com/club/playground/data/__149380724169420.jpg");
}

.fileDrop {
	width: 80%;
	height: 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}

.list-title {
	float: left;
	width: 65%;
}

.list-icon {
	float: left;
	width: 20%;
	vertical-align: middle;
}

.list-icon ul {
	float: left;
	list-style: none;
	vertical-align: middle;
}

.list-icon ul li {
	float: left;
	width: 45px;
}

#createPostBtn {
	float: right;
}

.modal-fullsize {
	width: 80%;
	height: auto;
}

.thumbnail {
	width: 20%;
	height: 200px;
	float: left;
}
.uploadedList{
	width: 100%;
}
.contentfiles {
	width: 100%;
}
</style>

<body>
	<div class="jumbotron">
		<div class="container">
			<h1>${course.courseName }</h1>
			<p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn more »</a>
			</p>
		</div>
	</div>


	<div class="row">
		<div class="col-md-8">
			<a class="btn btn-default" id="createPostBtn" data-toggle="modal" data-target="#createModal">글작성</a>
			<ul class="nav nav-tabs" id="boardTab">
				<li role="presentation" data-bid=0><a href="#">Home</a></li>
				<li role="presentation" data-bid=1><a href="#">공지사항</a></li>
				<li role="presentation" data-bid=3><a href="#">자유게시판</a></li>
			</ul>
			<div id="postDiv">
				<ul class="list-group">

				</ul>
			</div>
		</div>
		<div class="col-md-4">
			<div class="list-group">
				<a href="#" class="list-group-item disabled"> Cras justo odio </a> <a href="#" class="list-group-item">Dapibus ac facilisis in</a> <a href="#" class="list-group-item">Morbi leo risus</a> <a href="#" class="list-group-item">Porta ac consectetur ac</a> <a href="#" class="list-group-item">Vestibulum at eros</a>
			</div>
		</div>
	</div>



	<div class="col-md-4"></div>




	<!-- Modal -->
	<div id="createModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog modal-fullsize">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form id='registerPost' role="form" method="post">
						<div class="form-group">
							<label for="postTitle">제목</label>
							<input type="text" id="postTitle" class="form-control">
						</div>
						<div class="form-group">
							<label for="postBoard">게시판</label>
							<input type="text" id="postBoard" class="form-control" disabled="disabled">
						</div>
						<div class="form-group">
							<label for="userNick">닉네임</label>
							<input type="text" id="userNick" class="form-control" disabled="disabled" value="rnc1234">
						</div>
						<div class="form-group">
							<label for="postContent">내용</label>
							<textarea class="form-control" id="postContent" name="content" rows="3" placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">File DROP Here</label>
							<div class="fileDrop"></div>
						</div>
					</form>

					<!-- /.box-body -->
				</div>
				<div class="modal-footer">


					<button type="button" class="btn btn-info" id="postAddBtn">등록하기</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

					<div>
						<hr>
					</div>
					<div class="uploadedList">
					
					</div>
				</div>


			</div>
		</div>
	</div>


	${course.toString()}





	<script type="text/javascript" src="/resources/js/upload.js"></script>
	<!-- jQuery 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.2 JS -->
	<script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

	<script id="templatePost" type="text/x-handlebars-template">

{{#each .}}
		<a href="#collapse{{postId}}" data-pid={{postId}} class="list-group-item postList clearfix" data-toggle="collapse"
		arai-controls="collapse{{postId}}" aria-expanded="false">
			<div class="list-title">
				<h4 class="list-group-item-heading">{{postTitle}}</h4>
			</div>
			<div class='list-icon'>
				<ul>
					<li class=""><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> {{postView}}</li>
				</ul>
			</div>
			<div class='list-profile'>
				<span> {{userNick}} {{{prettifyDate postRegdate}}} </span>
			</div>
		</a>
		<div class="collapse" id="collapse{{postId}}">
			  <div class="well">

				<div class="row">
					<div class="contentfiles">
					
					</div>
				</div>
			  </div>
		</div>
{{/each}}

</script>

<!-- 삭제 -->
<script id="templateAttachForRead" type="text/x-handlebars-template">
    <div class="thumbnail" data-src='{{fullName}}'>
      <img src="{{imgsrc}}" alt="Attachment">
      <div class="caption">
        <a href="{{getLink}}"><h6>{{fileName}}</h6></a>
        <p><a href="#" class="btn btn-default btn-sm" role="button">삭제</a></p>
      </div>
    </div>              
</script>

<script id="templateAttach" type="text/x-handlebars-template">
    <div class="thumbnail" data-src='{{fullName}}'>
      <img src="{{imgsrc}}" alt="Attachment">
      <div class="caption">
       <a href="{{getLink}}"><h6>{{fileName}}</h6></a>
        <p><a href="#" class="btn btn-default btn-sm" role="button">삭제</a></p>
      </div>
    </div>
</script>

<script id="templatePostContent" type="text/x-handlebars-template">
<div class="panel panel-default clearfix">
	<div class="panel panel-heading clearfix">
		{{postTitle}}
	</div>
	<div class="panel-body">
		{{postContent}}
	</div>
</div>
</script>


	<script>
		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});
		
		/* 첨부파일 템플릿 */
		var template = Handlebars.compile($("#templateAttach").html());
		var templateAttach4Read = Handlebars.compile($("#templateAttachForRead").html());
		var templatePostContent = Handlebars.compile($("#templatePostContent").html());
	</script>



	<script>
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});

		$(".fileDrop").on("drop", function(event) {
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			alert("drop");
			var file = files[0];

			var formData = new FormData();
			
			formData.append("file", file);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {

					var fileInfo = getFileInfo(data);

					var html = template(fileInfo);

					$(".uploadedList").append(html);
				}
			});
		});
	</script>

	<script>
		// 현재 선택된 게시판
		var boardId;
		var courseId = ${course.courseId};
		
		/* 자유, 공지 게시판 불러오기 */
		$("#boardTab").on("click", "li", function() {
			boardId = $(this).attr("data-bid");
			alert(boardId);
			getPage("/courses/" + courseId + "/board/" + boardId + "/posts");
		});

		var printData = function(postArr, target, templateObject) {
			var template = Handlebars.compile(templateObject.html());

			var html = template(postArr);
			$(".postList").remove();
			target.html(html);

		}

		function getPage(pageInfo) {

			$.getJSON(pageInfo, function(data) {
				printData(data, $("#postDiv .list-group"), $('#templatePost'));

				/* 				$("#modifyModal").modal('hide');
				 $("#replycntSmall")
				 .html("[" + data.pagemarker.totalCount + "]"); */
			});
		}
		
		
		/* 게시글 내용 불러오기 */
		$("#postDiv").on("show.bs.collapse", ".collapse",function () {
			
			// 이미 load 된 내용은 가져오지 않는다.
			if($(this).data("loaded") == 1){
				return;
			}
			
			var postListObj = $(this).prevAll(".postList");
			var postId = postListObj.data("pid");
			
			var postCollapse = $(this);
			var postFileObj = $(this).find(".well .contentfiles");
			var postContentObj = $(this).find(".well");
			
			$.ajax({
				url : '/posts/' + postId,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'GET',
				success : function(data) {
					
					var post = JSON.parse(data);
					
					// 내용 추가
					var content = templatePostContent(post);
					postContentObj.prepend(content);
					
					// 파일 추가
					$.each(post.files, function(index){
						
						var fileInfo = getFileInfo(this.fileName);
	
						var html = templateAttach4Read(fileInfo);
	
						postFileObj.append(html);
					});
					
  					// 내용을 불러왔음을 체크
					postCollapse.data("loaded", 1);
				}
			});
		});
	</script>

	<script>
		/* 게시글 올리기  */

		$("#postAddBtn").on("click", function() {

			var postTitle = $("#postTitle").val();
			var postContent = $("#postContent").val();
			var userNick = $("#userNick").val();
			var filesObj = $(".thumbnail");
			
			var fileArr = new Array();
			
			var modalObj = $('#createModal');
			
			$.each(filesObj, function(index, item) {

				var fullName = $(this).attr("data-src");
				var fileName = $(this).find("h6").text();
				var file = new Object();
				file.fileName = fullName
				file.fileOriginname = fileName;
				file.userNick = userNick;
				fileArr[index] = file;
			});
			
			$.ajax({
				type : 'post',
				url : '/posts',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				data : JSON.stringify({
					courseId : courseId,
					boardId : boardId,
					postTitle : postTitle,
					postContent : postContent,
					userNick : userNick,
					files : fileArr
				}),
				dataType : 'text',
				success : function(result) {
						alert(result)
					if (result == 'SUCCESS') {
						getPage("/courses/" + courseId + "/board/" + boardId + "/posts");
						modalObj.modal('hide');
						
						// 폼 초기화
						modalObj.find("input[type='text'], textarea").val("");
						modalObj.find(".uploadedList .thumbnail").remove();
					}
				}
			});

		});
		
		
	</script>
	
	<script>
	/* 모달창 조작 */
	$('#createModal').on('shown.bs.modal', function (event) {
		
		if(boardId == null || boardId==0){
			alert("게시판을 선택하세요.");
			$("#createModal").modal("hide");
			return;
		}
		
		var board = $("[data-bid=" + boardId +"]").text();
		var modal = $(this);
		modal.find('#postBoard').val(board);
		// 닉네임 창에 닉네임 넣기
	});
		
	
	</script>
</body>
</html>

<!-- 게시판 선택안하면 모달창이 뜨지 않게 하기  ok-->
<!-- 게시글 내용 표시하기  ok-->
<!-- 게시글에 권한에 수정버튼-->
<!-- 파일 삭제기능 -->
<!-- 홈 게시판의 강의 소개는 어떻게 구혀할 것인가? -->

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
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/reply.css?ver=1">
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


.bbp {
   position: absolute;
   top: 5px;
   padding-left: 100px;
   width: 350px;
   float: left;
   color: #92B3B7
}
</style>

</head>
<body>
      <div class="navbar-wrapper">
   <div class="bbp">
      <h1 style="margin:0 auto">ReLearning</h1>
   </div>

      <div class="container">
         <nav class="navbar navbar-default">
            <div class="container">
               <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar">
                     <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand active" href="/user/login">BBPoject</a>
               </div>
               <div id="navbar" class="navbar-collapse collapse">
                  <ul class="nav navbar-nav">
                     <li><a href="/user/login">Home</a></li>
                     <li><a href="#about">About</a></li>
                     <li><a href="#contact">Contact</a></li>
                     <c:if test="${login.userId != null}">
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="/user/mypage">My Page <span class="caret"></span></a>
                           <ul class="dropdown-menu">
                              <li><a href="/user/check">내 게시판</a></li>
                              <li><a href="/qnaboard/list">질문과 답변</a></li>
                              <li><a href="/user/modify">내 계정 변경</a></li>
                              <li><a href="/user/delete">내 계정 삭제</a></li>
                              <li><a href="/user/logout">로그아웃</a></li>
                           </ul></li>
                     </c:if>
                  </ul>
               </div>
            </div>
         </nav>

      </div>
   </div>
   
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

      <!-- 준우 시작 -->

   <div class="container" style="text-align: left;">
      <div class="col-md-12" style="padding-top: 2%;">
         <div class="panel panel-success">
            <div class="panel-heading">
               <h3 class="box-title">새 댓글을 달아보세요!</h3>
            </div>
            <div class="panel-body">
               <label for="exampleInputEmail1">작성자</label>
               <input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter" value="${login.userNick }" readonly="readonly">
               <br />
               <label for="exampleInputEmail1">댓글 내용</label>
               <input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
            </div>
            <div style="padding-left: 1%; padding-bottom: 1%;">
               <button type="button" class="btn btn-info" id="replyAddBtn">댓글 달기</button>
            </div>
         </div>
         <ul class="timeline" style="list-style: none;">
            <li class="time-label" id="repliesDiv"><a> 댓글 리스트 <small id='replycntSmall'> </small>
            </a></li>

         </ul>
      </div>
      <!-- /.col -->
   </div>
   
   
     <!-- modify Modal -->
   <div class="modal modal-default fade" id="modifyModal" role="dialog" aria-labelledby="modalLabel">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">
                  <span aria-hidden="true">×</span><span class="sr-only">Close</span>
               </button>
               <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" data-rno>
               <p>
                  <input type="text" id="commentContent" class="form-control">
               </p>
            </div>
            <div class="modal-footer">
               <div class="btn-group btn-group-justified" role="group" aria-label="group button">
                  <div class="btn-group" role="group">
                     <button type="button" class="btn btn-info" id="replyModBtn">수정</button>
                  </div>
                  <div class="btn-group" role="group">
                     <button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
                  </div>
                  <div class="btn-group" role="group">
                     <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- rereply Modal -->
   <div id="rereplyModal" class="modal modal-default fade" role="dialog">
      <div class="modal-dialog">
         <!-- Modal content-->
         <div class="modal-content">

            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title"></h4>
            </div>

            <div class="modal-body" data-rno>
               <p>
                  <input type="text" id="reCommentContent" class="form-control">
                  <input type="hidden" id="reCommentGroupId">
                  <input type="hidden" id="reCommentStep">
                  <input type="hidden" id="reCommentIndent">
                  <input type="hidden" id="reuserNick" class="userNick" value="${login.userNick }">
               </p>
            </div>

            <div class="modal-footer">
               <button type="button" class="btn btn-info" id="rereplyBtn">답글 달기</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
            </div>

         </div>
      </div>
   </div>
   
  <script id="template" type="text/x-handlebars-template">
{{#each .}}
<div class="row">
   <li class="replyLi" data-rno={{commentId}}>
   <div class="message-item">
      <div class="message-inner">
         <div class="message-head clearfix">
            
               <div class="timeline-item indent" data-indent={{commentIndent}}>

                     <div class="user-detail">
                     <h5 class="handle">
                        <strong>{{commentId}}</strong> -{{userNick}}
                     </h5>

                     <div class="post-meta">
                        <div class="asker-meta">
                           <span class="time"> <i class="fa fa-clock-o"></i>{{prettifyDate commentRegdate}}
                           </span>
                        </div>
                     </div>
                  </div>
               </div>
         </div>
         <div class="message-head clearfix">
         <div class="timeline-body">{{commentContent}}</div></div>
         <input type="hidden" id="commentGroupId" value="{{commentGroupId}}">
         <input type="hidden" id="commentStep" value="{{commentStep}}">
         <input type="hidden" id="commentIndent" value="{{commentIndent}}">

         
         <div class="timeline-footer" >
            {{#eqReplyer userNick}} <a class="btn btn-warning btn-xs" data-toggle="modal" data-target="#modifyModal">수정 하기</a> {{/eqReplyer}}
 <a class="btn btn-info btn-xs" data-toggle="modal" data-target="#rereplyModal">답글 달기</a>
         </div>
   
         </div>
      </div>
      </li>
</div>
{{/each}}

</script>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
   <!-- Bootstrap 3.3.2 JS -->
   <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

   <script>
      $('.modal ').insertAfter($('body'));

      Handlebars.registerHelper("eqReplyer", function(userNick, block) {
         var accum = '';
         if (userNick == '${login.userNick}') {
            accum += block.fn();
         }
         return accum;
      });

      Handlebars.registerHelper("prettifyDate", function(timeValue) {
         var dateObj = new Date(timeValue);
         var year = dateObj.getFullYear();
         var month = dateObj.getMonth() + 1;
         var date = dateObj.getDate();
         return year + "/" + month + "/" + date;
      });

      var printCommentData = function(replyArr, target, templateObject) {

         var template = Handlebars.compile(templateObject.html());

         var html = template(replyArr);
         $(".replyLi").remove();
         target.after(html);
      }

      var postId = ${
         postId
      };

      function getCommentPage(pageInfo) {
         $.getJSON(pageInfo, function(data) {
            printCommentData(data.list, $("#repliesDiv"), $('#template'));
            getIndent();
            $("#replycntSmall").html("[ " + data.replyCount + " ]");
            $("#reCommentContent").val(" ");
            $("#modifyModal").modal('hide');
            $("#rereplyModal").modal('hide');

         });
      }

      $("#repliesDiv").on("click", function() {

         if ($(".timeline li").size() > 1) {
            return;
         }
         getCommentPage("/reply/" + postId);

      });

      $("#replyAddBtn").on("click", function() {
         alert(postId);
         var replyerObj = $("#newReplyWriter");
         var replytextObj = $("#newReplyText");
         var userNick = replyerObj.val();
         var commentContent = replytextObj.val();

         if (commentContent.length == 0) {
            alert("빈칸이 있습니다.");
            return;
         }

         $.ajax({
            type : 'post',
            url : '/reply/',
            headers : {
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
               postId : postId,
               userNick : userNick,
               commentContent : commentContent
            }),
            success : function(result) {
               console.log("result: " + result);
               if (result == 'SUCCESS') {
                  alert("등록 되었습니다.");
                  getCommentPage("/reply/" + postId);
                  replytextObj.val("");
               }
            }
         });
      });

      $(".timeline").on(
            "click",
            ".replyLi",
            function(event) {
               var reply = $(this);
               $("#commentContent").val(reply.find('.timeline-body').text());
               $("#reCommentGroupId").val(reply.find('#commentGroupId').val());
               $("#reCommentStep").val(reply.find('#commentStep').val());
               $("#reCommentIndent").val(reply.find('#commentIndent').val());
               $(".modal-title").html(reply.attr("data-rno"));


            });

      $("#replyModBtn").on("click", function() {
         var commentId = $(".modal-title").html();
         var commentContent = $("#commentContent").val();
         
         $.ajax({
            type : 'put',
            url : '/reply/' + commentId,
            headers : {
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "PUT"
            },
            data : JSON.stringify({
               commentContent : commentContent
            }),
            dataType : 'text',
            success : function(result) {
               console.log("result: " + result);
               if (result == 'SUCCESS') {
                  alert("수정 되었습니다.");
                  getCommentPage("/reply/" + postId);
               }
            }
         });
      });

      /*재 댓글 달기  */
      $("#rereplyBtn").on("click", function() {
         var commentContent = $("#reCommentContent").val();
         var commentGroupId = $("#reCommentGroupId").val();
         var commentStep = $("#reCommentStep").val();
         var commentIndent = $("#reCommentIndent").val();
         var userNick = $("#reuserNick").val();
         if (commentContent.length == 0) {
            alert("빈칸이 있습니다.");
            return;
         }
         $.ajax({
            type : 'post',
            url : '/reply/re',
            headers : {
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "POST"
            },
            data : JSON.stringify({
               postId : postId,
               commentContent : commentContent,
               commentGroupId : commentGroupId,
               commentStep : commentStep,
               commentIndent : commentIndent,
               userNick : userNick
            }),
            dataType : 'text',
            success : function(result) {
               console.log("result: " + result);
               if (result == 'SUCCESS') {
                  alert("등록 되었습니다.");
                  getCommentPage("/reply/" + postId);
               }
            }
         });
      });

      function getIndent() {
         var indent = $("div.timeline-item.indent");

         $.each(indent, function(index, item) {

            var num = $(this).attr("data-indent");
            var get = (95 - (num * 5));

            if (num != 0) {

               $(this).parents(".message-item").css({
                  "width" : get + "%",
                  "float" : "right"
               });

            }
         });
      }

      $("#replyDelBtn").on("click", function() {

         var commentId = $(".modal-title").html();
         var commentContent = $("#commentContent").val();
         $.ajax({
            type : 'delete',
            url : '/reply/' + commentId,
            headers : {
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "DELETE"
            },
            dataType : 'text',
            success : function(result) {
               console.log("result: " + result);
               if (result == 'SUCCESS') {
                  alert("삭제 되었습니다.");
                  getCommentPage("/reply/" + postId);
               }
            }
         });
      });
   </script>

<script src="/resources/bxslider/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="/resources/bxslider/jquery.bxslider.css" rel="stylesheet" />

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
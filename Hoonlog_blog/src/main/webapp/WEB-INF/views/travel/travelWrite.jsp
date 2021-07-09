<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
<script src = "/resources/ckeditor/ckeditor.js"></script>
<title>Travel</title>
<style type="text/css">

h1{
	text-align: center;
	padding: 10px;
}
	
</style>
<script>

$(document).ready(function(){
	var formObj = $("form[name='writeForm']");
	$("#btnSave").on("click", function(){
		formObj.attr("action", "/travel/saveTravel");
		formObj.attr("method", "post");
		formObj.submit();
	});
	fn_addFile();
})

function fn_addFile(){
	var fileIndex = 1;
	//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
	$(".fileAdd_btn").on("click", function(){
		$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"
		+"<button type='button' id='fileDelBtn' class='btn btn-sm btn-success'>"+"삭제"+"</button></div>");
	});
	$(document).on("click","#fileDelBtn", function(){
		$(this).parent().remove();
	});
}


	$(document).on('click', '#btnList', function(e){
		e.preventDefault();
		location.href="/travel/travelList";
	});
	
</script>
</head>
<body>

<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

		<div class="container">
			<form name="writeForm" id="form" method="post" action="/travel/saveTravel" enctype="multipart/form-data">
			<c:if test="${member != null }">
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
				</div>
				<div class="mb-3">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" name="writer" id="writer" value="${member.userid}" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>

				<div class="mb-3">
					<label for="tag">TAG</label>
					<input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요">
				</div>

				<div id="fileIndex">
					<label for="file">파일첨부</label>
					<input type="file" name="file"> <br>
				</div>
				
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				<button class="fileAdd_btn btn btn-sm btn-primary" type="button">파일추가</button>	
			</div>
			
			</c:if>
				</form>
				
			<c:if test="${member == null }">
  				<p align="center"><a href="/member/login">로그인</a> 후에 이용해주세요</p>
 			</c:if>	
		</div>


<script>
CKEDITOR.replace('content',{filebrowserUploadUrl:'/travel/imageUpload'});

</script>
</body>
</html>

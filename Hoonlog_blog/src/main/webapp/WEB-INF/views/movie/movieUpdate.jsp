<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "/resources/ckeditor/ckeditor.js"></script>
<link href="../resources/css/board.css" rel="stylesheet">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
<title>Movie</title>
<style type="text/css">
h1{
	text-align: center;
	padding: 10px;
}
	
.btn {margin-top: 10px;}

</style>
<script>

	$(document).ready(function(){
		var formObj = $("form[name='updateForm']");
		
		$(document).on("click","#fileDel", function(){
			$(this).parent().remove();
		})
		
		fn_addFile();
		
		$("#btnCancel").on("click", function(){
			event.preventDefault();
			location.href = "/movie/movieView?bno=${update.bno}"
				   + "&page=${scri.page}"
				   + "&perPageNum=${scri.perPageNum}"
				   + "&searchType=${scri.searchType}"
				   + "&keyword=${scri.keyword}";
		})
		
		$("#btnSave").on("click", function(){
			formObj.attr("action", "/movie/movieUpdate?bno="+${update.bno});
			formObj.attr("method", "post");
			formObj.submit();
		})
	})
	
 		function fn_addFile(){
			var fileIndex = 1;
			$(".fileAdd_btn").on("click", function(){
				$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"
				+"<button type='button' id='fileDelBtn' class='btn btn-sm btn-success'>"+"삭제"+"</button></div>");
			});
			$(document).on("click","#fileDelBtn", function(){
				$(this).parent().remove();
				
			});
		}
 		var fileNoArry = new Array();
 		var fileNameArry = new Array();
 		function fn_del(value, name){
 			
 			fileNoArry.push(value);
 			fileNameArry.push(name);
 			$("#fileNoDel").attr("value", fileNoArry);
 			$("#fileNameDel").attr("value", fileNameArry);
 		}	
	
	$(document).on('click', '#btnList', function(){
		location.href="/movie/movieList?bno={update.bno}"
				+ "&page=${scri.page}"
			    + "&perPageNum=${scri.perPageNum}"
			    + "&searchType=${scri.searchType}"
			    + "&keyword=${scri.keyword}";
	});
	
</script>
</head>
<body>


<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

		<div class="container" role="main">
			<form id="form" name="updateForm" method="post" action="/movie/movieUpdate" enctype="multipart/form-data">
				<input type="hidden" name="bno" value="${update.bno}" readonly="readonly"/>
				<input type="hidden" id="page" name="page" value="${scri.page}"> 
				<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
				<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
				<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
				<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
				<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
					
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" value="${update.title}" placeholder="제목을 입력해 주세요"/>
				</div>
				<div class="mb-3">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" name="writer" id="writer" value="${update.writer}" readonly="readonly" />
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" name="content" id="content" placeholder="내용을 입력해 주세요"><c:out  value="${update.content}" /></textarea>
				</div>

				<div class="mb-3">
					<label for="tag">TAG</label>
					<input type="text" class="form-control" name="tag" id="tag" value="${update.tag}" placeholder="태그를 입력해 주세요"/>
				</div>
				
				<div id="fileIndex">
						<c:forEach var="file" items="${file}" varStatus="var">
							<div>
								<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO}">
								<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
								<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
								<button id="fileDel" onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');" class= "btn btn-sm btn-success" type="button">삭제</button><br>
							</div>
						</c:forEach>
				</div>
				
			<div>
				<button type="button" class="btn btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-primary" id="btnCancel">취소</button>
				<button type="button" class="fileAdd_btn btn btn-warning">파일추가</button>
			</div>
			
			
			
		</form>	
		</div>

	
<script>
CKEDITOR.replace('content',{filebrowserUploadUrl:'/movie/imageUpload'});
</script>	
</body>
</html>

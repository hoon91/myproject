<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/util.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
<title>movie</title>
<script>
	//글 작성 이동
	$(document).on('click', '#btnWriteForm', function(e){
		e.preventDefault();
		location.href = "/movie/movieWrite";
	});
	
	
	/* 글상세보기 이벤트*/
	function fn_contentView(bno){
		var url = "/movie/movieView";
		url = url + "?bno="+bno;
		location.href = url;
	}
	
	$(function() {
		$('#searchBtn').click(function() {
		self.location = "movieList"
				+ '${pageMaker.makeQuery(1)}'
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword="
				+ encodeURIComponent($('#keywordInput').val());
				});
		});

</script>

<style type="text/css">
#page li {
	list-style: none; 
	padding: 10px; 
	display:inline-block;
}
</style>

</head>
<body>

<header class="header">
	<%@ include file="../include/header.jsp" %>
</header>


	<div class="container">
			<h1 align="center" style="margin-bottom: 30px;">MOVIE</h1>
		<hr>
		
		<div align ="right">
		<button type="button" class="btn btn-primary" id="btnWriteForm">글작성</button>
		</div>	
		
		<table class="table table-striped table-sm">
				
			<thead>
			<tr>
				<th style="width: 100px; text-align: center;">번호</th>
				<th style="width: 600px; text-align: center;">제목</th>
				<th style="width: 150px; text-align: center;">작성자</th>
				<th style="width: 100px; text-align: center;">등록일</th>
				<th style="width: 100px; text-align: center;">수정일</th>
				<th style="width: 100px; text-align: center;">조회수</th>
			</tr>
			</thead>
	
			<tbody>	
				<c:choose>
					<c:when test="${empty movieList}" >
						<tr><td colspan="6" align="center">데이터가 없습니다.</td></tr>
					</c:when> 
						
					<c:when test="${!empty movieList}">
					<c:forEach items="${movieList}" var = "list">
						<tr>
							<td style="width: 100px; text-align: center;"><c:out value="${list.bno}" /></td>
							<td>
								<a href="/movie/movieView?bno=${list.bno}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchType=${scri.searchType}&keyword=${scri.keyword}">
								<c:out value="${list.title}" />
								<c:if test="${list.reply_count > 0}">
								<span style="color:red; font-weight: bold;">(${list.reply_count})</span>
								</c:if>
								</a>
							</td>
							<td style="width: 200px; text-align: center;"><c:out value="${list.writer}" /></td>
							<td style="width: 200px; text-align: center;"><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
							<td style="width: 200px; text-align: center;"><fmt:formatDate value="${list.updatedate}" pattern="yyyy-MM-dd"/></td>
							<td style="width: 100px; text-align: center;"><c:out value="${list.viewCnt}" /></td>
						</tr>
					</c:forEach>
					</c:when>
				</c:choose>	
			</tbody>
		</table>
		
		
		
		<div align="center">
  			<ul class="pagination justify-content-center">
   			 	<c:if test="${pageMaker.prev}">
    			<li class="page-item"><a href="/movie/movieList${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
   				</c:if> 

    			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    			<li class="page-item"><a href="/movie/movieList${pageMaker.makeSearch(idx)}">${idx}</a></li>
    			</c:forEach>

    			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    			<li class="page-item"><a href="/movie/movieList${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    			</c:if> 
  			</ul>
		</div>
		
		
		<div class="search" align="center">
			<select name="searchType">
				<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
				<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
				<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
				<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
				<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
			</select> 
			
			<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" />
			<button id="searchBtn" type="button" class="btn btn-sm btn-primary">검색</button>				
		</div>
	</div>

	
		 


</body>
</html>

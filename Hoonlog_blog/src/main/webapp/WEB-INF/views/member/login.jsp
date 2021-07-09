<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
</head>
<style>
h1{
	text-align: center;
	padding: 30px;
}
</style>
<body>

<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

<div class="container">
		<div>
			<h1>로 그 인</h1>
		</div>
		<div>
			<form class="form-horizontal" action="/member/login" method="post">
				<div class="form-group">
					<label for="userid" class="col-sm-5 control-label">아이디</label>
				<div class="col-xs-3">
					<input id="userid" class="form-control" name="userid" placeholder="ID">
				</div>
				</div>
				<div class="form-group">
					<label for="userpw" class="col-sm-5 control-label">비밀번호</label>
					<div class="col-xs-3">
						<input type="password" class="form-control" id="userpw" name="userpw" placeholder="Password">
					</div>
				</div>

				<div align="center">
				 <c:if test="${msg == false}">
  					 <p style="color:#f00;">로그인에 실패했습니다. <br> 아이디와 비밀번호를 확인해주세요</p>
  				</c:if>
  				</div>
  
				<div class="form-group" id="buttons" align="center">
					<div>
						<button type="submit" class="btn btn-default">로그인</button>
						<button class="btn btn-default">회원가입</button>
					</div>
				</div>
			</form>
		</div>
		
	</div>

</body>
</html>
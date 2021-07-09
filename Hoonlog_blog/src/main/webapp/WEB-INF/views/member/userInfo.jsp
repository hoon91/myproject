<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
<link href="<c:url value="/resources/css/hoonlog_main.css"/>" rel="stylesheet">
<style type="text/css">
h1{
	text-align: center;
	padding: 10px;
}
</style>
</head>
<body>


<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

<section class="content">

	<aside>
		<%@ include file="../include/aside.jsp" %>
	</aside>
	
	<div id="content_box">
		
		<div class="row">
			<h1>내 정보 보기</h1>
			<hr>
		</div>
		<div class="row form-horizontal">
			
				<div class="form-group">
					<label for="userid" class="col-sm-4 control-label">아이디</label>
					<div class="col-sm-4">
						<input class="form-control" id="userid" placeholder="ID" name="userid" value="${userInfo.userid}" readonly>
						<span></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">이름</label>
					<div class="col-sm-4">
						<input class="form-control" id="username" placeholder="Name" name="username" value="${userInfo.username}" readonly>
					</div>
				</div>				
				<div class="form-group">
					<label for="userNname" class="col-sm-4 control-label">닉네임</label>
					<div class="col-sm-4">
						<input class="form-control" id="userNname" placeholder="NicName" name="userNname" value="${userInfo.userNname}" readonly>
					</div>
				</div>				
				<div class="form-group">
					<label for="useraddress" class="col-sm-4 control-label">Address</label>
					<div class="col-sm-4">
				    	<input class="form-control" id="useraddress" name="useraddress" placeholder="도로명주소" value="${userInfo.useraddress}" readonly>
				    </div>
				</div>		
				<div class="form-group">
					<label for="email" class="col-sm-4 control-label">Email</label>
					<div class="col-sm-4">
						<input type="email" class="form-control" id="email" placeholder="Email" name="email" value="${userInfo.email}" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="tel" class="col-sm-4 control-label">연락처</label>
					<div class="col-sm-4">
						<input type="tel" class="form-control" id="tel" placeholder="Tel" name="tel" value="${userInfo.tel}" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="birthDate" class="col-sm-4 control-label">BitrhDate</label>
					<div class="col-sm-4">
						<input type="date" class="form-control" id="birthDate" placeholder="BirthDate" name="birthDate" value="${userInfo.birthDate}" readonly>
					</div>
				</div>				
				<div class="form-group" align="center">
					<div class="col-sm-offset-2 col-sm-8">
						<button class="btn btn-default" id="update_userInfo">회원정보 수정</button>
						<button class="btn btn-default" id="back_to_main">메인으로</button>
					</div>
				</div>
		</div>
	</div>
</section>


<script type="text/javascript">
	$(document).ready(function () {
		$("#back_to_main").click(function() {
		location.assign("/");
		});
	});

	$("#update_userInfo").click(function() {
		var userid = $("#userid").val();
		location.assign("/member/updateUserInfo/" + userid);
	});
</script>

</body>
</html>
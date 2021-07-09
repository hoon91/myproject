<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
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
			<h1>내 정보 수정</h1>
			<hr>
		</div>
		<div class="row">
			<form class="form-horizontal" action="/member/updateUserInfo/${userInfo.userid}" method="post">

				<div class="form-group">
					<label for="userid" class="col-sm-4 control-label">아이디</label>
					<div class="col-sm-4">
						<input class="form-control" id="userid" name="userid" value="${userInfo.userid}" readonly>
					</div>
				</div>
				
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">이름</label>
					<div class="col-sm-4">
						<input class="form-control" id="username" name="username" value="${userInfo.username}">
					</div>
				</div>				
				<div class="form-group">
					<label for="userNname" class="col-sm-4 control-label">닉네임</label>
					<div class="col-sm-4">
						<input class="form-control" id="userNname" name="userNname" value="${userInfo.userNname}">
					</div>
				</div>				
				<div class="form-group">
					<label for="useraddress" class="col-sm-4 control-label">Address</label>
					<div class="col-sm-4">
    					<input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${userInfo.postcode}" class="form-control">
       					<input type="button" class="btn btn-default btn-sm" id="searchAdd" value="우편번호 찾기"><br>
				    	<input type="text" id="roadAddress" name="useraddress"value="${userInfo.useraddress}" class="form-control">
				    	<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" class="form-control">
				    </div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-4 control-label">Email</label>
					<div class="col-sm-4">
						<input type="email" class="form-control" id="email" name="email" value="${userInfo.email}">
					</div>
				</div>
				<div class="form-group">
					<label for="tel" class="col-sm-4 control-label">연락처</label>
					<div class="col-sm-4">
						<input type="tel" class="form-control" id="tel"  name="tel" value="${userInfo.tel}">
					</div>
				</div>
				<div class="form-group">
					<label for="birthDate" class="col-sm-4 control-label">BitrhDate</label>
					<div class="col-sm-4">
						<input type="date" class="form-control" id="birthDate" name="birthDate" value="${userInfo.birthDate}">
					</div>
				</div>				
				<div class="form-group" align="center">
					<div class="col-sm-offset-2 col-sm-8">
						<button type="button" class="updateInfo btn btn-default submit">수정하기</button>
						<button class="btn btn-default" id="back_to_myPage">이전으로</button>
					</div>
				</div>
				</form>
		</div><!-- class=row -->
	</div><!-- class=container -->
</section>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">

$(".updateInfo").click(function() {

	alert("정보가 수정되었습니다.");
	$("form").submit();
});

	$("#back_to_myPage").click(function(event) {
		event.preventDefault();
	
		var userid = $("#userid").val();
		location.assign("/member/userInfo/" + userid);
	});
	
	
	$("#searchAdd").click(function(event) {
		event.preventDefault();
		postcode();

	});


	function postcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var extraRoadAddr = ''; // 참고 항목 변수

	            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }
	            // 건물명이 있고, 공동주택일 경우 추가한다.
	            if(data.buildingName !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }
	            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	            if(extraRoadAddr !== ''){
	                extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }

	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("roadAddress").value = roadAddr;
	            
	        }

	    }).open();
	}
	
	
</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<link href="<c:url value="/resources/css/hoonlog_main.css"/>" rel="stylesheet">
<style type="text/css">
h1, h3, h4{text-align: center; padding: 5px;}
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
	
		<div>
			<h1>정말로 탈퇴하시겠어요?</h1>
			<h3>탈퇴하기 버튼을 누르시면 모든 정보가 사라집니다.</h3>
			    <h4>탈퇴를 원하시면 비밀번호를 입력하세요.</h4>
		</div>
	
	
	<div id="content_box">
		<div class="row deleteForm">
		<form class="form-horizontal" action="/member/deleteUser" method="post">
			<div class="form-group">
				<label for="userid" class="col-sm-4 control-label">아이디</label>
				<div class="col-xs-3">
					<input class="form-control" id="userid" name="userid" value="${userInfo.userid}" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-sm-4 control-label">비밀번호</label>
				<div class="col-xs-3">
					<input type="password" class="form-control" id="userpw" name="userpw" required>
					<input type="hidden" id="realUserPw" value="${userInfo.userpw}">
				</div>
			</div>
			<div class="form-group" align="center">
				<div class="col-sm-offset-2 col-sm-7">
					<div class="checkbox">
						<label><input type="checkbox" id="checkbox" required>탈퇴 시 주의사항을 확인했습니다.</label>
					</div>
				</div>
			</div>
			<div class="form-group" align="center">
				<div class="col-sm-offset-2 col-sm-7">
					<button type="button" class="btn btn-default" id="drop_out_of_member">탈퇴하기</button>
				</div>
			</div>
		</form>
		</div><!-- class=row -->
	</div><!-- class=container -->
</section>


<script type="text/javascript">
$(document).ready(function() {

	$("#drop_out_of_member").click(function(event) {
		event.preventDefault();
		
		var choice = confirm("정말로 탈퇴하시겠습니까?");
		/* 컨펌창에서 확인을 누르면 */
		if (choice) {
			
			var userpw = $("#userpw").val(); /* input으로 입력받은 비밀번호 값 */
			var realUserPw = $("#realUserPw").val(); /* DB에서 넘겨받은 비밀번호 값 */ 
			var isChecked = $("#checkbox").prop("checked"); /* 체크박스의 체크 여부를 가져오는 변수 */
			
			
			if (userpw == '') {
				/* 1. 비밀번호 입력란이 공란일 때 alert을 띄운 뒤 비밀번호 입력란에 focus */
				alert("비밀번호를 입력해주세요.");
				$("#userpw").focus();
			
			} else {
				/* 2. 비밀번호는 입력되어있는데 체크박스가 체크 안 된 경우 */
				if (!isChecked) { /* isChecked의 값이 false인 경우 */
					
					alert("탈퇴 동의란에 체크 해주세요.");
					/* alert을 띄워 동의란에 체크하도록 함 */
					
				} else {
					/* 3. 비밀번호, 체크박스 모두 입력되어있는데, DB에 저장된 비밀번호와 다른 경우 */
					if (userpw != realUserPw) {
						/* alert창을 띄워 비밀번호를 확인하게 한 뒤, 비밀번호 입력란에 focus */
						alert("잘못된 비밀번호입니다.");
						$("#userpw").focus();
					} else {
						/* 세가지 경우 모두에 해당하지 않는 경우 : 옳은 비밀번호 + 체크박스 체크 */
						$("form").submit();
					 	/* from 태그를 제출한다 */
					}

				}

			}

		} else { 
			var userid = $("#userid").val();
			location.assign("/");
		}
	});

});

</script>

</body>
</html>
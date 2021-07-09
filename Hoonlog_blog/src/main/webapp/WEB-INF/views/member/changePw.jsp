<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link href="<c:url value="/resources/css/hoonlog_main.css"/>" rel="stylesheet">
</head>
<body>

<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

<section class="content">

<aside>
	<%@ include file="../include/aside.jsp" %>
</aside>
	
	<div align="center">
		<h1>비밀번호 변경</h1>
	</div>
	<hr>
	
	<div id="content_box">	
		<div class="row">
		<form class="form-horizontal" action="/member/changePw" method="post">
			<div class="form-group">
				<label for="userid" class="col-sm-4 control-label">아이디</label>
				<div class="col-xs-3">
					<input class="form-control" id="userid" name="userid" value="${userInfo.userid}" readonly>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userpw" class="col-sm-4 control-label">기존 비밀번호</label>
				<div class="col-xs-3">
					<input type="password" id="userpw0" name="userpw0" class="form-control" onkeyup="userpassConfirm()">
					<input type="hidden" id="realUserPw" value="${userInfo.userpw}">
					<span id ="msg"></span>
				</div>
			</div>
			
			<div class="form-group">
					<label for="userpw" class="col-sm-4 control-label">변경할 비밀번호</label>
					<div class="col-xs-3">
						<input type="password" id="changeuserpw" placeholder="Password" name="userpw" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="userpw" class="col-sm-4 control-label">비밀번호 확인</label>
					<div class="col-xs-3">
						<input type="password" id="changeuserpw2" placeholder="Password" class="form-control" onkeyup="passConfirm()">
						<span id ="confirmMsg"></span>
					</div>
				</div>				
				
				
			<div class="form-group" align="center">
				<div class="col-sm-offset-2 col-sm-7">
					<button type="submit" class="btn btn-default" id="change_pw">변경하기</button>
				</div>
			</div>
		</form>
		</div><!-- class=row -->
	</div><!-- class=container -->
</section>


<script type="text/javascript">

function passConfirm() {
	/* 비밀번호, 비밀번호 확인 입력창에 입력된 값을 비교해서 같다면 비밀번호 일치, 그렇지 않으면 불일치 라는 텍스트 출력.*/
	/* document : 현재 문서를 의미함. 작성되고 있는 문서를 뜻함. */
	/* getElementByID('아이디') : 아이디에 적힌 값을 가진 id의 value를 get을 해서 password 변수 넣기 */
		
		var changepassword = document.getElementById('changeuserpw');					//비밀번호 
		var passwordConfirm = document.getElementById('changeuserpw2');	//비밀번호 확인 값
		var confrimMsg = document.getElementById('confirmMsg');				//확인 메세지
		var correctColor = "black";	//맞았을 때 출력되는 색깔.
		var wrongColor ="red";	//틀렸을 때 출력되는 색깔
		
		if (changepassword.value == passwordConfirm.value){//password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
			confirmMsg.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
			confirmMsg.innerHTML ="비밀번호가 일치합니다.";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
		}else{
			confirmMsg.style.color = wrongColor;
			confirmMsg.innerHTML ="비밀번호가 다릅니다. 다시 입력해주세요";
		}
		
		var userpw = document.getElementById('userpw0');
		if (userpw.value == changepassword.value) {
			confirmMsg.style.color = wrongColor;
			confirmMsg.innerHTML ="현재 사용중인 비밀번호입니다.";
		}
}


function userpassConfirm() {
	
	var userpw = $("#userpw0").val(); /* input으로 입력받은 비밀번호 값 */
	var realUserPw = $("#realUserPw").val(); /* DB에서 넘겨받은 비밀번호 값 */ 
	var msg = document.getElementById('msg');	
	
	if (userpw != realUserPw) {
		msg.innerHTML ="비밀번호가 다릅니다.";
	} else{	
		msg.innerHTML ="비밀번호가 일치합니다.";
	}
}




$(document).ready(function() {
	$("#change_pw").on("click",function(){
		
		var pw=$("#changeuserpw").val();
		var pw2=$("#changeuserpw2").val();
		if(pw!=pw2){
			alert("비밀번호가 일치하지 않습니다.")
			$("#changeuserpw2").focus();
			return false;
		}
		alert("비밀번호가 변경되었습니다. 다시 로그인 해주세요");
		$("form").submit();
	})
		
})



</script>


</body>
</html>
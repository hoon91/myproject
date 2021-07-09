<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/util.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
<title>회원가입</title>
<style type="text/css">
/*	.btn {
		margin-top: 10px;
	}
	*/
	h1{
		text-align: center;
		padding: 10px;
	}
	
	input {
		margin-top: 5px;
	}
</style>
</head>
<body>

<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

<div id="container" style="margin: 100px;">
	<div id="container_box">
		
		<div class="row">
			<h1>회원 가입</h1>
		</div>
		<div class="row">
			<form class="form-horizontal" action="/member/signup" method="post">
				<div class="form-group">
					<label for="userid" class="col-sm-5 control-label">아이디</label>
					<div class="col-sm-3">
						<input id="userid" placeholder="ID" name="userid" class="form-control">
						<input type="button" class="btn btn-default btn-sm" id="idCheck" value = "중복확인">
						<span></span>
					</div>
				</div>
				<div class="form-group">
					<label for="userpw" class="col-sm-5 control-label">비밀번호</label>
					<div class="col-sm-3">
						<input type="password" id="userpw" placeholder="Password" name="userpw" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="userpw2" class="col-sm-5 control-label">비밀번호 확인</label>
					<div class="col-sm-3">
						<input type="password" id="userpw2" class="form-control" placeholder="Password"  onkeyup="passConfirm()">
						<span id ="confirmMsg"></span>
					</div>
				</div>				
				<div class="form-group">
					<label for="username" class="col-sm-5 control-label">이름</label>
					<div class="col-sm-3">
						<input type="text" id="username" placeholder="Name" name="username" class="form-control">
					</div>
				</div>				
				<div class="form-group">
					<label for="userNname" class="col-sm-5 control-label">닉네임</label>
					<div class="col-sm-3">
						<input type="text" id="userNname" placeholder="NicName" name="userNname" class="form-control">
					</div>
				</div>				
				<div class="form-group">
					<label for="useraddress" class="col-sm-5 control-label">Address</label>
					<div class="col-sm-3">
    					<input type="text" id="postcode" name="postcode" placeholder="우편번호" class="form-control">
       					<button type="button" class="btn btn-default btn-sm" id="searchAdd">우편번호 찾기</button><br>
				    	<input type="text" id="roadAddress" name="useraddress" placeholder="도로명주소" class="form-control"><br>
				    	<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" class="form-control">
				    </div>
				</div>		
				<div class="form-group">
					<label for="email" class="col-sm-5 control-label">Email</label>
					<div class="col-sm-3">
						<input type="email"  id="email" placeholder="Email" name="email" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="tel" class="col-sm-5 control-label">연락처</label>
					<div class="col-sm-3">
						<input type="tel"  id="tel" placeholder="Tel" name="tel" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="birthDate" class="col-sm-5 control-label">BitrhDate</label>
					<div class="col-sm-3">
						<input type="date"  id="birthDate" placeholder="BirthDate" name="birthDate" class="form-control">
					</div>
				</div>				
				<div class="form-group" align="center">
					<div class="col-sm-offset-2 col-sm-9">
						<button type="submit" class="signup btn btn-default">회원 가입</button>
					</div>
				</div>
			</form>
		</div><!-- class=row -->
	</div><!-- class=container -->
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">

$(".signup").click(function() {

	alert("회원가입이 완료되었습니다.");

});



$(document).ready(function() {
	$("#idCheck").click(function(event) {
		event.preventDefault();
		var userid = $("#userid").val(); 
		
		$.ajax({
			
			type : 'post',
			url : '/member/idCheck',
			data : {
				userid : userid
			},
			dataType : 'text',
			success : function(result) {
				
				if (result == 1) {
					var span = $("#idCheck").next();
					span.html("사용중인 아이디 입니다.");
					$("#inputId").focus();
				} else {
					var span = $("#idCheck").next();
					span.html("사용 가능한 아이디 입니다.");
				}
			}
		});
		
	});
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

/* 자바 스크립트 함수 선언(비밀번호 확인) */

function passConfirm() {
/* 비밀번호, 비밀번호 확인 입력창에 입력된 값을 비교해서 같다면 비밀번호 일치, 그렇지 않으면 불일치 라는 텍스트 출력.*/
/* document : 현재 문서를 의미함. 작성되고 있는 문서를 뜻함. */
/* getElementByID('아이디') : 아이디에 적힌 값을 가진 id의 value를 get을 해서 password 변수 넣기 */
	var password = document.getElementById('userpw');					//비밀번호 
	var passwordConfirm = document.getElementById('userpw2');	//비밀번호 확인 값
	var confrimMsg = document.getElementById('confirmMsg');				//확인 메세지
	var correctColor = "black";	//맞았을 때 출력되는 색깔.
	var wrongColor ="red";	//틀렸을 때 출력되는 색깔
	
	if(password.value == passwordConfirm.value){//password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
		confirmMsg.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
		confirmMsg.innerHTML ="비밀번호가 일치합니다.";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
	}else{
		confirmMsg.style.color = wrongColor;
		confirmMsg.innerHTML ="비밀번호가 다릅니다. 다시 입력해주세요";
	}
}
</script>

</body>
</html>
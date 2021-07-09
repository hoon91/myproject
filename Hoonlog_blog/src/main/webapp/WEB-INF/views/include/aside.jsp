<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li><a href="/member/userInfo/${member.userid}">내정보보기</a></li>
	<li><a href="/member/deleteUser/${member.userid}">회원탈퇴</a></li>
	<li><a href="/member/changePw/${member.userid}">비밀번호 변경</a></li>
</ul>
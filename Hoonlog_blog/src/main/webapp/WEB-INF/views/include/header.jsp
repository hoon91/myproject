<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.nav { margin: 10px;}
.navbar { height: 80px;}

#nav-a {color: white; font-size: 18px; }
#membername {border-bottom: 1px solid white; color:white; margin-top: 10px; font-size: 15px;}
</style>
				
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
     <div class="container-fluid">

      <div class="nav navbar-header">
        <a class="navbar-brand" href="/" style="color:white; font-size: 40px;">Hoonlog</a>
      </div>

    <div class="nav navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a id="nav-a" href="/board/boardList">ETC</a></li>
        <li><a id="nav-a" href="/diary/diaryList">DIARY</a></li>
        <li><a id="nav-a" href="/travel/travelList">TRAVEL</a></li>
        <li><a id="nav-a" href="/movie/movieList">MOVIE</a></li>
        <li><a id="nav-a" href="/it/itList">IT</a></li>
      </ul>

	<ul class="nav navbar-nav navbar-right">
		<c:if test="${member == null}">
			<li><a href="/member/login" id="nav-a">로그인</a></li>
			<li><a  href="/member/signup" id="nav-a">회원가입</a></li>
		</c:if>
		<c:if test="${member != null}">
			<li id="membername">${member.userNname} 님, 환영합니다.</li>
			<li><a href="/member/userInfo/${member.userid}" id="nav-a">마이페이지</a></li>
			<li><a href="/member/logout" class="logout" id="nav-a" >로그아웃</a></li>
		</c:if>	
	</ul>
	</div>
	</div>
	</div>	
		
		
		
<script>
$(".logout").click(function(event) {
	event.preventDefault();
	
	var logout = confirm("로그아웃 하시겠습니까?");
	
	if (logout) {
		location.assign("/member/logout");
	}
});


</script>		
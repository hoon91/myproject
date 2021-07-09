<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/util.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>메인화면</title>
<style>
h1 {text-align: center;}
a.carousel-control {
	width: 10px;
	opacity: 0;
	}

</style>

</head>

<body>

<header id="header">
	<%@ include file="include/header.jsp" %>
</header>


		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center" style="margin-top: 85px;">
		
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
          <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
        </ol>
        <div class="carousel-inner">
          <div class="item">
            <img style="width: 1340px; height: 650px;" src="../resources/image/img.jpg" >
          </div>
          <div class="item active">
            <img style="width: 1340px; height: 650px;" src="../resources/image/img2.jpg" >
           </div>
          <div class="item">
           	<img style="width: 1340px; height: 650px;" src="../resources/image/img3.jpg" >
           </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>



</body>
</html>
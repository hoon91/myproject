<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/util.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/board.css" rel="stylesheet">
<link href="../resources/css/hoonlog_main.css" rel="stylesheet">
<title>Diary</title>

<script>
	/* 목록으로 이동 이벤트 */
	$(document).on('click', '#btnList', function(){
		location.href = "/diary/diaryList?page=${scri.page}"
						+"&perPageNum=${scri.perPageNum}"
						+"&searchType=${scri.searchType}&keyword=${scri.keyword}";
	});
	
	/* 수정 버튼 이벤트*/
	$(document).on('click', '#btnUpdate', function(){
		var url = "/diary/diaryUpdate";
		url = url + "?bno="+${diaryView.bno};
		location.href = url;
	});
	
	/* 삭제 버튼 이벤트 */
	$(document).on('click', '#btnDelete', function(){
		var deleteYN = confirm("삭제하시겠습니까?");
		if(deleteYN == true) {
			alert("삭제가 완료되었습니다.")
			var url = "/diary/diaryDelete";
    		url = url + "?bno=" + ${diaryView.bno};
			location.href = url;
		}
	});
	

	function fn_fileDown(fileNo){
		var formObj = $("form[name='readForm']");
		$("#FILE_NO").attr("value", fileNo);
		formObj.attr("action", "/diary/fileDown");
		formObj.submit();
	}
	
	function replyList(){
		 var bno = ${diaryView.bno};
		 $.getJSON("/diary/replyList" + "?bno=" + bno, function(data){
		  var str = "";
		  
		  $(data).each(function(){
		   
		   console.log(data);
		   
		   var regdate = new Date(this.regdate);
		   regdate = regdate.toLocaleDateString("ko-KR", {
			   	year: 'numeric',
			   	month: '2-digit',
			   	day: '2-digit',
			   	hour: '2-digit',
			   	minute: '2-digit'
		   })
		   
		   str += "<li data-rno='" + this.rno + "'>"
		     + "<div class='userInfo'>"
		     + "<span class='glyphicon glyphicon-user'></span>"
		     + "<span class='username'>" + this.userid +"("+ this.userNname + ")</span> &nbsp;"
		     + "<span class='glyphicon glyphicon-calendar'></span>"
		     + "<span class='date'>" + regdate + "</span> &nbsp;"
		     + "<a data-toggle='modal' data-target='#myModal' class='modify' data-rno='" + this.rno + "'>수정</a> &nbsp;"
		     + "<a class='delete' data-rno='" + this.rno + "'>삭제</a>"
		     
		     + "</div>"
		     + "<div class='replyContent'>" + this.replyContent + "</div>"
		     + "</li>";           
		  });
		  
		  $("section.replyList ol").html(str);
		 });
		}
	
</script>
<style>

h1{
	text-align: center;
	padding: 10px;
}
section.replyList ol li { padding:5px 0; border-bottom:2px solid #eee; }
section.replyList div.replyContent { padding:2px; margin:2px 10px; }
section.replyList div.userInfo .userName { font-size:15px; font-weight:bold; }
</style>
</head>
<body>

<header id="header">
	<%@ include file="../include/header.jsp" %>
</header>

	<div class="container">
			<form name="readForm" role="form" method="post">
			
  				<input type="hidden" id="bno" name="bno" value="${diaryView.bno}" />
  				<input type="hidden" id="page" name="page" value="${scri.page}"> 
  				<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
  				<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
  				<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
				<input type="hidden" id="FILE_NO" name="FILE_NO" value=""> 
			</form>

			<div class="bg-white rounded shadow-sm">
			<c:if test="${member != null}">
				<div class="board_title"><c:out value="${diaryView.title}"/></div>
				<div class="board_info_box">
					<span class="board_writer">
					<i class="fa fa-user-circle-o" aria-hidden="true"></i>
					<c:out value="${diaryView.writer}"/></span>
					
					<i class="fa fa-clock-o" aria-hidden="true"></i>
					<span class="board_date"><fmt:formatDate value="${diaryView.regdate}"  pattern="yy-MM-dd hh:mm"/></span>
					
					<i class="fa fa-eye" aria-hidden="true"></i>
					<span class="board_view_cnt"><c:out value="${diaryView.viewCnt}"/></span>
				</div>
				
				<div class="board_content">${diaryView.content}</div>
				<div class="board_tag">TAG : <a><c:out value="${diaryView.tag}"/></a></div>
			
			
			<span>파일 목록</span>
				<div class="form-group" style="border: 1px solid #dbdbdb;">
					<c:forEach var="file" items="${file}">
						<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
					</c:forEach>
				</div>	

				
			<div style="margin-top : 20px">
				<c:if test="${member.userid == diaryView.writer}">
				<button type="button" class="btn btn-primary" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-primary" id="btnDelete">삭제</button>
				</c:if>
				<button type="button" class="btn btn-primary" id="btnList">목록</button>
			</div>
</c:if>
		</div>	
	<hr>
	
		
		

 
  <c:if test="${member != null}">
<div id="reply">

 <section class="replyForm">
  <form role="form" method="post" autocomplete="off">
  <input type="hidden" name="bno" id="bno" value="${diaryView.bno}">
   <div class="mb-3">
   	<label for="reply">댓글</label>
   </div>
   <div class="input_area">
   <textarea name="replyContent" id="replyContent" placeholder="댓글을 작성하세요" style="width: 1050px; height:40px;"></textarea>
 
   <button type="button" id="reply_btn" class="btn btn-default" style="float: right;">작성</button>
   </div>
   
  </form>
 </section>
 
 
 <section class="replyList">
 <div class="mb-5">
 <label for="replylist">댓글 목록</label>
</div>
 <ol>
 </ol>    
 <script type="text/javascript"> 
	replyList();
</script>   

</section>

</div>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">댓글 수정</h4>
      </div>
      <div class="modal-body">
      <textarea class="modal_replyContent" name="modal_replyContent" style="width:550px; height:auto;"></textarea>
      </div>
      <div class="modal-footer">
         <button type="button" class="modal_modify_btn btn btn-warning" data-dismiss="modal">수정</button>
   		<button type="button" class="modal_cancel btn btn-danger" data-dismiss="modal">취소</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</c:if>

<c:if test="${member == null }">
  <p align="center"><a href="/member/login">로그인</a> 후에 이용해주세요</p>
 </c:if>	
</div>

<script>

$("#reply_btn").click(function(){
	  
	  var formObj = $(".replyForm form[role='form']");
	  var bno = $("#bno").val();
	  var replyContent = $("#replyContent").val()
	  
	  var data = {
	    bno : bno,
	    replyContent : replyContent
	    };
	  
	  $.ajax({
	   url : "/diary/replyWrite",
	   type : "post",
	   data : data,
	   success : function(){
		alert("댓글이 등록되었습니다.");
	    replyList();
	    $("#replyContent").val("");
	   }
	  });
	 });


$(document).on("click", ".delete", function(){
	  
	  var deletConfirm = confirm("정말로 삭제하시겠습니까?");
	  
	  if(deletConfirm) {
		 
	  var data = {rno : $(this).attr("data-rno")};
	   
	  $.ajax({
	   url : "/diary/deleteReply",
	   type : "post",
	   data : data,
	   success : function(result){
		   
		   if(result == 1) {
		    replyList();
		   } else {
		    alert("작성자 본인만 할 수 있습니다.");     
		   }
		  },
		  error : function() {
			  alert("로그인을 해주세요.");
		  }
	  });
	  }
	 });
	 
	 
$('#myModal').on('shown.bs.modal', function () {
	$('.modal_replyContent').focus();
	});
	
$(document).on("click", ".modify", function(){
	 var rno = $(this).attr("data-rno");
	 var replyContent = $(this).parent().parent().children(".replyContent").text();
	 
	 $(".modal_replyContent").val(replyContent);
	 $(".modal_modify_btn").attr("data-rno", rno);
});


$(".modal_modify_btn").click(function(){
	 var modifyConfirm = confirm("수정하시겠습니까?");
	 
	 if(modifyConfirm) {
	  var data = {
	     rno : $(this).attr("data-rno"),
	     replyContent : $(".modal_replyContent").val()
	    };  // ReplyVO 형태로 데이터 생성
	  
	  $.ajax({
	   url : "/diary/updateReply",
	   type : "post",
	   data : data,
	   success : function(result){
	    
	    if(result == 1) {
	     replyList();
	     $('#myModal').modal('hide');
	    } else {
	     alert("작성자 본인만 할 수 있습니다.");       
	    }
	   },
	   error : function(){
	    alert("로그인하셔야합니다.")
	   }
	  });
	 }
	 
	});
</script>
</body>
</html>

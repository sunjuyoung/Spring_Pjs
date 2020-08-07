<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>

.container{
	display:grid;
	grid-template-columns: 200px 700px 200px;
	padding: 10px;
	 
	 
}
div.replyGrid{
	padding-left: 70px;
}
.insert{
	height:300px;
	
}
.head1{
 	grid-column: 1 / span 3;
 	text-align: center;
 	font-size: 20px;
 	margin-top:5rem;
 	margin-bottom:2rem;
}
.head2{
 	grid-column: 1 / span 3;
 	padding-left: 52rem;
}
.head2 button{
	border-radius: 4px;
	cursor: pointer;
	transition-duration: 0.4s;
}
 button:active {
  background-color: gray;
  transform: translateY(4px);
}


input{
	border-radius: 4px;
}
textarea{
	border-radius: 4px;

}
div.menu{
	padding-right:10px;
}
div.reply{ 
	height:170px;
	width:600px;
	align-items: center;
	align-content: center;
	padding-left: 10px;
	border: 2px outset gray;
}
div.replyContent{
	margin:10px;
}
div.replyList{
	margin-bottom: 10px;
}
div.contentTop{
	display: flex;
}
div.topId{
	flex: 1;
}

</style>
<!-- reply module -->
<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
$(document).ready(function(){

	var bnoValue=${board.bno};
	var replyerValue = $("#writer").val();
	var form = $("#actionForm");
	var replyUrl = $(".replyContent");
	   $(".page-link").on("click",function(e){
		   e.preventDefault();
		   var pageNum = $(this).attr("href");
		   
		   form.find("input[name='pageNum']").val(pageNum);
		   form.submit();
	   });
	   
	  
	   
	   
	   /* reply list */
function showList(){
	replyService.list({bno:bnoValue,page:1},function(result){
		var len = result.length || 0;
		var str ="";
		console.log(result);
		if(result ==null || len == 0){
			replyUrl.html("");
			return;
		}
		 for(var i=0; i<len; i++){
			 
		str+='	<div class="replyList">            '
		str+='		<div class="contentTop">       ' 
		str+='			<div class="topId">'
		str+=         result[i].replyer
		str+='		</div>                         '
		str+='			<div class="topId">'
		str+='<button type="button" class="replyRemoveBtn" id="'+result[i].rno+'">x</button>'
		str+='		</div>    '
		str+='		</div>                         '
		str+='		<div class="contentMiddle">    '
		str+=         result[i].reply
		str+='		</div>                         '
		str+='		<div class="contentBottom">    '
		str+=			replyService.displayTime(result[i].upateDate);              
		str+='		</div>                         '
		str+='<input type="hidden" id="rno" name="rno" value="'+result[i].rno+'"/>'
		str+='	</div>                             '
		
		 }                                      
		replyUrl.html(str);                                 
		
	})
	   }
	
	/* 리플 등록 */
	$(".replyInsertBtn").on("click",function(){
		var replyValue = $("#replyContent").val();
 		replyService.add({bno:bnoValue ,reply:replyValue ,replyer:replyerValue},function(result){
			alert(result);
			$("#replyContent").val("");
			showList();
		}); 
	})
	
	/* 리플 삭제 */
	$('body').on("click",".replyRemoveBtn",function(e){ //document
		var rno = $(this).attr("id");
		 replyService.remove(rno,function(result){
			 console.log(result);
			alert(result);
			showList();
		}) 
	})
	
	
	showList();

});
</script>
</head>
<body>

<div class="container">
<div class="head1">MAIN</div>


<div class="head2"><button type="submit">글쓰기</button></div>
<div class="menu">
<nav class="navbar  bg-light navbar-light">

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Link 1</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 2</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 3</a>
    </li>
  </ul>

</nav>

</div>
	<div class="insert">
		<div class="col-lg-12">
			<div class="form-group">
				<label>제목</label>
				<input type="text" name="title" id="title" value="${board.title }" / readonly>
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea name="content" id="content"rows="7" cols="80" readonly>${board.content }</textarea>
			</div>
			<div class="form-group">
				<label>글쓴이</label>
				<input type="text" id="writer"name="writer" value="${board.writer }" readonly>
			</div>
		</div>
		
	</div>
	
	<div></div>
	<div></div>
	<div class="replyGrid">
		<div class="replyContent">
<!-- 			<div class="replyList">
				<div class="contentTop">
					<div class="topId">
					아이디
					</div>
					<div class="topRemoveBtn">
						<button type="button" class="replyRemoveBtn">x</button>
					</div>
					
				</div>
				<div class="contentMiddle">
					내용
				</div>
				<div class="contentBottom">
					내용
				</div>
			</div>  -->
		</div>
		
		
		<div class="reply">
			<p>${board.writer}</p>
			<textarea name="replyContent"rows="3" cols="77" id="replyContent"></textarea>
			<button class="replyInsertBtn" type="button">등록</button>
		</div>
	</div>
</div>


<input type="hidden" id="replyContent" name="replyContent" value=""/>


</body>
</html>
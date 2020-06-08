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
.insert{
	height:auto;
	
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
</style>


<script>
$(document).ready(function(){

	var form = $("#actionForm");
	   $(".page-link").on("click",function(e){
		   e.preventDefault();
		   var pageNum = $(this).attr("href");
		   
		   form.find("input[name='pageNum']").val(pageNum);
		   form.submit();
	   });

});
</script>
</head>
<body>

<div class="container">
<div class="head1">MAIN</div>


<div class="head2"><button type="submit">글쓰기</button></div>
<div></div>
	<div class="insert">
	<form role="form" action="/board/insert" method="post">
	<div class="col-lg-12">
	
	
	<div class="form-group">
		<label>제목</label>
		<input type="text" name="title" id="title" value="${board.title }" />
	</div>
	<div class="form-group">
		<label>내용</label>
		<textarea name="content" id="content"rows="7" cols="100">${board.content }</textarea>
	</div>
	<div class="form-group">
		<label>글쓴이</label>
		<input type="text" id="writer"name="writer" value="${board.writer }">
	</div>
	
	</div>
	<button type="submit">글쓰기</button> 
	</form>
	
	
	
	
	
	</div>
	
	<div></div>
	<div></div>
	<div>
	
	</div>
</div>




</body>
</html>
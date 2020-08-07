<%-- jstl-1.2.jar 파일 필요 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
.grid_Table{
	height:600px;
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

table.table{
/* margin-top:5rem; */

}

tr:nth-child(even){
	background: #f7f7f7;
}
div.menu{
	padding-right:10px;
}
</style>


<script>
$(document).ready(function(){

	var result = '<c:out value="${result}" />';
	
	
	var form = $("#actionForm");
	   $(".page-link").on("click",function(e){
		   e.preventDefault();
		  
		   var pageNum = $(this).attr("href");
		   
		   form.find("input[name='pageNum']").val(pageNum);
		  
		   form.submit();
		 
	   });

	   $(".insert").on("click",function(){
		  self.location = "/board/insert";
	   });
	   
});
</script>
</head>
<body>

<div class="container">
<div class="head1">MAIN</div>
<div class="head2"><button class="insert" type="button">글쓰기</button></div>
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
	<div class="grid_Table">
	
	<!-- board Table -->
	<table class="table">
		<thead>
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
			</tr>
		</thead>
			<tbody>
			<c:forEach var="board" items="${board }" >
				<tr>
					<td>${board.bno}</td>
					<td><a href="/board/get?bno=${board.bno }">${board.title}</a></td>
					<td>${board.writer}</td>
					<td><fmt:formatDate value="${board.updatedate}" type="time" /></td>
				</tr>
			</c:forEach>
			</tbody>
	</table>


	</div>
	<div></div>
	<div></div>
	<div>
		<!-- 페이지 -->
	<ul class="pagination">
	<c:if test="${page.prev }">
	  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
	  </c:if>
	  
	  <c:forEach var="page" begin="${page.startPage }" end="${page.endPage }">
	  	<li class="page-item"><a class="page-link" href="${page}">${page }</a></li>
	  </c:forEach>
	
	  
	  <c:if test="${page.next }">
	  <li class="page-item"><a class="page-link" href="#">Next</a></li>
	  </c:if>
	</ul>
	</div>
</div>


<form action="/board/main" method="get" id="actionForm">
	<input type="hidden" name="pageNum" value="${page.cri.pageNum }" />
	<input type="hidden" name="amount" value="${page.cri.amount }" />
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="col-md-12">
	<div class="col-md-6">
	<table class="table">
		<thead>
			<tr>
				<th>내용</th>
			</tr>
		</thead>
			<tbody>
			<c:forEach var="board" items="${board }" >
			<tr>
					<td>${board.bno}</td>
					<td>${board.content}</td>
					<td>${board.title}</td>
				</tr>
			
			</c:forEach>
				
			</tbody>
	</table>
${page.next }
	
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
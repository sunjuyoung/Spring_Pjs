<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}
</style>



<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {
		
		
		
		var movieApi = function(value){

			var bookData = { query : value
							,display : 5};
			
			
		$.ajax({
			url:'${pageContext.request.contextPath}/movie/movieMain.json',
			type:'post',
			
			data : bookData,

				
			error : function(request,status,error){
				console.log(request);
				console.log(status);
				console.log(error);
			},
			success:function(res){
			
				
				console.log(res.result);
				console.log(JSON.parse(res.result));
				var book = JSON.parse(res.result);
				console.log(book.items.length);
				
				
	
				for(var i=0; i<book.items.length; i++){
					$("#book").append("<span>"+book.items[i].title+"<span>");
					$("#book").append("<img src="+book.items[i].image+" />");
					$("#book").append("<a href="+book.items[i].link+">주소</a>")
					$("#book").append("<hr>");
				}
				
				
				
			}
		})
		
		}
		
		
		$("#searchBtn").on("click",function(e){
			e.preventDefault();
			$("#book").empty();
			
			var keyword = $("input[name='searchValue']").val();
			movieApi(keyword);
		
		})
		movieApi();
	});
</script>
</head>
<body>

	<div class="container">
		<div id="searchMovie">
			<form>
			<input type="text" placeholder="영화찾기" id="searchValue" name="searchValue">
			<button type="submit" id="searchBtn">찾기</button>
			</form>
		</div>
		
		<hr>
		<div id="book">
		
		
		</div>
		


	</div>


</body>
</html>
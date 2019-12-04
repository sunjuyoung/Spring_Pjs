<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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

		var clientId = '__nrAJSkyIyXZcu51oT7';
		var secretId = 'YUxjXONUas';
		
		$.ajax({
			url:'https://openapi.naver.com/v1/search/movie.json?display=10',
			type:'get',
			dataType: 'jsonp',
			processData: false,
			
			beforeSend : function(xhr){
				xhr.setRequestHeader('X-Naver-Client-Id',clientId);
				xhr.setRequestHeader('X-Naver-Client-Secret',secretId);
				xhr.setRequestHeader('Content-type','application/json');

			},
			error : function(res){
				console.log(res);
			},
			success:function(res){
				console.log(res);
			}
		})
	});
</script>
</head>
<body>

	<div class="container">
		


	</div>


</body>
</html>
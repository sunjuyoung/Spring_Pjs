<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>

div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

</style>

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
$(document).ready(function(){

	   // jQuery methods go here...

});
</script>
</head>
<body>


<!-- POST 방식으로 처리되기 때문에 CSRF 토큰값을 같이 지정합니다. -->
<div class="container">
<form action="/customLogout" method="post">
<h1>Logout</h1>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
<button>로그아웃</button>
<!-- 로그아웃시 내부적으로 자동으로 로그인 페이지를 호출하게 됩니다
   이부분은 스프링 시큐리티 기본 설정이므로 필요하다면 logout-success-url 속성 등을 이용해서 변경할 수 있습니다.
  -->

</form>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">


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

<!-- 모두가 볼수있는 페이지 에서
로그인 사용자라면 로그아웃이 보이고
로그인 상태가 아니라면 로그인표시가 보인다 -->
<div class="container">
<h2> all page</h2>
<sec:authorize access="isAnonymous()"> 
<a href="/customLogin">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<a href="/customLogout">로그아웃</a>
</sec:authorize>


<!-- 스프링 시큐리티 표현식
hasRole([role])
hasAuthority([]) 해당 권한이 있으면 true


hasAnyRole([])
hasAnyAuthority([]) 여러 권한들 중에서 하나라도 해당되는 권한이 있으면 true


principal 현재 사용자 정보

permitAll  모든사용자에게 허용

denyAll



 -->

<!-- <a href="/customLogout">Logout</a> -->
</div>

</body>
</html>
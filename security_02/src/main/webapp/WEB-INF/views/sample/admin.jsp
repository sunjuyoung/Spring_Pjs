<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

	var name = $("p#username").text();
	console.log(name);
	
	
});
</script>
</head>
<body>

<div class="container">
<h2> admin page</h2>


<!-- UserDetailsSerivce에서 반환된 객체 
     loadUserByUserName()에서 반환된 CustomUser객체가 됩니다
     즉 principal이 CustomUser를 의미하므로 principal.member는 CustomUser객체의 getMember()를 호출한다는 것
-->

<%-- <p> principal : <sec:authentication  property="principal" /></p> 
<p>MemberVO : <sec:authentication property="principal.member" /></p>
<p>사용자 이름 : <sec:authentication property="principal.member.userName" /></p>
<p id="username">사용자 아이디 : <sec:authentication property="principal.username" /></p>
<p>사용자 아이디 : <sec:authentication property="principal.member.userpw" /></p>
<p>사용자 권한 리스트 : <sec:authentication property="principal.member.authList" /></p>
<p>사용자 권한  : <sec:authentication property="principal.member.authList[0].auth" /></p>
<p> principal : <sec:authentication  property="principal.authorities" /></p> 

 --%>

<a href="${pageContext.request.contextPath}/customLogout">Logout</a>

</div>

</body>
</html>
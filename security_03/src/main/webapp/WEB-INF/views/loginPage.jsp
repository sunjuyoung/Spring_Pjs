<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title></title>

<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="/resources/css/loginPage.css">
<style>



</style>

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<script>
$(document).ready(function(){

	  var er = '<c:out value="${error}" />';
	  var logout = '<c:out value="${logout}" />';
	 
	  history.replaceState({},null,null);
	  if(er === 'Login Error'){
		  alert('회원정보를 확인하세요'+history.state);
	  }

	 if(logout === 'Logtout'){
		 alert('로그아웃 되었습니다.'+history.state);
	  }
	
});
</script>
</head>
<body>
<!-- csrf 사이트간 요청 위조 -->
 <div class="container">
        <div class="form">
            <h2>Login</h2>
            <form method="post" action="/login">  <!-- login으로 지정 -->
                <div class="inputBox">
                    <input type="text" name="username"
                    placeholder="Username">
                </div>
                <div class="inputBox">
                    <input type="password" name="password"
                    placeholder="password">
                </div>
                <div class="inputBox">
                    <input type="submit" value="Login"
                    >
                    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
                </div>
                <P>Forget Password? <a href="#">Click Here</a></P>
            </form>
        </div>
    </div>

</body>
</html>
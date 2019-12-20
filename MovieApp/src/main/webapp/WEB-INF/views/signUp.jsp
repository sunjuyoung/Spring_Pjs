<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>회원가입</title>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<script>
$(document).ready(function(){

	   // jQuery methods go here...

});
</script>
</head>
<body>

<div class="container">

					<div class="card fat">
						<div class="card-body">
						
							<form method="POST" class="my-login-validation" action="${pageContext.request.contextPath}/login"> <!-- 실제로 로그인의 처리 작업은 /login을 통해서 POST방식으로  -->
								<div class="form-group">
									<label for="name">ID</label>
									<input id="name" type="text" class="form-control" name="username"  value="admin"  >
								</div>


								<div class="form-group">
									<label for="password">Password</label>
									<input id="userpw" type="password" class="form-control" name="password"  value="admin" ><!--required data-eye  -->
								</div>
								
								<div class="form-group">
									<label for="name">Name</label>
									<input id="name" type="text" class="form-control" name="username"  value="admin"  >
								</div>
								
								
		


								<div class="form-group m-0">
									<input type="submit" class="btn btn-primary btn-block" value="등록">
										
								</div>
								<div class="mt-4 text-center">
									<a href="${pageContext.request.contextPath}/signUp">cancel</a>
									 <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
								</div>
							</form>
						</div>
					</div>


</div>

</body>
</html>
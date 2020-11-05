<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
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

<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- post방식으로 이용하는 경우 기본적으로 CSRF토큰이라는 것을 이용하게 됩니다. 
사이트간 위조 방지 목적으로 특정한 값의 토큰을 사용하는 방식
CSRF공격 자체가 사용자의 요청에 대한 출처를 검사하지 않아서 생기는 허접이기 때문에 사용자의 요청에대한
출처를 의미하는 referer 헤더를 체크하거나 일반적인 경우에 잘 사용되지 않고 REST방식에서 사용되는
PUT DELETE 같은 방식을 이용하는 등의 방식을 고려해 볼 수 있습니다.

CSRF토큰은 사용자가 임의로 변한는 토큰값을 서버에서 체크하는 방식

 -->



<script>
$(document).ready(function(){



});
</script>
</head>
<body class="my-login-page">

<h2><c:out value="${error}"/></h2>
<h2><c:out value="${logout}" /></h2>
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="/resources/img/logo.jpg" alt="bootstrap 4 login page">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Register</h4>
							<form method="POST" class="my-login-validation" action="/login"> <!-- 실제로 로그인의 처리 작업은 /login을 통해서 POST방식으로  -->
								<div class="form-group">
									<label for="name">Name</label>
									<input id="name" type="text" class="form-control" name="username"  value="admin"  >
									<div class="invalid-feedback">
										What's your name?
									</div>
								</div>


								<div class="form-group">
									<label for="password">Password</label>
									<input id="password" type="password" class="form-control" name="password"  value="admin" ><!--required data-eye  -->
									<div class="invalid-feedback">
										Password is required
									</div>
								</div>


								<div class="form-group m-0">
									<input type="submit" class="btn btn-primary btn-block">
										
								</div>
								<div class="mt-4 text-center">
									Already have an account? <a href="index.html">Login</a>
								</div>
								<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> <!-- 브라우저에서 확인하면 임의값이 지정되어있다 
																													세션 쿠키 삭제한후 다시 호출하면 값이 변경되어있다.-->
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; 2017 &mdash; Your Company 
					</div>
				</div>
			</div>
		</div>
	</section>


	<script src="/resources/js/my-login.js"></script>
</body>
</html>
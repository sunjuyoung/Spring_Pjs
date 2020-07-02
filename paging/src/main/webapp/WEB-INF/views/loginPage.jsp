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

	   // jQuery methods go here...

});
</script>
</head>
<body>

 <div class="container">
        <div class="form">
            <h2>Login</h2>
            <form method="post" action="/loginPage">
                <div class="inputBox">
                    <input type="text" name=""
                    placeholder="Username">
                </div>
                <div class="inputBox">
                    <input type="password" name=""
                    placeholder="Username">
                </div>
                <div class="inputBox">
                    <input type="submit" value="Login"
                    >
                </div>
                <P>Forget Password? <a href="#">Click Here</a></P>
            </form>
        </div>
    </div>

</body>
</html>
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

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {

		// jQuery methods go here...

	});
</script>
</head>
<body>

	<div class="container">
		<div>
			<button>입력</button>
			
		</div>

		<div>
			<table>
				<thead>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>내용</td>
						<td>날짜</td>
						<td></td>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<td><c:out value="${board.title }" /></td>
							<td><c:out value="${board.content }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }" /></td>
							<td>
								
								<button type="submit">삭제</button>
							</td>

						</tr>
					</c:forEach>

				</tbody>


			</table>
		</div>



	</div>


</body>
</html>
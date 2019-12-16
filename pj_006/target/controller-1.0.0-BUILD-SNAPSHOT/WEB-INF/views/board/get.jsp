<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		
			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">

						<div class="col-lg-12">
							<div class="p-5">
								<div class="text-center">
								
									<h1 class="h4 text-gray-900 mb-4">내용</h1>
								</div>

								<div class="form-group">
								<label>제목</label>
									<input type="text" class="form-control form-control-user"
										id="title" name="title"
										value="<c:out value="${board.title }" />" readonly="readonly">


								</div>
								<div class="form-group">
								<label>내용</label>
									<input type="text" style="height: 100px"
										class="form-control form-control-user" id="content"
										name="content" value="<c:out value="${board.content }" />"
										readonly="readonly">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
									<label>글쓴이</label>
										<input type="text" class="form-control form-control-user"
											id="writer" name="writer"
											value="<c:out value="${board.writer }" />"
											readonly="readonly">
									</div>

								</div>

								<input type="hidden" id="bno" name="bno"
									value='<c:out value="${board.bno }" />'>


								<button data-oper="modify"
									class="btn btn-primary btn-user btn-block ">
									수정</button>


								<button data-oper="remove"
									class="btn btn-danger btn-user btn-block ">삭제</button>

								<button data-oper="list" class="btn btn-info btn-user btn-block"> 
									목록</button> <!-- onclick="location.href='${pageContext.request.contextPath}/board/list'" -->


									<!-- 직접 버튼 링크를 처리하는 대신 나중에 다양한 상황을 처리하기 위해서 -->									
									<form id="openForm" method="get"action="${pageContext.request.contextPath}/board/list">
									<input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno }" />' >
									<input type="hidden" id=pageNum name="pageNum" value='<c:out value="${cri.pageNum }" />'>
									<input type="hidden" id=amount name="amount" value="${cri.amount }">
									
									</form>

								<hr>
								<a href="index.html" class="btn btn-google btn-user btn-block">
									<i class="fab fa-google fa-fw"></i> Register with Google
								</a> <a href="index.html"
									class="btn btn-facebook btn-user btn-block"> <i
									class="fab fa-facebook-f fa-fw"></i> Register with Facebook
								</a>


							</div>
						</div>
					</div>
				</div>
			</div>
		

	</div>

	<!-- Bootstrap core JavaScript-->

	<script type="text/javascript">
		$(document).ready(function() {

							var form = $("form");
							//버튼 클릭시
							$("button").on("click",	function(e) {

												e.preventDefault();

												var oper = $(this).data("oper");

												console.log(oper);
												//삭제 버튼
												if (oper === "remove") {
													form.attr("action","${pageContext.request.contextPath}/board/remove");
													form.attr("method", "POST");
												//수정 버튼
												} else if (oper === "modify") {

													form.attr("action","${pageContext.request.contextPath}/board/modify?bno="+${board.bno});
													form.attr("method", "get");
												//목록 버튼
												} else if (oper === "list"){
													form.find("#bno").remove();
												}

												form.submit();
											});

						});
	</script>



	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>

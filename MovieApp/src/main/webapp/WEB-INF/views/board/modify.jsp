<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Board modify Page!</title>

  <!-- Custom fonts for this template-->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">


<style>

.buttonCancel {
  background-color: white; 
  color: black; 
  border: 2px solid white;
}
</style>
</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block "><img src="${pageContext.request.contextPath}/resources/img/chaewon05.jpg" width="500" height="780"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Board modify Page!</h1>
              </div>
   <form role="form"  action = "/board/remove"method="post">
            
            
                            <div class="form-group row">
                  <div class="col-sm-12 ">
                  <b>bno</b>
                    <input type="text" class="form-control form-control-user" id="bno" name="bno" readonly="readonly" value='<c:out value="${board.bno }"></c:out>'>
                  </div>
                </div>
            
            
                <div class="form-group row">
                  <div class="col-sm-12 ">
                  <b>Title</b>
                    <input type="text" class="form-control form-control-user" id="title" name="title"  value='<c:out value="${board.title }"></c:out>'>
                  </div>
                <!--   <div class="col-sm-6">
                    <input type="text" class="form-control form-control-user" id="exampleLastName" placeholder="Last Name">
                  </div> -->
                </div>
                <div class="form-group">
                  <b>Content</b>
                <textarea class = "form-control form-control-user"rows="3" cols="12" name="content"  ><c:out value="${board.content }"></c:out></textarea>
                 <!--  <input type="email" class="form-control form-control-user" id="exampleInputEmail" placeholder="Email Address"> -->
                </div>
                <div class="form-group row">

                  <div class="col-sm-12">
                  <b>writer</b>
                    <input type="text" class="form-control form-control-user" id="writer" name="writer"  readonly ="readonly"value='<c:out value="${board.writer }"></c:out>'>
                  </div>
                </div>
                
                <div class="form-group row">
                <div class="col-sm-12">
                <label>Update Date</label>
                <input class="form-control" name="updateDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}" />'  readonly="readonly">
                </div>
                </div>
                
       <!--          <button type="submit" class="btn btn-primary btn-user btn-block">
                  Register Account
                </button> -->
                <hr>
                <!-- 어느 엘리멘트에나 data-로 시작하는 속성은 무엇이든 사용할 수 있습니다 -->
                <button type="submit" data-oper="modify"class="btn btn-google btn-user btn-block"> 
                  <i class="fab fa-google fa-fw"></i> 수정
                </button>
                <button type="submit" data-oper="remove" class="btn btn-facebook btn-user btn-block">
                  <i class="fab fa-facebook-f fa-fw"></i>삭제
                </button>
    
              <hr>
              <div class="text-center">
                <button type="submit"class="button buttonCancel" data-oper="list">cancel</button>
              </div>
              
              <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }" />'>
              <input type="hidden" name="amount" value='<c:out value="${cri.amount }" />'>
                </form> 
              <div class="text-center">
                <a class="small" href="login.html">Already have an account? Login!</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
<!--   <script src="/resources/vendor/jquery/jquery.min.js"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/resources/js/sb-admin-2.min.js"></script>
<script>

$(document).ready(function(){
	

	
	var formObj=$("form");
	
	$("button").on("click",function(e){
	e.preventDefault();
	
	var operation=$(this).data("oper");
	
	console.log(operation);
	
	if(operation === "modify"){
		formObj.attr("action","/board/modify");
	}else if(operation === "remove"){
		formObj.attr("action","/board/remove");
	}else{
		formObj.attr("action","/board/list").attr("method","get");
		
		//수정/삭제를 취소하고 목록페이지 이동
		//목록페이지는 pagenum과 amount 만을 사용하므로 다른 내용들은 삭제
		//form태그에서 필요한 부분만 잠시 복사해서 보관
		var pageNumTag = $("input[name ='pageNum']").clone();
		var amountTag = $("input[name = 'amount']").clone();
		
		formObj.empty();
		//모든내용 삭제후 필요한태그 추가
		formObj.append(pageNumTag);
		formObj.append(amountTag);
		
	}
	

	
	formObj.submit();
	
	});
	
	
	
	
	
	
});


</script>


</body>

</html>

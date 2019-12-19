<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Register</title>

  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/projects/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/projects/css/sb-admin-2.min.css" rel="stylesheet">

<style>
div#file{
margin : 10px;

}
#preview{
width : 100px;
height : 100px;
}

div.p-5{
padding : 
}

</style>

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
                <h1 class="h4 text-gray-900 mb-4">글 작성</h1>
              </div>
              <form class="form" action="${pageContext.request.contextPath}/board/register" method="post">
                <div class="form-group row">
                  <div class="col-sm-12 ">
                  <label>제목</label>
                    <input type="text" class="form-control form-control-user" id="title" name="title" placeholder="title">
                  </div>
 
                </div>
                <div class="form-group">
                 <label>내용</label>
                <textarea class = "form-control form-control-user"rows="3" cols="12" name="content" placeholder="content"></textarea>
                </div>
                <div class="form-group row">

                  <div class="col-sm-12">
                   <label>작성자</label>
                    <input type="text" class="form-control form-control-user" id="writer" name="writer">
                  </div>
                 <div class="form-group row">
				<div class="col-lg-7" id="file">
                    <input type="file"  id="uploadAjax" name="uploadAjax"  multiple />
                    </div>
                    	<div class="col-lg-4 uploadResult">
                    	<img src="${pageContext.request.contextPath}/resources/img/izone.jpg"  id="preview"/>
                    	</div>
               
                </div>
               </div>
                <button type="submit" class="btn btn-primary btn-user btn-block">
                 등록
                </button>

                <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
              </form>
              <hr>
              <div class="text-center">
                  <a class="small" href="${pageContext.request.contextPath}/board/mainList.do">cancel</a>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/projects/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/projects/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${pageContext.request.contextPath}/resources/projects/js/sb-admin-2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	//파일 종류, 크기
	var imageXp = new RegExp("(.*?)\.(jpg|png)$");
	var imageMax = 10240000;
	
	var name = '<sec:authentication property="principal.username" />';
	
	$("input[name='writer']").val(name);
	$("input[name='writer']").attr("readonly","readonly");
	
	
	var file = document.getElementById('uploadAjax');
	file.onchange = function(e) {
	var img = e.target.files;
	
	if(!checkImage(img[0].name,img[0].size)){
		$("input[name='uploadAjax']").val('');
		$("#preview").attr("src","${pageContext.request.contextPath}/resources/img/izone.jpg");
		return false;
		
	}else{
		var fileReader = new FileReader();
		fileReader.readAsDataURL(e.target.files[0]);
		fileReader.onload = function(e) {
		$("#preview").attr("src",e.target.result);
		}
	}
	 

	}
	
	function checkImage(fileName,filesize){
		
		if(filesize >= imageMax){
			alert("파일 크기가 초과 (10Mb)");
			return false;
		}
		if(!imageXp.test(fileName)){
			alert("해당 종류의 파일은 업로드할수 없습니다 (이미지파일전용)");
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	//파일 확장자나 크기 사전 처리
	var regex = new RegExp("(.*?)\.(exe|sh)$");
	var maxSize = 10240000;
	
	function checkExtension(fileName,fileSize){
		if(fileSize >= maxSize){
			alert("파일 크기 초과");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		
		return true;
	}
	
	//목록 출력
	var uploadResult = $(".uploadResult ul");
	function showUploadResult(arr){
		var str = "";
		$(arr).each(function(i,obj){
			if(!obj.image){
				str+="<li><img src='${pageContext.request.contextPath}/resources/attach-icon.jpg'>"+obj.fileName+"</li>";
			}else{
				
				//브라우저에서 GET방식으로 첨부파일의 이름을 사용할 때 파일이름에 공백,한글 등이 문제가 될 수 있다.
				//encodeURIComponent사용
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
				console.log(fileCallPath);
				str+="<li><img src='${pageContext.request.contextPath}/sample/display?fileName="+fileCallPath+"'>"+obj.fileName+"</li>";
			}
			
		})
		uploadResult.append(str);
	}
	
	
	
	//input type file 은 다른 DOM 다르게 readonly라 안쪽의 내용을 수정할 수 없다.
	//clone을 활용하여 업로드 후 초기화
	var cloneObj = $(".uploadDiv").clone();

	$("#uploadBtn").on("click",function(e){
		//가상의 form태그 생성
		//Ajax를 이용한 파일업로드는 formdata를 이용해서 필요한 파라미터를 담아서 전송
		var formData = new FormData();
		var inputFile = $("input[name='uploadAjax']");
		var files = inputFile[0].files;
		
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("uploadFile",files[i]);
		}

		$.ajax({
			url:'${pageContext.request.contextPath}/sample/uploadFormAction',
			processData : false,
			contentType : false,
			data : formData,
			type:'POST',
			dataType:'json',
			success:function(res){

				console.log(res);
				
				showUploadResult(res);
				
				$(".uploadDiv").html(cloneObj.html());
			}
			
		})
		
	})
	
	
	
	
});


</script>

</body>

</html>

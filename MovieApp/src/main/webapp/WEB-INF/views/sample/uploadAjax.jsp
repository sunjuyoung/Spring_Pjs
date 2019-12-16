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

.uploadResult {
width:100%;
}

.uploadResult ul li img {
width:60px;
height:60px;
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
</head>
<body>
	<c:set var="path"  value="${pageContext.request.contextPath }" />
	<div class="container">
		<h2>File Upload</h2>
		
		<form action="${path}/sample/uploadFormAction" method="post" enctype="multipart/form-data">
		
		
		<input type="file" name="uploadFile" multiple>  <!-- multiple input태그로 한번에 여러파일 업로드 가능 -->
		<button>submit</button>
		</form>
		
		<hr>
		<h2>Ajax UPload</h2>
		
		<div class="uploadDiv">
			<input type="file" name="uploadAjax" multiple >
			<button id="uploadBtn">Upload</button>
		</div>


		<div class="uploadResult">
			<ul>
			
			</ul>
			
		</div>
	</div>


</body>
</html>
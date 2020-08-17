<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SIST_쌍용교육센터</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>

div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.uploadResult ul{

display:flex;
flex-flow : row;
justify-content:center;
align-items:center;
}

.uploadResult ul li{

padding : 10px;

{

.uploadResult ul li img{

width : 20px;
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
	
	//파일 업로드 상세처리 , 파일의 확장자나 크기의 사전처리 exe,sh,zip
	
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); 
	
	var maxSize = 5242880; //5MB
	
	function checkExtension(filName, fileSize){
		
		if (fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(filName)){
			alert("해당 종료의 파일은 업로드할 수 없습니다.");
			return false;
		}
		
		return true;
		
	}
	
	
	

	
	
	
	
	
	var cloneObj = $(".uploadDiv").clone();
	
	//ajax 통한 파일 업로드
	$("#uploadBtn").on("click",function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files[0].name);
		console.log(files[0].size);
		
		for(var i=0; i<files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				console.log("false~~~");
				return false;
			} 
			
			formData.append("multipartFile",files[i]);
		}
		
		console.log("AJAJX@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		$.ajax({
			url: '/uploadAjaxAction',
			contentType : false,
			processData : false,
			data : formData,
			type:'POST',
			dataType: 'json',
			success : function(result){
				
				showUploadFile(result); 
				
				$(".uploadDiv").html(cloneObj.html()); //첨부파일을 업로드하기 전에 아무내용없는 input객체가 포함된 div 복사 해서 
				                                       //업로드한뒤에 div내에 다시 추가해서 첨부파일 부분을 초기화
				                             
				                                       
			}
			
			
		});
		
	});
	
	
	//
	var uploadResult = $(".uploadResult ul");
	//목록을 보여주는 함수
	function showUploadFile(uploadResultArr){
		
		var str="";
		$(uploadResultArr).each(function(i,obj){
			
			var index = i+1;
			
			if(!obj.image){
				str += "<li><img src='/resources/img/attach.png'>"+ obj.fileName + "</li>"; //이미지 파일이 아닐 경우 단순 파일 이미지 보여준다
				
			}else{
			
		//	str += "<li>"+ index +"."+obj.fileName + "</li>";
		
		//파일의 경로 + s_ + uuid 가 붙은 이름
		var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_"+obj.uuid+"_"+obj.fileName);
		
		str+= "<li><img src='/display?fileName="+fileCallPath+"'></li>";
		
			}
			
		});
		
		uploadResult.html(str);
		
	}
	

	
	

});
</script>
</head>
<body>

<div class="container">

<!--브라우저에서 섬네일 처리
	업로드 후에 업로드 부분을 초기화
	결과 데이터를 이용해서 화면에 섬네일이나 파일 이미지를 보여주는 작업

  -->

<h1>Upload With Ajax</h1>

<div class="uploadDiv">

<input type="file" name="uploadFile" multiple> 
</div>
<button id="uploadBtn">전송</button>

<div class = "uploadResult">
<ul>

</ul>

</div>

</div>

</body>
</html>
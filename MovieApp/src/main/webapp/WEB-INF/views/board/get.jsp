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

<title>Board Read Page!</title>

<!-- Custom fonts for this template-->
<link href="${pageContext.request.contextPath}/resources/projects/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/resources/projects/css/sb-admin-2.min.css" rel="stylesheet">
<style>
small{
float : right;

}
button#addReplyBtn{
float : right;


}




</style>
</head>

<body class="bg-gradient-primary">

	<div class="container">
		<input type="hidden" id="fileName" name="fileName" value="${board.fileName }"/>
		<input type="hidden" id="uuid" name="uuid" value="${board.uuid }"/>
		<input type="hidden" id="uploadPath" name="uploadPath" value="${board.uploadPath }"/>
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5">
				<img src="C://" style="width:100%; height:100%; " >
						
					</div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h6 class="h6 text-gray-800 mb-2">Board Read Page!</h6>
							</div>
						 <form class="form" action="${pageContext.request.contextPath}/board/remove" method="post"> 
						 
						 <input type="hidden" value='<c:out value="${board.bno}" />'  id="bno" name="bno"/>
							<div class="form-group row">
								<div class="col-sm-12 ">
									<b>Title</b> <input type="text"
										class="form-control form-control-user" id="title" name="title"
										readonly="readonly"
										value='<c:out value="${board.title }"></c:out>'>
								</div>
								<!--   <div class="col-sm-6">
                    <input type="text" class="form-control form-control-user" id="exampleLastName" placeholder="Last Name">
                  </div> -->
							</div>
							<div class="form-group">
								<b>Content</b>
								<textarea class="form-control form-control-user" rows="3"
									cols="12" name="content" readonly="readonly"><c:out
										value="${board.content }"></c:out></textarea>
							</div>
							<div class="form-group row">

								<div class="col-sm-12">
									<b>Writer</b> <input type="text"
										class="form-control form-control-user" id="writer"
										name="writer" readonly="readonly"
										value='<c:out value="${board.writer }"></c:out>'>
								</div>
							</div>
							<!--          <button type="submit" class="btn btn-primary btn-user btn-block">
                  Register Account
                </button> -->
							<hr>
							
							<div class="form-group row">
							<div class="col-sm-6">
							<a
								href="${pageContext.request.contextPath}/board/modify?bno=${board.bno}&pageNum=${vo.pageNum}&amount=${vo.amount}"
								class="btn btn-info btn-user btn-block">  수정
							</a>
							</div>

							
							<div class="col-sm-6">	
				 <button type="submit" data-oper="remove" class="btn btn-danger btn-user btn-block">
                  삭제
                </button>
                </div>
                </div>
                           
              <input type="hidden" name="pageNum" value='<c:out value="${vo.pageNum }" />'>
              <input type="hidden" name="amount" value='<c:out value="${vo.amount }" />'>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />		
			 </form> 
			 
						 <!-- -------------------  reply-----------------------  -->
			 <div class='row'>
			  <div class="col-lg-12">
			
			    <!-- /.panel -->
			    <div class="panel panel-default">
			     <div class="panel-heading">
			        <i class="fa fa-comments fa-fw"></i> Reply
			    
			        <button id="addReplyBtn" class="btn btn-primary btn-sm pull-right" data-oper="new"> New </button>
			      </div>
			      <!-- /.panel-heading -->
			      <div class="panel-body">  
			      <hr>      
			      
			        <ul class="chat">
			
			        </ul>
			        <!-- ./ end ul -->
			      </div>
			      <!-- /.panel .chat-panel -->
			      <div class = "panel-footer">  <!-- 댓글 페이징 -->
			      
			      </div>
			      
					</div>
			  </div>
			  <!-- ./ end row -->
			</div>
							
							
							<div class="text-center">
								<a class="small"
									href="${pageContext.request.contextPath}/board/mainList.do?pageNum=${vo.pageNum}&amount=${vo.amount}">메인화면</a>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<!--댓글 모달  -->
  <div class="modal fade" id="replyModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">

        </div>
        <div class="modal-body">
        <div class="col-sm-5">
        <label>작성자</label>
          <input class="form-control form-control-user"  type="text" id="replyer" name="replyer">
          </div>
           <div class="col-sm-12">
             <label>내용</label>
          <input class="form-control form-control-user"  type="text" id="reply" name="reply">
          </div>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-oper="replyUpdate">수정</button>
          <button type="button" class="btn btn-default" data-oper="replyAdd">입력</button>
           <button type="button" class="btn btn-default" data-oper="close">닫기</button>
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
	
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/projects/js/reply.js"></script>
	
	<script>
	$(document).ready(function(){
		
		//스프링시큐리티 토큰 처리
		var csrfHeader = "${_csrf.headerName}";
		var csrfToken =  "${_csrf.token}";
		//파일정보		
		var fileName = $("input[name='fileName']").val();
		var uuid = $("input[name='uuid']").val();
		var uploadPath = $("input[name='uploadPath']").val();
		
		var bnoValue = '<c:out value="${board.bno}"/>';
		var replyUL = $(".chat");
		//var fileCallPath = encodeURIComponent(uploadPath+"/s_"+uuid+"_"+fileName);
		var fileCallPath = uploadPath+"\\"+uuid+"_"+fileName;
		//이미지 정보
		var $imglocation = $(".getImg");
		var test1 ="";
		
		
		if(fileName != null){
			showImage();
		}else{
		//	test1+="<img src='${pageContext.request.contextPath}/resources/img/izone.jpg"'>";
		//	$imglocation.append(test1);
			
		}
		
		function showImage(){
			test1+="<img src='${pageContext.request.contextPath}/board/display?fileName='"+fileCallPath+">";
			
			console.log(test1);
			$imglocation.append(test1);
				
				
			
			
			
		}
		    //showList(1);
		    
		function showList(page){  //댓글 리스트 ajax처리
			
			  console.log("show list " + page);
		    
		    replyService.getList({bno:bnoValue,page: page|| 1 }, function( replyCnt,list) {
		    	console.log("replyCnt>>>>>>>>>>"+replyCnt );

		    	
		     var str="";
		     
		     if(list == null || list.length == 0){
		       return;
		     }
		     

		     
		     for (var i = 0, len = list.length || 0; i < len; i++) {
		       str +="<li class='left clearfix' data-rno='"+list[i].rno+"'  data-replyer='"+list[i].replyer+"'>";
		       str +="  <div><div class='header'><strong class='primary-font' style='font-size:80%;'>["
		    	   +list[i].rno+"] "+list[i].replyer+"</strong>"; 
		    	str +=" <a href='#'><img src='${pageContext.request.contextPath}/resources/projects/img/icon_delete_x.png'  width='13' height='14' title='replyD' alt='"+list[i].rno+"'></a> ";	  
		    	str +=" <a href='#'><img src='${pageContext.request.contextPath}/resources/projects/img/icon_Modify_pen.png'  width='13' height='14' title='replyM'  alt='"+list[i].rno+"'></a> ";	
		       str +="    <small class='pull-right text-muted'>"
		           +replyService.displayTime(list[i].replyDate)+"</small></div>";
		       str +="    <p style='font-size:80%;'>"+list[i].reply+"</p></div></li>";
		     }
		     
		     replyUL.html(str);
		     showReplyPage(replyCnt);
		     

		 
		   });//end function
		     
		 }//end showList
		
		 
		 
		 var pageNum =1;
		 var replyPageFooter = $(".panel-footer");
		 function showReplyPage(replyCnt){
			 
			 var endNum = Math.ceil(pageNum / 10.0) * 10;
			 var startNum = endNum - 9;
			 
			 var prev = startNum != 1;
			 var next = false;
			 
			 if(endNum * 10 >= replyCnt){
				 endNum = Math.ceil(replyCnt/10.0);
			 }
			 
			 if(endNum * 10 < replyCnt){
				 next =true;
			 }
			 
			 var str = "<ul class='pagination pull-right'>";
			 if(prev){
				 str+= "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Prev</a></li>";
			 }
			 
			 for(var i =startNum; i<=endNum; i++){
				 var active = pageNum== i? "active":"";
				 
				 str += "<li class='page-item  "+active+"'> <a class='page-link' href='"+i+"'> "+i+" </a></li> ";
				 
				 
			 }
			 if(next){
				 str+= "<li class='page-itme'><a class='page-link' href='"+ (endNum+1)+"'>Next</a></li>";
			 }
			 
			 str+= "</ul></div>";
			 
			 replyPageFooter.html(str);
			 
		 }
		 
		 
		 replyPageFooter.on("click","li a",function(e){
			e.preventDefault();
			
			var targetPageNum = $(this).attr("href");
			
			pageNum = targetPageNum;
			
			showList(pageNum);
			 
			 
		 });
		 
		 
		 
		 
		 
		 

		 //수정페이지 사진 사이즈 유동적 설정
/* 		 $(".col-lg-5").each(function (e){
			 
			 e.preventDefault();
			 
			 var ww= $(".col-lg-5").width();
			 var hh= $("div.col-lg-7").height();
			 console.log(hh);

			 $(".col-lg-5 img").css("width",ww +"px");
			 $(".col-lg-5 img").css("height" , hh-100 +"px");
		 
		 });
		 */

		var formObj=$("form");
		var modal =  $("#replyModal");
		
		$("button").on("click",function(e){
			
		e.preventDefault();
		var operation=$(this).data("oper");

		
		if(operation === "remove"){       //삭제 버튼 처리
			formObj.attr("action","${pageContext.request.contextPath}/board/remove")
			formObj.submit();
		}else if(operation === "new"){    //댓글 입력 모달창 처리
			 $("#replyModal").modal();
			
		}else if(operation === "replyAdd"){  //댓글 입력 ajax처리
			
			var modalInputReply = $("#replyModal").find("input[name='reply']");
			var modalInputReplyer = $("#replyModal").find("input[name='replyer']");
			 
			
			var reply ={reply:modalInputReply.val(), replyer:modalInputReplyer.val(),bno:bnoValue};
			
			replyService.add(reply,function(result){
				
				alert(result);
				
				 $("#replyModal").find("input").val("");
				 $("#replyModal").modal("hide");
				 showList(1);
				
			});
			
		}else if(operation === "replyUpdate"){ //댓글 수정
			
		}else if(operation === 'close'){
			$("#replyModal").find("input").val("");
			$("#replyModal").find("input[name='replyer']").removeAttr("readonly");
			$("#replyModal").modal("hide");
			 showList(1);
		}

		
		});
		

		
		$(".chat").on("click","img",function(){     //댓글 수정 삭제
			
			var title = $(this).attr("title");
			
			var rno = $(this).attr("alt");
			
			
			
			var replyer = $(".chat li").data("replyer");
			
		
				
				
		
			
			console.log(rno);
			
			
			var modal = $(".modal");
			
			if(title == 'replyD'){    //댓글 삭제
				
				if(confirm("이 코맨트를 삭제하시겠습니까?")){
					replyService.remove(rno,function(result){
						alert(result);
						showList(1);
						
					});
					
					
				}
				
			}else{  //댓글 수정
				console.log("------------------------"+rno);
				replyService.get(rno,function(reply){
			
				modal.find("input[name='replyer']").val(reply.replyer);
				modal.find("input[name='reply']").val(reply.reply);
				modal.find("input[name='replyer']").attr("readonly","readonly");
				modal.modal("show");
			
				});
				
		
			}
			
		});
		
		
		
		
	});
	
	
	
	</script>


	<script>
	console.log("========================");
	console.log("================TEST JS========");
	
	//댓글 목록 처리
	
	
	
	
	
	//입력
	/* replyService.add({reply:"JS TEST",replyer:"채원이",bno:bnoValue},function(result){
		alert("RESULT: "+ result);
	});
	 */
	 
	 
	 
	 //출력
/* 	replyService.ajaxList({bno:bnoValue,page:1},function(list){
		
		
		
		for(var i=0, len=list.length||0; i<len; i++){
			console.log(list[i]);
		} */
		
		

		//삭제
/* 		replyService.remove(42,function(count){
			console.log(count);
			
			if(count === "success"){
				alert("REMOVED")
			}
		},
		function(err){
			alert("ERROR");
			
			
		}); */
		
		//수정
/* 		replyService.update({reply:"아아아이이즈즈",rno:43},function(result){
			alert("수정완료");
		
		
		});
		 */

	
	</script>




</body>

</html>

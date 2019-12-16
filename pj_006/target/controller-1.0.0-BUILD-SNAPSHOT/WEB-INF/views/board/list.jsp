<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../board/includes/listHeader.jsp" %>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">게시판</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
							  <button id="regBtn"type="button" class="btn btn-primary ">New</button></h6> 
								
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>글쓴이</th>
											<th>날짜</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="board" items="${list}">
											<tr>
												<td><a class="move" href='<c:out value="${board.bno }" />'><c:out value="${board.bno}" /></a></td>
												<td><a class="move" href='<c:out value="${board.bno }" />'><c:out value="${board.title }" /> </a></td>
												<td><c:out value="${board.writer }" /></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }" /></td>
										
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
						
						                 <!--      페이징     -->
						                 
					<div class="pull-right">
					
					<ul class='pagination'>
						<c:if test="${page.prev }"> 
						<li class="paginate_button previous"><a href='${page.startPage -1 }'> prev</a></li>
						</c:if>
						
						<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }">
						
						<li class="paginate_button ${page.cri.pageNum == num? 'active':'' }"><a href="${num }"> ${num }</a></li>
						
						</c:forEach>
						
						
						<c:if test="${page.next }">
						<li class="paginate_button next"><a href="${page.endPage +1 }"> next</a></li>
						</c:if>
					</ul>
					
					
					
					
					</div>	
						<form id="actionForm" action ="${pageContext.request.contextPath}/board/list" method="get">
						<input type="hidden" id=pageNum name="pageNum" value="${page.cri.pageNum }">
						<input type="hidden" id=amount name="amount" value="${page.cri.amount }">
						</form>
						
						
						
					</div>




				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2019</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!--    Modal       -->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- result Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         
        </div>
        <div class="modal-body">
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
	
	
	
	
	
	
	
	
	

	<!-- Bootstrap core JavaScript-->
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		var result = '<c:out value="${result}" />';
		console.log(result);
		
		checkModal(result);
		
		//뒤로가기나 앞으로 가기 하면 서버를 다시 호출하는게 아니라 과거에 자신이 가진 모든 데이터를 활용한다
		//window의 history객체를 이용해서 표시를 해두는 방식
		//history객체를 이용하여 모달창 표시하지 않음
		history.replaceState({},null,null);
		
		//결과 모달창
		function checkModal(result){
			
			//history객체를 이용하여 모달창 표시하지 않음
			if(result === '' || history.state){
				return;
			}
			
			//수정,삭제,등록 완료시  success모달창 띄운다
			if(result != ''){
				$("div.modal-body").html( result + " 완료");
				
				
			}
			$("#myModal").modal("show");
			
		}
		
		
		//게시판 등록 버튼
		$("#regBtn").on("click",function(){
			
			self.location = "${pageContext.request.contextPath}/board/register"
			
		});
		
		
		
		//페이징 버튼
		var form = $("#actionForm");
		
		$(".paginate_button a").on("click",function(e){
			
			e.preventDefault();
			
			form.find("input[name='pageNum']").val($(this).attr("href"));
			form.submit();
			
			
		});
		
		//글 클릭 조회페이지 이동
		$("a.move").on("click",function(e){
			
			e.preventDefault();
			
			form.append("<input type='hidden' name='bno' id='bno' value='"+$(this).attr("href")+"'>");
			//조회 후 목록페이지로 돌아왔을시 페이지번호를 유지하기위해 페이징 정보를 넘긴다
			form.attr("action","${pageContext.request.contextPath}/board/get");
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

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/demo/datatables-demo.js"></script>

</body>

</html>

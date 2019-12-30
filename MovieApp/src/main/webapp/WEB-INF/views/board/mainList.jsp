<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="../board/includes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800"></h1>
            <a href="${pageContext.request.contextPath}/board/mainList.do" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Excel Download</a>
          </div>

          <!-- Content Row -->

          <div class="row">
		
						<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					

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
												
												<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate }" /></td>
										
											</tr>

										</c:forEach>

									</tbody>
								</table>
								
							</div>
							
							<div class="pull-right">
								<div id="page-wrapper">
									<ul class="pagination">
									
									<c:if test="${page.prev }">
										<li class="paginate_button previous"><a href="${page.pageNum -1 }">Previous</a></li>
									</c:if>
									
									<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }" >
										 <li class="paginate_button ${page.pageNum == num? 'active':''}"><a href="${num }">${num }</a></li>
									</c:forEach>
									
									<c:if test="${page.next }">
										<li class="paginate_button next"><a href="${page.pageNum +1 }">next</a></li>
									</c:if>
									</ul>
							
								</div>
							</div>
							
						</div>
						
						   
					</div>

				</div>
				<form id="actionForm" action="${pageContext.request.contextPath}/board/mainList.do" method="get">
					<input type="hidden" name="pageNum" value="${page.pageNum }">
					<input type="hidden" name="amount" value="${page.amount }">
				</form>
				

          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Content Column -->
            <div class="col-lg-6 mb-4">

              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">인기 글</h6>
                </div>
                <div class="card-body">
                  <h4 class="small font-weight-bold">Server Migration <span class="float-right">20%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-danger" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                  </div>
                  <h4 class="small font-weight-bold">Sales Tracking <span class="float-right">40%</span></h4>
                  <div class="progress mb-4">
                    <div class="progress-bar bg-warning" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                  </div>

                </div>
              </div>

               <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">인기 댓글</h6>
               </div>
              <!-- Color System -->
              <div class="row">
              
                <div class="col-lg-6 mb-4">
                  <div class="card bg-primary text-white shadow">
                    <div class="card-body">
                      Primary
                      <div class="text-white-50 small">#4e73df</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-success text-white shadow">
                    <div class="card-body">
                      Success
                      <div class="text-white-50 small">#1cc88a</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-info text-white shadow">
                    <div class="card-body">
                      Info
                      <div class="text-white-50 small">#36b9cc</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-warning text-white shadow">
                    <div class="card-body">
                      Warning
                      <div class="text-white-50 small">#f6c23e</div>
                    </div>
                  </div>
                </div>

              </div>

            </div>
            
            <!-- 최근 사진 뷰어 -->
           <div class="col-lg-6 mb-4">
             <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">최신 이미지</h6>
              </div>

				<div id="myCarousel" class="carousel slide" data-ride="carousel">
				    <!-- Indicators -->
				    <ol class="carousel-indicators">
				      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				      <li data-target="#myCarousel" data-slide-to="1"></li>
				      <li data-target="#myCarousel" data-slide-to="2"></li>
				    </ol>
				
				    <!-- Wrapper for slides -->
				    <div class="carousel-inner" role="listbox">
				      <div class="item active">
				      
				        <img src="${pageContext.request.contextPath}/resources/img/chicago.jpg" alt="New York" width="800" height="500">
				        <div class="carousel-caption">
				          <h3>New York</h3>
				          <p>The atmosphere in New York is lorem ipsum.</p>
				        </div>      
				      </div>
				
				      <div class="item">
				        <img src="${pageContext.request.contextPath}/resources/img/chicago.jpg" alt="Chicago" width="800" height="500">
				        <div class="carousel-caption">
				          <h3>Chicago</h3>
				          <p>Thank you, Chicago - A night we won't forget.</p>
				        </div>      
				      </div>
				    
				      <div class="item">
				        <img src="${pageContext.request.contextPath}/resources/img/chicago.jpg" alt="Los Angeles" width="800" height="500">
				        <div class="carousel-caption">
				          <h3>LA</h3>
				          <p>Even though the traffic was a mess, we had the best time playing at Venice Beach!</p>
				        </div>      
				      </div>
				    </div>
				
				    <!-- Left and right controls -->
				    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				      <span class="sr-only">Previous</span>
				    </a>
				    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				      <span class="sr-only">Next</span>
				    </a>
				</div>
           
            </div>
            <!--사진뷰어 끝  -->

          </div>

        </div>
        <!-- /.container-fluid -->

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

    <!-- End of Content Wrapper -->

  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>


  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         
        </div>
        <div class="modal-body">
          <p>${result }</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>






  <!-- Bootstrap core JavaScript-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   
<%--  <script src="${pageContext.request.contextPath}/resources/projects/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>  --%>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/projects/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${pageContext.request.contextPath}/resources/projects/js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="${pageContext.request.contextPath}/resources/projects/vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="${pageContext.request.contextPath}/resources/projects/js/demo/chart-area-demo.js"></script>
  <script src="${pageContext.request.contextPath}/resources/projects/js/demo/chart-pie-demo.js"></script>
  



<!-- Latest compiled JavaScript -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
  
  

	<script type="text/javascript">
	$(document).ready(function(){
		var $actionForm = $("#actionForm");
		var result = '<c:out value="${reulst}" />';
		
		/* 뒤로가기 앞으로가기 서버를 다시 호춣하는게 아니라 과거에 데이터를 활용
			window history객체를 이용*/
		history.replaceState({},null,null);
		
		function checkModal(result){
			
			if(result === '' || history.state){
				return;
			}
			
			if(result != ''){
				$("#myModal").modal("show");
			}
			
		}
		
		//페이지 버튼
		$(".paginate_button a").on("click",function(e){
			e.preventDefault();
			var num = $(this).attr("href");
			console.log(num);
			$("input[name='pageNum']").val(num);
			$actionForm.submit();
			
		});
		
		
		
		//글 조회
		$(".move").on("click",function(e){
			e.preventDefault();

			$actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			$actionForm.attr("action","${pageContext.request.contextPath}/board/get");
			$actionForm.submit();
			
		});
		
		
		//글 등록버튼
		$("#regBtn").on("click",function(e){
			e.preventDefault();
			$actionForm.attr("action","${pageContext.request.contextPath}/board/register");
			$actionForm.submit();
		});
		
		
		
		
		
		
		
		
		
	});
	
	</script>


</body>

</html>

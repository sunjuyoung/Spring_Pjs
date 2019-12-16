<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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




<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- kakaoMap -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=612e80d8d80169b92e1b4f4900929d96&libraries=services,clusterer,drawing"></script>

<script>
	$(document).ready(function() {
		
		var movieApi = function(value,pageNum){
			var bookData = { query : value
							,display : 100
							,page : pageNum};
			
		$.ajax({
			url:'${pageContext.request.contextPath}/movie/movieMain.json',
			type:'post',
			data : bookData,
			error : function(request,status,error){
				console.log(request);
				console.log(status);
				console.log(error);
			},
			success:function(res){
				console.log(JSON.parse(res.result));
				
				var book = JSON.parse(res.result);
				var p = JSON.parse(res.pageNum);
				console.log(book.total);
				console.log(p);
				
	
				for(var i=0; i<10; i++){
					$("#book").append("<span>"+book.items[i].title+"<span>");
					$("#book").append("<img src="+book.items[i].image+" />");
					$("#book").append("<a href="+book.items[i].link+">주소</a>")
					$("#book").append("<hr>");
				}
				
				for(var i=1; i<=book.total/10; i++){
	
					$(".pagination").append("<li><a  style='cursor:pointer' >"+i+"</a></li>")
				}
				
			}
		});
		
		}
			
		//영화찾기 버튼
		$("#searchBtn").on("click",function(e){
			e.preventDefault();
			$("#book").empty();
			$(".pagination").empty();
			
			var keyword = $("input[name='searchValue']").val();
			movieApi(keyword);
		
		})
		movieApi();
		
		
		$(document).on("click","a",function(){
			
			console.log($(this).text());
			
			var num = $(this).text();
			var numPage = num * 10;
			var keyword = $("input[name='searchValue']").val();
			$("#book").empty();
			$(".pagination").empty();
			movieApi(keyword,numPage);
			
});
		
		
		
		//kakao map
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.57220234093424, 127.18160687238502), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
			};
		  var markerPosition  = new kakao.maps.LatLng(37.57220234093424, 127.18160687238502);

		
		
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT)
		
		
		var geocoder = new kakao.maps.services.Geocoder();

		var callback = function(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		        console.log(result[0].x);
		        console.log(result[0].y);
		        
		        options.center = new kakao.maps.LatLng(result[0].y,result[0].x);
		        var markerPosition  =new kakao.maps.LatLng(result[0].y,result[0].x);
		    }
		};

		geocoder.addressSearch('미사강변북로25', callback);
		
		
		$("#mapBtn").on("click",function(){
			
			var value = $("input[name='mapValue']").val();
			console.log(value);
			
			geocoder.addressSearch(value, callback);
			
			var map = new kakao.maps.Map(container, options);
			
		});
		
		
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
		
		
		
	

	});
</script>
</head>
<body>

	<div class="container">
		<div id="searchMovie">
			<form>
			<input type="text" placeholder="영화찾기" id="searchValue" name="searchValue">
			<button type="submit" id="searchBtn">찾기</button>

			</form>
		</div>
		
		<hr>
<!-- 		<div id="book">
		</div> -->
		
			<div class="container-fluid text-center bg-grey">
			  <h2>Movie</h2>
			  <h4>What we have created</h4>
			  <div class="row text-center">
			    <div class="col-sm-4">
			      <div class="thumbnail">
			        <img src="paris.jpg" alt="Paris">
			        <p><strong>Paris</strong></p>
			        <p>Yes, we built Paris</p>
			      </div>
			    </div>
			    <div class="col-sm-4">
			      <div class="thumbnail">
			        <img src="newyork.jpg" alt="New York">
			        <p><strong>New York</strong></p>
			        <p>We built New York</p>
			      </div>
			    </div>
			    <div class="col-sm-4">
			      <div class="thumbnail">
			        <img src="sanfran.jpg" alt="San Francisco">
			        <p><strong>San Francisco</strong></p>
			        <p>Yes, San Fran is ours</p>
			      </div>
			    </div>
			</div>
		
		
		
		<div id="pageBtn">
			<ul class="pagination">
			
			</ul>
		</div>
		
		<hr>
		<input type="text" placeholder="위치 찾기" id="mapValue" name="mapValue">
			<button type="button" id="mapBtn">찾기</button>
		<div id="map" style="width:500px;height:400px;"></div>
		


	</div>


</body>
</html>
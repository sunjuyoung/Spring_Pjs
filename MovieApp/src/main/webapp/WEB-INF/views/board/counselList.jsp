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


 <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/jqGrid/css/ui.jqgrid.css" />
 
  <script src="${pageContext.request.contextPath}/resources/jqGrid/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/resources/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/resources/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>


<script>
	$(document).ready(function() {

	    var dataArray = [
	        {
	          "name": "Lorene Battle",
	          "phone": "(936) 574-3976"
	        },
	        {
	          "name": "Wendi Downs",
	          "phone": "(815) 510-3017"
	        }
	      ];
	   
	      $(document).ready(function() {
	        $("#list").jqGrid({
	          datatype: 'local',
	          styleUI: 'Foundation',
	          data: dataArray,
	          colModel: [
	            {name: 'name', label : 'Name'},
	            {name: 'phone', label : 'Phone Number'}
	          ],
	          caption : 'Users Grid',
	          height: 'auto',
	          rowNum: 5,
	          pager: '#pager'
	        });
	      });
	});
</script>
</head>
<body>

	<div class="container">
		  <table id="list"><tr><td></td></tr></table> 
  <div id="pager"></div> 

	</div>


</body>
</html>
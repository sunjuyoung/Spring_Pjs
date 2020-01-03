/**
 * 
 */



console.log("reply Module..");



var replyService = (function(){
	
	
	function getContextPath() {

		var hostIndex = location.href.indexOf( location.host ) + location.host.length;

		return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

	}
	
	
	//입력
	function insert(reply,callback,error){
		console.log("insert");
		var contextPath = getContextPath();
		
		$.ajax({
			type:'post',
			url : contextPath+'/reply/insert',
			data : JSON.stringify(reply),
			contentType : 'application/json; charset=utf-8',
			success : function(res,status,xhr){
				if(callback){
					callback(result);
				}
			},error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
			
		})
		
	}
	
	//리스트
	function list(param,callback,error){
		console.log("list");
	
		var contextPath = getContextPath();
		console.log(contextPath);
		var bno = param.bno;
		var pageNum = param.pageNum;
		
		$.ajax({
			type : 'get',
			url:contextPath+'/reply/list/'+bno+"/"+pageNum,
			contentType:'application/json; charset=utf-8',
			success : function(res){
				if(callback){
					callback(res);
				}
				
			},error:function(){
				console.log("error");
			}
			
		});
	}
	
	
	//날짜 포맷처리
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str ="";
		
		//24시간 지나면 날짜로 표시
		if(gap< (1000*60*60*24)){
			var hh = dateObj.getHours();
			var mm = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh > 9 ? '':'0') + hh, ':' , (mm > 9 ? '':'0')+mm, ':' ,(ss > 9 ? '':'0')+ss].join('');
		}else{
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;
			var dd = dateObj.getDate();
			
			return [(yy > 9 ? '':'0') + yy, '-' , (mm > 9 ? '':'0')+mm, '-' ,(dd > 9 ? '':'0')+dd].join('');
			
		}
	}
	
	
	return {insert:insert,
			list:list,
			displayTime:displayTime};
})();
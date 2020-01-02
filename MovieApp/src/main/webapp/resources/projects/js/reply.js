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
	
	
	function list(param,callback,error){
		console.log("list");
		var contextPath = getContextPath();
		
		$.ajax({
			type : 'get',
			url:contextPath+'reply/list/',
			contentType:'application/json; charset=utf-8',
			data : JSON.stringify()
			
		});
	}
	
	
	return {insert:insert};
})();
/**
 * 
 */



var contextPath     = "<%=contextPath%>";
console.log("reply Module..");
var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
var replyService = (function(){
	
	
	//입력
	function insert(reply,callback,error){
		console.log("insert");
		console.log(reply);
		
		$.ajax({
			type:'post',
			url : '/reply/insert',
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
	
	
	return {insert:insert};
})();
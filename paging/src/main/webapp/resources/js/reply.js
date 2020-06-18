console.log("reply module");

var replyService=(function(){
	
	function add(reply,callback){
		console.log("reply add");
		
		$.ajax({
			type:'post',
			url:'/replise/new',
			data:JSON.springify(reply),
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		})
	};
	
	function list(param,callback,error){
		console.log("reply list");
		var bno = param.bno;
		var page = param.page || 1;
		$.ajax({
			type:'get',
			url:'/replies/pages/'+bno+"/"+page,
			contentType:"application/json; charset=utf-8",
			success:function(result){
					callback(result);
				
			}
			
		})
	};
	
	
	return {
		add:add,
		list:list
		
	};
})();
console.log("reply module");

var replyService=(function(){
	
	//등록
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
	
	//목록
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
	
	
	//시간
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		//var str="";
		
		if(gap < (1000*60*60*24)){
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh>9? '':'0')+hh, ':' (mi>9? '':'0')+mi, ':' (ss>9? '':'0')+ss].join('');
		}else{
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
		}
	}
	
	
	
	return {
		add:add,
		list:list,
		displayTime:displayTime
		
	};
})();
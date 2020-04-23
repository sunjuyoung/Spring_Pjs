console.log("reply Module.................");

// DOM처리 ajax 처리등 마구 섞여서 유지보수하기 힘든 코드를 대비해
// 하나의 모듈처럼 구성 ,관련있는 함수들을 하나의 모듈처럼 묶음 자바스크립트 클로저 이용이 가장 대표적
// javascript를이용해서 메서드를 가지는 객체를 구성

var replyService = (function() {

	// 입력
	function add(reply, callback, error) {
		console.log("reply..................");

		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}

		});

	}

	// 조회
	function get(rno, callback, error) {
		console.log("------------------------" + rno);
		$.ajax({
			type : 'get',
			url : '/replies/' + rno + '.json',
			data : JSON.stringify(rno),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}

			},
			error : function(xhr, status, er) {
				if (error) {
					console.log("------------------------get error");
					error(er);
				}
			}

		});

	}

	// 리스트출력
	function ajaxList(param, callback, error) {

		var bno = param.bno;
		var page = param.page || 1;

		$.ajax({
			type : 'get',
			url : '/replies/pages/' + bno + '/' + page + '.json',
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				if (callback) {
					callback(result);
				}
			}
		});
	}

	// 리스트출력 getJSON
	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data) {
					if (callback) {
						// callback(data);                   //댓글 목록만 가져오는 경우
						callback(data.replyCnt, data.list); // 댓글 개수와 목록을 가져오는 경우
															
					}
				}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}

		});

	}

	// 제거
	function remove(rno, callback, error) {

		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}

		});
	}

	// 수정
	function update(reply, callback, error) {
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/JSON; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}

			},
			error : function(status, xhr, er) {
				if (error) {
					error(er);
				}
			}

		});

	}

	// 댓글 시간 처리

	function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}
	;

	return {
		add : add,
		get : get,
		getList : getList,
		ajaxList : ajaxList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};
})();
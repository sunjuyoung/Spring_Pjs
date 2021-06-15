
//index객체 안에서만 function이 유효하기 때문에 다른 JS와 겹칠 위험이 사라진다.
var main= {
    init: function () {
        var _this = this;

        /*저장 버튼*/
        $('#btn-save').on('click', function () {
            _this.save();
        });

        /*수정 버튼*/
        $('#btn-update').on('click', function () {
            _this.update();
        });

        /*삭제 버튼*/
        $('#btn-delete').on('click', function () {
            _this.delete();
        });

    },

    save: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("글 등록 완료");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()

        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function (result) {
            alert("글 수정 완료"+result);
            window.location.href = '/';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'

        }).done(function () {
            alert('삭제 완료');
            window.location.href = '/';

        }).fail(function (error) {
            alert(JSON.stringify(error));

        })

    }
}

main.init();
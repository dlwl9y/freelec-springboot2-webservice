var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {    // btn-save 버튼을 클릭하면 save 기능 호출
            _this.save();
        });

        $('#btn-update').on('click', function () {  // btn-update 버튼을 클릭하면 update 기능을 호출. ('#btn-update').on('click'): btn-update란 id를 가진 HTML 엘리먼트에 click 이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록한다.
            _this.update();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',   // HTTP 메소드 : 생성(Create) - POST / 읽기(Read) - GET / 수정(Update) - PUT / 삭제(Delete) - DELETE
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';     // 글 등록이 성공하면 메이페이지(/)로 이동한다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    update : function () {  // 신규로 추가될 update function이다.
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,   // 어느 게시글을 수정할지 URL Path로 구분하기 위해 Path에 id를 추가한다.
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();



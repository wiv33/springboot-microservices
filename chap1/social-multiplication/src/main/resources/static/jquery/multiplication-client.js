function updateMultiplication() {
    $.ajax("http://localhost:8080/multiplication/random")
        .then(data => {
            // 폼 비우기
            $("#attempt-form").find("input[name='result-attempt']").val("");
            $("#attempt-form").find("input[name='user-alias']").val("");
            // 무작위 문제를 API로 가져와서 추가하기
            $('.multiplication-a').empty().append(data.factorA);
            $('.multiplication-b').empty().append(data.factorB);
        });

}

$(document).ready(() => {
    updateMultiplication(); Document

    $("#attempt-form").submit(function (event) {

        // 폼 기본 제출 막기
        event.preventDefault();

        // 페이지에서 값 가져오기
        let a = $('.multiplication-a').text();
        let b = $('.multiplication-b').text();
        let $form = $(this),
            attempt = $form.find("input[name='result-attempt']").val(),
            userAlias = $form.find("input[name='user-alias']").val();

        // API 에 맞게 데이터를 조합하기
        const data = {user: {alias: userAlias}, multiplication: {factorA: a, factorB: b}, resultAttempt: attempt};

        // POST를 이용해서 데이터 보내기
        $.ajax({
            url: '/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                if (result.correct) {
                    $('.result-message').empty().append("정답입니다! 축하드려요!");
                } else {
                    $('.result-message').empty().append("오답입니다! 그래도 포기하지 마세요!");
                }
            }
        });

        updateMultiplication();
    });
});
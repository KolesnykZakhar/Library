
function get(url) {
    window.location.href = "#result";
    $.get(url, {

    }, function (responseText) {
        $('#result').html(responseText);
    });
}
function postTel(url, tel) {
    window.location.href = "#result'";
    $.post(url, {
        clientTel : tel
    }, function (responseText) {
        $('#result').html(responseText);
    });
}

// function postIsbn(url, isbnBook) {
//     window.location.href = "#result'";
//     $.post(url, {
//         isbn : isbnBook
//     }, function (responseText) {
//         $('#result').html(responseText);
//     });
// }

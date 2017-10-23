function configuraMoeda() {
    $(".moeda").maskMoney({decimal: ",", thousands: ".", allowZero: true});
}

function timeOut() {
    setTimeout(function () {
        $('#messages').hide(100000);
    }, 8000);
}

function subir() {
    $('html, body').animate({scrollTop: 0}, 'fast');
    $('#buscaProduto').focus();
    return false;
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.ref = null;

$(document).ajaxStop(function () {
    loadAlert();
});

$(document).ready(function () {
    loadAlert();
});

function loadAlert() {
    var sessionAlive = $.maxInactiveInterval;
    var notifyBefore = 15;

    if ($.ref !== null) {
        clearTimeout($.ref);
    }

    $.ref = setTimeout(function () {
        $(function () {
            swal({
                title: "¡Importante!",
                text: "Su sesión está a punto de expirar ¿Desea permanecer en el sitio?",
                type: "warning",
                showCancelButton: true,
                cancelButtonText: "Salir",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sí, continuar",
                closeOnConfirm: false,
                closeOnEsc: false,
                timer: notifyBefore * 1000
            }, function (isConfirm) {
                if (isConfirm) {
                    $.get($.keepSessionAlive, function () {
                        clearTimeout($.ref);
                        loadAlert();
                        swal.close();
                    });
                } else {
                    $(location).prop('href', $.logout);
                }
            });
        });
    }, (sessionAlive - notifyBefore) * 1000);
}
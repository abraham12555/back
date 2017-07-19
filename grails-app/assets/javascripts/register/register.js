$.validEmail = "validEmail";
$.passwordRecovery = "forgotPassword";

$(document).ready(function () {
    $('#updatePassword-btn').on('click', function (event) {
        event.preventDefault();
        validateNewPassword();
    });

    $('#recoveryPassword-btn').on('click', function (event) {
        event.preventDefault();

        $("#successMessage-div").html("");
        $("#errorMessage-div").html("");
        $("#successMessage-div").addClass("hidden");
        $("#errorMessage-div").addClass("hidden");
        validateForm();
    });
});

function validateNewPassword() {
    var errors = 0;
    $('p[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#password").val().trim() === "") {
        errors++;
        errorMessage($('#password'), "El campo es obligatorio");
    } else if (!validatePassword($("#password").val().trim())) {
        errors++;
        errorMessage($('#password'), "La contraseña debe contener mínimo 8 y máximo 12 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial");
    } else if ($("#password").val().trim() !== $("#confirm-password").val().trim()) {
        errors++;
        errorMessage($('#confirm-password'), "Las contraseñas no coinciden");
    }

    if (errors === 0) {
        $("#updatePassword").submit();
    }
}

function validateForm() {
    var errors = 0;
    $('p[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($('#email').val().trim() === "") {
        errors++;
        errorMessage($('#email'), "El campo es obligatorio");
    } else if (!validateEmail($('#email').val().trim())) {
        errors++;
        errorMessage($('#email'), "Tu dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com");
    } else {
        validEmail(function (response) {
            if (response.estatus === false) {
                errorMessage($('#email'), "La dirección de correo electrónico no ha sido registrada");
            } else if (response.estatus === "ERROR") {
                errorMessage($('#email'), "Ocurrió un error al validar la cuenta de correo electrónico. Inténtalo más tarde");
            } else if (response.estatus === true) {
                sendRequest();
            }
        });
    }
}

function errorMessage(element, message) {
    element.closest('.control').before("<p class='help-block marginRight25'><small style='color: red;'>" + message + "</small></p>");
}

function validEmail(callback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.validEmail,
        data: "email=" + $("#email").val().trim(),
        cache: false,
        success: function (response) {
            callback(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var response = {estatus: "ERROR"};
            callback(response);
        }
    });
}

function sendRequest() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.passwordRecovery,
        data: "email=" + $("#email").val().trim(),
        beforeSend: function (XMLHttpRequest, settings) {
            $("body").mLoading({
                text: "Enviando solicitud, espere por favor...",
                mask: true
            });
        },
        success: function (response) {
            cargaCompletada();

            if (response.error === true) {
                $("#errorMessage-div").html(response.message);
                $("#errorMessage-div").removeClass("hidden");
            } else {
                $("#recoverPassword")[0].reset();
                $("#successMessage-div").html(response.message);
                $("#successMessage-div").removeClass("hidden");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            cargaCompletada();

            $("#errorMessage-div").html("Algo salió mal, intenta nuevamente en unos minutos.");
            $("#errorMessage-div").removeClass("hidden");
        }
    });
}

function cargaCompletada() {
    setTimeout(function () {
        $("body").mLoading('hide');
    }, 1000);
}


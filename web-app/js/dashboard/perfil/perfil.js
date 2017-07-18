$.getProfile = $.contextAwarePathJS + "dashboard/getProfile";
$.deleteProfilePicture = $.contextAwarePathJS + "dashboard/deleteProfilePicture";

$(document).ready(function () {

    $("#editProfile-btn").on("click", function (event) {
        event.preventDefault();
        if ($(this).text() === "EDITAR") {
            $("#name").prop("disabled", false);
            $("#apPaterno").prop("disabled", false);
            $("#apMaterno").prop("disabled", false);
            $("#username").prop("disabled", false);
            $("#email").prop("disabled", false);
            $("#editProfile-btn").html("GUARDAR CAMBIOS");
            $("#discardChanges-btn").css("display", "block");
        } else {
            validateProfileForm();
        }
    });

    $('#discardChanges-btn').on('click', function (event) {
        event.preventDefault();
        getProfile();
    });

    $('#updatePassword-btn').on('click', function (event) {
        event.preventDefault();
        $('p[class*="help-block"]').each(function () {
            $(this).remove();
        });
        openModal('updatePassword');
    });

    $('#changePassword-btn').on('click', function (event) {
        event.preventDefault();
        validateNewPassword();
    });

    $('#cancelUpdatePassword-btn').on('click', function (event) {
        event.preventDefault();
        cerrarModal('updatePassword');
    });

    $("#editProfilePicture-btn").on("click", function (event) {
        event.preventDefault();
        openModal('updateProfilePicture');
    });

    $('#cancelChangePicture-btn').on('click', function (event) {
        event.preventDefault();
        resetProfilePictureForm();
    });

    $('#cancelChangePicture-btn').on('click', function (event) {
        event.preventDefault();
        resetProfilePictureForm();
    });

    $("#deleteProfilePicture-btn").on("click", function (event) {
        event.preventDefault();
        deleteProfilePicture();
    });

    getProfile();
});

function getProfile() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getProfile,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('p[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#formProfile")[0].reset();
        },
        success: function (response) {
            $("#userId").val(response.id);
            $("#name").val(response.nombre);
            $("#apPaterno").val(response.apellidoPaterno);
            $("#apMaterno").val(response.apellidoMaterno);
            $("#username").val(response.username);
            $("#email").val(response.email);

            $("#name").prop("disabled", true);
            $("#apPaterno").prop("disabled", true);
            $("#apMaterno").prop("disabled", true);
            $("#username").prop("disabled", true);
            $("#email").prop("disabled", true);

            $("#editProfile-btn").html("EDITAR");
            $("#discardChanges-btn").css("display", "none");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function validateProfileForm() {
    var errors = 0;

    $('p[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#name").val().trim() === "") {
        errors++;
        errorProfileMessage($('#name'), "El campo es obligatorio");
    }

    if ($("#apPaterno").val().trim() === "") {
        errors++;
        errorProfileMessage($('#apPaterno'), "El campo es obligatorio");
    }

    if ($("#apMaterno").val().trim() === "") {
        errors++;
        errorProfileMessage($('#apMaterno'), "El campo es obligatorio");
    }

    if ($("#username").val().trim() === "") {
        errors++;
        errorProfileMessage($('#username'), "El campo es obligatorio");
    } else if ($("#username").val().length < 6 || $("#username").val().length > 15) {
        errors++;
        errorProfileMessage($('#username'), "El nombre de usuario debe contener mínimo 6 y máximo 15 caracteres");
    }

    if ($('#email').val().trim() === "") {
        errors++;
        errorProfileMessage($('#email'), "El campo es obligatorio");
    } else if (!validateEmail($('#email').val())) {
        errors++;
        errorProfileMessage($('#email'), "Tu dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com");
    }

    if (errors === 0) {
        validUsername(function (response) {
            if (response.estatus === false) {
                errorProfileMessage($('#username'), "El nombre de usuario ya ha sido registrado");
            } else if (response.estatus === "ERROR") {
                errorProfileMessage($('#username'), "Ocurrió un error al validar el nombre de usuario");
            } else {
                validEmail(function (response) {
                    if (response.estatus === false) {
                        errorProfileMessage($('#email'), "La cuenta de correo electrónico ya ha sido registrada");
                    } else if (response.estatus === "ERROR") {
                        errorProfileMessage($('#email'), "Ocurrió un error al validar la cuenta de correo electrónico");
                    } else {
                        submitProfileForm();
                    }
                });
            }
        });
    }
}

function submitProfileForm() {
    var form = $("#formProfile");
    var postData = new FormData($(form)[0]);
    var formURL = $(form).attr('action');
    $.ajax({
        url: formURL,
        type: 'POST',
        data: postData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.error) {
                sweetAlert("Oops...", response.mensaje, "error");
            } else {
                getProfile();
                sweetAlert({html: false, title: "¡Excelente!", text: "Los datos del usuario se han guardado correctamente.", type: "success"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function errorProfileMessage(element, message) {
    element.closest('.control').before("<p class='help-block marginRight25'><small style='color: red;'>" + message + "</small></p>");
}

function validateNewPassword() {
    var errors = 0;
    $('p[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#password").val().trim() === "") {
        errors++;
        errorProfileMessage($('#password'), "El campo es obligatorio");
    } else if (!validatePassword($("#password").val().trim())) {
        errors++;
        errorProfileMessage($('#password'), "La contraseña debe contener mínimo 8 y máximo 12 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial");
    } else if ($("#password").val().trim() !== $("#confirm-password").val().trim()) {
        errors++;
        errorProfileMessage($('#confirm-password'), "Las contraseñas no coinciden");
    }

    if (errors === 0) {
        updatePassword();
    }
}

function updatePassword() {
    var form = $("#updatePassword-form");
    var postData = new FormData($(form)[0]);
    var formURL = $(form).attr('action');
    $.ajax({
        url: formURL,
        type: 'POST',
        data: postData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.error) {
                errorProfileMessage($('#password'), response.mensaje);
            } else {
                sweetAlert({html: false, title: "¡Excelente!", text: "La contraseña se ha guardado correctamente.", type: "success"});
                cerrarModal('updatePassword');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

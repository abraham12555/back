/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.getUsers = $.contextAwarePathJS + "dashboard/getUsers";
$.getUserDetails = $.contextAwarePathJS + "dashboard/getUserDetails";
$.validNoEmpleado = $.contextAwarePathJS + "dashboard/validNoEmpleado";
$.validNewPassword = $.contextAwarePathJS + "dashboard/validNewPassword";
$.userAuthentication = false;
$.currentValue;

$(document).ready(function () {
    $('#saveUser-btn').on('click', function (event) {
        event.preventDefault();
        validateUserInfoForm();
    });

    $('#newUser-btn').on('click', function (event) {
        event.preventDefault();
        resetUserForm();
        $("#userId").val("0");
        $("#password-div").css("display", "block");
        $("#lock-div").css("display", "none");
        $("#enabled-div").css("display", "none");
        $("#resetPassword-div").css("display", "none");
        $("#newPassword-div").css("display", "none");
        $("#operation-title").html("registro de usuario");
        openModal('modalAltaDeUsuario');
    });

    $('#usuarios-tb').on('click', 'button.userDetails', function (event) {
        event.preventDefault();
        var idUser = this.getAttribute('data-id');
        viewUserDetails(idUser);
    });

    $('#pagination').on('click', 'a.page', function (event) {
        event.preventDefault();
        var page = $(this).data('page');
        getUsers(page);
    });

    $('#rol').focus(function () {
        if (!$.userAuthentication && $("#userId").val() !== "0") {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });
            $("#formUserAuthentication")[0].reset();
            openModal('loginUsuario');
            $("#rol").val($.currentValue).change();
            $('#rol').css("display", "none");
        }
    });

    $('#autenticarUsuario-btn').on('click', function (event) {
        event.preventDefault();
        userAuthentication();
    });

    $('#cancelUserAuthentication-btn').on('click', function (event) {
        event.preventDefault();
        $('#rol').css("display", "block");
        cerrarModal('loginUsuario');
    });

    $('#username').keyup(function () {
        this.value = this.value.toLowerCase();
    });

    $('input[name="resetPassword"]').click(function () {
        $("#newPassword-div").toggle($("input[name='resetPassword']:checked").val());
    });

    getUsers(1);
});

function getUsers(page) {
    $("#currentPage").val(page);

    var filter = new Object();
    filter.page = page;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getUsers,
        data: JSON.stringify(filter),
        contentType: "application/json",
        beforeSend: function (XMLHttpRequest, settings) {
            $("#error-users-div").addClass("hide");
        },
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#pagination').empty();
            $("#usuarios-tb tbody").empty();
            if (response.usuarios.length > 0) {
                pagination(totalPages, page, 'pagination');
                $.each(response.usuarios, function (index) {
                    var row = "";
                    if (index === 0) {
                        row += "<tr></tr>";
                    }
                    row += '<tr>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'Nombre de usuario <br/>';
                    row += '<span class="font14 textlower tableDescriptionColor">' + this.username + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'nombre <br/>';
                    row += '<span class="font14 tableDescriptionColor textUpper">' + this.fullName + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'correo electrónico <br/>';
                    row += '<span class="font14 textlower tableDescriptionColor">' + this.email + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'rol <br/>';
                    $.each(this.authorities, function (i) {
                        if (i > 0) {
                            row += "<br/>";
                        }
                        row += '<span class="font14 textUpper tableDescriptionColor">' + this.authority + '</span>';
                    });
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite userDetails" data-id="' + this.id + '" type="button">editar</button>';
                    row += '</td>';
                    row += '</tr>';
                    $("#usuarios-tb tbody:last").append(row);
                });
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay usuarios registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#usuarios-tb tbody:last").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#error-users-div").html("Ocurrió un error al obtener la lista de usuarios. Inténtalo más tarde");
            $("#error-users-div").removeClass("hide");
        }
    });
}

function validateUserInfoForm() {
    var errors = 0;

    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#name").val().trim() === "") {
        errors++;
        errorMessage("name", "El campo es obligatorio");
    }

    if ($("#apPaterno").val().trim() === "") {
        errors++;
        errorMessage('apPaterno', "El campo es obligatorio");
    }

    if ($("#apMaterno").val().trim() === "") {
        errors++;
        errorMessage('apMaterno', "El campo es obligatorio");
    }

    if ($("#username").val().trim() === "") {
        errors++;
        errorMessage('username', "El campo es obligatorio");
    } else if ($("#username").val().length < 6 || $("#username").val().length > 15) {
        errors++;
        errorMessage('username', "El nombre de usuario debe contener mínimo 6 y máximo 15 caracteres");
    }

    if ($("#userId").val() === "0" && $("#password").val().trim() === "") {
        errors++;
        errorMessage('password', "El campo es obligatorio");
    } else if ($("#userId").val() === "0" && !validatePassword($("#password").val().trim())) {
        errors++;
        errorMessage('password', "La contraseña debe contener mínimo 8 y máximo 12 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial");
    }

    if ($('#email').val().trim() === "") {
        errors++;
        errorMessage('email', "El campo es obligatorio");
    } else if (!validateEmail($('#email').val())) {
        errors++;
        errorMessage('email', "Tu dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com");
    }

    if ($('#sucursal').val().length === 0) {
        errors++;
        errorMessage('sucursal', "El campo es obligatorio");
    }

    if ($("#noEmpleado").val().trim() === "") {
        errors++;
        errorMessage('noEmpleado', "El campo es obligatorio");
    }

    if ($("#rol").val() === null || $('#rol').val().length === 0) {
        errors++;
        errorMessage('rol', "El campo es obligatorio");
    }

    if ($("#userId").val() !== "0" && $("input[name='resetPassword']:checked").val() === 'true') {
        if ($("#newPassword").val().trim() === "") {
            errors++;
            errorMessage('newPassword', "El campo es obligatorio");
        } else if (!validatePassword($("#newPassword").val().trim())) {
            errors++;
            errorMessage('newPassword', "La contraseña debe contener mínimo 8 y máximo 12 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial");
        }
    }

    if (errors === 0) {
        validUsername(function (response) {
            if (response.estatus === false) {
                errorMessage('username', "El nombre de usuario ya ha sido registrado");
            } else if (response.estatus === "ERROR") {
                errorMessage('username', "Ocurrió un error al validar el nombre de usuario");
            } else {
                validEmail(function (response) {
                    if (response.estatus === false) {
                        errorMessage('email', "La cuenta de correo electrónico ya ha sido registrada");
                    } else if (response.estatus === "ERROR") {
                        errorMessage('email', "Ocurrió un error al validar la cuenta de correo electrónico");
                    } else {
                        validNoEmpleado(function (response) {
                            if (response.estatus === false) {
                                errorMessage('noEmpleado', "El número de empleado ya ha sido registrado");
                            } else if (response.estatus === "ERROR") {
                                errorMessage('noEmpleado', "Ocurrió un error al validar el número de empleado");
                            } else {
                                validNewPassword(function (response) {
                                    if (response.error === true) {
                                        errorMessage('newPassword', response.mensaje);
                                    } else if (response.estatus === "ERROR") {
                                        errorMessage('newPassword', "Ocurrió un error al validar la contraseña");
                                    } else {
                                        submitUserForm();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}

function errorMessage(element, message) {
    $("#" + element + "-control").html("<span class='help-block marginRight25'><small style='color: red;'>" + message + "</small></span>");
}

function resetUserForm() {
    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });
    $("#formAltaUsuario")[0].reset();
}

function viewUserDetails(idUser) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getUserDetails,
        data: "idUser=" + idUser,
        cache: false,
        success: function (response) {
            resetUserForm();
            var data = [];
            var sucursal = (response.sucursal !== null) ? response.sucursal.id : "";
            $.each(response.authorities, function () {
                data.push(this.id);
            });

            $("#userId").val(response.id);
            $("#name").val(response.nombre);
            $("#apPaterno").val(response.apellidoPaterno);
            $("#apMaterno").val(response.apellidoMaterno);
            $("#username").val(response.username);
            $("#password-div").css("display", "none");
            $("#email").val(response.email);
            $("#rol").val(data).change();
            $("#sucursal").val(sucursal).change();
            $("#noEmpleado").val(response.noEmpleado);
            $("input[name='accountLocked'][value=" + response.accountLocked + "]").prop('checked', true);
            $("input[name='enabled'][value=" + response.enabled + "]").prop('checked', true);

            $("#lock-div").css("display", "block");
            $("#enabled-div").css("display", "block");
            $("#resetPassword-div").css("display", "block");
            $("#newPassword-div").css("display", "none");
            $("#operation-title").html("datos del usuario");

            $.userAuthentication = false;
            $.currentValue = data;
            openModal('modalAltaDeUsuario');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function validNoEmpleado(callback) {
    var usuario = new Object();
    usuario.id = $("#userId").val().trim();
    usuario.noEmpleado = $("#noEmpleado").val().trim();

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.validNoEmpleado,
        data: JSON.stringify(usuario),
        contentType: "application/json",
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

function submitUserForm() {
    var form = $("#formAltaUsuario");
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
                cerrarModal('modalAltaDeUsuario');

                getUsers(parseInt($("#currentPage").val()));
                sweetAlert({html: false, title: "¡Excelente!", text: "Los datos del usuario se han guardado correctamente.", type: "success"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function userAuthentication() {
    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#authPassword").val().trim() === "") {
        errorMessage('authPassword', "El campo es obligatorio");
    } else {
        var form = $("#formUserAuthentication");
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
                if (response.estatus) {
                    $.userAuthentication = true;
                    $('#rol').css("display", "block");
                    cerrarModal('loginUsuario');
                } else {
                    errorMessage('authPassword', response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            }
        });
    }
}

function validNewPassword(callback) {
    if ($("#userId").val() === "0" || $("input[name='resetPassword']:checked").val() === 'false') {
        var response = {error: false};
        callback(response);
    } else {
        var usuario = new Object();
        usuario.id = $("#userId").val().trim();
        usuario.password = $("#newPassword").val().trim();

        $.ajax({
            type: "POST",
            dataType: "json",
            url: $.validNewPassword,
            data: JSON.stringify(usuario),
            contentType: "application/json",
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
}
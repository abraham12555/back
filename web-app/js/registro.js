function iniciarFormularioRegistro() {
    console.log("Entrando a inicializar los elementos nuevos...");
// animacion de fadeIn
    $('.register').addClass('animated fadeIn');

// elimino la clase para poder animar nuevamente
    setTimeout(function () {
        $('.register').removeClass('fadeIn');
    }, 3000);

    $('i').on('click', function () {
        console.log("i ->" + $(this).parent().attr('id'));
        if ($(this).parent().attr('id') === 'first_name_form') {
            submitFirstName();
        }
        if ($(this).parent().attr('id') === 'last_names_form') {
            submitLastNames();
        }
        if ($(this).parent().attr('id') === 'email_form') {
            submitEmail();
        }
        if ($(this).parent().attr('id') === 'phone_form') {
            submitPhone();
        }
        if ($(this).parent().attr('id') === 'codigo_form') {
            submitCodigo();
        }
    });

    $('#first_name').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitFirstName();
            return false;
        }
    });

    $('#last_names').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitLastNames();
            return false;
        }
    });


    $('#email').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitEmail();
            return false;
        }
    });

    $('#phone').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitPhone();
            return false;
        }
    });

    $('#codigo').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitCodigo();
            return false;
        }
    });
}

function submitFirstName() {
    if ($('#first_name').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } else {
        $('#termsPriv').fadeOut();
        $('.register').addClass('bounceOut');
        setTimeout(function () {
            $('.register').removeClass(' bounceOut');
            $('.register').addClass('fadeIn');
            $("#first_name_form").addClass('hide');
            $("#last_names_form").removeClass('hide');
            $("#last_names_form").addClass('animated fadeIn');
            $('.register').removeClass('fadeIn');
        }, 600);
    }
}

function submitLastNames() {
    if ($('#last_names').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } else {
        $('.register').addClass('bounceOut');
        setTimeout(function () {
            $('.register').removeClass(' bounceOut');
            $('.register').addClass('fadeIn');
            $("#last_names_form").addClass('hide');
            $('#email_form').removeClass('hide');
            $('#email_form').addClass('animated fadeIn');
            $('.register').removeClass('fadeIn');
        }, 600);

    }
}

function submitEmail() {
    if ($('#email').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } else {
        if (validateEmail($('#email').val())) {
            $('#email').removeClass('invalid');
            $('#email').siblings('.fa').removeClass('invalid');
        }
        if (!validateEmail($('#email').val())) {
            $('.register').addClass('shake');

            $('#email').addClass('invalid');
            $('#email').siblings('.fa').addClass('invalid');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
        } else {
            $('.register').addClass('bounceOut');
            setTimeout(function () {
                $('.register').removeClass(' bounceOut');
                $('.register').addClass('fadeIn');
                $('#email_form').addClass('hide');
                $('#phone_form').removeClass('hide');
                $('#phone_form').addClass('animated fadeIn');
                $('.register').removeClass('fadeIn');
            }, 600);
        }


    }
}

function submitPhone() {
    if ($('#phone').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } else {
        if ($('#phone').hasClass('invalid')) {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
        } else {
            $.ajax({
                type: 'POST',
                data: {
                    telefonoCelular: $('#phone').val()
                },
                url: "/cotizador/solicitarCodigo",
                success: function (data, textStatus) {
                    var respuesta = eval(data);
                    if (respuesta.mensajeEnviado === true) {
                        $('#editarTelefono').html("");
                        $('#editarTelefono').hide();
                        $('.register').addClass('bounceOut');
                        setTimeout(function () {
                            $('.register').removeClass(' bounceOut');
                            $('.register').addClass('fadeIn');
                            $('#phone_form').addClass('hide');
                            $('#codigo_form').removeClass('hide');
                            $('.register').removeClass('fadeIn');
                        }, 600);
                        setTimeout(function () {
                            $('#editarTelefono').html("<span class='backBtn'>Corregir Teléfono</span>");
                            $('#editarTelefono').fadeIn();
                            $('#editarTelefono').click(function () {
                                $('.register').addClass('bounceOut');
                                setTimeout(function () {
                                    $('.register').removeClass(' bounceOut');
                                    $('.register').addClass('fadeIn');
                                    $('#codigo_form').addClass('hide');
                                    $('#phone_form').removeClass('hide');
                                    $('#phone_form').addClass('animated fadeIn');
                                    $('.register').removeClass('fadeIn');
                                }, 600);
                            });
                        }, 10000);
                    } else {
                        $('#leyendaTel').html("<small style='color: red;'>Ocurrió un problema al enviar el mensaje. Verifica tu número de Celular.</small>");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    }
}

function submitCodigo() {
    if ($('#codigo').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } else {
        if ($('#codigo').hasClass('invalid')) {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
        } else {
            $.ajax({
                type: 'POST',
                data: {
                    codigoConfirmacion: $('#codigo').val()
                },
                url: "/cotizador/resultadoVerificacion",
                success: function (data, textStatus) {
                    var respuesta = eval(data);
                    if (respuesta.resultado === true) {
                        $('.register').addClass('bounceOut');
                        setTimeout(function () {
                            $('.register').removeClass(' bounceOut');
                            $('.register').addClass('fadeIn');
                            $('#codigo_form').addClass('hide');
                            $('#thanks').removeClass('hide');
                            var registeredName = $('#first_name').val();
                            $('.register').removeClass('fadeIn');
                            $('#thanks').addClass('animated fadeIn');
                            $('#registeredName').html(registeredName);
                        }, 600);
                        $('#datosRegistro').text("¡Registrado Correctamente!");
                        $('#divRegistroCompleto').html("<p id='btnRegistro' class='marginAuto width350 nextAction cotizador-box'>Siguiente</p>");
                        $('#divRegistroCompleto').fadeIn();
                        initActions();
                    } else {
                        $('#leyendaCodigo').html("<small style='color: red;'>El código indicado no es correcto, Verificalo e intenta nuevamente por favor.</small>");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    }
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
var folio = "";
var shortUrl = "";
var numeroTelefonico = "";
function iniciarFormularioRegistro() {
// animacion de fadeIn
    $('.register').addClass('animated fadeIn');
    if( $('#correoComparaBien').val() !== null){
        $('#email').val($('#correoComparaBien').val());
    }
    // elimino la clase para poder animar nuevamente
    setTimeout(function () {
        $('.register').removeClass('fadeIn');
    }, 3000);

    $('.sigPaso').on('click', function () {
        if ($(this).hasClass('locked') === false) {
            if ($(this).parent().attr('id') === 'first_name_form') {
                submitFirstName();
            }
            if ($(this).parent().parent().attr('id') === 'email_form') {
                submitEmail();
            }
            if ($(this).parent().parent().attr('id') === 'phone_form') {
                submitPhone();
            }
            if ($(this).parent().parent().attr('id') === 'codigo_form') {
                submitCodigo();
            }
            if ($(this).parent().parent().attr('id') === 'folio_form') {
                compareFolio("cotizador");
            }
        }
    });

    $('.antPaso').on('click', function () {
        if ($(this).hasClass('locked') === false) {
            if ($(this).parent().parent().attr('id') === 'email_form') {
                $('#termsPriv').fadeIn();
                $('.register').addClass('bounceOut');
                setTimeout(function () {
                    $('.register').removeClass(' bounceOut');
                    $('.register').addClass('fadeIn');
                    $("#email_form").addClass('hide');
                    $('#first_name_form').removeClass('hide');
                    $('#first_name_form').addClass('animated fadeIn');
                    $('.register').removeClass('fadeIn');
                }, 600);
            }
            if ($(this).parent().parent().attr('id') === 'phone_form') {
                $('.register').addClass('bounceOut');
                setTimeout(function () {
                    $('.register').removeClass(' bounceOut');
                    $('.register').addClass('fadeIn');
                    $("#phone_form").addClass('hide');
                    $('#email_form').removeClass('hide');
                    $('#email_form').addClass('animated fadeIn');
                    $('.register').removeClass('fadeIn');
                }, 600);
            }
        }
    });
    
    $('#first_name').on('keyup', function (e) {
        if (e.keyCode === 13) {
            submitFirstName();
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
        if (validateName($('#first_name').val())) {
            $('#leyendaNombre').html("");
            $('#first_name').removeClass('invalid');
            $('#first_name').siblings('.fa').removeClass('invalid');
        }
        if (!validateName($('#first_name').val())) {
            $('.register').addClass('shake');

            $('#first_name').addClass('invalid');
            $('#first_name').siblings('.fa').addClass('invalid');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
            $('#leyendaNombre').html("<small style='color: red;'>Tu nombre no puede contener caracteres especiales.</small>");
        } else {
            $('#termsPriv').fadeOut();
            $('.register').addClass('bounceOut');
            setTimeout(function () {
                $('.register').removeClass(' bounceOut');
                $('.register').addClass('fadeIn');
                $("#first_name_form").addClass('hide');
                $('#email_form').removeClass('hide');
                $('#email_form').addClass('animated fadeIn');
                $('.register').removeClass('fadeIn');
            }, 600);
        }
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
            $('#leyendaEmail').html("");
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
            $('#leyendaEmail').html("<small style='color: red;'>Tu dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com</small>");
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
   var phone = $('#phone').val();
   phone= phone.replace(/\-/g,"");
    if ($('#phone').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    }
    if ((phone.length < 10 || phone.length > 10) || validarSiNumero(phone) === true ) {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
        $('#leyendaTelError').html("<small style='color: red;'>El teléfono debe contener 10 caracteres numéricos</small>");
    } else {
        if ($('#phone').hasClass('invalid')) {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
        } else {
            $('#leyendaTelError').html("");
            $('#leyendaFolioError').html("");      
            $('#leyendaFolio').html("");            
            $('#phone').prop('disabled', true);
            $('.sigPaso').addClass('locked');
            $('#editarTelefono').html("");
            $('#editarTelefono').hide();
            $('#editarTelefono2').html("");
            $('#editarTelefono2').hide();
            $.ajax({
                type: 'POST',
                data: {
                    telefonoCelular: $('#phone').val(),
                    email: $('#email').val(),
                    nombreCompleto: $('#first_name').val(),
                    origen: "cotizador"
                },
                url: $.contextAwarePathJS + "cotizador/solicitarCodigo",
                success: function (data, textStatus) {
                    var respuesta = eval(data);
                    if (respuesta.sesionExpirada) {
                        sweetAlert("Oops...", "La sesión ha caducado recarga la página e intenta de nuevo.", "error");
                    }
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
                            $('#leyendaCodigo').html("<small style='color: #25a3ff;'><strong>Espera por favor entre 15 y 30 segundos para recibir tu código.</strong></small>");
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
                        }, 20000);
                    } else if (respuesta.encontrado === true && respuesta.shortUrl && respuesta.folio) {
                        $('#resumirSolicitud').html("");
                        $('#resumirSolicitud').hide();
                        $('.register').addClass('bounceOut');
                        setTimeout(function () {
                            $('.register').removeClass(' bounceOut');
                            $('.register').addClass('fadeIn');
                            $('#phone_form').addClass('hide');
                            $('#folio_form').removeClass('hide');
                            $('.register').removeClass('fadeIn');
                        }, 600);
                        setTimeout(function () {
                            $('#editarTelefono2').html("<span class='backBtn'>Corregir Teléfono</span>");
                            $('#editarTelefono2').fadeIn();
                            $('#editarTelefono2').click(function () {
                                $('.register').addClass('bounceOut');
                                setTimeout(function () {
                                    $('.register').removeClass(' bounceOut');
                                    $('.register').addClass('fadeIn');
                                    $('#folio_form').addClass('hide');
                                    $('#phone_form').removeClass('hide');
                                    $('#phone_form').addClass('animated fadeIn');
                                    $('.register').removeClass('fadeIn');
                                }, 600);
                            });
                        }, 20000);

                        folio = respuesta.folio;
                        shortUrl= respuesta.shortUrl;
                        numeroTelefonico= respuesta.numeroTelefonico;
                    } else if (respuesta.multiplesClientes === true){
                           $('#resumirSolicitud').html("");
                        $('#resumirSolicitud').hide();
                        $('.register').addClass('bounceOut');
                        setTimeout(function () {
                            $('.register').removeClass(' bounceOut');
                            $('.register').addClass('fadeIn');
                            $('#phone_form').addClass('hide');
                            $('#many_form').removeClass('hide');
                            $('.register').removeClass('fadeIn');
                        }, 600);
                        $('#resumirSolicitud').html("<span class='resumenBtn'>Existe más de una solicitud vigente a este número</span>");
                        $('#resumirSolicitud').fadeIn();
                        }
                        else {
                        $('#leyendaTel').html("<small style='color: red;'>Ocurrió un problema al enviar el mensaje. Verifica tu número de Celular.</small>");
                    }
                    $('#phone').prop('disabled', false);
                    $('.sigPaso').removeClass('locked');
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                    $('#phone').prop('disabled', false);
                    $('.sigPaso').removeClass('locked');
                }
            });
        }
    }
}

function submitCodigo() {
    var codigo = $('#codigo').val();
    if ($('#codigo').val() === '') {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
    } 
    if ((codigo.length < 5 || codigo.length > 5) || validarSiNumero(codigo) === true ) {
        $('.register').addClass('shake');
        setTimeout(function () {
            $('.register').removeClass('shake');
        }, 3000);
        $('#leyendaCodigoError').html("<small style='color: red;'>El código debe contener 5 caracteres numéricos</small>");
    }
    else {
        if ($('#codigo').hasClass('invalid')) {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
        } else {
            $('#leyendaCodigoError').html("");
            $.ajax({
                type: 'POST',
                data: {
                    codigoConfirmacion: $('#codigo').val()
                },
                url: $.contextAwarePathJS + "cotizador/resultadoVerificacion",
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

function validateName(nombre) {
    var re = /^([a-zA-Z \. \- \@ ñáéíóúüÑÁÉÍÓÚÜ])+$/;
    return re.test(nombre);
}
function validarSiNumero(numero){
    if (!/^([0-9])*$/.test(numero)){
        return true;
    }
    else{
        return false;
    }
  }
function compareFolio(origen) {
    if (origen === "cotizador") {
        if ($('#folio').val() === '') {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
            
            $('#leyendaFolio').html("");
            $('#leyendaFolioError').html("<small style='color: red;'>El folio no debe ir vacio</small>");
        }
        if ($('#folio').val() !== folio) {
            $('.register').addClass('shake');
            setTimeout(function () {
                $('.register').removeClass('shake');
            }, 3000);
            $('#leyendaFolio').html("");
            $('#leyendaFolioError').html("<small style='color: red;'>El folio no coincide favor de verificar</small>");
        }
        if($('#folio').val().length !==7){
            $('#leyendaFolio').html("");
            $('#leyendaFolioError').html("<small style='color: red;'>El folio debe contener 7 caracteres>");
        }
        if ($('#folio').val() === folio) {
            $('#leyendaFolioError').html("");
            window.location.href = shortUrl;
            
        }
    } else if (origen === "formulario") {
        if ($('#folioFormulario').val() === '') {
            $('#folioFormularioError').html("El folio no puede ir vacio.");
            $('#filaErrorFolioFormulario').removeClass('hide');
            $('#leyendaFolioSms').css('padding-top','');
        }
        if ($('#folioFormulario').val().length ===7) {
            var folioFormulario = $('#folioFormulario').val();
            $.ajax({
                type: 'POST',
                data: {
                    folio: folioFormulario
                },
                url: $.contextAwarePathJS + "cotizador/buscarFolio",
                success: function (data, textStatus) {
                    var respuesta = eval(data);
                    if(respuesta.sesionExpirada){
                     sweetAlert("Oops...", "La sesión ha caducado recarga la página e intenta de nuevo.", "error");
                    }
                    if (respuesta.encontrado) {
                        window.location.href = respuesta.shortUrl;
                    } else {
                        $('#folioFormulario').val("");
                        $('#folioFormularioError').html("El folio no existe o ya no se encuentra vigente por favor verificalo.");
                        $('#filaErrorFolioFormulario').removeClass('hide');
                        $('#leyendaFolioSms').css('padding-top','');
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                    $('#folioFormulario').val("");
                    $('#folioFormularioError').html("Algo salió mal, intenta nuevamente en unos minutos.");
                    $('#filaErrorFolioFormulario').removeClass('hide');
                    $('#leyendaFolioSms').css('padding-top','');

                }
            });
        }
        else if($('#folioFormulario').val().length !==7 ){
            $('#folioFormularioError').html("El folio debe contener 7 caracteres");
            $('#filaErrorFolioFormulario').removeClass('hide');
            $('#leyendaFolioSms').css('padding-top','');
        }


    }
}

function enviarFolio() {
    $('#leyendaFolioError').html("");
    $.ajax({
        type: 'POST',
        data: {
            numeroTelefonico: numeroTelefonico,
            folio: folio
        },
        url: $.contextAwarePathJS + "cotizador/enviarFolio",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.sesionExpirada) {
                     sweetAlert("Oops...", "La sesión ha caducado recarga la página e intenta de nuevo.", "error");
            }
            if (respuesta.mensajeEnviado) {
                setTimeout(function () {
                    $('#leyendaFolio').html("<small style='color: #25a3ff;'><strong>Espera por favor entre 15 y 30 segundos para recibir tu folio.</strong></small>");
                }, 600);
                setTimeout(function () {

                }, 20000);
            } else {
                $('#leyendaFolio').html("<small style='color: red;'>Ocurrió un problema al enviar el mensaje.</small>");
            }
            $('.sigPaso').removeClass('locked');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            $('#phone').prop('disabled', false);
            $('.sigPaso').removeClass('locked');
        }
    });
}

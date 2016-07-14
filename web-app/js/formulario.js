// Función para cambiar el fondo del banco solicitado
function seleccionarBanco(banco) {
    $('.brandingBox').removeClass("green");
    $('#div' + banco).addClass("green");
}

//Callback para hacer la consulta con el API Bancaria
function consultarBancos() {
    var banco = $('input[name="banco"]:checked').val();
    console.log("Validando seleccion de banco...");
    if (banco) {
        var cliente = $('#clientNo').val();
        var clave = $('#clave').val();
        var token = $('#tokenNo').val();
        var intentos = $('#intentos').val();
        console.log("Validando llenado de campos...");
        if (cliente && clave && token) {
            console.log("Mostrando barra de progreso...");
            $('#progresoConsulta').show('slow');
            $.ajax({
                type: 'POST',
                data: 'banco=' + banco + "&cliente=" + cliente + "&clave=" + clave + "&token=" + token + "&intentos=" + intentos,
                url: '/kosmos-app/solicitud/consultaBancos',
                success: function (data, textStatus) {
                    var respuesta = checkIfJson(data);
                    if (respuesta.status === 200) {
                        $('#dep90').val(formatCurrency(respuesta.depositosPromedio, "$"));
                        $('#ret90').val(formatCurrency(respuesta.retirosPromedio, "$"));
                        $('#saldo90').val(formatCurrency(respuesta.saldoPromedio, "$"));
                        $('#progresoConsulta').hide();
                        $('#consultarInfo').hide();
                        $('#confirmarConsulta').fadeIn();
                    } else if (respuesta.error) {
                        $('#intentos').val(respuesta.intentos);
                        $('#accionesNormal').hide();
                        $('#accionesError').fadeIn();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        } else {
            sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados.", "warning");
        }
    } else {
        sweetAlert("Antes de continuar...", "Selecciona primero el banco deseado", "warning");
    }
}

//Función para el control de reintentos
function reintentar() {
    console.log("Intento numero: " + $('#intentos').val());
    $('#accionesError').hide();
    $('#accionesNormal').fadeIn();
    consultarBancos();
}

//Verificar si la respuesta recibida está en formato JSON
function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}

function confirmarPaso4() {
    var depositoPromedio = $('#dep90').val();
    var retiroPromedio = $('#ret90').val();
    var saldoPromedio = $('#saldo90').val();
    if (depositoPromedio && retiroPromedio && saldoPromedio) {
        var depositos = $('input[name="depositoP"]:checked').val();
        var retiros = $('input[name="retiroP"]:checked').val();
        var saldo = $('input[name="saldoP"]:checked').val();
        if (depositos && retiros && saldo) {
            $('#dep90').prop('disabled', true);
            $('#ret90').prop('disabled', true);
            $('#saldo90').prop('disabled', true);
            $('input[name="depositoP"]').prop('disabled', true);
            $('input[name="retiroP"]').prop('disabled', true);
            $('input[name="saldoP"]').prop('disabled', true);
            $('#confirmacionPaso4').prop('disabled', true);
            $('#confirmacionPaso4').removeClass("colorGreen");
            $('#confirmacionPaso4').removeClass("colorWhite");
            $('#siguientePaso').prop('disabled', false);
            $('#siguientePaso').removeClass("rectangle250");
            $('#siguientePaso').removeClass("footerTextColor");
            $('#siguientePaso').addClass("rectangle250Green");
            $('#siguientePaso').addClass("colorWhite");
        } else {
            sweetAlert("Antes de continuar...", "Confirma los datos correspondientes a depósitos, retiros y saldo de los últimos 90 días", "warning");
        }
    } else {
        sweetAlert("Antes de continuar...", "Captura las cantidades solicitadas", "warning");
    }
}

//Función para cambiar el fondo de la opción seleccionada
function seleccionarRespuesta(divId, respuesta) {
    $('#' + divId + ' .correctaBoxGreen').addClass("correctaBox");
    $('#' + divId + ' .correctaBox').removeClass("correctaBoxGreen");
    $('#' + divId + ' p').addClass("lightGray");
    $('#' + divId + ' p').removeClass("colorWhite");
    $('#' + divId + respuesta).removeClass("correctaBox");
    $('#' + divId + respuesta).addClass("correctaBoxGreen");
    $('#' + divId + respuesta + " p").addClass("colorWhite");
    $('#' + divId + respuesta + " p").removeClass("lightGray");
    if (divId == 'tarjeta' && respuesta == 'Si') {
        $('#numeroTarjeta').prop('disabled', false);
    } else if (divId == 'tarjeta' && respuesta == 'No') {
        $('#numeroTarjeta').val('');
        $('#numeroTarjeta').prop('disabled', true);
    }
}

//Función para consultar el API del buró de crédito
function consultarBuro() {
    console.log("Validando seleccion de opciones...");
    var tarjeta = $('input[name="tCredito"]:checked').val();
    var hipoteca = $('input[name="creditoH"]:checked').val();
    var creditoAutomotriz = $('input[name="creditoA"]:checked').val();
    console.log("Validando llenado de campos...");
    if (tarjeta && hipoteca && creditoAutomotriz) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            console.log("Mostrando barra de progreso...");
            $('#progresoConsulta').show('slow');
            $.ajax({
                type: 'POST',
                data: 'tarjeta=' + tarjeta + "&numeroTarjeta=" + numeroTarjeta + "&hipoteca=" + hipoteca + "&creditoAutomotriz=" + creditoAutomotriz,
                url: '/kosmos-app/solicitud/consultarBuroDeCredito',
                success: function (data, textStatus) {
                    var respuesta = checkIfJson(data);
                    if (respuesta.status === 200) {
                        $('#progresoConsulta').hide('slow');
                        $('#accionesNormales').fadeOut('slow');
                        $('#accionesSuccess').fadeIn('slow');
                        $('#siguientePaso').prop('disabled', false);
                        $('#siguientePaso').removeClass("rectangle250");
                        $('#siguientePaso').removeClass("footerTextColor");
                        $('#siguientePaso').addClass("rectangle250Green");
                        $('#siguientePaso').addClass("colorWhite");
                    } else if (respuesta.error) {
                        $('#progresoConsulta').hide('slow');
                        $('#accionesNormales').fadeOut('slow');
                        $('#accionesError').fadeIn('slow');
                        $('#siguientePaso').prop('disabled', false);// Linea Temporal
                        $('#siguientePaso').removeClass("rectangle250");// Linea Temporal
                        $('#siguientePaso').removeClass("footerTextColor");// Linea Temporal
                        $('#siguientePaso').addClass("rectangle250Green");// Linea Temporal
                        $('#siguientePaso').addClass("colorWhite");// Linea Temporal
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    } else {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados.", "warning");
    }
}

function abrirModal(nombreModal) {
    $('#' + nombreModal).modal({
        fadeDuration: 300,
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function cerrarModal() {
    $.modal.close();
}

function avanzarPaso(paso) {
    cerrarModal();
    $.ajax({
        type: 'POST',
        data: 'siguientePaso=' + paso,
        url: '/kosmos-app/solicitud/cambiarPaso',
        success: function (data, textStatus) {
            $('#pasoActual').hide();
            $('#pasoActual').html(data);
            $('#pasoActual').fadeIn();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function avanzarPasoModal(paso, idModal, docto) {
    $('.headingColor').addClass("gray");
    $('.gray').removeClass("headingColor");
    $('#labelPaso' + paso).removeClass("gray");
    $('#labelPaso' + paso).addClass("headingColor");
    $('#paso' + (paso - 1) + idModal).hide();
    $('#paso' + paso + idModal).fadeIn();
    if (idModal === 'WebcamModalIdentificaciones') {
        $('#paso2ModalIdentificaciones').hide();
        inicializarCamara();
    } else if (paso === 3) {
        $('#paso2WebcamModalIdentificaciones').hide();
        $('#paso2WebcamModalIdentificaciones').html("");
        inicializarCamara();
    }
}

function inicializarCamara() {
    $("#webcam").html("");
    $('#webcam').photobooth().on("image", function (event, dataUrl) {
        $("#imagenCapturada").val(dataUrl);
        $("#webcam").html('<img src="' + dataUrl + '">');
        $('#repetirFoto').prop('disabled', false);
        $('#repetirFoto').removeClass("GrayButton");
        $('#repetirFoto').addClass("buttonOrange");
        $('#guardarFoto').prop('disabled', false);
        $('#guardarFoto').removeClass("GrayButton");
        $('#guardarFoto').addClass("colorGreen");
    });
    $('#repetirFoto').prop('disabled', true);
    $('#repetirFoto').removeClass("buttonOrange");
    $('#repetirFoto').addClass("GrayButton");
    $('#guardarFoto').prop('disabled', true);
    $('#guardarFoto').removeClass("colorGreen");
    $('#guardarFoto').addClass("GrayButton");
}

function guardarFoto() {
    $.ajax({
        type: 'POST',
        data: 'img_data=' + $("#imagenCapturada").val(),
        url: '/kosmos-app/solicitud/guardarFoto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.status === 200) {
                sweetAlert("!Enhorabuena¡", "La foto se subio correctamente", "success");
                avanzarPasoModal(3, 'ModalIdentificaciones', 'ife');
            } else {
                sweetAlert("Oops...", "Algo salió mal al subir la foto, intenta nuevamente en unos minutos.", "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function formatCurrency(n, currency) {
    return currency + " " + n.toFixed(2).replace(/./g, function (c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}

function activarCheck(check) {
    var valor = $('#' + check + 'Hdn').val();
    if (valor === 'NO') {
        $('#' + check).removeClass("whiteCheckBox");
        $('#' + check).addClass("colorGreen");
        $('#' + check).html("<i class='fa fa-check colorWhite paddingLeft2' aria-hidden='true'></i>");
        $('#' + check + 'Hdn').val('SI');
    } else {
        $('#' + check).removeClass("colorGreen");
        $('#' + check).addClass("whiteCheckBox");
        $('#' + check).html();
        $('#' + check + 'Hdn').val('NO');
    }
    verificarEnvioDeSolicitud();
}

function verificarEnvioDeSolicitud() {
    var terminosYCondiciones = $('#terminosHdn').val();
    var confidencialidad = $('#confidencialidadHdn').val();
    if (terminosYCondiciones === 'SI' && confidencialidad === 'SI') {
        $('#terminarSolicitud').prop('disabled', false);
        $('#terminarSolicitud').removeClass("GrayButton");
        $('#terminarSolicitud').removeClass("gray");
        $('#terminarSolicitud').addClass("blueButton");
        $('#terminarSolicitud').addClass("colorWhite");
    } else {
        $('#terminarSolicitud').prop('disabled', true);
        $('#terminarSolicitud').removeClass("blueButton");
        $('#terminarSolicitud').removeClass("colorWhite");
        $('#terminarSolicitud').addClass("GrayButton");
        $('#terminarSolicitud').addClass("gray");
    }
}
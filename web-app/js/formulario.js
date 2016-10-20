var winH;
var contentH;
var headerH;
var footerH;
var avancePorPaso = [0, 0, 0, 0, 0, 0, 0, 0];
$(document).ready(function () {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        console.log("Click fuera del modal");
        //$(this).parent().fadeOut();
        return false;
    });
    var pasoInicial = $('#pasoInicial').val();
    operacionesModal();
    stepBarHover();
    //submitNextPage();
    actualizarProgreso(pasoInicial);
    if (pasoInicial === "1" || pasoInicial === "2" || pasoInicial === "3") {
        if (pasoInicial === "1") {
            operacionesLogin();
        }
        operacionesPaso1al3();
    } else if (pasoInicial === "4") {
        operacionesPaso4();
    } else if (pasoInicial === "5") {
        operacionesPaso5();
    } else if (pasoInicial === "6") {
        operacionesPaso6();
    } else if (pasoInicial === "0") {
        operacionesLogin();
    }
    $('.showOnFill').each(function (index) {
        verificarCambios(index);
    });
});

function operacionesLogin() {
    $('#loginIdOf').click(function () {
        openModal('identification_oficial');
    });

    $('#loginCompDom').click(function () {
        openModal('comprobante_domicilio');
    });
}


function stepBarHover() {
    $(".grayCircle").hover(function () {
        if ($(this).hasClass("grayCircle")) {
            $(this).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
            $(this).addClass('nextBtn');
            $(this).addClass('sendBtn');
            $(this).children('p').addClass('paddingTop10');
            $(this).children('p').removeClass('paddingTop5');
            $(this).children('p').html("IR AL PASO " + $(this).children('p').text());
        }
    }, function () {
        if ($(this).hasClass("grayCircle")) {
            $(this).animate({width: "36px", height: "36px", marginTop: "5px"}, {queue: false});
            $(this).removeClass('nextBtn');
            $(this).removeClass('sendBtn');
            $(this).children('p').addClass('paddingTop5');
            $(this).children('p').removeClass('paddingTop10');
            $(this).children('p').html($(this).children('p').text().replace("IR AL PASO ", ""));
        }
    });
    $('#circuloPaso1').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("1");
        }
    });
    $('#circuloPaso2').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("2");
        }
    });
    $('#circuloPaso3').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("3");
        }
    });
    $('#circuloPaso4').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("4");
        }
    });
    $('#circuloPaso5').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("5");
        }
    });
    $('#circuloPaso6').click(function () {
        if ($(this).hasClass("grayCircle")) {
            showValues();
            avanzarPaso("6");
        }
    });
}

function operacionesPaso1al3() {
    /****FORMULARIOS 1 A 3 *******/
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        //$(this).parent().fadeOut();
        return false;
    });

    $('.showOnFill').each(function (index) {
        var thisStep = $(this);
        var maxIndex = $('.showOnFill').length;
        console.log("Indice: " + index);
        $('.formValues', this).change(function () {
            console.log("Registrando: " + $(this).val());
            if ($(this).val() !== '') {
                console.log("No esta vacio");
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
                console.log("--->" + $(this).attr('id'));
                if ($(this).attr('id') === 'nombre') {
                    console.log("Si entraaaaaaaa");
                    $('#nombreCliente').html('¡Hola! ' + $(this).val());
                }
            } else {
                console.log("Si esta vacio");
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }
            verificarCambios(index);
            console.log("A punto de calcular el avance....");
            calcularAvance();
        });
        $('.formValues', this).focusout(function () {
            verificarCambios(index);
        });
    });

    $('.formStep .confirmDiv .buttonM').click(function () {

        if ($(this).parent().parent().is(':hidden') === true) {
            console.log("Pos que esta oculto!!!");
        } else {
            if ($(this).parent().parent().hasClass('lastStep') === true) {
                console.log("Preparando el armado del form...");
                //$(".formValues").on("change", showValues);
                //showValues();
                $('.nextBtn').addClass('sendBtn');
                //submitNextPage();
                var currentStep = $('#pasoAnterior').val();
                $('#circuloPaso' + (parseInt(currentStep) + 1)).click();
            } else {
                $(this).parent().parent().slideUp();
                $(this).parent().parent().next('.formStep').slideDown();

            }
            $('.successBubble').fadeOut();
        }
    });

    $('#paso2CompDom').click(function () {
        $('#tipoDeDocumento').val('UtilityBill');
        openModal('comprobante_domicilio');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
    });

    var bestPictures = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        prefetch: '/kosmos-app/solicitud/buscarCodigoPostal',
        remote: {
            url: '/kosmos-app/solicitud/buscarCodigoPostal?query=%QUERY',
            wildcard: '%QUERY'
        }
    });
    $('#cpRemote .typeahead').typeahead({minLength: 3}, {
        name: 'codigos',
        display: 'value',
        source: bestPictures,
        limit: 5,
        templates: {
            empty: [
                '<div class="empty-message">',
                'No hay coincidencias.',
                '</div>'
            ].join('\n')
        }
    });
    $('#cpRemote .typeahead').bind('typeahead:select', function (ev, suggestion) {
        consultarCodigoPostal(suggestion);
    });

    //submitNextPage();
}

function verificarCambios(index) {
    var maxIndex = $('.showOnFill').length;
    var filledLength = $('.notEmpty:visible').length;
    var thisLength = $('.formValues:visible').length;
    var prellenado = $('#pasoPrellenado').val();
    var pasoActual = $('#pasoAnterior').val();
    console.log("Index: " + index + " - not Empty: " + filledLength + " -  total: " + thisLength + " - prellenado: " + prellenado);

    if (filledLength === thisLength) {
        if ((index + 1) < maxIndex) {
            $('.showOnFill').eq(index + 1).fadeIn();
            $('.showOnFill').eq(index + 1).css({'display': 'inline'});
            checkInputs();
        } else {
            //console.log("Else 1");
        }
    } else if (prellenado === "true") {
        $('.showOnFill').eq(index + 1).fadeIn();
        $('.showOnFill').eq(index + 1).css({'display': 'inline'});
        checkInputs();
    }
    var totalLength = $('.formStep:visible .formValues').length;
    //console.log("totalLength: " + totalLength);
    if (filledLength === totalLength) {
        if (prellenado === "true") {
            console.log("Mostrar el boton de confirmacion");
            $('.formStep:visible .confirmDiv').fadeIn();
        } else {
            console.log("Hacer click en el boton de confirmacion");
            $('.formStep:visible .confirmDiv .buttonM').click();
        }
        if (pasoActual === "1" && index === 6) {
            generarClaves();
        }
    }
}

function calcularAvance() {
    var percentStep;
    var avanceTotal = 0;
    var totalDeCampos = $('.formValues').length;
    var camposLlenos = $('.notEmpty').length;
    var porcentajePorPaso = 0;
    var pasoActual = $('#pasoAnterior').val();
    if (pasoActual === "1") {
        percentStep = 25;
    } else if (pasoActual === "2") {
        percentStep = 20;
    } else if (pasoActual === "3") {
        percentStep = 20;
    } else if (pasoActual === "4") {
        percentStep = 25;
    } else if (pasoActual === "5") {
        percentStep = 9;
    } else if (pasoActual === "6") {
        percentStep = 1;
    }
    if (pasoActual === "1" || pasoActual === "2" || pasoActual === "3") {
        porcentajePorPaso = camposLlenos / totalDeCampos;
        porcentajePorPaso = porcentajePorPaso * percentStep;
        porcentajePorPaso = porcentajePorPaso.toFixed();
        avancePorPaso[Number(pasoActual) - 1] = porcentajePorPaso;
    }
    console.log("Paso Actual: " + pasoActual);
    for (i = 0; i < 6; i++) {
        console.log("Avance Paso " + (i + 1) + ": " + avancePorPaso[i]);
        avanceTotal += Number(avancePorPaso[i]);
    }
    console.log("Resultado del calculo: totalDeCampos: " + totalDeCampos + " -- camposLlenos: " + camposLlenos + " -- porcentajeCalculado: " + porcentajePorPaso + "  -- avanceTotal: " + avanceTotal);
    $('.progressPerc').html(avanceTotal + '%');
    $('.activeProgress').animate({width: avanceTotal + '%'}, {queue: false});
}

function consultarCodigoPostal(sugerencia) {
    var respuesta = eval(sugerencia);
    var idCodigo = respuesta.id;
    $.ajax({
        type: 'POST',
        data: 'idCodigoPostal=' + idCodigo,
        url: '/kosmos-app/solicitud/consultarCodigoPostal',
        success: function (data, textStatus) {
            var response = eval(data)
            $('#municipio').append($('<option>', {
                value: response.municipio.id,
                text: response.municipio.nombre,
                selected: true
            }));
            $('#estado').append($('<option>', {
                value: response.estado.id,
                text: response.estado.nombre,
                selected: true
            }));
            $('.typeahead').addClass('notEmpty');
            $('.typeahead').addClass('headingColor');
            $('#municipio').addClass('notEmpty');
            $('#municipio').addClass('headingColor');
            $('#estado').addClass('notEmpty');
            $('#estado').addClass('headingColor');
            $('.showOnFill').each(function (index) {
                verificarCambios(index);
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function operacionesPaso4() {
    /***** BANK BUTTONS HOVER ******/
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        $(this).parent().fadeOut();
    });

    $(".bankButton").mouseover(function () {
        console.log("pasando por la imagen");
        var hoverImage = $('img', this).data('hover');
        $('img', this).attr('src', '/kosmos-app/images/' + hoverImage + '_white.png');
    });

    $(".bankButton").mouseout(function () {
        console.log("quitando el mouse de la imagen");
        if ($(this).hasClass('active_green') === false) {
            var hoverImage = $('img', this).data('hover');
            $('img', this).attr('src', '/kosmos-app/images/' + hoverImage + '.png');
        }
    });

    $('.brandingBox').click(function () {
        var thisIndex = $(this).index();
        var bankChoice = $('img', this).data('hover');
        console.log("dando click en uno de los botones " + bankChoice);
        $('.brandingBox').removeClass('active_green');
        $(".bankButton").each(function (index) {
            if (index !== thisIndex) {
                var hoverImage = $('img', this).data('hover');
                $('img', this).attr('src', '/kosmos-app/images/' + hoverImage + '.png');
            }
        });
        if ($(this).hasClass('bankButton') === true) {
            var thisSrc = $('img', this).attr('src');
            $('img', this).attr('src', thisSrc);
        }
        $(this).addClass('active_green');

        $('.formLoginBank').removeClass('hide').delay(1000).addClass('animated flipInX');

        if (bankChoice === "banamex") {
            fixLoginView(6);
            $('.messageLoginBank').text("NÚMERO DE CLIENTE");
            $('.messagePasswordBank').text("CLAVE DE ACCESO");
        } else if (bankChoice === "bancomer") {
            fixLoginView(6);
            $('.messageLoginBank').text("TARJETA");
            $('.messagePasswordBank').text("CONTRASEÑA");
        } else if (bankChoice === "hsbc") {
            fixLoginView(3);
            $('.messageLoginBank').text("USUARIO");
            $('.messagePasswordBank').text("PASSWORD");
            $('.messageSelectBank').text("AUTENTIFICACIÓN");
            $('.messageMemorable').text("FECHA MEMORABLE");
        } else if (bankChoice === "santander") {
            fixLoginView(6);
            $('.messageLoginBank').text("USUARIO");
            $('.messagePasswordBank').text("CLAVE");
        } else if (bankChoice === "banorte") {
            fixLoginView(6);
            $('.messageLoginBank').text("USUARIO");
            $('.messagePasswordBank').text("CONTRASEÑA");
        }
        $('.bankChoice').val(bankChoice);
        $('.bankChoice').addClass('notEmpty');
    });
    function fixLoginView(tipo) {
        if (tipo === 6) {
            $('.messageLoginBankDiv').removeClass('col3');
            $('.messageLoginBankDiv').removeClass('col3-tab');
            $('.messageLoginBankDiv').removeClass('col3-mob');
            $('.messageLoginBankDiv').addClass('col6');
            $('.messageLoginBankDiv').addClass('col6-tab');
            $('.messageLoginBankDiv').addClass('col6-mob');

            $('.messagePasswordBankDiv').removeClass('col3');
            $('.messagePasswordBankDiv').removeClass('col3-tab');
            $('.messagePasswordBankDiv').removeClass('col3-mob');
            $('.messagePasswordBankDiv').addClass('col6');
            $('.messagePasswordBankDiv').addClass('col6-tab');
            $('.messagePasswordBankDiv').addClass('col6-mob');

            $('.loginMethodHsbc').fadeOut();

        }
        if (tipo === 3) {
            $('.messageLoginBankDiv').removeClass('col6');
            $('.messageLoginBankDiv').removeClass('col6-tab');
            $('.messageLoginBankDiv').removeClass('col6-mob');
            $('.messageLoginBankDiv').addClass('col3');
            $('.messageLoginBankDiv').addClass('col3-tab');
            $('.messageLoginBankDiv').addClass('col3-mob');

            $('.messagePasswordBankDiv').removeClass('col6');
            $('.messagePasswordBankDiv').removeClass('col6-tab');
            $('.messagePasswordBankDiv').removeClass('col6-mob');
            $('.messagePasswordBankDiv').addClass('col3');
            $('.messagePasswordBankDiv').addClass('col3-tab');
            $('.messagePasswordBankDiv').addClass('col3-mob');

            //$('.messageLoginBankDiv').delay( 2 ).fadeOut().fadeIn();
            //$('.messagePasswordBankDiv').delay( 2 ).fadeOut().fadeIn();
            $('.loginMethodHsbc').delay(2).fadeOut().fadeIn();
            validarFechaMemorable($('#login_method').val());
        }
    }
    $('#login_method').on('click', function () {
        validarFechaMemorable($(this).val());
    });


    function validarFechaMemorable(tipo) {
        if (tipo == "Con OTP") {
            // Disable #x
            $('#memorable').attr('disabled', 'disabled');
            $('.messageMemorableDiv').fadeOut();
        } else {
            // Enable #x
            $('#memorable').removeAttr('disabled');
            $('.messageMemorableDiv').fadeIn();
        }
    }
    $('.consultarBox').click(function () {

        if (!$(this).hasClass('exito')) {
            //consultarBancos();
            authenticate();
        }
    });

    $('#reintentarBtn').click(function () {
        reintentar();
    });

    $('#subirEstadosDeCuentaBtn').click(function () {
        $('#consultaBancaria').fadeOut();
        $('#recibosUpload').fadeIn();
    });

    $(".bankStep .formValues").each(function (index) {
        $(this).change(function () {
            var thisInputvalue = $(this).val();
            if (thisInputvalue != "") {
                $(this).addClass('notEmpty');
            } else {
                $(this).removeClass('notEmpty');
            }
        });

    });


    $(".confirmInfo").each(function (index) {
        thisParent = $(this);
        no_confimations = $(".confirmInfo").length;

        $('.correctaBox', this).click(function () {
            $(this).parent().children('.correctaBox').removeClass('active_green');
            $(this).addClass('active_green');
            no_active = $('.correctaBox.active_green').length;

            if (no_active == no_confimations) {
                $('.confirmDb').fadeIn();
            }

        });
    });

    $(".ccInfo").each(function (index) {
        thisParent = $(this);
        no_confimations = $(".ccInfo").length;

        $('.correctaBox', this).click(function () {
            $(this).parent().children('.correctaBox').removeClass('active_green');
            $(this).addClass('active_green');
            no_active = $('.correctaBox.active_green').length;
            parentIndex = $(this).parent().index(".ccInfo");

            if (parentIndex == 0) {

                if ($(this).hasClass('hasCc') == true) {
                    $('.inPuts4a').addClass('formValues');
                    $('.inPuts4a').removeAttr("disabled");
                } else {
                    $('.inPuts4a').removeClass('formValues');
                    $('.inPuts4a').attr('disabled', true);
                    $('.inPuts4a').val('');
                }

            }
        });
    });


    $('.confirmDb').click(function () {
        //showValues();
        console.log("Dando click en confirmDb");
        $('.nextBtn').addClass('sendBtn');
        var currentStep = $('#pasoAnterior').val();
        $('#circuloPaso' + (parseInt(currentStep) + 1)).click();

        //submitNextPage();
    });

    $('#saldo_correcto_si').click(function () {
        $('#saldoCorrecto').val("SI");
    });
    $('#saldo_correcto_no').click(function () {
        $('#saldoCorrecto').val("NO");
    });
    $('#retiro_correcto_si').click(function () {
        $('#retiroCorrecto').val("SI");
    });
    $('#retiro_correcto_no').click(function () {
        $('#retiroCorrecto').val("NO");
    });
    $('#deposito_correcto_si').click(function () {
        $('#depositoCorrecto').val("SI");
    });
    $('#deposito_correcto_no').click(function () {
        $('#depositoCorrecto').val("NO");
    });

}

function resumeCountdown() {
    var $clock = $('#clock');
    $clock.countdown('resume');
}

function resetCountdown() {
    var $clock = $('#clock');
    $clock.countdown(get2MinutesFromNow());
}



function validarConsultaBuroCredito() {
    var reporteBuro = $('#reporteBuroCredito').val();
    var errorConsulta = $('#errorConsulta').val();
    if (reporteBuro != "") {
        if (errorConsulta == "") {
            $('#accionesNormales').fadeOut();
            $('#accionesSuccess').fadeIn();
            $('#divAutorizacionBuro').fadeOut();
        } else if (errorConsulta.indexOf("ERR") >= 0) {
            $('#accionesNormales').fadeOut();
            $('#accionesError').fadeIn();
            $('#divAutorizacionBuro').fadeOut();
            setCountdown()
            resetCountdown()
            resumeCountdown();
        }
    }
}

function get2MinutesFromNow() {
    return new Date(new Date().valueOf() + 2 * 60 * 1000);
}

function setCountdown() {
    var $clock = $('#clock');
    $clock.countdown(get2MinutesFromNow(), function (event) {
        $(this).html(event.strftime('%M:%S'));
    });

}

function operacionesPaso5() {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();
    $("#fechaAutorizacionConsulta").text(GetTodayDate());
    $("#divAutorizacionBuro").fadeIn();

    validarConsultaBuroCredito();
    setCountdown()


    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        $(this).parent().fadeOut();
    });

    $(".ccInfo").each(function (index) {
        thisParent = $(this);
        no_confimations = $(".ccInfo").length;

        console.log("Indice: " + index)

        $('.correctaBox', this).click(function () {
            $(this).parent().children('.correctaBox').removeClass('active_green');
            $(this).addClass('active_green');
            no_active = $('.correctaBox.active_green').length;
            parentIndex = $(this).parent().index(".ccInfo");
            console.log("----> " + parentIndex);
            if (parentIndex === 0) {
                if ($(this).hasClass('hasCc') === true) {
                    $('.inPuts4a').addClass('formValues');
                    $('.inPuts4a').removeAttr("disabled");
                    $('#tCredito').val('SI');
                } else {
                    $('.inPuts4a').removeClass('formValues');
                    $('.inPuts4a').attr('disabled', true);
                    $('.inPuts4a').val('');
                    $('#tCredito').val('NO');
                }

            } else if (parentIndex === 1) {
                if ($(this).hasClass('hasCc') === true) {
                    $('#creditoH').val('SI');
                } else {
                    $('#creditoH').val('NO');
                }

            } else if (parentIndex === 2) {
                if ($(this).hasClass('hasCc') === true) {
                    $('#creditoA').val('SI');
                } else {
                    $('#creditoA').val('NO');
                }

            }
        });
    });


    $('#tarjeta_correcto_si').click(function () {
        $('#tCredito').val("SI");
        $('.inPuts4a').removeAttr("disabled");
    });
    $('#tarjeta_correcto_no').click(function () {
        $('#tCredito').val("NO");
    });
    $('#hipotecario_correcto_si').click(function () {
        $('#creditoH').val("SI");
    });
    $('#hipotecario_correcto_no').click(function () {
        $('#creditoH').val("NO");
    });
    $('#automotriz_correcto_si').click(function () {
        $('#creditoA').val("SI");
    });
    $('#automotriz_correcto_no').click(function () {
        $('#creditoA').val("NO");
    });
    //@Deprecated
    $('.marcoLegalCorrectaBox_SI').click(function () {
        $('#autorizacionForm').fadeOut();
        $('#autorizacionLoading').delay(600).addClass('animated bounceIn').fadeIn();
        consultarBuro();
    });

    $('.marcoLegalCorrectaBox_NO').click(function () {
        cerrarModal();
    });

    $('#consultarBuroBtn').click(function () {
        validarAutorizacion();
        //$("#fechaAutorizacionConsulta").text(GetTodayDate());
    });

    $('.avanzaBtn').click(function () {
        if ($(this).hasClass('colorGreen') || $(this).hasClass('buttonOrange')) {
            showValues();
            avanzarPaso("6");
        }
    });
}

function GetTodayDate() {

    var m_names = new Array("Enero", "Febrero", "Marzo",
            "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre");

    var tdate = new Date();
    var dd = tdate.getDate(); //Dia
    var MM = tdate.getMonth(); //Mes
    var yyyy = tdate.getFullYear(); //Año
    var fecha = dd + " / " + (MM + 1) + " / " + yyyy;
    var fechaD = dd + " de  " + m_names[(MM)] + " de " + yyyy;
    return fechaD;
}
function operacionesPaso6() {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        $(this).parent().fadeOut();
    });
    /************* FORM 6 *****************/

    $(".checkBox").each(function (index) {

        $(this).click(function () {
            $(this).toggleClass("colorGreen");
            $('.fa', this).toggleClass("hide");

            var no_checkboxes = $(".checkBox").length;
            var no_checked = $(".checkBox.colorGreen").length;

            if (no_checkboxes == no_checked) {
                $('.enviarSolicitud').addClass('blueButton pointer');
            } else {
                $('.enviarSolicitud').removeClass('blueButton pointer');
            }

        });
    });


    $('.enviarSolicitud').click(function () {
        showValues();
        $('.enviarSolicitud').addClass('sendBtn');
        //submitNextPage();
    });

    $('#paso6IdOf').click(function () {
        if ($(this).hasClass("darkGray")) {
            openModal('identification_oficial');
            //$('#tipoDeDocumento').val('Identicaciones');
            //inicializarDropzone('div#divDropzoneIds', '#subirIdentificacion');
            //$(this).addClass('colorGreen');
        }
    });

    $('#paso6CompDom').click(function () {
        if ($(this).hasClass("darkGray")) {
            //$('#tipoDeDocumento').val('UtilityBill');
            openModal('comprobante_domicilio');
            //inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
            //$(this).addClass('colorGreen')
        }
    });

    $('#terminarSolicitud').click(function () {
        if ($(this).hasClass("blueButton")) {
            avanzarPaso("8");
        }
    });

    $('.solicitud_modal').click(function () {
        if ($(this).hasClass('blueButton') == true) {
            $('#resumen_solicitud').fadeIn();
        }
    });

}

function showValues() {
    var allInputs = $(".formValues").serializeArray();
    //$(".sendValues").html('');
    $.each(allInputs, function (i, field) {
        console.log("Añadiendo: " + field.name);
        console.log("Ya existe? " + $('#formPaso input[name=' + field.name + ']').val());
        console.log("Es indefinido? " + ($('#formPaso input[name=' + field.name + ']').val() === undefined));
        if ($('#formPaso input[name=' + field.name + ']').val() === undefined) {
            $(".sendValues").append('<input type="hidden" name="' + field.name + '" value="' + field.value + '" />');
        }
    });
}

/*function submitNextPage() {
 $('.grayCircle').click(function () {
 //$('.sendValues').submit();
 avanzarPaso();
 });
 }*/

function loadBar() {
    $('.loadingBar').fadeIn();
    $(".loadingActive").animate({width: "100%"}, 800);
}
function loadBar(percentage) {
    $('.loadingBar').fadeIn();
    $(".loadingActive").animate({width: percentage}, 800);
}

function restartLoadBar() {
    $('.loadingBar').fadeIn();
    $('.loadingActive').css({'width': '0%'});
}

function checkInputs() {
    var totalLength = $('.formStep:visible .formValues').length;
    var filledLength = $('.notEmpty:visible').length;
    var prellenado = $('#pasoPrellenado').val();

    if (filledLength === totalLength) {
        if (prellenado === "true") {
            $('.formStep:visible .confirmDiv').fadeIn();
        }
    }
}

function openSelect(elem) {
    if (document.createEvent) {
        var e = document.createEvent("MouseEvents");
        e.initMouseEvent("mousedown", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        elem[0].dispatchEvent(e);
    } else if (element.fireEvent) {
        elem[0].fireEvent("onmousedown");
    }
}

$(window).resize(function () {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();
})


function setFooter() {
    if ((headerH + contentH) >= (winH - footerH)) {
        $('.footerContainer').css({
            top: (headerH + contentH),
            bottom: 'auto'
        });
    } else {
        $('.footerContainer').css({
            bottom: 0,
            top: 'auto'
        });
    }
}

// ***************************** Inicio de Funciones Auxiliares


function avanzarPaso(paso) {
    //cerrarModal();
    var paso = paso;
    $('#siguientePaso').val(paso);
    console.log("Avanzando a paso " + paso);
    //alert("Datos::"+$('.sendValues').serialize());
    $.ajax({
        type: 'POST',
        data: $('.sendValues').serialize(),
        url: '/kosmos-app/solicitud/cambiarPaso',
        success: function (data, textStatus) {
            $('#pasoActual').hide();
            $('#pasoActual').html(data);
            $('#pasoActual').fadeIn();
            if (paso === "1" || paso === "2" || paso === "3") {
                console.log("Cargando funciones de paso " + paso);
                operacionesPaso1al3();
                $('.showOnFill').each(function (index) {
                    verificarCambios(index);
                });
            } else if (paso === "4") {
                console.log("Cargando funciones de paso 4");
                operacionesPaso4();
                verificacionSubPaso4();
            } else if (paso === "5") {
                console.log("Cargando funciones de paso 5");
                operacionesPaso5();
                verificacionSubPaso5();
            } else if (paso === "6") {
                console.log("Cargando funciones de paso 6");
                operacionesPaso6();
            } else {
                console.log("Paso desconocido " + paso);
            }
            setFooter();
            actualizarProgreso(paso);
            stepBarHover();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function verificacionSubPaso5() {
    if ($("#tCredito").val() === "SI") {
        $('#tarjeta_correcto_si').addClass('active_green');
        $('.inPuts4a').removeAttr("disabled");
    }
    if ($("#tCredito").val() === "NO") {
        $('#tarjeta_correcto_no').addClass('active_green');
    }
    if ($("#creditoH").val() === "SI") {
        $('#hipotecario_correcto_si').addClass('active_green');
    }
    if ($("#creditoH").val() === "NO") {
        $('#hipotecario_correcto_no').addClass('active_green');
    }
    if ($("#creditoA").val() === "SI") {
        $('#automotriz_correcto_si').addClass('active_green');
    }
    if ($("#creditoA").val() === "NO") {
        $('#automotriz_correcto_no').addClass('active_green');
    }
}



function verificacionSubPaso4() {
    console.log("login_id:" + $("#login_id").val());
    if ($("#login_id").val() != "") {
        $('.loadingActive').hide();
        $('#consultarInfo').hide();
        $('.defaultBubble').fadeOut();
        $('.successBubble').fadeIn();
        $('#confirmarConsulta').fadeIn();
    }
    if ($("#depositoCorrecto").val() && $("#retiroCorrecto").val() && $("#saldoCorrecto").val()) {
        $('.confirmDb').removeClass('hide');
    }
    if ($("#depositoCorrecto").val() === 'SI') {
        $('#deposito_correcto_si').addClass('active_green');
    }
    if ($("#depositoCorrecto").val() === 'NO') {
        $('#deposito_correcto_no').addClass('active_green');
    }
    if ($("#retiroCorrecto").val() === "SI") {
        $('#retiro_correcto_si').addClass('active_green');
    }
    if ($("#retiroCorrecto").val() === "NO") {
        $('#retiro_correcto_no').addClass('active_green');
    }
    if ($("#saldoCorrecto").val() === "SI") {
        $('#saldo_correcto_si').addClass('active_green');
    }
    if ($("#saldoCorrecto").val() === "NO") {
        $('#saldo_correcto_no').addClass('active_green');
    }
}

function actualizarProgreso(paso) {
    console.log("Actualizando progreso paso " + paso);
    $('.pasoTitle').html('paso ' + paso);
    $('#circuloPaso' + paso).removeClass("grayCircle");
    $('#circuloPaso' + paso).addClass("blueCircle");
    $('#circuloPaso' + paso).addClass("colorWhite");
    $('#circuloPaso' + paso).prop('disabled', true);
    $('#pPaso' + paso).removeClass("paddingTop5");
    $('#pPaso' + paso).removeClass("footerTextColor");
    $('#pPaso' + paso).addClass("paddingTop10");
    if (paso === "1") {
        $('.stepTitle').html('Datos Generales');
    } else if (paso === "2") {
        $('.stepTitle').html('Vivienda');
    } else if (paso === "3") {
        $('.stepTitle').html('Empleo');
    } else if (paso === "4") {
        $('.stepTitle').html('Datos Bancarios');
    } else if (paso === "5") {
        $('.stepTitle').html('Historial Crediticio');
        avancePorPaso[3] = 25;
        console.log("Si lo registro? " + avancePorPaso[3]);
    } else if (paso === "6") {
        $('.stepTitle').html('Documentos');
        avancePorPaso[4] = 9;
        console.log("Si lo registro? " + avancePorPaso[4]);
    } else if (paso === "7") {
        $('.stepTitle').html('Datos Generales');
    } else if (paso === "8") {
        avancePorPaso[5] = 1;
        console.log("Si lo registro? " + avancePorPaso[5]);
        $('.pasoTitle').html('');
        $('.stepTitle').html('Tu Crédito');
    }
    calcularAvance();
}

function fillFB(data) {
    console.log("Regresa de FB: " + data);
    var json = JSON.parse(data);
    var x = 0;
    if (json["picture"]) {
        $("#imagenUsuario").html("<img class='userPicture floatLeft' src='" + json["picture"].data.url + "'/>");
        x++;
    }
    if (json["gender"]) {
        var sexo = (json["gender"] === "male" ? 1 : (json["gender"] === "female" ? 2 : 0));
        $("#sexo").val(sexo);
        $("#sexo").addClass('notEmpty');
        $("#sexo").addClass('headingColor');
        x++;
    }
    if (json["relationship_status"]) {
        var estadoCivil = (json["relationship_status"] === "single" ? 1 : (json["relationship_status"] === "married" ? 2 : 0));
        $("#estadoCivil").val(estadoCivil);
        $("#estadoCivil").addClass('notEmpty');
        $("#estadoCivil").addClass('headingColor');
        x++;
    }
    if (json["first_name"]) {
        $("#nombre").val(json["first_name"]);
        $("#nombre").addClass('notEmpty');
        $("#nombre").addClass('headingColor');
        x++;
    }
    if (json["last_name"]) {
        $("#apellidoPaterno").val(json["last_name"]);
        $("#apellidoPaterno").addClass('notEmpty');
        $("#apellidoPaterno").addClass('headingColor');
        x++;
    }
    if (json["birthday"]) {
        if (json["birthday"].substring(0, 2)) {
            $("#mes").val(json["birthday"].substring(0, 2));
            $("#mes").addClass('notEmpty');
            $("#mes").addClass('headingColor');
            x++;
        }
        if (json["birthday"].substring(3, 5)) {
            $("#dia").val(json["birthday"].substring(3, 5));
            $("#dia").addClass('notEmpty');
            $("#dia").addClass('headingColor');
            x++;
        }
        if (json["birthday"].substring(6, 12)) {
            $("#anio").val(json["birthday"].substring(6, 12));
            $("#anio").addClass('notEmpty');
            $("#anio").addClass('headingColor');
            x++;
        }
    }
    if (x > 0) {
        $('#pasoPrellenado').val("true");
        $('#nombreCliente').html('¡ Hola ' + json["first_name"] + ' !');
        $('.defaultBubble').fadeOut();
        $('.successBubble').fadeIn();
        $('.showOnFill').each(function (index) {
            verificarCambios(index);
        });
    }
}

function fillGoogle(data) {
    console.log("Regresa de Google: " + data);
    var json = JSON.parse(data);
    var x = 0;
    if (json["Eea"]) { //Id
        console.log("Id Google: " + json["Eea"]);
    }
    if (json["ofa"]) { //Nombre
        $("#nombre").val(json["ofa"]);
        $("#nombre").addClass('notEmpty');
        $("#nombre").addClass('headingColor');
        x++;
    }
    if (json["wea"]) { //Apellidos
        $("#apellidoPaterno").val(json["wea"]);
        $("#apellidoPaterno").addClass('notEmpty');
        $("#apellidoPaterno").addClass('headingColor');
        x++;
    }
    if (json["Paa"]) { //Imagen
        $("#imagenUsuario").html("<img class='userPicture floatLeft' src='" + json["Paa"] + "' />");
        x++;
    }
    if (json["U3"]) { //correo
        console.log("Email: " + json["U3"]);
    }
    if (x > 0) {
        $('#pasoPrellenado').val("true");
        $('#nombreCliente').html('¡ Hola ' + json["ofa"] + ' !');
        $('.defaultBubble').fadeOut();
        $('.successBubble').fadeIn();
        $('.showOnFill').each(function (index) {
            verificarCambios(index);
        });
    }
}

//Callback para hacer la consulta con el API Bancaria
function loginInteractive() {
    loading($('.bankChoice').val());
    $('#loginInteractiveHtml').fadeOut();
    var data = {};
    $("#formLoginInteractive").find("input, select").each(function (i, field) {
        data[field.name] = field.value;
    });
    console.log("PUT Interactive Mode....");
    $.ajax({
        type: 'POST',
        data: 'data=' + JSON.stringify(data),
        url: '/kosmos-app/solicitud/loginInteractive',
        success: function (data, textStatus) {
            var respuesta = checkIfJson(data);
            if ('error_class' in respuesta) {
                cerrarModal();
                sweetAlert("Oops...", "Parece que hubo un error :" + respuesta.error_class, "error");
            } else {
                cerrarModal();
                $("#spinner").html("");
                var index = 0;
                $.each(respuesta.accounts_resume, function (i, item) {
                    if (respuesta.accounts_resume[i].select) {
                        index = respuesta.accounts_resume[i].select;
                    }
                    /*if (respuesta.accounts_resume[i].depositoPromedio > 0) {
                     index = i;
                     }*/
                });
                $('#dep90').val(formatCurrency(respuesta.accounts_resume[index].depositoPromedio, "$"));
                $('#ret90').val(formatCurrency(respuesta.accounts_resume[index].retiroPromedio, "$"));
                $('#saldo90').val(formatCurrency(respuesta.accounts_resume[index].saldoPromedio, "$"));
                $('#login_id').val(respuesta.login_id);
                $('.loadingActive').hide();
                $('#consultarInfo').hide();
                $('.defaultBubble').fadeOut();
                $('.successBubble').fadeIn();
                $('#confirmarConsulta').fadeIn();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            cerrarModal();
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

//Callback para hacer la consulta con el API Bancaria
function authenticate() {
    loading($('.bankChoice').val());
    var data = {};
    data["password"] = $('#password').val();
    data["login"] = $('#login').val();
    data["provider_code"] = $('.bankChoice').val();
    data["customer_id"] = $('#customer_id').val();
    data["login_method"] = $('#login_method').val();
    data["memorable"] = $('#memorable').val();
    console.log("Authenticating....");
    $('#loginInteractiveHtml').fadeIn();
    $.ajax({
        type: 'POST',
        data: 'data=' + JSON.stringify(data),
        url: '/kosmos-app/solicitud/authenticate',
        success: function (data, textStatus) {
            var respuesta = checkIfJson(data);
            if ('error_class' in respuesta) {
                cerrarModal();
                sweetAlert("Oops...", "Parece que hubo un error :" + respuesta.error_class, "error");
            } else if ('interactiveFieldsName' in respuesta) {
                $("#spinner").html("");
                $('#loginInteractiveHtml').html(buildLoginBank(respuesta));
            } else {
                cerrarModal();
                var index = 0;
                $.each(respuesta.accounts_resume, function (i, item) {
                    if (respuesta.accounts_resume[i].select) {
                        index = respuesta.accounts_resume[i].select;
                    }
                    /*
                     if (respuesta.accounts_resume[i].saldoPromedio > 0 && (respuesta.accounts_resume[i].nature == "account" || respuesta.accounts_resume[i].nature == "checking")) {
                     index = i;
                     }
                     */
                });
                console.log("DATOS::" + respuesta.accounts_resume[index].depositoPromedio);
                $('#dep90').val(formatCurrency(respuesta.accounts_resume[index].depositoPromedio, "$"));
                $('#ret90').val(formatCurrency(respuesta.accounts_resume[index].retiroPromedio, "$"));
                $('#saldo90').val(formatCurrency(respuesta.accounts_resume[index].saldoPromedio, "$"));
                $('#login_id').val(respuesta.login_id);
                $('.loadingActive').hide();
                $('#consultarInfo').hide();
                $('.defaultBubble').fadeOut();
                $('.successBubble').fadeIn();
                $('#confirmarConsulta').fadeIn();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            cerrarModal();
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function generarClaves() {
    console.log("A por la generacion de llaves!!!");
    var curp = generaCurp({
        nombre: $('#nombre').val(),
        apellido_paterno: $('#apellidoPaterno').val(),
        apellido_materno: $('#apellidoMaterno').val(),
        sexo: 'H',
        estado: 'DF',
        fecha_nacimiento: [$('#dia').val(), $('#mes').val(), $('#anio').val()]
    });
    $('#curp').val(curp);
    $('#rfc').val(curp.substring(0, 10));
    $("#curp").addClass('notEmpty');
    $("#curp").addClass('headingColor');
    $("#rfc").addClass('notEmpty');
    $("#rfc").addClass('headingColor');
    console.log(curp);
}

function loading(bank) {
    $("#spinner").html(spinner(bank));
    $("#spinner").fadeIn();
    $('#modalloginBank').modal({
        keyboard: false,
        backdrop: false,
        escapeClose: false,
        clickClose: false,
        showClose: false
    })
}

function complete() {
    $("#spinner").html("");
    $("#loginInteractiveHtml").html("");
}

function spinner(bank) {
    var html = "<center><i class=\"fa fa-refresh fa-spin\" style=\"font-size:60px;color:#298df5\"></i><p style=\"font-size:16px;color:#298df5\">...</p></center>";
    html += "<center><p style=\"font-size:16px;color:#298df5\">Estableciendo Conexi&oacute;n con la Instituci&oacute;n Financiera</p>";
    if (bank == 'banamex') {

        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/banamex.png\"/></center>";
    } else if (bank == 'bancomer') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/bancomer.png\"/></center>";
    } else if (bank == 'american_express') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/american_express.png\"/></center>";
    } else if (bank == 'santander') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/santander.png\"/></center>";
    } else if (bank == 'banorte') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/banorte.png\"/></center>";
    } else if (bank == 'hsbc') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/hsbc.png\"/></center>";
    }
    return html;
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
    complete();
}

function buildLoginBank(respuesta) {
    console.log("buildLoginBank...");
    var html_base = "";
    html_base += "<center>";
    html_base += "<div class=\"row\">";
    html_base += "<div class=\"col6 col12-mob floatLeft\">";
    if (respuesta.loginInteractiveHtml == null) {
        html_base += "Ingresa tu Token de Seguridad"
    } else {
        html_base += respuesta.loginInteractiveHtml
    }
    html_base += "</div>";
    html_base += "</div>";
    html_base += "</center>";
    var html = "";
    html += "<form action=\"#\" name=\"formLoginInteractive\" id=\"formLoginInteractive\">"
    $.each(respuesta.interactiveFieldsName, function (i, fieldName) {
        console.log("FIELD" + fieldName);
        html += "<center>";
        html += "<div class=\"row\">";
        html += "<div class=\"col6 col12-mob floatLeft\">";
        html += "<input class=\"inPuts4a formValues textUpper headingColor notEmpty\" name=\"" + fieldName + "\" id=\"" + fieldName + "\" autocomplete=\"off\" autocapitalize=\"off\" value=\"\" type=\"password\">";
        html += "</div>";
        html += "</div>";
        html += "</center>";
    });
    html += "<input class=\"\" name=\"customer_id\" id=\"customer_id\" autocomplete=\"off\" autocapitalize=\"off\" value=\"" + respuesta.customer_id + "\" type=\"hidden\">";
    html += "<div class=\"clearfix\"><br/></div>";
    html += "<center>";
    html += "<div class=\"row\">";
    html += "<div class=\"col12 col12-mob floatLeft\">";
    html += "<input type=\"button\" class=\"consultarBox marginLeft15 center colorWhite letterspacing1 font16\" value=\"Enviar\" onclick=\"loginInteractive();\" />";
    html += "</div>";
    html += "</div>";
    html += "</center>";
    html += "</form>";
    console.log("resp" + html_base + html);
    return html_base + html
}

function consultarBancos() {
    var banco = $('.bankChoice').val();
    console.log("Validando seleccion de banco...");
    if (banco) {
        var cliente = $('#clientNo').val();
        var clave = $('#clave').val();
        var token = $('#tokenNo').val();
        var intentos = $('#intentos').val();
        console.log("Validando llenado de campos...");
        if (cliente && clave && token) {
            console.log("Mostrando barra de progreso...");
            loadBar();
            $.ajax({
                type: 'POST',
                data: 'banco=' + banco + "&cliente=" + cliente + "&clave=" + clave + "&token=" + token + "&intentos=" + intentos,
                url: '/kosmos-app/solicitud/consultaBancos',
                success: function (data, textStatus) {
                    var respuesta = checkIfJson(data);
                    restartLoadBar();
                    if (respuesta.status === 200) {
                        $('#dep90').val(formatCurrency(respuesta.depositosPromedio, "$"));
                        $('#ret90').val(formatCurrency(respuesta.retirosPromedio, "$"));
                        $('#saldo90').val(formatCurrency(respuesta.saldoPromedio, "$"));
                        $('.loadingActive').hide();
                        $('#consultarInfo').hide();
                        $('.defaultBubble').fadeOut();
                        $('.successBubble').fadeIn();
                        $('#confirmarConsulta').fadeIn();
                    } else if (respuesta.error) {
                        $('#intentos').val(respuesta.intentos);
                        $('#accionesNormal').hide();
                        $('#accionesError').fadeIn();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    restartLoadBar();
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

//Función para consultar el API del buró de crédito
function consultarBuro() {
    console.log("Consultando WS Buro de Credito...");
    loadBar("45%");
    var tarjeta = $('#tCredito').val();
    var hipoteca = $('#creditoH').val();
    var creditoAutomotriz = $('#creditoA').val();
    console.log("Validando llenado de campos...");
    if (tarjeta && hipoteca && creditoAutomotriz) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            console.log("Mostrando barra de progreso...");
            loadBar("75%");
            $.ajax({
                type: 'POST',
                data: 'tarjeta=' + tarjeta + "&numeroTarjeta=" + numeroTarjeta + "&hipoteca=" + hipoteca + "&creditoAutomotriz=" + creditoAutomotriz,
                url: '/kosmos-app/solicitud/consultarBuroDeCredito',
                success: function (data, textStatus) {
                    loadBar("100%");
                    //cerrarModal();
                    var respuesta = checkIfJson(data);
                    $('.loadingBar').fadeOut();
                    $('.creditBtns').fadeOut();
                    $('.loadingContainer .buttonM').delay(1000).fadeIn();
                    if (respuesta.status === 200) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesSuccess').fadeIn();
                        $('#divAutorizacionBuro').fadeOut();
                    } else if (respuesta.error) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesError').fadeIn();
                        $('#divAutorizacionBuro').fadeOut();
                        resumeCountdown();
                    }
                    showValues();
                    $('.nextBtn').addClass('sendBtn');
                    //submitNextPage();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    restartLoadBar();
                    $('.loadingBar').fadeOut();
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    } else {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados.", "warning");
    }
}

//Funcion para validar campos capturados y Mostrar modal de Autorizacion para Buro de Credito.
function validarAutorizacion() {
    console.log("Validando seleccion de opciones...");
    var tarjeta = $('#tCredito').val();
    var hipoteca = $('#creditoH').val();
    var creditoAutomotriz = $('#creditoA').val();
    var nombre = $("#nombre").val();
    var apellidoPaterno = $("#apellidoPaterno").val();
    var apellidoMaterno = $("#apellidoMaterno").val();
    var fechaNac = $("#fechaNac").val();
    var rfc = $("#rfc").val();
    var calle = $("#calle").val();
    var noExterior = $("#noExterior").val();
    var noInterior = $("#noInterior").val();
    var colonia = $("#colonia").val();
    var municipio = $("#municipio").val();
    console.log("Validando llenado de campos...");
    if (tarjeta && hipoteca && creditoAutomotriz && nombre && apellidoPaterno && apellidoMaterno && fechaNac && calle && noExterior && colonia && municipio) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            consultarBuro();
            //$('#autorizacionLoading').delay( 200 ).fadeOut();
            //$('#autorizacionForm').fadeIn();
            //$('#modalAutorizacion').modal({keyboard: false, backdrop: false})
        }
    } else if (!nombre || !apellidoPaterno || !apellidoMaterno || !fechaNac) {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados en el paso 1", "warning");
    } else if (!calle || !noExterior || !colonia || !municipio) {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados en el paso 2", "warning");
    } else {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados de este paso", "warning");
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

//Formatear números como moneda
function formatCurrency(n, currency) {
    return currency + " " + n.toFixed(2).replace(/./g, function (c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}

function inicializarCamara(cara) {
    console.log("Inicializando camara...");
    $('#webcam' + cara).html("");
    $('#webcam' + cara).photobooth().on("image", function (event, dataUrl) {
        $('#webcam' + cara).data('photobooth').destroy();
        $("#imagenCapturada" + cara).val(dataUrl);
        $('#webcam' + cara).html('<img src="' + dataUrl + '">');
        $('#repetirFoto' + cara).prop('disabled', false);
        $('#repetirFoto' + cara).removeClass("GrayButton");
        $('#repetirFoto' + cara).addClass("buttonOrange");
        $('#guardarFoto' + cara).prop('disabled', false);
        $('#guardarFoto' + cara).removeClass("GrayButton");
        $('#guardarFoto' + cara).addClass("colorGreen");
    });
    $('#repetirFoto' + cara).prop('disabled', true);
    $('#repetirFoto' + cara).removeClass("buttonOrange");
    $('#repetirFoto' + cara).addClass("GrayButton");
    $('#guardarFoto' + cara).prop('disabled', true);
    $('#guardarFoto' + cara).removeClass("colorGreen");
    $('#guardarFoto' + cara).addClass("GrayButton");
}

function detenerCamara(cara) {
    $('#webcam' + cara).data('photobooth').pause();
    $('.photobooth').remove();
    $('#webcam' + cara).html('');
}

function guardarFoto(cara) {
    $.ajax({
        type: 'POST',
        data: 'img_data=' + $("#imagenCapturada" + cara).val() + "&cara=" + cara,
        url: '/kosmos-app/solicitud/guardarFoto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.status === 200) {
                sweetAlert("!Enhorabuena¡", "La foto se subio correctamente", "success");
                if (cara === 'Frente') {
                    detenerCamara('Frente');
                    $('#fotoFrente').fadeOut();
                    $('.active_blue').removeClass('active_blue');
                    $('.paddingBottom15').addClass('active_blue');
                    $('#fotoVuelta').fadeIn();
                    inicializarCamara('Vuelta');
                } else if (cara === 'Vuelta') {
                    detenerCamara('Vuelta');
                    $('#identification_oficial').fadeOut();
                }
            } else {
                sweetAlert("Oops...", "Algo salió mal al subir la foto, intenta nuevamente en unos minutos.", "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function openModal(divModal) {
    $('#' + divModal).fadeIn();
}

function closeModal(divModal) {
    $('#' + divModal).fadeOut();
}

function operacionesModal() {
    $('.closeModal').click(function () {
        $(this).parent().parent().fadeOut();
    });
    $('.pasaporteId').click(function () {
        $('#tipoDeDocumento').val('Pasaportes');
        $('#nombreDeLaIdentificacion').html('frente de tu Pasaporte');
        inicializarDropzone('div#divDropzoneIds', '#subirIdentificacion');
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().next('.idView').delay(300).fadeIn();
        $('#label2Ids').addClass('active_blue');
        $('#label1Ids').removeClass('active_blue');
        $('#label3Ids').removeClass('active_blue');
        $('#label3Ids').addClass('gray');
        $('#label1Ids').addClass('gray');
        $('#label2Ids').removeClass('gray');
    });

    $('.ineId').click(function () {
        $('#tipoDeDocumento').val('Identicaciones');
        $('#nombreDeLaIdentificacion').html('frente de tu Credencial de Elector');
        inicializarDropzone('div#divDropzoneIds', '#subirIdentificacion');
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().next('.idView').delay(300).fadeIn();
        $('#label2Ids').addClass('active_blue');
        $('#label1Ids').removeClass('active_blue');
        $('#label3Ids').removeClass('active_blue');
        $('#label3Ids').addClass('gray');
        $('#label1Ids').addClass('gray');
        $('#label2Ids').removeClass('gray');
    });

    $('.phoneCapture').click(function () {
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $('.phone_capture').delay(300).fadeIn();
    });

    $('.camCapture').click(function () {
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $('.webcam_capture').fadeIn();
        inicializarCamara('Frente');
    });

    $('.camCaptureComp').click(function () {
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $('.webcamCaptureComp').fadeIn();
        inicializarCamara('Comprobante');
    });

    $('#repetirFotoFrente').click(function () {
        inicializarCamara('Frente');
    });

    $('#guardarFotoFrente').click(function () {
        guardarFoto('Frente');
    });

    $('#repetirFotoVuelta').click(function () {
        inicializarCamara('Vuelta');
    });

    $('#guardarFotoVuelta').click(function () {
        guardarFoto('Vuelta');
    });

    $('#repetirFotoComprobante').click(function () {
        inicializarCamara('Comprobante');
    });

    $('#guardarFotoComprobante').click(function () {
        guardarFoto('Comprobante');
    });

    $('.goLastStep').click(function () {

    });
    //Temporal //nombreDelDocumento
    /*$('.docChoice').click(function () {
     $('#tipoDeDocumento').val('UtilityBill');
     inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
     $(this).parent().parent().parent().parent().parent().parent().fadeOut();
     $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
     $('#label2Comp').addClass('active_blue');
     $('#label1Comp').removeClass('active_blue');
     $('#label1Comp').addClass('gray');
     $('#label2Comp').removeClass('gray');
     });*/
    $('.cfe').click(function () {
        $('#tipoDeDocumento').val('UtilityBill');
        $('#nombreDelDocumento').html('Recibo de C.F.E.');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
        $(this).parent().parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
        $('#label2Comp').addClass('active_blue');
        $('#label1Comp').removeClass('active_blue');
        $('#label1Comp').addClass('gray');
        $('#label2Comp').removeClass('gray');
    });
    $('.telmex').click(function () {
        $('#tipoDeDocumento').val('UtilityBill');
        $('#nombreDelDocumento').html('Recibo de Telmex');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
        $(this).parent().parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
        $('#label2Comp').addClass('active_blue');
        $('#label1Comp').removeClass('active_blue');
        $('#label1Comp').addClass('gray');
        $('#label2Comp').removeClass('gray');
    });

    $('#paso1CompDom').click(function () {
        $('#label1Comp').addClass('active_blue');
        $('#label2Comp').removeClass('active_blue');
        $('#label2Comp').addClass('gray');
        $('#label1Comp').removeClass('gray');
        $('#seleccionComprobante').show();
        $('#metodoSubidaComprobante').hide();
        $('#capturaTelefonoComp').hide();
        $('#webcamCaptureComp').hide();
    });

    $('#paso2CompDom').click(function () {
        if ($('#tipoDeDocumento').val()) {
            $('#label2Comp').addClass('active_blue');
            $('#label1Comp').removeClass('active_blue');
            $('#label1Comp').addClass('gray');
            $('#label2Comp').removeClass('gray');
            $('#seleccionComprobante').hide();
            $('#metodoSubidaComprobante').show();
            $('#capturaTelefonoComp').hide();
            $('#webcamCaptureComp').hide();
        }
    });

    $('#paso1Ids').click(function () {
        $('#label1Ids').addClass('active_blue');
        $('#label2Ids').removeClass('active_blue');
        $('#label3Ids').removeClass('active_blue');
        $('#label3Ids').addClass('gray');
        $('#label2Ids').addClass('gray');
        $('#label1Ids').removeClass('gray');
        $('#seleccionIdentificacion').show();
        $('#metodoSubidaIdentificacion').hide();
        $('#capturaTelefonoIds').hide();
        $('#fotoFrente').show();
        $('#fotoVuelta').hide();
        $('#webcamCaptureIds').hide();
    });

    $('#paso2Ids').click(function () {
        if ($('#tipoDeDocumento').val()) {
            $('#label2Ids').addClass('active_blue');
            $('#label1Ids').removeClass('active_blue');
            $('#label3Ids').removeClass('active_blue');
            $('#label3Ids').addClass('gray');
            $('#label1Ids').addClass('gray');
            $('#label2Ids').removeClass('gray');
            $('#seleccionIdentificacion').hide();
            $('#metodoSubidaIdentificacion').show();
            $('#capturaTelefonoIds').hide();
            $('#fotoFrente').show();
            $('#fotoVuelta').hide();
            $('#webcamCaptureIds').hide();
        }
    });
}

function habilitarTerminarSolicitud() {
    var no_buttons = $('.greenClick').length;
    var no_active = $('.greenClick.colorGreen').length;

    if (no_active === no_buttons) {
        $('.solicitud_modal').addClass('blueButton colorWhite pointer');
    } else {
        $('.solicitud_modal').removeClass('blueButton colorWhite pointer');
    }
}

Dropzone.autoDiscover = false;

function inicializarDropzone(elemento, boton) {
    //Dropzone.autoDiscover = false;
    var kosmosDropzone = new Dropzone(elemento, {
        url: "/kosmos-app/solicitud/consultarEphesoft",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        params: {'docType': $('#tipoDeDocumento').val()},
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: ".pdf, .png, .jpg, .jpeg",
        autoQueue: true,
        createImageThumbnails: false,
        clickable: boton
    });
    kosmosDropzone.on("addedfile", function (file) {
        console.log("Archivo enviado: " + file);
        $('.dz-preview').hide();
        $('#progresoConsultaComp').fadeIn();
        $('#progresoConsultaIds').fadeIn();
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);
        console.log("Respuesta recibida: " + respuesta);
        $('#progresoConsultaComp').fadeOut();
        console.log("Documento Vigente? " + respuesta.vigente);
        if (respuesta.vigente === true) {
            var pasoActual = $('#pasoInicial').val();
            if (pasoActual === "0") {
                $('#formLogin').submit();
            } else {
                var direccion = (respuesta.calle ? respuesta.calle : respuesta.direccion);
                if (respuesta.direccion) {
                    sweetAlert({html: true, title: "¡Excelente!", text: "Se obtuvieron los siguientes datos: <br/> <strong>Nombre:</strong>" + respuesta.nombrePersona + "<br/><strong>Dirección: </strong>" + respuesta.direccion + "<br/><strong>Fecha del Recibo: </strong> " + respuesta.fechaRecibo + "</br>", type: "success"});
                    closeModal('comprobante_domicilio');
                    $('#calle').val(direccion);
                    $('#calle').addClass("notEmpty");
                    $('#calle').addClass("headingColor");
                    if (respuesta.codigoPostal) {
                        $('#codigoPostal').val(respuesta.codigoPostal);
                        $('#codigoPostal').addClass("notEmpty");
                        $('#codigoPostal').addClass("headingColor");
                        $('#municipio').val(respuesta.municipio);
                        $('#municipio').addClass("notEmpty");
                        $('#municipio').addClass("headingColor");
                        $('#estado').val(respuesta.estado);
                        $('#estado').addClass("notEmpty");
                        $('#estado').addClass("headingColor");
                    }
                    $('.defaultBubble').fadeOut();
                    $('.successBubble').fadeIn();
                    $('#paso6CompDom').addClass('colorGreen');
                    $('#pasoPrellenado').val("true");
                    $('.showOnFill').each(function (index) {
                        verificarCambios(index);
                    });
                    habilitarTerminarSolicitud();
                    if (document.getElementById('login')) {
                        $('#submitLogin').click();
                    }
                } else if (respuesta.nombre && (respuesta.apellidoPaterno || respuesta.apellidoMaterno)) {
                    sweetAlert({html: true, title: "¡Excelente!", text: "Se obtuvieron los siguientes datos: <br/> <strong>Nombre:</strong>" + respuesta.nombre + "<br/><strong>Apellido Paterno: </strong>" + respuesta.apellidoPaterno + "<br/><strong>Apellido Materno: </strong> " + respuesta.apellidoMaterno + "</br>", type: "success"});
                    closeModal('identification_oficial');
                    $('#paso6IdOf').addClass('colorGreen');
                    $('#pasoPrellenado').val("true");
                    $('.showOnFill').each(function (index) {
                        verificarCambios(index);
                    });
                    habilitarTerminarSolicitud();
                    if (document.getElementById('login')) {
                        $('#submitLogin').click();
                    }
                } else if (respuesta.nombre && (respuesta.apellidos || respuesta.noDocumento)) {
                    sweetAlert({html: true, title: "¡Excelente!", text: "Se obtuvieron los siguientes datos: <br/> <strong>Nombre:</strong>" + respuesta.nombre + "<br/><strong>Apellidos: </strong>" + respuesta.apellidos + "<br/><strong>Documento No.: </strong> " + respuesta.noDocumento + "</br>", type: "success"});
                    closeModal('identification_oficial');
                    $('#paso6IdOf').addClass('colorGreen');
                    $('#pasoPrellenado').val("true");
                    $('.showOnFill').each(function (index) {
                        verificarCambios(index);
                    });
                    habilitarTerminarSolicitud();
                    if (document.getElementById('login')) {
                        $('#submitLogin').click();
                    }
                } else if (respuesta.error) {
                    sweetAlert("Oops...", respuesta.error, "error");
                    this.removeAllFiles();
                } else if (respuesta.fatal) {
                    sweetAlert("Oops...", "Ocurrio un error al procesar el documento: " + respuesta.error, "error");
                    this.removeAllFiles();
                } else {
                    sweetAlert("¡Advertencia!", "No se recuperaron datos legibles", "warning");
                    this.removeAllFiles();
                    closeModal('identification_oficial');
                    closeModal('comprobante_domicilio');
                }
            }
        } else if (respuesta.vigente === false) {
            sweetAlert("Oops...", "El documento envíado no está vigente. Por favor, suba un documento vigente.", "error");
        } else {
            sweetAlert("Oh no!", "No se ha podido determinar la vigencia del documento. Verifique que la imagen/archivo es legible y no tenga tachaduras o enmendaduras, así como que no tenga border blancos.", "warning");
        }
        this.removeAllFiles();
        $('#progresoConsultaComp').fadeOut();
        $('#progresoConsultaIds').fadeOut();
    });
    kosmosDropzone.on("error", function (file, response) {
        console.log(response);
        sweetAlert("Oops...", "Ocurrio un problema al consultar los datos del documento", "error");
    });
}
// ***************************** Fin de Funciones Auxiliares

// ***************************** Inicio de Validaciones

function validacionPaso1() {
    $("#formPaso1").validate({
        rules: {
            accionesAudiencia: {
                required: function (element) {
                    return !($('[name="diferirAudiencia"]').is(':checked'));
                }
            },
            resultadoAudiencia: {
                required: function (element) {
                    return !($('[name="diferirAudiencia"]').is(':checked'));
                }
            },
            motivo: {
                required: function (element) {
                    return $('[name="diferirAudiencia"]').is(':checked');
                }
            },
            fechaAud: {
                required: function (element) {
                    return $('[name="diferirAudiencia"]').is(':checked');
                }
            },
            hora: {
                required: function (element) {
                    return $('[name="diferirAudiencia"]').is(':checked');
                }
            },
            nombreAsistente: "required"
        },
        messages: {
            accionesAudiencia: "Por favor indique las acciones de la audiencia",
            resultadoAudiencia: "Por favor indique el resultado de la audiencia",
            motivo: "Por favor indique el motivo",
            fechaAud: "Por favor indique la nueva fecha",
            hora: "Por favor indique la nueva hora",
            nombreAsistente: "Por favor indique el asistente"
        },
        submitHandler: function () {
            actualizarAudiencia();
        }
    });
}

// ***************************** Fin de VALIDACIONES

jQuery(function ($) {

    var _oldShow = $.fn.show;

    $.fn.show = function (speed, oldCallback) {
        return $(this).each(function () {
            var obj = $(this),
                    newCallback = function () {
                        if ($.isFunction(oldCallback)) {
                            oldCallback.apply(obj);
                        }
                        obj.trigger('afterShow');
                    };

            // you can trigger a before show if you want
            obj.trigger('beforeShow');

            // now use the old function to show the element passing the new callback
            _oldShow.apply(obj, [speed, newCallback]);
        });
    }
});

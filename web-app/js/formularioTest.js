var winH;
var contentH;
var headerH;
var footerH;
var avancePorPaso;
var elementos;
var ponderaciones;
var slideIndex = 1;
var listaDeControl;
var generoConyugue;
var ubicacionSucursal;
var listaDeSucursales;
var cara = "frente";
showSlides(slideIndex);

function inicializarFormulario() {
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        $('.phoneCapture').hide();
        $('.camCapture').hide();
        $('.mobileCapture').show();
        $('.desktopCapture').hide();
    } else {
        $('.mobileCapture').hide();
        $('.desktopCapture').show();
    }
    slideIndex = 1;
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();
    elementos = $('.showOnFill');
    var length = elementos.length;
    listaDeControl = new Array(length);
    for (var i = 0; i < length; i++) {
        listaDeControl[i] = 0;
    }

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        return false;
    });
    var pasoInicial = $('#pasoInicial').val();
    var tipoDePaso = $('#tipoDePaso').val();
    operacionesModal();
    actualizarProgreso(pasoInicial);
    if (tipoDePaso === "pasoFormulario") {
        if (pasoInicial === "1") {
            operacionesLogin();
        }
        operacionesFormulario();
    } else if (tipoDePaso === "consultaBancaria") {
        operacionesBancos();
    } else if (tipoDePaso === "consultaBuro") {
        operacionesBuro();
    } else if (tipoDePaso === "resumen") {
        operacionesResumen();
    } else if (tipoDePaso === "confirmacion") {
        operacionesConfirmacion();
    }

    habilitarBotonesAvance();

    if ($('#revisionInicial').val() === "false") {
        $('#revisionInicial').remove();
    } else {
        mostrarSiguienteCampo(0);
    }

    $(document).tooltip({
        position: {
            my: "center bottom-20",
            at: "center top",
            using: function (position, feedback) {
                $(this).css(position);
                $("<div>")
                        .addClass("arrow")
                        .addClass(feedback.vertical)
                        .addClass(feedback.horizontal)
                        .appendTo(this);
            }
        }
    });

    $('.select2').select2();
}

function habilitarBotonesAvance() {
    $('.freeNav').unbind('hover');
    $(".freeNav").hover(function () {
        if ($(this).hasClass("grayCircle") || $(this).hasClass("blueCircle")) {
            $(this).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
            $(this).addClass('nextBtn');
            $(this).addClass('sendBtn');
            $(this).children('p').addClass('paddingTop10');
            $(this).children('p').removeClass('paddingTop5');
            $(this).children('p').html("IR AL PASO " + $(this).children('p').text());
        }
    }, function () {
        if ($(this).hasClass("grayCircle") || $(this).hasClass("blueCircle")) {
            $(this).animate({width: "45px", height: "45px", marginTop: "0px"}, {queue: false});
            $(this).removeClass('nextBtn');
            $(this).removeClass('sendBtn');
            $(this).children('p').addClass('paddingTop10');
            $(this).children('p').removeClass('paddingTop5');
            $(this).children('p').html($(this).children('p').text().replace("IR AL PASO ", ""));
        }
    });
    $('.botonCambioDePaso').unbind('click');
    $('.botonCambioDePaso').click(function () {
        if ($(this).hasClass("sendBtn")) {
            var numeroDePaso = $(this).data('numeroDePaso');
            avanzarPaso(numeroDePaso);
        }
    });
}

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = $('.formStep').length;
    if (n > slides) {
        slideIndex = 1;
    }
    if (n < 1) {
        slideIndex = slides;
    }
    for (i = 0; i < slides; i++) {
        $('.formStep').eq(i).slideUp();
    }
    $('.formStep').eq(slideIndex - 1).slideDown();
}

function cargarPonderaciones(listaDePonderaciones, listaDeAvances) {
    var avance = JSON.parse(listaDeAvances);
    ponderaciones = JSON.parse(listaDePonderaciones);
    if (avancePorPaso === undefined) {
        var length = Object.keys(ponderaciones).length;
        avancePorPaso = new Array(length);
        for (var i = 0; i < length; i++) {
            avancePorPaso[i] = Number(avance[("paso" + (i + 1))]);
        }
    } else {
    }
    calcularAvance();
}

function cargarSucursales(sucursalesElegidas) {
    if (sucursalesElegidas !== undefined && sucursalesElegidas !== null && sucursalesElegidas !== '') {
        listaDeSucursales = JSON.parse(sucursalesElegidas);
    }
}

function operacionesLogin() {
    $('#loginIdOf').click(function () {
        openModal('identification_oficial');
    });

    $('#loginCompDom').click(function () {
        openModal('comprobante_domicilio');
    });
}

function operacionesFormulario() {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        return false;
    });

    $('.showOnFill').change(function (index) {
        var thisStep = $(this);
        var maxIndex = $('.showOnFill').length;
        $('.formValues', this).change(function () {
            if ($(this).val() !== '') {
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
                if ($(this).attr('id') === 'cliente_nombre') {
                    $('#nombreCliente').html('¡Hola! ' + $(this).val());
                } else if ($(this).attr('id') === 'cliente_nacionalidad') {
                    if ($('#cliente_nacionalidad').val() === '2') {
                        swal({
                            title: "¡Importante!",
                            text: "Por favor, asista a una sucursal para continuar con su trámite.",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "Me equivoque en la selección",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "Ok, Enterado",
                            closeOnConfirm: false
                        }, function (isConfirm) {
                            if (isConfirm) {
                                avanzarPaso(0);
                                window.location.href = "https://micreditolibertad.com";
                            } else {
                                $('#cliente_nacionalidad').val(1);
                            }
                            return false;
                        });
                    }
                } else if ($(this).attr('id') === 'cliente_estadoCivil') {
                    var estadoCivilTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='cliente_estadoCivil']");
                    $(listaElementos).each(function () {
                        if (Number(estadoCivilTmp) === Number($(this).data('valorDependencia'))) {
                            $(this).children('.formValues').removeClass('notEmpty');
                            $(this).children('.formValues').removeClass('noMostrar');
                            $(this).children().children('.formValues').removeClass('notEmpty');
                            $(this).children().children('.formValues').removeClass('noMostrar');
                            listaDeControl[(index + $(this).data('id'))] = 0;
                        } else {
                            $(this).children('.formValues').addClass('notEmpty');
                            $(this).children('.formValues').addClass('noMostrar');
                            $(this).children().children('.formValues').addClass('notEmpty');
                            $(this).children().children('.formValues').addClass('noMostrar');
                            listaDeControl[(index + $(this).data('id'))] = -1;
                        }
                    });
                } else if ($(this).attr('id') === 'direccionCliente_tipoDeVivienda') {
                    var tipoDeViviendaTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='direccionCliente_tipoDeVivienda']");
                    $(listaElementos).each(function () {
                        if (Number(tipoDeViviendaTmp) === Number($(this).data('valorDependencia'))) {
                            $(this).children('.formValues').removeClass('notEmpty');
                            $(this).children('.formValues').removeClass('noMostrar');
                            $(this).children().children('.formValues').removeClass('notEmpty');
                            $(this).children().children('.formValues').removeClass('noMostrar');
                            listaDeControl[$(this).data('id')] = 0;
                        } else {
                            $(this).children('.formValues').addClass('notEmpty');
                            $(this).children('.formValues').addClass('noMostrar');
                            $(this).children().children('.formValues').addClass('notEmpty');
                            $(this).children().children('.formValues').addClass('noMostrar');
                            listaDeControl[$(this).data('id')] = -1;
                        }
                    });
                } else if (($('#cliente_nombre').val() !== '' && $('#cliente_nombre').val() !== undefined) && ($('#cliente_apellidoPaterno').val() !== '' && $('#cliente_apellidoPaterno').val() !== undefined) && ($('#cliente_apellidoMaterno').val() !== '' && $('#cliente_apellidoMaterno').val() !== undefined) && ($('#cliente_genero').val() !== '' && $('#cliente_genero').val() !== undefined) && ($('#cliente_lugarDeNacimiento').val() !== '' && $('#cliente_nombre').val() !== undefined)) {
                    generarClaves('cliente');
                } else if (($('#cliente_nombreDelConyugue').val() !== '' && $('#cliente_nombreDelConyugue').val() !== undefined) && ($('#cliente_apellidoPaternoDelConyugue').val() !== '' && $('#cliente_apellidoPaternoDelConyugue').val() !== undefined) && ($('#cliente_apellidoMaternoDelConyugue').val() !== '' && $('#cliente_apellidoMaternoDelConyugue').val() !== undefined) && (generoConyugue) && ($('#cliente_lugarDeNacimientoDelConyugue').val() !== '' && $('#cliente_lugarDeNacimientoDelConyugue').val() !== undefined)) {
                    generarClaves('conyugue');
                } else if (($('#empleoCliente_ingresosFijos').val() !== '') && ($('#empleoCliente_ingresosVariables').val() !== '')) {
                    var sumatoria = Number($('#empleoCliente_ingresosFijos').val()) + Number($('#empleoCliente_ingresosVariables').val());
                    $('#empleoCliente_ingresosTotales').val(sumatoria);
                    $('#empleoCliente_ingresosTotales').addClass('notEmpty');
                    $('#empleoCliente_ingresosTotales').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#empleoCliente_ingresosTotales').closest('.showOnFill'))] = 1;
                }
            } else {
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }
            mostrarSiguienteCampo(index);
            calcularAvance();
        });
    });

    $('.showOnFill').each(function (index) {
        var thisStep = $(this);
        var maxIndex = $('.showOnFill').length;
        $('.formValues', this).change(function () {
            if ($(this).val() !== '') {
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
                if ($(this).attr('id') === 'cliente_nombre') {
                    $('#nombreCliente').html('¡Hola! ' + $(this).val());
                } else if ($(this).attr('id') === 'cliente_nacionalidad') {
                    if ($('#cliente_nacionalidad').val() === '2') {
                        swal({
                            title: "¡Importante!",
                            text: "Por favor, asista a una sucursal para continuar con su trámite.",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "Me equivoque en la selección",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "Ok, Enterado",
                            closeOnConfirm: false
                        }, function (isConfirm) {
                            if (isConfirm) {
                                avanzarPaso(0);
                                window.location.href = "https://micreditolibertad.com";
                            } else {
                                $('#cliente_nacionalidad').val(1);
                            }
                            return false;
                        });
                    }
                } else if ($(this).attr('id') === 'direccionCliente_codigoPostal' || $(this).attr('id') === 'empleoCliente_codigoPostal') {
                    consultarCodigoPostal($(this).attr('id'), $(this).val());
                } else if ($(this).attr('id') === 'direccionCliente_sucursal') {
                    mostrarDireccionSucursal($(this).val());
                } else if ($(this).attr('id') === 'cliente_estadoCivil') {
                    var estadoCivilTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='cliente_estadoCivil']");
                    $(listaElementos).each(function () {
                        if (Number(estadoCivilTmp) === Number($(this).data('valorDependencia'))) {
                            $(this).children('.formValues').removeClass('notEmpty');
                            $(this).children('.formValues').removeClass('noMostrar');
                            $(this).children().children('.formValues').removeClass('notEmpty');
                            $(this).children().children('.formValues').removeClass('noMostrar');
                            listaDeControl[(index + $(this).data('id'))] = 0;
                        } else {
                            $(this).children('.formValues').addClass('notEmpty');
                            $(this).children('.formValues').addClass('noMostrar');
                            $(this).children().children('.formValues').addClass('notEmpty');
                            $(this).children().children('.formValues').addClass('noMostrar');
                            listaDeControl[(index + $(this).data('id'))] = -1;
                        }
                    });
                } else if ($(this).attr('id') === 'direccionCliente_tipoDeVivienda') {
                    var tipoDeViviendaTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='direccionCliente_tipoDeVivienda']");
                    $(listaElementos).each(function () {
                        if (Number(tipoDeViviendaTmp) === Number($(this).data('valorDependencia'))) {
                            $(this).children('.formValues').removeClass('notEmpty');
                            $(this).children('.formValues').removeClass('noMostrar');
                            $(this).children().children('.formValues').removeClass('notEmpty');
                            $(this).children().children('.formValues').removeClass('noMostrar');
                            listaDeControl[$(this).data('id')] = 0;
                        } else {
                            $(this).children('.formValues').addClass('notEmpty');
                            $(this).children('.formValues').addClass('noMostrar');
                            $(this).children().children('.formValues').addClass('notEmpty');
                            $(this).children().children('.formValues').addClass('noMostrar');
                            listaDeControl[$(this).data('id')] = -1;
                        }
                    });
                } else if (($('#cliente_rfc').val() === '') && ($('#cliente_curp').val() === '')) {
                    if (($('#cliente_nombre').val() !== '' && $('#cliente_nombre').val() !== undefined) && ($('#cliente_apellidoPaterno').val() !== '' && $('#cliente_apellidoPaterno').val() !== undefined) && ($('#cliente_apellidoMaterno').val() !== '' && $('#cliente_apellidoMaterno').val() !== undefined) && ($('#cliente_genero').val() !== '' && $('#cliente_genero').val() !== undefined) && ($('#cliente_lugarDeNacimiento').val() !== '' && $('#cliente_nombre').val() !== undefined)) {
                        generarClaves('cliente');
                    } else {
                    }
                } else if (($('#cliente_rfcDelConyugue').val() === '') && ($('#cliente_curpDelConyugue').val() === '')) {
                    if (($('#cliente_nombreDelConyugue').val() !== '' && $('#cliente_nombreDelConyugue').val() !== undefined) && ($('#cliente_apellidoPaternoDelConyugue').val() !== '' && $('#cliente_apellidoPaternoDelConyugue').val() !== undefined) && ($('#cliente_apellidoMaternoDelConyugue').val() !== '' && $('#cliente_apellidoMaternoDelConyugue').val() !== undefined) && (generoConyugue) && ($('#cliente_lugarDeNacimientoDelConyugue').val() !== '' && $('#cliente_lugarDeNacimientoDelConyugue').val() !== undefined)) {
                        generarClaves('conyugue');
                    } else {
                    }
                } else if (($('#empleoCliente_ingresosFijos').val() !== '') && ($('#empleoCliente_ingresosVariables').val() !== '')) {
                    var sumatoria = Number($('#empleoCliente_ingresosFijos').val()) + Number($('#empleoCliente_ingresosVariables').val());
                    $('#empleoCliente_ingresosTotales').val(sumatoria);
                    $('#empleoCliente_ingresosTotales').addClass('notEmpty');
                    $('#empleoCliente_ingresosTotales').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#empleoCliente_ingresosTotales').closest('.showOnFill'))] = 1;
                }
            } else {
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }
            mostrarSiguienteCampo(index);
            calcularAvance();
        });
    });
    $('.formStep .confirmDiv .buttonM').unbind("click");
    $('.formStep .confirmDiv .buttonM').click(function () {
        var prellenado = $('#pasoPrellenado').val();
        if ($(this).parent().parent().is(':hidden') === true) {
            if (prellenado === "true") {
                if ($(this).parent().parent().hasClass('lastStep') === true) {
                    $('.nextBtn').addClass('sendBtn');
                    var currentStep = $('#pasoAnterior').val();
                    $(this).parent().parent().slideUp();
                    $(this).parent().parent().next('.formStep').slideDown();
                    $('.slideStep').fadeIn();
                    slideIndex++;
                    $('.successBubble').fadeOut();
                    if ($('#circuloPaso' + (parseInt(currentStep) + 1)).hasClass("grayCircle")) {
                        $('.footerContainer .width600').animate({width: "490px"}, {queue: false});
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('nextBtn');
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('sendBtn');
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').addClass('paddingTop10');
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').removeClass('paddingTop5');
                        $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').html("IR AL PASO " + (parseInt(currentStep) + 1));
                    }
                }
                $('#pasoPrellenado').val("false");
            } else {
            }
        } else {
            if ($(this).parent().parent().hasClass('lastStep') === true) {
                $('.nextBtn').addClass('sendBtn');
                var currentStep = $('#pasoAnterior').val();
                if ($('#circuloPaso' + (parseInt(currentStep) + 1)).hasClass("grayCircle")) {
                    $('.footerContainer .width600').animate({width: "490px"}, {queue: false});
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('nextBtn');
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('sendBtn');
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').addClass('paddingTop10');
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').removeClass('paddingTop5');
                    $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').html("IR AL PASO " + (parseInt(currentStep) + 1));
                }
            } else {
                $(this).parent().parent().slideUp();
                $(this).parent().parent().next('.formStep').slideDown();
                $('.slideStep').fadeIn();
                slideIndex++;
            }
            $('.successBubble').fadeOut();
        }
    });
}

function mostrarSiguienteCampo(index) {
    var prellenado = $('#pasoPrellenado').val();
    var pasoActual = $('#pasoAnterior').val();
    var elementosPorGrupo = $('.showOnFill').eq(index).children('.formValues').length;
    var elementosLLenosPorGrupo = $('.showOnFill').eq(index).children('.notEmpty').length;
    if (elementosPorGrupo === 0) {
        elementosPorGrupo = $('.showOnFill').eq(index).children().children('.formValues').length;
        if (elementosPorGrupo === 0) {
            elementosPorGrupo = $('.showOnFill').eq(index).children().children().children('.formValues').length;
        }
    }
    if (elementosLLenosPorGrupo === 0) {
        elementosLLenosPorGrupo = $('.showOnFill').eq(index).children().children('.notEmpty').length;
        if (elementosLLenosPorGrupo === 0) {
            elementosLLenosPorGrupo = $('.showOnFill').eq(index).children().children().children('.notEmpty').length;
        }
    }
    if (prellenado === "true") {
        var elemento;
        var indice = index;
        var estadoCivilTmp = $('#cliente_estadoCivil').val();
        var listaElementos = $(".showOnFill[data-depende-de='cliente_estadoCivil']");
        $(listaElementos).each(function () {
            if (Number(estadoCivilTmp) === Number($(this).data('valorDependencia'))) {
                if (!$(this).children('.formValues').hasClass('headingColor')) {
                    $(this).children('.formValues').removeClass('notEmpty');
                    $(this).children('.formValues').removeClass('noMostrar');
                }
                if (!$(this).children().children('.formValues').hasClass('headingColor')) {
                    $(this).children().children('.formValues').removeClass('notEmpty');
                    $(this).children().children('.formValues').removeClass('noMostrar');
                }
                listaDeControl[(11 + $(this).data('id'))] = 0;
            } else {
                $(this).children('.formValues').addClass('notEmpty');
                $(this).children('.formValues').addClass('noMostrar');
                $(this).children().children('.formValues').addClass('notEmpty');
                $(this).children().children('.formValues').addClass('noMostrar');
                listaDeControl[(11 + $(this).data('id'))] = -1;
            }
        });
        var tipoDeViviendaTmp = $('#direccionCliente_tipoDeVivienda').val();
        var listaElementos = $(".showOnFill[data-depende-de='direccionCliente_tipoDeVivienda']");
        $(listaElementos).each(function () {
            if (Number(tipoDeViviendaTmp) === Number($(this).data('valorDependencia'))) {
                if (!$(this).children('.formValues').hasClass('headingColor')) {
                    $(this).children('.formValues').removeClass('notEmpty');
                    $(this).children('.formValues').removeClass('noMostrar');
                }
                if (!$(this).children().children('.formValues').hasClass('headingColor')) {
                    $(this).children().children('.formValues').removeClass('notEmpty');
                    $(this).children().children('.formValues').removeClass('noMostrar');
                }
                listaDeControl[$(this).data('id')] = 0;
            } else {
                $(this).children('.formValues').addClass('notEmpty');
                $(this).children('.formValues').addClass('noMostrar');
                $(this).children().children('.formValues').addClass('notEmpty');
                $(this).children().children('.formValues').addClass('noMostrar');
                listaDeControl[$(this).data('id')] = -1;
            }
        });
        elementos.each(function () {
            if ($(this).children('.formValues').attr('id') !== undefined) {
                elemento = $(this).children('.formValues');
            } else if ($(this).children().children('.formValues').attr('id') !== undefined) {
                elemento = $(this).children().children('.formValues');
            } else if ($(this).children().children().children('.formValues').attr('id') !== undefined) {
                elemento = $(this).children().children().children('.formValues');
            }
            if (($(elemento).hasClass('noMostrar') === false) && (listaDeControl[indice + 1] !== -1)) {
                $(this).fadeIn();
                $(this).css({'display': 'inline'});
            } else if (($(elemento).hasClass('noMostrar') === true) && (listaDeControl[indice + 1] === -1)) {
                $(this).fadeOut();
                $(this).css({'display': 'none'});
            }
        });
    } else if (elementosPorGrupo === elementosLLenosPorGrupo) {
        listaDeControl[index] = 1;
        if ((index + 1) < elementos.length) {
            var indice = index;
            var elementoMostrado = false;
            while ((indice + 1) < elementos.length && elementoMostrado === false) {
                if (listaDeControl[indice + 1] === 0) {
                    var elemento;
                    $('.showOnFill').eq(indice + 1).fadeIn();
                    $('.showOnFill').eq(indice + 1).css({'display': 'inline'});
                    if ($('.showOnFill').eq(indice + 1).children('.formValues').attr('id') !== undefined) {
                        elemento = $('.showOnFill').eq(indice + 1).children('.formValues');
                    } else if ($('.showOnFill').eq(indice + 1).children().children('.formValues').attr('id') !== undefined) {
                        elemento = $('.showOnFill').eq(indice + 1).children().children('.formValues');
                    } else if ($('.showOnFill').eq(indice + 1).children().children().children('.formValues').attr('id') !== undefined) {
                        elemento = $('.showOnFill').eq(indice + 1).children().children().children('.formValues');
                    }
                    if ($('.showOnFill').eq(indice + 1).hasClass('required') && !$(elemento).hasClass('notEmpty')) {
                        elementoMostrado = true;
                    }
                } else if (listaDeControl[indice + 1] === 1) {
                    $('.showOnFill').eq(indice + 1).fadeIn();
                    $('.showOnFill').eq(indice + 1).css({'display': 'inline'});
                } else if (listaDeControl[indice + 1] === -1) {
                    $('.showOnFill').eq(indice + 1).fadeOut();
                    $('.showOnFill').eq(indice + 1).css({'display': 'none'});
                }
                indice++;
            }
        }
    }
    var totalElementosRequeridosSubpaso = $(".required[data-subpaso='" + (slideIndex - 1) + "']").find(".formValues").length;
    var totalElementosRequeridosLlenosSubpaso = $(".required[data-subpaso='" + (slideIndex - 1) + "']").find(".notEmpty").length;
    var elementosLlenosVisibles = $('.notEmpty:visible').length;
    var totalElementosVisibles = $('.formStep:visible .formValues').length;
    var totalElementosRequeridos = $('.required .formValues').length;
    var totalElementosRequeridosLLenos = $('.required .notEmpty').length;
    totalElementosVisibles -= $('.formStep:visible .noMostrar').length;
    if ((elementosLlenosVisibles === totalElementosVisibles) || (totalElementosRequeridos === totalElementosRequeridosLLenos) || ((totalElementosRequeridosSubpaso === totalElementosRequeridosLlenosSubpaso))) {
        if (prellenado === "true" && (totalElementosRequeridos === totalElementosRequeridosLLenos)) {
            $('.lastStep .confirmDiv .buttonM').click();
        } else {
            $('.formStep:visible .confirmDiv .buttonM').click();
        }
    } else {
        if ($('#circuloPaso' + (parseInt(pasoActual) + 1)).hasClass("sendBtn")) {
            $('.footerContainer .width600').animate({width: "490px"}, {queue: false});
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).animate({width: "36px", height: "36px", marginTop: "5px"}, {queue: false});
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('nextBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('sendBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').addClass('paddingTop5');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').removeClass('paddingTop10');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').html($('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').text().replace("IR AL PASO ", ""));
        }
    }
}

function mostrarDireccionSucursal(idSucursal) {
    var x = 0;
    var encontrado = false;
    var ubicacion;
    var html;
    if (listaDeSucursales !== undefined && listaDeSucursales !== null) {
        while ((x < listaDeSucursales.length) && encontrado === false) {
            if (listaDeSucursales[x].id === Number(idSucursal)) {
                ubicacion = listaDeSucursales[x].ubicacion;
                encontrado = true;
            }
            x++;
        }
        if (ubicacion !== null && ubicacion !== undefined) {
            html = "<p> Ubicada en: " + ubicacion + "</p>";
            $('#accionesFinales2').html(html);
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
    var tipoDePaso = $('#tipoDePaso').val();
    percentStep = Number(ponderaciones[("paso" + pasoActual)]);
    if (avancePorPaso === undefined) {
        var length = Object.keys(ponderaciones).length;
        avancePorPaso = new Array(length);
        for (var i = 0; i < length; i++) {
            avancePorPaso[i] = 0;
        }
    }
    if (tipoDePaso === "pasoFormulario") {
        porcentajePorPaso = camposLlenos / totalDeCampos;
        porcentajePorPaso = porcentajePorPaso * percentStep;
        porcentajePorPaso = porcentajePorPaso.toFixed();
        avancePorPaso[Number(pasoActual) - 1] = Number(porcentajePorPaso);
    }
    for (var j = 0; j < avancePorPaso.length; j++) {
        avanceTotal += Number(avancePorPaso[j]);
    }
    $('.progressPerc').html(avanceTotal + '%');
    $('.activeProgress').animate({width: avanceTotal + '%'}, {queue: false});
    if (avanceTotal !== 0) {
        $('.progressTile').animate({fontSize: "20px"}, 200);
        setTimeout(function () {
            $('.progressTile').animate({fontSize: "12px"}, 200);
        }, 200)
    }
}

function consultarCodigoPostal(elemento, codigo) {
    var idCodigo = codigo;
    $("body").mLoading({
        text: "Cargando, espere por favor...",
        icon: "/images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: 'idCodigoPostal=' + idCodigo,
        url: '/solicitud/consultarCodigoPostal',
        success: function (data, textStatus) {
            var response = eval(data);
            if (elemento === 'direccionCliente_codigoPostal') {
                var colonia = $('#direccionCliente_colonia').val();
                $('#direccionCliente_colonia').html("");
                $('#direccionCliente_delegacion').html("");
                $('#direccionCliente_estado').html("");
                $('#direccionCliente_sucursal').html("");
                if (response.asentamientos && response.asentamientos !== null && response.asentamientos !== undefined) {
                    for (var x = 0; x < response.asentamientos.length; x++) {
                        $('#direccionCliente_colonia').append($('<option>', {
                            value: response.asentamientos[x],
                            text: response.asentamientos[x],
                            selected: true
                        }));
                    }
                    $('#direccionCliente_colonia').addClass('notEmpty');
                    $('#direccionCliente_colonia').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#direccionCliente_colonia').closest('.showOnFill'))] = 1;
                    if (response.municipio && response.municipio !== null && response.municipio !== undefined) {
                        $('#direccionCliente_delegacion').append($('<option>', {
                            value: response.municipio.id,
                            text: response.municipio.nombre,
                            selected: true
                        }));
                        $('#direccionCliente_delegacion').addClass('notEmpty');
                        $('#direccionCliente_delegacion').addClass('headingColor');
                        listaDeControl[$(".showOnFill").index($('#direccionCliente_delegacion').closest('.showOnFill'))] = 1;
                    }
                    if (response.estado && response.estado !== null && response.estado !== undefined) {
                        $('#direccionCliente_estado').append($('<option>', {
                            value: response.estado.id,
                            text: response.estado.nombre,
                            selected: true
                        }));
                        $('#direccionCliente_estado').addClass('notEmpty');
                        $('#direccionCliente_estado').addClass('headingColor');
                        listaDeControl[$(".showOnFill").index($('#direccionCliente_estado').closest('.showOnFill'))] = 1;
                    }
                    $('#direccionCliente_codigoPostal').addClass('notEmpty');
                    $('#direccionCliente_codigoPostal').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#direccionCliente_codigoPostal').closest('.showOnFill'))] = 1;
                    mostrarSiguienteCampo($(".showOnFill").index($('#direccionCliente_codigoPostal').closest('.showOnFill')));
                } else {
                    $('#direccionCliente_colonia').append($('<option>', {
                        value: null,
                        text: "C.P. no Registrado",
                        selected: true
                    }));
                }
                if (response.sucursales.noHaySucursales) {
                    swal({
                        title: "¡Importante!",
                        text: "Lo sentimos, aún no tenemos presencia en tu estado. Nos pondremos en contacto cuando podamos atender tu solicitud. Gracias.",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonText: "Me equivoque en la selección",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Ok, Enterado",
                        closeOnConfirm: false
                    }, function (isConfirm) {
                        if (isConfirm) {
                            avanzarPaso(0);
                            window.location.href = "https://micreditolibertad.com";
                        } else {
                            $('#direccionCliente_codigoPostal').val("");
                            $('#direccionCliente_colonia').html("");
                            $('#direccionCliente_delegacion').html("");
                            $('#direccionCliente_estado').html("");
                            $('#direccionCliente_sucursal').html("");
                        }
                        return false;
                    });
                } else if (response.sucursales.error) {
                    swal({
                        title: "¡Importante!",
                        text: "Lo sentimos, aún no tenemos presencia en tu estado. Nos pondremos en contacto cuando podamos atender tu solicitud. Gracias.",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonText: "Me equivoque en la selección",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Ok, Enterado",
                        closeOnConfirm: false
                    }, function (isConfirm) {
                        if (isConfirm) {
                            avanzarPaso(0);
                            window.location.href = "https://micreditolibertad.com";
                        } else {
                            $('#direccionCliente_codigoPostal').val("");
                            $('#direccionCliente_colonia').html("");
                            $('#direccionCliente_delegacion').html("");
                            $('#direccionCliente_estado').html("");
                            $('#direccionCliente_sucursal').html("");
                        }
                        return false;
                    });
                } else {
                    var html = "";
                    html += "<option value=''>Elija una opción...</option>";
                    for (var x = 0; x < response.sucursales.length; x++) {
                        html += "<option value='" + response.sucursales[x].id + "'> Sucursal " + response.sucursales[x].numeroDeSucursal + " - " + response.sucursales[x].nombre + "</option>";
                    }
                    $('#direccionCliente_sucursal').html(html);
                    listaDeSucursales = response.sucursales;
                }
            } else if (elemento === 'empleoCliente_codigoPostal') {
                $('#empleoCliente_delegacion').html("");
                $('#empleoCliente_delegacion').append($('<option>', {
                    value: response.municipio.id,
                    text: response.municipio.nombre,
                    selected: true
                }));
                $('#empleoCliente_estado').html("");
                $('#empleoCliente_estado').append($('<option>', {
                    value: response.estado.id,
                    text: response.estado.nombre,
                    selected: true
                }));
                $('#empleoCliente_codigoPostal').addClass('notEmpty');
                $('#empleoCliente_codigoPostal').addClass('headingColor');
                $('#empleoCliente_delegacion').addClass('notEmpty');
                $('#empleoCliente_delegacion').addClass('headingColor');
                $('#empleoCliente_estado').addClass('notEmpty');
                $('#empleoCliente_estado').addClass('headingColor');
                listaDeControl[$(".showOnFill").index($('#empleoCliente_codigoPostal').closest('.showOnFill'))] = 1;
                listaDeControl[$(".showOnFill").index($('#empleoCliente_delegacion').closest('.showOnFill'))] = 1;
                listaDeControl[$(".showOnFill").index($('#empleoCliente_estado').closest('.showOnFill'))] = 1;
                mostrarSiguienteCampo($(".showOnFill").index($('#empleoCliente_codigoPostal').closest('.showOnFill')));
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
    $("body").mLoading('hide');
}
//function operacionesPaso4() {
function operacionesBancos() {
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
        var hoverImage = $('img', this).data('hover');
        $('img', this).attr('src', '/images/' + hoverImage + '_white.png');
    });

    $(".bankButton").mouseout(function () {
        if ($(this).hasClass('active_green') === false) {
            var hoverImage = $('img', this).data('hover');
            $('img', this).attr('src', '/images/' + hoverImage + '.png');
        }
    });

    $('.brandingBox').click(function () {
        var thisIndex = $(this).index();
        var bankChoice = $('img', this).data('hover');
        $('.brandingBox').removeClass('active_green');
        $(".bankButton").each(function (index) {
            if (index !== thisIndex) {
                var hoverImage = $('img', this).data('hover');
                $('img', this).attr('src', '/images/' + hoverImage + '.png');
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

            $('.loginMethodHsbc').delay(2).fadeOut().fadeIn();
            validarFechaMemorable($('#login_method').val());
        }
    }
    $('#login_method').on('click', function () {
        validarFechaMemorable($(this).val());
    });


    function validarFechaMemorable(tipo) {
        if (tipo == "Con OTP") {
            $('#memorable').attr('disabled', 'disabled');
            $('.messageMemorableDiv').fadeOut();
        } else {
            $('#memorable').removeAttr('disabled');
            $('.messageMemorableDiv').fadeIn();
        }
    }
    $('.consultarBox').click(function () {

        if (!$(this).hasClass('exito')) {
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
        $('.nextBtn').addClass('sendBtn');
        var currentStep = $('#pasoAnterior').val();
        $('#circuloPaso' + (parseInt(currentStep) + 1)).click();
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
    if (reporteBuro !== "") {
        if (errorConsulta === "") {
            $('#accionesNormales').fadeOut();
            $('#accionesSuccess').fadeIn();
            $('#divAutorizacionBuro').fadeOut();
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

//function operacionesPaso5() {
function operacionesBuro() {
    winH = $(window).height();
    contentH = $('.contentHeight').outerHeight();
    headerH = $('.topHeader').outerHeight();
    footerH = $('.footerContainer').outerHeight();
    setFooter();
    $("#fechaAutorizacionConsulta").text(GetTodayDate());
    $("#divAutorizacionBuro").fadeIn();

    validarConsultaBuroCredito();


    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        $(this).parent().fadeOut();
    });

    $(".ccInfo").each(function (index) {
        thisParent = $(this);
        no_confimations = $(".ccInfo").length;

        $('.correctaBox', this).click(function () {
            $(this).parent().children('.correctaBox').removeClass('active_green');
            $(this).addClass('active_green');
            no_active = $('.correctaBox.active_green').length;
            parentIndex = $(this).parent().index(".ccInfo");
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

    });

    $('.avanzaBtn').click(function () {
        if ($(this).hasClass('colorGreen') || $(this).hasClass('buttonOrange')) {

            var currentStep = $('#pasoAnterior').val();
            if ($('#circuloPaso' + (parseInt(currentStep) + 1)).hasClass("grayCircle")) {
                $('.footerContainer .width600').animate({width: "490px"}, {queue: false});
                $('#circuloPaso' + (parseInt(currentStep) + 1)).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
                $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('nextBtn');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('sendBtn');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').addClass('paddingTop10');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').removeClass('paddingTop5');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').html("IR AL PASO " + (parseInt(currentStep) + 1));
                avancePorPaso[Number(currentStep - 1)] = Number(ponderaciones[("paso" + currentStep)]);
            }
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

//function operacionesPaso6() {
function operacionesResumen() {
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

    $('#medioDeContacto').change(function () {
        cargarOpcionesDeContacto($(this).val());
    });

    $('#opcionMedioDeContacto').change(function () {
        if ($(this).val() !== null && $(this).val() !== '') {
            $('.enviarSolicitud').addClass('blueButton pointer');
            $('.enviarSolicitud').css({'color': '#FFFFFF'});
        } else {
            $('.enviarSolicitud').removeClass('blueButton pointer');
            $('.enviarSolicitud').css({'color': '#005398'});
        }
    });

    $('.enviarSolicitud').click(function () {
        showValues();
        $('.enviarSolicitud').addClass('sendBtn');
    });

    $('#paso6IdOf').click(function () {
        if ($(this).hasClass("darkGray")) {
            openModal('identification_oficial');
        }
    });

    $('#paso6CompDom').click(function () {
        if ($(this).hasClass("darkGray")) {
            openModal('comprobante_domicilio');
        }
    });

    $('#paso6Docto').click(function () {
        if ($(this).hasClass("darkGray")) {
            $('#tipoDeDocumento').val('ComprobanteDeIngresos');
            openModal('documento_solicitud');
            inicializarDropzone('div#divDropzoneDocs', '.foldersBox');
        }
    });

    $('.foldersBox').click(function () {
        $('#doctoCargado').val($(this).data('box'));
    });

    $('#terminarSolicitud').click(function () {
        if ($(this).hasClass("blueButton")) {
            $('#resumen_solicitud').fadeOut();
            $("#valorMedioDeContacto").val($('#medioDeContacto').val());
            $("#opcMedioDeContacto").val($('#opcionMedioDeContacto').val());
            avanzarPaso($(this).data("numeroDePaso"));
        }
    });

    $('.solicitud_modal').click(function () {
        if ($(this).hasClass('blueButton') === true) {
            $('#resumen_solicitud').fadeIn();
        }
    });

}

function cargarOpcionesDeContacto(opcion) {
    $.ajax({
        type: 'POST',
        data: {
            opcionElegida: opcion
        },
        url: "/solicitud/obtenerOpciones",
        success: function (data, textStatus) {
            var resultado = eval(data);
            var html = "";
            if (resultado.length > 0) {
                $('.enviarSolicitud').removeClass('blueButton pointer');
                $('.enviarSolicitud').css({'color': '#005398'});
                html += "<option value=''>Elija una opción...</option>";
            } else {
                $('.enviarSolicitud').addClass('blueButton pointer');
                $('.enviarSolicitud').css({'color': '#FFFFFF'});
                html += "<option value=''>No hay opciones disponibles</option>";
            }
            for (var x = 0; x < resultado.length; x++) {
                html += "<option value='" + resultado[x].id + "'>" + resultado[x].nombre + "</option>";
            }
            $('#opcionMedioDeContacto').html(html);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function registrarUbicacion(datosSucursal) {
    ubicacionSucursal = JSON.parse(datosSucursal);
}

function operacionesConfirmacion() {
    $('.closeModal').click(function () {
        $(this).parent().parent().fadeOut();
    });
    $('.abrirMapa').click(function () {
        openModal('modalGoogleMaps');
        if (mapaGenerado() === false) {
            initMap();
            ubicarSucursal(ubicacionSucursal.coordenadas, ubicacionSucursal.ubicacion, ubicacionSucursal.numeroDeSucursal, ubicacionSucursal.nombre);
        } else {

        }
    });

}

function showValues() {
    var allInputs = $(".formValues").serializeArray();
    $.each(allInputs, function (i, field) {
        if ($('#formPaso input[name=' + field.name + ']').val() === undefined) {
            $(".sendValues").append('<input type="hidden" name="' + field.name + '" value="' + field.value + '" />');
        }
    });
}

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
    /*if ((headerH + contentH) >= (winH - footerH)) {
     $('.footerContainer').css({
     top: (headerH + contentH),
     bottom: 'auto'
     });
     } else {
     $('.footerContainer').css({
     bottom: 0,
     top: 'auto'
     });
     }*/
}

// ***************************** Inicio de Funciones Auxiliares

function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}


function avanzarPaso(paso) {
    var paso = paso;
    $('#modalSalvado').fadeIn();
    $('#siguientePaso').val(paso);
    $('#erroBubble').fadeOut();
    $('#erroBubble').html("");
    $.ajax({
        type: 'POST',
        data: $('#formFormulario').serialize(),
        url: '/solicitud/cambiarPaso',
        success: function (data, textStatus) {
            var respuesta = checkIfJson(data);
            if (respuesta.error) {
                var html = "<div class='container clearFix relative autoMargin width920'>";
                html += "<div class='errorBoxRed floatLeft'><div class='infoContainer4c'>";
                html += "<p class='center letterspacing0.5 font13 paddingLeft15 paddingTop15 paddingBottom10 colorWhite marginTop5'>" + respuesta.mensaje.toUpperCase() + "</p>";
                html += "</div></div><div class='buttonOrange line18 floatLeft' style='background-color: #fb5e48;'></div><div class='buttonOrange crosCircle floatLeft' style='background-color: #fb5e48;'>";
                html += "<p class='center marginTop5 font12 colorWhite'><i class='fa fa-times' aria-hidden='true'></i></p></div></div>";
                $('#erroBubble').html(html);
                $('#erroBubble').fadeIn();
            } else if (respuesta.sesionExpirada) {
                var mensaje = "<p style='text-align: justifiy;'>" + respuesta.mensaje + "</p>";
                sweetAlert({html: true,
                    title: "¡Atención!",
                    text: mensaje,
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonText: "Continuar mi solicitud",
                    closeOnConfirm: false
                }, function () {
                    window.location.href = $('.tuShortUrl').text().replace("TU URL: ", "");
                });
            } else {
                $('#pasoActual').hide();
                $('#pasoActual').html(data);
                $('#pasoActual').fadeIn();
                var tipoDePaso = $('#tipoDePaso').val();
                if (tipoDePaso === "pasoFormulario") {
                    inicializarFormulario();
                } else if (tipoDePaso === "consultaBancaria") {
                    habilitarBotonesAvance();
                    operacionesBancos();
                    verificacionSubPaso4();
                } else if (tipoDePaso === "consultaBuro") {
                    habilitarBotonesAvance();
                    operacionesBuro();
                    verificacionSubPaso5();
                } else if (tipoDePaso === "resumen") {
                    operacionesResumen();
                } else if (tipoDePaso === "confirmacion") {
                    operacionesConfirmacion();
                }
                actualizarProgreso(paso);
            }
            $('#modalSalvado').fadeOut();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $('#modalSalvado').fadeOut();
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
    $('.pasoTitle').html('paso ' + paso);
    $('.stepTitle').html($('#tituloDelPaso').val());
    calcularAvance();
}

function fillFB(data) {
    var json = JSON.parse(data);
    var x = 0;
    if (json["picture"]) {
        $("#imagenUsuario").html("<img class='userPicture floatLeft' src='" + json["picture"].data.url + "'/>");
        x++;
    }
    if (json["gender"]) {
        var sexo = (json["gender"] === "male" ? 1 : (json["gender"] === "female" ? 2 : 0));
        $("#cliente_genero").val(sexo);
        $("#cliente_genero").addClass('notEmpty');
        $("#cliente_genero").addClass('headingColor');
        x++;
    }
    if (json["relationship_status"]) {
        var estadoCivil = (json["relationship_status"] === "single" ? 1 : (json["relationship_status"] === "married" ? 2 : 0));
        $("#cliente_estadoCivil").val(estadoCivil);
        $("#cliente_estadoCivil").addClass('notEmpty');
        $("#cliente_estadoCivil").addClass('headingColor');
        x++;
    }
    if (json["first_name"]) {
        $("#cliente_nombre").val(json["first_name"]);
        $("#cliente_nombre").addClass('notEmpty');
        $("#cliente_nombre").addClass('headingColor');
        x++;
    }
    if (json["last_name"]) {
        $("#cliente_apellidoPaterno").val(json["last_name"]);
        $("#cliente_apellidoPaterno").addClass('notEmpty');
        $("#cliente_apellidoPaterno").addClass('headingColor');
        x++;
    }
    if (json["birthday"]) {
        if (json["birthday"].substring(0, 2)) {
            $("#cliente_fechaDeNacimiento_mes").val(json["birthday"].substring(0, 2));
            $("#cliente_fechaDeNacimiento_mes").addClass('notEmpty');
            $("#cliente_fechaDeNacimiento_mes").addClass('headingColor');
            x++;
        }
        if (json["birthday"].substring(3, 5)) {
            $("#cliente_fechaDeNacimiento_dia").val(json["birthday"].substring(3, 5));
            $("#cliente_fechaDeNacimiento_dia").addClass('notEmpty');
            $("#cliente_fechaDeNacimiento_dia").addClass('headingColor');
            x++;
        }
        if (json["birthday"].substring(6, 12)) {
            $("#cliente_fechaDeNacimiento_anio").val(json["birthday"].substring(6, 12));
            $("#cliente_fechaDeNacimiento_anio").addClass('notEmpty');
            $("#cliente_fechaDeNacimiento_anio").addClass('headingColor');
            x++;
        }
    }
    if (x > 0) {
        $('#pasoPrellenado').val("true");
        $('#nombreCliente').html('¡ Hola ' + json["first_name"] + ' !');
        $('.defaultBubble').fadeOut();
        $('.successBubble').fadeIn();
        $('.showOnFill').each(function (index) {
            mostrarSiguienteCampo(index);
        });
    }
}

function fillGoogle(data) {
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
            mostrarSiguienteCampo(index);
        });
    }
}

function loginInteractive() {
    loading($('.bankChoice').val());
    $('#loginInteractiveHtml').fadeOut();
    var data = {};
    $("#formLoginInteractive").find("input, select").each(function (i, field) {
        data[field.name] = field.value;
    });
    $.ajax({
        type: 'POST',
        data: 'data=' + JSON.stringify(data),
        url: '/solicitud/loginInteractive',
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


function authenticate() {
    loading($('.bankChoice').val());
    var data = {};
    data["password"] = $('#password').val();
    data["login"] = $('#login').val();
    data["provider_code"] = $('.bankChoice').val();
    data["customer_id"] = $('#customer_id').val();
    data["login_method"] = $('#login_method').val();
    data["memorable"] = $('#memorable').val();

    $('#loginInteractiveHtml').fadeIn();
    $.ajax({
        type: 'POST',
        data: 'data=' + JSON.stringify(data),
        url: '/solicitud/authenticate',
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

function generarClaves(persona) {
    var estados = ['AS', 'BC', 'BS', 'CC', 'CS', 'CH', 'CL', 'CM', 'DF', 'DG',
        'GT', 'GR', 'HG', 'JC', 'MC', 'MN', 'MS', 'NT', 'NL', 'OC', 'PL', 'QT',
        'QR', 'SP', 'SL', 'SR', 'TC', 'TS', 'TL', 'VZ', 'YN', 'ZS', 'NE'];
    if (persona === 'cliente') {
        var idEstado = Number($('#cliente_lugarDeNacimiento').val());
        var curp = generaCurp({
            nombre: $('#cliente_nombre').val(),
            apellido_paterno: $('#cliente_apellidoPaterno').val(),
            apellido_materno: $('#cliente_apellidoMaterno').val(),
            sexo: ($('#cliente_genero').val() === '1' ? 'H' : 'M'),
            estado: estados[(idEstado - 1)],
            fecha_nacimiento: [$('#cliente_fechaDeNacimiento_dia').val(), $('#cliente_fechaDeNacimiento_mes').val(), $('#cliente_fechaDeNacimiento_anio').val()]
        });
        if ($('#cliente_genero').val() === '1') {
            generoConyugue = '2';
        } else {
            conyugue = '1';
        }
        var rfc = generarHomoclave($('#cliente_nombre').val(), $('#cliente_apellidoPaterno').val(), $('#cliente_apellidoMaterno').val(), curp.substring(0, 10));
        $('#cliente_curp').val(curp);
        $('#cliente_rfc').val(rfc);
        $("#cliente_curp").addClass('notEmpty');
        $("#cliente_curp").addClass('headingColor');
        $("#cliente_rfc").addClass('notEmpty');
        $("#cliente_rfc").addClass('headingColor');
        listaDeControl[$(".showOnFill").index($('#cliente_curp').closest('.showOnFill'))] = 1;
        listaDeControl[$(".showOnFill").index($('#cliente_rfc').closest('.showOnFill'))] = 1;
    } else if (persona === 'conyugue') {
        var idEstado = Number($('#cliente_lugarDeNacimientoDelConyugue').val());
        var curp = generaCurp({
            nombre: $('#cliente_nombreDelConyugue').val(),
            apellido_paterno: $('#cliente_apellidoPaternoDelConyugue').val(),
            apellido_materno: $('#cliente_apellidoMaternoDelConyugue').val(),
            sexo: (generoConyugue === '1' ? 'H' : 'M'),
            estado: estados[(idEstado - 1)],
            fecha_nacimiento: [$('#cliente_fechaDeNacimientoDelConyugue_dia').val(), $('#cliente_fechaDeNacimientoDelConyugue_mes').val(), $('#cliente_fechaDeNacimientoDelConyugue_anio').val()]
        });
        var rfc = generarHomoclave($('#cliente_nombreDelConyugue').val(), $('#cliente_apellidoPaternoDelConyugue').val(), $('#cliente_apellidoMaternoDelConyugue').val(), curp.substring(0, 10));
        $('#cliente_curpDelConyugue').val(curp);
        $('#cliente_rfcDelConyugue').val(rfc);
        $("#cliente_curpDelConyugue").addClass('notEmpty');
        $("#cliente_curpDelConyugue").addClass('headingColor');
        $("#cliente_rfcDelConyugue").addClass('notEmpty');
        $("#cliente_rfcDelConyugue").addClass('headingColor');
        listaDeControl[$(".showOnFill").index($('#cliente_curpDelConyugue').closest('.showOnFill'))] = 1;
        listaDeControl[$(".showOnFill").index($('#cliente_rfcDelConyugue').closest('.showOnFill'))] = 1;
    }
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

        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/banamex.png\"/></center>";
    } else if (bank == 'bancomer') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/bancomer.png\"/></center>";
    } else if (bank == 'american_express') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/american_express.png\"/></center>";
    } else if (bank == 'santander') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/santander.png\"/></center>";
    } else if (bank == 'banorte') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/banorte.png\"/></center>";
    } else if (bank == 'hsbc') {
        html += "<img class=\"width120 blockAuto paddingTop20\" src=\"/images/hsbc.png\"/></center>";
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
    return html_base + html
}

function consultarBancos() {
    var banco = $('.bankChoice').val();
    if (banco) {
        var cliente = $('#clientNo').val();
        var clave = $('#clave').val();
        var token = $('#tokenNo').val();
        var intentos = $('#intentos').val();
        if (cliente && clave && token) {
            loadBar();
            $.ajax({
                type: 'POST',
                data: 'banco=' + banco + "&cliente=" + cliente + "&clave=" + clave + "&token=" + token + "&intentos=" + intentos,
                url: '/solicitud/consultaBancos',
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

function consultarBuro() {
    loadBar("45%");
    var tarjeta = $('#tCredito').val();
    var hipoteca = $('#creditoH').val();
    var creditoAutomotriz = $('#creditoA').val();
    if (tarjeta && hipoteca && creditoAutomotriz) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            loadBar("75%");
            $.ajax({
                type: 'POST',
                data: 'tarjeta=' + tarjeta + "&numeroTarjeta=" + numeroTarjeta + "&hipoteca=" + hipoteca + "&creditoAutomotriz=" + creditoAutomotriz,
                url: '/solicitud/consultarBuroDeCredito',
                success: function (data, textStatus) {
                    loadBar("100%");
                    var respuesta = checkIfJson(data);
                    $('.loadingBar').fadeOut();
                    $('.creditBtns').fadeOut();
                    $('.loadingContainer .buttonM').delay(1000).fadeIn();
                    if (respuesta.status === 200) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesSuccess').fadeIn();
                        $('#divAutorizacionBuro').fadeOut();
                        $('.avanzaBtn').click();
                        $('.correctaBox').unbind('click');
                    } else if (respuesta.problemasBuro) {
                        var mensaje = "Algo salió mal, por favor verifica los siguientes datos: <br /> <ul>";
                        for (var i = 0; i < respuesta.problemasBuro.length; i++) {
                            mensaje += "<li>" + respuesta.problemasBuro[i].segmento + " ( Paso " + respuesta.problemasBuro[i].pasoError.numeroDePaso + " - " + respuesta.problemasBuro[i].pasoError.tituloPaso + ") </li>";
                        }
                        mensaje += "</ul>";
                        sweetAlert({html: true, title: "¡Oops!", text: mensaje, type: "warning"});
                        $('#accionesNormales').fadeIn();
                        $('#divAutorizacionBuro').fadeIn();
                    } else if (respuesta.error) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesError').fadeIn();
                        $('#divAutorizacionBuro').fadeOut();
                        $('.avanzaBtn').click();
                    }
                    showValues();
                    $('.nextBtn').addClass('sendBtn');
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
    if (tarjeta && hipoteca && creditoAutomotriz && nombre && apellidoPaterno && apellidoMaterno && fechaNac && calle && noExterior && colonia && municipio) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            consultarBuro();
        }
    } else if (!nombre || !apellidoPaterno || !apellidoMaterno || !fechaNac) {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados en el paso 1", "warning");
    } else if (!calle || !noExterior || !colonia || !municipio) {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados en el paso 2", "warning");
    } else {
        sweetAlert("Antes de continuar...", "Por favor llena los datos solicitados de este paso", "warning");
    }
}


function reintentar() {
    $('#accionesError').hide();
    $('#accionesNormal').fadeIn();
    consultarBancos();
}


function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}


function formatCurrency(n, currency) {
    return currency + " " + n.toFixed(2).replace(/./g, function (c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}

function inicializarCamara(cara) {
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
        url: '/solicitud/guardarFoto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.status === 200) {
                sweetAlert("¡Enhorabuena!", "La foto se subio correctamente", "success");
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
        inicializarDropzone('div#divDropzoneIds', '.foldersBox');
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().next('.idView').delay(300).fadeIn();
        $('#label2Ids').addClass('active_blue');
        $('#label1Ids').removeClass('active_blue');
        $('#label3Ids').removeClass('active_blue');
        $('#label3Ids').addClass('gray');
        $('#label1Ids').addClass('gray');
        $('#label2Ids').removeClass('gray');
        cara = "frente";
    });

    $('.ineId').click(function () {
        $('#tipoDeDocumento').val('Identicaciones');
        $('#nombreDeLaIdentificacion').html('frente de tu Credencial de Elector');
        inicializarDropzone('div#divDropzoneIds', '.foldersBox');
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().next('.idView').delay(300).fadeIn();
        $('#label2Ids').addClass('active_blue');
        $('#label1Ids').removeClass('active_blue');
        $('#label3Ids').removeClass('active_blue');
        $('#label3Ids').addClass('gray');
        $('#label1Ids').addClass('gray');
        $('#label2Ids').removeClass('gray');
        cara = "frente";
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

    $('#subirIdentificacion').click(function () {
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $('.file_uploads').fadeIn();
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
        enviarShortUrl();
    });

    $('.cfe').click(function () {
        $('#tipoDeDocumento').val('reciboCfe');
        $('#nombreDelDocumento').html('Recibo de C.F.E.');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
        $(this).parent().parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
        $('#label2Comp').addClass('active_blue');
        $('#label1Comp').removeClass('active_blue');
        $('#label1Comp').addClass('gray');
        $('#label2Comp').removeClass('gray');
        cara = "frente";
    });
    $('.telmex').click(function () {
        $('#tipoDeDocumento').val('reciboTelmex');
        $('#nombreDelDocumento').html('Recibo de Telmex');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
        $(this).parent().parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
        $('#label2Comp').addClass('active_blue');
        $('#label1Comp').removeClass('active_blue');
        $('#label1Comp').addClass('gray');
        $('#label2Comp').removeClass('gray');
        cara = "frente";
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
        $('#fotoFrente').hide();
        $('#fotoVuelta').hide();
        $('#archivoFrente').fadeIn();
        $('#archivoVuelta').fadeOut();
        $('.file_uploads').fadeOut();
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
            $('#fotoFrente').hide();
            $('#fotoVuelta').hide();
            $('#archivoFrente').fadeIn();
            $('#archivoVuelta').fadeOut();
            $('.file_uploads').fadeOut();
            $('#webcamCaptureIds').hide();
        }
    });
}

function habilitarTerminarSolicitud() {
    var no_buttons = $('.greenClick').length;
    var no_active = $('.greenClick.colorGreen').length;
    var pasoActual = $('#pasoAnterior').val();
    var cantidadRequerida = Number((($('#cantidadSolicitada').val() !== undefined || $('#cantidadSolicitada').val() !== null) ? $('#cantidadSolicitada').val() : 0));
    var doctosSubidos = $('.checkmark').length;
    if (no_active === no_buttons && cantidadRequerida === doctosSubidos) {
        avancePorPaso[Number(pasoActual - 1)] = Number(ponderaciones[("paso" + pasoActual)]);
        $('.solicitud_modal').addClass('blueButton colorWhite pointer');
    } else {
        //avancePorPaso[Number(pasoActual - 1)] = 0;
        //$('.solicitud_modal').removeClass('blueButton colorWhite pointer');
    }
    calcularAvance();
}

function enviarShortUrl() {
    var documento = $('#tipoDeDocumento').val();
    $.ajax({
        type: 'POST',
        data: {
            opcionElegida: documento
        },
        url: "/solicitud/enviarShortUrl",
        success: function (data, textStatus) {
            var resultado = eval(data);
            if (resultado.mensajeEnviado) {
                if (documento === 'reciboCfe' || documento === 'reciboTelmex') {
                    closeModal('comprobante_domicilio');
                } else if (documento === 'Pasaportes' || documento === 'Identicaciones') {
                    closeModal('identification_oficial');
                }
                var html = '<svg class="checkmark" style="margin: 5% auto;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>';
                html += '<p class="gray center marginTop20 marginBottom30"> ¡El mensaje fue enviado correctamente! <br/> Lo recibirás en breve. </p>';
                $('.shortUrlAction').html(html);
            } else {
                sweetAlert("Oops...", "El mensaje no pudó ser enviado, intenta nuevamente en unos minutos.", "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

Dropzone.autoDiscover = false;

function inicializarDropzone(elemento, boton) {

    var kosmosDropzone = new Dropzone(elemento, {
        url: "/solicitud/consultarOCR",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        params: {'docType': $('#tipoDeDocumento').val(), 'cara': cara},
        maxFiles: 1,
        maxFilesize: 5,
        acceptedFiles: ".pdf, .png, .jpg, .jpeg",
        autoQueue: true,
        createImageThumbnails: false,
        clickable: boton
    });
    kosmosDropzone.on("addedfile", function (file) {

        $('.dz-preview').hide();
        $('.barraProgresoComp').fadeIn();
        $('#progresoConsultaIds').fadeIn();
    });
    kosmosDropzone.on("sending", function (file, xhr, formData) {

        formData.set("docType", $('#tipoDeDocumento').val());
        formData.set("cara", cara);
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);

        if (respuesta.vigente === true || respuesta.exito === true) {
            var pasoActual = $('#pasoInicial').val();
            if (pasoActual === "0") {
                $('#formLogin').submit();
            } else {
                var direccion = (respuesta.calle ? respuesta.calle : respuesta.direccion);
                if (respuesta.direccion) {
                    sweetAlert({html: false, title: "¡Excelente!", text: "El documento se ha subido correctamente.", type: "success"});
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
                        mostrarSiguienteCampo(index);
                    });
                    habilitarTerminarSolicitud();
                    if (document.getElementById('login')) {
                        $('#submitLogin').click();
                    }
                } else if (respuesta.motivosRechazo) {

                    var mensaje = "Se presentaron los siguientes problemas al analizar el documento: <br /> <ul>";
                    for (var i = 0; i < respuesta.motivosRechazo.length; i++) {
                        mensaje += "<li><strong>" + (i + 1) + "</strong> -" + respuesta.motivosRechazo[i] + " </li>";
                    }
                    mensaje += "</ul>";
                    sweetAlert({html: true, title: "¡Oops!", text: mensaje, type: "warning"});
                } else if (respuesta.exito === true && respuesta.idArchivo) {
                    var posicion = $('#doctoCargado').val();

                    var cantidadRequerida = $('#cantidadSolicitada').val();

                    var html = '<svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>';
                    html += '<p class="center letterspacing1.4 gray">Cargada Correctamente</p>';
                    $('#uploadDocto' + posicion).html(html);
                    if ($('.checkmark').length === Number(cantidadRequerida)) {
                        closeModal('documento_solicitud');
                        $('#paso6Docto').removeClass('darkGray');
                        $('#paso6Docto').addClass('colorGreen');
                    }
                    habilitarTerminarSolicitud();
                } else if (respuesta.exito || (respuesta.nombre && (respuesta.apellidoPaterno || respuesta.apellidoMaterno))) {
                    sweetAlert({html: true, title: "¡Excelente!", text: "El documento se ha subido correctamente.", type: "success"});
                    if ($('#tipoDeDocumento').val() === "Identicaciones" && cara === "frente") {
                        $('#archivoFrente').fadeOut();
                        $('#archivoVuelta').fadeIn();
                        cara = "vuelta";
                        $('#label3Ids').addClass('active_blue');
                        $('#label2Ids').removeClass('active_blue');
                        $('#label1Ids').removeClass('active_blue');
                        $('#label2Ids').addClass('gray');
                        $('#label1Ids').addClass('gray');
                        $('#label3Ids').removeClass('gray');
                        var html = '<svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>';
                        html += '<p class="center letterspacing1.4 gray">Cargada Correctamente</p>';
                        $('#uploadFrente').html(html);
                    } else if ($('#tipoDeDocumento').val() === "Identicaciones" && cara === "vuelta") {
                        var html = '<svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>';
                        html += '<p class="center letterspacing1.4 gray">Cargada Correctamente</p>';
                        $('#uploadVuelta').html(html);
                        closeModal('identification_oficial');
                        $('#paso6IdOf').addClass('colorGreen');
                        $('#paso6IdOf').removeClass('darkGray');
                        $('#pasoPrellenado').val("true");
                        mostrarSiguienteCampo(0);
                        habilitarTerminarSolicitud();
                        if (document.getElementById('login')) {
                            $('#submitLogin').click();
                        }
                    } else if ($('#tipoDeDocumento').val() === "Pasaportes") {
                        closeModal('identification_oficial');
                        $('#paso6IdOf').addClass('colorGreen');
                        $('#paso6IdOf').removeClass('darkGray');
                        $('#pasoPrellenado').val("true");
                        mostrarSiguienteCampo(0);
                        habilitarTerminarSolicitud();
                        if (document.getElementById('login')) {
                            $('#submitLogin').click();
                        }
                    }
                } else if (respuesta.nombre && (respuesta.apellidos || respuesta.noDocumento)) {
                    sweetAlert({html: true, title: "¡Excelente!", text: "Se obtuvieron los siguientes datos: <br/> <strong>Nombre:</strong>" + respuesta.nombre + "<br/><strong>Apellidos: </strong>" + respuesta.apellidos + "<br/><strong>Documento No.: </strong> " + respuesta.noDocumento + "</br>", type: "success"});
                    closeModal('identification_oficial');
                    $('#paso6IdOf').addClass('colorGreen');
                    $('#paso6IdOf').removeClass('darkGray');
                    $('#pasoPrellenado').val("true");
                    $('.showOnFill').each(function (index) {
                        mostrarSiguienteCampo(index);
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
        $('.barraProgresoComp').fadeOut();
        $('#progresoConsultaIds').fadeOut();
    });
    kosmosDropzone.on("error", function (file, response) {
        $('.barraProgresoComp').fadeOut();
        $('#progresoConsultaIds').fadeOut();
        sweetAlert("Oops...", "Ocurrio un problema al consultar los datos del documento", "error");
        this.removeAllFiles();
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

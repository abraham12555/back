var winH;
var contentH;
var headerH;
var footerH;
var avancePorPaso
var elementos;
var ponderaciones;
var slideIndex = 1;
var listaDeControl
var generoConyugue;
showSlides(slideIndex);

function inicializarFormulario() {
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        console.log('Estoy en mobile')
        $('.phoneCapture').hide()
        $('.camCapture').hide()
        $('.mobileCapture').show()
        $('.desktopCapture').hide()
    } else {
        $('.mobileCapture').hide()
        $('.desktopCapture').show()
    }
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
    //console.log(listaDeControl);

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    $('.overlay').click(function () {
        //console.log("Click fuera del modal");
        //$(this).parent().fadeOut();
        return false;
    });
    var pasoInicial = $('#pasoInicial').val();
    var tipoDePaso = $('#tipoDePaso').val();
    operacionesModal();
    //submitNextPage();
    actualizarProgreso(pasoInicial);
    //console.log(tipoDePaso);
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
        operacionesLogin();
    }

    habilitarBotonesAvance();

    $('.showOnFill').each(function (index) {
        //verificarCambios(index);
        mostrarSiguienteCampo(index);
    });
}

function habilitarBotonesAvance() {
    $('.botonCambioDePaso').click(function () {
        console.log("Dando click en boton para avanzar...");
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
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides
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
            avancePorPaso[i] = Number(avance[("paso" + (i+1))]);
        }
    } else {
        console.log(avancePorPaso);
    }
    console.log(avancePorPaso);
    calcularAvance();
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

    $('.showOnFill').change(function (index) {
        var thisStep = $(this);
        var maxIndex = $('.showOnFill').length;
        //console.log("Indice: " + index);
        $('.formValues', this).change(function () {
            //console.log("Registrando: " + $(this).val() + " -adfafdadfsa:  " + $(this));
            if ($(this).val() !== '') {
                //console.log("No esta vacio");
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
                console.log("--->" + $(this).attr('id'));
                if ($(this).attr('id') === 'cliente_nombre') {
                    //console.log("Si entraaaaaaaa");
                    $('#nombreCliente').html('¡Hola! ' + $(this).val());
                } else if ($(this).attr('id') === 'cliente_nacionalidad') {
                    console.log("Id-->" + $('#cliente_nacionalidad').val());
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
                        }, function () {
                            avanzarPaso(0);
                            window.location.href = "http://nuuptech.xyz/kosmos-app";
                            return false;
                        });
                    }
                } else if ($(this).attr('id') === 'cliente_estadoCivil') {
                    var estadoCivilTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='cliente_estadoCivil']");
                    //console.log("La lista resultante es: " + listaElementos.length + " - " + estadoCivilTmp);
                    $(listaElementos).each(function () {
                        //console.log("EstadoCivilTmp:" + estadoCivilTmp + " - " + $(this).data('valorDependencia') + " - data-id: " + $(this).data('id') + " - $(this).index(): " + $(this).index());
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
                            //console.log("Indice de conyugue: " + (index + $(this).data('id')));
                            listaDeControl[(index + $(this).data('id'))] = -1;
                        }
                    });
                    console.log(listaDeControl);
                } else if ($(this).attr('id') === 'direccionCliente_tipoDeVivienda') {
                    var tipoDeViviendaTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='direccionCliente_tipoDeVivienda']");
                    //console.log("La lista resultante es: " + listaElementos.length + " - " + tipoDeViviendaTmp);
                    $(listaElementos).each(function () {
                        //console.log("tipoDeViviendaTmp:" + tipoDeViviendaTmp + " - " + $(this).data('valorDependencia') + " - data-id: " + $(this).data('id') + " - $(this).index(): " + $(this).index());
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
                            //console.log("Indice de conyugue: " + ($(this).data('id')));
                            listaDeControl[$(this).data('id')] = -1;
                        }
                    });
                    //console.log(listaDeControl);
                } else if (($('#cliente_rfc').val() === '') && ($('#cliente_curp').val() === '')) {
                    if (($('#cliente_nombre').val() !== '' && $('#cliente_nombre').val() !== undefined) && ($('#cliente_apellidoPaterno').val() !== '' && $('#cliente_apellidoPaterno').val() !== undefined) && ($('#cliente_apellidoMaterno').val() !== '' && $('#cliente_apellidoMaterno').val() !== undefined) && ($('#cliente_genero').val() !== '' && $('#cliente_genero').val() !== undefined) && ($('#cliente_lugarDeNacimiento').val() !== '' && $('#cliente_nombre').val() !== undefined)) {
                        generarClaves('cliente');
                    } else {
                        //console.log("No entra a la validación");
                    }
                } else if (($('#cliente_rfcDelConyugue').val() === '') && ($('#cliente_curpDelConyugue').val() === '')) {
                    if (($('#cliente_nombreDelConyugue').val() !== '' && $('#cliente_nombreDelConyugue').val() !== undefined) && ($('#cliente_apellidoPaternoDelConyugue').val() !== '' && $('#cliente_apellidoPaternoDelConyugue').val() !== undefined) && ($('#cliente_apellidoMaternoDelConyugue').val() !== '' && $('#cliente_apellidoMaternoDelConyugue').val() !== undefined) && (generoConyugue) && ($('#cliente_lugarDeNacimientoDelConyugue').val() !== '' && $('#cliente_lugarDeNacimientoDelConyugue').val() !== undefined)) {
                        generarClaves('conyugue');
                    } else {
                        //console.log("No entra a la validación");
                    }
                } else if (($('#empleoCliente_ingresosFijos').val() !== '') && ($('#empleoCliente_ingresosVariables').val() !== '')) {
                    var sumatoria = Number($('#empleoCliente_ingresosFijos').val()) + Number($('#empleoCliente_ingresosVariables').val());
                    $('#empleoCliente_ingresosTotales').val(sumatoria);
                    $('#empleoCliente_ingresosTotales').addClass('notEmpty');
                    $('#empleoCliente_ingresosTotales').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#empleoCliente_ingresosTotales').closest('.showOnFill'))] = 1;
                    //console.log(listaDeControl);
                }
            } else {
                //console.log("Si esta vacio");
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }
            //verificarCambios(index);
            mostrarSiguienteCampo(index);
            //console.log("A punto de calcular el avance....");
            calcularAvance();
            window.scrollTo(0, document.body.scrollHeight);
        });
        $('.formValues', this).focusin(function () {
            //console.log("Input " + $(this).attr('id') + " - Indice " + index + " ha tomado el foco");
            //verificarCambios(index);
        });
    });

    $('.showOnFill').each(function (index) {
        var thisStep = $(this);
        var maxIndex = $('.showOnFill').length;
        //console.log("Indice: " + index);
        $('.formValues', this).change(function () {
            //console.log("Registrando: " + $(this).val() + " -adfafdadfsa:  " + $(this));
            if ($(this).val() !== '') {
                //console.log("No esta vacio");
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
                console.log("--->" + $(this).attr('id'));
                if ($(this).attr('id') === 'cliente_nombre') {
                    //console.log("Si entraaaaaaaa");
                    $('#nombreCliente').html('¡Hola! ' + $(this).val());
                } else if ($(this).attr('id') === 'cliente_nacionalidad') {
                    console.log("Id-->" + $('#cliente_nacionalidad').val());
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
                        }, function () {
                            avanzarPaso(0);
                            window.location.href = "http://nuuptech.xyz/kosmos-app";
                            return false;
                        });
                    }
                } else if ($(this).attr('id') === 'cliente_estadoCivil') {
                    var estadoCivilTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='cliente_estadoCivil']");
                    //console.log("La lista resultante es: " + listaElementos.length + " - " + estadoCivilTmp);
                    $(listaElementos).each(function () {
                        //console.log("EstadoCivilTmp:" + estadoCivilTmp + " - " + $(this).data('valorDependencia') + " - data-id: " + $(this).data('id') + " - $(this).index(): " + $(this).index());
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
                            //console.log("Indice de conyugue: " + (index + $(this).data('id')));
                            listaDeControl[(index + $(this).data('id'))] = -1;
                        }
                    });
                    console.log(listaDeControl);
                } else if ($(this).attr('id') === 'direccionCliente_tipoDeVivienda') {
                    var tipoDeViviendaTmp = $(this).val();
                    var listaElementos = $(".showOnFill[data-depende-de='direccionCliente_tipoDeVivienda']");
                    //console.log("La lista resultante es: " + listaElementos.length + " - " + tipoDeViviendaTmp);
                    $(listaElementos).each(function () {
                        //console.log("tipoDeViviendaTmp:" + tipoDeViviendaTmp + " - " + $(this).data('valorDependencia') + " - data-id: " + $(this).data('id') + " - $(this).index(): " + $(this).index());
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
                            //console.log("Indice de conyugue: " + ($(this).data('id')));
                            listaDeControl[$(this).data('id')] = -1;
                        }
                    });
                    //console.log(listaDeControl);
                } else if (($('#cliente_rfc').val() === '') && ($('#cliente_curp').val() === '')) {
                    if (($('#cliente_nombre').val() !== '' && $('#cliente_nombre').val() !== undefined) && ($('#cliente_apellidoPaterno').val() !== '' && $('#cliente_apellidoPaterno').val() !== undefined) && ($('#cliente_apellidoMaterno').val() !== '' && $('#cliente_apellidoMaterno').val() !== undefined) && ($('#cliente_genero').val() !== '' && $('#cliente_genero').val() !== undefined) && ($('#cliente_lugarDeNacimiento').val() !== '' && $('#cliente_nombre').val() !== undefined)) {
                        generarClaves('cliente');
                    } else {
                        //console.log("No entra a la validación");
                    }
                } else if (($('#cliente_rfcDelConyugue').val() === '') && ($('#cliente_curpDelConyugue').val() === '')) {
                    if (($('#cliente_nombreDelConyugue').val() !== '' && $('#cliente_nombreDelConyugue').val() !== undefined) && ($('#cliente_apellidoPaternoDelConyugue').val() !== '' && $('#cliente_apellidoPaternoDelConyugue').val() !== undefined) && ($('#cliente_apellidoMaternoDelConyugue').val() !== '' && $('#cliente_apellidoMaternoDelConyugue').val() !== undefined) && (generoConyugue) && ($('#cliente_lugarDeNacimientoDelConyugue').val() !== '' && $('#cliente_lugarDeNacimientoDelConyugue').val() !== undefined)) {
                        generarClaves('conyugue');
                    } else {
                        //console.log("No entra a la validación");
                    }
                } else if (($('#empleoCliente_ingresosFijos').val() !== '') && ($('#empleoCliente_ingresosVariables').val() !== '')) {
                    var sumatoria = Number($('#empleoCliente_ingresosFijos').val()) + Number($('#empleoCliente_ingresosVariables').val());
                    $('#empleoCliente_ingresosTotales').val(sumatoria);
                    $('#empleoCliente_ingresosTotales').addClass('notEmpty');
                    $('#empleoCliente_ingresosTotales').addClass('headingColor');
                    listaDeControl[$(".showOnFill").index($('#empleoCliente_ingresosTotales').closest('.showOnFill'))] = 1;
                    //console.log(listaDeControl);
                }
            } else {
                //console.log("Si esta vacio");
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }
            //verificarCambios(index);
            mostrarSiguienteCampo(index);
            console.log("A punto de calcular el avance....");
            calcularAvance();
            window.scrollTo(0, document.body.scrollHeight);
        });
        $('.formValues', this).focusin(function () {
            //console.log("Input " + $(this).attr('id') + " - Indice " + index + " ha tomado el foco");
            //verificarCambios(index);
        });
    });

    $('.formStep .confirmDiv .buttonM').click(function () {
        //console.log("Si entraaaaa al click");
        if ($(this).parent().parent().is(':hidden') === true) {
            //console.log("Pos que esta oculto!!!");
        } else {
            if ($(this).parent().parent().hasClass('lastStep') === true) {
                //console.log("Preparando el armado del form...");
                //$(".formValues").on("change", showValues);
                //showValues();
                $('.nextBtn').addClass('sendBtn');
                //submitNextPage();
                var currentStep = $('#pasoAnterior').val();
                if ($('#circuloPaso' + (parseInt(currentStep) + 1)).hasClass("grayCircle")) {
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
    console.log("Inicializando typeahead....");
    $('#direccionCliente_codigoPostal_remote .typeahead').typeahead({minLength: 3}, {
        name: 'codigos',
        display: 'value',
        source: bestPictures,
        limit: 10,
        templates: {
            empty: [
                '<div class="empty-message">',
                'No hay coincidencias.',
                '</div>'
            ].join('\n')
        }
    });
    $('#direccionCliente_codigoPostal_remote .typeahead').bind('typeahead:select', function (ev, suggestion) {
        consultarCodigoPostal(suggestion);
    });

    //submitNextPage();
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
    //console.log("index: " + index + " - elementosPorGrupo:" + elementosPorGrupo + " - elementosLLenosPorGrupo: " + elementosLLenosPorGrupo);
    if (elementosPorGrupo === elementosLLenosPorGrupo) {
        listaDeControl[index] = 1;
        if ((index + 1) < elementos.length) {
            var indice = index;
            var elementoMostrado = false;
            while ((indice + 1) < elementos.length && elementoMostrado === false) {
                if (listaDeControl[indice + 1] === 0) {
                    //console.log("Mostrando el siguiente paso....");
                    $('.showOnFill').eq(indice + 1).fadeIn();
                    $('.showOnFill').eq(indice + 1).css({'display': 'inline'});
                    if ($('.showOnFill').eq(indice + 1).children('.formValues')) {
                        $('.showOnFill').eq(indice + 1).children('.formValues').focus();
                    } else if ($('.showOnFill').eq(indice + 1).children().children('.formValues')) {
                        $('.showOnFill').eq(indice + 1).children().children('.formValues').focus();
                    } else if ($('.showOnFill').eq(indice + 1).children().children().children('.formValues')) {
                        $('.showOnFill').eq(indice + 1).children().children().children('.formValues').focus();
                    }
                    checkInputs();
                    //console.log("El siguiente campo es requerido?? " + $('.showOnFill').eq(indice + 1).hasClass('required'));
                    if ($('.showOnFill').eq(indice + 1).hasClass('required')) {
                        elementoMostrado = true;
                    }
                } else if (listaDeControl[indice + 1] === 1) {
                    //console.log("Mostrando el siguiente paso....");
                    $('.showOnFill').eq(indice + 1).fadeIn();
                    $('.showOnFill').eq(indice + 1).css({'display': 'inline'});
                    $('.showOnFill').eq(indice + 1).children('.formValues').focus();
                    checkInputs();
                } else if (listaDeControl[indice + 1] === -1) {
                    //console.log("Mostrando el siguiente paso....");
                    $('.showOnFill').eq(indice + 1).fadeOut();
                    $('.showOnFill').eq(indice + 1).css({'display': 'none'});
                    checkInputs();
                }
                indice++;
            }
        }
    } else if (prellenado === "true") {
        if ($('.showOnFill').eq(index + 1).hasClass('noMostrar') === false) {
            $('.showOnFill').eq(index + 1).fadeIn();
            $('.showOnFill').eq(index + 1).css({'display': 'inline'});
            checkInputs();
        }
    }

    var elementosLlenosVisibles = $('.notEmpty:visible').length;
    var totalElementosVisibles = $('.formStep:visible .formValues').length;
    var totalElementosRequeridos = $('.required .formValues').length;
    var totalElementosRequeridosLLenos = $('.required .notEmpty').length;
    totalElementosVisibles -= $('.formStep:visible .noMostrar').length;
    //console.log("index: " + index + " - elementosLlenosVisibles:" + elementosLlenosVisibles + " - totalElementosVisibles: " + totalElementosVisibles + " - totalElementosRequeridos: " + totalElementosRequeridos + " - totalElementosRequeridosLLenos: " + totalElementosRequeridosLLenos);
    if ((elementosLlenosVisibles === totalElementosVisibles) || (totalElementosRequeridos === totalElementosRequeridosLLenos)) {
        if (prellenado === "true") {
            //console.log("Mostrar el boton de confirmacion");
            $('.formStep:visible .confirmDiv').fadeIn();
        } else {
            //console.log("Hacer click en el boton de confirmacion");
            //$('.formStep:visible .confirmDiv').fadeIn();
            $('.formStep:visible .confirmDiv .buttonM').click();
        }
        /*if (pasoActual === "1" && index === 6) {
         generarClaves();
         }*/
    } else {
        if ($('#circuloPaso' + (parseInt(pasoActual) + 1)).hasClass("sendBtn")) {
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).animate({width: "36px", height: "36px", marginTop: "5px"}, {queue: false});
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('nextBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('sendBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').addClass('paddingTop5');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').removeClass('paddingTop10');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').html($('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').text().replace("IR AL PASO ", ""));
        }
    }
}

function verificarCambios(index) {
    var maxIndex = $('.showOnFill').length;
    var filledLength = $('.notEmpty:visible').length;
    var thisLength = $('.formValues:visible').length;
    var prellenado = $('#pasoPrellenado').val();
    var pasoActual = $('#pasoAnterior').val();
    //console.log("Index: " + index + " - not Empty: " + filledLength + " -  total: " + thisLength + " - prellenado: " + prellenado);

    if (filledLength === thisLength) {
        if ((index + 1) < maxIndex) {
            //console.log("Mostrando el siguiente paso....");
            $('.showOnFill').eq(index + 1).fadeIn();
            $('.showOnFill').eq(index + 1).css({'display': 'inline'});
            $('.showOnFill').eq(index + 1).children('.formValues').focus();
            checkInputs();
        } else {
            //console.log("Else 1");
        }
    } else if (prellenado === "true") {
        if ($('.showOnFill').eq(index + 1).hasClass('noMostrar') === false) {
            $('.showOnFill').eq(index + 1).fadeIn();
            $('.showOnFill').eq(index + 1).css({'display': 'inline'});
            checkInputs();
        }
    }
    var totalLength = $('.formStep:visible .formValues').length;
    //console.log("totalLength: " + totalLength);
    if (filledLength === totalLength) {
        if (prellenado === "true") {
            //console.log("Mostrar el boton de confirmacion");
            $('.formStep:visible .confirmDiv').fadeIn();
        } else {
            //console.log("Hacer click en el boton de confirmacion");
            //$('.formStep:visible .confirmDiv').fadeIn();
            $('.formStep:visible .confirmDiv .buttonM').click();
        }
        /*if (pasoActual === "1" && index === 6) {
         generarClaves();
         }*/
    } else {
        if ($('#circuloPaso' + (parseInt(pasoActual) + 1)).hasClass("sendBtn")) {
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).animate({width: "36px", height: "36px", marginTop: "5px"}, {queue: false});
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('nextBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).removeClass('sendBtn');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').addClass('paddingTop5');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').removeClass('paddingTop10');
            $('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').html($('#circuloPaso' + (parseInt(pasoActual) + 1)).children('p').text().replace("IR AL PASO ", ""));
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
    //console.log("Resultado del calculo: totalDeCampos: " + totalDeCampos + " -- camposLlenos: " + camposLlenos + " -- porcentajeCalculado: " + porcentajePorPaso + "  -- avanceTotal: " + avanceTotal);
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
            $('#direccionCliente_delegacion').append($('<option>', {
                value: response.municipio.id,
                text: response.municipio.nombre,
                selected: true
            }));
            $('#direccionCliente_estado').append($('<option>', {
                value: response.estado.id,
                text: response.estado.nombre,
                selected: true
            }));
            $('.typeahead').addClass('notEmpty');
            $('.typeahead').addClass('headingColor');
            $('#direccionCliente_delegacion').addClass('notEmpty');
            $('#direccionCliente_delegacion').addClass('headingColor');
            $('#direccionCliente_estado').addClass('notEmpty');
            $('#direccionCliente_estado').addClass('headingColor');
            listaDeControl[$(".showOnFill").index($('.typeahead').closest('.showOnFill'))] = 1;
            listaDeControl[$(".showOnFill").index($('#direccionCliente_delegacion').closest('.showOnFill'))] = 1;
            listaDeControl[$(".showOnFill").index($('#direccionCliente_estado').closest('.showOnFill'))] = 1;
            //$('.showOnFill').each(function (index) {
            mostrarSiguienteCampo($(".showOnFill").index($('.typeahead').closest('.showOnFill')));
            //});
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

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
        }/* else if (errorConsulta.indexOf("ERR") >= 0) {
         $('#accionesNormales').fadeOut();
         $('#accionesError').fadeIn();
         $('#divAutorizacionBuro').fadeOut();
         setCountdown()
         resetCountdown()
         resumeCountdown();
         }*/
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
            //showValues();
            //avanzarPaso("6");
            var currentStep = $('#pasoAnterior').val();
            if ($('#circuloPaso' + (parseInt(currentStep) + 1)).hasClass("grayCircle")) {
                $('#circuloPaso' + (parseInt(currentStep) + 1)).animate({width: "250px", height: "45px", marginTop: "0px"}, {queue: false});
                $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('nextBtn');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).addClass('sendBtn');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').addClass('paddingTop10');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').removeClass('paddingTop5');
                $('#circuloPaso' + (parseInt(currentStep) + 1)).children('p').html("IR AL PASO " + (parseInt(currentStep) + 1));
                avancePorPaso[Number(currentStep - 1)] = Number(ponderaciones[("paso" + currentStep)]);
                console.log("Si lo registro? " + avancePorPaso[Number(currentStep - 1)]);
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

    $('#medioDeContacto').change(function () {
        cargarOpcionesDeContacto($(this).val());
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
            avanzarPaso($(this).data("numeroDePaso"));
        }
    });

    $('.solicitud_modal').click(function () {
        if ($(this).hasClass('blueButton') == true) {
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
        url: "/kosmos-app/solicitud/obtenerOpciones",
        success: function (data, textStatus) {
            var resultado = eval(data);
            var html = "";
            if (resultado.length > 0) {
                html += "<option value=''>Elija una opción...</option>";
            } else {
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

function showValues() {
    var allInputs = $(".formValues").serializeArray();
    //$(".sendValues").html('');
    $.each(allInputs, function (i, field) {
        //console.log("Añadiendo: " + field.name);
        //console.log("Ya existe? " + $('#formPaso input[name=' + field.name + ']').val());
        //console.log("Es indefinido? " + ($('#formPaso input[name=' + field.name + ']').val() === undefined));
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
    //console.log("Avanzando a paso " + paso);
    //alert("Datos::"+$('.sendValues').serialize());
    $.ajax({
        type: 'POST',
        data: $('#formFormulario').serialize(),
        url: '/kosmos-app/solicitud/cambiarPaso',
        success: function (data, textStatus) {
            $('#pasoActual').hide();
            $('#pasoActual').html(data);
            $('#pasoActual').fadeIn();
            var tipoDePaso = $('#tipoDePaso').val();
            //console.log("Cargando funciones de paso " + paso);
            if (tipoDePaso === "pasoFormulario") {
                inicializarFormulario();
                //operacionesFormulario();
                $('.showOnFill').each(function (index) {
                    //verificarCambios(index);
                });
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
            } else {
                //console.log("Paso desconocido " + paso);
            }
            setFooter();
            actualizarProgreso(paso);
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
    //console.log("Actualizando progreso paso " + paso);
    $('.pasoTitle').html('paso ' + paso);
    $('.stepTitle').html($('#tituloDelPaso').val());
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

function generarClaves(persona) {
    console.log("A por la generacion de llaves!!! --->" + persona);
    if (persona === 'cliente') {
        console.log($('#cliente_nombre').val() + ', ' + $('#cliente_apellidoPaterno').val() + ', ' + $('#cliente_apellidoMaterno').val() + ', ' + $('#cliente_genero').val() + ',' + $('#cliente_fechaDeNacimiento_dia').val() + '/' + $('#cliente_fechaDeNacimiento_mes').val() + '/' + $('#cliente_fechaDeNacimiento_anio').val());
        var curp = generaCurp({
            nombre: $('#cliente_nombre').val(),
            apellido_paterno: $('#cliente_apellidoPaterno').val(),
            apellido_materno: $('#cliente_apellidoMaterno').val(),
            sexo: ($('#cliente_genero').val() === '1' ? 'H' : 'M'),
            estado: 'DF',
            fecha_nacimiento: [$('#cliente_fechaDeNacimiento_dia').val(), $('#cliente_fechaDeNacimiento_mes').val(), $('#cliente_fechaDeNacimiento_anio').val()]
        });
        if ($('#cliente_genero').val() === '1') {
            generoConyugue = '2';
        } else {
            conyugue = '1';
        }
        $('#cliente_curp').val(curp);
        $('#cliente_rfc').val(curp.substring(0, 10));
        $("#cliente_curp").addClass('notEmpty');
        $("#cliente_curp").addClass('headingColor');
        $("#cliente_rfc").addClass('notEmpty');
        $("#cliente_rfc").addClass('headingColor');
        listaDeControl[$(".showOnFill").index($('#cliente_curp').closest('.showOnFill'))] = 1;
        listaDeControl[$(".showOnFill").index($('#cliente_rfc').closest('.showOnFill'))] = 1;
    } else if (persona === 'conyugue') {
        var curp = generaCurp({
            nombre: $('#cliente_nombreDelConyugue').val(),
            apellido_paterno: $('#cliente_apellidoPaternoDelConyugue').val(),
            apellido_materno: $('#cliente_apellidoMaternoDelConyugue').val(),
            sexo: (generoConyugue === '1' ? 'H' : 'M'),
            estado: 'DF',
            fecha_nacimiento: [$('#cliente_fechaDeNacimientoDelConyugue_dia').val(), $('#cliente_fechaDeNacimientoDelConyugue_mes').val(), $('#cliente_fechaDeNacimientoDelConyugue_anio').val()]
        });
        $('#cliente_curpDelConyugue').val(curp);
        $('#cliente_rfcDelConyugue').val(curp.substring(0, 10));
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
    //console.log("Inicializando camara...");
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
    var pasoActual = $('#pasoAnterior').val();

    if (no_active === no_buttons) {
        avancePorPaso[Number(pasoActual - 1)] = Number(ponderaciones[("paso" + pasoActual)]);
        //console.log("Si lo registro? " + avancePorPaso[Number(pasoActual - 1)]);
        $('.solicitud_modal').addClass('blueButton colorWhite pointer');
    } else {
        avancePorPaso[Number(pasoActual - 1)] = 0;
        $('.solicitud_modal').removeClass('blueButton colorWhite pointer');
    }
    calcularAvance();
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
        //console.log("Archivo enviado: " + file);
        $('.dz-preview').hide();
        $('#progresoConsultaComp').fadeIn();
        $('#progresoConsultaIds').fadeIn();
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);
        //console.log("Respuesta recibida: " + respuesta);
        $('#progresoConsultaComp').fadeOut();
        //console.log("Documento Vigente? " + respuesta.vigente);
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
                } else if (respuesta.exito || (respuesta.nombre && (respuesta.apellidoPaterno || respuesta.apellidoMaterno))) {
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
        //console.log(response);
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

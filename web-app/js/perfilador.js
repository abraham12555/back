var ofertasCalculadas;
var generoConyugue;

function operacionesPerfilador() {
    $(".mat-input").focus(function () {
        $(this).parent().addClass("is-active is-completed");
    });

    $(".mat-input").focusout(function () {
        if ($(this).val() === "")
            $(this).parent().removeClass("is-completed");
        $(this).parent().removeClass("is-active");
    });

    $(".js-example-basic-single").select2({
        placeholder: "Tipo de comprobante de Ingresos"
    });

    $("#empleoCliente_profesion").select2({
        placeholder: "Profesión"
    });

    $("#empleoCliente_ocupacion").select2({
        placeholder: "Ocupación"
    });

    $('#direccionCliente_montoDeLaRenta_autoNumeric').autoNumeric('init', {currencySymbol: '$', allowDecimalPadding: false});
    $('#direccionCliente_montoDeLaHipoteca_autoNumeric').autoNumeric('init', {currencySymbol: '$', allowDecimalPadding: false});
    $('#empleoCliente_ingresosFijos_autoNumeric').autoNumeric('init', {currencySymbol: '$', allowDecimalPadding: false});
    $('#empleoCliente_ingresosVariables_autoNumeric').autoNumeric('init', {currencySymbol: '$', allowDecimalPadding: false});

    $('.datepicker').pickadate();

    $('#cliente_si').on('click', function () {
        console.log("Si entraaaaaa");
        $(this).parent().children('.correctaBox').removeClass('active_green');
        $(this).addClass('active_green');
        $('#clienteExistente').val('SI');
        $('#clienteExistente').addClass('notEmpty');
        $('#inputNoCliente').removeClass('hide');
    });

    $('#cliente_no').on('click', function () {
        console.log("Si entraaaaaa");
        $(this).parent().children('.correctaBox').removeClass('active_green');
        $(this).addClass('active_green');
        $('#clienteExistente').val('NO');
        $('#clienteExistente').addClass('notEmpty');
        $('#inputNoCliente').addClass('hide');
        $('#inputNoCliente').val('');
    });

    $('.datoPerfilador').change(function (index) {
        console.log("--->" + $(this).attr('id') + " = " + $(this).val());
        if ($(this).val() !== '') {
            if ($(this).hasClass('validarNombre')) {
                if (validateName($(this).val())) {
                    $(this).removeClass('mat-input-error');
                    $(this).addClass('mat-input');
                    $(this).addClass('notEmpty');
                    $(this).addClass('headingColor');
                } else {
                    $(this).removeClass('mat-input');
                    $(this).removeClass('notEmpty');
                    $(this).removeClass('headingColor');
                    $(this).addClass('mat-input-error');
                    vNotify.error({text: 'Los nombres y apellidos deben contener letras únicamente.', title: 'Error de Validación.'});
                }
            } else if ($(this).hasClass('validarEmail')) {
                if (validateEmail($(this).val())) {
                    if (validateDefaultAddress($(this).val())) {
                        $(this).removeClass('mat-input-error');
                        $(this).addClass('mat-input');
                        $(this).addClass('notEmpty');
                        $(this).addClass('headingColor');
                    } else {
                        $(this).removeClass('mat-input');
                        $(this).removeClass('notEmpty');
                        $(this).removeClass('headingColor');
                        $(this).addClass('mat-input-error');
                        vNotify.error({text: 'Ha introducido un correo de LIbertad SF, solo se aceptará capturar el correo genérico 1234@libertad.com.mx', title: 'Error de Validación.', visibleDuration: 15000});
                    }
                } else {
                    $(this).removeClass('mat-input');
                    $(this).removeClass('notEmpty');
                    $(this).removeClass('headingColor');
                    $(this).addClass('mat-input-error');
                    vNotify.error({text: 'La dirección de correo es incorrecta, verifica la estructura. Ej. ejemplo@mail.com.', title: 'Error de Validación.'});
                }
            } else if ($(this).hasClass('validarEdad')) {
                if (($('#cliente_fechaDeNacimiento_dia').val() !== null && $('#cliente_fechaDeNacimiento_dia').val() !== '') && ($('#cliente_fechaDeNacimiento_mes').val() !== null && $('#cliente_fechaDeNacimiento_mes').val() !== '') && ($('#cliente_fechaDeNacimiento_anio').val() !== null && $('#cliente_fechaDeNacimiento_anio').val() !== '')) {
                    var hoy = new Date();
                    var fechaDeNacimiento = new Date(Number($('#cliente_fechaDeNacimiento_anio').val()), Number($('#cliente_fechaDeNacimiento_mes').val() - 1), Number($('#cliente_fechaDeNacimiento_dia').val()), 0, 0, 0);
                    if (fechaValida(Number($('#cliente_fechaDeNacimiento_anio').val()), Number($('#cliente_fechaDeNacimiento_mes').val() - 1), Number($('#cliente_fechaDeNacimiento_dia').val()))) {
                        if (calculateAge(fechaDeNacimiento, hoy) < 18) {
                            $('#cliente_fechaDeNacimiento_dia').css('color', '#fb5e48');
                            $('#cliente_fechaDeNacimiento_dia').removeClass('select');
                            $('#cliente_fechaDeNacimiento_dia').removeClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_dia').removeClass('headingColor');
                            $('#cliente_fechaDeNacimiento_mes').css('color', '#fb5e48');
                            $('#cliente_fechaDeNacimiento_mes').removeClass('select');
                            $('#cliente_fechaDeNacimiento_mes').removeClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_mes').removeClass('headingColor');
                            $('#cliente_fechaDeNacimiento_anio').css('color', '#fb5e48');
                            $('#cliente_fechaDeNacimiento_anio').removeClass('select');
                            $('#cliente_fechaDeNacimiento_anio').removeClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_anio').removeClass('headingColor');
                            vNotify.error({text: 'El prospecto no cuenta con 18 años cumplidos.', title: 'Error de Validación.'});
                        } else {
                            $('#cliente_fechaDeNacimiento_dia').css('color', '#005398');
                            $('#cliente_fechaDeNacimiento_dia').addClass('select');
                            $('#cliente_fechaDeNacimiento_dia').addClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_dia').addClass('headingColor');
                            $('#cliente_fechaDeNacimiento_mes').css('color', '#005398');
                            $('#cliente_fechaDeNacimiento_mes').addClass('select');
                            $('#cliente_fechaDeNacimiento_mes').addClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_mes').addClass('headingColor');
                            $('#cliente_fechaDeNacimiento_anio').css('color', '#005398');
                            $('#cliente_fechaDeNacimiento_anio').addClass('select');
                            $('#cliente_fechaDeNacimiento_anio').addClass('notEmpty');
                            $('#cliente_fechaDeNacimiento_anio').addClass('headingColor');
                        }
                    } else {
                        $('#cliente_fechaDeNacimiento_dia').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimiento_dia').removeClass('select');
                        $('#cliente_fechaDeNacimiento_dia').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimiento_dia').removeClass('headingColor');
                        $('#cliente_fechaDeNacimiento_mes').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimiento_mes').removeClass('select');
                        $('#cliente_fechaDeNacimiento_mes').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimiento_mes').removeClass('headingColor');
                        $('#cliente_fechaDeNacimiento_anio').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimiento_anio').removeClass('select');
                        $('#cliente_fechaDeNacimiento_anio').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimiento_anio').removeClass('headingColor');
                        vNotify.error({text: 'La fecha indicada es incorrecta.', title: 'Error de Validación.'});
                    }
                }
            } else if ($(this).hasClass('validarFecha')) {
                var fechaTiempoEnVivienda;
                var fechaTiempoDeResidencia;
                var fechaInicioEnEmpleo;
                var hoy = new Date();
                var verificacionVivienda = false;
                var verificacionResidencia = false;
                if (($('#direccionCliente_tiempoDeVivir_mes').val() !== null && $('#direccionCliente_tiempoDeVivir_mes').val() !== '') && ($('#direccionCliente_tiempoDeVivir_anio').val() !== null && $('#direccionCliente_tiempoDeVivir_anio').val() !== '')) {
                    //fechaTiempoEnVivienda = new Date(($('#direccionCliente_tiempoDeVivir_anio').val() + "-" + $('#direccionCliente_tiempoDeVivir_mes').val() + "-" + "01"));
                    fechaTiempoEnVivienda = new Date(Number($('#direccionCliente_tiempoDeVivir_anio').val()), (Number($('#direccionCliente_tiempoDeVivir_mes').val()) - 1), 1, 0, 0, 0);
                }
                if (($('#direccionCliente_tiempoDeResidencia_mes').val() !== null && $('#direccionCliente_tiempoDeResidencia_mes').val() !== '') && ($('#direccionCliente_tiempoDeResidencia_anio').val() !== null && $('#direccionCliente_tiempoDeResidencia_anio').val() !== '')) {
                    //fechaTiempoDeResidencia = new Date(($('#direccionCliente_tiempoDeResidencia_anio').val() + "-" + $('#direccionCliente_tiempoDeResidencia_mes').val() + "-" + "01"));
                    fechaTiempoDeResidencia = new Date(Number($('#direccionCliente_tiempoDeResidencia_anio').val()), (Number($('#direccionCliente_tiempoDeResidencia_mes').val()) - 1), 1, 0, 0, 0);
                }
                if (($('#empleoCliente_antiguedad_mes').val() !== null && $('#empleoCliente_antiguedad_mes').val() !== '') && ($('#empleoCliente_antiguedad_anio').val() !== null && $('#empleoCliente_antiguedad_anio').val() !== '')) {
                    //fechaInicioEnEmpleo = new Date(($('#empleoCliente_antiguedad_anio').val() + "-" + $('#empleoCliente_antiguedad_mes').val() + "-" + "01"));
                    fechaInicioEnEmpleo = new Date(Number($('#empleoCliente_antiguedad_anio').val()), (Number($('#empleoCliente_antiguedad_mes').val()) - 1), 1, 0, 0, 0);
                }
                if (fechaTiempoEnVivienda !== undefined && fechaTiempoEnVivienda !== null) {
                    if (compareDates(fechaTiempoEnVivienda, hoy)) {
                        $('#direccionCliente_tiempoDeVivir_mes').css('color', '#005398');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('select');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('headingColor');
                        $('#direccionCliente_tiempoDeVivir_anio').css('color', '#005398');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('select');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('headingColor');
                        verificacionVivienda = true;
                    } else {
                        $('#direccionCliente_tiempoDeVivir_mes').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('select');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('headingColor');
                        $('#direccionCliente_tiempoDeVivir_anio').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('select');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('headingColor');
                        vNotify.error({text: 'La antigüedad en la vivienda no puede ser posterior a la fecha de hoy.', title: 'Error de Validación.'});
                    }
                }
                if (fechaTiempoDeResidencia !== undefined && fechaTiempoDeResidencia !== null) {
                    if (compareDates(fechaTiempoDeResidencia, hoy)) {
                        $('#direccionCliente_tiempoDeResidencia_mes').css('color', '#005398');
                        $('#direccionCliente_tiempoDeResidencia_mes').addClass('select');
                        $('#direccionCliente_tiempoDeResidencia_mes').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeResidencia_mes').addClass('headingColor');
                        $('#direccionCliente_tiempoDeResidencia_anio').css('color', '#005398');
                        $('#direccionCliente_tiempoDeResidencia_anio').addClass('select');
                        $('#direccionCliente_tiempoDeResidencia_anio').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeResidencia_anio').addClass('headingColor');
                        verificacionResidencia = true;
                    } else {
                        $('#direccionCliente_tiempoDeResidencia_mes').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeResidencia_mes').removeClass('select');
                        $('#direccionCliente_tiempoDeResidencia_mes').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeResidencia_mes').removeClass('headingColor');
                        $('#direccionCliente_tiempoDeResidencia_anio').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeResidencia_anio').removeClass('select');
                        $('#direccionCliente_tiempoDeResidencia_anio').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeResidencia_anio').removeClass('headingColor');
                        vNotify.error({text: 'La antigüedad de residencia en la ciudad no puede ser posterior a la fecha de hoy.', title: 'Error de Validación.'});
                    }
                }
                if ((fechaTiempoDeResidencia !== undefined && fechaTiempoDeResidencia !== null) && (fechaTiempoEnVivienda !== undefined && fechaTiempoEnVivienda !== null)) {
                    if (verificacionVivienda === true && verificacionResidencia === true && compareDates(fechaTiempoDeResidencia, fechaTiempoEnVivienda)) {
                        $('#direccionCliente_tiempoDeVivir_mes').css('color', '#005398');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('select');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_mes').addClass('headingColor');
                        $('#direccionCliente_tiempoDeVivir_anio').css('color', '#005398');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('select');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_anio').addClass('headingColor');
                    } else {
                        $('#direccionCliente_tiempoDeVivir_mes').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('select');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_mes').removeClass('headingColor');
                        $('#direccionCliente_tiempoDeVivir_anio').css('color', '#fb5e48');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('select');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('notEmpty');
                        $('#direccionCliente_tiempoDeVivir_anio').removeClass('headingColor');
                        vNotify.error({text: 'La antigüedad en la vivienda no puede ser anterior a la fecha de residencia en la ciudad.', title: 'Error de Validación.'});
                    }
                }
                if (fechaInicioEnEmpleo !== undefined && fechaInicioEnEmpleo !== null) {
                    if (compareDates(fechaInicioEnEmpleo, hoy)) {
                        $('#empleoCliente_antiguedad_mes').css('color', '#005398');
                        $('#empleoCliente_antiguedad_mes').addClass('select');
                        $('#empleoCliente_antiguedad_mes').addClass('notEmpty');
                        $('#empleoCliente_antiguedad_mes').addClass('headingColor');
                        $('#empleoCliente_antiguedad_anio').css('color', '#005398');
                        $('#empleoCliente_antiguedad_anio').addClass('select');
                        $('#empleoCliente_antiguedad_anio').addClass('notEmpty');
                        $('#empleoCliente_antiguedad_anio').addClass('headingColor');
                    } else {
                        $('#empleoCliente_antiguedad_mes').css('color', '#fb5e48');
                        $('#empleoCliente_antiguedad_mes').removeClass('select');
                        $('#empleoCliente_antiguedad_mes').removeClass('notEmpty');
                        $('#empleoCliente_antiguedad_mes').removeClass('headingColor');
                        $('#empleoCliente_antiguedad_anio').css('color', '#fb5e48');
                        $('#empleoCliente_antiguedad_anio').removeClass('select');
                        $('#empleoCliente_antiguedad_anio').removeClass('notEmpty');
                        $('#empleoCliente_antiguedad_anio').removeClass('headingColor');
                        vNotify.error({text: 'La antigüedada en la empresa no puede ser posterior a la fecha de hoy.', title: 'Error de Validación.'});
                    }
                }
                if (($('#cliente_fechaDeNacimientoDelConyugue_dia').val() !== null && $('#cliente_fechaDeNacimientoDelConyugue_dia').val() !== '') && ($('#cliente_fechaDeNacimientoDelConyugue_mes').val() !== null && $('#cliente_fechaDeNacimientoDelConyugue_mes').val() !== '') && ($('#cliente_fechaDeNacimientoDelConyugue_anio').val() !== null && $('#cliente_fechaDeNacimientoDelConyugue_anio').val() !== '')) {
                    var hoy = new Date();
                    if (fechaValida(Number($('#cliente_fechaDeNacimientoDelConyugue_anio').val()), Number($('#cliente_fechaDeNacimientoDelConyugue_mes').val() - 1), Number($('#cliente_fechaDeNacimientoDelConyugue_dia').val()))) {
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').css('color', '#005398');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').addClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').addClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').addClass('headingColor');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').css('color', '#005398');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').addClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').addClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').addClass('headingColor');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').css('color', '#005398');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').addClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').addClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').addClass('headingColor');
                    } else {
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').removeClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_dia').removeClass('headingColor');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').removeClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_mes').removeClass('headingColor');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').css('color', '#fb5e48');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').removeClass('select');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').removeClass('notEmpty');
                        $('#cliente_fechaDeNacimientoDelConyugue_anio').removeClass('headingColor');
                        vNotify.error({text: 'La fecha indicada es incorrecta.', title: 'Error de Validación.'});
                    }
                }
            } else {
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
            }
            if ($(this).attr('id') === 'telefonoCliente_telefonoCelular') {
                enviarSms($('#telefonoCliente_telefonoCelular').val());
            } else if ($(this).attr('id') === 'direccionCliente_codigoPostal' || $(this).attr('id') === 'empleoCliente_codigoPostal') {
                consultarCodigoPostal($(this).attr('id'), $(this).val());
            } else if ($(this).attr('id') === 'direccionCliente_tipoDeVivienda' && $('#direccionCliente_tipoDeVivienda').is(':visible')) {
                controlarCampos("tipoDeVivienda");
            } else if ($(this).attr('id') === 'cliente_estadoCivil' && $('#cliente_estadoCivil').is(':visible')) {
                controlarCampos("estadoCivil");
            } else if ($(this).attr('id') === 'direccionCliente_montoDeLaRenta_autoNumeric' && $('#direccionCliente_montoDeLaRenta_autoNumeric').is(':visible')) {
                $('#direccionCliente_montoDeLaRenta').val($('#direccionCliente_montoDeLaRenta_autoNumeric').autoNumeric('get'));
            } else if ($(this).attr('id') === 'direccionCliente_montoDeLaHipoteca_autoNumeric' && $('#direccionCliente_montoDeLaHipoteca_autoNumeric').is(':visible')) {
                $('#direccionCliente_montoDeLaHipoteca').val($('#direccionCliente_montoDeLaHipoteca_autoNumeric').autoNumeric('get'));
            } else if ($(this).attr('id') === 'empleoCliente_ingresosFijos_autoNumeric' && $('#empleoCliente_ingresosFijos_autoNumeric').is(':visible')) {
                $('#empleoCliente_ingresosFijos').val($('#empleoCliente_ingresosFijos_autoNumeric').autoNumeric('get'));
            } else if ($(this).attr('id') === 'empleoCliente_ingresosVariables_autoNumeric' && $('#empleoCliente_ingresosVariables_autoNumeric').is(':visible')) {
                $('#empleoCliente_ingresosVariables').val($('#empleoCliente_ingresosVariables_autoNumeric').autoNumeric('get'));
            } else if (($('#cliente_nombre').is(':visible')) && ($('#cliente_nombre').val() !== '' && $('#cliente_nombre').val() !== undefined && $('#cliente_nombre').val() !== null) && ($('#cliente_apellidoPaterno').val() !== '' && $('#cliente_apellidoPaterno').val() !== undefined && $('#cliente_apellidoPaterno').val() !== null) && ($('#cliente_apellidoMaterno').val() !== '' && $('#cliente_apellidoMaterno').val() !== undefined && $('#cliente_apellidoMaterno').val() !== null) && ($('#cliente_genero').val() !== '' && $('#cliente_genero').val() !== undefined && $('#cliente_genero').val() !== null) && ($('#cliente_lugarDeNacimiento').val() !== '' && $('#cliente_lugarDeNacimiento').val() !== undefined && $('#cliente_lugarDeNacimiento').val() !== null) && ($('#cliente_fechaDeNacimiento_dia').val() !== '' && $('#cliente_fechaDeNacimiento_dia').val() !== undefined && $('#cliente_fechaDeNacimiento_dia').val() !== null) && ($('#cliente_fechaDeNacimiento_mes').val() !== '' && $('#cliente_fechaDeNacimiento_mes').val() !== undefined && $('#cliente_fechaDeNacimiento_mes').val() !== null) && ($('#cliente_fechaDeNacimiento_anio').val() !== '' && $('#cliente_fechaDeNacimiento_anio').val() !== undefined && $('#cliente_fechaDeNacimiento_anio').val() !== null)) {
                generarClaves('cliente');
            } else if (($('#cliente_nombreDelConyugue').is(':visible')) && ($('#cliente_nombreDelConyugue').val() !== '' && $('#cliente_nombreDelConyugue').val() !== undefined) && ($('#cliente_apellidoPaternoDelConyugue').val() !== '' && $('#cliente_apellidoPaternoDelConyugue').val() !== undefined) && ($('#cliente_apellidoMaternoDelConyugue').val() !== '' && $('#cliente_apellidoMaternoDelConyugue').val() !== undefined) && (generoConyugue) && ($('#cliente_lugarDeNacimientoDelConyugue').val() !== '' && $('#cliente_lugarDeNacimientoDelConyugue').val() !== undefined)) {
                generarClaves('conyugue');
            }
        } else {
            $(this).removeClass('notEmpty');
            $(this).removeClass('headingColor');
        }
        validarAvanceFormulario();
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

        $('#consultaAutenticadorBtn').off().on('click', function () {
            $('#divTipoDeConsultaBC').addClass('hide');
            $('#consultaAutenticador').removeClass('hide');
            $('#accionesNormales').show();
            $('#tipoDeConsultaBC').val('autenticador');
        });

        $('#consultaINTLBtn').off().on('click', function () {
            $('#divTipoDeConsultaBC').addClass('hide');
            $('#consultaINTL').removeClass('hide');
            $('#accionesNormales').show();
            $('#tipoDeConsultaBC').val('intl');
            inicializarDropzone('div#uploadFormato', '.foldersBox', 'consentimientoConsultaBC');
        });

        $('#consultarBuroBtn').off().on('click', function () {
            console.log("Dando click para consulta a buró");
            consultarBuro();
        });
    });
}

function goBackStep1() {
    $('#step1').show();
    $('#step2').addClass('hide');
    $('.step1 .step').addClass('active');
    $('.step1').addClass('active');
    $('#menuComprobante').addClass('active');
    $('#menuComprobante').removeClass('completed');
    $('.step1').removeClass('completed');
    $('.step2').removeClass('active');
    $('.step2 .step').removeClass('active');
    $('#menuPersonales').removeClass('active');
}

function goStep2() {
    var clienteExistente = $('#clienteExistente').val();
    if (clienteExistente === "SI") {
        $.ajax({
            type: 'POST',
            data: {
                rfcClienteExistente: $('#rfcClienteExistente').val()
            },
            url: $.contextAwarePathJS + "dashboard/buscarPerfilExistente",
            success: function (data, textStatus) {
                var respuesta = eval(data);
                if (respuesta.encontrado === true) {
                    $('#step1').hide();
                    $('#step2').removeClass('hide');
                    $('.step1 .step').removeClass('active');
                    $('.step1').removeClass('active');
                    $('#menuComprobante').removeClass('active');
                    $('#menuComprobante').addClass('completed');
                    $('.step1').addClass('completed');
                    $('.step2').addClass('active');
                    $('.step2 .step').addClass('active');
                    $('#menuPersonales').addClass('active');
                    $('#cliente_rfc').focus();
                    $('#cliente_rfc').val($('#rfcClienteExistente').val());
                    $('#cliente_rfc').prop('readonly', true);
                    $("#cliente_rfc").addClass('notEmpty');
                    $("#cliente_rfc").addClass('headingColor');
                    sweetAlert("¡Excelente!", "El R.F.C indicado ha sido encontrado. Complementa los datos que son solicitados en los siguientes apartados.", "success");
                    vNotify.warning({text: 'Favor de capturar el Correo del Cliente, en caso de no contar con correo solo se aceptará capturar el correo genérico 1234@libertad.com.mx', title: 'Importante.', visibleDuration: 30000});
                } else {
                    sweetAlert("¡Atención!", "El R.F.C indicado no ha sido encontrado, Verificalo e intenta nuevamente por favor.", "warning");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            }
        });
    } else {
        $('#step1').hide();
        $('#step2').removeClass('hide');
        $('.step1 .step').removeClass('active');
        $('.step1').removeClass('active');
        $('#menuComprobante').removeClass('active');
        $('#menuComprobante').addClass('completed');
        $('.step1').addClass('completed');
        $('.step2').addClass('active');
        $('.step2 .step').addClass('active');
        $('#menuPersonales').addClass('active');
        if ($('#rfcClienteExistente').val() !== null && $('#rfcClienteExistente').val() !== undefined && $('#rfcClienteExistente').val() !== '') {
            $('#rfcClienteExistente').val('');
            $('#cliente_rfc').val('');
            $('#cliente_rfc').prop('readonly', false);
            $("#cliente_rfc").removeClass('notEmpty');
            $("#cliente_rfc").removeClass('headingColor');
        }
        vNotify.warning({text: 'Favor de capturar el Correo del Cliente, en caso de no contar con correo solo se aceptará capturar el correo genérico 1234@libertad.com.mx', title: 'Importante.', visibleDuration: 30000});
    }
}

function goBackStep2() {
    $('#step2').show();
    $('#step3').addClass('hide');
    $('#menuPersonales').addClass('active');
    $('#menuVivienda').removeClass('active');
}

function goStep3() {
    $.ajax({
        type: 'POST',
        data: {
            codigoConfirmacion: $('#code').val()
        },
        url: $.contextAwarePathJS + "dashboard/resultadoVerificacion",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.resultado === true) {
                $('#step2').hide();
                $('#step3').removeClass('hide');
                $('#menuPersonales').removeClass('active');
                $('#menuVivienda').addClass('active');
            } else {
                sweetAlert("Oops...", "El código indicado no es correcto, Verificalo e intenta nuevamente por favor.", "warning");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function goBackStep3() {
    $('#step3').show();
    $('#step4').addClass('hide');
    $('#menuVivienda').addClass('active');
    $('#menuFamilia').removeClass('active');
}

function goStep4() {
    $('#step3').hide();
    $('#step4').removeClass('hide');
    $('#menuVivienda').removeClass('active');
    $('#menuFamilia').addClass('active');
}

function goBackStep4() {
    $('#step4').show();
    $('#step5').addClass('hide');
    $('#menuFamilia').addClass('active');
    $('#menuEmpleo').removeClass('active');
}

function goStep5() {
    $('#step4').hide();
    $('#step5').removeClass('hide');
    $('#menuFamilia').removeClass('active');
    $('#menuEmpleo').addClass('active');
}

function goConsultaBuro() {
    $("body").mLoading({
        text: "Guardando los datos de la solicitud, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: $('#perfiladorForm').serialize(),
        url: $.contextAwarePathJS + 'dashboard/estructurarSolicitud',
        success: function (data, textStatus) {
            $('#step5').hide();
            $('#buro').removeClass('hide');
            $('.step2').removeClass('active');
            $('.step2 .step').removeClass('active');
            $('.step2').addClass('completed');
            $('.step3').addClass('active');
            $('.step3 .step').addClass('active');
            $('#menuEmpleo').removeClass('active');
            $("body").mLoading('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("body").mLoading('hide');
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function verOfertas() {
    $("body").mLoading({
        text: "Obteniendo Ofertas aplicables... Esta operación puede tardar algunos minutos, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        url: $.contextAwarePathJS + 'dashboard/obtenerOfertas',
        success: function (data, textStatus) {
            mostrarOfertas(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("body").mLoading('hide');
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function regresarPaso(paso) {
    if (paso < 6) {
        $('.step1').removeClass('active');
        $('.step1 .step').removeClass('active');
        $('.step2').removeClass('active');
        $('.step2 .step').removeClass('active');
        $('.step3').removeClass('active');
        $('.step3 .step').removeClass('active');
        $('.step4').removeClass('active');
        $('.step4 .step').removeClass('active');
        $('.step5').removeClass('active');
        $('.step5 .step').removeClass('active');

        validarAvanceFormulario();
    }
}

function validarAvanceFormulario() {
    var elementosLlenosVisibles = $('.notEmpty:visible').length;
    var totalElementosVisibles = $('.datoPerfilador:visible').length;
    var totalElementosHidden = $(".stepContent:visible input.datoPerfilador[type=hidden]").length;
    var totalElementosHiddenLlenos = $(".stepContent:visible input.notEmpty[type=hidden]").length;
    var elementosRequeridosLlenosVisibles = $('.datoPerfilador.requerido.notEmpty:visible').length;
    var totalElementosRequeridosVisibles = $('.datoPerfilador.requerido:visible').length;
    var boton = $('.stepContent:visible .greenBox');
    totalElementosVisibles += totalElementosHidden;
    elementosLlenosVisibles += totalElementosHiddenLlenos;
    console.log("Elementos-> Llenos Visibles: " + elementosLlenosVisibles + " - Total Visibles: " + totalElementosVisibles + " -- Total de Hidden: " + totalElementosHidden + " - Total de Hidden Llenos: " + totalElementosHiddenLlenos + " - Elementos Requeridos Llenos (Visibles): " + elementosRequeridosLlenosVisibles + " - Total Elementos Requeridos (Visibles): " + totalElementosRequeridosVisibles);
    if ((elementosLlenosVisibles === totalElementosVisibles) || ((totalElementosRequeridosVisibles > 0) && (elementosRequeridosLlenosVisibles === totalElementosRequeridosVisibles))) {
        boton.fadeIn();
    } else {
        boton.fadeOut();
    }
}

function slide() {
    $('.first').hide();
    $('.third')
            .addClass('first')
            .removeClass('third')
            .removeClass('scale');
    //.css({"-webkit-transform":"translateX(-100%)"})
    setTimeout(function () {

        $('.second')
                .removeClass('second')
                .addClass('third')
                .addClass('scale');
    }, 1000);
}

$("select").each(function () {
    $(this).change(function () {
        $(this).parent().parent().siblings().css({'display': 'block'});
    });
});

function generarClaves(persona) {
    var estados = ['AS', 'BC', 'BS', 'CC', 'CS', 'CH', 'CL', 'CM', 'DF', 'DG',
        'GT', 'GR', 'HG', 'JC', 'MC', 'MN', 'MS', 'NT', 'NL', 'OC', 'PL', 'QT',
        'QR', 'SP', 'SL', 'SR', 'TC', 'TS', 'TL', 'VZ', 'YN', 'ZS', 'NE'];
    var nombres;
    var apellidoMaterno;
    if (persona === 'cliente') {
        console.log("Entonces que trae el rfc del paso 1 ? " + $('#rfcClienteExistente').val());
        var idEstado = Number($('#cliente_lugarDeNacimiento').val());
        if ($('#cliente_segundoNombre').val().trim() !== '') {
            nombres = $('#cliente_nombre').val() + " " + $('#cliente_segundoNombre').val();
        } else {
            nombres = $('#cliente_nombre').val();
        }
        if ($('#cliente_apellidoMaterno').val().toUpperCase() === "NA") {
            console.log("El apellido viene con NA");
            apellidoMaterno = "";
        } else {
            apellidoMaterno = $('#cliente_apellidoMaterno').val();
        }
        var curp = generaCurp({
            nombre: nombres,
            apellido_paterno: $('#cliente_apellidoPaterno').val(),
            apellido_materno: apellidoMaterno,
            sexo: ($('#cliente_genero').val() === '1' ? 'H' : 'M'),
            estado: estados[(idEstado - 1)],
            fecha_nacimiento: [$('#cliente_fechaDeNacimiento_dia').val(), $('#cliente_fechaDeNacimiento_mes').val(), $('#cliente_fechaDeNacimiento_anio').val()]
        });
        if ($('#cliente_genero').val() === '1') {
            generoConyugue = '2';
        } else {
            generoConyugue = '1';
        }
        if ($('#rfcClienteExistente').val() === null || $('#rfcClienteExistente').val() === undefined || $('#rfcClienteExistente').val() === '') {
            var rfcParcial = generarRfcParcial({
                nombre: nombres,
                apellido_paterno: $('#cliente_apellidoPaterno').val(),
                apellido_materno: apellidoMaterno,
                fecha_nacimiento: [$('#cliente_fechaDeNacimiento_dia').val(), $('#cliente_fechaDeNacimiento_mes').val(), $('#cliente_fechaDeNacimiento_anio').val()]
            });
            var rfc = generarHomoclave((nombres), $('#cliente_apellidoPaterno').val(), apellidoMaterno, rfcParcial);
            $('#cliente_rfc').focus();
            $('#cliente_rfc').val(rfc);
            $("#cliente_rfc").addClass('notEmpty');
            $("#cliente_rfc").addClass('headingColor');
        }
        $('#cliente_curp').focus();
        $('#cliente_curp').val(curp);
        $("#cliente_curp").addClass('notEmpty');
        $("#cliente_curp").addClass('headingColor');
    } else if (persona === 'conyugue') {
        var idEstado = Number($('#cliente_lugarDeNacimientoDelConyugue').val());
        if ($('#cliente_apellidoMaternoDelConyugue').val() === "NA") {
            apellidoMaterno = "";
        } else {
            apellidoMaterno = $('#cliente_apellidoMaternoDelConyugue').val();
        }
        var curp = generaCurp({
            nombre: $('#cliente_nombreDelConyugue').val(),
            apellido_paterno: $('#cliente_apellidoPaternoDelConyugue').val(),
            apellido_materno: apellidoMaterno,
            sexo: (generoConyugue === '1' ? 'H' : 'M'),
            estado: estados[(idEstado - 1)],
            fecha_nacimiento: [$('#cliente_fechaDeNacimientoDelConyugue_dia').val(), $('#cliente_fechaDeNacimientoDelConyugue_mes').val(), $('#cliente_fechaDeNacimientoDelConyugue_anio').val()]
        });
        var rfcParcial = generarRfcParcial({
            nombre: $('#cliente_nombreDelConyugue').val(),
            apellido_paterno: $('#cliente_apellidoPaternoDelConyugue').val(),
            apellido_materno: apellidoMaterno,
            fecha_nacimiento: [$('#cliente_fechaDeNacimientoDelConyugue_dia').val(), $('#cliente_fechaDeNacimientoDelConyugue_mes').val(), $('#cliente_fechaDeNacimientoDelConyugue_anio').val()]
        });
        var rfc = generarHomoclave($('#cliente_nombreDelConyugue').val(), $('#cliente_apellidoPaternoDelConyugue').val(), apellidoMaterno, rfcParcial);
        $('#cliente_curpDelConyugue').focus();
        $('#cliente_curpDelConyugue').val(curp);
        $('#cliente_rfcDelConyugue').focus();
        $('#cliente_rfcDelConyugue').val(rfc);
        $("#cliente_curpDelConyugue").addClass('notEmpty');
        $("#cliente_curpDelConyugue").addClass('headingColor');
        $("#cliente_rfcDelConyugue").addClass('notEmpty');
        $("#cliente_rfcDelConyugue").addClass('headingColor');
    }
}

function consultarCodigoPostal(elemento, codigo) {
    console.log("Si se invoca el metodo de cp");
    var idCodigo = codigo;
    $("body").mLoading({
        text: "Cargando, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: 'idCodigoPostal=' + idCodigo,
        url: $.contextAwarePathJS + 'solicitud/consultarCodigoPostal',
        success: function (data, textStatus) {
            var response = eval(data);
            if (elemento === 'direccionCliente_codigoPostal') {
                var colonia = $('#direccionCliente_colonia').val();
                $('#direccionCliente_colonia').html("");
                $('#direccionCliente_delegacion').html("");
                $('#direccionCliente_estado').html("");
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
                    if (response.municipio && response.municipio !== null && response.municipio !== undefined) {
                        $('#direccionCliente_delegacion').append($('<option>', {
                            value: response.municipio.id,
                            text: response.municipio.nombre,
                            selected: true
                        }));
                        $('#direccionCliente_delegacion').addClass('notEmpty');
                        $('#direccionCliente_delegacion').addClass('headingColor');
                    }
                    if (response.estado && response.estado !== null && response.estado !== undefined) {
                        $('#direccionCliente_estado').append($('<option>', {
                            value: response.estado.id,
                            text: response.estado.nombre,
                            selected: true
                        }));
                        $('#direccionCliente_estado').addClass('notEmpty');
                        $('#direccionCliente_estado').addClass('headingColor');
                    }
                    $('#direccionCliente_codigoPostal').addClass('notEmpty');
                    $('#direccionCliente_codigoPostal').addClass('headingColor');
                } else {
                    $('#direccionCliente_colonia').append($('<option>', {
                        value: null,
                        text: "C.P. no Registrado",
                        selected: true
                    }));
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
            }
            $("body").mLoading('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("body").mLoading('hide');
        }
    });
}

function controlarCampos(control) {
    console.log("Controlar Campos: " + control);
    if (control === "tipoDeVivienda") {
        if ($('#direccionCliente_tipoDeVivienda').val() === "2") {
            $('#divMontoDeLaRenta').show();
            $("#direccionCliente_montoDeLaRenta_autoNumeric").removeClass("hide");
            $('#divMontoDeLaHipoteca').hide();
            $("#direccionCliente_montoDeLaHipoteca_autoNumeric").addClass("hide");
        } else if ($('#direccionCliente_tipoDeVivienda').val() === "6" || $('#direccionCliente_tipoDeVivienda').val() === "7" || $('#direccionCliente_tipoDeVivienda').val() === "8" || $('#direccionCliente_tipoDeVivienda').val() === "9") {
            $('#divMontoDeLaHipoteca').show();
            $("#direccionCliente_montoDeLaHipoteca_autoNumeric").removeClass("hide");
            $('#divMontoDeLaRenta').hide();
            $("#direccionCliente_montoDeLaRenta_autoNumeric").addClass("hide");
        } else {
            $('#divMontoDeLaRenta').hide();
            $('#divMontoDeLaHipoteca').hide();
            $("#direccionCliente_montoDeLaRenta_autoNumeric").addClass("hide");
            $("#direccionCliente_montoDeLaHipoteca_autoNumeric").addClass("hide");
        }
    } else if (control === "estadoCivil") {
        if ($('#cliente_estadoCivil').val() === "2") {
            $("#divRegimenMatrimonial").show();
            $("#divNombreDelConyugue").show();
            $("#divApellidoPaternoDelConyugue").show();
            $("#divApellidoMaternoDelConyugue").show();
            $("#divFechaDeNacimientoDelConyugue").show();
            $("#divLugarNacimientoConyugue").show();
            $("#divRfcDelConyugue").show();
            $("#divCurpDelConyugue").show();
            $("#divNacionalidadDelConyugue").show();
            $("#cliente_regimenMatrimonial").removeClass("hide");
            $("#cliente_nombreDelConyugue").removeClass("hide");
            $("#cliente_apellidoPaternoDelConyugue").removeClass("hide");
            $("#cliente_apellidoMaternoDelConyugue").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_dia").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_mes").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_anio").removeClass("hide");
            $("#cliente_lugarDeNacimientoDelConyugue").removeClass("hide");
            $("#cliente_rfcDelConyugue").removeClass("hide");
            $("#cliente_curpDelConyugue").removeClass("hide");
            $("#cliente_nacionalidadDelConyugue").removeClass("hide");
        } else if ($('#cliente_estadoCivil').val() === "3") {
            $("#divRegimenMatrimonial").hide();
            $("#divNombreDelConyugue").show();
            $("#divApellidoPaternoDelConyugue").show();
            $("#divApellidoMaternoDelConyugue").show();
            $("#divFechaDeNacimientoDelConyugue").show();
            $("#divLugarNacimientoConyugue").show();
            $("#divRfcDelConyugue").show();
            $("#divCurpDelConyugue").show();
            $("#divNacionalidadDelConyugue").show();
            $("#cliente_regimenMatrimonial").addClass("hide");
            $("#cliente_nombreDelConyugue").removeClass("hide");
            $("#cliente_apellidoPaternoDelConyugue").removeClass("hide");
            $("#cliente_apellidoMaternoDelConyugue").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_dia").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_mes").removeClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_anio").removeClass("hide");
            $("#cliente_lugarDeNacimientoDelConyugue").removeClass("hide");
            $("#cliente_rfcDelConyugue").removeClass("hide");
            $("#cliente_curpDelConyugue").removeClass("hide");
            $("#cliente_nacionalidadDelConyugue").removeClass("hide");
        } else {
            $("#divRegimenMatrimonial").hide();
            $("#divNombreDelConyugue").hide();
            $("#divApellidoPaternoDelConyugue").hide();
            $("#divApellidoMaternoDelConyugue").hide();
            $("#divFechaDeNacimientoDelConyugue").hide();
            $("#divLugarNacimientoConyugue").hide();
            $("#divRfcDelConyugue").hide();
            $("#divCurpDelConyugue").hide();
            $("#divNacionalidadDelConyugue").hide();
            $("#cliente_regimenMatrimonial").addClass("hide");
            $("#cliente_nombreDelConyugue").addClass("hide");
            $("#cliente_apellidoPaternoDelConyugue").addClass("hide");
            $("#cliente_apellidoMaternoDelConyugue").addClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_dia").addClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_mes").addClass("hide");
            $("#cliente_fechaDeNacimientoDelConyugue_anio").addClass("hide");
            $("#cliente_lugarDeNacimientoDelConyugue").addClass("hide");
            $("#cliente_rfcDelConyugue").addClass("hide");
            $("#cliente_curpDelConyugue").addClass("hide");
            $("#cliente_nacionalidadDelConyugue").addClass("hide");
        }
    }
    validarAvanceFormulario();
}

function enviarSms(telefonoCelular) {
    $("body").mLoading({
        text: "Enviando SMS, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: {
            telefonoCelular: telefonoCelular
        },
        url: $.contextAwarePathJS + "dashboard/solicitarCodigo",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.mensajeEnviado === true) {
                sweetAlert("SMS Enviado", "Espera por favor entre 15 y 30 segundos para recibir tu código.", "success");
            } else {
                sweetAlert("Oops...", "Ocurrió un problema al enviar el mensaje. Verifica tu número de Celular.", "error");
            }
            $("body").mLoading('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            $("body").mLoading('hide');
        }
    });
}

function consultarBuro() {
    loadBar("45%");
    var tarjeta = $('#tCredito').val();
    var hipoteca = $('#creditoH').val();
    var creditoAutomotriz = $('#creditoA').val();
    var consentimiento = $('#consentimientoConsulta').val();
    var tipoDeConsulta = 'autenticador';
    var cadenaDeBuro = null;
    if($.contextAwarePathJS === "/qa/") {
       cadenaDeBuro = encodeURIComponent($('#cadenaBuroTest').val());//$('#cadenaBuroTest').val();
       tipoDeConsulta = $('#tipoDeConsultaBC').val();
    }
    if ((tipoDeConsulta === 'autenticador' && tarjeta && hipoteca && creditoAutomotriz && cadenaDeBuro) || (tipoDeConsulta === 'intl' && consentimiento)) {
        var numeroTarjeta = $('#numeroTarjeta').val();
        if (tipoDeConsulta === 'autenticador' && tarjeta === 'SI' && !numeroTarjeta) {
            sweetAlert("Antes de continuar...", "Por favor proporcione lo últimos 4 digitos de su tarjeta de crédito.", "warning");
        } else {
            loadBar("75%");
            $.ajax({
                type: 'POST',
                data: {tarjeta: tarjeta, numeroTarjeta: numeroTarjeta, hipoteca: hipoteca, creditoAutomotriz: creditoAutomotriz, cadenaDeBuro: cadenaDeBuro, intl: (tipoDeConsulta === 'intl' ? true : false)},
                url: $.contextAwarePathJS + 'solicitud/consultarBuroDeCredito',
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
                        $('.consultarB').fadeIn();
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
                        $('.consultarB').fadeIn();
                    }
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

function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}

function formatCurrency(n, currency) {
    return currency + "" + parseFloat(n).toFixed(2).replace(/./g, function (c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}

function mostrarOfertas(data) {
    var respuesta = eval(data);
    var html = "";
    var productos = [];
    ofertasCalculadas = respuesta;
    html += "<h2>Ofertas disponibles</h2>";
    html += "<div class=\"promos\">";
    html += "<div id=\"wrapper_bu\">";
    if (respuesta.length > 0) {
        for (var i = 0; i < respuesta.length; i++) {
            html += "<div id=\"bu" + (i + 1) + "\">";
            html += "<div class=\"promo\">";
            html += "<h4>" + respuesta[i].producto.claveDeProducto + "</h4><h4>" + respuesta[i].producto.tituloEnCotizador + "</h4>";
            html += "<ul class=\"features\">";
            html += "<li class=\"brief\"><i class=\"fa fa-male\"></i></li>";
            html += "<li class=\"price\"> <span id=\"monto_" + respuesta[i].producto.id + "\">" + formatCurrency(respuesta[i].listaDeOpciones[0].montoMaximo, "$") + "</span> ";
            html += "<div class=\"flat-slider\" id=\"flat-slider-" + respuesta[i].producto.id + "\"></div>";
            html += "<p class=\"floatLeft marginLeft12 font14 fontWeight500 gray opacity05 paddingTop5\">MIN</p>";
            html += "<p class=\"floatRight marginRight12 font14 fontWeight500 gray opacity05 paddingTop5\">MAX</p>";
            html += "</li>";
            html += "<li>";
            html += "<h6>PLAZO</h6> <span id=\"plazo_" + respuesta[i].producto.id + "\">";
            html += "<select class=\"browser-default\" style=\"border-bottom: 0px;\" onchange= \"cambiarPlazo(" + i + ", this.value)\">";
            html += "<option value=\"0\" selected>" + respuesta[i].listaDePlazos[0].plazos + " " + respuesta[i].listaDePlazos[0].periodicidad.toUpperCase() + "</option>";
            for (var j = 1; j < respuesta[i].listaDePlazos.length; j++) {
                html += "<option value=\"" + j + "\">" + respuesta[i].listaDePlazos[j].plazos + " " + respuesta[i].listaDePlazos[j].periodicidad.toUpperCase() + "</option>";
            }
            html += "</select></span></li>";
            html += "<li id=\"pago_" + respuesta[i].producto.id + "\">";
            html += "<h6>PAGO " + respuesta[i].listaDeOpciones[0].periodicidad.nombre.toUpperCase() + "</h6> " + formatCurrency(respuesta[i].listaDeOpciones[0].cuota, "$") + " </li>";
            //inicio-temporal
            html += "<li id=\"temporal_" + respuesta[i].producto.id + "\"><h6>CÁLCULOS</h6>";
            html += "<p style=\"font-size: 0.89em;\"><strong>MONTO A PAGAR BC:</strong> " + formatCurrency(respuesta[i].listaDeOpciones[0].montoAPagar, "$") + "</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>PROBABILIDAD DE MORA:</strong> " + respuesta[i].listaDeOpciones[0].probabilidadDeMora + "</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>TASA DE INTERES:</strong> " + round((respuesta[i].listaDeOpciones[0].tasaDeInteres * 100), 2) + " %</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>SEGURO:</strong> " + formatCurrency(respuesta[i].listaDeOpciones[0].montoSeguro, "$") + "</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>LIBERASISTENCIA:</strong> " + formatCurrency(respuesta[i].listaDeOpciones[0].montoAsistencia, "$") + "</p>";
            html += "</li>";
            //fin-temporal
            html += "<li><h6>REQUISITOS</h6></li>";
            html += "<li class=\"requisitos\">1. IDENTIFICACIÓN OFICIAL</li>";
            html += "<li class=\"requisitos\">2. COMPROBANTE DE DOMICILIO</li>";
            html += "<li class=\"requisitos\">3. COMPROBANTE DE INGRESOS</li>";
            html += "<li id=\"requisitos_" + respuesta[i].producto.id + "\" class=\"requisitos\">4. GARANTIAS <ul>";
            for (var k = 0; k < respuesta[i].listaDeOpciones[0].garantias.length; k++) {
                html += "<li class=\"requisitos\">" + respuesta[i].listaDeOpciones[0].garantias[k].descripcion + "</li>";
            }
            html += "</ul></li>";
            html += "<input type=\"hidden\" id=\"plazoSeleccionado_" + respuesta[i].producto.id + "\">";
            html += "<input type=\"hidden\" id=\"periodicidadSeleccionada_" + respuesta[i].producto.id + "\">";
            html += "<input type=\"hidden\" id=\"montoSeleccionado_" + respuesta[i].producto.id + "\">";
            html += "<input type=\"hidden\" id=\"pagoSeleccionado_" + respuesta[i].producto.id + "\">";
            html += "<li class=\"buy\"><button onclick=\"confirmarSeleccion(" + (i) + ",'" + respuesta[i].producto.id + "');\">APLICAR</button></li>";
            html += "</ul></div></div>";
            productos.push(respuesta[i].producto.id);
        }
    } else {
        
        var respuestaListado = eval(data);
        html += "<div>No se encontraron ofertas que se ajusten al perfil del cliente.</div>";
        html += "<div>Por el siguiente motivo: </div> ";
        if(respuestaListado.productosAplicables != null ){
            
            html += "<h2><b><strong> - " + respuestaListado.productosAplicables + " </strong></b></p>";
        }else {
            if(respuestaListado.politicas != null ){
                html += "<h2><b><strong> - " + respuestaListado.politicas + "</strong> </b></p>";
            }else{
                if(respuestaListado.dictamenPerfil != null ){
                    html += "<h2><b><strong> - " + respuestaListado.dictamenPerfil + "</strong> </b></p>";
                }else  {
                    if(respuestaListado.bitacoraBuro != null ){
                        html += "<h2><b><strong> -    " + respuestaListado.bitacoraBuro + "</strong></b></p>";
                    }else {
                         if(respuestaListado.ratio != null ){
                            html += "<h2><b><strong> - " + respuestaListado.ratio + "</strong> </b></p>";
                        }
                    }
                }
            }
        }
        
        
        
        
    }
    html += "</div></div>";
    $('#ofertas').html(html);
    $('#buro').hide();
    $('#ofertas').removeClass('hide');
    $('.step3').removeClass('active');
    $('.step3 .step').removeClass('active');
    $('.step3').addClass('completed');
    $('.step4').addClass('active');
    $('.step4 .step').addClass('active');
    $("body").mLoading('hide');
    for (var i = 0; i < respuesta.length; i++) {
        $(('#plazoSeleccionado_' + ofertasCalculadas[i].producto.id)).val(ofertasCalculadas[i].listaDeOpciones[0].plazos);
        $(('#periodicidadSeleccionada_' + ofertasCalculadas[i].producto.id)).val(ofertasCalculadas[i].listaDeOpciones[0].periodicidad.id);
        $(('#pagoSeleccionado_' + ofertasCalculadas[i].producto.id)).val(ofertasCalculadas[i].listaDeOpciones[0].cuota);
        inicializarSlider(("flat-slider-" + respuesta[i].producto.id), respuesta[i].producto.id, (Number(respuesta[i].listaDeOpciones[0].montoMaximo)), (Number(respuesta[i].listaDeOpciones[0].montoMinimo)), (Number(respuesta[i].listaDeOpciones[0].montoMaximo)), 1000);
    }

    var Conclave = (function () {
        var buArr = [], arlen;
        var numPromos = $('.promo').length;
        return {
            init: function () {
                this.addCN();
                this.clickReg();
            },
            addCN: function () {
                var buarr = ["holder_bu_center", "holder_bu_awayR1", "holder_bu_awayL1", "holder_bu_awayR2", "holder_bu_awayL2"];
                for (var i = 1; i <= buarr.length; ++i) {
                    $("#bu" + i).removeClass().addClass(buarr[i - 1] + " holder_bu");
                }
                if (buarr.length < numPromos) {
                    var dif = numPromos - buarr.length;
                    var firstIndexBeforeArrLength = buarr.length + 1;
                    while (dif > 0) {
                        $("#bu" + firstIndexBeforeArrLength).removeClass().addClass("holder_bu_no_display holder_bu");
                        dif--;
                        firstIndexBeforeArrLength++;
                    }
                }
            },
            clickReg: function () {
                $(".holder_bu").each(function () {
                    buArr.push($(this).attr('class'));
                });
                arlen = buArr.length;
                for (var i = 0; i < arlen; ++i) {
                    buArr[i] = buArr[i].replace(" holder_bu", "");
                }
                ;
                $(".holder_bu").click(function (buid) {
                    var me = this, id = this.id || buid, joId = $("#" + id), joCN = joId.attr("class").replace(" holder_bu", "");
                    var cpos = buArr.indexOf(joCN), mpos = buArr.indexOf("holder_bu_center");
                    if (cpos !== mpos) {
                        tomove = cpos > mpos ? arlen - cpos + mpos : mpos - cpos;
                        while (tomove) {
                            var t = buArr.shift();
                            buArr.push(t);
                            for (var i = 1; i <= arlen; ++i) {
                                $("#bu" + i).removeClass().addClass(buArr[i - 1] + " holder_bu");
                            }
                            --tomove;
                        }
                    }
                });
            },
            auto: function () {
                for (i = 1; i <= 1; ++i) {
                    $(".holder_bu").delay(4000).trigger('click', "bu" + i).delay(4000);
                    console.log("called");
                }
            }
        };
    })();

    window['conclave'] = Conclave;
    Conclave.init();
}

function inicializarSlider(elemento, producto, montoInicial, montoMinimo, montoMaximo, incremento) {
    console.log("Inicializando slider: " + elemento + " del producto " + producto);
    console.log("Parametros de inicializacion: " + montoInicial + ", " + montoMinimo + ", " + montoMaximo + ", " + incremento);
    $("#" + elemento).slider({
        orientation: 'horizontal',
        range: false,
        value: montoInicial,
        min: montoMinimo,
        max: montoMaximo,
        step: incremento,
        create: function (event, ui) {
            console.log("Entra al create");
            var montoElegido = montoInicial;
            $(('#montoSeleccionado_' + producto)).val(montoElegido);
            $(('#monto_' + producto)).html(formatCurrency(montoElegido, "$"));
        },
        slide: function (event, ui) {
            var valorElegido;
            valorElegido = (ui.value);
            console.log("Entra al slide");
            $(('#montoSeleccionado_' + producto)).val(valorElegido);
            $(('#monto_' + producto)).html(formatCurrency(valorElegido, "$"));
            $(('#pago_' + producto)).html("<h6> RECALCULANDO PAGO </h6> </li>");
        },
        stop: function (event, ui) {
            var valorElegido;
            valorElegido = (ui.value);
            console.log("Entra al stop");
            $(('#montoSeleccionado_' + producto)).val(valorElegido);
            $(('#monto_' + producto)).html(formatCurrency(valorElegido, "$"));
            calcularOferta(producto, valorElegido);
        }
    });
}

function reiniciarSlider(elemento, producto, montoInicial, montoMinimo, montoMaximo, incremento) {
    console.log("Reinicializando slider: " + elemento + " del producto " + producto);
    console.log("Parametros de reinicializacion: " + montoInicial + ", " + montoMinimo + ", " + montoMaximo + ", " + incremento);
    var valorElegido = montoInicial;
    $("#" + elemento).slider("option", "value", montoInicial);
    $("#" + elemento).slider("option", "min", montoMinimo);
    $("#" + elemento).slider("option", "max", montoMaximo);
    $("#" + elemento).slider("option", "step", incremento);
    $(('#montoSeleccionado_' + producto)).val(valorElegido);
    $(('#monto_' + producto)).html(formatCurrency(valorElegido, "$"));
}


function cambiarPlazo(producto, plazo) {
    $(('#montoSeleccionado_' + ofertasCalculadas[producto].producto.id)).val(ofertasCalculadas[producto].listaDeOpciones[plazo].montoMaximo);
    $(('#plazoSeleccionado_' + ofertasCalculadas[producto].producto.id)).val(ofertasCalculadas[producto].listaDeOpciones[plazo].plazos);
    $(('#periodicidadSeleccionada_' + ofertasCalculadas[producto].producto.id)).val(ofertasCalculadas[producto].listaDeOpciones[plazo].periodicidad.id);
    $(('#pagoSeleccionado_' + ofertasCalculadas[producto].producto.id)).val(ofertasCalculadas[producto].listaDeOpciones[plazo].cuota);
    $(('#monto_' + ofertasCalculadas[producto].producto.id)).html(formatCurrency(ofertasCalculadas[producto].listaDeOpciones[plazo].montoMaximo, "$"));
    $(('#pago_' + ofertasCalculadas[producto].producto.id)).html("<h6>PAGO " + ofertasCalculadas[producto].listaDeOpciones[plazo].periodicidad.nombre.toUpperCase() + "</h6> " + formatCurrency(ofertasCalculadas[producto].listaDeOpciones[plazo].cuota, "$") + " </li>");
    reiniciarSlider(("flat-slider-" + ofertasCalculadas[producto].producto.id), ofertasCalculadas[producto].producto.id, (Number(ofertasCalculadas[producto].listaDeOpciones[plazo].montoMaximo)), (Number(ofertasCalculadas[producto].listaDeOpciones[plazo].montoMinimo)), (Number(ofertasCalculadas[producto].listaDeOpciones[plazo].montoMaximo)), 1000);
    //inicio-temporal
    var html = "<h6>CÁLCULOS (Fines de Prueba)</h6>";
    html += "<p style=\"font-size: 0.89em;\"><strong>MONTO A PAGAR BC:</strong> " + formatCurrency(ofertasCalculadas[producto].listaDeOpciones[plazo].montoAPagar, "$") + "</p>";
    html += "<p style=\"font-size: 0.89em;\"><strong>PROBABILIDAD DE MORA:</strong> " + ofertasCalculadas[producto].listaDeOpciones[plazo].probabilidadDeMora + "</p>";
    html += "<p style=\"font-size: 0.89em;\"><strong>TASA DE INTERES:</strong> " + round((ofertasCalculadas[producto].listaDeOpciones[plazo].tasaDeInteres * 100), 2) + " %</p>";
    html += "<p style=\"font-size: 0.89em;\"><strong>SEGURO:</strong> " + formatCurrency(ofertasCalculadas[producto].listaDeOpciones[plazo].montoSeguro, "$") + "</p>";
    html += "<p style=\"font-size: 0.89em;\"><strong>LIBERASISTENCIA:</strong> " + formatCurrency(ofertasCalculadas[producto].listaDeOpciones[plazo].montoAsistencia, "$") + "</p>";
    $('#temporal_' + ofertasCalculadas[producto].producto.id).html(html);
    //fin-temporal
    var html2 = "4. GARANTIAS <ul>";
    for (var k = 0; k < ofertasCalculadas[producto].listaDeOpciones[plazo].garantias.length; k++) {
        html2 += "<li class=\"requisitos\">" + ofertasCalculadas[producto].listaDeOpciones[plazo].garantias[k].descripcion + "</li>";
    }
    html2 += "</ul>";
    $('#requisitos_' + ofertasCalculadas[producto].producto.id).html(html2);
}

function calcularOferta(producto, montoDeCredito) {
    console.log("Aca se mandaria llamar el recalculo de la oferta");
    $("body").mLoading({
        text: "Recalculando Oferta, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: {
            productoId: producto,
            montoDeCredito: montoDeCredito,
            plazo: $(('#plazoSeleccionado_' + producto)).val(),
            periodicidadId: $(('#periodicidadSeleccionada_' + producto)).val()
        },
        url: $.contextAwarePathJS + "dashboard/recalcularOferta",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            $(('#pago_' + producto)).html("<h6>PAGO " + respuesta.periodicidad + "</h6> " + formatCurrency(respuesta.cuota.cuota, "$") + " </li>");
            $(('#pagoSeleccionado_' + producto)).val(respuesta.cuota.cuota);
            //inicio-temporal
            var html = "<h6>CÁLCULOS (Fines de Prueba)</h6>";
            html += "<p style=\"font-size: 0.89em;\"><strong>TASA DE INTERES:</strong> " + round((respuesta.tasaDeInteres * 100), 2) + " %</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>SEGURO:</strong> " + formatCurrency(respuesta.cuota.montoSeguro, "$") + "</p>";
            html += "<p style=\"font-size: 0.89em;\"><strong>LIBERASISTENCIA:</strong> " + formatCurrency(respuesta.cuota.montoAsistencia, "$") + "</p>";
            $('#temporal_' + producto).html(html);
            //fin-temporal
            var html2 = "4. GARANTIAS <ul>";
            for (var k = 0; k < respuesta.garantias.length; k++) {
                html2 += "<li class=\"requisitos\">" + respuesta.garantias[k].descripcion + "</li>";
            }
            html2 += "</ul>";
            $('#requisitos_' + producto).html(html2);
            $("body").mLoading('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            $("body").mLoading('hide');
        }
    });
}

function confirmarSeleccion(posicion, producto) {
    swal({
        title: "¡Importante!",
        text: "¿La oferta elegida es correcta?",
        type: "warning",
        showCancelButton: true,
        cancelButtonText: "No",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: true
    }, function (isConfirm) {
        if (isConfirm) {
            seleccionarOferta(posicion, producto);
        }
        return false;
    });
}

function seleccionarOferta(posicion, producto) {
    $("body").mLoading({
        text: "Guardando Selección, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
    $.ajax({
        type: 'POST',
        data: {
            posicion: posicion,
            productoId: producto,
            montoDeCredito: $(('#montoSeleccionado_' + producto)).val(),
            montoPago: $(('#pagoSeleccionado_' + producto)).val(),
            plazo: $(('#plazoSeleccionado_' + producto)).val(),
            periodicidadId: $(('#periodicidadSeleccionada_' + producto)).val()
        },
        url: $.contextAwarePathJS + "dashboard/seleccionarOferta",
        success: function (data, textStatus) {
            $('#confirmacion').html(data);
            $('.step4').addClass('completed');
            $('.step4').removeClass('active');
            $('#ofertas').hide();
            $('#confirmacion').removeClass('hide');
            $("body").mLoading('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
            $("body").mLoading('hide');
        }
    });
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validateDefaultAddress(email) {
    var regex = /.+@libertad.com.mx$/;
    if (regex.test(email)) {
        if (email !== "1234@libertad.com.mx") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
}

function validateName(nombre) {
    var re = /^([a-zA-Z \. \- \@ ñáéíóúüÑÁÉÍÓÚÜ])+$/;
    return re.test(nombre);
}

function compareDates(fechaInicial, fechaFinal) {
    console.log("Fecha Inicial: " + fechaInicial + " - en ms " + fechaInicial.getTime());
    console.log("Fecha Final: " + fechaFinal + " - en ms " + fechaFinal.getTime());
    console.log("Fechas bien? " + fechaFinal.getTime() >= fechaInicial.getTime());
    return (fechaFinal.getTime() >= fechaInicial.getTime());
}

function calculateAge(fechaDeNacimiento, ahora) {
    console.log("Fecha de nacimiento: " + fechaDeNacimiento);
    console.log("Ahota: " + ahora);
    var birthday = new Date(ahora.getFullYear(), fechaDeNacimiento.getMonth(), fechaDeNacimiento.getDate());
    if (ahora >= birthday) {
        console.log("Edad " + (ahora.getFullYear() - fechaDeNacimiento.getFullYear()));
        return ahora.getFullYear() - fechaDeNacimiento.getFullYear();
    } else {
        return ahora.getFullYear() - fechaDeNacimiento.getFullYear() - 1;
    }
}

function round(value, decimals) {
    return Number(Math.round(value + 'e' + decimals) + 'e-' + decimals);
}

function fechaValida(year, month, day) {
    var d = new Date(year, month, day);
    if (d.getFullYear() === year && d.getMonth() === month && d.getDate() === day) {
        return true;
    } else {
        return false;
    }
}

Dropzone.autoDiscover = false;

function inicializarDropzone(elemento, boton, tipoDeDocumento) {
    //Dropzone.autoDiscover = false;
    kosmosDropzone = new Dropzone(elemento, {
        url: $.contextAwarePathJS + "solicitud/consultarOCR",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        params: {'docType': tipoDeDocumento},
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: ".png, .jpg, .jpeg",
        autoQueue: true,
        createImageThumbnails: false,
        clickable: boton
    });
    kosmosDropzone.on("addedfile", function (file) {
        console.log("Archivo enviado: " + file);
        $('.dz-preview').hide();
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);
        console.log("Respuesta recibida: " + respuesta);
        if (respuesta.exito) {
            sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            var html = '<svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>';
            html += '<p class="center letterspacing1.4 gray">Subido Correctamente</p>';
            $('#uploadFormato').html(html);
            $('#consentimientoConsulta').val('true');
        } else {
            sweetAlert("Oops...", respuesta.mensaje, "error");
            this.removeAllFiles();
        }
    });
    kosmosDropzone.on("error", function (file, response) {
        console.log(response);
        sweetAlert("Oops...", "Ocurrio un problema al consultar los datos del documento", "error");
    });
}

//Global variables
var step = 0; //cotizador step count
var lastStep; //cotizador max steps
var productSelected;
var productoElegido;
var rubroElegido;
var documentoElegido;
var periodicidadElegida;
var modelName;
var colorName;
var priceSelected = 0;
var enganche = 0;
var montoSeguro = 0;
var montoElegido = 0;
var restante = 0;
var decision;
var frecuencia = "mensual";
var frecuenciaDesc = "meses";
var pagosAnuales = 24;
var frecMonto;
var frecTotalPagos;
var seguro;
var tipoDeProductoSelected;
var pagoCalculado = 0;
var plazos;
$(document).ready(function () {
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
    $('.closeModal').click(function () {
        closeModal('modalTerminosCondiciones');
    });
    lastStep = Number($('#ultimoPaso').val());
    initSteps();
    initActions();
}); //end of document ready

function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
}

function cambiarCss() {
    var colorTitulos = $('#colorTitulos').val();
    var colorGradienteInferior = $('#colorGradienteInferior').val();
    var colorGradienteSuperior = $('#colorGradienteSuperior').val();
    $('.headingColor').css({'color': colorTitulos});
    $('.ui-state-default').css({'background-image': 'linear-gradient(244deg, ' + colorGradienteInferior + ', ' + colorGradienteSuperior + ') !important;'});
    $('.cotizador-box:hover').css({'background-image': 'linear-gradient(232deg, ' + colorGradienteInferior + ', ' + colorGradienteSuperior + ') !important;'});
}

Number.prototype.formatMoney = function (c, d, t) {
    var n = this,
            c = isNaN(c = Math.abs(c)) ? 2 : c,
            d = d == undefined ? "." : d,
            t = t == undefined ? "," : t,
            s = n < 0 ? "-" : "",
            i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))),
            j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};
function formatCurrency(n, currency) {
    return currency + "" + parseFloat(n).toFixed(2).replace(/./g, function (c, i, a) {
        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
    });
}

function initSteps() {
//Hide All
    $('.summaryDiv').hide();
    $('.actionsDiv').hide();
    $('.stepShadow').hide();
    $('#terminosYCondiciones').hide();
    $('.cotizadorStep').css({'opacity': '0'}).find('.stepShadow').hide();
    $('.cotizadorStep').eq(0).css({'opacity': '1'}).find('.stepShadow').show();
    $('.cotizadorStep').eq(0).find('.actionsDiv').show();
    $('.cotizadorStep').eq(1).css({'opacity': '0.2'});
    $('.cotizadorStep').eq(1).find('.actionsDiv').show();
    $('.cotizadorStep').eq(2).css({'opacity': '0.2'});
    $('.cotizadorStep').eq(2).find('.actionsDiv').show();
    //insertar primer modelo como muestra (usar Ajax)
    $('#modelosList').html("");
    $('#modelosList').append(fakeAjax('model', 4));
    $('#modelosList').append(fakeAjax('model', 5));
    $('#coloresList').html("");
    $('#coloresList').append(fakeAjax('color', 0));
    $('#coloresList').append(fakeAjax('color', 1));
    $('.cambiar').click(function () {
//get step and execute step action
        var parentStep = $(this).closest('.cotizadorStep');

        var currentStep = step;
        step = $(parentStep).data('step');
        step = step - 1;

        if (step === 0) {
            priceSelected = 0;
            enganche = 0;
            montoSeguro = 0;
            montoElegido = 0;
            restante = 0;
            decision = null;
        }
        $('.cotizadorStep').eq(currentStep).find('.summaryDiv').slideUp();
        //hide ALL (reset)
        $('.summaryDiv').slideUp();
        $('.actionsDiv').slideUp();
        $('.stepShadow').fadeOut();
        $('#terminosYCondiciones').fadeOut();
        $('.cotizadorStep').css({'opacity': '1'});
        //Show this step
        $('.cotizadorStep').eq(step).find('.actionsDiv').slideDown();
        $('.cotizadorStep').eq(step).find('.stepShadow').fadeIn();
        //show next 2 steps
        if (step < (lastStep - 2)) {
            $('.cotizadorStep').eq(step + 2).find('.actionsDiv').slideDown();
            $('.cotizadorStep').eq((step + 2)).css({'opacity': '0.2'}).find('.stepShadow').hide();
        }

        if (step < (lastStep - 1)) {
            $('.cotizadorStep').eq(step + 1).find('.actionsDiv').slideDown();
            $('.cotizadorStep').eq((step + 1)).css({'opacity': '0.2'}).find('.stepShadow').hide();
        }

//Prepare previous steps according to the current step
        if (step > 0) {
            $('.cotizadorStep').eq((step - 1)).find('.summaryDiv').slideDown();
            $('.cotizadorStep').eq((step - 1)).css({'opacity': '1'}).find('.stepShadow').hide();
        }
        if (step > 1) {
//hide all previous steps
            $('.cotizadorStep').eq((step - 2)).find('.summaryDiv').slideDown();
            $('.cotizadorStep').eq((step - 2)).css({'opacity': '0.2'}).find('.stepShadow').hide();
        }
        $("#btnCotSub").prop("disabled", true);
        $('#submitCotizador').addClass('blur');
        $('#terminosYCondiciones').fadeOut();
    });
}


function initActions() {
    $('.nextAction').click(function () {

        if ($(this).closest('.cotizadorStep').css('opacity') === '1') {
            var parentStep = $(this).closest('.cotizadorStep');
            var tipoDePaso = $(parentStep).data('tipoDePaso');
            step = $(parentStep).data('step');

            stepAction(step, tipoDePaso, $(this));

            if ((tipoDePaso !== "stepProducto" && $('#aplicacionVariable').val() === "true") || ($('#aplicacionVariable').val() === "false")) {

                habilitarPaso(step, lastStep, parentStep);
            }
        }
    });
}

function removeActions() {
    $('.nextAction').unbind();
}

function habilitarPaso(step, lastStep, parentStep) {

    if (step === lastStep) {
        $('.summaryDiv').slideDown();
        $('.actionsDiv').slideUp();
        $('.cotizadorStep').css({'opacity': '1'}).find('.stepShadow').hide();
    } else {
//Hide this step
        $(parentStep).find('.summaryDiv').slideDown();
        $(parentStep).find('.actionsDiv').slideUp();
        $(parentStep).find('.stepShadow').fadeOut();
        //Show next step
        var tipoDePaso = $('.cotizadorStep').eq((step)).data('tipoDePaso');
        $('.cotizadorStep').eq((step)).css({'opacity': '1'}).find('.stepShadow').fadeIn();
        $('.cotizadorStep').eq((step)).find('.actionsDiv').slideDown();
        $('.cotizadorStep').eq((step)).find('.summaryDiv').slideUp();
        //Slightly Show next step
        if (tipoDePaso === 'stepRegistro') {
            iniciarFormularioRegistro();
        }
        if (step < lastStep) {
            $('.cotizadorStep').eq((step + 1)).css({'opacity': '0.2'}).find('.stepShadow').hide();
        }

//Show only this step and slightly the one before
        if (step > 1) {
            $('.cotizadorStep').eq((step - 2)).css({'opacity': '0.2'}).find('.stepShadow').hide();
            $('.cotizadorStep').eq((step - 2)).find('.actionsDiv').slideUp();
            $('.cotizadorStep').eq((step - 2)).find('.summaryDiv').slideDown();
            if (step > 2) {
//hide previous step
//$('.cotizadorStep').eq((step-3)).css({'opacity':'0'}).find('.stepShadow').hide();
                $('.cotizadorStep').eq((step - 3)).find('.actionsDiv').slideUp();
                $('.cotizadorStep').eq((step - 3)).find('.summaryDiv').slideUp();
            }
        }
    }
}

function stepAction(step, tipoDePaso, elm) {
    esperePorFavor();
    var ultimoPaso = Number($('#ultimoPaso').val());
    var aplicacionVariable = $('#aplicacionVariable').val();
    if (tipoDePaso === "stepTipoDeProducto") {
        var tipoDeProductoID = $(elm).data('id');
        tipoDeProductoSelected = tipoDeProductoID;
        obtenerProductos(tipoDeProductoSelected);
        $("#tipoDeProductoElegido").val($(elm).data('nombre'));
    } else if (tipoDePaso === "stepProducto") {
        var productID = $(elm).data('id');
        productSelected = productID;
        if (aplicacionVariable === "true") {
            obtenerPasos(productSelected, step, elm);
            $('#txtRubro').val(productSelected);
            cargarImagen('rubroId', productSelected);
        } else {
            initProduct(productSelected);
            obtenerModelos(productSelected);
            $("#txtProducto").val(productSelected);
        }
    } else if (tipoDePaso === "stepDecision") {
        var decisionID = $(elm).data('id');
        decision = decisionID;
        $('#tieneAtraso').html($(elm).html());
        $('#txtTieneAtrasos').val(decision);
    } else if (tipoDePaso === "stepDocumentos") {
        var documentID = $(elm).data('id');
        documentoElegido = documentID;
        $('#documentoElegido').html($(elm).html());
        $('#txtDocumento').val(documentoElegido);
        identificarProducto(productSelected, documentoElegido, decision);
    } else if (tipoDePaso === "stepModelo") {
        var modelID = $(elm).data('id');
        var modelPrice = $(elm).data('price');
        priceSelected = modelPrice;

        modelName = $(elm).html();
        $('#precioDelProducto').html(formatCurrency(priceSelected, "$"));
        $("#modeloElegido").html(modelName);
        $("#modeloElegido3").html(modelName);
        $("#txtModelo").val(modelID);
        obtenerColores(modelID);
        obtenerSeguros(modelID);
        montoElegido = priceSelected;
    } else if (tipoDePaso === "stepColor") {
        var colorID = $(elm).data('id');
        colorName = $(elm).html();
        $('#colorElegido').html(colorName);
        $('#colorElegido3').html(colorName);
        imagenDelColor(colorID);
        $("#txtColor").val(colorID);
        if ($("#sliderEnganche").slider("instance") !== undefined) {
            reiniciarSlider('sliderEnganche', '%', 'enganche', 10, 80, 30, 10);
        } else {
            iniciarSliderEnganche('sliderEnganche', 10, 80, 30, 10);
        }
    } else if (tipoDePaso === "stepEnganche") {
        if (productSelected === 7 && aplicacionVariable === "true") {
            cargarPlazos(productoElegido, documentoElegido, montoElegido);
        } else if (aplicacionVariable === "false") {
            for (i = 0; i < 4; i++) {
                $(".frecuenciaOpt").eq(i).find('.frecuenciaTotal').html(pagosAnuales * (i + 1));
                $(".frecuenciaOpt").eq(i).find('.frecuenciaTipo').html(frecuenciaDesc);
                $(".frecuenciaOpt").eq(i).find('.frecuenciaCantidad').html("$" + (Math.round(restante / (pagosAnuales * (i + 1))).formatMoney(2, '.', ',')));
            }
            inicializarBotonesPeriodicidad();
        }
        $('#txtEnganche').val(enganche);
    } else if (tipoDePaso === "stepMontoCredito") {
        if (productSelected === 7 && aplicacionVariable === "true") {
            if ($("#sliderEnganche").slider("instance") !== undefined) {
                reiniciarSlider('sliderEnganche', '%', 'enganche', 20, 40, 20, 10);
            } else {
                iniciarSliderEnganche('sliderEnganche', 20, 40, 20, 10);
            }
        } else {
            cargarPlazos(productoElegido, documentoElegido, montoElegido);
        }
        $("#precioDelProducto").html(formatCurrency(montoElegido, "$"));
    } else if (tipoDePaso === "stepPlazos") {
        if (aplicacionVariable === "true") {
            frecMonto = pagoCalculado;
        } else {
            frecMonto = $(elm).find('.frecuenciaCantidad').html();
            frecMonto = Number(frecMonto.replace(/[^0-9\.]+/g, ""));
            frecTotalPagos = $(elm).find('.frecuenciaTotal').html();

        }
        $("#txtPlazo").val(frecTotalPagos);
        $("#txtPago").val(frecMonto);
        $("#pagoElegido").html(formatCurrency(frecMonto, "$") + " | " + frecuencia + " | " + frecTotalPagos + " pagos");
    } else if (tipoDePaso === "stepSeguro") {
        var seguroID = $(elm).data('id');
        seguro = $(elm).find('.seguroNombre').html();
        $('#seguroElegido').html(seguro);
        $('.actionsDiv').slideUp();
        $("#txtSeguro").val(seguroID);
    }
    if (step === ultimoPaso) {
        $("#btnCotSub").prop("disabled", false);
        $('#submitCotizador').removeClass('blur');
        $('#terminosYCondiciones').fadeIn();
    }
    cargaCompletada();
}

/**** Ajax Calls ******/
function initProduct(id) {
    $.ajax({
        type: 'POST',
        data: {
            id: id
        },
        url: $.contextAwarePathJS + "cotizador/obtenerProducto",
        success: function (jsonResponse, textStatus) {
            var data = checkIfJson(jsonResponse);
            // Sustituir el contenido necesario
            productoElegido = data.tituloEnCotizador.toUpperCase();
            $('#nombreDelProducto').html(productoElegido);
            $('#descripcionDelProducto').html(data.descripcion);
            $('#imagenDelProducto').css('background-image', 'url(../images/nissan/' + data.rutaImagenDefault + ')');
            $('#productoElegido').html(productoElegido);
            $('#productoElegido2').html(productoElegido);
            $('#productoElegido3').html(productoElegido);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function obtenerPasos(rubro, paso, elm) {
    $.ajax({
        type: 'POST',
        data: {
            rubroId: rubro,
            ef: $('#entidadFinancieraId').val()
        },
        url: $.contextAwarePathJS + "cotizador/obtenerPasos",
        success: function (data, textStatus) {
            $('#contenedorDePasos').html('');
            $('#contenedorDePasos').html(data);
            initSteps();
            removeActions();
            initActions();
            var parentStep = $(".cotizadorStep[data-step='" + paso + "']");
            lastStep = Number($('#ultimoPaso').val());
            habilitarPaso(step, lastStep, parentStep);
            actualizarTexto('nombreDelProducto', $(elm).data('nombre').toUpperCase());
            actualizarTexto('precioDelProducto', ' ');
            actualizarTexto('descripcionDelProducto', $(elm).data('descripcion'));
            actualizarTexto('productoElegido', $(elm).data('nombre'));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function obtenerProductos(tipoDeProductoId) {
    $.ajax({
        type: 'POST',
        data: {
            tipoDeProductoId: tipoDeProductoId
        },
        url: $.contextAwarePathJS + "cotizador/obtenerProductos",
        success: function (data, textStatus) {
            $('#productosList').html('');
            $('#productosList').html(data);
            removeActions();
            initActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function obtenerModelos(productoId) {
    $.ajax({
        type: 'POST',
        data: {
            productoId: productoId
        },
        url: $.contextAwarePathJS + "cotizador/obtenerModelos",
        success: function (data, textStatus) {
            $('#modelosList').html('');
            $('#modelosList').html(data);
            removeActions();
            initActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}


function obtenerColores(modeloId) {
    $.ajax({
        type: 'POST',
        data: {
            modeloId: modeloId
        },
        url: $.contextAwarePathJS + "cotizador/obtenerColores",
        success: function (data, textStatus) {
            $('#coloresList').html('');
            $('#coloresList').html(data);
            removeActions();
            initActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function imagenDelColor(colorId) {
    var background = fakeAjax('colorBG', colorId);
    $('#imagenDelProducto').css('background-image', 'url(' + background + ')');
}

function obtenerSeguros(modeloId) {
    $.ajax({
        type: 'POST',
        data: {
            modeloId: modeloId
        },
        url: $.contextAwarePathJS + "cotizador/obtenerSeguros",
        success: function (data, textStatus) {
            $('#segurosList').html('');
            $('#segurosList').html(data);
            removeActions();
            initActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function identificarProducto(rubro, documento, atraso) {
    $.ajax({
        type: 'POST',
        data: {
            rubroId: rubro,
            documentoId: documento,
            atrasoEnPagos: atraso
        },
        url: $.contextAwarePathJS + "cotizador/identificarProducto",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var minimo = (Number(respuesta.montoMinimo) / 1000);
                var maximo = (Number(respuesta.montoMaximo) / 1000);
                var inicial = minimo;
                var incremento = 1;
                if ($("#slider").slider("instance") !== undefined) {
                    reiniciarSlider('slider', ' Mil', 'dinero', minimo, maximo, inicial, incremento);
                } else {
                    iniciarSlider('slider', ' Mil', 'dinero', minimo, maximo, inicial, incremento);
                }
                productoElegido = respuesta.idProducto;
                $('#nombreDelProducto').html(respuesta.nombreDelProducto.toUpperCase());
                $("#txtProducto").val(respuesta.idProducto);
                removeActions();
                initActions();
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "warning");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function cargarPlazos(producto, documento, montoSeleccionado) {
    $.ajax({
        type: 'POST',
        data: {
            productoId: producto,
            documentoId: documento,
            monto: montoSeleccionado
        },
        url: $.contextAwarePathJS + "cotizador/obtenerPlazosProducto",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            var html = "";
            if (respuesta.exito) {
                html += "<h1 class='dark-blue-titles font20 fontWeight400 letterspacing1 marginBottom20 clearFloat' style='color:#005398;'><span id='pagosleyenda'>¿En Cuántas " + respuesta.periodicidad.nomenclatura + " Pagarás tu Crédito?</span></h1>";
                html += "<div class='marginTop52'>";
                html += "<div class='marginBottom25 clearFix loading-bar-container inner marginBottom40'>";
                html += "<div id='listaDeFrecuencias' class='loading-bar-line blueButton autoMargin'>";
                html += "</div>";
                html += "<p class='floatLeft marginLeft12 font14 fontWeight500 gray paddingTop5'>MIN</p>";
                html += "<p class='floatRight marginRight12 font14 fontWeight500 gray paddingTop5'>MAX</p>";
                html += "<div class='clearFloat'></div>";
                html += "</div>";
                html += "</div>";
                html += "<div>";
                html += "<p class='letterspacing1 marginBottom20 clearFloat'><span id='datosSeguro'></span></p>";
                html += "<div class='clearFix loading-bar-container inner marginBottom40' style='margin: 15px 0;'>";
                html += "<p id='pagoCalculado' class='pagos-box marginAuto width350 font20'></p>";
                html += "<div class='clearFloat'></div>";
                html += "</div>";
                html += "</div>";
                html += "<p data-id='1' class='marginAuto width350 nextAction cotizador-box'>Siguiente</p>";
                $('#periodicidadList').html('');
                $('#periodicidadList').html(html);
                frecuencia = respuesta.periodicidad.nombre.toLowerCase();
                periodicidadElegida = respuesta.periodicidad.id;
                $('#txtPeriodo').val(periodicidadElegida);
                if ($("#listaDeFrecuencias").slider("instance") !== undefined) {
                    reiniciarSlider('listaDeFrecuencias', '', 'cantidad', respuesta.plazoMinimo, respuesta.plazoMaximo, respuesta.plazoMinimo, respuesta.saltoSlider, respuesta.usarListaDePlazos, respuesta.plazosPermitidos);
                } else {
                    iniciarSlider('listaDeFrecuencias', '', 'cantidad', respuesta.plazoMinimo, respuesta.plazoMaximo, respuesta.plazoMinimo, respuesta.saltoSlider, respuesta.usarListaDePlazos, respuesta.plazosPermitidos);
                }
                removeActions();
                initActions();
                inicializarBotonesPeriodicidad();
            } else {

            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

/***** Borrar la siguiente functiones, simulacion Ajax  ******/

function fakeAjax(action, id) {
    if (action === 'model') {
//Replace with info from Database
        var models = new Array('SENSE 2.5L´17', 'ADVANCE 2.5L´17', 'ARMOR CVT ´16', 'ADVANCE 2 ROW ´17', 'ADVANCE CVT 3.5LTS ´16', 'EXCLUSIVE CVT 3.5LTS ´16', 'EXCLUSIVE CVT MY´17', 'SENSE MT MY´17');
        var prices = new Array('346000', '383000', '415000', '480000', '256000', '288000', '401000', '460000');
        var str_models = '<div class="col6 floatLeft marginBottom10 modelOption">';
        str_models += '<div class="paddingAside5">';
        str_models += '<p data-id="' + id + '" data-price="' + prices[id] + '" class="width350 cotizador-box nextAction">' + models[id] + '</p>';
        str_models += '</div>';
        str_models += '</div>';
        return str_models;
    }
    if (action === 'color') {
        var modelColor = new Array('Azul', 'Gris', 'Rojo', 'Blanca', 'Negra', 'Verde', 'Azul', 'Gris', 'Rojo', 'Blanco', 'Gris', 'Negro');
        var str_colors = '<div class="col6 floatLeft marginBottom10 modelOption">';
        str_colors += '<div class="paddingAside5">';
        str_colors += '<p data-id="' + id + '" data-loaquesea="lkjklj" class="width350 cotizador-box nextAction">' + modelColor[id] + '</p>';
        str_colors += '</div>';
        str_colors += '</div>';
        return str_colors;
    }

    if (action === 'colorBG') {
        var modelColorBG = new Array('../images/nissan/2016-maxima-azul.png',
                '../images/nissan/2016-maxima-azul.png',
                '../images/nissan/2016-maxima-gris.png',
                '../images/nissan/2016-maxima-rojo.png',
                '../images/nissan/2016-sentra-blanco.png',
                '../images/nissan/2016-sentra-gris.png',
                '../images/nissan/2016-sentra-negro.png',
                '../images/nissan/2016-altima-azul.png',
                '../images/nissan/2016-altima-gris_2.png',
                '../images/nissan/2016-altima-rojo.png',
                '../images/nissan/2016-xtrail-blanca.png',
                '../images/nissan/2016-xtrail-negra.png',
                '../images/nissan/2016-xtrail-verde.png');
        return modelColorBG[id];
    }
}

function iniciarSlider(elemento, etiqueta, tipoDeCifra, montoMinimo, montoMaximo, montoInicial, incremento, usarLista, plazosPermitidos) {
    plazos = plazosPermitidos;

    $("#" + elemento).slider({
        animate: "fast",
        value: montoInicial,
        min: montoMinimo,
        max: montoMaximo,
        step: incremento,
        create: function (event, ui) {
            var valorElegido;
            $(this).slider('value', montoInicial);
            if (usarLista) {
                valorElegido = plazos[montoInicial];
            } else {
                valorElegido = montoInicial;
            }
            $('#' + elemento + ' .ui-slider-handle').html('< ' + (valorElegido + etiqueta) + ' >');
            if (tipoDeCifra === "cantidad") {
                if (enganche === 0) {
                    restante = montoElegido;
                } else {
                    restante = montoElegido - enganche;
                }
                if (usarLista) {
                    montoInicial = plazos[montoInicial];
                }
                $('#txtMontoCredito').val(restante);
                calcularPago($('#entidadFinancieraId').val(), restante, productoElegido, valorElegido, periodicidadElegida);
            } else if (tipoDeCifra === "dinero") {
                montoElegido = (montoInicial * 1000);
                $('#montoElegido').html(formatCurrency(montoElegido, "$"));
                //Optimizar codigo:
                $("#montoElegido2").html(formatCurrency(montoElegido, "$"));
                $("#montoElegido3").html(formatCurrency(montoElegido, "$"));
            }
        },
        slide: function (event, ui) {
            var valorElegido;
            if (usarLista) {
                valorElegido = plazos[ui.value];
            } else {
                valorElegido = ui.value;
            }
            if (tipoDeCifra === "dinero") {
                montoElegido = (ui.value * 1000);
                $('#montoElegido').html(formatCurrency(montoElegido, "$"));
                $("#montoElegido2").html(formatCurrency(montoElegido, "$"));
                $("#montoElegido3").html(formatCurrency(montoElegido, "$"));
            } else {
                if (enganche === 0) {
                    restante = montoElegido;
                } else {
                    restante = montoElegido - enganche;
                }
                $('#txtMontoCredito').val(restante);
                $('#pagoCalculado').html("Calculando ...");
            }
            $('#' + elemento + ' .ui-slider-handle').html('< ' + (valorElegido + etiqueta) + ' >');
        },
        stop: function (event, ui) {
            var valorElegido;
            if (usarLista) {
                valorElegido = plazos[ui.value];
            } else {
                valorElegido = ui.value;
            }
            if (tipoDeCifra === "dinero") {
                montoElegido = (ui.value * 1000);
                $('#montoElegido').html(formatCurrency(montoElegido, "$"));

                $("#montoElegido2").html(formatCurrency(montoElegido, "$"));
                $("#montoElegido3").html(formatCurrency(montoElegido, "$"));
            } else {
                if (enganche === 0) {
                    restante = montoElegido;
                } else {
                    restante = montoElegido - enganche;
                }
                $('#txtMontoCredito').val(restante);
                calcularPago($('#entidadFinancieraId').val(), restante, productoElegido, valorElegido, periodicidadElegida);
            }
            $('#' + elemento + ' .ui-slider-handle').html('< ' + (valorElegido + etiqueta) + ' >');
        }

    });
    cambiarCss();
}

function iniciarSliderEnganche(elemento, porcentajeMinimo, porcentajeMaximo, valorInicial, incremento) {

    $("#" + elemento).slider({
        animate: "fast",
        value: valorInicial,
        min: porcentajeMinimo,
        max: porcentajeMaximo,
        step: incremento,
        create: function (event, ui) {
            enganche = montoElegido * (valorInicial / 100);
            restante = montoElegido - enganche;
            $('#engancheElegido').html("$" + (enganche).formatMoney(2, '.', ','));
            $('#' + elemento + ' .ui-slider-handle').html('< ' + valorInicial + '% >');

            $("#engancheElegido2").html("$" + enganche.formatMoney(2, '.', ','));
            $("#engancheElegido3").html("$" + enganche.formatMoney(2, '.', ','));
        },
        slide: function (event, ui) {
            enganche = montoElegido * (ui.value / 100);
            restante = montoElegido - enganche;
            $('#engancheElegido').html("$" + (enganche).formatMoney(2, '.', ','));
            $('#' + elemento + ' .ui-slider-handle').html('< ' + ui.value + '% >');

            $("#engancheElegido2").html("$" + enganche.formatMoney(2, '.', ','));
            $("#engancheElegido3").html("$" + enganche.formatMoney(2, '.', ','));
        }

    });
}

function calcularPago(entidad, monto, producto, plazo, periodicidad) {
    $.ajax({
        type: 'POST',
        data: {
            entidadFinancieraId: entidad,
            montoFinanciado: monto,
            productoId: producto,
            plazoElegido: plazo,
            periodicidadId: periodicidad
        },
        url: $.contextAwarePathJS + "cotizador/calcularPagos",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                pagoCalculado = respuesta.renta;
                frecTotalPagos = plazo;
                montoSeguro = respuesta.montoSeguro;
                $('#txtMontoSeguro').val(montoSeguro);
                $('#txtMontoAsistencia').val(respuesta.montoAsistencia);
                $('#pagoCalculado').html("Pago " + respuesta.nombrePeriodo + ": " + formatCurrency(respuesta.renta, "$"));
//              $('#cat').val(respuesta.cat);

            } else {
                sweetAlert("Oops...", respuesta.mensaje, "warning");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function inicializarBotonesPeriodicidad() {
    $('.frecuencia').click(function () {

        $('.frecuencia').removeClass('blueButton');
        $(this).addClass('blueButton');
        frecuencia = $(this).data('frecuencia');
        if (frecuencia === 'quincenal') {
            frecuenciaDesc = 'quincenas';
            pagosAnuales = 48;
            $('#pagosleyenda').html("A cuantas quincenas");
            $("#txtPeriodo").val(2);
        }
        if (frecuencia === 'mensual') {
            frecuenciaDesc = 'meses';
            pagosAnuales = 24;
            $('#pagosleyenda').html("A cuantos meses");
            $("#txtPeriodo").val(1);
        }
        for (i = 0; i < 4; i++) {
            $(".frecuenciaOpt").eq(i).find('.frecuenciaTotal').html(pagosAnuales * (i + 1));
            $(".frecuenciaOpt").eq(i).find('.frecuenciaTipo').html(frecuenciaDesc);
            $(".frecuenciaOpt").eq(i).find('.frecuenciaCantidad').html("$" + (Math.round(restante / pagosAnuales * (i + 1)).formatMoney(2, '.', ',')));
        }
    });
}

function actualizarTexto(elemento, texto) {
    $('#' + elemento).text(texto);
}

function reiniciarSlider(elemento, etiqueta, tipoDeCifra, montoMinimo, montoMaximo, montoInicial, incremento, usarLista, plazosPermitidos) {
    plazos = plazosPermitidos;
    var valorElegido;
    if (usarLista) {
        valorElegido = plazos[montoInicial];
    } else {
        valorElegido = montoInicial;
    }
    $("#" + elemento).slider("option", "value", montoInicial);
    $("#" + elemento).slider("option", "min", montoMinimo);
    $("#" + elemento).slider("option", "max", montoMaximo);
    $("#" + elemento).slider("option", "step", incremento);
    $('#' + elemento + ' .ui-slider-handle').html('< ' + (valorElegido + etiqueta) + ' >');
    if (tipoDeCifra === "cantidad") {
        if (enganche === 0) {
            restante = montoElegido;
        } else {
            restante = montoElegido - enganche;
        }
        $('#txtMontoCredito').val(restante);
        calcularPago($('#entidadFinancieraId').val(), restante, productoElegido, valorElegido, periodicidadElegida);
    } else if (tipoDeCifra === "dinero") {
        montoElegido = (montoInicial * 1000);
        $('#montoElegido').html(formatCurrency(montoElegido, "$"));
        $("#montoElegido2").html(formatCurrency(montoElegido, "$"));
        $("#montoElegido3").html(formatCurrency(montoElegido, "$"));
    } else if (tipoDeCifra === "enganche") {
        enganche = montoElegido * (montoInicial / 100);
        restante = montoElegido - enganche;
        $('#engancheElegido').html("$" + (enganche).formatMoney(2, '.', ','));
        $("#engancheElegido2").html("$" + enganche.formatMoney(2, '.', ','));
        $("#engancheElegido3").html("$" + enganche.formatMoney(2, '.', ','));
    }
}

function openModal(divModal) {
    $('#' + divModal).fadeIn();
}

function closeModal(divModal) {
    $('#' + divModal).fadeOut();
}

function cargarImagen(tipo, identificador) {
    $.ajax({
        type: 'POST',
        data: (tipo + '=' + identificador),
        url: $.contextAwarePathJS + "cotizador/cargarImagen",
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.extension !== undefined && respuesta.base64 !== undefined) {
                $('#imagenDelProducto').animate({
                    opacity: 0
                }, 'fast', function () {
                    $(this).css({
                        'background-image': 'url("data:image/' + respuesta.extension + ';base64,' + respuesta.base64 + '")',
                        'background-size': '95%',
                        'background-position': 'left center'
                    }).animate({
                        opacity: 1
                    });
                });
            } else {

                $('#imagenDelProducto').animate({
                    opacity: 0
                }, 'fast', function () {
                    $(this).css({
                        'background-image': 'url($.contextAwarePathJS + "images/no-disponible.png")',
                        'background-size': '50%',
                        'background-position': 'center'
                    }).animate({
                        opacity: 1
                    });
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
}

function esperePorFavor() {
    $("body").mLoading({
        text: "Cargando, espere por favor...",
        icon: $.contextAwarePathJS + "images/spinner.gif",
        mask: true
    });
}

function cargaCompletada() {
    setTimeout(function () {
        $("body").mLoading('hide');
    }, 1000);
}

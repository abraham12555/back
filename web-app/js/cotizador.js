//Global variables
var step = 0; //cotizador step count
var lastStep = 6; //cotizador max steps
var productSelected;
var productoElegido;
var modelName;
var colorName;
var priceSelected;
var enganche;
var restante;
var frecuencia = "mensual";
var frecuenciaDesc = "meses";
var pagosAnuales = 24;
var frecMonto;
var frecTotalPagos;
var seguro;

$(document).ready(function () {
    //prevent scroll
    /*$('body').css({
     heigt:$(window).height(),
     overflow:'hidden'
     });*/

    initSteps();
    initActions();

    //slider
    $("#slider").slider({
        animate: "fast",
        value: 30,
        min: 10,
        max: 80,
        step: 10,
        slide: function (event, ui) {
            enganche = priceSelected * (ui.value / 100);
            restante = priceSelected - enganche;
            $('#engancheElegido').html("$" + (enganche).formatMoney(2, '.', ','));
            $('.cotizador-p1-buttons .ui-slider-handle').html(ui.value + '%');
            //Optimizar codigo:
            $("#engancheElegido2").html("$" + enganche.formatMoney(2, '.', ','));
            $("#engancheElegido3").html("$" + enganche.formatMoney(2, '.', ','));
        }

    });

    $('.cambiar').click(function () {
        //get step and execute step action
        var parentStep = $(this).closest('.cotizadorStep');
        //var nextActionElm = $(parentStep).find('.nextAction');
        var currentStep = step;
        step = $(parentStep).data('step');
        step = step - 1;
        //stepAction(step,'back');

        //Slide UP this step
        $('.cotizadorStep').eq(currentStep).find('.summaryDiv').slideUp();
        //hide ALL (reset)
        $('.summaryDiv').slideUp();
        $('.actionsDiv').slideUp();
        $('.stepShadow').fadeOut();
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
        $('#submitCotizador').fadeOut();
    });

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

}); //end of document ready

function checkIfJson(data) {
    try {
        return JSON.parse(data);
    } catch (e) {
        return data;
    }
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

function initSteps() {
    //Hide All
    $('.summaryDiv').hide();
    $('.actionsDiv').hide();
    $('.stepShadow').hide();
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

}


function initActions() {
    $('.nextAction').click(function () {
        //get step and execute step action
        var parentStep = $(this).closest('.cotizadorStep');
        step = $(parentStep).data('step');
        stepAction(step, $(this));
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
            $('.cotizadorStep').eq((step)).css({'opacity': '1'}).find('.stepShadow').fadeIn();
            $('.cotizadorStep').eq((step)).find('.actionsDiv').slideDown();
            $('.cotizadorStep').eq((step)).find('.summaryDiv').slideUp();
            //Slightly Show next step
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
    }); //End of nextAction
}

function removeActions() {
    $('.nextAction').unbind();
}


function stepAction(step, elm) {
    switch (step) {
        case 0:
            //back to the beginning
            break;
        case 1:
            var productID = $(elm).data('id');
            productSelected = productID;
            initProduct(productSelected);
            modeloList(productSelected);
            $("#txtProducto").val(productSelected);
            break;
        case 2:
            var modelID = $(elm).data('id');
            var modelPrice = $(elm).data('price');
            priceSelected = modelPrice;
            modelName = $(elm).html();
            $('#precioDelProducto').html('$' + priceSelected.formatMoney(2, '.', ','));
            $("#modeloElegido").html(modelName);
            $("#modeloElegido3").html(modelName);
            colorDelModelo(modelID);
            $("#txtModelo").val(modelID);
            //start slider at 30%
            $('.cotizador-p1-buttons .ui-slider-handle').html('30%');
            enganche = (priceSelected * 0.3);
            $("#txtEnganche").val(enganche);
            restante = priceSelected - enganche;
            $('#engancheElegido').html("$" + enganche.formatMoney(2, '.', ','));
            $("#engancheElegido2").html("$" + enganche.formatMoney(2, '.', ','));
            break;
        case 3:
            var colorID = $(elm).data('id');
            colorName = $(elm).html();
            $('#colorElegido').html(colorName);
            $('#colorElegido3').html(colorName);
            imagenDelColor(colorID);
            $("#txtColor").val(colorID);
            break;
        case 4:
            for (i = 0; i < 4; i++) {
                $(".frecuenciaOpt").eq(i).find('.frecuenciaTotal').html(pagosAnuales * (i + 1));
                $(".frecuenciaOpt").eq(i).find('.frecuenciaTipo').html(frecuenciaDesc);
                $(".frecuenciaOpt").eq(i).find('.frecuenciaCantidad').html("$" + (Math.round(restante / (pagosAnuales * (i + 1))).formatMoney(2, '.', ',')));
            }
            break;
        case 5:
            frecMonto = $(elm).find('.frecuenciaCantidad').html();
            frecTotalPagos = $(elm).find('.frecuenciaTotal').html();
            $("#txtPlazo").val(frecTotalPagos);
            $("#txtPago").val(frecMonto);
            $("#pagoElegido").html(frecMonto + " | " + frecuencia + " | " + frecTotalPagos + " pagos");
            break;
        case 6:
            seguro = $(elm).find('.seguroNombre').html();
            $('#seguroElegido').html(seguro);
            $('.actionsDiv').slideUp();
            //Add values to formatMoney
            $("#txtSeguro").val(seguro);
            //show button
            $('#submitCotizador').fadeIn();
            break;
    }
}

/**** Ajax Calls ******/
function initProduct(id) {
    $.ajax({
        type: 'POST',
        data: {
            id: id
        },
        url: "/kosmos-app/cotizador/obtenerProducto",
        success: function (jsonResponse, textStatus) {
            var data = checkIfJson(jsonResponse);
            // Sustituir el contenido necesario
            productoElegido = data.marca.nombre.toUpperCase() + " " + data.nombreDelProducto.toUpperCase();
            $('#nombreDelProducto').html(productoElegido);
            $('#descripcionDelProducto').html(data.descripcion);
            $('#imagenDelProducto').css('background-image', 'url(../kosmos-app/images/nissan/' + data.rutaImagenDefault + ')');
            $('#productoElegido').html(productoElegido);
            $('#productoElegido2').html(productoElegido);
            $('#productoElegido3').html(productoElegido);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}


/**** SIMULACIONES ****/

function modeloList(productoId) {

    //remove modelos
    $('#modelosList').html('');
    //insert modelos
    /* Cambiar por ajax */
    switch (productoId) {
        case 1:
            $('#modelosList').append(fakeAjax('model', 4));
            $('#modelosList').append(fakeAjax('model', 5));
            break;
        case 2:
            $('#modelosList').append(fakeAjax('model', 0));
            $('#modelosList').append(fakeAjax('model', 1));
            break;
        case 3:
            $('#modelosList').append(fakeAjax('model', 6));
            $('#modelosList').append(fakeAjax('model', 7));
            break;
        case 6:
            $('#modelosList').append(fakeAjax('model', 2));
            $('#modelosList').append(fakeAjax('model', 3));
            break;

    }

    removeActions();
    initActions();
}


function colorDelModelo(modeloId) {
    //remove colors
    $('#coloresList').html("");
    //insert colros
    //Reemplazar por ajaz
    switch (modeloId) {
        case 0:
            $('#coloresList').append(fakeAjax('color', 0));
            $('#coloresList').append(fakeAjax('color', 1));
            break;
        case 1:
            $('#coloresList').append(fakeAjax('color', 2));
            break;
        case 2:
            $('#coloresList').append(fakeAjax('color', 3));
            break;
        case 3:
            $('#coloresList').append(fakeAjax('color', 4));
            $('#coloresList').append(fakeAjax('color', 5));
            break;
        case 4:
            $('#coloresList').append(fakeAjax('color', 6));
            $('#coloresList').append(fakeAjax('color', 7));
            break;
        case 5:
            $('#coloresList').append(fakeAjax('color', 8));
            break;
        case 6:
            $('#coloresList').append(fakeAjax('color', 9));
            $('#coloresList').append(fakeAjax('color', 10));
            break;
        case 7:
            $('#coloresList').append(fakeAjax('color', 11));
            break;
    }
    removeActions();
    initActions();
}

function imagenDelColor(colorId) {
    var background = fakeAjax('colorBG', colorId);
    $('#imagenDelProducto').css('background-image', 'url(' + background + ')');
}


/***** Borrar la siguiente functiones, simulacion Ajax  ******/

function fakeAjax(action, id) {
    if (action == 'model') {
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
    if (action == 'color') {
        var modelColor = new Array('Azul', 'Gris', 'Rojo', 'Blanca', 'Negra', 'Verde', 'Azul', 'Gris', 'Rojo', 'Blanco', 'Gris', 'Negro');

        var str_colors = '<div class="col6 floatLeft marginBottom10 modelOption">';
        str_colors += '<div class="paddingAside5">';
        str_colors += '<p data-id="' + id + '" data-loaquesea="lkjklj" class="width350 cotizador-box nextAction">' + modelColor[id] + '</p>';
        str_colors += '</div>';
        str_colors += '</div>';
        return str_colors;
    }

    if (action == 'colorBG') {
        var modelColorBG = new Array('../kosmos-app/images/nissan/2016-altima-azul.png',
                '../kosmos-app/images/nissan/2016-altima-gris_2.png',
                '../kosmos-app/images/nissan/2016-altima-rojo.png',
                '../kosmos-app/images/nissan/2016-xtrail-blanca.png',
                '../kosmos-app/images/nissan/2016-xtrail-negra.png',
                '../kosmos-app/images/nissan/2016-xtrail-verde.png',
                '../kosmos-app/images/nissan/2016-maxima-azul.png',
                '../kosmos-app/images/nissan/2016-maxima-gris.png',
                '../kosmos-app/images/nissan/2016-maxima-rojo.png',
                '../kosmos-app/images/nissan/2016-sentra-blanco.png',
                '../kosmos-app/images/nissan/2016-sentra-gris.png',
                '../kosmos-app/images/nissan/2016-sentra-negro.png');
        return modelColorBG[id];
    }
}
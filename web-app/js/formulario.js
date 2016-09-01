var winH;
var contentH;
var headerH;
var footerH;
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
        $(this).parent().fadeOut();
    });
    var pasoInicial = $('#pasoInicial').val();
    operacionesModal();
    closeModal();
    stepBarHover();
    //submitNextPage();
    actualizarProgreso(pasoInicial);
    if (pasoInicial === "1" || pasoInicial === "2" || pasoInicial === "3") {
        operacionesPaso1al3();
    } else if (pasoInicial === "4") {
        operacionesPaso4();
    } else if (pasoInicial === "5") {
        operacionesPaso5();
    } else if (pasoInicial === "6") {
        operacionesPaso6();
    }
    verificarCambios(0);
});

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
    $('#terminarSolicitud').click(function () {
        if ($(this).hasClass("GrayButton")) {
            avanzarPaso("8");
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
        $(this).parent().fadeOut();
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
        });
    });

    $('.formStep .confirmDiv .buttonM').click(function () {

        if ($(this).parent().parent().is(':hidden') === true) {

        } else {
            if ($(this).parent().parent().hasClass('lastStep') === true) {

                $(".formValues").on("change", showValues);
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

    console.log("not Empty: " + filledLength + " -  total: " + thisLength);

    if (filledLength === thisLength) {
        if ((index + 1) < maxIndex) {
            $('.showOnFill').eq(index + 1).fadeIn();
            $('.showOnFill').eq(index + 1).css({'display': 'inline'});
            checkInputs();
        } else {
        }

    } else {
    }
    var totalLength = $('.formStep:visible .formValues').length;

    if (filledLength === totalLength) {
        $('.formStep:visible .confirmDiv').fadeIn();
    }
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
            $('#delegacion').append($('<option>', {
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
            $('#delegacion').addClass('notEmpty');
            $('#delegacion').addClass('headingColor');
            $('#estado').addClass('notEmpty');
            $('#estado').addClass('headingColor');
            verificarCambios(1);
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
        $('.bankChoice').val(bankChoice);
        $('.bankChoice').addClass('notEmpty');
    });


    $('.consultarBox').click(function () {
        //consultarBancos();
    	authenticate();
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
        showValues();
        $('.nextBtn').addClass('sendBtn');
        //submitNextPage();
    });
}

function operacionesPaso5() {
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
                    $('#credito').val('NO');
                }

            }
        });
    });

    $('#consultarBuroBtn').click(function () {
        consultarBuro();
    });
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
        openModal('identification_oficial');
        $('#tipoDeDocumento').val('Identicaciones');
        inicializarDropzone('div#divDropzoneIds', '#subirIdentificacion');
        $(this).addClass('colorGreen');
        habilitarTerminarSolicitud();
    });

    $('#paso6CompDom').click(function () {
        $('#tipoDeDocumento').val('UtilityBill');
        openModal('comprobante_domicilio');
        inicializarDropzone('div#divDropzoneComp', '#subirComprobante');
        $(this).addClass('colorGreen');
        habilitarTerminarSolicitud();
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
        $(".sendValues").append('<input type="hidden" name="' + field.name + '" value="' + field.value + '" />');
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

function restartLoadBar() {
    $('.loadingBar').fadeIn();
    $('.loadingActive').css({'width': '0%'});
}

function closeModal() {
    $('.closeModal').click(function () {
        $(this).parent().parent().fadeOut();
    });
}

function checkInputs() {
    var totalLength = $('.formStep:visible .formValues').length;
    var filledLength = $('.notEmpty:visible').length;

    if (filledLength === totalLength) {
        $('.formStep:visible .confirmDiv').fadeIn();
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
            } else if (paso === "5") {
                console.log("Cargando funciones de paso 5");
                operacionesPaso5();
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

function actualizarProgreso(paso) {
    var percentStep;
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
        percentStep = 5;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Datos Generales');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "2") {
        percentStep = 25;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Vivienda');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "3") {
        percentStep = 45;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Empleo');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "4") {
        percentStep = 65;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Datos Bancarios');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "5") {
        percentStep = 90;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Historial Crediticio');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "6") {
        percentStep = 99;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Documentos');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "7") {
        percentStep = 5;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.stepTitle').html('Datos Generales');
        $('.progressPerc').html(percentStep + '%');
    } else if (paso === "8") {
        percentStep = 100;
        $('.activeProgress').css({
            'width': percentStep + '%'
        });
        $('.pasoTitle').html('');
        $('.stepTitle').html('Tu Crédito');
        $('.progressPerc').html(percentStep + '%');
    }
}

function fillFB(data) {
    var json = JSON.parse(data);
    $("#usuario").html(json["name"]);
    $("#nombre").val(json["first_name"]);
    $("#apellidoPaterno").val(json["last_name"]);
    $("#imagenUsuario").html("<img src=" + json["picture"].data.url + "/>");
    $("#mes").val(json["birthday"].substring(0, 2));
    $("#dia").val(json["birthday"].substring(3, 5));
    $("#anio").val(json["birthday"].substring(6, 12));
    $("#estadoCivil").val(json["relationship_status"]);
    if (json["first_name"]) {
        $("#nombre").addClass('notEmpty');
        $("#nombre").addClass('headingColor');
    }
    if (json["last_name"]) {
        $("#apellidoPaterno").addClass('notEmpty');
        $("#apellidoPaterno").addClass('headingColor');
    }
    if (json["birthday"].substring(0, 2)) {
        $("#mes").addClass('notEmpty');
        $("#mes").addClass('headingColor');
    }
    if (json["birthday"].substring(3, 5)) {
        $("#dia").addClass('notEmpty');
        $("#dia").addClass('headingColor');
    }
    if (json["birthday"].substring(6, 12)) {
        $("#anio").addClass('notEmpty');
        $("#anio").addClass('headingColor');
    }
    if (json["relationship_status"]) {
        $("#estadoCivil").addClass('notEmpty');
        $("#estadoCivil").addClass('headingColor');
    }
    $('#nombreCliente').html('¡Hola! ' + json["first_name"]);
    $('.defaultBubble').fadeOut();
    $('.successBubble').fadeIn();
    verificarCambios(0);
}

function fillGoogle(data) {
    var json = JSON.parse(data);
    console.log("JSON-->" + json);
    console.log("ID: " + json.getId());
    console.log('Full Name: ' + json.getName());
    console.log('Given Name: ' + json.getGivenName());
    console.log('Family Name: ' + json.getFamilyName());
    console.log("Image URL: " + json.getImageUrl());
    console.log("Email: " + json.getEmail());
    $('#nombreCliente').html('¡Hola! ' + json.getName());
    $('.defaultBubble').fadeOut();
    $('.successBubble').fadeIn();
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
	             $.each(respuesta.accounts_resume, function(i, item) {
	             	if(respuesta.accounts_resume[i].depositoPromedio > 0){
	             		index=i;
                }
	             });
                 $('#dep90').val(formatCurrency(respuesta.accounts_resume[index].depositoPromedio, "$"));
                 $('#ret90').val(formatCurrency(respuesta.accounts_resume[index].retiroPromedio, "$"));
                 $('#saldo90').val(formatCurrency(respuesta.accounts_resume[index].saldoPromedio, "$"));
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
    console.log("Authenticating....");
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
            	$.each(respuesta.accounts_resume, function(i, item) {
            		if(respuesta.accounts_resume[i].depositoPromedio > 0){
            			index=i;
                    }
            	});
                $('#dep90').val(formatCurrency(respuesta.accounts_resume[index].depositoPromedio, "$"));
                $('#ret90').val(formatCurrency(respuesta.accounts_resume[index].retiroPromedio, "$"));
                $('#saldo90').val(formatCurrency(respuesta.accounts_resume[index].saldoPromedio, "$"));
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

function loading(bank) {
	$("#spinner").html(spinner(bank));
	$("#spinner").fadeIn();
    $('#modalloginBank').modal({keyboard: false, backdrop: false})
}

function complete() {
    $("#spinner").html("");
    $("#loginInteractiveHtml").html("");
}

function spinner(bank) {
	var html = "<center><i class=\"fa fa-refresh fa-spin\" style=\"font-size:60px;color:#298df5\"></i><p style=\"font-size:16px;color:#298df5\">...</p></center>";
	html+="<center><p style=\"font-size:16px;color:#298df5\">Estableciendo Conexi&oacute;n con la Instituci&oacute;n Financiera</p>";
	if(bank == 'banamex'){
		
		html+="<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/banamex.png\"/></center>";
	}else if (bank == 'bancomer'){
		html+="<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/bancomer.png\"/></center>";
	}else if (bank == 'american_express'){
		html+="<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/american_express.png\"/></center>";
	}else if (bank == 'santander'){
		html+="<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/santander.png\"/></center>";
	}else if (bank == 'banorte'){
		html+="<img class=\"width120 blockAuto paddingTop20\" src=\"/kosmos-app/images/banorte.png\"/></center>";
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
    console.log("Validando seleccion de opciones...");
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
            loadBar();
            $.ajax({
                type: 'POST',
                data: 'tarjeta=' + tarjeta + "&numeroTarjeta=" + numeroTarjeta + "&hipoteca=" + hipoteca + "&creditoAutomotriz=" + creditoAutomotriz,
                url: '/kosmos-app/solicitud/consultarBuroDeCredito',
                success: function (data, textStatus) {
                    var respuesta = checkIfJson(data);
                    $('.loadingBar').fadeOut();
                    $('.creditBtns').fadeOut();
                    $('.loadingContainer .buttonM').delay(1000).fadeIn();
                    if (respuesta.status === 200) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesSuccess').fadeIn();
                    } else if (respuesta.error) {
                        $('#accionesNormales').fadeOut();
                        $('#accionesError').fadeIn();
                    }
                    restartLoadBar();
                    showValues();
                    $('.nextBtn').addClass('sendBtn');
                    //submitNextPage();
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
    $('.idType').click(function () {
        $(this).parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().next('.idView').delay(300).fadeIn();
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

    $('.docChoice').click(function () {
        $(this).parent().parent().parent().parent().parent().parent().fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().fadeIn();
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

function inicializarDropzone(elemento, boton) {
    Dropzone.autoDiscover = false;
    var kosmosDropzone = new Dropzone(elemento, {
        url: "/kosmos-app/solicitud/consultarEphesoft",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        params: {'docType': $('#tipoDeDocumento').val()},
        maxFiles: 1,
        maxFilesize: 5,
        acceptedFiles: ".pdf, .png, .jpg, .jpeg",
        autoQueue: true,
        createImageThumbnails: false,
        clickable: boton
    });
    kosmosDropzone.on("addedfile", function (file) {
        console.log("Archivo enviad: " + file);
        $('.dz-preview').hide();
        $('#progresoConsultaComp').fadeIn();
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);
        console.log("Respuesta recibida: " + respuesta);
        $('#progresoConsultaComp').fadeOut();
        if (respuesta.direccion) {
            sweetAlert({html: true, title: "¡Excelente!", text: "Se obtuvieron los siguientes datos: <br/> <strong>Nombre:</strong>" + respuesta.nombrePersona + "<br/><strong>Dirección: </strong>" + respuesta.direccion + "<br/><strong>Fecha del Recibo: </strong> " + respuesta.fechaRecibo + "</br>", type: "success"});
            this.removeAllFiles();
            closeModal('comprobante_domicilio');
            $('#calle').val(respuesta.direccion);
            $('#calle').addClass("notEmpty");
            $('#calle').addClass("headingColor");
            $('.defaultBubble').fadeOut();
            $('.successBubble').fadeIn();
        } else if (respuesta.error) {
            sweetAlert("Oops...", respuesta.error, "error");
        } else if (respuesta.fatal) {
            sweetAlert("Oops...", "Ocurrio un error al procesar el documento: " + respuesta.error, "error");
        }
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

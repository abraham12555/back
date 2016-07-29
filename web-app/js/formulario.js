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
    closeModal();
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
});

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

        $('.formValues', this).change(function () {

            if ($(this).val() != '') {
                $(this).addClass('notEmpty');
                $(this).addClass('headingColor');
            } else {
                $(this).removeClass('notEmpty');
                $(this).removeClass('headingColor');
            }

            var filledLength = $('.notEmpty:visible').length;
            var thisLength = $('.formValues:visible').length;

            if (filledLength == thisLength) {
                if ((index + 1) < maxIndex) {
                    $('.showOnFill').eq(index + 1).css({'display': 'inline'});
                    checkInputs();
                } else {
                }

            } else {
            }
            var totalLength = $('.formStep:visible .formValues').length;


            if (filledLength == totalLength) {
                //alert('show submit');
                $('.formValues.notEmpty').addClass('headingColor');
                $('.formStep:visible .confirmDiv').fadeIn();
                $('.defaultBubble').fadeOut();
                $('.successBubble').fadeIn();

            } else {
                //alert('hide submit');
                $('.formValues.notEmpty').removeClass('headingColor');
            }
        });
    });

    $('.formStep .confirmDiv .buttonM').click(function () {

        if ($(this).parent().parent().is(':hidden') == true) {

        } else {
            if ($(this).parent().parent().hasClass('lastStep') == true) {

                $(".formValues").on("change", showValues);
                showValues();
                $('.nextBtn').addClass('sendBtn');
                submitNextPage();

            } else {
                $(this).parent().parent().slideUp();
                $(this).parent().parent().next('.formStep').slideDown();

            }
            $('.successBubble').fadeOut();
        }
    });

    submitNextPage();
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
        consultarLoginBancos();
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
        submitNextPage();
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
        submitNextPage();
    });

    $('.greenClick').click(function () {
        $(this).addClass('colorGreen');
        var no_buttons = $('.greenClick').length;
        var no_active = $('.greenClick.colorGreen').length;

        if ($(this).index('.greenClick') == 0) {
            $('#identification_oficial').fadeIn();
            $(this).html('ID GUARDADA');

        } else if ($(this).index('.greenClick') == 1) {
            $('#comprobante_domicilio').fadeIn();
            $(this).html('COMPROBANTE GUARDADO');
        }

        if (no_active == no_buttons) {
            $('.solicitud_modal').addClass('blueButton colorWhite pointer');
        } else {
            $('.solicitud_modal').removeClass('blueButton colorWhite pointer');
        }
    });

    $('.solicitud_modal').click(function () {
        if ($(this).hasClass('blueButton') == true) {
            $('#resumen_solicitud').fadeIn();
        }
    });

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

    $('.goLastStep').click(function () {

    });

    $('.docChoice').click(function () {
        $(this).parent().parent().parent().parent().parent().parent().delay(400).fadeOut();
        $(this).parent().parent().parent().parent().parent().parent().next().delay(500).fadeIn();
    });

    $('.tomarFoto').click(function () {
        $(this).parent().parent().fadeOut();
        $('.defaultTP').delay(300).fadeOut();
        $('.takePicture').delay(350).fadeIn();
        $('.docControls').delay(350).fadeIn();
    });

    /*$('.afterSelect').click(function(){
     openSelect($('#selTest'));
     });*/
}

function showValues() {
    var allInputs = $(".formValues").serializeArray();
    //$(".sendValues").html('');
    $.each(allInputs, function (i, field) {
        $(".sendValues").append('<input type="hidden" name="' + field.name + '" value="' + field.value + '" />');
    });
}

function submitNextPage() {
    $('.sendBtn').click(function () {
        //$('.sendValues').submit();
        avanzarPaso();
    });
}

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

    if (filledLength == totalLength) {
        $('.formValues.notEmpty').addClass('headingColor');
        $('.formStep:visible .confirmDiv').fadeIn();
        $('.defaultBubble').fadeOut();
        $('.successBubble').fadeIn();

    } else {
        //alert('hide submit');
        $('.formValues.notEmpty').removeClass('headingColor');
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


function avanzarPaso() {
    //cerrarModal();
    var paso = $('#siguientePaso').val();
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
    /*$("#usuario").html(json["name"]);
     $("#nombre").val(json["first_name"]);
     $("#apellidoPaterno").val(json["last_name"]);
     $("#imagenUsuario").html("<img src=" + json["picture"].data.url + "/>");*/
}

//Callback para hacer la consulta con el API Bancaria
function consultarLoginBancos(){
	var banco = $('.bankChoice').val();
	loadBar();
	//Inicializando Formas
	cleanformLogin()
	console.log("Validando seleccion de banco...");
	if (banco) {
		$.ajax({
            type: 'POST',
            data: 'banco=' + banco,
            url: '/kosmos-app/solicitud/consultarLoginBancos',
            success: function (data, textStatus) {
                var respuesta = checkIfJson(data);
                restartLoadBar();
                if ('errorCode' in respuesta) {
                	restartLoadBar();
                	sweetAlert("Oops...", "Lo sentimos , hubo un problema consultar al banco. Por favor, inténtelo de nuevo más tarde. Codigo de Error"+respuesta.errorCode, "error");
                }else if ('conjunctionOp' in respuesta){
                	restartLoadBar();
                	$('#formLoginBank').html(buildLoginBank(respuesta));
                	abrirModal('modalloginBank');
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
	}
}

function peticionLoginBancos(formInv,paso){
	$("#formLoginBank").hide();
	$("#formMfaLogin").hide();
	loadBar();
	var data = {};
	$("#"+formInv).find("input, select").each(function(i, field) {
	    data[field.name] = field.value;
	});
	$("#spinner").html(spinner());
	if (paso) {
		$.ajax({
            type: 'POST',
            data: 'paso=' + paso +"&data=" + JSON.stringify(data),
            url: '/kosmos-app/solicitud/flujoConsultaBancos',
            success: function (data, textStatus) {
                var respuesta = checkIfJson(data);
                restartLoadBar();
                if ( paso == 'addAccount') {
                	if('errorCode' in respuesta){ //Excepcion al Consultar Yoddle
                		sweetAlert("Oops...", "Lo sentimos , hubo un problema al consultar su cuenta . Por favor, inténtelo de nuevo más tarde. Codigo de Error "+respuesta.errorCode, "error");
                		cerrarModal();
                	}else if (respuesta.isMessageAvailable.fieldInfo != 'undefined'){
                		console.log("Flujo MFA");
                		$('#formLoginBank').hide();
                		$("#spinner").html("");
                		$('#formMfaLogin').html(buildFormMFA(respuesta));
                		$("#formMfaLogin").fadeIn();
                	}else{
                		alert("Cuenta Agregada");
                		$('#formMfaLogin').html("").show();
                    	$('#formLoginBank').html("").show();
                    	$("#spinner").html("");
                    	$('#dep90').val(formatCurrency(respuesta.depositosPromedio, "$"));
                        $('#ret90').val(formatCurrency(respuesta.retirosPromedio, "$"));
                        $('#saldo90').val(formatCurrency(respuesta.saldoPromedio, "$"));
                        $('.loadingActive').hide();
                        $('#consultarInfo').hide();
                        $('.defaultBubble').fadeOut();
                        $('.successBubble').fadeIn();
                        $('#confirmarConsulta').fadeIn();
                	}
                }else if( paso == 'mfaLogin'){
                	if('errorCode' in respuesta){ //Excepcion al Consultar Yoddle
                		sweetAlert("Oops...", "Lo sentimos , hubo un problema consultar su cuenta . Por favor, inténtelo de nuevo más tarde. Codigo de Error"+respuesta.errorCode, "error");
                		cerrarModal();
                	}else{
                		$('#formMfaLogin').html("").show();
                    	$('#formLoginBank').html("").show();
                    	$("#spinner").html("");
                    	$('#dep90').val(formatCurrency(respuesta.depositosPromedio, "$"));
                        $('#ret90').val(formatCurrency(respuesta.retirosPromedio, "$"));
                        $('#saldo90').val(formatCurrency(respuesta.saldoPromedio, "$"));
                        $('.loadingActive').hide();
                        $('#consultarInfo').hide();
                        $('.defaultBubble').fadeOut();
                        $('.successBubble').fadeIn();
                        $('#confirmarConsulta').fadeIn();
                        cerrarModal();
                	}
                }else if (respuesta.error) {
                    $('#accionesNormal').hide();
                    $('#accionesError').fadeIn();
                    cerrarModal();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                restartLoadBar();
                sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                cerrarModal();
            }
        });
	}
}
function cleanformLogin(){
	$('#formLoginBank').html("");
	$('#formLoginBank').show();
	$('#formMfaLogin').html("");
	$('#formMfaLogin').show();
	$("#spinner").html("");
}

function spinner(){
	var html="<center><i class=\"fa fa-refresh fa-spin\" style=\"font-size:60px;color:#298df5\"></i><p style=\"font-size:16px;color:#298df5\">Consultando...</p></center>";
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
}


function buildLoginBank(respuesta){
	console.log("buildLoginBank...");
	var html="";
	//html+="<p class=\"headingColor textUpper letterspacing1.5 font25 paddingRight15 stepTitle\">"+respuesta.defaultOrgDisplayName+"</p>";
	html+="<form action=\"#\" name=\"formLoginBank\" id=\"formLoginBank\">"
	$.each(respuesta.componentList, function(key, componentList) {
				html+="<div class=\"floatLeft paddingTop20 col4 col6-tab col12-mob\">";
				html+="<p class=\" marginBottom15 gray font14\">"+respuesta.componentList[key].displayName+"</p>";
				html+="<input class=\"inPuts4a formValues headingColor\" name=\""+respuesta.componentList[key].name+"\" id=\""+respuesta.componentList[key].valueIdentifier+"\" size=\""+respuesta.componentList[key].size+"\" maxlength=\""+respuesta.componentList[key].maxlength+"\" autocomplete=\"off\" autocapitalize=\"off\" value=\"\" type=\"password\">";
				html+="</div>";
	});
	html+="<div class=\"floatLeft paddingTop20 col4 col6-tab col12-mob\">";
		html+="<input type=\"button\" class=\"consultarBox marginLeft15 center colorWhite letterspacing1 font16\" value=\"Procesar\" onclick=\"peticionLoginBancos('formLoginBank','addAccount');\" />";
	html+="</div>";
	html+="</form>"
	return html;
}

function buildFormMFA(respuesta){
	console.log("BuildFormMFA...");
	var html="";
	html+="<div class=\"floatLeft paddingTop20 col4 col6-tab col12-mob\">";
	html+="</div>";
	html+="<div class=\"floatLeft paddingTop20 col4 col6-tab col12-mob\">";
	html+="<p class=\" marginBottom15 gray font14\">"+respuesta.fieldInfo.displayString+"</p>";
	if(respuesta.fieldInfo.mfaFieldInfoType == "TOKEN_ID"){
		html+="<form action=\"#\" name=\"formLoginMFA\" id=\"formLoginMFA\">";
			html+="<input name=\"token\" class=\"inPuts4a formValues headingColor\" id=\"token\"  maxlength=\""+respuesta.fieldInfo.maximumLength+"\" autocomplete=\"off\" autocapitalize=\"off\" value=\"\" type=\""+respuesta.fieldInfo.responseFieldType+"\">";
			html+="<div class=\"floatLeft paddingTop20 col4 col6-tab col12-mob\">";
				html+="<input type=\"button\" class=\"consultarBox marginLeft15 center colorWhite letterspacing1 font16\" value=\"Enviar\" onclick=\"peticionLoginBancos('formLoginMFA','mfaLogin');\" />";
			html+="</div>";
		html+="</form>";
	}else{
		html+="<h1>Opcion en Desarrollo....</h1>";
	}
	html+="</div>";
	return html;
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
                    submitNextPage();
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
                } else if (cara === 'Vuelta'){
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

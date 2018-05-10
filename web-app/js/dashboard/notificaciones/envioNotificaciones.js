$.getCronList = $.contextAwarePathJS +"notificaciones/getCronList";
$.loadCronInformation = $.contextAwarePathJS + "notificaciones/loadCronInformation";
$.loadDataCron = $.contextAwarePathJS +"notificaciones/loadDataCron";
$.deleteCron = $.contextAwarePathJS +"notificaciones/deleteCron";

$(document).ready(function () {
    $(".js-example-basic-multiple").select2({
        allowClear: true
    });
    $(".js-example-basic-multiple2").select2({
        allowClear: true
    });
    $("#hours-cron").timepicker({
        showMinutes: false,
        showPeriod: false,
        showLeadingZero: false,
        showPeriodLabels: false,
        hourText: 'Hora'
    });

    $('.day-cron').timepicker({
        showPeriodLabels: false,
        showLeadingZero: false,
        hourText: 'Hora',
        minuteText: 'Minuto'
    });

    $('.day-cron').inputmask({
        mask: '99:99'
    });

    $('#newCronConfiguration-btn').on('click', function (event) {
        event.preventDefault();
        if ($("input[name=tipoDeEnvioEnvio]:checked").val() === '0') {
            loadDataCron(0);
        } else if($("input[name=tipoDeEnvioEnvio]:checked").val() === '1'){
            loadDataCronAbandono(0);
        }
    });

    $("#datepickerCron").datepicker({
        changeMonth: false,
        changeYear: false,
        dayNamesMin: ["", "", "", "", "", "", ""],
        stepMonths: 0,
        duration: 0,
        onSelect: function () {
            var selectedDate = $(this).datepicker('getDate');
            $("#datepickerAux").datepicker("setDate", selectedDate);
            $("#datepickerCron").val(selectedDate.getDate());
        }
    }).focus(function () {
        var auxDate = $("#datepickerAux").datepicker('getDate');
        $("#datepickerCron").datepicker("setDate", auxDate);
        $("#datepickerCron").val(auxDate.getDate());

        $(".ui-datepicker-prev, .ui-datepicker-next, .ui-datepicker-header, .ui-widget-header").remove();
    });

    $("#datepickerAux").datepicker();

    $('#saveCron-btn').on('click', function (event) {
        event.preventDefault();
        validateForm();
    });

    $('#cron-tb').on('click', 'button.cronDetails', function (event) {
        event.preventDefault();
        var idTipoDeEnvio = this.getAttribute('data-idtipodeenvio');
        var idCron = this.getAttribute('data-id');
        loadDataCronDetails(idCron,idTipoDeEnvio);
    });

    $('#cron-tb').on('click', 'button.deleteCron', function (event) {
        event.preventDefault();
        var idCron = this.getAttribute('data-id');
        deleteCron(idCron);
    });

    $('.only-number').keypress(function (event) {
        return validarNumero(event);
    });

    getCronList();
    confugurationCron();
});

function getCronList() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getCronList,
        contentType: "application/json",
        success: function (response) {
            $("#cron-tb tbody").empty();
            if (response.cronList.length > 0) {
                $.each(response.cronList, function (index) {
                    var row = "";
                    if (index === 0) {
                        row += "<tr></tr>";
                    }
                    row += '<tr>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10">';
                    row += '<span class="textUpper">Configuración</span><br/>';
                    row += '<span class="font14 tableDescriptionColor">' + this.cronExpression + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'Plantilla SMS <br/>';
                    $.each(this.templates, function () {
                        if (this.tipoPlantilla.name === "SMS") {
                            row += '<span class="font14 textUpper tableDescriptionColor">' + this.nombrePlantilla + '</span><br/>';
                        }
                    });
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += 'Plantilla email <br/>';
                    $.each(this.templates, function () {
                        if (this.tipoPlantilla.name === "EMAIL") {
                            row += '<span class="font14 textUpper tableDescriptionColor">' + this.nombrePlantilla + '</span><br/>';
                        }
                    });
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10">';
                    row += '<span class="textUpper">Tipo de Envío</span><br/>';
                    $.each(this.templates, function (index) {
                        if (index === 0) {
                            row += '<span class="font14 tableDescriptionColor">' + (this.tipoDeEnvio === 0 ? "Fijo" : "Por Inactividad") + '</span>';
                        }
                    });
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 cronDetails"';
                      $.each(this.templates, function (index) {
                        if (index === 0) {
                            row += 'data-idtipodeenvio="'+(this.tipoDeEnvio === 0 ? 0 : 1)+'" ' ;
                        }
                    });
                    row += 'data-id="' + this.id + '" type="button">editar</button>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 deleteCron" data-id="' + this.id + '" type="button">eliminar</button>';
                    row += '</td>';
                    row += '</tr>';
                    $("#cron-tb tbody:last").append(row);
                });
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="3" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay envíos registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#cron-tb tbody:last").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function loadDataCron(idCron) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataCron,
        data: "idCron=" + idCron+ "&idTipoDeEnvio=" + 0,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#smsOptions-div").empty();
            $("#emailOptions-div").empty();
            $(".js-example-basic-multiple").empty();
            $(".js-example-basic-multiple2").empty();
            $("#idTipoDeEnvio2").val(0);
        },
        success: function (response) {
            $('#formAddCron')[0].reset();

            if (response.templatesSms.length > 0 || response.templatesEmail.length > 0) {
                $("#idCron").val(idCron);
                $("[name=cronOptions]").val([response.notificacionEnvio.cronOptions]);
                $("#hours-cron").val(response.notificacionEnvio.hour);
                $("#dayTime").val(response.notificacionEnvio.dayTime);
                $("#daysWeek").val(response.notificacionEnvio.weekDay);
                $("#weekTime").val(response.notificacionEnvio.weekTime);
                $('#cronOptions-div').show();
                $('#eachMinute2').hide();
                $("#monthTime").val(response.notificacionEnvio.monthTime);

                var date = new Date(2017, 0, response.notificacionEnvio.dayMonth);
                $("#datepickerCron").datepicker("setDate", date);
                var day = $("#datepickerCron").datepicker('getDate').getDate();
                $("#datepickerCron").val(day);
                $("#datepickerAux").datepicker("setDate", date);

                $('input:radio[name="cronOptions"]').change(function () {
                    if ($(this).is(':checked')) {
                        selectFrequency($(this).val());
                    }
                });

                selectFrequency($('input:radio[name="cronOptions"]:checked').val());
                
              if (response.templatesSms.length > 0) {
                    var options = $('.js-example-basic-multiple');
                    $.each(response.templatesSms, function () {
                        options.append($("<option />").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#smsTemplateConfig-div").css("display", "block");
                } else {
                    $("#smsTemplateConfig-div").css("display", "none");
                }
                if (response.templatesEmail.length > 0) {
                    var options2 = $('.js-example-basic-multiple2');
                    $.each(response.templatesEmail, function () {
                        options2.append($("<option />").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#emailTemplateConfig-div").css("display", "block");
                } else {
                    $("#emailTemplateConfig-div").css("display", "none");
                }
                //fillTemplates(response);

                openModal('modalEnvio');
            } else {
                sweetAlert({html: false, title: "¡Atención!", text: "Para configurar el envío de mensajes, primero debe registrar plantillas al sistema.", type: "warning"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function selectFrequency(value) {
    switch (value) {
        case "MINUTO":
            $("#eachHour").css("display", "none");
            $("#eachDay").css("display", "none");
            $("#eachWeek").css("display", "none");
            $("#eachMonth").css("display", "none");
            break;
        case "HORA":
            $("#eachHour").css("display", "block");
            $("#eachDay").css("display", "none");
            $("#eachWeek").css("display", "none");
            $("#eachMonth").css("display", "none");
            break;
        case "DIA":
            $("#eachHour").css("display", "none");
            $("#eachDay").css("display", "block");
            $("#eachWeek").css("display", "none");
            $("#eachMonth").css("display", "none");
            break;
        case "SEMANA":
            $("#eachHour").css("display", "none");
            $("#eachDay").css("display", "none");
            $("#eachWeek").css("display", "block");
            $("#eachMonth").css("display", "none");
            break;
        case "MES":
            $("#eachHour").css("display", "none");
            $("#eachDay").css("display", "none");
            $("#eachWeek").css("display", "none");
            $("#eachMonth").css("display", "block");
            break;
        default:
            $("#eachHour").css("display", "none");
            $("#eachDay").css("display", "none");
            $("#eachWeek").css("display", "none");
            $("#eachMonth").css("display", "none");
            break;
    }
}

function saveCron() {
    var form = $("#formAddCron");
    var postData = new FormData($(form)[0]);
    var formURL = $(form).attr('action');

    $.ajax({
        url: formURL,
        type: 'POST',
        data: postData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.content.error) {
                cronErrorMessage('time', "" + response.content.mensaje + "");
            } else {
                getCronList();
                cerrarModal('modalEnvio');
                cerrarModal('seleccionEnvio');

                sweetAlert({html: false, title: "¡Excelente!", text: "La configuración se ha guardado correctamente.", type: "success"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function deleteCron(idCron) {
    swal({
        title: "¡Importante!",
        text: "¿Está seguro que desea cancelar el envío de mensajes definitivamente?",
        type: "warning",
        showCancelButton: true,
        cancelButtonText: "Me equivoque en la selección",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sí, continuar",
        closeOnConfirm: false
    }, function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: $.deleteCron,
                data: "idCron=" + idCron,
                success: function (response) {
                    if (response.error) {
                        sweetAlert("Oops...", response.mensaje, "error");
                    } else {
                        getCronList();
                        sweetAlert({html: false, title: "¡Excelente!", text: "El envío de mansajes se ha eliminado correctamente.", type: "success"});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    });
}

function validateForm() {
    var errors = 0;
 
    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });
    if($('#idTipoDeEnvio2').val()==='0'){
    
    if ($('input[name=cronOptions]:checked').length <= 0) {
        errors++;
        cronErrorMessage('frequency', "El campo es obligatorio");
    } else {
        if ($('input[name=cronOptions]:checked').val() === "HORA") {
            if ($("#hours-cron").val() === "") {
                errors++;
                cronErrorMessage('time', "El campo es obligatorio");
            } else if (!validateHour($("#hours-cron").val())) {
                errors++;
                cronErrorMessage('time', "La hora del envío contiene un formato inválido");
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "DIA") {
            if ($("#dayTime").val() === "") {
                errors++;
                cronErrorMessage('time', "Debe seleccionar la hora del envío de mensajes");
            } else if (!validateHourTime($("#dayTime").val())) {
                errors++;
                cronErrorMessage('time', "La hora del envío contiene un formato inválido");
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "SEMANA") {
            if ($("#weekTime").val() === "") {
                errors++;
                cronErrorMessage('time', "Debe seleccionar la hora del envío de mensajes");
            } else if (!validateHourTime($("#weekTime").val())) {
                errors++;
                cronErrorMessage('time', "La hora del envío contiene un formato inválido");
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "MES") {
            if ($("#datepickerCron").val() === "") {
                errors++;
                cronErrorMessage('time', "Debe seleccionar el día del envío de mensajes");
            } else if (!validateDay($("#datepickerCron").val())) {
                errors++;
                cronErrorMessage('time', "El día del envío contiene un formato inválido");
            } else if ($("#monthTime").val() === "") {
                errors++;
                cronErrorMessage('time', "Debe seleccionar la hora del envío de mensajes");
            } else if (!validateHourTime($("#monthTime").val())) {
                errors++;
                cronErrorMessage('time', "La hora del envío contiene un formato inválido");
            }
        }
    }
}else{
    if($('#minutosPrueba').val() ===''){
        $('#minutosPrueba').val(0);
    }
    if($('#horasPrueba').val() ===''){
        $('#horasPrueba').val(0);
    }
    if($('#diasPrueba').val() ===''){
        $('#diasPrueba').val(0);
    }

    if($('#horasPrueba').val() === '0' && $('#diasPrueba').val() === '0'){
        if($('#minutosPrueba').val() === '0' || $('#minutosPrueba').val() === '1' || $('#minutosPrueba').val() === '2' || $('#minutosPrueba').val() === '3' || $('#minutosPrueba').val() === '4'){
                errors++;
                cronErrorMessage('time', "Los minutos minimos deben ser 5");
        }
    }
}


if(($('#myselect').val().length === 0 && $('#myselect2').val().length === 0) ){
            errors++;
            cronErrorMessage('plantilla', "Debe seleccionar al menos una plantilla");
}

    if (errors === 0) {
        saveCron();
    }
}

function validateHourTime(value) {
    return (/^(?:([01]?\d|2[0-3]):([0-5]?\d))$/.test(value));
}

function validateHour(value) {
    return (/^(?:([01]?\d|2[0-3]))$/.test(value));
}

function validateDay(value) {
    return (/^([1-9]|[12]\d|3[0-1])$/.test(value));
}

function validarNumero(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla === 13)
        return true;
    if (tecla === 8)
        return true;
    if (tecla === 9)
        return true;
    if (tecla === 0)
        return true;
    var patron = /^[0-9]$/;
    var te = String.fromCharCode(tecla);


    document.onkeypress = KeyPressed;
    function KeyPressed(e)
    {
        return ((window.event) ? event.keyCode : e.keyCode) !== 13;
    }

    return patron.test(te);
}

function confugurationCron() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadCronInformation,
        cache: false,
        success: function (response) {
            $.each(response.listCronOptions, function () {
                var radioBtn = $('<input name="cronOptions" value="' + this + '" type="radio"> <span class="marginRight10">' + this + '</span>');
                radioBtn.appendTo('#cronOptions-div');
            });

            $("#daysWeek").find('option').remove();
            $.each(response.listweekDayOptions, function (k, v) {
                $("#daysWeek").append($("<option />").val(k).text(v.name));
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function cronErrorMessage(element, message) {
    $("#" + element + "-control").html("<span class='help-block marginRight25'><small style='color: red;'>" + message + "</small></span>");
}

function fillTemplates(response) {
    if (response.templatesSms.length > 0) {
        $.each(response.templatesSms, function () {
            var checkBtn = $('<input name="templateOptions" value="' + this.id + '" type="checkbox"> <span class="marginRight10"> ' + this.status + '</span>');
            checkBtn.appendTo('#smsOptions-div');
        });
        $("#smsTemplateConfig-div").css("display", "block");
    } else {
        $("#smsTemplateConfig-div").css("display", "none");
    }

    if (response.templatesEmail.length > 0) {
        $.each(response.templatesEmail, function () {
            var checkBtn = $('<input name="templateOptions" value="' + this.id + '" type="checkbox"> <span class="marginRight10"> ' + this.status + '</span>');
            checkBtn.appendTo('#emailOptions-div');
        });
        $("#emailTemplateConfig-div").css("display", "block");
    } else {
        $("#emailTemplateConfig-div").css("display", "none");
    }

    $.each(response.notificacionEnvio.templateOptions, function () {
        $("input[name=templateOptions][value='" + this + "']").prop('checked', true);
    });
}
function seleccionTipoDeEnvioEnvio(){
    openModal('seleccionEnvio');
}

function loadDataCronAbandono(idCron) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataCron,
        data: "idCron=" + idCron+ "&idTipoDeEnvio=" + 1,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#smsOptions-div").empty();
            $("#emailOptions-div").empty();
            $(".js-example-basic-multiple").empty();
            $(".js-example-basic-multiple2").empty();
            $("#idTipoDeEnvio2").val(1);
        },
        success: function (response) {
            $('#formAddCron')[0].reset();
            if (response.templatesSms.length > 0 || response.templatesEmail.length > 0) {
                $("#idCron").val(idCron);
                $('#cronOptions-div').hide();
                $('#eachMinute2').show();
                
                $("[name=cronOptions]").val([response.notificacionEnvio.cronOptions]);
                $("#hours-cron").val(response.notificacionEnvio.hour);
                $("#dayTime").val(response.notificacionEnvio.dayTime);
                $("#daysWeek").val(response.notificacionEnvio.weekDay);
                $("#weekTime").val(response.notificacionEnvio.weekTime);

                $("#monthTime").val(response.notificacionEnvio.monthTime);

                var date = new Date(2017, 0, response.notificacionEnvio.dayMonth);
                $("#datepickerCron").datepicker("setDate", date);
                var day = $("#datepickerCron").datepicker('getDate').getDate();
                $("#datepickerCron").val(day);
                $("#datepickerAux").datepicker("setDate", date);

                $('input:radio[name="cronOptions"]').change(function () {
                    if ($(this).is(':checked')) {
                        selectFrequency($(this).val());
                    }
                });

                selectFrequency($('input:radio[name="cronOptions"]:checked').val());
                

                if (response.templatesSms.length > 0) {
                    var options = $('.js-example-basic-multiple');
                    $.each(response.templatesSms, function () {
                        options.append($("<option />").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#smsTemplateConfig-div").css("display", "block");
                } else {
                    $("#smsTemplateConfig-div").css("display", "none");
                }
                if (response.templatesEmail.length > 0) {
                    var options2 = $('.js-example-basic-multiple2');
                    $.each(response.templatesEmail, function () {
                        options2.append($("<option />").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#emailTemplateConfig-div").css("display", "block");
                } else {
                    $("#emailTemplateConfig-div").css("display", "none");
                }
                //fillTemplates(response);

                openModal('modalEnvio');
            } else {
                sweetAlert({html: false, title: "¡Atención!", text: "Para configurar el envío de mensajes, primero debe registrar plantillas al sistema.", type: "warning"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}
function loadDataCronDetails(idCron,idTipoDeEnvio) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataCron,
        data: "idCron=" + idCron+ "&idTipoDeEnvio=" + idTipoDeEnvio,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#smsOptions-div").empty();
            $("#emailOptions-div").empty();
            $(".js-example-basic-multiple").empty();
            $(".js-example-basic-multiple2").empty();
            if (idTipoDeEnvio === "0") {
                $("#idTipoDeEnvio2").val(0);
            } else if (idTipoDeEnvio === "1") {
                $("#idTipoDeEnvio2").val(1);
            }

        },
        success: function (response) {
            $('#formAddCron')[0].reset();
            if (response.templatesSms.length > 0 || response.templatesEmail.length > 0) {
                if (idTipoDeEnvio === "0") {
                    $('#cronOptions-div').show();
                    $('#eachMinute2').hide();
                } else if (idTipoDeEnvio === "1") {
                    $('#cronOptions-div').hide();
                    $('#eachMinute2').show();
                     $('#diasPrueba').val(response.notificacionEnvio.dias);
                     $('#horasPrueba').val(response.notificacionEnvio.horas);
                     $('#minutosPrueba').val(response.notificacionEnvio.minutos);
                }
                $("#idCron").val(idCron);
                $("[name=cronOptions]").val([response.notificacionEnvio.cronOptions]);
                $("#hours-cron").val(response.notificacionEnvio.hour);
                $("#dayTime").val(response.notificacionEnvio.dayTime);
                $("#daysWeek").val(response.notificacionEnvio.weekDay);
                $("#weekTime").val(response.notificacionEnvio.weekTime);

                $("#monthTime").val(response.notificacionEnvio.monthTime);

                var date = new Date(2017, 0, response.notificacionEnvio.dayMonth);
                $("#datepickerCron").datepicker("setDate", date);
                var day = $("#datepickerCron").datepicker('getDate').getDate();
                $("#datepickerCron").val(day);
                $("#datepickerAux").datepicker("setDate", date);

                $('input:radio[name="cronOptions"]').change(function () {
                    if ($(this).is(':checked')) {
                        selectFrequency($(this).val());
                    }
                });

                selectFrequency($('input:radio[name="cronOptions"]:checked').val());


                if (response.templatesSms.length > 0) {
                    var options = $('.js-example-basic-multiple');
                    $.each(response.templatesSms, function () {
                          options.append($("<option />").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#smsTemplateConfig-div").css("display", "block");
                } else {
                    $("#smsTemplateConfig-div").css("display", "none");
                }

                if (response.templatesEmail.length > 0) {
                    var options2 = $('.js-example-basic-multiple2');
                    $.each(response.templatesEmail, function () {
                        options2.append($("<option/>").val(this.id).text(this.nombrePlantilla));
                    });
                    $("#emailTemplateConfig-div").css("display", "block");
                } else {
                    $("#emailTemplateConfig-div").css("display", "none");
                }
                
                $.each(response.notificacionEnvio.templateOptions, function () {
                    var aux1 = this.valueOf();
                    $("#myselect option").each(function ()
                    {
                        var aux2 = parseInt($(this).val());
                        if (aux1 === aux2) {
                            $("#myselect option[value='" + aux1 + "']").prop('selected', true);

                        }
                    });
                    $("#myselect2 option").each(function ()
                    {
                        var aux2 = parseInt($(this).val());
                        if (aux1 === aux2) {
                            $("#myselect2 option[value='" + aux1 + "']").prop('selected', true);

                        }
                    });
                });

                openModal('modalEnvio');
            } else {
                sweetAlert({html: false, title: "¡Atención!", text: "Para configurar el envío de mensajes, primero debe registrar plantillas al sistema.", type: "warning"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}


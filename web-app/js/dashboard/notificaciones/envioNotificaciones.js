$.getCronList = "/notificaciones/getCronList";
$.loadCronInformation = "/notificaciones/loadCronInformation";
$.loadDataCron = "/notificaciones/loadDataCron";
$.deleteCron = "/notificaciones/deleteCron";
$.validCronConfig = "/notificaciones/validCronConfig";

$(document).ready(function () {
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
        loadDataCron(0);
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
        var idCron = this.getAttribute('data-id');
        loadDataCron(idCron);
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
                    row += '<span class="font14 tableDescriptionColor">' + this.cronExpression + '</span>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 cronDetails" data-id="' + this.id + '" type="button">editar</button>';
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
        data: "idCron=" + idCron,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('#leyendaCron').html("");
        },
        success: function (response) {
            $('#formAddCron')[0].reset();

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

            $("#daysWeek").selectmenu();

            selectFrequency($('input:radio[name="cronOptions"]:checked').val());

            openModal('modalEnvio');
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
            getCronList();
            cerrarModal('modalEnvio');

            sweetAlert({html: false, title: "¡Excelente!", text: "La configuración se ha guardado correctamente.", type: "success"});
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
                    if (response.error === true) {
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
    if ($('input[name=cronOptions]:checked').length <= 0) {
        $('#leyendaCron').html("<small style='color: red;'>Debe seleccionar la frecuencia del envío de mensajes</small>");
    } else {
        var errorMessage = "";
        $('#leyendaCron').html("");

        if ($('input[name=cronOptions]:checked').val() === "HORA") {
            if ($("#hours-cron").val() === "") {
                errorMessage = "Debe seleccionar la hora del envío de mensajes";
            } else if (!validateHour($("#hours-cron").val())) {
                errorMessage = "La hora del envío contiene un formato inválido";
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "DIA") {
            if ($("#dayTime").val() === "") {
                errorMessage = "Debe seleccionar la hora del envío de mensajes";
            } else if (!validateHourTime($("#dayTime").val())) {
                errorMessage = "La hora del envío contiene un formato inválido";
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "SEMANA") {
            if ($("#weekTime").val() === "") {
                errorMessage = "Debe seleccionar la hora del envío de mensajes";
            } else if (!validateHourTime($("#weekTime").val())) {
                errorMessage = "La hora del envío contiene un formato inválido";
            }
        }

        if ($('input[name=cronOptions]:checked').val() === "MES") {
            if ($("#datepickerCron").val() === "") {
                errorMessage = "Debe seleccionar el día del envío de mensajes";
            } else if (!validateDay($("#datepickerCron").val())) {
                errorMessage = "El día del envío contiene un formato inválido";
            } else if ($("#monthTime").val() === "") {
                errorMessage = "Debe seleccionar la hora del envío de mensajes";
            } else if (!validateHourTime($("#monthTime").val())) {
                errorMessage = "La hora del envío contiene un formato inválido";
            }
        }

        if (errorMessage === "") {
            validCronConfig(function (response) {
                if (response.estatus === false) {
                    $('#leyendaCron').html("<small style='color: red;'>La configuración de envío de mensajes ya ha sido registrada</small>");
                } else if (response.estatus === "ERROR") {
                    $('#leyendaCron').html("<small style='color: red;'>Ocurrió un error al validar la configuración</small>");
                } else {
                    saveCron();
                }
            });
        } else {
            $('#leyendaCron').html("<small style='color: red;'>" + errorMessage + "</small>");
        }
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
                var radioBtn = $('<input name="cronOptions" value="' + this + '" type="radio"> <span class="marginRight20">' + this + '</span>');
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

function validCronConfig(callback) {
    var envio = new Object();
    envio.idNotificacionCron = $("#idCron").val();
    envio.cronOptions = $('input:radio[name="cronOptions"]:checked').val();
    envio.hour = $("#hours-cron").val().trim();
    envio.dayTime = $("#dayTime").val().trim();
    envio.weekDay = $("#daysWeek").val();
    envio.weekTime = $("#weekTime").val().trim();
    envio.dayMonth = $("#datepickerCron").val().trim();
    envio.monthTime = $("#monthTime").val().trim();

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.validCronConfig,
        data: JSON.stringify(envio),
        contentType: "application/json",
        cache: false,
        success: function (response) {
            callback(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var response = {estatus: "ERROR"};
            callback(response);
        }
    });
}
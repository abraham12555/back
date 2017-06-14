$.loadDataCron = "/notificaciones/loadDataCron";
$.deleteCron = "/notificaciones/deleteCron";

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
        loadDataCron();
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

    $('#editCron-btn').on('click', function (event) {
        event.preventDefault();
        loadDataCron();
    });

    $('#deleteCron-btn').on('click', function (event) {
        event.preventDefault();
        deleteCron();
    });

    $('.only-number').keypress(function (event) {
        return validarNumero(event);
    });
});

function loadDataCron() {
    var notificacion = $("#idConfiguracionNotificacion").val();

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataCron,
        data: "notification=" + notificacion,
        cache: false,
        beforeSend: function (XMLHttpRequest, settings) {
            $('#leyendaCron').html("");
        },
        success: function (response) {
            $('#formAddCron')[0].reset();

            $("#idNotificacionCron").val(notificacion);
            $("[name=cronOptions]").val([response.notificacionEnvio.frequency]);
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
                $('#leyendaCron').html("");
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
            $("#cronMessage").html(response.content);
            $("#newCron").css("display", "none");
            $("#cronContent").css("display", "block");
            $("#editCron-div").css("display", "block");
            $("#deleteCron-div").css("display", "block");

            cerrarModal('modalEnvio');

            sweetAlert({html: false, title: "¡Excelente!", text: "La configuración se ha guardado correctamente.", type: "success"});
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function deleteCron() {
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
                data: "idNotificacion=" + $("#idConfiguracionNotificacion").val(),
                success: function (response) {
                    if (response.confirm === true) {
                        $("#newCron").css("display", "block");
                        $("#cronContent").css("display", "none");
                        $("#editCron-div").css("display", "none");
                        $("#deleteCron-div").css("display", "none");
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
            saveCron();
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
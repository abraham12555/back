$.getSmsTemplates = $.contextAwarePathJS + "notificaciones/getSmsTemplates";
$.loadDataSmsTemplate = $.contextAwarePathJS + "notificaciones/loadDataSmsTemplate";
$.viewSmsTemplateDetails = $.contextAwarePathJS + "notificaciones/viewTemplateDetails";
$.deleteSmsTemplate = $.contextAwarePathJS + "notificaciones/deleteSmsTemplate";

$(document).ready(function () {
    $('#newSmsTemplate-btn').on('click', function (event) {
        event.preventDefault();
        loadDataSmsTemplate();
    });

    $('#saveSmsTemplate-btn').on('click', function (event) {
        event.preventDefault();
        validateTemplate();
    });

    $('#plantillasSms-tb').on('click', 'button.smsTemplateDetails', function (event) {
        event.preventDefault();
        var idTemplate = this.getAttribute('data-id');
        viewSmsTemplateDetails(idTemplate);
    });

    $('#plantillasSms-tb').on('click', 'button.deleteSmsTemplate', function (event) {
        event.preventDefault();
        var idTemplate = this.getAttribute('data-id');
        deleteSmsTemplate(idTemplate);
    });

    getSmsTemplates();
});

function getSmsTemplates() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getSmsTemplates,
        contentType: "application/json",
        success: function (response) {
            $("#plantillasSms-tb tbody").empty();
            if (response.templates.length > 0) {
                $.each(response.templates, function (index) {
                    var row = "";
                    if (index === 0) {
                        row += "<tr></tr>";
                    }
                    row += '<tr>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<span class="textUpper">estatus </span><br/>';
                    row += '<span class="font14 tableDescriptionColor textUpper">' + this.status + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10">';
                    row += '<span class="textUpper">plantilla </span><br/>';
                    row += '<span class="font14 tableDescriptionColor">' + this.plantilla + '</span>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 smsTemplateDetails" data-id="' + this.id + '" type="button">editar</button>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 deleteSmsTemplate" data-id="' + this.id + '" type="button">eliminar</button>';
                    row += '</td>';
                    row += '</tr>';
                    $("#plantillasSms-tb tbody:last").append(row);
                });

                if (response.statusOption) {
                    $("#newSmsTemplate-btn").css("display", "block");
                } else {
                    $("#newSmsTemplate-btn").css("display", "none");
                }
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="4" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay plantillas registradas</span>';
                row += '</td>';
                row += '</tr>';
                $("#plantillasSms-tb tbody:last").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function initDroppable($elements) {
    $elements.droppable({
        accept: ":not(.ui-sortable-helper)",
        drop: function (event, ui) {
            var tempid = ui.draggable.text();
            var dropText;
            dropText = "{" + tempid + "}";
            var range1 = $elements[0].selectionStart;
            var val = $elements.val();
            var str1 = val.substring(0, range1);
            var str3 = val.substring(range1, val.length);
            $elements.val(str1 + dropText + str3);
        }
    });
}

function loadDataSmsTemplate() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataSmsTemplate,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#status-div").empty();
        },
        success: function (response) {
            $("#formAddTemplate")[0].reset();
            $("#idTemplate").val("0");

            $("#statusConfig-div").css("display", "block");
            loadAvailableFields(response.fields);

            $.each(response.status, function () {
                var radioBtn = $('<input name="status" value="' + this + '" type="radio"> <span class="marginRight20">' + this + '</span>');
                radioBtn.appendTo('#status-div');
            });

            initDroppable($("#contenidoSms"));
            openModal('modalPlantillaSms');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function saveSmsTemplate() {
    var form = $("#formAddTemplate");
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
            if (response.error) {
                sweetAlert("Oops...", response.mensaje, "error");
            } else {
                getSmsTemplates();
                cerrarModal('modalPlantillaSms');
                sweetAlert({html: false, title: "¡Excelente!", text: "La plantilla se ha guardado correctamente.", type: "success"});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function viewSmsTemplateDetails(idTemplate) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.viewSmsTemplateDetails,
        data: "idTemplate=" + idTemplate,
        success: function (response) {
            $("#statusConfig-div").css("display", "none");
            loadAvailableFields(response.fields);

            $("#idTemplate").val(response.template.id);
            $("#contenidoSms").val(response.template.plantilla);
            initDroppable($("#contenidoSms"));

            openModal('modalPlantillaSms');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function loadAvailableFields(fields) {
    $(".availableFields li").remove();
    $.each(fields, function () {
        var content = '<li class="gray font14 fontWeight500 latterspacing1 paddingTop12" style="cursor:move">';
        content += this;
        content += '</li>';
        $(".availableFields").append(content);
    });

    $(".availableFields li").draggable({
        appendTo: "body",
        helper: "clone",
        cursor: "move",
        revert: "invalid"
    });
}

function deleteSmsTemplate(idTemplate) {
    swal({
        title: "¡Importante!",
        text: "¿Está seguro que desea eliminar la plantilla definitivamente?",
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
                url: $.deleteSmsTemplate,
                data: "idTemplate=" + idTemplate,
                success: function (response) {
                    if (response.error) {
                        sweetAlert("Oops...", response.mensaje, "error");
                    } else {
                        getSmsTemplates();
                        sweetAlert({html: false, title: "¡Excelente!", text: "La plantilla se ha eliminado correctamente.", type: "success"});
                        getCronList();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    });
}

function validateTemplate() {
    var errors = 0;

    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#contenidoSms").val().trim() === "") {
        errors++;
        errorMessageTemplate('contenidoSms', "El campo es obligatorio " );
    } else if ($('#contenidoSms').val().length > 120) {
        errors++;
        errorMessageTemplate('contenidoSms', "El texto no puede contener más de 120 caracteres "  );
    }

    if ($("#idTemplate").val() === "0" && $('input[name=status]:checked').length <= 0) {
        errors++;
        errorMessageTemplate('status', "El campo es obligatorio  ");
    }

    if (errors === 0) {
        saveSmsTemplate();
    }
}

function errorMessageTemplate(element, message) {
    $("#" + element + "-control").html("<span class='help-block marginRight25'><small style='color: red;'>" + message + "</small></span>");
}

$.getEmailTemplates = $.contextAwarePathJS + "notificaciones/getEmailTemplates";
$.loadDataEmailTemplate = $.contextAwarePathJS + "notificaciones/loadDataEmailTemplate";
$.viewEmailTemplateDetails = $.contextAwarePathJS + "notificaciones/viewTemplateDetails";
$.deleteEmailTemplate = $.contextAwarePathJS + "notificaciones/deleteEmailTemplate";

$(document).ready(function () {
    $('#newEmailTemplate-btn').on('click', function (event) {
        event.preventDefault();
        loadDataEmailTemplate();
    });

    $('#saveEmailTemplate-btn').on('click', function (event) {
        event.preventDefault();
        validateEmailTemplate();
    });

    $('#plantillasEmail-tb').on('click', 'button.emailTemplateDetails', function (event) {
        event.preventDefault();
        var idEmailTemplate = this.getAttribute('data-id');
        viewEmailTemplateDetails(idEmailTemplate);
    });

    $('#plantillasEmail-tb').on('click', 'button.deleteEmailTemplate', function (event) {
        event.preventDefault();
        var idEmailTemplate = this.getAttribute('data-id');
        deleteEmailTemplate(idEmailTemplate);
    });

    getEmailTemplates();
});

function getEmailTemplates() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getEmailTemplates,
        contentType: "application/json",
        success: function (response) {
            $("#plantillasEmail-tb tbody").empty();
            if (response.templates.length > 0) {
                $.each(response.templates, function (index) {
                    var row = "";
                    if (index === 0) {
                        row += "<tr></tr>";
                    }
                    row += '<tr>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<span class="textUpper">nombre de plantilla </span><br/>';
                    row += '<span class="font14 tableDescriptionColor textUpper">' + this.nombrePlantilla + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<span class="textUpper">tipo de envío </span><br/>';
                    row += '<span class="font14 tableDescriptionColor textUpper">' + (this.tipoDeEnvio === 0 ? "Fijo" : "Por Inactividad")  + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<span class="textUpper">Paso Solicitud </span><br/>';
                   var estatusEmail = this.status
                    $.each(response.statusOption, function (index, value) {
                        if(index == estatusEmail){
                    row += '<span class="font14 tableDescriptionColor textUpper">' + value + '</span>';
                        }
                    });
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10">';
                    row += '<span class="textUpper">asunto </span><br/>';
                    row += '<span class="font14 tableDescriptionColor">' + this.asunto + '</span>';
                    row += '</td>';
                    row += '<td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10">';
                    row += '<span class="textUpper">plantilla </span><br/>';
                    row += '<span class="font14 tableDescriptionColor">' + this.plantilla + '</span>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 emailTemplateDetails" data-id="' + this.id + '" type="button">editar</button>';
                    row += '</td>';
                    row += '<td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                    row += '<button class="greenBox colorWhite width100 deleteEmailTemplate" data-id="' + this.id + '" type="button">eliminar</button>';
                    row += '</td>';
                    row += '</tr>';
                    $("#plantillasEmail-tb tbody:last").append(row);

                    if (response.statusOption) {
                        $("#newEmailTemplate-btn").css("display", "block");
                    } else {
                        $("#newEmailTemplate-btn").css("display", "none");
                    }
                });
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="4" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay plantillas registradas</span>';
                row += '</td>';
                row += '</tr>';
                $("#plantillasEmail-tb tbody:last").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function loadDataEmailTemplate() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataEmailTemplate,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });

            $("#statusEmail-div").empty();
        },
        success: function (response) {
            $("#formAddEmailTemplate")[0].reset();
            $("#idEmailTemplate").val("0");

            $("#statusEmailConfig-div").css("display", "block");
            loadAvailableFieldsEmail(response.fields);

            $.each(response.status, function (index,value) {
                var radioBtn = $('<li><input name="statusEmail" value="' + index + '" type="radio"> <span class="marginRight20">' + value + '</span></li>');
                radioBtn.appendTo('#statusEmail-div');
            });

            dragNDrop();
            openModal('seleccionEmail');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function saveEmailTemplate() {
    var form = $("#formAddEmailTemplate");
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
                getEmailTemplates();
                cerrarModal('modalPlantillaEmail');
                cerrarModal('seleccionEmail');
                sweetAlert({html: false, title: "¡Excelente!", text: "La plantilla se ha guardado correctamente.", type: "success"});
                getCronList();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function viewEmailTemplateDetails(idEmailTemplate) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.viewEmailTemplateDetails,
        data: "idTemplate=" + idEmailTemplate,
        beforeSend: function (XMLHttpRequest, settings) {
            $('span[class*="help-block"]').each(function () {
                $(this).remove();
            });
            $("#statusEmail-div").empty();
        },
        success: function (response) {

            iniciarSummernote();
            $("#statusEmailConfig-div").css("display", "block");
            loadAvailableFieldsEmail(response.fields);

            $("#idEmailTemplate").val(response.template.id);
            $("#idTipoDeEnvioEmail").val(response.template.tipoDeEnvio);   
            $("#summernote").summernote("code", response.template.plantilla);
            //$("#summernote").val(response.template.plantilla);
            $("#emailAsunto").val(response.template.asunto);
            $("#nombrePlantillaEmail").val(response.template.nombrePlantilla);
            $.each(response.status, function (index,value) {
                var radioBtn;
               if(parseInt(index) === response.template.status){
                    radioBtn = $('<li><input name="statusEmail" value="' + index + '" type="radio" checked> <span class="marginRight20">' + value + '</span></li>');
               }else{
                    radioBtn = $('<li><input name="statusEmail" value="' + index + '" type="radio"> <span class="marginRight20">' + value + '</span></li>');
               }
                radioBtn.appendTo('#statusEmail-div');
            });
            dragNDrop();
            openModal('modalPlantillaEmail');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function deleteEmailTemplate(idEmailTemplate) {
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
                url: $.deleteEmailTemplate,
                data: "idTemplate=" + idEmailTemplate,
                success: function (response) {
                    if (response.error) {
                        sweetAlert("Oops...", response.mensaje, "error");
                    } else {
                        getEmailTemplates();
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

function validateEmailTemplate() {
    var errors = 0;

    $('span[class*="help-block"]').each(function () {
        $(this).remove();
    });

    if ($("#summernote").val().trim() === "") {
        errors++;
        errorMessageTemplate('contenidoEmail', "El campo es obligatorio");
    } else if ($('#summernote').val().trim().length > 2000) {
        errors++;
        errorMessageTemplate('contenidoEmail', "El texto no puede contener más de 2000 caracteres");
    }

    if ($("#emailAsunto").val().trim() === "") {
        errors++;
        errorMessageTemplate('emailAsunto', "El campo es obligatorio");
    } else if ($('#emailAsunto').val().trim().length > 100) {
        errors++;
        errorMessageTemplate('emailAsunto', "El texto no puede contener más de 100 caracteres");
    }

    if ($('input[name=statusEmail]:checked').length <= 0) {
        errors++;
        errorMessageTemplate('statusEmail', "El campo es obligatorio");
    }
    if ($("#nombrePlantillaEmail").val().trim() === "") {
        errors++;
        errorMessageTemplate('nombrePlantillaEmail', "El campo es obligatorio");
    }

    if (errors === 0) {
        saveEmailTemplate();
    }
}
function seleccionTipoDeEnvioEmail() {

    if ($("input[name=tipoDeEnvioEmail]:checked").val() === '0') {
        $("#idTipoDeEnvioEmail").val('0');
        openModal('modalPlantillaEmail');
        iniciarSummernote();

    } else if ($("input[name=tipoDeEnvioEmail]:checked").val() === '1') {
        $("#idTipoDeEnvioEmail").val('1');
        openModal('modalPlantillaEmail');
        iniciarSummernote();
    }
}

function dragNDrop (){
    
    $('.prueba').on('dragstart', function (e) {
      var elemento = $(e.currentTarget).data('elemento');
      e.originalEvent.dataTransfer.setData('Text', elemento);
    });
  }
function iniciarSummernote() {
    $('#summernote').summernote('destroy');
    $('#summernote').summernote({
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['view', ['codeview']]
        ],
        height: 200,
        width: 400,
        disableDragAndDrop: true

    });
    $("#summernote").summernote("code", "");

}

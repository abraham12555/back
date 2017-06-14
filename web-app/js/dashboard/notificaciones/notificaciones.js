$.loadDataTemplate = "/notificaciones/loadDataTemplate";
$.searchDataTemplate = "/notificaciones/searchDataTemplate";
$.deleteDataTemplate = "/notificaciones/deleteDataTemplate";

$(document).ready(function () {
    $('#newTemplate-btn').on('click', function (event) {
        event.preventDefault();
        loadDataTemplate();
    });

    $('#saveTemplate-btn').on('click', function (event) {
        event.preventDefault();

        if ($('#contenidoSms').val().trim() === '') {
            $('#leyendaContenidoSms').html("<small style='color: red;'>Debe introducir el texto de la plantilla</small>");
        } else if($('#contenidoSms').val().trim().length > 200){
            $('#leyendaContenidoSms').html("<small style='color: red;'>El texto no puede contener más de 300 caracteres</small>");
        } 
        else{
            $('#leyendaContenidoSms').html("");
            saveTemplate();
        }
    });

    $('#editTemplate-btn').on('click', function (event) {
        event.preventDefault();
        searchDataTemplate();
    });

    $('#deleteTemplate-btn').on('click', function (event) {
        event.preventDefault();
        deleteTemplate();
    });
});

function initDroppable($elements) {
    $elements.droppable({
        accept: ":not(.ui-sortable-helper)",
        drop: function (event, ui) {
            var tempid = ui.draggable.text();
            var dropText;
            dropText = " ${" + tempid + "} ";
            var droparea = document.getElementById('contenidoSms');
            var range1 = droparea.selectionStart;
            var val = droparea.value;
            var str1 = val.substring(0, range1);
            var str3 = val.substring(range1, val.length);
            droparea.value = str1 + dropText + str3;
        }
    });
}

function loadDataTemplate() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.loadDataTemplate,
        success: function (response) {
            var fields = response;
            loadAvailableFields(fields);

            $("#idNotificacion").val($("#idConfiguracionNotificacion").val());
            $("#contenidoSms").val("");
            initDroppable($("#contenidoSms"));

            $('#leyendaContenidoSms').html("");
            openModal('modalPlantillaSms');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function saveTemplate() {
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
            $("#templateSms").html(response.templateSms);
            $("#idConfiguracionNotificacion").val(response.notificacion);

            $("#newTemplate").css("display", "none");
            $("#contentTemplate").css("display", "block");
            $("#editTemplate-div").css("display", "block");
            $("#deleteTemplate-div").css("display", "block");
            $("#emptyCronContent").css("display", "none");
            
            if($("#cronContent").css('display') === 'none'){
                $("#newCron").css("display", "block");
            }
                        
            cerrarModal('modalPlantillaSms');

            sweetAlert({html: false, title: "¡Excelente!", text: "La plantilla se ha guardado correctamente.", type: "success"});
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function searchDataTemplate() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.searchDataTemplate,
        success: function (response) {
            var fields = response.availableOptions;
            loadAvailableFields(fields);

            $("#idNotificacion").val($("#idConfiguracionNotificacion").val());
            $("#contenidoSms").val(response.templateSms);
            initDroppable($("#contenidoSms"));

            $('#leyendaContenidoSms').html("");
            openModal('modalPlantillaSms');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
        }
    });
}

function loadAvailableFields(fields) {
    $("#availableFields li").remove();
    $.each(fields, function () {
        var content = '<li class="gray font14 fontWeight500 latterspacing1 paddingTop12" style="cursor:move">';
        content += this;
        content += '</li>';
        $("#availableFields").append(content);
    });

    $("#availableFields li").draggable({
        appendTo: "body",
        helper: "clone",
        cursor: "move",
        revert: "invalid"
    });
}

function deleteTemplate() {
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
                url: $.deleteDataTemplate,
                data: "idNotificacion=" + $("#idConfiguracionNotificacion").val(),
                success: function (response) {
                    if (response.confirm === true) {
                        $("#idConfiguracionNotificacion").val("0");

                        $("#newTemplate").css("display", "block");
                        $("#contentTemplate").css("display", "none");
                        $("#editTemplate-div").css("display", "none");
            $("#deleteTemplate-div").css("display", "none");
                        $("#emptyCronContent-td").html("<strong>No se ha configurado ninguna plantilla para el envío de mensajes</strong>");
                        $("#emptyCronContent").css("display", "block");
                        $("#newCron").css("display", "none");
                        $("#cronContent").css("display", "none");
                        $("#editCron-div").css("display", "none");
                        $("#deleteCron-div").css("display", "none");

                        sweetAlert({html: false, title: "¡Excelente!", text: "La plantilla se ha eliminado correctamente.", type: "success"});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    });
}
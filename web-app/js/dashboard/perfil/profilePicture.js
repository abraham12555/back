var allowedSize = $.allowedSize;
var allowedContentTypes = $.allowedContentTypes;

$.noConflict();
jQuery(document).ready(function ($) {
    $('.image-editor').cropit();

    $('#changePicture-btn').on('click', function (event) {
        event.preventDefault();

        $('p[class*="help-block"]').each(function () {
            $(this).remove();
        });

        var errors = 0;
        var imageData;
        var type;

        if (!existeFile()) {
            errors++;
            errorProfileMessage($('#profileFile'), "El campo es obligatorio");
        } else if (!validType(allowedContentTypes)) {
            errors++;
            errorProfileMessage($('#profileFile'), "El tipo del archivo es inválido");
        } else {
            type = getType();
            imageData = $('.image-editor').cropit('export', {
                type: type,
                quality: 0.9,
                originalSize: true,
                fillBg: '#fff'
            });

            if (!validaTamano(allowedSize, imageData)) {
                errors++;
                errorProfileMessage($('#profileFile'), "El tamaño de la imagen excede al total permitido: " + (allowedSize / 1024 / 1024) + " MB");
            }
        }

        if (errors === 0) {
            $('.hidden-image-data').val(imageData);
            setName();
            $("#typeFile").val(type);

            var form = $("#updateProfilePicture-form");
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
                        sweetAlert("Oops...", response.message, "error");
                    } else {
                        $("#profilePicture").attr("src", imageData);
                        $("#bannerProfilePicture").attr("src", imageData);
                        $("#deleteProfilePicture-btn").css("display", "block");
                        resetProfilePictureForm();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    });

    var existeFile = function () {
        var existeFile = false;
        var file = $('#profileFile')[0].files;
        var length = file.length;
        if (length > 0) {
            existeFile = true;
        }
        return existeFile;
    };

    var validType = function (allowedContentTypes) {
        var contentTypes = new Array();
        contentTypes = allowedContentTypes.split(",");
        var valid = false;
        var filename = $("#profileFile").val();
        if (filename !== "") {
            var mime = $('#profileFile')[0].files[0].type;
            if (mime !== "") {
                if ($.inArray(mime, contentTypes) !== -1) {
                    valid = true;
                }
            }
        }
        return valid;
    };

    var validaTamano = function (allowedSize, imageData) {
        allowedSize = Number(allowedSize);
        var size = Math.ceil(imageData.length * 3 / 4);
        return (size < allowedSize);
    };
});

function setName() {
    var filename = $("#profileFile").val();
    if (filename !== "") {
        var name = $('#profileFile')[0].files[0].name;
        if (name !== "") {
            $("#nameFile").val(name);
        }
    }
}

function getType() {
    var type = $('#profileFile')[0].files[0].type;
    return type;
}

function resetProfilePictureForm() {
    $('p[class*="help-block"]').each(function () {
        $(this).remove();
    });
    $("#updateProfilePicture-form")[0].reset();
    $(".cropit-preview-image").attr("src", "");
    $("input[type=range]").val(0);
    cerrarModal('updateProfilePicture');
}

function deleteProfilePicture() {
    swal({
        title: "¡Importante!",
        text: "¿Está seguro que desea eliminar su foto de perfil?",
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
                url: $.deleteProfilePicture,
                success: function (response) {
                    if (response.error) {
                        sweetAlert("Oops...", response.mensaje, "error");
                    } else {
                        $("#bannerProfilePicture").attr("src", $.defaultProfilePicture);
                        $("#profilePicture").attr("src", $.defaultProfilePicture);
                        $("#deleteProfilePicture-btn").css("display", "none");
                        sweetAlert({html: false, title: "¡Excelente!", text: "Su foto de perfil se ha eliminado correctamente.", type: "success"});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
                }
            });
        }
    });
}
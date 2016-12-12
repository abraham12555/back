$(document).ready(function () {
    var opcion = $('#opcionMenu').val();
    $('.elementoMenuPrincipal').removeClass('blueButton');
    $('#principalOpc' + opcion).addClass('blueButton');
    inicializarDropzone('div#divDropzone', '#subirLogo');
    $("#colorBase").spectrum({
        color: "#298df5",
        showButtons: false,
        clickoutFiresChange: true
    });
    $("#colorFondo").spectrum({
        color: "#F1F3FA",
        showButtons: false,
        clickoutFiresChange: true,
        change: function (color) {
            $("body").css("background-color", color.toHexString()); // #ff0000
        }
    });
    $("#colorHighlight").spectrum({
        color: "#33c56e",
        showButtons: false,
        clickoutFiresChange: true,
        change: function (color) {
            $(".hoverBtn:hover").css("background-color", color.toHexString());
            $(".hoverBtn.gray2:hover").css("background-color", color.toHexString());
        }
    });
    $("#colorTexto").spectrum({
        color: "#252d60",
        showButtons: false,
        clickoutFiresChange: true
    });
    $("#colorErrores").spectrum({
        color: "#fb5e48",
        showButtons: false,
        clickoutFiresChange: true,
        change: function (color) {
            $(".notificationBox").css("background-color", color.toHexString());
        }
    });
});

window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

function mostrarOpciones() {
    $('#opcionesUsuario').toggleClass("show");
}

function consultarSolicitud(numeroDeSolicitud) {
    window.location.href = "/kosmos-app/dashboard/detalleSolicitud/" + numeroDeSolicitud;
}

function realizarVerificacion(numeroDeSolicitud) {
    window.location.href = "/kosmos-app/dashboard/detalleVerificacion/" + numeroDeSolicitud;
}

function mostrarTab(tab) {
    $('.solicitudTab').fadeOut();
    $('.opcionMenuSolicitud').removeClass('blueButton');
    $('.opcionMenuSolicitud').addClass('gray');
    $('#' + tab + 'Button').addClass('blueButton');
    $('#' + tab + 'Button').removeClass('gray');
    $('#' + tab).fadeIn();
}

function mostrarApartado(claseBoton, claseDiv, apartado) {
    $('.' + claseDiv + '').fadeOut();
    $('.' + claseBoton + '').removeClass('lightGrayBG');
    $('#' + apartado + 'Button').addClass('lightGrayBG');
    $('#' + apartado).fadeIn();
    if (claseBoton === "opcConfiguracion") {
        $('.configuracionSubMenu').fadeOut();
        $('#' + apartado + 'SubMenu').fadeIn();
    }
}

function iniciarVisita() {
    $('#iniciarVisitaBtn').hide();
    $('#verificacionFormulario').fadeIn();
}

function verificarDatos(campo, respuesta) {
    $('#' + campo + 'Verificar .checkVerificacion').removeClass('colorGreen');
    $('#' + campo + 'Verificar .checkVerificacion').addClass('whiteBox');
    $('#' + campo + 'Verificar .checkVerificacion a').removeClass('colorWhite');
    $('#' + campo + respuesta).removeClass('whiteBox');
    $('#' + campo + respuesta).removeClass('gray');
    $('#' + campo + respuesta).addClass('colorGreen');
    $('#' + campo + respuesta + ' a').addClass('colorWhite');
    if (respuesta === "No") {
        $('#' + campo).removeAttr('disabled');
        $('#' + campo).focus();
        $('#' + campo).addClass('headingColor');
    } else if (respuesta === "Si") {
        $('#' + campo).attr('disabled', true);
        $('#' + campo).removeClass('headingColor');
    }
}

function listarSolicitudesPor(criterio) {
    seleccionarTemporalidad(criterio);
    if (criterio !== 5) {
        consultarSolicitudesPorTiempo(criterio, "dictaminadas", null, null);
        consultarSolicitudesPorTiempo(criterio, "noDictaminadas", null, null);
    }

}

function consultarSolicitudesPorTiempo(temporalidad, idDiv, fechaInicio, fechaFinal) {
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'temporalidad=' + temporalidad + "&template=" + idDiv + complemento,
        url: '/kosmos-app/dashboard/consultarSolicitudes',
        success: function (data, textStatus) {
            $('#' + idDiv).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function genererarEstadisticasPor(criterio) {
    seleccionarTemporalidad(criterio);
}

function seleccionarTemporalidad(opcion) {
    $('.elementoSubMenu').removeClass('blueButton');
    $('.elementoSubMenu').addClass('gray');
    $('#subMenuOpc' + opcion).addClass('blueButton');
    $('#subMenuOpc' + opcion).removeClass('gray');
}

function mostrarModal(idModal) {
    $('#' + idModal).fadeIn();
}

function cerrarModal(idModal) {
    $('#' + idModal).fadeOut();
}

function cambiarEstatus(estatus, idSolicitud) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idSolicitud + "&status=" + estatus,
        url: '/kosmos-app/dashboard/cambiarEstadoSolicitud',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                sweetAlert("Actualización Correcta", respuesta.mensaje, "success");
                if (respuesta.bloquearOpciones) {
                    $('#opcionesScore').html("");
                }
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
                $('#loginAutorizacion').fadeOut();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function guardarNuevaEntidad() {
    jQuery.ajax({
        type: 'POST',
        data: $('#crearEntidadForm').serialize(),
        url: '/kosmos-app/entidadFinanciera/save',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var html = "";
                html += "<section class='container marginBottom20'>";
                html += "<div class='width990 solicitudBox autoMargin radius2'>";
                html += "<div class='clearFix'>";
                html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'>NOMBRE</p>";
                html += "<p class='font14 gray2'>" + respuesta.entidad.nombre + "</p>";
                html += "</div></div>";
                html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'>FECHA DE REGISTRO</p>";
                html += "<p class='font14 gray2'>" + $.format.date(respuesta.entidad.fechaDeRegistro, "dd/MM/yyyy HH:mm") + "</p>";
                html += "</div></div>";
                html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'>ESTATUS</p>";
                html += "<p class='font14 gray2'>" + (respuesta.entidad.activa === true ? "ACTIVA" : "INACTIVA") + "</p>";
                html += "</div></div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='editar' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10'>EDITAR</a>";
                html += "</div></div></div></div>";
                html += "</section>";
                $('#listaDeEntidades').append(html);
                cerrarModal('nuevaEntidad');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Ocurrio un error grave. Intentelo nuevamente más tarde.", "error");
        }
    });
}

Dropzone.autoDiscover = false;

function inicializarDropzone(elemento, boton) {
    //Dropzone.autoDiscover = false;
    var kosmosDropzone = new Dropzone(elemento, {
        url: "/kosmos-app/dashboard/subirImagen",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        params: {'imgType': $('#tipoDeDocumento').val()},
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: ".png, .jpg, .jpeg",
        autoQueue: true,
        createImageThumbnails: false,
        clickable: boton
    });
    kosmosDropzone.on("addedfile", function (file) {
        console.log("Archivo enviado: " + file);
        $('.dz-preview').hide();
    });
    kosmosDropzone.on("success", function (file, response) {
        var respuesta = eval(response);
        console.log("Respuesta recibida: " + respuesta);
        if (respuesta.exito) {
            sweetAlert("¡Excelente!", respuesta.mensaje, "success");
        } else {
            sweetAlert("Oops...", respuesta.mensaje, "error");
        }
        this.removeAllFiles();
    });
    kosmosDropzone.on("error", function (file, response) {
        console.log(response);
        sweetAlert("Oops...", "Ocurrio un problema al consultar los datos del documento", "error");
    });
}

function openModal(divModal) {
    $('#' + divModal).fadeIn();
}

function closeModal(divModal) {
    console.log("Cerrando modal");
    $('#' + divModal).fadeOut();
}

function mostrarDetalleProducto(idProducto) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idProducto,
        url: '/kosmos-app/producto/obtenerDetalleProducto',
        success: function (data, textStatus) {
        $('#detalleProducto').html(data);
        openModal('modalDetalleProducto');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
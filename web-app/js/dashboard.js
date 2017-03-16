var solicitudes;
var kosmosDropzone;
var pasoActual = 1;
function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
}
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
}

$(document).ready(function () {
    var opcion = $('#opcionMenu').val();
    $('.elementoMenuPrincipal').removeClass('blueButton');
    $('#principalOpc' + opcion).addClass('blueButton');
    console.log("Dropzone? -> " + document.getElementById('divDropzone'));
    if (document.getElementById('divDropzone') !== null && document.getElementById('subirLogo') !== null) {
        console.log("Si existe el div para Dropzone");
        inicializarDropzone('div#divDropzone', '#subirLogo');
    } else if (opcion === '4' && $('#solicitudId').val()) {
        console.log("Si existe el div para Dropzone");
        inicializarDropzone(document.body, '.cameraBox');
    }
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

    $('.cameraBox').click(function () {
        $('#tipoDeDocumento').val($(this).data('tipoDeImagen'));
        if (kosmosDropzone !== null && kosmosDropzone !== undefined) {
            kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': $('#opcionMenu').val(), 'solicitudId': $('#solicitudId').val()};
        }
    });

    $('.pregunta').change(function () {
        if ($(this).val() !== '') {
            $(this).addClass('filled');
            $(this).addClass('headingColor');
        } else {
            $(this).removeClass('filled');
            $(this).removeClass('headingColor');
        }
        validarPasoCompletado();
    });

    $('#btnEnviarEncuesta').click(function () {
        pasoActual++;
        $('#paso' + pasoActual + 'Verificacion').fadeIn();
        $("html, body").animate({
            scrollTop: $("html, body").get(0).scrollHeight
        }, 1500);
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
    window.location.href = "/dashboard/detalleSolicitud/" + numeroDeSolicitud;
}

function realizarVerificacion(numeroDeSolicitud) {
    window.location.href = "/dashboard/detalleVerificacion/" + numeroDeSolicitud;
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
    $("html, body").animate({
        scrollTop: $("html, body").get(0).scrollHeight
    }, 1500);
}

function verificarDatos(campo, respuesta) {
    $('#' + campo + 'Verificar').addClass('filled');
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
    if ($('#resultadoVerificacionDireccion').val() === "" || $('#resultadoVerificacionDireccion').val() === undefined) {
        $('#resultadoVerificacionDireccion').val(respuesta);
    } else if ($('#resultadoVerificacionDireccion').val() === "Si" && respuesta === "No") {
        $('#resultadoVerificacionDireccion').val(respuesta);
    }
    validarPasoCompletado();
}

function confirmarAccion(boton, respuesta) {
    if ($('#' + boton + respuesta).hasClass('whiteBox')) {
        $('#' + boton + ' .checkVerificacion').removeClass('colorGreen');
        $('#' + boton + ' .checkVerificacion').addClass('whiteBox');
        $('#' + boton + ' .checkVerificacion a').removeClass('colorWhite');
        $('#' + boton + respuesta).removeClass('whiteBox');
        $('#' + boton + respuesta).removeClass('gray');
        $('#' + boton + respuesta).addClass('colorGreen');
        $('#' + boton + respuesta).addClass('filled');
        $('#' + boton + respuesta + ' a').addClass('colorWhite');
        $('#' + boton + 'Resultado').val(respuesta);
    } else {
        $('#' + boton + respuesta).addClass('whiteBox');
        $('#' + boton + ' .checkVerificacion').removeClass('colorGreen');
        $('#' + boton + ' .checkVerificacion').addClass('whiteBox');
        $('#' + boton + ' .checkVerificacion a').removeClass('colorWhite');
        $('#' + boton + respuesta).addClass('gray');
        $('#' + boton + respuesta).removeClass('colorGreen');
        $('#' + boton + respuesta).removeClass('filled');
        $('#' + boton + respuesta + ' a').removeClass('colorWhite');
        $('#' + boton + 'Resultado').val('');
    }
    if (boton === "documento") {
        var cantidad = $('#modalComplemento .colorGreen').length;
        if (cantidad === 0) {
            $('#btnComplemento').removeClass('blueButton');
            $('#btnComplemento').attr('disabled', true);
        } else if (cantidad >= 1) {
            if (!$('#btnComplemento').hasClass('blueButton')) {
                $('#btnComplemento').addClass('blueButton');
                $('#btnComplemento').attr('disabled', false);
            }
        }
    }
    validarPasoCompletado();
}

function listarSolicitudesPor(criterio) {
    seleccionarTemporalidad(criterio);
    if (criterio !== 5 && criterio !== 0) {
        $('#rangoDeFechas').fadeOut();
        consultarSolicitudesPorTiempo(criterio, "dictaminadas", null, null);
        consultarSolicitudesPorTiempo(criterio, "noDictaminadas", null, null);
        consultarSolicitudesPorTiempo(criterio, "complementoSolicitado", null, null);
    } else if (criterio === 5) {
        $('#rangoDeFechas').fadeIn();
        habilitarDatepicker();
    } else if (criterio === 0) {
        if ($('#from').val() && $('#to').val()) {
            consultarSolicitudesPorTiempo(5, "dictaminadas", $('#from').val(), $('#to').val());
            consultarSolicitudesPorTiempo(5, "noDictaminadas", $('#from').val(), $('#to').val());
            consultarSolicitudesPorTiempo(5, "complementoSolicitado", $('#from').val(), $('#to').val());
        } else {
            sweetAlert("¡Espera!", "¡Debes colocar ambas fechas antes de realizar la consulta!", "warning");
        }
    }
}

function habilitarDatepicker() {
    $(function () {
        var dateFormat = "dd/mm/yy",
                from = $("#from")
                .datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 1
                })
                .on("change", function () {
                    to.datepicker("option", "minDate", getDate(this));
                }),
                to = $("#to").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1
        })
                .on("change", function () {
                    from.datepicker("option", "maxDate", getDate(this));
                });

        function getDate(element) {
            var date;
            try {
                date = $.datepicker.parseDate(dateFormat, element.value);
            } catch (error) {
                date = null;
            }

            return date;
        }
    });
}

function consultarSolicitudesPorTiempo(temporalidad, idDiv, fechaInicio, fechaFinal) {
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    jQuery.ajax({
        type: 'POST',
        data: 'temporalidad=' + temporalidad + "&template=" + idDiv + complemento,
        url: '/dashboard/consultarSolicitudes',
        success: function (data, textStatus) {
            $('#' + idDiv).html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function genererarEstadisticasPor(criterio) {
    seleccionarTemporalidad(criterio);
    if (criterio !== 5 && criterio !== 0) {
        cargarGraficas(criterio, null, null);
    } else if (criterio === 5) {
        $('#rangoDeFechas').fadeIn();
        habilitarDatepicker();
    } else if (criterio === 0) {
        if ($('#from').val() && $('#to').val()) {
            cargarGraficas(criterio, $('#from').val(), $('#to').val());
        } else {
            sweetAlert("¡Espera!", "¡Debes colocar ambas fechas antes de realizar la consulta!", "warning");
        }
    }
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
    if (estatus === 8 && ($('#cantidadDePreguntas').val() === undefined || Number($('#cantidadDePreguntas').val()) === 0)) {
        sweetAlert("!Atención¡", "No has agregado ninguna pregunta, es necesario contar con al menos una pregunta para registrar la visita ocular.", "warning");
    } else {
        var complemento;
        if (estatus === 6) {
            complemento = [];
            $('#modalComplemento .colorGreen').each(function (index) {
                complemento.push($(this).data("idDocumento"));
            });
        }
        jQuery.ajax({
            type: 'POST',
            data: {id: idSolicitud, status: estatus, complemento: complemento},
            url: '/dashboard/cambiarEstadoSolicitud',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                if (respuesta.ok) {
                    sweetAlert("Actualización Correcta", respuesta.mensaje, "success");
                    if (respuesta.bloquearOpciones) {
                        $('#opcionesScore').html("");
                    } else if (estatus === 8) {
                        $('#solicitarVisita').replaceWith("");
                        $('#modalPreguntas').replaceWith("");
                    }
                } else {
                    sweetAlert("Oops...", respuesta.mensaje, "error");
                    $('#loginAutorizacion').fadeOut();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }
}

function actualizarConfiguracionBuroCredito() {
    jQuery.ajax({
        type: 'POST',
        data: $('#configuracionBuroCreditoForm').serialize(),
        url: '/configuracionBuroCredito/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
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


function guardarNuevaEntidad() {
    jQuery.ajax({
        type: 'POST',
        data: $('#crearEntidadForm').serialize(),
        url: '/entidadFinanciera/save',
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
    kosmosDropzone = new Dropzone(elemento, {
        url: "/dashboard/subirImagen",
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
            getBase64(file, respuesta.tipoDeFotografia, respuesta.descripcion);
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
        url: '/producto/obtenerDetalleProducto',
        success: function (data, textStatus) {
            $('#detalleProducto').html(data);
            openModal('modalDetalleProducto');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function registrarSolicitudes(listaDeSolicitudes) {
    solicitudes = JSON.parse(listaDeSolicitudes);
    console.log("Solicitudes: " + solicitudes);
}

function cargarDireccioneEnMapa() {
    if (mapaGenerado() === false) {
        initMap();
        for (i = 0; i < solicitudes.length; i++) {
            agregarMarcador(solicitudes[i].coordenadas, solicitudes[i].direccion, solicitudes[i].folio, solicitudes[i].cliente)
        }
    } else {
        console.log("El mapa ya está inicializado");
    }
}

function getBase64(file, tipoDeFotografia, descripcion) {
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        var html = '<div class="cameraBox center width170 radius2 boxMargins">';
        html += '<a href="' + reader.result + '" rel="prettyPhoto[pp_gal]"><img src="' + reader.result + '" width="88" height="88" alt="' + descripcion + '" /></a></div>';
        $('#' + tipoDeFotografia + "Div").html(html);
        $('#' + tipoDeFotografia + "Div").addClass('filled');
        $("a[rel^='prettyPhoto[pp_gal]']").prettyPhoto();
        validarPasoCompletado();
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };
}

function validarPasoCompletado() {
    var elementosLlenosVisibles = $('.datoRequerido:visible').length;
    var totalElementosVisibles = $('.filled:visible').length;
    if (elementosLlenosVisibles === totalElementosVisibles) {
        pasoActual++;
        $('#paso' + pasoActual + 'Verificacion').fadeIn();
        $("html, body").animate({
            scrollTop: $("html, body").get(0).scrollHeight
        }, 1500);
    }
}

function agregarPregunta() {
    var textoPregunta = $('#textoPregunta').val();
    $.ajax({
        type: 'POST',
        data: 'pregunta=' + textoPregunta,
        url: '/dashboard/agregarPregunta',
        success: function (data, textStatus) {
            mostrarPreguntas(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function eliminarPregunta(idPregunta) {
    $.ajax({
        type: 'POST',
        data: 'idPregunta=' + idPregunta,
        url: '/dashboard/eliminarPregunta',
        success: function (data, textStatus) {
            mostrarPreguntas(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function mostrarPreguntas(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDePreguntas').val(resultado.length);
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + " - " + resultado[x].texto + "</td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarPregunta(" + resultado[x].id + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        console.log(html);
        $('#preguntasAgregadas tbody').html(html);
        $('#btnVerificar').addClass('blueButton');
        $('#btnVerificar').prop("disabled", false);
        $('#textoPregunta').val('');
    } else {
        html += "<td colspan='2' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#preguntasAgregadas tbody').html(html);
    }
}

function iniciarPrettyPhoto() {
    $("a[rel^='prettyPhoto[pp_gal]']").prettyPhoto();
}

var solicitudes;
var kosmosDropzone;
var pasoActual = 1;
$.validUsername = "/dashboard/validUsername";
$.validEmail = "/dashboard/validEmail";
$.getProfilePicture = "/dashboard/profilePicture";

function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
}
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
}

$(document).ready(function () {
    console.log($('#pagina').val());
    /*if ($('#pagina').val() === 'index') {
        $.getSolicitudes = "/dashboard/getSolicitudes";
        var idPaginacion = "paginationSolicitudes";
        $('#' + idPaginacion).on('click', 'a.page', function (event) {
            event.preventDefault();
            var page = $(this).data('page');
            getSolicitudes(page, idPaginacion);
        });
        getSolicitudes(1, idPaginacion);
    } else {
        var idPaginacion = 'paginationSolicitudesBusqueda';
        var totalPages = $('#totalPages').val();
        var page = $('#page').val();
        $("#currentPageSolicitudesBusqueda").val(page);
        pagination(totalPages, page, idPaginacion);
    }

    $(function () {
        $("#accordion").accordion({
            header: "h1",
            collapsible: true
        });
    });*/

    var opcion = $('#opcionMenu').val();
    $('.elementoMenuPrincipal').removeClass('blueButton');
    $('#principalOpc' + opcion).addClass('blueButton');
    console.log("Dropzone? -> " + document.getElementById('divDropzone'));
    if (document.getElementById('divDropzone') !== null && document.getElementById('subirLogo') !== null) {
        console.log("Si existe el div para Dropzone en Perfil de Marca");
    } else if (opcion === '4' && $('#solicitudId').val()) {
        console.log("Si existe el div para Dropzone ");
        inicializarDropzone(document.body, '.cameraBox');
    }

    jQuery.ajax({
        type: 'POST',
        url: '/dashboard/consultaConfiguracion',
        success: function (data, textStatus) {

            $("#colorBordeSuperior").spectrum({
                preferredFormat: "hex3",
                color: data.colorBordeSuperior,
                showButtons: false,
                clickoutFiresChange: true
            });

            $("#colorEncabezado").spectrum({
                preferredFormat: "hex3",
                color: data.colorEncabezado,
                showButtons: false,
                clickoutFiresChange: true
            });

            $("#colorFondo").spectrum({
                preferredFormat: "hex3",
                color: data.colorFondo,
                showButtons: false,
                clickoutFiresChange: true
            });

            $("#colorGradienteInferior").spectrum({
                preferredFormat: "hex3",
                color: data.colorGradienteInferior,
                showButtons: false,
                clickoutFiresChange: true
            });

            $("#colorGradienteSuperior").spectrum({
                preferredFormat: "hex3",
                color: data.colorGradienteSuperior,
                showButtons: false,
                clickoutFiresChange: true
            });

            $("#colorTitulos").spectrum({
                preferredFormat: "hex3",
                color: data.colorTitulos,
                showButtons: false,
                clickoutFiresChange: true
            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

    $('.cameraBox').click(function () {
        $('#tipoDeDocumento').val($(this).data('tipoDeImagen'));
        if (kosmosDropzone !== null && kosmosDropzone !== undefined) {
            kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': $('#opcionMenu').val(), 'solicitudId': $('#solicitudId').val()};
        }
    });

    $('.buscarBtn').click(function () {
        $('#tipoDeDocumento').val('imagen');
        inicializarDropzone('div#divDropzone', '#subirLogo');
        kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': 10, 'solicitudId': $('#solicitudId').val()};

    });

    $('.landingBtn').click(function () {
        $('#tipoDeDocumento').val('imagen');
        inicializarDropzone('div#divDropzone', '#subirLanding');
        kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': 11, 'solicitudId': $('#solicitudId').val()};

    });
    $('.cssBtn').click(function () {
        $('#tipoDeDocumento').val('imagen');
        inicializarDropzone('div#divDropzone', '#subirCss');
        kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': 12, 'solicitudId': $('#solicitudId').val()};

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

    $(".regular").slick({
        dots: true,
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3
    });

    $(function () {
        $('#terminosYCondiciones').froalaEditor({
            height: 300,
            language: 'es'
        })
    });

    $(".js-example-basic-multiple").select2({
        allowClear: true
    });

    $('#btnEnviarEncuesta').click(function () {
        pasoActual++;
        $('#paso' + pasoActual + 'Verificacion').fadeIn();
        $("html, body").animate({
            scrollTop: $("html, body").get(0).scrollHeight
        }, 1500);
    });

    $('.crosCircle').click(function () {
        $(this).parent().fadeOut();
    });

    getProfilePicture();
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
function habilitarGuia(idDiv) {

    if (idDiv === "modalDocumentos") {
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            duration: 5000,
            steps: [
                {
                    element: "#tercerPaso",
                    title: "Sección Documentos",
                    content: "Selecciona los documentos que quieras vincular al rubro.",
                    placement: "bottom",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                }

            ]});
    } else if (idDiv === "modalProductos") {
        document.location.href = "#cuartoPaso";
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            duration: 5000,
            steps: [
                {
                    element: "#cuartoPaso",
                    title: "Sección Productos",
                    content: "Llena el formulario con los datos del producto que quieras vincular al rubro.",
                    placement: "bottom",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                }

            ]});
    } else if (idDiv === "modalVistas") {
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            duration: 5000,
            steps: [
                {
                    element: "#septimoPaso",
                    title: "Sección Vistas",
                    content: "Selecciona de la lista las vistas que quieras vincular al rubro",
                    placement: "bottom",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                }

            ]});
    } else if (idDiv === "modalPlazos") {
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            duration: 5000,
            steps: [
                {
                    element: "#quintoPaso",
                    title: "Sección Plazos",
                    content: "LLena los datos del plazo que quieras vincular al producto",
                    placement: "bottom",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                }

            ]});
    } else if (idDiv === "modalGarantias") {
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            duration: 5000,
            steps: [
                {
                    element: "#sextoPaso",
                    title: "Sección Garantías",
                    content: "Llena los datos de la Garantía que quieras vincular al producto",
                    placement: "bottom",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                }

            ]});
    } else
    {
        var tour = new Tour({
            smartPlacement: true,
            storage: false,
            keyboard: true,
            backdrop: false,
            //duration:5000,
            steps: [
                {
                    element: "#primerPaso",
                    title: "Cotizador Configuración ",
                    content: "En esta seccion podras configurar los productos y rubros que estarán disponibles en el cotizador.",
                    placement: "bottom",
                    onNext: function () {
                        mostrarModal('modalRubros');
                    },
                    onShown: function () {
                        $("[data-role='prev'],[data-role='pause-resume']").hide();
                    }

                },
                {
                    element: "#segundoPaso",
                    title: "Agrega los rubros que necesites",
                    content: "Llena el Formulario con los datos de tu preferencia !",
                    placement: "right",
                    animation: true,
                    onShown: function () {
                        $("[data-role='next'],[data-role='prev'],[data-role='pause-resume']").hide();
                    }
                }
            ]});
    }


    if (tour.ended()) {
        tour.restart();
    } else {
        tour.init();
        tour.start();
    }
}

function mostrarOpciones() {
    $('#opcionesUsuario').toggleClass("show");
}

function consultarSolicitud(numeroDeSolicitud, temporal) {
    if (temporal === false) {
        window.location.href = "/dashboard/detalleSolicitud/" + numeroDeSolicitud;
    } else {
        window.location.href = "/dashboard/detalleSolicitud?id=" + numeroDeSolicitud + "&temporal=true";
    }
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

function seleccionarIcono(icono, idDiv) {
    $('#' + idDiv).val('fa ' + icono);
    closeModal('modalDetalleIconos');
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

function mostrarCampo(claseDiv, decision) {

    $('.' + claseDiv + '').fadeIn();
    if (decision === "ocultar") {
        $('.plazoMaximo').fadeIn();
        $('.plazoMinimo').fadeIn();
        $('.plazosPermitidos').fadeOut();
    }

}
function ocultarCampo(claseDiv, decision) {
    $('.' + claseDiv + '').fadeOut();
    if (decision === "mostrar") {
        $('.plazosPermitidos').fadeIn();
        $('.plazoMaximo').fadeOut();
        $('.plazoMinimo').fadeOut();
    }

}
function mostrarDetalle(elemento, idRubroTemp, idDiv, idProductoTemp, idBoton) {

    if ($('#' + idBoton).hasClass('solicitudWhiteBox')) {
        $('#').removeClass('whiteBox');
        $('#').removeClass('whiteBox');
        $('#').removeClass('whiteBox');
        $('#').removeClass('whiteBox');

        $('#' + idBoton).addClass('blueButton');

    }
    if (elemento === "rubros") {
        $.ajax({
            type: 'POST',
            data: 'idRubroTemporal=' + idRubroTemp,
            url: '/dashboard/buscarRubros',
            success: function (data, textStatus) {
                var resultado = eval(data);
                var html = ""
                $('#' + idDiv).html("");
                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>POSICIÓN</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].posicion + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>DESCRIPCIÓN </p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].descripcion + "</p>";
                        html += "</div>";
                        html += "</div> ";
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>ICONO </p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'> <i class= \"" + resultado[x].claseIconoPaso + "\"></i></p>";
                        html += "</div>";
                        html += "</div> ";


                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>TOOLTIP </p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].tooltip + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>TEXTO TOOLTIP </p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].textoTooltip + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>ACTIVO </p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].activo + "</p>";
                        html += "</div>";
                        html += "</div> ";

                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);

                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY RUBROS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    } else if (elemento === "vistas") {
        $.ajax({
            type: 'POST',
            data: 'idRubroTemporal=' + idRubroTemp,
            url: '/dashboard/buscarVistas',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                var html = ""
                var resultado = eval(respuesta.sessionvistaVistas);
                $('#' + idDiv).html("");
                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>VISTAS</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].nombreDePaso + "</p>";
                        html += "</div>";
                        html += "</div> ";
                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY VISTAS ASOCIADAS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    } else if (elemento === "documentos") {
        $.ajax({
            type: 'POST',
            data: 'idRubro=' + idRubroTemp,
            url: '/dashboard/buscarDocumentos',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                var html = ""
                var resultado = eval(respuesta.sessionPrueba);
                $('#' + idDiv).html("");

                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>DOCUMENTOS</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].nombreDeDocumento + "</p>";
                        html += "</div>";
                        html += "</div> ";
                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);

                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY DOCUMENTOS ASOCIADOS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    } else if (elemento === "productos") {
        $.ajax({
            type: 'POST',
            data: 'idRubroTemporal=' + idRubroTemp,
            url: '/dashboard/buscarProducto',
            success: function (data, textStatus) {
                console.log(data);
                var respuesta = eval(data);
                var html = ""
                var resultado = eval(respuesta.sessionProductosVista);
                $('#' + idDiv).html("");

                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class='marginTop10 blueButton radius2 clearFix'>";
                        html += "<p class='font18 fontWeight600  colorWhite paddingTop15 paddingBottom10 floatLeft'>NOMBRE DEL PRODUCTO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight600 colorWhite paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].nombreDelProducto + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>MONTO MÁXIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].montoMaximo + "</p>";
                        html += "</div>";
                        html += "</div> ";


                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>MONTO MÍNIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].montoMinimo + "</p>";
                        html += "</div>";
                        html += "</div> ";


                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>TIPO DE INGRESOS</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>";
                        for (var y = 0; y < resultado[x].nombreDelIngreso.length; y++) {
                            html += "" + resultado[x].nombreDelIngreso[y] + "";
                            html += "<br>";
                        }
                        html += "</p>";
                        html += "</div>";
                        html += "</div> ";



                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>VER PLAZOS DEL PRODUCTO</p>";
                        html += "<div class='floatRight marginRight20'>";
                        html += "<button type='button' onclick='mostrarDetalle(" + '"plazos"' + "," + resultado[x].idRubroTemporal + ",\"idVistaPrevia" + resultado[x].idRubroTemporal + "\"," + resultado[x].id + ");' class='loginButton letterspacing2 font14 pointer blueButton' style='width: 150%;height: 33px;margin-bottom: 15px;'>Ver</button>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>VER GARANTÍAS DEL PRODUCTO</p>";
                        html += "<div class='floatRight marginRight20'>";
                        html += "<button type='button' onclick='mostrarDetalle(" + '"garantias"' + "," + resultado[x].idRubroTemporal + ",\"idVistaPrevia" + resultado[x].idRubroTemporal + "\"," + resultado[x].id + ");' class='loginButton letterspacing2 font14 pointer blueButton' style='width: 150%;height: 33px;margin-bottom: 15px;'>Ver</button>";
                        html += "</div>";
                        html += "</div> ";

                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);

                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY PRODUCTOS ASOCIADOS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    } else if (elemento === "plazos") {
        $.ajax({
            type: 'POST',
            data: {idRubroTemp: idRubroTemp, idProductoTemp: idProductoTemp},
            url: '/dashboard/buscarPlazo',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                var html = ""
                var resultado = eval(respuesta.sessionPrueba2);
                var resultado2 = eval(respuesta.sessionPrueba);
                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                html += "<div class='marginTop10 blueButton radius2 clearFix'>";
                html += "<p class='font18 fontWeight600  colorWhite paddingTop15 paddingBottom10 floatLeft'>NOMBRE DEL PRODUCTO</p>";
                html += "<div class='floatRight marginRight35'>";
                html += "<p class='font17 fontWeight600 colorWhite paddingTop15 paddingBottom10 floatLeft'>" + resultado2[0].nombreDelProducto + "</p>";
                html += "</div>";
                html += "</div> ";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class=''>";
                        html += "<div class='colorGreen autoMargin marginTop12 radius4'>";
                        html += "<p class='colorWhite fontWeighht700 font25 center paddingTop15 paddingBottom15'>PLAZO : " + resultado[x].id + "</p>";
                        html += "</div>";
                        html += "  </div>";
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>USAR LISTA DE PLAZOS</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].usarListaDePlazos + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>PLAZOS PERMITIDOS</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].plazosPermitidos + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>PLAZO MÁXIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].plazoMaximo + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>PLAZO MÍNIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].plazoMinimo + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>SALTO SLIDER</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].saltoSlider + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>IMPORTE MÁXIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].importeMaximo + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>IMPORTE MÍNIMO</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].importeMinimo + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>PERIODICIDAD</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].periodicidadId + "</p>";
                        html += "</div>";
                        html += "</div> ";


                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);

                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY PLAZOS ASOCIADOS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    } else if (elemento === "garantias") {
        $.ajax({
            type: 'POST',
            data: {idRubroTemp: idRubroTemp, idProductoTemp: idProductoTemp},
            url: '/dashboard/buscarGarantia',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                var html = ""
                var resultado = eval(respuesta.sessionPrueba2);
                var resultado2 = eval(respuesta.sessionPrueba);
                html += "<div class='col6 col12-mob floatLeft'>";
                html += "<div class='mobileAside10 solicitudWhiteBox radius2 paddingBottom12 marginBottom20'>";
                html += "<div class='blueButton radius2'>";
                html += "<p class='paddingLeft30 colorWhite letterspacing2 fontWeight600 font18 paddingTop15 paddingBottom10'>INFORMACIÓN</p>";
                html += "</div>";
                html += "<div class='marginTop10 blueButton radius2 clearFix'>";
                html += "<p class='font18 fontWeight600  colorWhite paddingTop15 paddingBottom10 floatLeft'>NOMBRE DEL PRODUCTO</p>";
                html += "<div class='floatRight marginRight35'>";
                html += "<p class='font17 fontWeight600 colorWhite paddingTop15 paddingBottom10 floatLeft'>" + resultado2[0].nombreDelProducto + "</p>";
                html += "</div>";
                html += "</div> ";
                if (resultado.length > 0) {
                    for (var x = 0; x < resultado.length; x++) {
                        html += "<div class=''>";
                        html += "<div class='colorGreen autoMargin marginTop12 radius4'>";
                        html += "<p class='colorWhite fontWeighht700 font25 center paddingTop15 paddingBottom15'>GARANTÍA : " + resultado[x].id + "</p>";
                        html += "</div>";
                        html += "  </div>";
                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>CANTIDAD MÁXIMA</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].cantidadMaxima + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>CANTIDAD MÍNIMA</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].cantidadMinima + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>DESCRIPCIÓN</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].descripcion + "</p>";
                        html += "</div>";
                        html += "</div> ";

                        html += "<div class='marginLeft32 clearFix'>";
                        html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>TIPO DE GARANTÍA</p>";
                        html += "<div class='floatRight marginRight35'>";
                        html += "<p class='font17 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>" + resultado[x].tipoDeGarantiaNombre + "</p>";
                        html += "</div>";
                        html += "</div> ";

                    }
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);

                } else {
                    html += "<div class='marginLeft32 clearFix'>";
                    html += "<p class='font18 fontWeight500 darkBluetitle paddingTop15 paddingBottom10 floatLeft'>NO HAY GARANTÍAS ASOCIADAS</p>";
                    html += "</div> ";
                    html += " </div>";
                    html += "</div>";
                    $('#' + idDiv).html(html);
                }
                $('#' + idDiv).fadeIn("slow");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });

    }

}
function selecciona() {
    var idTipoDeCampo = $("#idTipoDeCampo").val();
    $.ajax({
        type: 'POST',
        data: 'idTipoDeCampo=' + idTipoDeCampo,
        url: '/dashboard/obtenerTipoDeCampo',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            var html = "";
            if (respuesta.campoFormulario.length > 0) {

                for (var x = 0; x < respuesta.campoFormulario.length; x++) {
                    html += "<div id=" + respuesta.campoFormulario[x].id + " class='col12 marginRight10 paddingBottom5 paddingTop10 fullInputs pointer checkVerificacion' draggable='true'  ondragstart='drag(event);' >";
                    html += "<a  class='block font14 letterspacing1 fontWeight500 lightGray paddingTop15 paddingBottom15 center textUpper' >" + respuesta.claseAsociada[x].nombre + " / " + respuesta.campoFormulario[x].nombreDelCampo + "</a> ";
                    html += "</div>";
                    $('#elementosFormulario').html(html);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
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



function allowDrop(ev) {
    ev.preventDefault();
}
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}
function removeNode(node) {
    node.parentNode.removeChild(node);
}
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.stopPropagation();
    var idCampoFormulario = data;
    jQuery.ajax({
        data: {idCampoFormulario: idCampoFormulario},
        type: 'POST',
        url: '/producto/altaCampoFormulario',
        success: function (data, textStatus) {
            $('#altaCampoFormulario').html(data);
            openModal('modalAltaCampoFormulario');

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
    return false;
}


function dropClear(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.stopPropagation();
    var idCampoFormulario = data;
    sweetAlert({
        title: "Estas seguro de eliminar el campo?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'idCampoFormulario=' + idCampoFormulario,
                    url: '/dashboard/eliminarCampoFormulario',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.respuesta.ok) {
                            sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                            mostrarCamposAgregados(respuesta.session);
                            cerrarModal("modalAltaCampoFormulario");
                        } else {
                            sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });

    return false;
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
        consultarSolicitudesPorTiempo(criterio, "dictaminadas", null, null, "listaDeSolicitudesDictaminadas", "paginationSolicitudesDictaminadas", "temporalidadSolicitudesDictaminadas", 1);
        consultarSolicitudesPorTiempo(criterio, "noDictaminadas", null, null, "listaDeSolicitudesNoDictaminadas", "paginationSolicitudesNoDictaminadas", "temporalidadSolicitudesNoDictaminadas", 1);
        consultarSolicitudesPorTiempo(criterio, "complementoSolicitado", null, null, "listaSolicitudesConComplementoSolicitado", "paginationComplementoSolicitado", "temporalidadComplementoSolicitado", 1);
    } else if (criterio === 5) {
        $('#rangoDeFechas').fadeIn();
        habilitarDatepicker();
    } else if (criterio === 0) {
        if ($('#from').val() && $('#to').val()) {
            consultarSolicitudesPorTiempo(5, "noDictaminadas", $('#from').val(), $('#to').val(), "listaDeSolicitudesNoDictaminadas", "paginationSolicitudesNoDictaminadas", "temporalidadSolicitudesNoDictaminadas", 1);
            consultarSolicitudesPorTiempo(5, "dictaminadas", $('#from').val(), $('#to').val(), "listaDeSolicitudesDictaminadas", "paginationSolicitudesDictaminadas", "temporalidadSolicitudesDictaminadas", 1);
            consultarSolicitudesPorTiempo(5, "complementoSolicitado", $('#from').val(), $('#to').val(), "listaSolicitudesConComplementoSolicitado", "paginationComplementoSolicitado", "temporalidadComplementoSolicitado", 1);
        } else {
            sweetAlert("¡Espera!", "¡Debes colocar ambas fechas antes de realizar la consulta!", "warning");
        }
    }
}

function habilitarDatepicker() {
    $(function () {
        $.datepicker.setDefaults($.datepicker.regional["es"]);
        var dateFormat = "dd/mm/yy",
                from = $("#from")
                .datepicker({
                    changeMonth: true,
                    changeYear: true
                })
                .on("change", function () {
                    to.datepicker("option", "minDate", getDate(this));
                }),
                to = $("#to").datepicker({
            changeMonth: true,
            changeYear: true
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

function consultarSolicitudesPorTiempo(temporalidad, idDiv, fechaInicio, fechaFinal, idTabla, idPaginacion, idTemporalidad, page) {
    var complemento = "";
    if (fechaInicio && fechaFinal) {
        complemento += "&fechaInicio=" + fechaInicio + "&fechaFinal=" + fechaFinal;
    }
    fechaInicio = $('#from').val();
    fechaFinal = $('#to').val();
    var filter = new Object();
    filter.page = page;
    filter.temporalidad = temporalidad;
    filter.template = idDiv;
    filter.fechaInicio = fechaInicio;
    filter.fechaFinal = fechaFinal;
    jQuery.ajax({
        type: 'POST',
        dataType: "json",
        data: JSON.stringify(filter),
        contentType: "application/json",
        url: '/dashboard/getSolicitudesBusqueda',
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#' + idPaginacion).empty();
            if (idDiv === "noDictaminadas") {
                $("#fechaInicio").val(fechaInicio);
                $("#fechaFinal").val(fechaFinal);
            } else if (idDiv === "dictaminadas") {
                $("#fechaInicioDictaminadas").val(fechaInicio);
                $("#fechaFinalDictaminadas").val(fechaFinal);
            } else if (idDiv === "complementoSolicitado") {
                $("#fechaInicioComplementoSolicitado").val(fechaInicio);
                $("#fechaFinalComplementoSolicitado").val(fechaFinal);
            }

            if (response.solicitudes.length > 0) {
                pagination(totalPages, page, idPaginacion);
                mostrarSolicitudesFechas(response.solicitudes, idTabla);
                $("#" + idTemporalidad).val(temporalidad);
            } else {
                $('#' + idTabla + 'tbody').empty();

                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="8" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay resultados con esa busqueda</span>';
                row += '</td>';
                row += '</tr>';
                $("#" + idTabla + 'tbody').html(row);
            }
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

function genererarInformesPor(criterio) {
    seleccionarTemporalidad(criterio);
    if (criterio !== 5 && criterio !== 0) {
        $('#rangoDeFechas').fadeOut();
        cargarReportesAnaliticas(criterio, null, null);
    } else if (criterio === 5) {
        $('#rangoDeFechas').fadeIn();
        habilitarDatepicker();
    } else if (criterio === 0) {
        if ($('#from').val() && $('#to').val()) {
           cargarReportesAnaliticas(criterio, $('#from').val(), $('#to').val());
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

function mostrarModal(idModal, idRubroTemp, nombre, idProductoTemp, idDiv) {
    $('#' + idModal).fadeIn();
    $(".idRubroTemp").val(idRubroTemp);
    $(".idRubroTemp3").val(idRubroTemp);
    $(".idproductoTemp").val(idProductoTemp);
    $(".nombreRubro").val(nombre);
    if ((idModal === "modalDocumentos") && idRubroTemp && (nombre)) {
        habilitarGuia(idModal);
        var idRubroTemp = idRubroTemp;
        $.ajax({
            type: 'POST',
            data: 'idRubro=' + idRubroTemp + "&idDiv=" + idDiv,
            url: '/dashboard/buscarDocumentos',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                mostrarRubroElegido(respuesta.sessionRubro);
                mostrarDocumentos(respuesta.sessionPrueba);
                $('#idDiv').val(respuesta.sessionRubro[0].idDiv);
                //abrirTour("#cuartoPaso", "Agregar Documentos", "Selecciona los documentos requeridos de la lista y da click en Agregar", "right");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }
    if ((idModal === "modalProductos") && idRubroTemp && (nombre)) {
        habilitarGuia(idModal);

        var idRubroTemp = idRubroTemp;
        $.ajax({
            type: 'POST',
            data: 'idRubroTemporal=' + idRubroTemp + "&idDiv=" + idDiv,
            url: '/dashboard/buscarProducto',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                mostrarRubroElegido(respuesta.sessionRubro);
                mostrarProductos(respuesta.sessionProductosVista);
                $('#idDivProductos').val(respuesta.sessionRubro[0].idDiv);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }
    if ((idModal === "modalVistas") && idRubroTemp && (nombre)) {
        habilitarGuia(idModal);
        var idRubroTemp = idRubroTemp;
        $.ajax({
            type: 'POST',
            data: 'idRubroTemporal=' + idRubroTemp + "&idDiv=" + idDiv,
            url: '/dashboard/buscarVistas',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                mostrarRubroElegido(respuesta.sessionRubro);
                mostrarVistas(respuesta.sessionvistaVistas);
                $('#idDivVistas').val(respuesta.sessionRubro[0].idDiv);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }
    if ((idRubroTemp) && (nombre) && (idProductoTemp) && (idModal === "modalPlazos")) {
        habilitarGuia(idModal);
        var idRubroTemp = idRubroTemp;
        $.ajax({
            type: 'POST',
            data: {idRubroTemp: idRubroTemp, idProductoTemp: idProductoTemp},
            url: '/dashboard/buscarPlazo',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                mostrarProductoElegido(respuesta.sessionPrueba);
                mostrarPlazos(respuesta.sessionPrueba2);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }
    if ((idRubroTemp) && (nombre) && (idProductoTemp) && (idModal === "modalGarantias")) {
        habilitarGuia(idModal);
        var idRubroTemp = idRubroTemp;
        $.ajax({
            type: 'POST',
            data: {idRubroTemp: idRubroTemp, idProductoTemp: idProductoTemp},
            url: '/dashboard/buscarGarantia',
            success: function (data, textStatus) {
                var respuesta = eval(data);
                mostrarProductoElegido(respuesta.sessionPrueba);
                mostrarGarantias(respuesta.sessionPrueba2);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {}
        });
    }

}

/*function mostrarModal(idModal) {
    $('#' + idModal).fadeIn();
}*/

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

function mostrarUsuariosPaginados(data) {
    $('#listaDeUsuarios').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'>USERNAME</p>";
        html += "<p class='font14 gray2'>" + respuesta[x].username + "</p>";
        html += "</div></div>";
        html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'>NOMBRE</p>";
        html += "<p class='font14 gray2'>" + respuesta[x].fullName + "</p>";
        html += "</div></div>";
        html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'>Rol</p>";
        html += "<p class='font14 gray2'>";
        for (var y = 0; y < respuesta[x].authorities.length; y++) {
            html += "" + respuesta[x].authorities[y].authority + "";
            html += "<br>";
        }
        html += "</p>";
        html += "</div></div>";
        html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'>E-MAIL</p>";
        html += "<p class='font14 gray2'>" + respuesta[x].email + "</p>";
        html += "</div></div>";

        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='editar' onclick='editarUsuario(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>EDITAR</a>";
        html += "</div></div></div></div>";
        html += "</section>";
    }
    $('#listaDeUsuarios').html(html);

}

function mostrarSegurosSobreDeudaPaginados(data) {
    $('#listaSeguroSobreDeuda').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>SEGURO MONTO INICIAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].montoInicial + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>SEGURO MONTO FINAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].montoFinal + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>SEGURO PLAZO ANUAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].plazoAnual + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class=font12 gray2 marginBottom5'><strong>SEGURO IMPORTE</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].importeSeguro + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>PORCENTAJE</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].porcentaje + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>PORCENTAJE SEGURO</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].porcentajeSeguro + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='mostrarDetalleSeguroSobreDeuda(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += " </section>";
    }
    $('#listaSeguroSobreDeuda').html(html);

}

function mostrarServicioDeAsistenciaPaginados(data) {
    $('#listaServicioDeAsistencia').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {

        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'> ";
        html += "<p class='font12 gray2 marginBottom5'><strong> MONTO INICIAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].montoInicial + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong> MONTO FINAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].montoFinal + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>PLAZO ANUAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].plazoAnual + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong> PLAZO QUINCENAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].plazoQuincenal + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong> PLAZO SEMANAL</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].plazoSemanal + "</p>";
        html += "</div>";
        html += "</div>";

        html += "<div class='col1fifth col6 - col12 - mob floatLeft'>";
        html += "<div class='borderGrayRight marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>IMPORTE ASISTENCIA</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].importeAsistencia + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='mostrarDetalleServicioDeAsistencia(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
        html += "</div>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</section>";
    }
    $('#listaServicioDeAsistencia').html(html);

}

function mostrarProductosPaginados(data) {
    $('#listaDeProductos').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>CLAVE DEL PRODUCTO</strong></p>";
        html += "<p class='font14 gray2' style='text-align: center;margin-left: -30px;'>" + respuesta[x].claveDeProducto + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>NOMBRE</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].nombreDelProducto + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>MARCA</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].marca + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
        html += "<div class='marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>DESCRIPCION</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].descripcion + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='mostrarDetalleProducto(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "</section>";
    }
    $('#listaDeProductos').html(html);

}


function mostrarPasosCotizadorPaginados(data) {
    $('#listaPasosSolicitud').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>TÍTULO DEL PASO</strong></p>";
        html += "<p class='font14 gray2' style='text-align: center;margin-left: -30px;'>" + respuesta[x].tituloDelPaso + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>NÚMERO DE PASO</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].numeroDePaso + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>ID LISTA AJAX</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].idListaAjax + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>VALOR VARIABLE SELECCIONADA</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].variableValorSeleccionado + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='mostrarDetallePasoCotizador(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
        html += "</div>";
        html += "</div>";
        html += "</div> ";
        html += "</div> ";
        html += "</section>";
    }
    $('#listaPasosCotizador').html(html);

}

function mostrarPasosSolicitudPaginados(data) {
    $('#listaPasosSolicitud').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        html += "<section class='container marginBottom20'>";
        html += "<div class='width990 solicitudBox autoMargin radius2'>";
        html += "<div class='clearFix'>";
        html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += " <p class='font12 gray2 marginBottom5'><strong>TÍTULO DEL PASO</strong></p>";
        html += "<p class='font14 gray2' style='text-align: center;margin-left: -30px;'>" + respuesta[x].titulo + "</p>";
        html += "</div>";
        html += " </div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>NÚMERO DE PASO</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].numeroDePaso + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>PONDERACIÓN</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].ponderacion + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class=col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "<p class='font12 gray2 marginBottom5'><strong>MODO DE DESPLIEGUE</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].modoDeDespliegue + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += " <p class='font12 gray2 marginBottom5'><strong>ULTIMO PASO</strong></p>";
        html += "<p class='font14 gray2'>" + respuesta[x].ultimoPaso + "</p>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
        html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
        html += "   <p class='font12 gray2 marginBottom5'><strong>TIPO DE PASO</strong></p>";
        html += "  <p class='font14 gray2'>" + respuesta[x].tipoDePaso.nombre + "</p>";
        html += " </div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='mostrarDetallePasoSolicitud(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='configurarPasoSolicitud(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>CONFIGURAR ESTE PASO</a>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
        html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
        html += "<a title='Ver Detalle' onclick='eliminarPasoSolicitud(" + respuesta[x].id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>ElIMINAR</a>";
        html += "</div>";
        html += "</div>";

        html += "</div>";
        html += " </div>";
        html += "</section>";
    }
    $('#listaPasosSolicitud').html(html);

}

function registrarProducto() {
    var nombreDelProducto = $('[name=nombreDelProductoAlta]').val();
    var tituloEnCotizador = $('[name=tituloEnCotizadorAlta]').val();
    var claveDeProducto = $('[name=claveDeProductoAlta]').val();
    var descripcion = $('[name=descripcionProdAlta]').val();
    var claseIconoPaso = $('[name=claseIconoPasoProd]').val();
    var file_data = $("#rutaImagenDefault22").prop("files")[0];
    var montoMaximo = $('[name=montoMaximoAlta]').val();
    var montoMinimo = $('[name=montoMinimoAlta]').val();
    var tasaDeInteres = $('[name=tasaDeInteresAlta]').val();
    var cat = $('[name=catAlta]').val();

    var esquemaId = $('[name=esquemaId]').val();
    var marcaId = $('[name=marcaId]').val();
    var tipoDeProductoId = $('[name=tipoDeProductoId]').val();
    var tipoDeTasaDeInteresId = $('[name=tipoDeTasaDeInteresId]').val();

    var usoEnPerfilador = $('input:radio[name=usoEnPerfilador]:checked').val();
    var segundoCredito = $('input:radio[name=segundoCredito]:checked').val();
    var activo = $('input:radio[name=activo]:checked').val();
    var form_data = new FormData();
    form_data.append("rutaImagenDefault", file_data);
    form_data.append("nombreDelProducto", nombreDelProducto);
    form_data.append("tituloEnCotizador", tituloEnCotizador);
    form_data.append("claveDeProducto", claveDeProducto);
    form_data.append("descripcion", descripcion);
    form_data.append("claseIconoPaso", claseIconoPaso);
    form_data.append("montoMaximo", montoMaximo);
    form_data.append("montoMinimo", montoMinimo);
    form_data.append("tasaDeInteres", tasaDeInteres);
    form_data.append("cat", cat);
    form_data.append("esquemaId", esquemaId);
    form_data.append("marcaId", marcaId);
    form_data.append("tipoDeProductoId", tipoDeProductoId);
    form_data.append("tipoDeTasaDeInteresId", tipoDeTasaDeInteresId);
    form_data.append("usoEnPerfilador", usoEnPerfilador);
    form_data.append("segundoCredito", segundoCredito);
    form_data.append("activo", activo);
    jQuery.ajax({
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: form_data,
        url: '/producto/save',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = "";
                html += "<section class='container marginBottom20'>";
                html += "<div class='width990 solicitudBox autoMargin radius2'>";
                html += "<div class='clearFix'>";
                html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>CLAVE DEL PRODUCTO</strong></p>";
                html += "<p class='font14 gray2' style='text-align: center;margin-left: -30px;'>" + respuesta.producto.claveDeProducto + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>NOMBRE</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.producto.nombreDelProducto + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>MARCA</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.marca + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col6-tab col12-mob floatLeft'>";
                html += "<div class='marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>DESCRIPCION</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.producto.descripcion + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='Ver Detalle' onclick='mostrarDetalleProducto(" + respuesta.producto.id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</section>";
                $('#listaDeProductos').append(html);
                cerrarModal('modalAltaProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function registrarDocumentoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#altaDocumentoProductoForm').serialize(),
        url: '/producto/registrarDocumentoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var html = ""
                html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeDocumento.nombre + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto.obligatorio + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto.activo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarDocumentoProducto(" + respuesta.documentoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarDocumentoProducto(" + respuesta.documentoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                $('#documentoProductoAgregado tbody').append(html);
                cerrarModal('modalAltaDocumentoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function registrarPasoCotizadorEntidadFinanciera() {
    jQuery.ajax({
        type: 'POST',
        data: $('#altaPasoCotizadorEntidadFinancieraForm').serialize(),
        url: '/dashboard/registrarPasoCotizadorEntidadFinanciera',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = "";
                html += "<section class='container marginBottom20'>";
                html += "<div class='width990 solicitudBox autoMargin radius2'>";
                html += "<div class='clearFix'>";
                html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>TÍTULO DEL PASO</strong></p>";
                html += "<p class='font14 gray2' style='text-align: center;margin-left: -30px;'>" + respuesta.pasoCotizadorEntidadFinanciera.tituloDelPaso + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>NÚMERO DE PASO</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.pasoCotizadorEntidadFinanciera.numeroDePaso + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>ID LISTA AJAX</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.pasoCotizadorEntidadFinanciera.idListaAjax + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>VALOR VARIABLE SELECCIONADA</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.pasoCotizadorEntidadFinanciera.variableValorSeleccionado + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='Ver Detalle' onclick='mostrarDetallePasoCotizador(" + respuesta.pasoCotizadorEntidadFinanciera.id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
                html += "</div>";
                html += "</div>";
                html += "</div> ";
                html += "</div> ";
                html += "</section>";
                $('#listaPasosCotizador').append(html);
                cerrarModal('modalAltaPasosCotizador');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function registrarPasoSolicitudEntidadFinanciera() {
    jQuery.ajax({
        type: 'POST',
        data: $('#altaPasoSolicitudEntidadFinancieraForm').serialize(),
        url: '/dashboard/registrarPasoSolicitudEntidadFinanciera',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = "";
                html += "<section class='container marginBottom20'>";
                html += "<div class='width990 solicitudBox autoMargin radius2'>";
                html += "<div class='clearFix'>";
                html += "<div class='col1fifth col2-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>TÍTULO DEL PASO</strong></p>";
                html += "<p class='font14 gray2 textUpper' style='text-align: center;margin-left: -30px;'>" + respuesta.pasoSolicitudEntidadFinanciera.titulo + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>NÚMERO DE PASO</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.pasoSolicitudEntidadFinanciera.numeroDePaso + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>PONDERACIÓN</strong></p>";
                html += "<p class='font14 gray2'>" + respuesta.pasoSolicitudEntidadFinanciera.ponderacion + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft' > ";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>MODO DE DESPLIEGUE</strong></p>";
                html += "<p class='font14 gray2 textUpper'>" + respuesta.pasoSolicitudEntidadFinanciera.modoDeDespliegue + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>ULTIMO PASO</strong></p>";
                html += "<p class='font14 gray2 textUpper'>" + respuesta.pasoSolicitudEntidadFinanciera.ultimoPaso + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col3-tab col12-mob floatLeft'>";
                html += "<div class='borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10'>";
                html += "<p class='font12 gray2 marginBottom5'><strong>TIPO DE PASO</strong></p>";
                html += "<p class='font14 gray2 textUpper'>" + respuesta.pasoSolicitudEntidadFinancieraTipoDePaso.nombre + "</p>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='Ver Detalle' onclick='configurarPasoSolicitud(" + respuesta.pasoSolicitudEntidadFinanciera.id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>VER DETALLE</a>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='Ver Detalle' onclick='configurarPasoSolicitud(" + respuesta.pasoSolicitudEntidadFinanciera.id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>CONFIGURAR ESTE PASO</a>";
                html += "</div>";
                html += "</div>";
                html += "<div class='col1fifth col12-tab col12-mob floatLeft'>";
                html += "<div class='marginTop10 marginBottom10 clearFix paddingAside10'>";
                html += "<a title='Ver Detalle' onclick='eliminarPasoSolicitud(" + respuesta.pasoSolicitudEntidadFinanciera.id + ");' class='tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer'>ElIMINAR</a>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</section>";
                $('#listaPasosSolicitud').append(html);
                cerrarModal('modalAltaPasosSolicitud');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function registrarLimitePlazoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#altaLimitePlazosProductoForm').serialize(),
        url: '/producto/registrarLimitePlazoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var html = "";
                html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto.limiteMaximo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto.limiteMinimo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad.nombre + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto.plazo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarLimiteProducto(" + respuesta.limitePlazoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarLimiteProducto(" + respuesta.limitePlazoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                $('#limitePlazoProductoAgregado tbody').append(html);
                cerrarModal('modalAltaLimitePlazoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function registrarPlazoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#plazoProductoForm').serialize(),
        url: '/producto/registrarPlazoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var html = ""
                html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.importeMaximo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.importeMinimo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad.nombre + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.plazoMaximo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.plazoMinimo + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.saltoSlider + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto.plazosPermitidos + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarPlazoProducto(" + respuesta.plazoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarPlazoProducto(" + respuesta.plazoProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                $('#plazoProductoAgregado tbody').append(html);
                cerrarModal('modalAltaPlazoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function registrarGarantiaProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#altaGarantiaProductoForm').serialize(),
        url: '/producto/registrarGarantiaProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.exito) {
                var html = ""
                html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto.cantidadMaxima + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto.cantidadMinima + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto.descripcion + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeGarantia.nombre + "</td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarGarantiaProducto(" + respuesta.garantiaProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarGarantiaProducto(" + respuesta.garantiaProducto.id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                $('#garantiaProductoAgregado tbody').append(html);
                cerrarModal('modalAltaGarantiaProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function actualizarCampoFormulario() {
    jQuery.ajax({
        type: 'POST',
        data: $('#campoFormularioUpdateForm').serialize(),
        url: '/dashboard/updateCampoFormulario',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarCamposAgregados(respuesta.session);
                cerrarModal("modalDetalleCampoFormulario");
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}



function actualizarDocumentoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#actualizarDocumentoProductoForm').serialize(),
        url: '/producto/updateDocumentoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var respuesta = eval(data);
                var html = ""
                if (respuesta.tipoDeDocumento.length > 0) {
                    for (var x = 0; x < respuesta.tipoDeDocumento.length; x++) {
                        html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeDocumento[x].nombre + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto[x].obligatorio + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto[x].activo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarDocumentoProducto(" + respuesta.documentoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarDocumentoProducto(" + respuesta.documentoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                        $('#documentoProductoAgregado tbody').html(html);
                    }
                } else {
                    html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Documentos</td>";
                    $('#documentoProductoAgregado tbody').html(html);
                }
                cerrarModal('modalDetalleDocumentoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function actualizarPasoCotizadorEntidadFinanciera() {
    jQuery.ajax({
        type: 'POST',
        data: $('#updatePasoCotizadorEntidadFinancieraForm').serialize(),
        url: '/pasoCotizadorEntidadFinanciera/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetallePasoCotizador');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });

}
function actualizarPasoSolicitudEntidadFinanciera() {
    jQuery.ajax({
        type: 'POST',
        data: $('#updatePasoSolicitudEntidadFinancieraForm').serialize(),
        url: '/pasoSolicitudEntidadFinanciera/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetallePasoSolicitud');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });

}


function actualizarPlazoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#plazosForm').serialize(),
        url: '/producto/updatePlazoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var respuesta = eval(data);
                var html = "";
                if (respuesta.plazoProducto.length > 0) {
                    for (var x = 0; x < respuesta.plazoProducto.length; x++) {
                        html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].importeMaximo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].importeMinimo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad[x].nombre + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazoMaximo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazoMinimo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].saltoSlider + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazosPermitidos + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarPlazoProducto(" + respuesta.plazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarPlazoProducto(" + respuesta.plazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                        $('#plazoProductoAgregado tbody').html(html);
                    }
                }
                cerrarModal('modalDetallePlazoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function actualizarLimitePlazoProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#limitePlazosProductoForm').serialize(),
        url: '/producto/updateLimitePlazoProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = ""
                if (respuesta.limitePlazoProducto.length > 0) {
                    for (var x = 0; x < respuesta.limitePlazoProducto.length; x++) {
                        html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].limiteMaximo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].limiteMinimo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad[x].nombre + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].plazo + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarLimiteProducto(" + respuesta.limitePlazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarLimiteProducto(" + respuesta.limitePlazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                        $('#limitePlazoProductoAgregado tbody').html(html);
                    }
                } else {
                    html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Plazos</td>";
                    $('#limitePlazoProductoAgregado tbody').html(html);
                }

                cerrarModal('modalDetalleLimitePlazoProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function actualizarGararantiaProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#garantiaProductoForm').serialize(),
        url: '/producto/updateGarantiaProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = ""
                if (respuesta.garantiaProducto.length > 0) {
                    for (var x = 0; x < respuesta.garantiaProducto.length; x++) {
                        html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].cantidadMaxima + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].cantidadMinima + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].descripcion + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeGarantia[x].nombre + "</td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarGarantiaProducto(" + respuesta.garantiaProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarGarantiaProducto(" + respuesta.garantiaProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                        $('#garantiaProductoAgregado tbody').html(html);
                    }
                } else {
                    html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Plazos</td>";
                    $('#garantiaProductoAgregado tbody').html(html);
                }

                cerrarModal('modalDetalleGarantiaProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function actualizarRubro() {
    jQuery.ajax({
        type: 'POST',
        data: $('#rubroDeAplicacionDeCreditoForm').serialize(),
        url: '/rubroDeAplicacionDeCredito/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetalleRubroDeAplicacionDeCredito');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });
}
function actualizarProducto() {
    jQuery.ajax({
        type: 'POST',
        data: $('#productoForm').serialize(),
        url: '/producto/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetalleProducto');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });
}
function actualizarSeguroSobreDeuda() {
    jQuery.ajax({
        type: 'POST',
        data: $('#updateSeguroSobreDeudaForm').serialize(),
        url: '/seguroSobreDeuda/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetalleSeguroSobreDeuda');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });
}
function actualizarServicioDeAsistencia() {
    jQuery.ajax({
        type: 'POST',
        data: $('#updateServicioDeAsistenciaForm').serialize(),
        url: '/servicioDeAsistencia/update',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                cerrarModal('modalDetalleServicioDeAsistencia');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
    });
}
function actualizarConfiguracionEntidadFinanciera() {

    jQuery.ajax({
        type: 'POST',
        data: $('#configuracionEntidadFinancieraForm').serialize(),
        url: '/configuracionEntidadFinanciera/update',
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
    var nombreEntidadFinanciera = $('#nombreEntidadFinanciera').val();
    var imagenDefault = $("#imagenDefault2").prop("files")[0];
    var rutaLogotipo = $("#rutaLogotipo2").prop("files")[0];
    var aplicacionVariable = $("#aplicacionVariable").val()
    var form_data = new FormData();
    form_data.append("nombreEntidadFinanciera", nombreEntidadFinanciera);
    form_data.append("imagenDefault", imagenDefault);
    form_data.append("rutaLogotipo", rutaLogotipo);
    form_data.append("aplicacionVariable", aplicacionVariable);
    jQuery.ajax({
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        data: form_data,
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
                cerrarModal('modalNuevaEntidadFinanciera');
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
    console.log($('#tipoDeDocumento').val());
    kosmosDropzone = new Dropzone(elemento, {
        url: "/dashboard/subirImagen",
        uploadMultiple: true,
        parallelUploads: 1,
        paramName: "archivo",
        addRemoveLinks: true,
        params: {'imgType': $('#tipoDeDocumento').val()},
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: ".png, .jpg, .jpeg, .css",
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
    $(window).scrollTop($('#' + divModal).position().top);
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
            $(".js-example-basic-multiple").select2();
            $('.subirImagen').click(function () {
                inicializarDropzone('div#divDropzone2', '#subirImagenProducto');
                kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': 6, 'solicitudId': $('#idProducto').val()};

            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function mostrarDetalleSeguroSobreDeuda(idSeguroSobreDeuda) {
    jQuery.ajax({
        type: 'POST',
        data: 'idSeguroSobreDeuda=' + idSeguroSobreDeuda,
        url: '/seguroSobreDeuda/obtenerDetalleSeguroSobreDeuda',
        success: function (data, textStatus) {
            $('#detalleSeguroSobreDeuda').html(data);
            openModal('modalDetalleSeguroSobreDeuda');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function mostrarDetalleServicioDeAsistencia(idServicioDeAsistencia) {
    jQuery.ajax({
        type: 'POST',
        data: 'idServicioDeAsistencia=' + idServicioDeAsistencia,
        url: '/servicioDeAsistencia/obtenerDetalleServicioDeAsistencia',
        success: function (data, textStatus) {
            $('#detalleServicioDeAsistencia').html(data);
            openModal('modalDetalleServicioDeAsistencia');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function altaDocumentoProducto(idProducto) {
    jQuery.ajax({
        type: 'POST',
        data: 'idProducto=' + idProducto,
        url: '/producto/altaDocumentoProducto',
        success: function (data, textStatus) {
            $('#altaDocumentoProducto').html(data);
            openModal('modalAltaDocumentoProducto');
            $(".js-example-basic-multiple").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function altaPlazoProducto(idProducto) {
    jQuery.ajax({
        type: 'POST',
        data: 'idProducto=' + idProducto,
        url: '/producto/altaPlazoProducto',
        success: function (data, textStatus) {
            $('#altaPlazoProducto').html(data);
            openModal('modalAltaPlazoProducto');
            $(".js-example-basic-multiple").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function altaLimitePlazoProducto(idProducto) {
    jQuery.ajax({
        type: 'POST',
        data: 'idProducto=' + idProducto,
        url: '/producto/altaLimitePlazoProducto',
        success: function (data, textStatus) {
            $('#altaLimitePlazoProducto').html(data);
            openModal('modalAltaLimitePlazoProducto');
            $(".js-example-basic-multiple").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function altaGarantiaProducto(idProducto) {
    jQuery.ajax({
        type: 'POST',
        data: 'idProducto=' + idProducto,
        url: '/producto/altaGarantiaProducto',
        success: function (data, textStatus) {
            $('#altaGarantiaProducto').html(data);
            openModal('modalAltaGarantiaProducto');

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function mostrarDetallePasoCotizador(idPasoCotizador) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idPasoCotizador,
        url: '/pasoCotizadorEntidadFinanciera/obtenerDetallePasoCotizador',
        success: function (data, textStatus) {
            $('#detallePasoCotizador').html(data);
            openModal('modalDetallePasoCotizador');
            $(".js-example-basic-multiple").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function mostrarDetallePasoSolicitud(idPasoCotizador) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idPasoCotizador,
        url: '/pasoSolicitudEntidadFinanciera/obtenerDetallePasoSolicitud',
        success: function (data, textStatus) {
            $('#detallePasoSolicitud').html(data);
            openModal('modalDetallePasoSolicitud');
            $(".js-example-basic-multiple").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function mostrarDetalleTipoDeDocumento(idTipoDeDocumento) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeDocumento,
        url: '/tipoDeDocumento/obtenerDetalleTipoDeDocumento',
        success: function (data, textStatus) {
            $('#detalleTipoDeDocumento').html(data);
            openModal('modalDetalleTipoDeDocumento');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function eliminarTipoDeAsentamiento(idTipoDeAsentamiento) {
    sweetAlert({
        title: "Estas seguro de eliminar el Tipo de Asentamiento?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'id=' + idTipoDeAsentamiento,
                    url: '/tipoDeAsentamiento/eliminar',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {

                            sweetAlert("El Tipo de Asentamiento se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });
}


function eliminarTipoDeVivienda(idTipoDeVivienda) {
    sweetAlert({
        title: "Estas seguro de eliminar el Tipo de Vivienda?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'id=' + idTipoDeVivienda,
                    url: '/tipoDeVivienda/eliminar',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {

                            sweetAlert("El Tipo de Asentamiento se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });
}



function eliminarPasoSolicitud(idPasoSolicitudEntidadFinanciera) {
    sweetAlert({
        title: "Estas seguro de eliminar el Paso ?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'id=' + idPasoSolicitudEntidadFinanciera,
                    url: '/pasoSolicitudEntidadFinanciera/eliminar',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {

                            sweetAlert("El Paso se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });
}

function editarTipoDeAsentamiento(idTipoDeAsentamiento) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeAsentamiento,
        url: '/tipoDeAsentamiento/obtenerDetalleTipoDeAsentamiento',
        success: function (data, textStatus) {
            $('#detalleTipoDeAsentamiento').html(data);
            openModal('modalDetalleTipoDeAsentamiento');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function editarRubroDeAplicacionDeCredito(idRubroDeAplicacionDeCredito) {

    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idRubroDeAplicacionDeCredito,
        url: '/rubroDeAplicacionDeCredito/obtenerDetalleRubroDeAplicacionDeCredito',
        success: function (data, textStatus) {
            $('#detalleRubroDeAplicacionDeCredito').html(data);
            openModal('modalDetalleRubroDeAplicacionDeCredito');
            $(".js-example-basic-multiple").select2();
            $('.subirImagen').click(function () {
                inicializarDropzone('div#divDropzone2', '#subirImagenRubro');
                kosmosDropzone.options.params = {'imgType': $('#tipoDeDocumento').val(), 'origen': $('#opcionMenu').val(), 'solicitudId': $('#idRubroDeAplicacionDeCredito').val()};

            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}



function editarTipoDeCampo(idTipoDeCampo) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeCampo,
        url: '/tipoDeCampo/obtenerDetalleTipoDeCampo',
        success: function (data, textStatus) {
            $('#detalleTipoDeCampo').html(data);
            openModal('modalDetalleTipoDeCampo');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeGarantia(idTipoDeGarantia) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeGarantia,
        url: '/tipoDeGarantia/obtenerDetalleTipoDeGarantia',
        success: function (data, textStatus) {
            $('#detalleTipoDeGarantia').html(data);
            openModal('modalDetalleTipoDeGarantia');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeIngresos(idTipoDeIngresos) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeIngresos,
        url: '/tipoDeIngresos/obtenerDetalleTipoDeIngresos',
        success: function (data, textStatus) {
            $('#detalleTipoDeIngresos').html(data);
            openModal('modalDetalleTipoDeIngresos');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeFotografia(idTipoDeFotografia) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeFotografia,
        url: '/tipoDeFotografia/obtenerDetalleTipoDeFotografia',
        success: function (data, textStatus) {
            $('#detalleTipoDeFotografia').html(data);
            openModal('modalDetalleTipoDeFotografia');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeContrato(idTipoDeContrato) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeContrato,
        url: '/tipoDeContrato/obtenerDetalleTipoDeContrato',
        success: function (data, textStatus) {
            $('#detalleTipoDeContrato').html(data);
            openModal('modalDetalleTipoDeContrato');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeVivienda(idTipoDeVivienda) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeVivienda,
        url: '/tipoDeVivienda/obtenerDetalleTipoDeVivienda',
        success: function (data, textStatus) {
            $('#detalleTipoDeVivienda').html(data);
            openModal('modalDetalleTipoDeVivienda');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function editarTipoDeTasaDeInteres(idTipoDeTasaDeInteres) {
    jQuery.ajax({
        type: 'POST',
        data: 'id=' + idTipoDeTasaDeInteres,
        url: '/tipoDeTasaDeInteres/obtenerDetalleTipoDeTasaDeInteres',
        success: function (data, textStatus) {
            $('#detalleTipoDeTasaDeInteres').html(data);
            openModal('modalDetalleTipoDeTasaDeInteres');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function verListaIconos(idDiv, idCampo) {
    jQuery.ajax({
        type: 'POST',
        data: 'idDiv=' + idDiv + "&idCampo=" + idCampo,
        url: '/producto/obtenerIconos',
        success: function (data, textStatus) {
            $('#' + idDiv).html(data);
            openModal('modalDetalleIconos');

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
            agregarMarcador(solicitudes[i].coordenadas, solicitudes[i].direccion, solicitudes[i].folio, solicitudes[i].cliente);
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

function agregarDocumento() {
    var tipoDeDocumentoId = $('#tipoDeDocumentoId').val();
    var idRubroTemp = $('#idRubroTemp').val();
    var idDiv = $('#idDiv').val();
    $.ajax({
        type: 'POST',
        data: {tipoDeDocumentoId: tipoDeDocumentoId, idRubroTemp: idRubroTemp},
        url: '/dashboard/agregarDocumento',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarDocumentos(respuesta.session);
                cerrarModal('modalDocumentos');
                $('#' + idDiv).removeClass('redButton');
                $('#' + idDiv).addClass('blueButton');
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function agregarVistas() {
    var idDiv = $('#idDivVistas').val();
    var pasoCotizadorId = $('#pasoCotizadorId').val();
    var idRubroTemp = $('#idRubroTemp').val();
    $.ajax({
        type: 'POST',
        data: {pasoCotizadorId: pasoCotizadorId, idRubroTemp: idRubroTemp},
        url: '/dashboard/agregarVistas',
        success: function (data, textStatus) {
            mostrarVistas(data);
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarVistas(respuesta.sessionvistaVistas);
                cerrarModal('modalVistas');
                $('#' + idDiv).removeClass('redButton');
                $('#' + idDiv).addClass('blueButton');
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });


}


function registrarSeguroSobreDeuda() {
    var montoInicial = $('#montoInicial').val();
    var montoFinal = $('#montoFinal').val();
    var plazoAnual = $('#plazoAnual').val();
    var importeSeguro = $('#importeSeguro').val();
    $.ajax({
        type: 'POST',
        data: {montoInicial: montoInicial, montoFinal: montoFinal, plazoAnual: plazoAnual, importeSeguro: importeSeguro},
        url: '/dashboard/registrarSeguroSobreDeuda',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                sweetAlert("Correcto", respuesta.mensaje, "success");
                cerrarModal('modalAltaSeguroSobreDeuda');
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function registrarServicioDeAsistencia() {
    var montoInicial = $('#montoInicial2').val();
    var montoFinal = $('#montoFinal2').val();
    var importeAsistencia = $('#importeAsistencia').val();
    var plazoAnual = $('#plazoAnual2').val();
    var plazoQuincenal = $('#plazoQuincenal2').val();
    var plazoSemanal = $('#plazoSemanal2').val();
    $.ajax({
        type: 'POST',
        data: {montoInicial: montoInicial, montoFinal: montoFinal, importeAsistencia: importeAsistencia, plazoAnual: plazoAnual, plazoQuincenal: plazoQuincenal, plazoSemanal: plazoSemanal},
        url: '/dashboard/registrarServicioDeAsistencia',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                sweetAlert("Correcto", respuesta.mensaje, "success");
                cerrarModal('modalAltaServicioDeAsistencia');
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function editarPerfilDeMarca(idEntidadFinanciera) {
    var idEntidadFinanciera = idEntidadFinanciera;
    $.ajax({
        type: 'POST',
        data: {idEntidadFinanciera: idEntidadFinanciera},
        url: '/dashboard/editarPerfilDeMarca',
        success: function (data, textStatus) {

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function agregarProducto() {
    var idRubroTemp = $('#idRubroTemp').val();
    var idDiv = $('#idDivProductos').val();
    var nombreDelProducto = $('#nombreDelProducto2').val();
    var rutaImagenDefault = $('#rutaImagenDefault2').val();
    var descripcionProducto = $('#descripcionProducto2').val();
    var tituloEnCotizador = $('#tituloEnCotizador2').val();
    var claveDeProducto = $('#claveDeProducto2').val();
    var haTenidoAtrasos = $('input:radio[name=haTenidoAtrasos]:checked').val();
    var cat = $('#cat2').val();
    var activo = $('input:radio[name=activoProducto]:checked').val();
    var montoMaximo = $('#montoMaximo2').val();
    var montoMinimo = $('#montoMinimo2').val();
    var tasaDeInteres = $('#tasaDeInteres2').val();
    var tipoDeIngresosId = $('#tipoDeIngresosId').val();
    var claseIconoPaso = $('#claseIconoProductos22').val();
    var file_data = $("#rutaImagenDefault2").prop("files")[0];
    var form_data = new FormData();
    for (var i = 0; i < tipoDeIngresosId.length; i++) {
        form_data.append('tipoDeIngresosId[]', tipoDeIngresosId[i]);
    }
    form_data.append("nombreDelProducto", nombreDelProducto);
    form_data.append("descripcionProducto", descripcionProducto);
    form_data.append("tituloEnCotizador", tituloEnCotizador);
    form_data.append("claveDeProducto", claveDeProducto);
    form_data.append("haTenidoAtrasos", haTenidoAtrasos);
    form_data.append("cat", cat);
    form_data.append("activo", activo);
    form_data.append("montoMaximo", montoMaximo);
    form_data.append("montoMinimo", montoMinimo);
    form_data.append("tasaDeInteres", tasaDeInteres);
    form_data.append("claseIconoPaso", claseIconoPaso);
    form_data.append("rutaImagenDefault", file_data);
    form_data.append("idRubroTemp", idRubroTemp);
    $.ajax({
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: form_data,
        url: '/dashboard/agregarProducto',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarProductos(respuesta.session);
                //abrirTour("#plazosPaso", "Asociar Plazos", "Para continuar debes agregar los plazos que seran asociados.", "right");
                $('#' + idDiv).removeClass('redButton');
                $('#' + idDiv).addClass('blueButton');

            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function agregarCampoFormulario() {
    $.ajax({
        type: 'POST',
        data: $('#campoFormularioForm').serialize(),
        url: '/dashboard/agregarCampoFormulario',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarCamposAgregados(respuesta.session);
                cerrarModal("modalAltaCampoFormulario");
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function agregarRubro() {
    var nombre = $('#nombre').val();
    var posicion = $('#posicion').val();
    var descripcion = $('#descripcionrubro').val();
    var claseIconoPaso = $('#claseIconoPasoRubro').val();
    var tooltip = $('input:radio[name=tooltip]:checked').val();
    var textoTooltip = $('#textoTooltip').val();
    var activo = $('input:radio[name=activoRubro]:checked').val();

    var file_data = $("#urlImagen").prop("files")[0];
    var form_data = new FormData();
    form_data.append("nombre", nombre);
    form_data.append("posicion", posicion);
    form_data.append("descripcion", descripcion);
    form_data.append("claseIconoPaso", claseIconoPaso);
    form_data.append("tooltip", tooltip);
    form_data.append("textoTooltip", textoTooltip);
    form_data.append("urlImagen", file_data);
    form_data.append("activo", activo);
    $.ajax({
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: form_data,
        url: '/dashboard/agregarRubro',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarRubros(respuesta.session);
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function agregarGarantias() {
    var idRubroTemp = $('.idRubroTemp').val();
    var idProductoTemp = $('.idproductoTemp').val();
    var cantidadMaxima = $('#cantidadMaxima').val();
    var cantidadMinima = $('#cantidadMinima').val();
    var descripcionGarantia = $('#descripcionGarantia').val();
    var tipoDeGarantiaId = $('#tipoDeGarantiaId').val();

    $.ajax({
        type: 'POST',
        data: {idproductoTemp: idProductoTemp, cantidadMaxima: cantidadMaxima, cantidadMinima: cantidadMinima,
            descripcionGarantia: descripcionGarantia, tipoDeGarantiaId: tipoDeGarantiaId},
        url: '/dashboard/agregarGarantia',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarGarantias(respuesta.sessionGarantiasVista);
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });



}
function agregarPlazo() {

    var idRubroTemp = $('.idRubroTemp').val();
    var idProductoTemp = $('.idproductoTemp').val();
    console.log(idRubroTemp);
    var importeMaximo = $('#importeMaximo').val();
    var importeMinimo = $('#importeMinimo').val();
    var plazoMaximo = $('#plazoMaximo').val();
    var plazoMinimo = $('#plazoMinimo').val();
    var plazosPermitidos = $('#plazosPermitidos22').val();
    var saltoSlider = $('#saltoSlider').val();
    var periodicidadId = $('#periodicidadId').val();
    var usarListaDePlazos = $('input:radio[name=usarListaDePlazos]:checked').val();

    $.ajax({
        type: 'POST',
        data: {idproductoTemp: idProductoTemp, importeMaximo: importeMaximo, importeMinimo: importeMinimo,
            plazoMaximo: plazoMaximo, plazoMinimo: plazoMinimo, periodicidadId: periodicidadId,
            plazosPermitidos: plazosPermitidos, saltoSlider: saltoSlider, usarListaDePlazos: usarListaDePlazos},
        url: '/dashboard/agregarPlazo',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.respuesta.ok) {
                sweetAlert("Correcto", respuesta.respuesta.mensaje, "success");
                mostrarPlazos(respuesta.sessionPlazosVista);
            } else {
                sweetAlert("Oops...", respuesta.respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function verificar() {
    $.ajax({
        type: 'POST',
        url: '/dashboard/verificar',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                sweetAlert("Rubro Guardado Correctamente", respuesta.mensaje, "success");
            } else {
                sweetAlert("Oops...", respuesta.mensaje, "error");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}
function vistaPrevia() {
    $.ajax({
        type: 'POST',
        url: '/dashboard/vistaPrevia',
        success: function (data, textStatus) {
            $('#vistaPreviaConfiguracionCotizador').html(data);
            openModal('modalVistaPrevia');
            $(function () {
                $("#accordion").accordion({
                    header: "h1",
                    collapsible: true
                });
            });
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
function eliminarDocumentoProducto(idDocumentoProducto) {
    sweetAlert({
        title: "Estas seguro de eliminar este Documento?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'idDocumentoProducto=' + idDocumentoProducto,
                    url: '/producto/eliminarDocumentoProducto',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {
                            var html = ""
                            if (respuesta.tipoDeDocumento.length > 0) {
                                for (var x = 0; x < respuesta.tipoDeDocumento.length; x++) {
                                    html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeDocumento[x].nombre + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto[x].obligatorio + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.documentoProducto[x].activo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarDocumentoProducto(" + respuesta.documentoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarDocumentoProducto(" + respuesta.documentoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                                    $('#documentoProductoAgregado tbody').html(html);
                                }
                            } else {
                                html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Plazos</td>";
                                $('#documentoProductoAgregado tbody').html(html);
                            }
                            sweetAlert("El documento se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });
}

function eliminarPlazoProducto(idPlazoProducto) {
    sweetAlert({
        title: "Estas seguro de eliminar este Plazo?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'idPlazoProducto=' + idPlazoProducto,
                    url: '/producto/eliminarPlazoProducto',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {
                            var html = ""
                            if (respuesta.plazoProducto.length > 0) {
                                for (var x = 0; x < respuesta.plazoProducto.length; x++) {
                                    html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].importeMaximo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].importeMinimo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad[x].nombre + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazoMaximo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazoMinimo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].saltoSlider + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.plazoProducto[x].plazosPermitidos + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarPlazoProducto(" + respuesta.plazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarPlazoProducto(" + respuesta.plazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                                    $('#plazoProductoAgregado tbody').html(html);
                                }
                            } else {
                                html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Plazos</td>";
                                $('#plazoProductoAgregado tbody').html(html);
                            }
                            sweetAlert("El Plazo se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });



}

function eliminarLimiteProducto(idLimiteProducto) {
    sweetAlert({
        title: "Estas seguro de eliminar este Limite?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'idLimiteProducto=' + idLimiteProducto,
                    url: '/producto/eliminarLimitePlazoProducto',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {
                            var html = ""
                            if (respuesta.limitePlazoProducto.length > 0) {
                                for (var x = 0; x < respuesta.limitePlazoProducto.length; x++) {
                                    html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].limiteMaximo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].limiteMinimo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.periodicidad[x].nombre + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.limitePlazoProducto[x].plazo + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarLimiteProducto(" + respuesta.limitePlazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarLimiteProducto(" + respuesta.limitePlazoProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                                    $('#limitePlazoProductoAgregado tbody').html(html);
                                }
                            }

                            sweetAlert("El Limite se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });

}

function eliminarGarantiaProducto(idGarantiaProducto) {
    sweetAlert({
        title: "Estas seguro de eliminar esta Garantía?",
        text: "Se eliminara para siempre!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, Borrar!",
        closeOnConfirm: false
    },
            function () {
                $.ajax({
                    type: 'POST',
                    data: 'idGarantiaProducto=' + idGarantiaProducto,
                    url: '/producto/eliminarGarantiaProducto',
                    success: function (data, textStatus) {
                        var respuesta = eval(data);
                        if (respuesta.ok) {
                            var html = ""
                            if (respuesta.garantiaProducto.length > 0) {
                                for (var x = 0; x < respuesta.garantiaProducto.length; x++) {
                                    html += "<tr><td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].cantidadMaxima + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].cantidadMinima + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.garantiaProducto[x].descripcion + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>" + respuesta.tipoDeGarantia[x].nombre + "</td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='modificarGarantiaProducto(" + respuesta.garantiaProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Modificar</button>  </td>";
                                    html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'> <button type='button' onclick='eliminarGarantiaProducto(" + respuesta.garantiaProducto[x].id + ");' class='blueButton  pointer' style='width:100% ;height:20% ;' >Eliminar</button>  </td> </tr>";
                                    $('#garantiaProductoAgregado tbody').html(html);
                                }
                            } else {
                                html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Garantías</td>";
                                $('#garantiaProductoAgregado tbody').html(html);
                            }


                            sweetAlert("El Limite se ha eliminado", respuesta.mensaje, "success");
                        } else {
                            sweetAlert("Oops...", respuesta.mensaje, "error");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {}
                });

            });

}





function modificarCampoFormulario(idCampoFormularioTemp) {
    $.ajax({
        type: 'POST',
        data: 'idCampoFormularioTemp=' + idCampoFormularioTemp,
        url: '/dashboard/modificarCampoFormulario',
        success: function (data, textStatus) {
            $('#detalleCampoFormulario').html(data);
            openModal('modalDetalleCampoFormulario');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function modificarDocumentoProducto(idDocumentoProducto) {
    $.ajax({
        type: 'POST',
        data: 'idDocumentoProducto=' + idDocumentoProducto,
        url: '/producto/modificarDocumentoproducto',
        success: function (data, textStatus) {
            $('#detalleDocumentoProducto').html(data);
            openModal('modalDetalleDocumentoProducto');
            $(".js-example-basic-single").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}




function modificarLimiteProducto(idLimiteProducto) {
    $.ajax({
        type: 'POST',
        data: 'idLimiteProducto=' + idLimiteProducto,
        url: '/producto/modificarLimiteProducto',
        success: function (data, textStatus) {
            $('#detalleLimitePlazoProducto').html(data);
            openModal('modalDetalleLimitePlazoProducto');
            $(".js-example-basic-single").select2();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function modificarPlazoProducto(idPlazoProducto) {
    $.ajax({
        type: 'POST',
        data: 'idPlazoProducto=' + idPlazoProducto,
        url: '/producto/modificarPlazoProducto',
        success: function (data, textStatus) {
            $('#detallePlazoProducto').html(data);
            openModal('modalDetallePlazoProducto');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}


function modificarGarantiaProducto(idGarantiaProducto) {
    $.ajax({
        type: 'POST',
        data: 'idGarantiaProducto=' + idGarantiaProducto,
        url: '/producto/modificarGarantiaProducto',
        success: function (data, textStatus) {
            $('#detalleGarantiaProducto').html(data);
            openModal('modalDetalleGarantiaProducto');
            $(".js-example-basic-single").select2();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}



function eliminarRubro(idRubro) {
    $.ajax({
        type: 'POST',
        data: 'idRubro=' + idRubro,
        url: '/dashboard/eliminarRubro',
        success: function (data, textStatus) {
            mostrarRubros(data);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function eliminarVistas(idPaso, idRubroTemp) {
    $.ajax({
        type: 'POST',
        data: 'idPaso=' + idPaso + "&idRubroTemp=" + idRubroTemp,
        url: '/dashboard/eliminarVistas',
        success: function (data, textStatus) {
            mostrarVistas(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function eliminarDocumento(idDocumento, idRubroTemp) {
    $.ajax({
        type: 'POST',
        data: 'idDocumento=' + idDocumento + "&idRubroTemp=" + idRubroTemp,
        url: '/dashboard/eliminarDocumento',
        success: function (data, textStatus) {
            mostrarDocumentos(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function eliminarProducto(idProducto, idRubroTemp) {
    $.ajax({
        type: 'POST',
        data: 'idProducto=' + idProducto + "&idRubroTemp=" + idRubroTemp,
        url: '/dashboard/eliminarProducto',
        success: function (data, textStatus) {
            mostrarProductos(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function eliminarPlazo(idPlazo, idProductoTemp) {
    $.ajax({
        type: 'POST',
        data: 'idPlazo=' + idPlazo + "&idProductoTemp=" + idProductoTemp,
        url: '/dashboard/eliminarPlazo',
        success: function (data, textStatus) {
            mostrarPlazos(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}
function eliminarGarantia(idGarantia, idProductoTemp) {
    $.ajax({
        type: 'POST',
        data: 'idGarantia=' + idGarantia + "&idProductoTemp=" + idProductoTemp,
        url: '/dashboard/eliminarGarantia',
        success: function (data, textStatus) {
            mostrarGarantias(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function mostrarDocumentos(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDePreguntas').val(resultado.length);
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + " - " + resultado[x].texto + "</td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarDocumento(" + resultado[x].id + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        console.log(html);
        $('#documentosAgregados tbody').html(html);
        $('#btnVerificar').addClass('blueButton');
        $('#btnVerificar').prop("disabled", false);
        $('#textoPregunta').val('');
    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#documentosAgregados tbody').html(html);
    }
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

function mostrarDetalleUsuario(data) {
    var html = ""
    var resultado = eval(data);
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<div class='col12 col12-mob floatLeft clearFix marginTop25 marginBottom25 paddingBottom20 '>";
            html += "<div class='col4 floatLeft'>";
            html += "<div class='paddingTop20'>";
            html += "<p class='gray font14 fontWeight500 latterspacing1 center'>Nombre(s)</p>";
            html += "</div>";
            html += "<div class='paddingTop20'>";
            html += "<p class='gray font14 fontWeight500 latterspacing1 center'>Apellido Paterno</p>";
            html += "</div>";
            html += "<div class='paddingTop20'>";
            html += "<p class='gray font14 fontWeight500 latterspacing1 center'>Apellido Materno</p>";
            html += "</div>";
            html += "<div class='paddingTop20'>";
            html += "<p class='gray font14 fontWeight500 latterspacing1 center'>Username</p>";
            html += "</div>";
            html += "<div class='paddingTop20'>";
            html += "<p class='gray font14 fontWeight500 latterspacing1 center'>E-mail</p>";
            html += "</div>";
            html += "</div>";
            html += "<input  type='hidden' id='idUsuario' value=" + resultado[x].idUsuario + " >";

            html += "<div class='col8 floatLeft'>";
            html += "<div class='paddingTop15'>";
            html += "<input class='block cameraBox col11 height30' type='text' id='nombreUsuario'name='nombre'value=" + resultado[x].nombre + " >";
            html += "</div>";
            html += "<div class='paddingTop8'>";
            html += "<input class='block cameraBox col11 height30' type='text'  id='apellidoPaternoUsuario' name='apellidoPaterno' value=" + resultado[x].apellidoPaterno + " >";
            html += "</div>";
            html += " <div class='paddingTop8'>";
            html += "<input class='block cameraBox col11 height30' type='text' id='apellidoMaternoUsuario' name='apellidoMaterno' value=" + resultado[x].apellidoMaterno + " >";
            html += "</div>";
            html += "<div class='paddingTop8'>";
            html += "<input class='block cameraBox col11 height30' type='text'  id='usernameUsuario' name='username' value=" + resultado[x].username + " >";
            html += "</div>";
            html += " <div class='paddingTop8'>";
            html += "<input class='block cameraBox col11 height30' type='text' id='emailUsuario' name='email' value=" + resultado[x].email + " >";
            html += " </div>";
            html += " </div>";

        }
        console.log(html);
        $('#detalleU').html(html);


    } else {
        html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Rubros</td>";
        $('#detalleU').html(html);
    }
}



function mostrarCamposAgregados(data) {
    var html = ""
    var resultado = eval(data);
    if (resultado.length > 0) {
        $('#campoFormularioAgregado').html('');
        html += "<fieldset class='step1 formStep' style='padding: 0px 15px;' >";
        html += "<div class='font35 marginTop30 letterspacing1 formTitleColor lineHeight60'>";

        for (var x = 0; x < resultado.length; x++) {
            var mostrarAlInicio = "";
            var obligatorio = "nonRequired";
            if (resultado[x].mostrarAlInicio === false) {
                var mostrarAlInicio = "nonRequired";
            }
            if (resultado[x].obligatorio === true) {
                var mostrarAlInicio = "nonRequired";
            }

            html += "<span id=" + resultado[x].id + " data-subpaso=" + resultado[x].id + " class='showOnFill " + mostrarAlInicio + " " + obligatorio + "  required ' draggable='true' ondragstart='drag(event);' onclick='modificarCampoFormulario(" + resultado[x].id + ");'>";

            html += "" + resultado[x].textoAnterior + "";

            if (resultado[x].tipoDeCampo.elementoDeEntrada === "TEXTFIELD") {
                html += "<input type='text' title='" + resultado[x].textoAyuda + "' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";' class='inputsFormulario formValues filledColor'  placeholder='" + resultado[x].placeholder + "'>";
            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "SELECT") {
                html += " <span class='inline selectWrap' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";'>";
                if (resultado[x].aplicarFiltro) {

                } else {
                    html += "<g:select title='" + resultado[x].textoAyuda + "' class='formulariOptions gray formValues' noSelection=" + resultado[x].placeholder + " />";
                }
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "DYNAMICSELECT") {
                html += "<span class='inline selectWrap' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";'>";
                html += "<select title='" + resultado[x].textoAyuda + "'  class='formulariOptions gray formValues'>";
                html += " <option value=''>Elija una opción...</option>";
                html += "<option value=''>Elija una opción...</option>";
                html += "</select>";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";

            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "FECHA") {
                html += "<span class='width70 inline selectWrap'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "' class='formulariOptions gray formValues '  from='${1..31}' noSelection='['':'Día']' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
                html += "de&nbsp;";
                html += "<span class='width70 inline selectWrap'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "' class='formulariOptions gray formValues '  from='${1..31}' noSelection='['':'Día']' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
                html += "de&nbsp;";
                html += "<span class='width70 inline selectWrap'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "'   class='formulariOptions gray formValues '  from='${1..31}' noSelection='['':'Día']' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "MESANIO") {
                html += "<span class='width70 inline selectWrap'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "' class='formulariOptions gray formValues '  from='${1..31}' noSelection='['':'Día']' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
                html += "de&nbsp;";
                html += "<span class='width70 inline selectWrap'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "'  class='formulariOptions gray formValues '  from='${1..31}' noSelection='['':'Día']' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";

            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "SERIE") {
                html += "<span class='inline selectWrap' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";'>";
                html += "<g:select title='" + resultado[x].textoAyuda + "class='formulariOptions gray formValue' from='${0..20}' />";
                html += "</span>";
                html += "<span class='afterSelect'>";
                html += "<i class='fa fa-caret-down' aria-hidden='true'></i>";
                html += "</span>";
            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "AUTOCOMPLETE") {
                html += "<input type='text' title='" + resultado[x].textoAyuda + "' style='text-align:center;' class='inputsFormulario formValues width120'  name='' id='' placeholder=" + resultado[x].placeholder + " value=''/>";
            }


            if (resultado[x].tipoDeCampo.elementoDeEntrada === "NUMERICO") {
                html += "<input type='text' title='" + resultado[x].textoAyuda + "' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";' class='inputsFormulario formValues filledColor' id='' name='' placeholder=" + resultado[x].placeholder + " onKeyPress=¿return numbersonly(this, event)'> ";
            }

            if (resultado[x].tipoDeCampo.elementoDeEntrada === "TELEFONO") {
                html += "<input type='text' title='" + resultado[x].textoAyuda + "' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";' class='inputsFormulario formValues filledColor  id='' name='' placeholder=" + resultado[x].placeholder + "  data-mask='99-99-99-99-99'>";
            }
            if (resultado[x].tipoDeCampo.elementoDeEntrada === "TEXTAREA") {
                html += "<textArea title='" + resultado[x].textoAyuda + "' style='text-align:center; width:" + resultado[x].longitudDelCampo + ";' class='inputsFormulario formValues filledColor  id='' name='' placeholder=" + resultado[x].placeholder + "></textArea>";
            }



            html += "  " + resultado[x].textoPosterior + "</span>";
            if (resultado[x].saltoDeLineaAlFinal) {
                html += "<br/>";
            }


        }
        html += "</div>";
        html += "<fieldset>";

        $('#campoFormularioAgregado').html(html);

    } else {
        html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Campos</td>";
        $('#campoFormularioAgregado').html(html);
    }
}

function mostrarRubros(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDePreguntas').val(resultado.length);
    if (resultado.length > 0) {

        for (var x = 0; x < resultado.length; x++) {
            var documentosLlenos = "redButton";
            var productosLlenos = "redButton";
            var vistasLlenas = "redButton";
            console.log(resultado[x].documentosLlenos);
            console.log(resultado[x].productosLlenos);
            console.log(resultado[x].vistasLlenas);
            if (resultado[x].documentosLlenos === true) {
                documentosLlenos = "blueButton";
            }
            if (resultado[x].productosLlenos === true) {
                productosLlenos = "blueButton";
            }
            if (resultado[x].vistasLlenas === true) {
                vistasLlenas = "blueButton";
            }
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + " - " + resultado[x].texto + "</td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' id='pasoDocumentos" + resultado[x].id + "' onclick='mostrarModal(" + '"modalDocumentos"' + "," + resultado[x].id + ", \"" + resultado[x].texto + "\"," + null + ",\"pasoDocumentos" + resultado[x].id + "\");' class='loginButton " + documentosLlenos + " letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Asociar Documentos</button></td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' id='pasoProductos" + resultado[x].id + "' onclick='mostrarModal(" + '"modalProductos"' + "," + resultado[x].id + ", \"" + resultado[x].texto + "\"," + null + ",\"pasoProductos" + resultado[x].id + "\");' class='loginButton " + productosLlenos + " letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Asociar Producto</button></td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button'id='pasoVistas" + resultado[x].id + "' onclick='mostrarModal(" + '"modalVistas"' + "," + resultado[x].id + ", \"" + resultado[x].texto + "\"," + null + ",\"pasoVistas" + resultado[x].id + "\");' class='loginButton " + vistasLlenas + " letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Asociar Vistas</button></td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarRubro(" + resultado[x].id + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";

        }
        console.log(html);
        $('#rubrosAgregados tbody').html(html);
        $('#btnVerificar').addClass('blueButton');
        $('#btnVerificar').prop("disabled", false);
        $('#btnVistaPrevia').addClass('blueButton');
        $('#btnVistaPrevia').prop("disabled", false);
        $('#nombre').val('');

    } else {
        html += "<td colspan='5' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Rubros</td>";
        $('#rubrosAgregados tbody').html(html);
    }
}



function mostrarDocumentos(data) {
    console.log(data);
    var html = ""
    var resultado = eval(data);
    $('#cantidadDeDocumentos').val(resultado.length);
    var idRubroTemp = $('#idRubroTemp').val();
    $('#documentosAgregados tbody').html("");
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + " - " + resultado[x].nombreDeDocumento + "</td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarDocumento(" + resultado[x].id + "," + idRubroTemp + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        console.log(html);
        $(".js-example-basic-multiple").select2();
        $('#documentosAgregados tbody').html(html);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#documentosAgregados tbody').html(html);
    }
}

function mostrarProductoElegido(data) {
    var html = ""
    var resultado = eval(data);
    $('.caracteristicasProducto').html("");
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<thead><tr class='letterspacing2 colorWhite'><th> PRODUCTO </th> <th> MONTO MAXIMO </th><th> MONTO MINIMO </th><th> TIPO DE INGRESOS </th> </tr></thead>";

            html += "<tr><td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].nombreDelProducto + " </td> <td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].montoMaximo + " </td> <td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].montoMinimo + "</td><td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].nombreDelIngreso + " </td></tr>";
        }
        $('.caracteristicasProducto').html(html);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#caracteristicasProducto tbody').html(html);
    }
}
function mostrarRubroElegido(data) {
    var html = ""
    var resultado = eval(data);
    $('.caracteristicasRubro').html("");
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<thead><tr class='letterspacing2 colorWhite'><th> NOMBRE DEL RUBRO </th> <th> DESCRIPCION </th></tr></thead>";

            html += "<tr><td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].texto + " </td> <td class='colorWhite letterspacing2 textUpper center'>" + resultado[x].descripcion + " </td></tr>";
        }
        $('.caracteristicasRubro').html(html);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#caracteristicasRubro').html(html);
    }
}


function mostrarVistas(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDeVistas').val(resultado.length);
    var idRubroTemp = $('#idRubroTemp').val();

    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + " - " + resultado[x].nombreDePaso + "</td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarVistas(" + resultado[x].id + "," + idRubroTemp + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        console.log(html);
        $(".js-example-basic-multiple").select2();
        $('#vistasAgregadas tbody').html(html);
        $('#btnVerificarDoc').addClass('blueButton');
        $('#btnVerificarDoc').prop("disabled", false);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#vistasAgregadas tbody').html(html);
    }
}

function mostrarProductos(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDeProductos').val(resultado.length);
    var idRubroTemp = $('.idRubroTemp').val();
    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + "  Nombre Producto - ' " + resultado[x].nombreDelProducto + "' Monto Maximo  -  " + resultado[x].montoMaximo + " Monto Minimo - " + resultado[x].montoMinimo + "  -  " + resultado[x].nombreDelIngreso + " </td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button'  id='plazosPaso'onclick='mostrarModal(" + '"modalPlazos"' + "," + idRubroTemp + ", \"" + resultado[x].nombreDelProducto + "\"," + resultado[x].id + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Asociar Plazos</button></td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button'  id='garantiasPaso'onclick='mostrarModal(" + '"modalGarantias"' + "," + idRubroTemp + ", \"" + resultado[x].nombreDelProducto + "\"," + resultado[x].id + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Asociar Garantías</button></td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarProducto(" + resultado[x].id + "," + idRubroTemp + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
            //$('#idRubroTemp').val(resultado[x].idRubroTemporal);
        }
        $('#nombreDelProducto2').val('');
        $('#productosAgregados tbody').html(html);
    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Productos</td>";
        $('#productosAgregados tbody').html(html);
    }
}

function mostrarPlazos(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDePlazos').val(resultado.length);
    var idRubroTemp = $('.idRubroTemp').val();

    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'>" + (x + 1) + "PLAZO CON --IMPORTE MAXIMO " + resultado[x].importeMaximo + " -- IMPORTE MINIMO" + resultado[x].importeMinimo + " -- PLAZO MINIMO " + resultado[x].plazoMinimo + " -- PLAZO MAXIMO " + resultado[x].plazoMaximo + " -- PLAZOS PERMITIDOS " + resultado[x].plazosPermitidos + " -- PERIODICIDAD " + resultado[x].periodicidadId + " </td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarPlazo(" + resultado[x].id + "," + resultado[x].idproductoTemp + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        $('#importeMaximo').val('');

        $('#plazosAgregados tbody').html(html);
        $('#btnVerificarDoc').addClass('blueButton');
        $('#btnVerificarDoc').prop("disabled", false);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado preguntas</td>";
        $('#plazosAgregados tbody').html(html);
    }
}

function mostrarGarantias(data) {
    var html = ""
    var resultado = eval(data);
    $('#cantidadDeGarantias').val(resultado.length);
    var idRubroTemp = $('.idRubroTemp').val();

    if (resultado.length > 0) {
        for (var x = 0; x < resultado.length; x++) {
            html += "<tr><td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper left borderGrayBottom'> GARANTÍA CON --CANTIDAD MAXIMA " + resultado[x].cantidadMaxima + " -- CANTIDAD MINIMA" + resultado[x].cantidadMinima + " -- DESCRIPCIÓN " + resultado[x].descripcion + " -- TIPO DE GARANTÍA " + resultado[x].tipoDeGarantiaNombre + " </td>";
            html += "<td class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>";
            html += "<button type='button' onclick='eliminarGarantia(" + resultado[x].id + "," + resultado[x].idproductoTemp + ");' class='loginButton redButton letterspacing2 font14 pointer' style='width: 100%;height: 33px;margin-bottom: 15px;' >Eliminar</button></td></tr>";
        }
        $('#cantidadMaxima').val('');
        $('#garantiasAgregadas tbody').html(html);
        $('#btnVerificarDoc').addClass('blueButton');
        $('#btnVerificarDoc').prop("disabled", false);

    } else {
        html += "<td colspan='4' class='tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom'>No se han agregado Garantías</td>";
        $('#garantiasAgregadas tbody').html(html);
    }
}


function iniciarPrettyPhoto() {
    $("a[rel^='prettyPhoto[pp_gal]']").prettyPhoto();
}

function pagination(totalPages, page, idPaginacion) {
    if (totalPages > 1) {
        $('#' + idPaginacion).empty();
        $('#' + idPaginacion).append(function () {
            var li = $('<li class="floatLeft">');
            li.append(function () {
                var a = $('<a class="font14 fontWeight600 displayInline pageMarker">');
                if (page !== 1) {
                    a.addClass('page');
                }
                a.attr('title', "Anterior");
                a.attr('data-page', page - 1);
                a.attr('href', '#').append('<i class="fa fa-angle-left" aria-hidden="true"></i>');

                return a;
            });
            if (page === 1) {
                li.addClass('disabled');
            }
            return li;
        });

        var paginationLength = 6;
        var leftValue = 3;
        var middleValue = leftValue + 1;
        var rightValue = 2;

        if (totalPages <= paginationLength || page <= middleValue) {
            var previousPages = page - (page - 1);
            var followingPages = (totalPages > paginationLength) ? paginationLength : totalPages;
            for (var i = previousPages; i <= followingPages; i++) {
                buildPagination(i, page, idPaginacion);
            }
        } else if (page > middleValue && page <= (totalPages - rightValue)) {
            var previousPages = page - leftValue;
            var followingPages = page + rightValue;
            for (var i = previousPages; i <= followingPages; i++) {
                buildPagination(i, page, idPaginacion);
            }
        } else if (page > (totalPages - rightValue)) {
            var previousPages = page - (paginationLength - ((totalPages - page) + 1));
            for (var i = previousPages; i <= totalPages; i++) {
                buildPagination(i, page, idPaginacion);
            }
        }
        $('#' + idPaginacion).append(function () {
            var li = $('<li class="floatLeft">');
            li.append(function () {
                var a = $('<a class="font14 fontWeight600 displayInline pageMarker">');
                if (page !== (totalPages)) {
                    a.addClass('page');
                }
                a.attr('title', "Siguiente");
                a.attr('data-page', page + 1);
                a.attr('href', '#').append('<i class="fa fa-angle-right" aria-hidden="true"></i>');

                return a;
            });
            if (page === (totalPages)) {
                li.addClass('disabled');
            }
            return li;
        });
    }
}

function buildPagination(i, page, idPaginacion) {
    $('#' + idPaginacion).append(function () {
        var li = $('<li class="floatLeft">');
        li.append(function () {
            var a = $('<a class="font14 fontWeight400 displayInline pageMarker">');
            if (i === page) {
                a.addClass('pageSelected');
            } else {
                a.addClass('page');
            }
            a.attr('title', "Página " + i);
            a.attr('data-page', i);
            return a.attr('href', '#').append(i);
        });

        return li;
    });
}

function getSolicitudes(page, idPaginacion) {
    $("#currentPageSolicitudes").val(page);
    var filter = new Object();
    filter.page = page;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getSolicitudes,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#' + idPaginacion).empty();
            if (response.solicitudes.length > 0) {
                console.log("Si entra a la funcion, osea que si trae solicitudes....");
                pagination(totalPages, page, idPaginacion);
                mostrarSolicitudesPaginados(response.solicitudes);
            } else {
                console.log("No entra a la funcion, osea que no trae solicitudes....");
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Solicitudes Registradas</span>';
                row += '</td>';
                row += '</tr>';
                $("#listaDeSolicitudes").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Ocurrio error");
        }
    });
}


function getSolicitudesBusqueda(page, idPaginacion, folio, nombre, apellidoPaterno, apellidoMaterno, rfc) {
    $("#currentPageSolicitudes").val(page);
    var filter = new Object();
    filter.page = page;
    filter.folio = folio;
    filter.nombre = nombre;
    filter.apellidoPaterno = apellidoPaterno;
    filter.apellidoMaterno = apellidoMaterno;
    filter.rfc = rfc;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: '/dashboard/busquedaCriterio',
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            console.log("dentro del ajax");
            console.log(response);
            var page = response.page;
            var totalPages = response.totalPages;
            $('#' + idPaginacion).empty();
            if (response.solicitudes.length > 0) {
                pagination(totalPages, page, idPaginacion);
                mostrarSolicitudesPaginadosBusqueda(response.solicitudes);
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Solicitudes Registradas</span>';
                row += '</td>';
                row += '</tr>';
                $("#listaDeSolicitudesBusqueda").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {



        }
    });
}


function mostrarSolicitudesPaginados(data) {
    $('#listaDeSolicitudes tbody').html('');
    console.log(data);
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        if (respuesta[x].puntoDeVenta === null) {
            respuesta[x].puntoDeVenta = "";
        }
        html += "<tr>";
        html += "<td  class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "folio <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].folio + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "cliente <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].nombreCliente + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "STATUS <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].statusDeSolicitud + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PDV <br>";

        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].puntoDeVenta + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FUENTE <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].autenticadoMediante + "</span>";
        html += "</td>";
        html += "<td class='left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PRODUCTO <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].producto.nombreDelProducto + "</span>";
        html += "</td>";

        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FECHA <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + $.format.date(respuesta[x].fechaDeSolicitud, "dd/MM/yyyy HH:mm") + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "MONTO <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>$" + respuesta[x].montoCredito.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + "</span>";
        html += "</td>";
        html += "<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>";
        if (respuesta[x].folio && respuesta[x].folio !== "-") {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button' onclick='consultarSolicitud(" + respuesta[x].solicitud.id + ");'>ver detalle</button>";
            html += "</td>";
        } else {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button'>ver detalle</button>";
            html += "</td>";
        }
        html += "</sec:ifAnyGranted>";
        html += "</tr>";
    }
    $('#listaDeSolicitudes tbody').html(html);

}

function mostrarSolicitudesFechas(data, idTabla) {
    console.log(idTabla);
    $('#' + idTabla + 'tbody').html('');
    //$('#lista1').html('');
    $(window).scrollTop($('#' + idTabla).position().top);

    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        if (respuesta[x].puntoDeVenta === null) {
            respuesta[x].puntoDeVenta = "";
        }
        html += "<tr>";
        html += "<td  class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "folio <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].folio + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "cliente <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].nombreCliente + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "STATUS <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].statusDeSolicitud + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PDV <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].puntoDeVenta + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FUENTE <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].autenticadoMediante + "</span>";
        html += "</td>";
        html += "<td class='left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PRODUCTO <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].producto.nombreDelProducto + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FECHA <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + $.format.date(respuesta[x].fechaDeSolicitud, "dd/MM/yyyy HH:mm") + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "MONTO <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>$" + respuesta[x].montoCredito.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + "</span>";
        html += "</td>";
        html += "<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>";
        if (respuesta[x].folio && respuesta[x].folio != "-") {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button' onclick='consultarSolicitud(" + respuesta[x].solicitud.id + ");'>ver detalle</button>";
            html += "</td>";
        } else {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button'>ver detalle</button>";
            html += "</td>";
        }
        html += "</sec:ifAnyGranted>";
        html += "</tr>";
    }
    $('#' + idTabla + ' tbody').html(html);


}

function mostrarSolicitudesPaginadosBusqueda(data) {
    $('#listaDeSolicitudesBusqueda tbody').html('');
    $('#lista1').html('');
    var respuesta = eval(data);
    var html = "";
    for (var x = 0; x < respuesta.length; x++) {
        if (respuesta[x].puntoDeVenta === null) {
            respuesta[x].puntoDeVenta = "";
        }
        html += "<tr>";
        html += "<td  class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "folio <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].folio + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "cliente <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].nombreCliente + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "STATUS <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].statusDeSolicitud + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PDV <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].puntoDeVenta + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FUENTE <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].autenticadoMediante + "</span>";
        html += "</td>";
        html += "<td class='left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "PRODUCTO <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + respuesta[x].producto.nombreDelProducto + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "FECHA <br>";
        html += "<span class='font14 textlower tableDescriptionColor'>" + $.format.date(respuesta[x].fechaDeSolicitud, "dd/MM/yyyy HH:mm") + "</span>";
        html += "</td>";
        html += "<td class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
        html += "MONTO <br>";

        html += "<span class='font14 textlower tableDescriptionColor'>$" + respuesta[x].montoCredito.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + " </span>";
        html += "</td>";
        html += "<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>";
        if (respuesta[x].folio && respuesta[x].folio != "-") {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button' onclick='consultarSolicitud(" + respuesta[x].solicitud.id + ");'>ver detalle</button>";
            html += "</td>";
        } else {
            html += "<td class='center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
            html += "<button class='greenBox colorWhite' type='button'>ver detalle</button>";
            html += "</td>";
        }
        html += "</sec:ifAnyGranted>";
        html += "</tr>";
    }
    $('#listaDeSolicitudesBusqueda tbody').html(html);

}


function configurarPasoSolicitud(idPasoSolicitud) {
    $.ajax({
        type: 'POST',
        data: 'idPasoSolicitud=' + idPasoSolicitud,
        url: '/dashboard/buscarPaso',
        success: function (data, textStatus) {
            var respuesta = eval(respuesta);
            if (data.ok) {
                sweetAlert("ALERTA", data.mensaje, "error");
            } else if (!data.ok) {
                $('#listaPasosSolicitud').fadeOut();
                $('#elRender').html(data);
                $('#elRender').fadeIn();
                $(".js-example-basic-multiple").select2();
                $("#paginationPasosSolicitud").fadeOut();
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });

}

function validUsername(callback) {
    var usuario = new Object();
    usuario.id = $("#userId").val().trim();
    usuario.username = $("#username").val().trim();

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.validUsername,
        data: JSON.stringify(usuario),
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

function validEmail(callback) {
    var usuario = new Object();
    usuario.id = $("#userId").val().trim();
    usuario.email = $("#email").val().trim();

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.validEmail,
        data: JSON.stringify(usuario),
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

function getProfilePicture() {
    $.ajax({
        type: "POST",
        url: $.getProfilePicture,
        contentType: "application/json",
        success: function (response) {
            if(!response.empty) {
                $("#bannerProfilePicture").attr("src", "data:image/" + response.type + ";base64," + response.base64);
                $("#profilePicture").attr("src", "data:image/" + response.type + ";base64," + response.base64);
                $("#deleteProfilePicture-btn").css("display", "block");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            sweetAlert("Oops...", "Algo salió mal con tu foto de perfil, intenta nuevamente en unos minutos.", "error");
        }
    });
}
function atras() {
    $('#elRender').fadeOut();
    $('#listaPasosSolicitud').fadeIn();
    $("#paginationPasosSolicitud").fadeIn();

}
function expander(idDivIzquierdo, idDivDerecho) {

    if ($('#' + idDivIzquierdo).hasClass('hide')) {
        $('#' + idDivIzquierdo).removeClass('hide');
        $('#' + idDivDerecho).removeClass('containerDrag paddingTop20 paddingBottom20 clearFix contentHeight serializeForm');
        $('#' + idDivDerecho).addClass('col8 col8-tab col12-mob floatLeft clearFix solicitudWhiteBox');

    } else {
        $('#' + idDivIzquierdo).addClass('hide');
        $('#' + idDivDerecho).removeClass('col8 col8-tab col12-mob floatLeft clearFix solicitudWhiteBox');
        $('#' + idDivDerecho).addClass('containerDrag paddingTop20 paddingBottom20 clearFix contentHeight serializeForm');
    }

}
function registrarConfiguracionPasoSolicitud(idPasoSolicitudEntidadFinanciera) {

    jQuery.ajax({
        type: 'POST',
        data: {idPasoSolicitudEntidadFinanciera: idPasoSolicitudEntidadFinanciera},
        url: '/dashboard/registrarConfiguracionPasoSolicitud',
        success: function (data, textStatus) {
            var respuesta = eval(data);
            if (respuesta.ok) {
                var html = "";
                //cerrarModal('modalPasosCotizador');
                sweetAlert("¡Excelente!", respuesta.mensaje, "success");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}





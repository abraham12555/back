$(document).ready(function () {
    var opcion = $('#opcionMenu').val();
    $('.elementoMenuPrincipal').removeClass('blueButton');
    $('#principalOpc' + opcion).addClass('blueButton');
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

function mostrarModal(idModal){
    $('#' + idModal).fadeIn();
}

function cerrarModal(idModal){
    $('#' + idModal).fadeOut();
}

function cambiarEstatus(estatus){
    
}
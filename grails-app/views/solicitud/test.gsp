<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="solicitud"/>
        <title>Kosmos - Formulario</title>
    </head>
    <body>
        <input type="hidden" id="tipoDeDocumento">
        <input type="hidden" id="pasoInicial" value="${pasoActual?.numeroDePaso}">
        <div id="pasoActual">
            <g:render template="${pasoActual?.tipoDePaso?.nombre}"/>
        </div>
        <g:render template="modalIdentificaciones"/>
        <g:render template="modalComprobantes"/>
        <script>
            $(document).ready(function() {
        if("${tipoLogin}" == 'FB'){
            fillFB("${datosLogin.encodeAsJSON()}");
        }else if("${tipoLogin}" == 'Google'){
            fillGoogle("${datosLogin.encodeAsJSON()}");
            }
            console.log("Iniciando carga de ponderaciones");
    cargarPonderaciones('${raw(ponderaciones as String)}');
            console.log("Iniciando funciones...");
            inicializarFormulario();
            });
        </script>
    </body>
</html>
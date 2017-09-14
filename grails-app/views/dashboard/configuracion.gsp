<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <g:urlContextAware value="/" var="urlContextAware"/>
        <script type="text/javascript">
            $.contextAwarePathJS = "${urlContextAware}";
        </script>
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR,ROLE_MERCADOTECNIA'>
        <g:external dir="js" file="dashboard/notificaciones/notificaciones.min.js" />
        <g:external dir="js" file="dashboard/notificaciones/notificacionesEmail.min.js" />
        <g:external dir="js" file="dashboard/notificaciones/envioNotificaciones.min.js" />
        <g:external dir="js" file="jasny-bootstrap.min.js" />
        <g:external dir="js" file="jquery.ui.timepicker.js" />
        <g:external dir="css" file="jquery.ui.timepicker.css" />
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
        <g:external dir="js" file="validaciones.min.js" />
        <g:external dir="js" file="dashboard/usuarios/usuarios.min.js" />
    </sec:ifAnyGranted>
    <title>Sample title</title>
</head>
<body>
    <input type="hidden" id="opcionMenu" value="6">
    <section class="container marginBottom28">
        <li class="  floatLeft paddingLeft5 paddingRight5">
            <a title="CONFIGURACIÓN" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20 paddingRight20 pointer">CONFIGURACIÓN</a>
        </li>
    </section>
    <section class="regular slider container marginBottom28">
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div>
                <li id="usuariosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','usuarios');" title="USUARIOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Usuarios</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR,ROLE_MERCADOTECNIA'>
            <div>
                <li id="notificacionesButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','notificaciones');" title="NOTIFICACIONES" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">Notificaciones</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="configuracionPasosSolicitudButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','configuracionPasosSolicitud');" title="SOLICITUD" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Solicitud</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="usuariosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','configuracionPasosCotizador');" title="COTIZADOR" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Cotizador </a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO, ROLE_DIRECTOR'>
            <div>
                <li id="perfilDeMarcaButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','perfilDeMarca');" title="PERFIL DE MARCA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Perfil de Marca</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','configuracionBuroCredito');"  title="CONFIGURACIÓN BURÓ DE CRÉDITO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Buró de Crédito</a>
                </li>      
            </div>
        </sec:ifAnyGranted>   
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS, ROLE_DIRECTOR'>
            <div>
                <li id="variablesButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','variables');"  title="VARIABLES" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Variables</a>
                </li>
            </div>
        </sec:ifAnyGranted>    
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="seguroButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','seguro');"  title="SEGURO SOBRE DEUDA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Seguros Sobre Deuda </a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="servicioDeAsistenciaButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','servicioDeAsistencia');"  title="SERVICIO DE ASISTENCIA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Servicio de Asistencia </a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','productos');"  title="PRODUCTOS" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Producto </a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div>
                <li id="rubroDeAplicacionDeCreditoButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','rubroDeAplicacionDeCredito');"  title="RUBRO DE APLICACIÓN DE CRÉDITO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Rubro de Aplicación de Crédito</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeAsentamiento');"  title="TIPO DE ASENTAMIENTO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Asentamiento</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeDocumento');"  title="TIPO DE DOCUMENTO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Documento</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeVivienda');"  title="TIPO DE VIVIENDA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Vivienda</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeTasaDeInteres');"  title="TIPO DE TASA DE INTERES" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Tasa de Interes</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeCampo');"  title="TIPO DE CAMPO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo Campo</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeContrato');"  title="TIPO DE CONTRATO" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Contrato</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeFotografia');"  title="TIPO DE FOTOGRAFÍA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo Fotografía</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeGarantia');"  title="TIPO DE GARANTÍA" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Garantía</a>
                </li>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_ADMINISTRADOR'>
            <div>
                <li id="productosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                    <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','tipoDeIngreso');"  title="TIPO DE INGRESOS" class="displayInline font20 fontWeight500 darkBluetitle padding20  pointer">Tipo de Ingresos</a>
                </li>
            </div>
        </sec:ifAnyGranted>
    </section>



    <section class="container marginBottom28">
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
                <div id="usuariosSubMenu" class="configuracionSubMenu lightGrayBG">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Nuevo usuario" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer" id="newUser-btn">NUEVO USUARIO</a>
                        </li>
                        <li class="floatLeft">
                            <a title="Descargar lista" class="displayInline font14 gray2 paddingTop10 paddingBottom10 paddingRight20 pointer" href="${createLink(controller: 'dashboard', action:'downloadUserList')}">DESCARGAR LISTA DE USUARIOS</a>
                        </li>
                    </ul>
                </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR,ROLE_DISENO,ROLE_DIRECTOR'>
            <div id="perfilDeMarcaSubMenu" class="configuracionSubMenu lightGrayBG" style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10  paddingRight20 pointer">GUARDAR CAMBIOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer">VISTA PREVIA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO, ROLE_DIRECTOR'>
            <div id="configuracionPasosSolicitudSubMenu" class="configuracionSubMenu lightGrayBG" style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" onclick="openModal('modalAltaPasosSolicitud');" class="displayInline font14 greenTitle paddingTop10 paddingBottom10  paddingRight20 pointer">NUEVO PASO SOLICITUD </a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS, ROLE_DIRECTOR'>
            <div id="variablesSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">GUARDAR CAMBIOS</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div id="seguroSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE SEGUROS SOBRE DEUDA</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaSeguroSobreDeuda');">NUEVO SEGURO SOBRE DEUDA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div id="servicioDeAsistenciaSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE SEGUROS DE ASISTENCIA</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaServicioDeAsistencia');">NUEVO SEGURO DE ASISTENCIA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div id="rubroDeAplicacionDeCreditoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA RUBROS DE APLICACIÓN DE CRÉDITO</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaRubroDeAplicacionDeCredito');">NUEVO RUBRO DE APLICACIÓN DE CRÉDITO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div id="productosSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE PRODUCTOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaProducto');">NUEVO PRODUCTO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeDocumentoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE DOCUMENTOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeDocumento');">NUEVO TIPO DE DOCUMENTO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeAsentamientoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE ASENTAMIENTOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeAsentamiento');">NUEVO TIPO DE ASENTAMIENTO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeViviendaSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE VIVIENDA</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeVivienda');">NUEVO TIPO DE VIVIENDA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeCampoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE CAMPOS </a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeCampo');">NUEVO TIPO DE CAMPO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeTasaDeInteresSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE TASA DE INTERES</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeTasaDeInteres');">NUEVO TIPO DE TASA DE INTERES</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <div id="configuracionPasosCotizadorSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE PASOS COTIZADOR</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaPasosCotizador');">NUEVO PASO EN COTIZADOR</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer" onclick="habilitarGuia();" id="primerPaso" >CONFIGURAR COTIZADOR</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>

        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeContratoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE CONTRATOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeContrato');">NUEVO TIPO DE CONTRATO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeFotografiaSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE FOTOGRAFÍA</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeFotografia');">NUEVO TIPO DE FOTOGRAFÍA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeGarantiaSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE GARANTÍA</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeGarantia');">NUEVO TIPO DE GARANTÍA</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
            <div id="tipoDeIngresoSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE TIPO DE INGRESOS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer"onclick="openModal('modalAltaTipoDeIngresos');">NUEVO TIPO DE INGRESOS</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR,ROLE_MERCADOTECNIA'>
            <div id="notificacionesSubMenu" class="configuracionSubMenu lightGrayBG" style="display: none;">
                <ul class="clearFix paddingLeft30">
                    <li class="floatLeft">
                        <a title="Plantillas SMS" class="displayInline font14 paddingTop10 paddingBottom10 paddingRight20 pointer" onclick="mostrarApartadoSubmenu(this);" data-id="notificaciones">PLANTILLAS SMS</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Plantillas EMAIL" class="displayInline font14 paddingTop10 paddingBottom10 paddingRight20 pointer" onclick="mostrarApartadoSubmenu(this);" data-id="notificacionesEmail">PLANTILLAS EMAIL</a>
                    </li>
                    <li class="floatLeft">
                        <a title="Envío" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer" onclick="mostrarApartadoSubmenu(this);" data-id="envioNotificaciones">ENVÍO</a>
                    </li>
                </ul>
            </div>
        </sec:ifAnyGranted>
    </section>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="usuarios">
        <g:render template="usuarios"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="configuracionPasosCotizador" style="display: none;" >
        <g:render template="configurarPasosCotizador"/>
    </div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="configuracionPasosSolicitud" style="display: none;">
        <g:render template="configurarPasosSolicitud"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="rubroDeAplicacionDeCredito" style="display: none;">
        <g:render template="rubroDeAplicacionDeCredito"/>
    </div>
</sec:ifAnyGranted>


<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="seguro" style="display: none;">
        <g:render template="seguroSobreDeuda"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="servicioDeAsistencia" style="display: none;">
        <g:render template="servicioDeAsistencia"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="perfilDeMarca" style="display: none;">
        <g:render template="perfilDeMarca"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="variables" style="display: none;">
        <g:render template="variables"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="productos" style="display: none;">
        <g:render template="productos"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIRECTOR,ROLE_MERCADOTECNIA'>
    <div class="configuracionTab" id="notificaciones" style="display: none;">
        <g:render template="configuracion/notificaciones/notificaciones"/>
    </div>
    <div class="configuracionTab" id="notificacionesEmail" style="display: none;">
        <g:render template="configuracion/notificaciones/notificacionesEmail"/>
    </div>
    <div class="configuracionTab" id="envioNotificaciones" style="display: none;">
        <g:render template="configuracion/notificaciones/envioNotificaciones"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <div class="configuracionTab" id="configuracionBuroCredito" style="display: none;">
        <g:render template="configuracionBuroCredito"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeDocumento" style="display: none;">
        <g:render template="tipoDeDocumento"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeAsentamiento" style="display: none;">
        <g:render template="tipoDeAsentamiento"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeVivienda" style="display: none;">
        <g:render template="tipoDeVivienda"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeCampo" style="display: none;">
        <g:render template="tipoDeCampo"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeContrato" style="display: none;">
        <g:render template="tipoDeContrato"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeFotografia" style="display: none;">
        <g:render template="tipoDeFotografia"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeTasaDeInteres" style="display: none;">
        <g:render template="tipoDeTasaDeInteres"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeGarantia" style="display: none;">
        <g:render template="tipoDeGarantia"/>
    </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
    <div class="configuracionTab" id="tipoDeIngreso" style="display: none;">
        <g:render template="tipoDeIngresos"/>
    </div>
</sec:ifAnyGranted>


</body>
</html>
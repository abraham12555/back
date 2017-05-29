<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="6">
        <section class="container marginBottom28">
            <div class="width998 solicitudBox autoMargin radius2">
                <div class="">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a title="CONFIGURACIÓN" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20 paddingRight20 pointer">CONFIGURACIÓN</a>
                        </li>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
                            <li id="usuariosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','usuarios');" title="USUARIOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">USUARIOS</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO'>
                            <li id="perfilDeMarcaButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','perfilDeMarca');" title="PERFIL DE MARCA" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">PERFIL DE MARCA</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
                            <li id="productosButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','configuracionBuroCredito');"  title="CONFIGURACIÓN BURÓ DE CRÉDITO" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">BURÓ DE CRÉDITO</a>
                            </li>                           
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS'>
                            <li id="variablesButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','variables');"  title="VARIABLES" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">VARIABLES</a>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
                            <li id="productosButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                                <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','productos');"  title="PRODUCTOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">PRODUCTOS</a>
                            </li>
                        </sec:ifAnyGranted>
                    </ul>
                </div>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
                    <div id="usuariosSubMenu" class="configuracionSubMenu lightGrayBG">
                        <ul class="clearFix paddingLeft30">
                            <li class="floatLeft">
                                <a title="Reportar contratiempo" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer" onclick="openModal('modalAltaDeUsuario');">NUEVO USUARIO</a>
                            </li>
                        </ul>
                    </div>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO'>
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
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS'>
                    <div id="variablesSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                        <ul class="clearFix paddingLeft30">
                            <li class="floatLeft">
                                <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">GUARDAR CAMBIOS</a>
                            </li>
                        </ul>
                    </div>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
                    <div id="productosSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                        <ul class="clearFix paddingLeft30">
                            <li class="floatLeft">
                                <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">VER LISTA DE PRODUCTOS</a>
                            </li>
                            <li class="floatLeft">
                                <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer">NUEVO PRODUCTO</a>
                            </li>
                        </ul>
                    </div>
                </sec:ifAnyGranted>
            </div>
        </section>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
        <div class="configuracionTab" id="usuarios">
            <g:render template="usuarios"/>
        </div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DISENO'>
        <div class="configuracionTab" id="perfilDeMarca" style="display: none;">
            <g:render template="perfilDeMarca"/>
        </div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_RIESGOS'>
        <div class="configuracionTab" id="variables" style="display: none;">
            <g:render template="variables"/>
        </div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
        <div class="configuracionTab" id="productos" style="display: none;">
            <g:render template="productos"/>
        </div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR'>
        <div class="configuracionTab" id="configuracionBuroCredito" style="display: none;">
            <g:render template="configuracionBuroCredito"/>
        </div>
    </sec:ifAnyGranted>
    
    
</body>
</html>

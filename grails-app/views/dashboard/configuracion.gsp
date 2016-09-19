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
        <input type="hidden" id="opcionMenu" value="5">
        <section class="container marginBottom28">
            <div class="width998 solicitudBox autoMargin radius2">
                <div class="">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a title="CONFIGURACIÓN" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20 paddingRight20 pointer">CONFIGURACIÓN</a>
                        </li>
                        <li id="usuariosButton" class="opcConfiguracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','usuarios');" title="USUARIOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">USUARIOS</a>
                        </li>
                        <li id="perfilDeMarcaButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','perfilDeMarca');" title="PERFIL DE MARCA" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">PERFIL DE MARCA</a>
                        </li>
                        <li id="variablesButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','variables');"  title="VARIABLES" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">VARIABLES</a>
                        </li>
                        <li id="productosButton" class="opcConfiguracion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcConfiguracion','configuracionTab','productos');"  title="PRODUCTOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">PRODUCTOS</a>
                        </li>
                    </ul>
                </div>
                <div id="usuariosSubMenu" class="configuracionSubMenu lightGrayBG">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer">NUEVO USUARIO</a>
                        </li>
                    </ul>
                </div>
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
                <div id="variablesSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">GUARDAR CAMBIOS</a>
                        </li>
                    </ul>
                </div>
                <div id="productosSubMenu" class="configuracionSubMenu lightGrayBG"  style="display: none;">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10 paddingRight20 pointer">GUARDAR CAMBIOS</a>
                        </li>
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 gray paddingTop10 paddingBottom10 paddingRight20 pointer">NUEVO PRODUCTO</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="configuracionTab" id="usuarios">
            <g:render template="usuarios"/>
        </div>
        <div class="configuracionTab" id="perfilDeMarca" style="display: none;">
            <g:render template="perfilDeMarca"/>
        </div>
        <div class="configuracionTab" id="variables" style="display: none;">
            <g:render template="variables"/>
        </div>
        <div class="configuracionTab" id="productos" style="display: none;">
            <g:render template="productos"/>
        </div>
    </body>
</html>

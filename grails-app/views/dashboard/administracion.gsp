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
                            <a title="ADMINISTRACIÓN" class="displayInline font24 fontWeight700 darkBluetitle paddingTop17 paddingBottom17 paddingLeft20 paddingRight20 pointer">ADMINISTRACIÓN</a>
                        </li>
                        <li id="entidadesButton" class="opcAdministracion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcAdministracion','administracionTab','entidades');" title="ENTIDADES FINANCIERAS" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">ENTIDADES FINANCIERAS</a>
                        </li>
                        <li id="catalogosButton" class="opcAdministracion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcAdministracion','administracionTab','catalogos');" title="CATÁLOGOS" class="displayInline font20 fontWeight500 darkBluetitle padding20 opacity05 pointer">CATÁLOGOS</a>
                        </li>
                    </ul>
                </div>
                <div id="entidadesSubMenu" class="administracionSubMenu lightGrayBG">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Nueva Entidad Financiera" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer" onclick="mostrarModal('nuevaEntidad');">NUEVA ENTIDAD FINANCIERA</a>
                        </li>
                    </ul>
                </div>
                <div id="catalogosSubMenu" class="administracionSubMenu lightGrayBG" style="display: none;">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 greenTitle paddingTop10 paddingBottom10  paddingRight20 pointer">GUARDAR CAMBIOS</a>
                        </li>
                        <li class="floatLeft">
                            <a title="Reportar contratiempo" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer">VISTA PREVIA</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="administracionTab" id="entidades">
            <g:render template="administracion/entidades"/>
        </div>
        <div class="administracionTab" id="catalogos" style="display: none;">
            <g:render template="administracion/catalogos"/>
        </div>
        <div class="solicitudLightbox hide" id="nuevaEntidad">
            <g:render template="administracion/entidadFinanciera/nuevaEntidad"/>
        </div>
    </body>
</html>

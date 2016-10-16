<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="dashboard"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <input type="hidden" id="opcionMenu" value="2">
        <section class="container marginBottom28">
            <div class="width990 solicitudBox autoMargin">
                <div class="">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="SOLICITUDES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop20 paddingBottom15 paddingLeft20 paddingRight20">SOLICITUDES</a>
                        </li>
                        <li id="dictaminadasButton" class="opcSolicitudes floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcSolicitudes','solicitudesTab','dictaminadas');" title="Ver Solicitudes Dictaminadas"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">DICTAMINADAS</a>
                        </li>
                        <li id="noDictaminadasButton" class="opcSolicitudes floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcSolicitudes','solicitudesTab','noDictaminadas');" title="Ver Solicitudes por Dictaminar" class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20 pointer">POR DICTAMINAR</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG">
                    <ul class="clearFix paddingLeft30 solicitude_submenu">
                        <li class="floatLeft">
                            <a id="subMenuOpc1" onclick="listarSolicitudesPor(1);" title="Día" class="elementoSubMenu displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc7" onclick="listarSolicitudesPor(7);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc31" onclick="listarSolicitudesPor(31);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc365" onclick="listarSolicitudesPor(365);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="listarSolicitudesPor(5);" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="solicitudesTab" id="dictaminadas">
            <g:render template="solicitudesDictaminadas"/>
        </div>
        <div class="solicitudesTab" id="noDictaminadas" style="display: none;">
            <g:render template="solicitudesNoDictaminadas"/>
        </div>
    </body>
</html>

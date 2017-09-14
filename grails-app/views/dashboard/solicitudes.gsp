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
                        <li id="complementoSolicitadoButton" class="opcSolicitudes floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcSolicitudes','solicitudesTab','complementoSolicitado');" title="Ver Solicitudes con Complemento Solicitado" class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20 pointer">COMPLEMENTO SOLICITADO</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG">
                    <ul class="clearFix paddingLeft30 solicitude_submenu">
                        <li class="floatLeft">
                            <a id="subMenuOpc1" onclick="listarSolicitudesPor(1,'rangoDeFechas',null,null);" title="Día" class="elementoSubMenu displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc7" onclick="listarSolicitudesPor(7,'rangoDeFechas',null,null);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc31" onclick="listarSolicitudesPor(31,'rangoDeFechas',null,null);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc365" onclick="listarSolicitudesPor(365,'rangoDeFechas',null,null);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="listarSolicitudesPor(5,'rangoDeFechas','from','to');" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <section class="container marginBottom28" id="rangoDeFechas" style="display: none;">
            <div class="width990 solicitudBox autoMargin" style="height: 10%;">
                <div class="lightGrayBG" style="height: 100%;">
                    <div class="col12 floatLeft" style="padding: 15px;">
                        <div class="col4 floatLeft">
                            <div class="col3 floatLeft">
                                <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>Desde</strong></p>
                            </div>
                            <div class="col9 floatLeft">
                                <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="from" name="from">
                            </div>
                        </div>
                        <div class="col4 floatLeft">
                            <div class="col3 floatLeft">
                                <p class="gray font14 fontWeight500 letterspacing1 marginLeft20" style="padding: 5px;"><strong>a</strong></p>
                            </div>
                            <div class="col9 floatLeft">
                                <input class="block cameraBox col11 height30" style="text-align: center;" readonly type="text" id="to" name="to">
                            </div>
                        </div>
                        <div class="col4 floatLeft">
                            <center>
                                <li>
                                    <a onclick="listarSolicitudesPor(0,null,null,null);" title="Buscar" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Buscar</a>
                                </li>
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="solicitudesTab" id="dictaminadas">
            <g:render template="solicitudesDictaminadas"/>
        </div>
        <div class="solicitudesTab" id="noDictaminadas" style="display: none;">
            <g:render template="solicitudesNoDictaminadas"/>
        </div>
        <div class="solicitudesTab" id="complementoSolicitado" style="display: none;">
            <g:render template="solicitudesConComplementoSolicitado"/>
        </div>
    </body>
</html>

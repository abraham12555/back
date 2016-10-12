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
                            <a id="subMenuOpc2" onclick="listarSolicitudesPor(2);" title="Semana" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc3" onclick="listarSolicitudesPor(3);" title="Mes" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc4" onclick="listarSolicitudesPor(4);" title="Año" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a id="subMenuOpc5" onclick="listarSolicitudesPor(5);" title="Por Fecha" class="elementoSubMenu displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <div class="solicitudesTab" id="dictaminadas">
            <section class="container marginBottom50 ">

                <table class="applicationContainers solicitudes_table width990 autoMargin">
                    <thead>
                    <th colspan="9" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes dictaminadas</h1></th>
                    </thead>
                    <tr class="lightGrayBG">
                        <td class="gray left">FOLIO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">CLIENTE <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">ESTATUS <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">PDV <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">FUENTE <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">PRODUCTO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="grayleft">FECHA <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="grayleft">MONTO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray"></td>
                    </tr>
                    <g:if test="${solicitudesDictaminadas}">
                        <g:each var='solicitud' in='${solicitudesDictaminadas}'>
                            <tr>
                                <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    folio <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.folio}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    cliente <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.nombreCliente}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    ESTATUS <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.statusDeSolicitud}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    PDV <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.puntoDeVenta}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    FUENTE <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.autenticadoMediante}</span>
                                </td>
                                <td class="left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    PRODUCTO <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.producto}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    FECHA <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.fechaDeSolicitud}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    MONTO <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.montoCredito}</span>
                                </td>
                                <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(${solicitud.id});">ver detalle</button>
                                </td>
                            </tr>
                        </g:each>
                    </g:if>
                    <g:else>
                        <tr>
                            <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                <span class="font14 textlower tableDescriptionColor">No hay solicitudes dictaminadas</span>
                            </td>
                        </tr>
                    </g:else>
                </table>
            </section>
            <section class="container marginBottom50 marginTop50">
                <div class="width480 autoMargin solicitudBox">
                    <div class="autoMargin">
                        <ul class="clearFix">
                            <li class="floatLeft">
                                <a href="#" title="Previous Page" class="font14 fontWeight600 displayInline pageMarker">
                                    <i class="fa fa-angle-left" aria-hidden="true"></i>
                                </a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 1" class="font14 fontWeight400 displayInline pageMarker pageSelected">1</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 2" class="font14 fontWeight400 displayInline pageMarker">2</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 3" class="font14 fontWeight400 displayInline pageMarker">3</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 4" class="font14 fontWeight400 displayInline pageMarker">4</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="..." class="font14 fontWeight400 displayInline pageMarker">...</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 12" class="font14 fontWeight400 displayInline pageMarker">12</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Next Page" class="font14 fontWeight600 displayInline pageMarker">
                                    <i class="fa fa-angle-right" aria-hidden="true"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
        <div class="solicitudesTab" id="noDictaminadas" style="display: none;">
            <section class="container marginBottom50 ">

                <table class="applicationContainers solicitudes_table width990 autoMargin">
                    <thead>
                    <th colspan="9" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes por dictaminar</h1></th>
                    </thead>
                    <tr class="lightGrayBG">
                        <td class="gray left">FOLIO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">CLIENTE <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">ESTATUS <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">PDV <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">FUENTE <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray left">PRODUCTO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="grayleft">FECHA <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="grayleft">MONTO <i class="fa fa-caret-down" aria-hidden="true"></i></td>
                        <td class="gray"></td>
                    </tr>
                    <g:if test="${solicitudesNoDictaminadas}">
                        <g:each var='solicitud' in='${solicitudesNoDictaminadas}'>
                            <tr>
                                <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    folio <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.folio}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    cliente <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.nombreCliente}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    ESTATUS <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.statusDeSolicitud}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    PDV <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.puntoDeVenta}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    FUENTE <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.autenticadoMediante}</span>
                                </td>
                                <td class="left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    PRODUCTO <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.producto}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    FECHA <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.fechaDeSolicitud}</span>
                                </td>
                                <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    MONTO <br>
                                    <span class="font14 textlower tableDescriptionColor">${solicitud.montoCredito}</span>
                                </td>
                                <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                    <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(${solicitud.id});">ver detalle</button>
                                </td>
                            </tr>
                        </g:each>
                    </g:if>
                    <g:else>
                        <tr>
                            <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                                <span class="font14 textlower tableDescriptionColor">No hay solicitudes por dictaminar</span>
                            </td>
                        </tr>
                    </g:else>
                </table>
            </section>
            <section class="container marginBottom50 marginTop50">
                <div class="width480 autoMargin solicitudBox">
                    <div class="autoMargin">
                        <ul class="clearFix">
                            <li class="floatLeft">
                                <a href="#" title="Previous Page" class="font14 fontWeight600 displayInline pageMarker">
                                    <i class="fa fa-angle-left" aria-hidden="true"></i>
                                </a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 1" class="font14 fontWeight400 displayInline pageMarker pageSelected">1</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 2" class="font14 fontWeight400 displayInline pageMarker">2</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 3" class="font14 fontWeight400 displayInline pageMarker">3</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 4" class="font14 fontWeight400 displayInline pageMarker">4</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="..." class="font14 fontWeight400 displayInline pageMarker">...</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Page 12" class="font14 fontWeight400 displayInline pageMarker">12</a>
                            </li>
                            <li class="floatLeft">
                                <a href="#" title="Next Page" class="font14 fontWeight600 displayInline pageMarker">
                                    <i class="fa fa-angle-right" aria-hidden="true"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>

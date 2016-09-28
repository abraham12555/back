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
                        <li class="floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a href="#" title="DICTAMINADAS" class="displayInline font20 fontWeight500 darkBluetitle padding20">DICTAMINADAS</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="POR DICTAMINAR" class="displayInline font20 fontWeight500 darkBluetitle opacity05 padding20">POR DICTAMINAR</a>
                        </li>
                    </ul>
                </div>
                <div class="lightGrayBG">
                    <ul class="clearFix paddingLeft30 solicitude_submenu">
                        <li class="floatLeft">
                            <a href="#" title="Día" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Día</a>
                        </li>
                        <li class="floatLeft">
                            <a href="#" title="Semana" class="displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Semana</a>
                        </li>
                        <li class="floatLeft">
                            <a href="#" title="Mes" class="displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Mes</a>
                        </li>
                        <li class="floatLeft">
                            <a href="#" title="Año" class="displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Año</a>
                        </li>
                        <li class="floatLeft">
                            <a href="#" title="Por Fecha" class="displayInline gray font16 fontweight300 paddingTop10 paddingBottom10 paddingLeft20 paddingRight20">Por Fecha</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>

        <section class="container marginBottom50 ">

            <table class="applicationContainers solicitudes_table width990 autoMargin">
                <thead>
                <th colspan="9" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes</h1></th>
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
                <tr>
                    <td  class="tableBorderBottom left tableTitleColor textUpper">
                        folio <br>
                        <span class="font14 textlower tableDescriptionColor">00021</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor textUpper">
                        cliente <br>
                        <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        ESTATUS <br>
                        <span class="font14 textlower tableDescriptionColor">En Proceso</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PDV <br>
                        <span class="font14 textlower tableDescriptionColor">Cuajimalpa</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FUENTE <br>
                        <span class="font14 textlower tableDescriptionColor">Google</span>
                    </td>
                    <td class="tableBorderBottom left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PRODUCTO <br>
                        <span class="font14 textlower tableDescriptionColor">CS</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FECHA <br>
                        <span class="font14 textlower tableDescriptionColor">10/04/2016</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        MONTO <br>
                        <span class="font14 textlower tableDescriptionColor">$250,000.00</span>
                    </td>
                    <td class="center tableBorderBottom colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(1);">ver detalle</button>
                    </td>
                </tr>

                <tr>
                    <td  class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        folio <br>
                        <span class="font14 textlower tableDescriptionColor">00021</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        cliente <br>
                        <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        ESTATUS <br>
                        <span class="font14 textlower tableDescriptionColor">En Proceso</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PDV <br>
                        <span class="font14 textlower tableDescriptionColor">Cuajimalpa</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FUENTE <br>
                        <span class="font14 textlower tableDescriptionColor">Google</span>
                    </td>
                    <td class="tableBorderBottom left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PRODUCTO <br>
                        <span class="font14 textlower tableDescriptionColor">CS</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FECHA <br>
                        <span class="font14 textlower tableDescriptionColor">10/04/2016</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        MONTO <br>
                        <span class="font14 textlower tableDescriptionColor">$250,000.00</span>
                    </td>
                    <td class="center tableBorderBottom colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(2);">ver detalle</button>
                    </td>
                </tr>

                <tr>
                    <td  class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        folio <br>
                        <span class="font14 textlower tableDescriptionColor">00021</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        cliente <br>
                        <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        ESTATUS <br>
                        <span class="font14 textlower tableDescriptionColor">En Proceso</span>
                    </td>
                    <td class=" tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PDV <br>
                        <span class="font14 textlower tableDescriptionColor">Cuajimalpa</span>
                    </td>
                    <td class="left tableBorderBottom tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FUENTE <br>
                        <span class="font14 textlower tableDescriptionColor">Google</span>
                    </td>
                    <td class="tableBorderBottom left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PRODUCTO <br>
                        <span class="font14 textlower tableDescriptionColor">CS</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FECHA <br>
                        <span class="font14 textlower tableDescriptionColor">10/04/2016</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        MONTO <br>
                        <span class="font14 textlower tableDescriptionColor">$250,000.00</span>
                    </td>
                    <td class="center tableBorderBottom colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(3);">ver detalle</button>
                    </td>
                </tr>

                <tr>
                    <td  class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        folio <br>
                        <span class="font14 textlower tableDescriptionColor">00021</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        cliente <br>
                        <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        ESTATUS <br>
                        <span class="font14 textlower tableDescriptionColor">En Proceso</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PDV <br>
                        <span class="font14 textlower tableDescriptionColor">Cuajimalpa</span>
                    </td>
                    <td class="tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FUENTE <br>
                        <span class="font14  textlower tableDescriptionColor">Google</span>
                    </td>
                    <td class="tableBorderBottom left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        PRODUCTO <br>
                        <span class="font14 textlower tableDescriptionColor">CS</span>
                    </td>
                    <td class=" tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        FECHA <br>
                        <span class="font14 textlower tableDescriptionColor">10/04/2016</span>
                    </td>
                    <td class=" tableBorderBottom left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        MONTO <br>
                        <span class="font14 textlower tableDescriptionColor">$250,000.00</span>
                    </td>
                    <td class="center tableBorderBottom colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(4);">ver detalle</button>
                    </td>
                </tr>
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
    </body>
</html>

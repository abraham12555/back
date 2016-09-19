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
        <input type="hidden" id="opcionMenu" value="4">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin">
                <div class="clearFix">
                    <ul class="floatLeft clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a title="VERIFICACIONES" class="displayInline font24 fontWeight700 darkBluetitle paddingTop20 paddingBottom15 paddingLeft20 paddingRight20">VERIFICACIONES</a>
                        </li>
                        <li id="agendaButton" class="opcVerificacion floatLeft lightGrayBG paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','agenda');" title="Ver agenda" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver agenda</a>
                        </li>
                        <li id="mapaButton" class="opcVerificacion floatLeft paddingLeft5 paddingRight5">
                            <a onclick="mostrarApartado('opcVerificacion','verificacionTab','mapa');" title="Ver mapa" class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ver mapa</a>
                        </li>
                    </ul>
                    <div class="floatRight whiteBox clearFix marginTop15 marginRight10">
                        <i class="fa fa-search gray2 paddingTop10 paddingBottom10 paddingLeft15 paddingRight10" aria-hidden="true"></i>
                        <input type="text" placeholder="Buscar" class="font14 fontweight500 gray2 paddingTop10 paddingBottom10 paddingLeft5">
                    </div>
                </div>
            </div>
        </section>
        <div class="verificacionTab" id="agenda">
            <section class="container marginBottom40 width990">

                <table class="applicationContainers solicitudes_table">
                    <thead>
                        <tr>
                            <td colspan="6" class="navyBg left">
                                <h1 class="graphHeading colorWhite letterspacing2 textUpper">LISTA DE PROSPECTOS</h1>
                            </td>
                        </tr>
                    </thead>
                    <tr>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(1);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(2);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(3);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(4);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(5);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(6);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(7);">ver detalle</button>
                        </td>
                    </tr>

                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">00021</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">Mariana Hernandez F.</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">Orizaba 234. Int. 23 Col. Roma Norte. Cuauhtem…</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            HORARIO <br>
                            <span class="font14 textlower tableDescriptionColor">9:40 AM</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">A TIEMPO</span>
                        </td>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(8);">ver detalle</button>
                        </td>
                    </tr>
                </table>
            </section>
        </div>
        <div class="verificacionTab" id="mapa" style="display: none;">
            <section class="container marginBottom84">
                <div class="width990 autoMargin clearFix">
                    <div class="solicitudWhiteBox radius2 marginBottom30">
                        <div class="darkBlueBG radius2">
                            <p class="paddingLeft32 colorWhite letterspacing2 fontWeight600 font18 paddingTop16 paddingBottom11">MAPA DE PROSPECTOS</p>
                        </div>
                        <div class="padding15">
                            <div class="height477 overflowHide autoMargin">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d481726.8873951675!2d-99.42381479170352!3d19.391166885849092!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sca!4v1467138517694" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <section class="container">
            <div class="width480 autoMargin solicitudBox marginBottom84">
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

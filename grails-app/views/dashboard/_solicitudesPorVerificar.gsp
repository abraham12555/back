<section class="container marginBottom50 ">

    <table class="applicationContainers solicitudes_table width990 autoMargin">
        <thead>
            <tr>
                <td colspan="6" class="navyBg left">
                    <h1 class="graphHeading colorWhite letterspacing2 textUpper">LISTA DE PROSPECTOS</h1>
                </td>
            </tr>
        </thead>
        <tbody>
            <g:if test="${solicitudesPorVerificar}">
                <g:each var='solicitud' in='${solicitudesPorVerificar}'>
                    <tr>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud.folio}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            PROSPECTO <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud.cliente}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper tableBorderBottom">
                            DIRECCIÓN <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud.direccion}</span>
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
                            <button class="greenBox colorWhite" type="button" onclick="realizarVerificacion(${solicitud.id});">ver detalle</button>
                        </td>
                    </tr>
                </g:each>
            </g:if>
            <g:else>
                <tr>
                    <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">No hay solicitudes para verificación</span>
                    </td>
                </tr>
            </g:else>
        </tbody>
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
<section class="container paddingBottom38">
    <table id='listaDeSolicitudesBusqueda' class="applicationContainers solicitudes_table dashboard">
        <thead>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>
            <th colspan="8" class="left navyBg">
                <h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes </h1>
            </th>
            <th class="darkGray">
                <a href="${createLink(controller:'dashboard', action:'solicitudes')}">
                    <p class=" vermasLine center colorWhite fontWeight300">VER TODAS</p>
                </a>
            </th>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMINISTRADOR, ROLE_EJECUTIVO, ROLE_SUCURSAL,ROLE_CAJERO'>
            <th colspan="9" class="left navyBg">
                <h1 class="graphHeading colorWhite letterspacing2 textUpper">Solicitudes </h1>
            </th>
        </sec:ifAnyGranted>
        </thead>
        <tbody><div id="lista1">
            <g:if test="${solicitudes}">
                <g:each var='solicitud' in='${solicitudes}'>
                    <tr>
                        <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            folio <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.folio}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            cliente <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.nombreCliente}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            ESTATUS <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.statusDeSolicitud}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            PDV <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.puntoDeVenta}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            FUENTE <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.autenticadoMediante}</span>
                        </td>
                        <td class="left font12 tableTitleColor paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            PRODUCTO <br>
                            <span class="font14 textlower tableDescriptionColor">${solicitud?.producto}</span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            FECHA <br>
                            <span class="font14 textlower tableDescriptionColor"><g:formatDate format="dd/MM/yyyy" date="${solicitud?.fechaDeSolicitud}"/></span>
                        </td>
                        <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            MONTO <br>
                            <span class="font14 textlower tableDescriptionColor"><g:formatNumber number="${solicitud?.montoCredito}" format="\044###,###,###.##"/></span>
                        </td>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA,ROLE_EJECUTIVO,ROLE_SUCURSAL,ROLE_CAJERO'>
                    <g:if test="${solicitud?.folio && solicitud?.folio != "-"}">
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            <button class="greenBox colorWhite" type="button" onclick="consultarSolicitud(${solicitud?.id},false);">ver detalle</button>
                        </td>
                    </g:if>
                    <g:else>
                        <td class="center colorWhite font14 paddingTop5 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                            <button class="greenBox colorWhite" type="button">ver detalle</button>
                        </td>
                    </g:else>
                </sec:ifAnyGranted>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <tr>
                <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                    <span class="font14 textlower tableDescriptionColor">No hay solicitudes registradas</span>
                </td>
            </tr>
        </g:else>
        </tbody> </div>
    </table>
</section>
<g:if test="${cuantos}">
    <input type ="hidden" id="folioBuscado" value ="${folio}" />
    <input type ="hidden" id="nombreBuscado" value ="${nombre}" />
    <input type ="hidden" id="apellidoMaternoBuscado" value ="${apellidoMaterno}" />
    <input type ="hidden" id="apellidoPaternoBuscado" value ="${apellidoPaterno}" />
    <input type ="hidden" id="rfcBuscado" value ="${rfc}" />
    <input type ="hidden" id="cuantos" value ="${cuantos}" />
    <input type ="hidden" id="totalPages" value ="${totalPages}" />
    <input type ="hidden" id="page" value ="${page}" />
</g:if>
<section class="container">
    <div class="width480 autoMargin solicitudBox marginBottom84">
        <div class="autoMargin">
            <input type="hidden" id="currentPageSolicitudesBusqueda"/>
            <ul class="clearFix" id="paginationSolicitudesBusqueda">
            </ul>
        </div>
    </div>
</section>





<g:urlContextAware value="/dashboard/getUsers" var="urlGetUsers"/>
<script>
$.getUsers = "${urlGetUsers}";
$(document).ready(function () {
    var idPaginacion = "paginationSolicitudesBusqueda";
        var totalPages = $('#totalPages').val();
        var page = $('#page').val();
        var folio = $('#folioBuscado').val();
        var nombre = $('#nombreBuscado').val();
        var apellidoPaterno = $('#apellidoPaternoBuscado').val();
        var apellidoMaterno = $('#apellidoMaternoBuscado').val();
        var rfc = $('#rfcBuscado').val();
        
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
		event.preventDefault();
		var page = $(this).data('page');
		//buscar(page,idPaginacion);
                getSolicitudesBusqueda(page, idPaginacion, folio, nombre, apellidoPaterno, apellidoMaterno, rfc)
	    });

});
</script>
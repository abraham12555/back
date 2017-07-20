<section class="container marginBottom50 ">
    <table id="listaDeSolicitudesNoDictaminadas" class="applicationContainers solicitudes_table width990 autoMargin">
        <thead>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>
            <th colspan="9" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes por dictaminar</h1></th>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <th colspan="8" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes por dictaminar</h1></th>
        </sec:ifAnyGranted>
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
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA'>
            <td class="gray"></td>
        </sec:ifAnyGranted>
        </tr>
    </table>
</section>
<section class="container">
    <div class="width480 autoMargin solicitudBox marginBottom84">
        <div class="autoMargin">
            <input type="hidden" id="fechaInicio" value=""/>
            <input type="hidden" id="fechaFinal" value="" />
            <input type="hidden" id="template" value="noDictaminadas"/>
            <input type="hidden" id="temporalidadSolicitudesNoDictaminadas" value =1 />
            <input type="hidden" id="currentPageSolicitudesNoDictaminadas"/>
            <ul class="clearFix" id="paginationSolicitudesNoDictaminadas">
            </ul>
        </div>
    </div>
</section>

<g:urlContextAware value="/dashboard/getSolicitudesBusqueda" var="urlSolicitudesBusqueda"/>
<script>
    $.getSolicitudesBusqueda = "${urlSolicitudesBusqueda}";
    $(document).ready(function () {
    var fechaInicio = $("#fechaInicio").val();
    var fechaFinal = $("#fechaFinal").val();

    var idPaginacion = "paginationSolicitudesNoDictaminadas";
    $('#'+idPaginacion).on('click', 'a.page', function (event) {
    var fechaInicio = $("#fechaInicio").val();
    var fechaFinal = $("#fechaFinal").val();
    var temporalidad = $("#temporalidadSolicitudesNoDictaminadas").val();
    var template = $("#template").val();
    event.preventDefault();
    var page = $(this).data('page');
    if (fechaInicio && fechaFinal ){
    console.log("preparandome para buscar");
    consultarSolicitudesPorTiempo(temporalidad,"noDictaminadas",fechaInicio,fechaFinal,"listaDeSolicitudesNoDictaminadas",idPaginacion,"temporalidadSolicitudesNoDictaminadas",page);
    }else{
    getSolicitudesBusqueda(page,idPaginacion,temporalidad,template,"listaDeSolicitudesNoDictaminadas",null,null);
    }
    });
    
     getSolicitudesBusqueda(1,idPaginacion,1,"noDictaminadas","listaDeSolicitudesNoDictaminadas",null,null);
    
    });

    function getSolicitudesBusqueda(page,idPaginacion,temporalidad,template,idTabla,fechaInicio,fechaFinal) {
    $("#currentPageSolicitudesNoDictaminadas").val(page);
    $("#temporalidadSolicitudesNoDictaminadas").val(temporalidad);
    var filter = new Object();
    filter.page = page;
    filter.temporalidad = temporalidad;
    filter.template = template;
    filter.fechaInicio = fechaInicio;
    filter.fechaFinal = fechaFinal;
    console.log("adentro");
    console.log(page);
    $.ajax({
    type: "POST",
    dataType: "json",
    url: ${urlSolicitudesBusqueda},
    data: JSON.stringify(filter),
    contentType: "application/json",
    success: function (response) {
    var page = response.page;
    var totalPages = response.totalPages;
    $('#'+idPaginacion).empty();
    $("#listaDeSolicitudesNoDictaminadas").empty();
    if (response.solicitudes.length > 0) {
    pagination(totalPages, page,idPaginacion);
    mostrarSolicitudesFechas(response.solicitudes,idTabla);
    } else {
    var row = "";
              row += "<tr>";
                row += "<td colspan='9'  class='left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper'>";
                row += "<span class='font14 textlower tableDescriptionColor'>No hay solicitudes registradas</span>";
                row += "</td>";
                row += "</tr>";
    $("#"+idTabla+'tbody').html(row);
    }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {

    }
    });
    }
</script>

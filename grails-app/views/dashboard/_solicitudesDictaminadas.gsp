<section class="container marginBottom50 ">

    <table id="listaDeSolicitudesDictaminadas" class="applicationContainers solicitudes_table width990 autoMargin">
        <thead>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA,ROLE_CAJERO'>
            <th colspan="9" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes dictaminadas</h1></th>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <th colspan="8" class="navyBg left"><h1 class="graphHeading colorWhite letterspacing2 textUpper">solicitudes dictaminadas</h1></th>
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
            <input type="hidden" id="fechaInicioDictaminadas" value=""/>
            <input type="hidden" id="fechaFinalDictaminadas" value="" />
          <input type="hidden" id="templateDictaminadas" value="dictaminadas"/>
           <input type="hidden" id="temporalidadSolicitudesDictaminadas" value =1 />
            <input type="hidden" id="currentPageSolicitudesDictaminadas"/>
            <ul class="clearFix" id="paginationSolicitudesDictaminadas">
            </ul>
        </div>
    </div>
</section>
<script>
    
<g:urlContextAware value="/dashboard/getSolicitudesBusqueda" var="urlSolicitudesBusqueda"/>
$.getSolicitudesBusqueda = "${urlSolicitudesBusqueda}";
 
$(document).ready(function () {
    var fechaInicio = $("#fechaInicioDictaminadas").val();
    var fechaFinal = $("#fechaFinalDictaminadas").val();
    
    var idPaginacion = "paginationSolicitudesDictaminadas";
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
        var fechaInicio = $("#fechaInicioDictaminadas").val();
         var fechaFinal = $("#fechaFinalDictaminadas").val();
        var temporalidad = $("#temporalidadSolicitudesDictaminadas").val();
        var template = $("#templateDictaminadas").val();
	event.preventDefault();
        var page = $(this).data('page');
         if (fechaInicio && fechaFinal ){
    consultarSolicitudesPorTiempo(temporalidad,"dictaminadas",fechaInicio,fechaFinal,"listaDeSolicitudesDictaminadas",idPaginacion,"temporalidadSolicitudesDictaminadas",page);
    }else {
        getSolicitudesBusqueda(page,idPaginacion,temporalidad,template,"listaDeSolicitudesDictaminadas",null,null);
    }
        });
	getSolicitudesBusqueda(1,idPaginacion,1,"dictaminadas","listaDeSolicitudesDictaminadas",null,null);
});

function getSolicitudesBusqueda(page,idPaginacion,temporalidad,template,idTabla,fechaInicio,fechaFinal) {
    $("#currentPageSolicitudesDictaminadas").val(page);
    $("#temporalidadSolicitudesDictaminadas").val(temporalidad);
    var filter = new Object();
    filter.page = page;
    filter.temporalidad = temporalidad;
    filter.template = template;
    filter.fechaInicio = fechaInicio;
    filter.fechaFinal = fechaFinal;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getSolicitudesBusqueda,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#'+idPaginacion).empty();
            $("#listaDeSolicitudesDictaminadas").empty();

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
                $("#"+idTabla+'tbody').append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
        }
    });
}




</script>
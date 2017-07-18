<!DOCTYPE HTML>
<div id ="listaPasosSolicitud"></div>
 <section class="container">
        <div class="width480 autoMargin solicitudBox marginBottom84">
            <div class="autoMargin">
                <input type="hidden" id="currentPagePasosSolicitud"/>
                <ul class="clearFix" id="paginationPasosSolicitud">
                </ul>
            </div>
        </div>
    </section>

<g:render template="configuracion/pasoSolicitudEntidadFinanciera/altaPasoSolicitudEntidadFinanciera"/>
<div id="detallePasoSolicitud"></div>
<div id="elRender"></div>


<g:urlContextAware value="/dashboard/getPasosSolicitud" var="urlPasosSolicitud"/>
<script>
$.getPasosSolicitud = "${urlPasosSolicitud}";
$(document).ready(function () {
    var idPaginacion = "paginationPasosSolicitud";
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
		event.preventDefault();
		var page = $(this).data('page');
		getPasosSolicitud(page,idPaginacion);
	    });
	getPasosSolicitud(1,idPaginacion);
});

function getPasosSolicitud(page,idPaginacion) {
    $("#currentPagePasosSolicitud").val(page);
    
    var filter = new Object();
    filter.page = page;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getPasosSolicitud,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#'+idPaginacion).empty();
            $("#listaPasosSolicitud").empty();
            if (response.pasosSolicitud.length > 0) {
            pagination(totalPages, page,idPaginacion);
            mostrarPasosSolicitudPaginados(response.pasosSolicitud);

            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Pasos Registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#listaPasosSolicitud").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
        }
    });
}




</script>

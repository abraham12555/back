<div id="listaServicioDeAsistencia"></div>
    <section class="container">
        <div class="width480 autoMargin solicitudBox marginBottom84">
            <div class="autoMargin">
                <input type="hidden" id="currentPageServicioDeAsistencia"/>
                <ul class="clearFix" id="paginationServicioDeAsistencia">
                </ul>
            </div>
        </div>
    </section>
<g:render template="configuracion/servicioDeAsistencia/altaServicioDeAsistencia"/>
<div id="detalleServicioDeAsistencia"></div>

<script>
$.getServicioDeAsistencia = "/dashboard/getServicioDeAsistencia";
 
$(document).ready(function () {
    var idPaginacion = "paginationServicioDeAsistencia";
    
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
		event.preventDefault();
		var page = $(this).data('page');
		getServicioDeAsistencia(page,idPaginacion);
	    });
	getServicioDeAsistencia(1,idPaginacion);
});

function getServicioDeAsistencia(page,idPaginacion) {
    $("#currentPageServicioDeAsistencia").val(page);
    var filter = new Object();
    filter.page = page;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getServicioDeAsistencia,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#'+idPaginacion).empty();
            $("#listaServicioDeAsistencia").empty();
            if (response.servicioDeAsistencia.length > 0) {
            pagination(totalPages, page,idPaginacion);
            mostrarServicioDeAsistenciaPaginados(response.servicioDeAsistencia);

            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Servicio de Asistencia registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#listaServicioDeAsistencia").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
        }
    });
}

</script>
<div id="listaSeguroSobreDeuda"></div>
    <section class="container">
        <div class="width480 autoMargin solicitudBox marginBottom84">
            <div class="autoMargin">
                <input type="hidden" id="currentPageSegurosSobreDeuda"/>
                <ul class="clearFix" id="paginationSegurosSobreDeuda">
                </ul>
            </div>
        </div>
    </section>
<g:render template="configuracion/seguroSobreDeuda/altaSeguroSobreDeuda"/>
<div id="detalleSeguroSobreDeuda"></div>

<script>
$.getSegurosSobreDeuda = "/dashboard/getSegurosSobreDeuda";
 
$(document).ready(function () {
    var idPaginacion = "paginationSegurosSobreDeuda";
    
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
		event.preventDefault();
		var page = $(this).data('page');
		getSegurosSobreDeuda(page,idPaginacion);
	    });
	getSegurosSobreDeuda(1,idPaginacion);
});

function getSegurosSobreDeuda(page,idPaginacion) {
    $("#currentPageSegurosSobreDeuda").val(page);
    var filter = new Object();
    filter.page = page;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getSegurosSobreDeuda,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#'+idPaginacion).empty();
            $("#listaSeguroSobreDeuda").empty();
            if (response.segurosSobreDeuda.length > 0) {
            pagination(totalPages, page,idPaginacion);
            mostrarSegurosSobreDeudaPaginados(response.segurosSobreDeuda);

            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Seguros sobre Deuda registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#listaSeguroSobreDeuda").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
        }
    });
}




</script>